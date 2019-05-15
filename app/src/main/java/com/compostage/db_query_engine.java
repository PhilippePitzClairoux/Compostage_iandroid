package com.compostage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.io.File;

public class db_query_engine extends SQLiteOpenHelper{

    // Logcat tag
    private static final String LOG = db_query_engine.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "compostage.db";

    // Table Names
    private static final String TABLE_ALERT_CONFIGURATION = "alert_configuration";
    private static final String TABLE_TA_ALERT_EVENT = "ta_alert_event";
    private static final String TABLE_ALERT_TYPE = "alert_type";
    private static final String TABLE_BED = "bed";
    private static final String TABLE_MEASURES = "measures";
    private static final String TABLE_MEASURE_TYPE = "measure_type";
    private static final String TABLE_PERMISSIONS = "permissions";
    private static final String TABLE_RASPBERRY_PI = "raspberry_pi";
    private static final String TABLE_RASPBERRY_PI_TYPE = "raspberry_pi_type";
    private static final String TABLE_SENSOR = "sensor";
    private static final String TABLE_SENSOR_STATE = "sensor_state";
    private static final String TABLE_SENSOR_TYPE = "sensor_type";
    private static final String TABLE_TA_MEASURE_TYPE = "ta_measure_type";
    private static final String TABLE_TA_SENSOR_ALERTS = "ta_sensor_alerts";
    private static final String TABLE_TA_USERS_PERMISSIONS= "ta_users_permissions";
    private static final String TABLE_UPDATE = "`update`";
    private static final String TABLE_UPDATE_COMPLETED = "update_completed";
    private static final String TABLE_UPDATE_STATE = "update_state";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_USER_TYPE = "user_type";
    private static final String TABLE_ZONE = "zone";

    //permissions table - column names
    private static final String KEY_PERMISSION_NAME = "permission_name";
    private static final String KEY_PERMISSION_DESCRIPTION = "permission_description";

    //user_type table - column names
    private static final String KEY_USER_TYPE_NAME = "user_type_name";
    private static final String KEY_USER_TYPE_DESCRIPTION = "user_type_description";

    //users table - column names
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_TYPE_ID = "user_type_id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_AUTH_QUESTION = "auth_question";
    private static final String KEY_AUTH_ANSWER = "auth_answer";

    //bed table - column names
    private static final String KEY_BED_ID = "_id";
    private static final String KEY_BED_NAME = "bed_name";

    //zone table - column names
    private static final String KEY_ZONE_ID = "zone_id";
    private static final String KEY_ZONE_NAME = "zone_name";

    //update_state table - column names
    private static final String KEY_UPDATE_STATE_ID = "update_state_id";
    private static final String KEY_UPDATE_STATE = "update_state";
    private static final String KEY_UPDATE_STATE_DESCRIPTION = "update_state_description";

    //update table - column names
    private static final String KEY_UPDATE_ID = "update_id";
    private static final String KEY_UPDATE_NAME = "update_name";
    private static final String KEY_UPDATE_DATE = "update_date";

    //raspberry_pi_type table - column names
    private static final String KEY_RASPBERRY_PI_TYPE= "raspberry_pi_type";
    private static final String KEY_RASPBERRY_PI_TYPE_DESCRIPTION= "raspberry_pi_type_description";

    //raspberry_pi table - column names
    private static final String KEY_RASPBERRY_PI_ID= "raspberry_pi_id";
    private static final String KEY_USER_ID= "user_id";
    private static final String KEY_RASPBERRY_PI_AQUISITION_DATE= "raspberry_pi_aquisition_date";
    private static final String KEY_RASPBERRY_PI_CAPACITY= "raspberry_pi_capacity";

