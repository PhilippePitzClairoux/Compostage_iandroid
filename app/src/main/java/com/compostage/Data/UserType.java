package com.compostage.Data;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.compostage.db_query_engine;


import java.util.ArrayList;

public class UserType {

    private String userTypeName;
    private String userTypeDescription;
    private ArrayList<UserPermissions> userPermissions;


    private final static String GET_DESCRIPTION = "SELECT * FROM user_type WHERE user_type_name = ?";
    private final static String GET_PERMISSIONS = "SELECT * FROM ta_users_permissions INNER JOIN" +
            " permissions ON permissions.permission_name = ta_users_permissions.permission WHERE user_type = ?";


    public UserType(String userTypeName, String userTypeDescription) {

        this.userTypeName = userTypeName;
        this.userTypeDescription = userTypeDescription;
        this.userPermissions = new ArrayList<>();
    }

    public UserType(String userTypeName, db_query_engine engine) {

        this.userTypeName = userTypeName;
        this.userPermissions = new ArrayList<>();
        this.fetch_data(engine);
    }

    public ArrayList<UserPermissions> getUserPermissions() {
        return userPermissions;
    }

    public UserPermissions getUserPermission(Integer index) {
        return userPermissions.get(index);
    }

    public void addUserPermission(UserPermissions userPermissions) {
        this.userPermissions.add(userPermissions);
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    public void setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
    }

    private void fetch_data(db_query_engine engine) {


        Cursor info = engine.execution_with_return(GET_DESCRIPTION, new String[] {this.getUserTypeName()} );

        this.setUserTypeDescription(info.getString(info.getColumnIndex("user_type_description")));

        info.close();
        info = engine.execution_with_return(GET_PERMISSIONS, new String[] {this.getUserTypeName()} );

        while (info.moveToNext()) {

            this.addUserPermission(new UserPermissions(info.getString(info.getColumnIndex("permission_name")),
                    info.getString(info.getColumnIndex("permission_description"))));
        }

        info.close();
    }

}
