package com.compostage;

public class ServerQueries {

    private static final String HOST = "192.168.0.102";

    public static final String GET_USER_INFO = "http://" + HOST +
            "/controller/java/getUserInfo.php?username=%s";

    public static final String VALIDATE_PASSWORD = "http://" + HOST +
            "/controller/java/testPassword.php?to_test=%s&encrypted_password=%s";

    public static final String GET_ENCRYPTED_PASSWORD = "http://" + HOST +
            "/controller/java/getEncrypedPassword.php?password=%s";

    public static final String SEND_MEASUREMENT_DATA = "http://" + HOST +
            "/controller/java/sendMeasurementData.php?sensor_id=%i&value=%f&type_id=%i&";


    private static final String SYNC_USER_INFO = "http://" + HOST +
            "/controller/java/updateUserInfo.php?username=%s";

    public static String getSyncUserInfoString(String username, String password, String email,
                                               String auth_question, String auth_answer) {

        String toBuild;

        if (username.isEmpty())
            return "";
        else
            toBuild = String.format(SYNC_USER_INFO, username);

        if (!password.isEmpty())
            toBuild += "&password=" + password;

        if (!email.isEmpty())
            toBuild += "&email=" + email;

        if (!auth_question.isEmpty())
            toBuild += "&user_auth_question=" + auth_question;

        if (!auth_answer.isEmpty())
            toBuild += "&user_auth_answer=" + auth_answer;

        return toBuild;
    }


}
