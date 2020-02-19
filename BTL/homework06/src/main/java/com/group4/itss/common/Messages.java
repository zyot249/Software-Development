package com.group4.itss.common;

public class Messages {
    public final String BLANK_EMAIL_MSG = "You cannot leave the email field blank!";
    public final String BLANK_PASSWORD_MSG = "You cannot leave the password field blank!";
    public final String BLANK_CONFIRM_PASSWORD_MSG = "You cannot leave the confirm password field blank!";
    public final String BLANK_NAME_MSG = "You cannot leave the name field blank!";
    public final String BLANK_PHONE_MSG = "You cannot leave the phone field blank!";

    public final String WRONG_EMAIL_MSG = "Wrong email!";
    public final String TOO_SHORT_PASSWORD_MSG = "The password must contain at least 8 characters!";
    public final String WRONG_CONFIRM_PASSWORD_MSG = "The confirm password must be similar with password!";
    public final String WRONG_DOB_MSG = "Wrong DOB!";
    public final String WRONG_PHONE_MSG = "Wrong phone number!";

    public final String CANCEL_SIGN_UP_CONFIRM_MSG = "Are you sure to cancel signing up an account?";
    public final String CANCEL_UPDATE_INFO_CONFIRM_MSG = "Are you sure to cancel updating supplier's information?";
    public final String SUBMIT_CONFIRM_MSG = "Are you sure all the field you filled in are correct?";

    public final String EXISTED_EMAIL_MSG = "Email is existed! Please choose another one!";
    public final String REGISTER_SUCCESS_MSG = "Register successfully!";
    public final String UPDATE_SUCCESS_MSG = "Update successfully!";

    public final String ERROR_MSG = "Something's wrong! Please try again after minutes!";

    public final String LOGIN_WRONG_PASSWORD_MSG = "Invalid Password!";
    public final String NOT_FOUND_EMAIL_MSG = "Can not find account associate with this email!";

    public final String UPDATE_ORDER_SUCCESS_MSG = "Order is updated successfully!";
    public final String SUBSCRIBED_MERCHANDISE_MSG = "All merchandise in this order are subscribed!";

    public final String NO_SELECTED_ROW_MSG = "Please choose a order before subscribing!";
    public final String LOGOUT_CONFIRM_MSG = "Are you sure to log out system?";


    public static Messages instance;
    private Messages() {

    }

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }
        return instance;
    }
}
