package cn.edu.hpu.autoweb.entity;

/**
 * Created by Aries on 2017/3/16.
 */
public class UserAccess {
    private String UserAccessId;
    private String UserId;
    private String AccessId;

    public String getUserAccessId() {
        return UserAccessId;
    }

    public void setUserAccessId(String userAccessId) {
        UserAccessId = userAccessId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAccessId() {
        return AccessId;
    }

    public void setAccessId(String accessId) {
        AccessId = accessId;
    }
}