    //alert_configuration - column names
    private static final String KEY_ALERT_CONFIGURATION_ID = "alert_configuration_id";
    private static final String KEY_ALERT_CONFIGURATION_MESSAGE = "alert_configuration_message";
    private static final String KEY_ALERT_CONFIGURATION_MIN_VALUE = "alert_configuration_min_value";
    private static final String KEY_ALERT_CONFIGURATION_MAX_VALUE = "alert_configuration_max_value";

    //sensor_state - column names
    private static final String KEY_SENSOR_STATE_ID = "sensor_state_id";
    private static final String KEY_SENSOR_STATE = "sensor_state";

    //sensor_type - column names
    private static final String KEY_SENSOR_TYPE_ID = "sensor_type_id";
    private static final String KEY_SENSOR_TYPE_NAME = "sensor_type_name";


    //alert_type - column names
    private static final String KEY_ALERT_TYPE_ID = "alert_type_id";
    private static final String KEY_ALERT_TYPE_NAME = "alert_type_name";

    //measure_type  - column names
    private static final String KEY_MEASURE_TYPE_ID = "measure_type_id";
    private static final String KEY_MEASURE_TYPE_NAME = "measure_type_name";

    //sensor - column names
    private static final String KEY_SENSOR_ID ="sensor_id";
    private static final String KEY_SENSOR_AQUISITION_DATE ="sensor_aquisition_date";
    private static final String KEY_SENSOR_SERIAL_NUMBER ="sensor_serial_number";

    //measures - column names
    private static final String KEY_MEASURE_ID ="measure_id";
    private static final String KEY_MEASURE_TIMESTAMP ="measure_timestamp";


    //ta_measure_type - column names
    private static final String KEY_MEASURE_VALUE ="measure_value";

    //ta_users_permissions - column names
    private static final String KEY_PERMISSION ="permission";
    private static final String KEY_USER_TYPE ="user_type";



    /************************************************/

    // Table Create Statements
    // Permissions table - create statement
    private static final String CREATE_PERMISSIONS = "CREATE TABLE "+ TABLE_PERMISSIONS+
            "("+KEY_PERMISSION_NAME+" VARCHAR(64) NOT NULL PRIMARY KEY ," +
            KEY_PERMISSION_DESCRIPTION +" VARCHAR(512) NOT NULL)";

    // User type table - create statement
    private static final String CREATE_USER_TYPE = "CREATE TABLE "+TABLE_USER_TYPE+" (" +
            KEY_USER_TYPE_NAME+ " VARCHAR(64) NOT NULL PRIMARY KEY , " +
            KEY_USER_TYPE_DESCRIPTION+" VARCHAR(256))";

    // Users table - create statement
    private static final String CREATE_USERS = "CREATE TABLE "+ TABLE_USERS +
            " ( "+KEY_USERNAME+" VARCHAR(64) NOT NULL PRIMARY KEY, "+KEY_USER_TYPE_ID+
            " VARCHAR(64) NOT NULL, "+KEY_PASSWORD+" VARCHAR(255) NOT NULL, " + KEY_EMAIL+
            " VARCHAR(64) NOT NULL, "+KEY_AUTH_QUESTION+" VARCHAR(1024) NOT NULL, "+KEY_AUTH_ANSWER+
            " VARCHAR(255) NOT NULL, CONSTRAINT users_ibfk_1 FOREIGN KEY( "+KEY_USER_TYPE_ID+" ) REFERENCES "+TABLE_USER_TYPE+"("+ KEY_USER_TYPE_NAME+"))";

