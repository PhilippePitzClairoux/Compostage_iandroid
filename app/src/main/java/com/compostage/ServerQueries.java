package com.compostage;

class ServerQueries {

    private static final String HOST = "192.168.0.102";

    static final String VALIDATE_USERNAME = "http://" + HOST +
            "/controller/java/getUserInfo.php?username=%s";

    static final String VALIDATE_PASSWORD = "http://" + HOST +
            "/controller/java/testPassword.php?to_test=%s&encrypted_password=%s";

    static final String GET_ENCRYPTED_PASSWORD = "http://" + HOST +
            "/controller/java/getEncrypedPassword.php?password=%s";

    static final String SEND_MEASUREMENT_DATA = "http://" + HOST +
            "/controller/java/sendMeasurementData.php?sensor_id=%i&value=%f&type_id=%i&";

}
