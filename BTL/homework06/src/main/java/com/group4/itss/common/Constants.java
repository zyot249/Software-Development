package com.group4.itss.common;

public class Constants {
    public static final String[] TRANSPORTATION_TYPES = {"Air", "Train"};
    public static final String[] EMPLOYEE_SYSTEMS = {"Sale", "Stock", "Order"};
    public static final Roles ROLES = Roles.getInstance();
    public static final Messages MESSAGES = Messages.getInstance();

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    public static final String SUPPLIER_ORDER_LINE_UNCHECKED_STATUS = "N";
    public static final String SUPPLIER_ORDER_LINE_CHECKED_STATUS = "Y";

    public static final int MIN_PASSWORD_CHARACTERS = 8;

    private static final int[] SCREEN_SIZE = {900, 522};
    private static final int[] POPUP_SCREEN_SIZE = {800, 422};

    public static int getScreenWidth() {
        return SCREEN_SIZE[0];
    }

    public static int getScreenHeight() {
        return SCREEN_SIZE[1];
    }

    public static int getPopupScreenWidth() {
        return POPUP_SCREEN_SIZE[0];
    }

    public static int getPopupScreenHeight() {
        return POPUP_SCREEN_SIZE[1];
    }

    public static final int LOG_ROUND = 12;
}

