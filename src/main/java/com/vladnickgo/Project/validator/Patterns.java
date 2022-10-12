package com.vladnickgo.Project.validator;

import java.util.regex.Pattern;

public class Patterns {
    public static final String REGEX_FOR_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(REGEX_FOR_EMAIL);
    public static final String REGEX_FOR_FIRST_NAME = "^[A-ZА-ЯЁІЇ][a-zа-яёії]*([-][A-ZА-ЯЁІЇ][a-zа-яёії]*)*";
    public static final Pattern FIRST_NAME_PATTERN = Pattern.compile(REGEX_FOR_FIRST_NAME);
    public static final String REGEX_FOR_LAST_NAME = "^[A-ZА-ЯЁІЇ][a-zа-яёіЇ]*([-][A-ZА-ЯЁІЇ][a-zа-яёії]*)*";
    public static final Pattern LAST_NAME_PATTERN = Pattern.compile(REGEX_FOR_LAST_NAME);
    public static final String REGEX_FOR_PASSWORD = "^([A-Za-z0-9_-]+)";
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(REGEX_FOR_PASSWORD);
}
