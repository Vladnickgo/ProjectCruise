USE cruise;
DROP PROCEDURE IF EXISTS change_the_cruise_status_on_in_progress_or_finished;
DROP PROCEDURE IF EXISTS change_date_end_for_cabin_status;
DELIMITER //
CREATE PROCEDURE change_the_cruise_status_on_in_progress_or_finished()
BEGIN
    DECLARE date_now DATE;
    SELECT curdate() INTO date_now;
    UPDATE cruises SET cruise_status_id=2 WHERE date_start >= date_now AND date_end <= date_now;
    UPDATE cruises SET cruise_status_id=3 WHERE date_end > date_now;
END;
//
CREATE PROCEDURE change_date_end_for_cabin_status()
BEGIN
    DECLARE max_date_end DATE;
    DECLARE done INT default 0;
    DECLARE date_end_cursor CURSOR FOR SELECT max(status_end) FROM cabin_statuses GROUP BY cabin_id;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
    OPEN date_end_cursor;
    WHILE done = 0
        DO
            FETCH date_end_cursor INTO max_date_end;
            UPDATE cabin_statuses
            SET status_end = DATE_ADD(now(), INTERVAL 1 YEAR)
            WHERE status_end = max_date_end
              AND status_statement_id = 1;
        END WHILE;
    CLOSE date_end_cursor;
END;
//
CALL change_the_cruise_status_on_in_progress_or_finished();
CALL change_date_end_for_cabin_status();
//
DROP EVENT IF EXISTS exec_change_the_status;
DROP EVENT IF EXISTS exec_change_date_end;
//
SET GLOBAL event_scheduler = ON;
CREATE EVENT exec_change_the_status
    ON SCHEDULE EVERY 24 HOUR
        STARTS '2022-10-01 00:00:00'
    ON COMPLETION NOT PRESERVE ENABLE
    DO
    CALL change_the_cruise_status_on_in_progress_or_finished;
//
CREATE EVENT exec_change_date_end
    ON SCHEDULE EVERY 24 HOUR
        STARTS '2022-10-01 00:00:00'
    ON COMPLETION NOT PRESERVE ENABLE
    DO
    CALL change_date_end_for_cabin_status;
//