    // Bed table - create statement
    private static final String CREATE_BED = "CREATE TABLE "+TABLE_BED+"( "+KEY_BED_ID+
            " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+KEY_BED_NAME +" VARCHAR(64) NOT NULL)";

    // zone table - create statement
    private static final String CREATE_ZONE ="CREATE TABLE "+TABLE_ZONE+" ( "+KEY_ZONE_ID+
            " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+KEY_BED_ID+" INTEGER NOT NULL, "+
            KEY_ZONE_NAME+" VARCHAR(64) NOT NULL, CONSTRAINT zone_ibfk_1 FOREIGN KEY("+
            KEY_BED_ID+") REFERENCES "+TABLE_BED+"("+KEY_BED_ID+"))";

    // update_state table - create statement
    private static final String CREATE_UPDATE_STATE ="CREATE TABLE "+TABLE_UPDATE_STATE+" ( " +
            KEY_UPDATE_STATE_ID+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_UPDATE_STATE+" VARCHAR(64) NOT NULL, " +
            KEY_UPDATE_STATE_DESCRIPTION+" VARCHAR(255) NOT NULL)";

    // update table - create statement
    private static final String CREATE_UPDATE = "CREATE TABLE "+TABLE_UPDATE+" ( " +
            KEY_UPDATE_ID+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_UPDATE_STATE_ID +" INTEGER NOT NULL, " +
            KEY_UPDATE_NAME+ " VARCHAR(64) NOT NULL, " +
            KEY_UPDATE_DATE +" DATETIME NOT NULL, " +
            "CONSTRAINT update_ibfk_1 FOREIGN KEY("+KEY_UPDATE_STATE_ID+") REFERENCES "+TABLE_UPDATE_STATE+"("+KEY_UPDATE_STATE_ID+"))";

    // raspberry_pi_type table - create statement
    private static final String CREATE_RASPBERRY_PI_TYPE = "CREATE TABLE "+TABLE_RASPBERRY_PI_TYPE+"( " +
            KEY_RASPBERRY_PI_TYPE +" VARCHAR(64) NOT NULL PRIMARY KEY, " +
            KEY_RASPBERRY_PI_TYPE_DESCRIPTION +" VARCHAR(255) NOT NULL)";

    //raspberry_pi table - create statement
    private static final String CREATE_RASPBERRY_PI = "CREATE TABLE "+TABLE_RASPBERRY_PI+"( "+
            KEY_RASPBERRY_PI_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_ZONE_ID +" INTEGER NOT NULL, " +
            KEY_USER_ID +" VARCHAR(64) NOT NULL, " +
            KEY_RASPBERRY_PI_TYPE +" VARCHAR(64) NOT NULL, " +
            KEY_RASPBERRY_PI_AQUISITION_DATE +" DATE NOT NULL, " +
            KEY_RASPBERRY_PI_CAPACITY +" INTEGER NOT NULL, " +
            "CONSTRAINT raspberry_pi_ibfk_1 FOREIGN KEY("+KEY_ZONE_ID+") REFERENCES "+TABLE_ZONE+"("+KEY_ZONE_ID+"), " +
            "CONSTRAINT raspberry_pi_ibfk_2 FOREIGN KEY("+KEY_RASPBERRY_PI_TYPE+") REFERENCES "+TABLE_RASPBERRY_PI_TYPE+"("+KEY_RASPBERRY_PI_TYPE+"), " +
            "CONSTRAINT raspberry_pi_ibfk_3 FOREIGN KEY("+KEY_USER_ID+") REFERENCES "+TABLE_USERS+"("+KEY_USERNAME+"))";

    //alert_configuration - create statement
    private static final String CREATE_ALERT_CONFIGURATION="CREATE TABLE "+TABLE_ALERT_CONFIGURATION+"( "+
            KEY_ALERT_CONFIGURATION_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            KEY_ALERT_CONFIGURATION_MESSAGE +" VARCHAR(64) NOT NULL, "+
            KEY_ALERT_CONFIGURATION_MIN_VALUE +" FLOAT, "+
            KEY_ALERT_CONFIGURATION_MAX_VALUE +" FLOAT)";

    //sensor_state - create statement
    private static final String CREATE_SENSOR_STATE="CREATE TABLE "+TABLE_SENSOR_STATE +"( " +
            KEY_SENSOR_STATE_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_SENSOR_STATE +" VARCHAR(64) NOT NULL)";

    //sensor_type - create statement
    private static final String CREATE_SENSOR_TYPE="CREATE TABLE "+TABLE_SENSOR_TYPE +"( " +
            KEY_SENSOR_TYPE_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_SENSOR_TYPE_NAME +" VARCHAR(64) NOT NULL)";

    //alert_type - create statement
    private static final String CREATE_ALERT_TYPE="CREATE TABLE "+TABLE_ALERT_TYPE+" ( " +
            KEY_ALERT_TYPE_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_ALERT_TYPE_NAME +" VARCHAR(255) NOT NULL)";

    //measure_type  - create statement
    private static final String CREATE_MEASURE_TYPE= "CREATE TABLE "+TABLE_MEASURE_TYPE +"(" +
            KEY_MEASURE_TYPE_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_MEASURE_TYPE_NAME +" VARCHAR(255) NOT NULL)";

    //sensor - create statement
    private static final String CREATE_SENSOR = "CREATE TABLE "+TABLE_SENSOR +"( " +
            KEY_SENSOR_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_SENSOR_TYPE_ID +" INTEGER NOT NULL, " +
            KEY_SENSOR_STATE_ID +" INTEGER NOT NULL, " +
            KEY_RASPBERRY_PI_ID +" INTEGER NOT NULL, " +
            KEY_SENSOR_AQUISITION_DATE +" DATE NOT NULL, " +
            KEY_SENSOR_SERIAL_NUMBER +" VARCHAR(64) NOT NULL, " +
            "CONSTRAINT sensor_ibfk_1 FOREIGN KEY("+KEY_SENSOR_TYPE_ID+") REFERENCES "+TABLE_SENSOR_TYPE+"("+KEY_SENSOR_TYPE_ID+"), " +
            "CONSTRAINT sensor_ibfk_2 FOREIGN KEY("+KEY_SENSOR_STATE_ID+") REFERENCES "+TABLE_SENSOR_STATE+"("+KEY_SENSOR_STATE_ID+"), " +
            "CONSTRAINT sensor_ibfk_3 FOREIGN KEY("+KEY_RASPBERRY_PI_ID+") REFERENCES "+TABLE_RASPBERRY_PI+"("+KEY_RASPBERRY_PI_ID+"))";

    //measures - create statement
    private static final String CREATE_MEASURE ="CREATE TABLE "+TABLE_MEASURES+"( " +
            KEY_MEASURE_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_SENSOR_ID +" INTEGER NOT NULL, " +
            KEY_MEASURE_TIMESTAMP +" DATETIME NOT NULL, " +
            "CONSTRAINT measure_ibfk_1 FOREIGN KEY("+KEY_SENSOR_ID+") REFERENCES "+TABLE_SENSOR+"("+KEY_SENSOR_ID+"))";

    //ta_alert_event - create statement
    private static final String CREATE_TA_ALERT_EVENT="CREATE TABLE "+TABLE_TA_ALERT_EVENT+" ( " +
            KEY_ALERT_TYPE_ID +" INTEGER NOT NULL, " +
            KEY_MEASURE_ID +" INTEGER NOT NULL, " +
            "CONSTRAINT ta_alert_event_ibfk_1 FOREIGN KEY("+KEY_ALERT_TYPE_ID+") REFERENCES "+TABLE_ALERT_TYPE+"("+KEY_ALERT_TYPE_ID+"), " +
            "CONSTRAINT ta_alert_event_ibfk_2 FOREIGN KEY("+KEY_MEASURE_ID+") REFERENCES "+TABLE_MEASURES+"("+KEY_MEASURE_ID+"), " +
            "CONSTRAINT ta_alert_event_ibpk PRIMARY KEY("+KEY_ALERT_TYPE_ID+", "+KEY_MEASURE_ID+"))";

    //ta_sensor_alerts - create statement
    private static final String CREATE_TA_SENSOR_ALERTS="CREATE TABLE "+TABLE_TA_SENSOR_ALERTS+"( " +
            KEY_ALERT_CONFIGURATION_ID +" INTEGER NOT NULL, " +
            KEY_SENSOR_ID +" INTEGER NOT NULL, " +
            "CONSTRAINT ta_sensor_alerts_ibfk_1 FOREIGN KEY("+KEY_ALERT_CONFIGURATION_ID+") REFERENCES "+TABLE_ALERT_CONFIGURATION+"("+KEY_ALERT_CONFIGURATION_ID+"), " +
            "CONSTRAINT ta_sensor_alerts_ibfk_2 FOREIGN KEY("+KEY_SENSOR_ID+") REFERENCES "+TABLE_SENSOR+"("+KEY_SENSOR_ID+"), " +
            "CONSTRAINT ta_sensor_alerts_ibpk PRIMARY KEY("+KEY_ALERT_CONFIGURATION_ID+", "+KEY_SENSOR_ID+"))";

    //ta_measure_type - create statement
    private static final String CREATE_TA_MEASURE_TYPE="CREATE TABLE "+TABLE_TA_MEASURE_TYPE+"( " +
            KEY_MEASURE_ID +" INTEGER NOT NULL, " +
            KEY_MEASURE_TYPE_ID +" INTEGER NOT NULL, " +
            KEY_MEASURE_VALUE +" FLOAT NOT NULL, " +
            "CONSTRAINT ta_measure_type_ibfk_1 FOREIGN KEY("+KEY_MEASURE_ID+") REFERENCES "+TABLE_MEASURES+"("+KEY_MEASURE_ID+"),\n" +
            "CONSTRAINT ta_measure_type_ibfk_2 FOREIGN KEY("+KEY_MEASURE_TYPE_ID+") REFERENCES "+TABLE_MEASURE_TYPE+"("+KEY_MEASURE_TYPE_ID+"),\n" +
            "CONSTRAINT ta_measure_type_ibpk PRIMARY KEY("+KEY_MEASURE_ID+", "+KEY_MEASURE_TYPE_ID+"))";

    //update_completed - create statement
    private static final String CREATE_UPDATE_COMPLETED = "CREATE TABLE "+TABLE_UPDATE_COMPLETED+" ( " +
            KEY_UPDATE_ID +" INTEGER NOT NULL, " +
            KEY_RASPBERRY_PI_ID +" INTEGER NOT NULL, " +
            "CONSTRAINT update_completed_ibfk_1 FOREIGN KEY("+KEY_UPDATE_ID+") REFERENCES "+TABLE_UPDATE+"("+KEY_UPDATE_ID+"),\n" +
            "CONSTRAINT update_completed_ibfk_2 FOREIGN KEY("+KEY_RASPBERRY_PI_ID+") REFERENCES "+TABLE_RASPBERRY_PI+"("+KEY_RASPBERRY_PI_ID+"),\n" +
            "CONSTRAINT update_completed_ibpk PRIMARY KEY("+KEY_UPDATE_ID+", "+KEY_RASPBERRY_PI_ID+"))";

    //ta_users_permissions - create statement
    private static final String CREATE_TA_USERS_PERMISSIONS="CREATE TABLE "+TABLE_TA_USERS_PERMISSIONS +"( " +
            KEY_PERMISSION +" VARCHAR(64) NOT NULL, " +
            KEY_USER_TYPE +" VARCHAR(64) NOT NULL, " +
            "CONSTRAINT ta_users_permissions_ibfk_1 FOREIGN KEY("+KEY_PERMISSION+") REFERENCES "+TABLE_PERMISSIONS+"("+KEY_PERMISSION_NAME+"), " +
            "CONSTRAINT ta_users_permissions_ibfk_2 FOREIGN KEY("+KEY_USER_TYPE+") REFERENCES "+TABLE_USER_TYPE+"("+KEY_USER_TYPE_NAME+"), " +
            "CONSTRAINT ta_users_permissions_ibpk PRIMARY KEY("+KEY_PERMISSION+", "+KEY_USER_TYPE+"))";


    public db_query_engine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public String getTablePermissions() {
        return TABLE_PERMISSIONS;
    }
    public String getTableUserType() {
        return TABLE_USER_TYPE;
    }
    public String getTableUsers() {
        return TABLE_USERS;
    }
    public String getTableBed() {
        return TABLE_BED;
    }
    public String getTableZone() {
        return TABLE_ZONE;
    }
    public String getTableUpdateState() {
        return TABLE_UPDATE_STATE;
    }
    public String getTableUpdate() {
        return TABLE_UPDATE;
    }
    public String getTableRaspberryPiType() {
        return TABLE_RASPBERRY_PI_TYPE;
    }
    public String getTableRaspberryPi() {
        return TABLE_RASPBERRY_PI;
    }
    public String getTableAlertConfiguration() {
        return TABLE_ALERT_CONFIGURATION;
    }
    public String getTableSensorState() {
        return TABLE_SENSOR_STATE;
    }
    public String getTableSensorType() { return TABLE_SENSOR_TYPE; }
    public String getTableAlertType() { return TABLE_ALERT_TYPE; }
    public String getTableMeasureType() { return TABLE_MEASURE_TYPE; }
    public String getTableSensor() { return TABLE_SENSOR; }
    public String getTableMeasures() { return TABLE_MEASURES; }
    public String getTableTaAlertEvent() { return TABLE_TA_ALERT_EVENT; }
    public String getTableTaSensorAlerts() { return TABLE_TA_SENSOR_ALERTS; }
    public String getTableTaMeasureType() { return TABLE_TA_MEASURE_TYPE; }
    public String getTableUpdateCompleted() { return TABLE_UPDATE_COMPLETED; }
    public String getTableTaUsersPermissions() { return TABLE_TA_USERS_PERMISSIONS; }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_PERMISSIONS);
        db.execSQL(CREATE_USER_TYPE);
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_BED);
        db.execSQL(CREATE_ZONE);
        db.execSQL(CREATE_UPDATE_STATE);
        db.execSQL(CREATE_UPDATE);
        db.execSQL(CREATE_RASPBERRY_PI_TYPE);
        db.execSQL(CREATE_RASPBERRY_PI);
        db.execSQL(CREATE_ALERT_CONFIGURATION);
        db.execSQL(CREATE_SENSOR_STATE);
        db.execSQL(CREATE_SENSOR_TYPE);
        db.execSQL(CREATE_ALERT_TYPE);
        db.execSQL(CREATE_MEASURE_TYPE);
        db.execSQL(CREATE_SENSOR);
        db.execSQL(CREATE_MEASURE);
        db.execSQL(CREATE_TA_ALERT_EVENT);
        db.execSQL(CREATE_TA_SENSOR_ALERTS);
        db.execSQL(CREATE_TA_MEASURE_TYPE);
        db.execSQL(CREATE_UPDATE_COMPLETED);
        db.execSQL(CREATE_TA_USERS_PERMISSIONS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TA_USERS_PERMISSIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE_COMPLETED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TA_MEASURE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TA_SENSOR_ALERTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TA_ALERT_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASURE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERT_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSOR_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENSOR_STATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALERT_CONFIGURATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RASPBERRY_PI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RASPBERRY_PI_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE_STATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERMISSIONS);

        onCreate(db);
    }


    public void execution(String sql){
        SQLiteDatabase db = this.getWritableDatabase();

        //System.out.println("Testing");
        db.execSQL(sql);
    }


    public Cursor execution_with_return(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e(LOG, sql);

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        return c;
    }

    public Cursor execution_with_return(String sql, String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e(LOG, sql);

        return db.rawQuery(sql, args);
    }

    public SQLiteStatement compile_statement(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.compileStatement(query);
    }


}
