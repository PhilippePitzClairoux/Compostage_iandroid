package com.compostage.Data;

import java.io.Serializable;

public class UserPermissions implements Serializable {

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
