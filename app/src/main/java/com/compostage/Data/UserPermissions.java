package com.compostage.Data;

public class UserPermissions implements IDataBase {

    private String permissionName;
    private String permissionDescription;


    public UserPermissions(String permissionName) {
        this.permissionName = permissionName;
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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
}
