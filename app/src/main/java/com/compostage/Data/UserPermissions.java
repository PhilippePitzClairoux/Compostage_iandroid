package com.compostage.Data;

public class UserPermissions {

    private String permissionName;
    private String permissionDescription;


    public UserPermissions(String permissionName, String permissionDescription) {

        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
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
