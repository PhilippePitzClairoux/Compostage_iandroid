package com.compostage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class insertData {

    private db_query_engine db;
    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;

    public insertData(db_query_engine db, Context context) {
        this.db = db;
        mContext = context;
    }

    private int getCountTable(String databaseTable){
        Cursor c = null;
        mSQLiteDatabase = db.getReadableDatabase();
        try {
            String query = "select count(*) from " + databaseTable;
            c = mSQLiteDatabase.rawQuery(query, null);
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        }
        finally {
            if (c != null) {
                c.close();
            }
            if (mSQLiteDatabase != null) {
                mSQLiteDatabase.close();
            }
        }
    }


    public void insert(){
        //Insert in table permissions
        if(getCountTable(db.getTablePermissions()) == 0){
            db.execution("INSERT INTO "+db.getTablePermissions()+"(permission_name, permission_description) VALUES (\"read\", \"read data from the system\")");
            db.execution("INSERT INTO "+db.getTablePermissions()+"(permission_name, permission_description) VALUES (\"write\", \"write data in the system\")");
            db.execution("INSERT INTO "+db.getTablePermissions()+"(permission_name, permission_description) VALUES (\"manage\", \"can edit what he wants\")");

        }

        //Insert in table user_type
        if(getCountTable(db.getTableUserType()) == 0){
            db.execution("INSERT INTO "+db.getTableUserType()+"(user_type_name, user_type_description) VALUES (\"admin\", \"This user can do what he please\")");
            db.execution("INSERT INTO "+db.getTableUserType()+"(user_type_name, user_type_description) VALUES (\"visitor\", \"This user can only see data\")");
            db.execution("INSERT INTO "+db.getTableUserType()+"(user_type_name, user_type_description) VALUES (\"normal\", \"This user can do more than a visitor but less than an admin\")");
            db.execution("INSERT INTO "+db.getTableUserType()+"(user_type_name, user_type_description) VALUES (\"raspberry_pi\", \"this user can only write data\")");
        }

        //Insert in table ta_users_permissions
        if(getCountTable(db.getTableTaUsersPermissions()) == 0){
            db.execution("INSERT INTO "+db.getTableTaUsersPermissions()+"(permission, user_type) VALUES (\"manage\", \"admin\"), (\"read\", \"visitor\"), (\"read\", \"normal\"), (\"write\", \"normal\"), (\"write\", \"raspberry_pi\")");
        }

        //Insert in table users
        if(getCountTable(db.getTableUsers()) == 0){
            db.execution("INSERT INTO "+db.getTableUsers()+"(username, user_type_id, password, email, auth_question, auth_answer)"+
                    " VALUES (\"admin\", \"admin\", \"$2y$10$ZIaeQm9egZQLh0h7u2WUpuMSbUZprck3/sWFkyuFLDfpc9OpTv.ia\", \"test@gmail.com\", \"hehe?\", \"$2y$10$Mrv.jrNC6NNNyFaa5OBwWeAuGmd7XLvNXWSxMs0k8CVQV5NLs1FEC\"),"+
                    " (\"raspberry_pi\", \"raspberry_pi\", \"$2y$10$ZIaeQm9egZQLh0h7u2WUpuMSbUZprck3/sWFkyuFLDfpc9OpTv.ia\", \"raspberry@test.com\", \"hehe?\", \"$2y$10$Mrv.jrNC6NNNyFaa5OBwWeAuGmd7XLvNXWSxMs0k8CVQV5NLs1FEC\")");
        }

        //Insert in table bed
        if(getCountTable(db.getTableBed()) == 0){
            db.execution("INSERT INTO "+db.getTableBed()+"(bed_name) VALUES (\"ALPHA\"), (\"BRAVO\"), (\"BOB\"), (\"ANTOINE\")");
        }

        //Insert in table zone
        if(getCountTable(db.getTableZone()) == 0){
            db.execution("INSERT INTO "+db.getTableZone()+"(_id, zone_name) VALUES (1, \"UNO\"), (1, \"DOS\"), (1, \"TRES\"), (2, \"UNO\"), (2, \"DOS\"), (3, \"TRES\")");
        }

        //Insert in table raspberry_pi_type
        if(getCountTable(db.getTableRaspberryPiType()) == 0){
            db.execution("INSERT INTO "+db.getTableRaspberryPiType()+"(raspberry_pi_type, raspberry_pi_type_description) VALUES (\"MODEL_3\", \"This is the current last gen of raspberry pi's.\")");
        }

        //Insert in table raspberry_pi
        if(getCountTable(db.getTableRaspberryPi()) == 0){
            db.execution("INSERT INTO "+db.getTableRaspberryPi()+"(zone_id, user_id, raspberry_pi_type, raspberry_pi_aquisition_date, raspberry_pi_capacity) VALUES (1, \"raspberry_pi\", \"MODEL_3\", \"2019-04-24\", 32)");
            db.execution("INSERT INTO "+db.getTableRaspberryPi()+"(zone_id, user_id, raspberry_pi_type, raspberry_pi_aquisition_date, raspberry_pi_capacity) VALUES (4, \"raspberry_pi\", \"MODEL_3\", \"2019-04-24\", 32)");
            db.execution("INSERT INTO "+db.getTableRaspberryPi()+"(zone_id, user_id, raspberry_pi_type, raspberry_pi_aquisition_date, raspberry_pi_capacity) VALUES (3, \"raspberry_pi\", \"MODEL_3\", \"2019-04-24\", 32)");
        }

        //Insert in table alert_configuration
        if(getCountTable(db.getTableAlertConfiguration()) == 0){
            db.execution("INSERT INTO "+db.getTableAlertConfiguration()+"(alert_configuration_message, alert_configuration_min_value, alert_configuration_max_value) VALUES (\"Oh noo! Looks like Bed#%i is below the normal amount of ph!\", 5.5, 14)");
            db.execution("INSERT INTO "+db.getTableAlertConfiguration()+"(alert_configuration_message, alert_configuration_min_value, alert_configuration_max_value) VALUES (\"Oh noo! Looks like Bed#%i is below the normal temperature!\", 15, NULL)");
            db.execution("INSERT INTO "+db.getTableAlertConfiguration()+"(alert_configuration_message, alert_configuration_min_value, alert_configuration_max_value) VALUES (\"Oh noo! Looks like Bed#%i has a high amount of humidity!\", NULL, 0.40)");

        }

        //Insert in table sensor_type
        if(getCountTable(db.getTableSensorType()) == 0){
            db.execution("INSERT INTO "+db.getTableSensorType()+"(sensor_type_name) VALUES (\"PH_SENOSR\"), (\"HUMIDITY_SENSOR\"), (\"TEMPERATURE_SENSOR\")");

        }

        //Insert in table sensor_state
        if(getCountTable(db.getTableSensorState()) == 0){
            db.execution("INSERT INTO "+db.getTableSensorState()+"(sensor_state) VALUES (\"WORKING\"), (\"BROKEN\"), (\"NEEDS_CHECKUP\")");
        }

        //Insert in table measure_type
        if(getCountTable(db.getTableMeasureType()) == 0){
            db.execution("INSERT INTO "+db.getTableMeasureType()+"(measure_type_name) VALUES (\"PH\"), (\"HUMIDITY\"), (\"TEMPERATURE\")");
        }

        //Insert in table sensor
        if(getCountTable(db.getTableSensor()) == 0){
            db.execution("INSERT INTO "+db.getTableSensor()+"(sensor_type_id, sensor_state_id, raspberry_pi_id, sensor_aquisition_date, sensor_serial_number) VALUES (1, 1, 1, \"2019-04-24 11:06:23\", \"666-696969-666\")");
            db.execution("INSERT INTO "+db.getTableSensor()+"(sensor_type_id, sensor_state_id, raspberry_pi_id, sensor_aquisition_date, sensor_serial_number) VALUES (1, 1, 2, \"2019-04-24 11:06:23\", \"666-696969-666\")");
            db.execution("INSERT INTO "+db.getTableSensor()+"(sensor_type_id, sensor_state_id, raspberry_pi_id, sensor_aquisition_date, sensor_serial_number) VALUES (1, 1, 3, \"2019-04-24 11:06:23\", \"666-696969-666\")");
        }

        //Insert in table update_state
        if(getCountTable(db.getTableUpdateState()) == 0){
            db.execution("INSERT INTO "+db.getTableUpdateState()+"(update_state, update_state_description) VALUES (\"pending\", \"Not ready to be deployed\"), (\"done\", \"update is completely deployed\")");
        }

        //Insert in table update
        if(getCountTable(db.getTableUpdate()) == 0){
            db.execution("INSERT INTO "+db.getTableUpdate()+"(update_state_id, update_name, update_date) VALUES (2, \"LmaoXD\", \"2019-05-06\")");
            db.execution("INSERT INTO "+db.getTableUpdate()+"(update_state_id, update_name, update_date) VALUES (1, \"OOF\", \"2019-02-22\")");
        }

        //Insert in table update_completed
        if(getCountTable(db.getTableUpdateCompleted()) == 0){
            db.execution("INSERT INTO "+db.getTableUpdateCompleted()+"(update_id, raspberry_pi_id) VALUES (1, 1)");
            db.execution("INSERT INTO "+db.getTableUpdateCompleted()+"(update_id, raspberry_pi_id) VALUES (2, 1)");
        }



    }

}
