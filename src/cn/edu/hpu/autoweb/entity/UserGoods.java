package cn.edu.hpu.autoweb.entity;

import java.util.Date;

public class UserGoods {
    private Integer id;
    private String UserId;
    private Integer goods_Id;
    private Date create_Time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Integer getGoods_Id() {
        return goods_Id;
    }

    public void setGoods_Id(Integer goods_Id) {
        this.goods_Id = goods_Id;
    }

    public Date getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(Date create_Time) {
        this.create_Time = create_Time;
    }
}
