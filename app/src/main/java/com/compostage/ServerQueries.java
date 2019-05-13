package com.compostage;

class ServerQueries {

    private static final String HOST = "192.168.0.102";

    static final String GET_USER_INFO = "http://" + HOST +
            "/controller/java/getUserInfo.php?username=%s";

    static final String VALIDATE_PASSWORD = "http://" + HOST +
            "/controller/java/testPassword.php?to_test=%s&encrypted_password=%s";

    static final String GET_ENCRYPTED_PASSWORD = "http://" + HOST +
            "/controller/java/getEncrypedPassword.php?password=%s";

    static final String SEND_MEASUREMENT_DATA = "http://" + HOST +
            "/controller/java/sendMeasurementData.php?sensor_id=%i&value=%f&type_id=%i&";


    //Note : user_auth_answer will be encrypted during this request
    static final String SYNC_USER_INFO = "http://" + HOST +
            "/controller/java/updateUserInfo.php?username=%s&user_password=%s&user_email=%s&user_auth_question=%s&user_auth_answer=%s";

}
