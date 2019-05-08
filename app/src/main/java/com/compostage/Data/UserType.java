package com.compostage.Data;

class UserType implements IDataBase {

    private String userTypeName;
    private String userTypeDescription;

    public UserType(String userTypeName) {
        this.userTypeName = userTypeName;
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
