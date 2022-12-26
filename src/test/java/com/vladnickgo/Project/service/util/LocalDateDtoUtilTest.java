package com.vladnickgo.Project.service.util;

import com.vladnickgo.Project.controller.dto.LocalDateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class LocalDateDtoUtilTest {
    private static final LocalDate TEST_LOCAL_DATE_START=LocalDate.parse("2022-12-01");
    private static final LocalDate TEST_LOCAL_DATE_END=LocalDate.parse("2022-12-30");
    private static final LocalDateDtoUtil TEST_LOCAL_DATE_DTO_UTIL =
            new LocalDateDtoUtil("2022-12-01", "2022-12-30");
    @Mock
    LocalDate localDate;
    @InjectMocks
    LocalDateDtoUtil localDateDtoUtil;

    @Test
    void getLocalDateDtoTest() {
        LocalDateDto actual = TEST_LOCAL_DATE_DTO_UTIL.getLocalDateDto();
        LocalDateDto expected= LocalDateDto.newBuilder()
                .dateStart(TEST_LOCAL_DATE_START)
                .dateEnd(TEST_LOCAL_DATE_END)
                .build();
        Assertions.assertEquals(expected,actual);
    }

}