package cn.edu.hpu.autoweb.entity;

/**
 * Created by Aries on 2017/3/16.
 */
public class RoleAccess {
    private String RoleAccessId;
    private String RoleId;
    private String AccessId;

    public String getRoleAccessId() {
        return RoleAccessId;
    }

    public void setRoleAccessId(String roleAccessId) {
        RoleAccessId = roleAccessId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getAccessId() {
        return AccessId;
    }

    public void setAccessId(String accessId) {
        AccessId = accessId;
    }
}
