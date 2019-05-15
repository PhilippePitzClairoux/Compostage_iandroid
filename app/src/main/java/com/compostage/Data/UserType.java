package com.compostage.Data;

import java.util.ArrayList;

public class UserType {

    private String userTypeName;
    private String userTypeDescription;
    private ArrayList<UserPermissions> userPermissions;

    public UserType(String userTypeName, String userTypeDescription) {

        this.userTypeName = userTypeName;
        this.userTypeDescription = userTypeDescription;
        this.userPermissions = new ArrayList<>();
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
}
