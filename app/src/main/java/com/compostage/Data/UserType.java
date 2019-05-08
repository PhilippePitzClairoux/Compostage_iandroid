package com.compostage.Data;

class UserType implements IDataBase {

    private String userTypeName;
    private String userTypeDescription;
    private UserPermissions[] userPermissions;

    public UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public UserPermissions[] getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(UserPermissions[] userPermissions) {
        this.userPermissions = userPermissions;
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

    @Override
    public void fetch_data() {

    }

    @Override
    public void insert_data() {

    }

    @Override
    public void sync_data() {

    }
}
