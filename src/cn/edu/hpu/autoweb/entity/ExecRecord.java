package cn.edu.hpu.autoweb.entity;

import java.util.Date;

public class ExecRecord {
    private Integer id;

    private String type;

    private Boolean is_Success;

    private Date create_Time;

    private Date end_Time;

    private Boolean is_Confirm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_Success() {
        return is_Success;
    }

    public void setIs_Success(Boolean is_Success) {
        this.is_Success = is_Success;
    }

    public Date getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(Date create_Time) {
        this.create_Time = create_Time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEnd_Time() {
        return end_Time;
    }

    public void setEnd_Time(Date end_Time) {
        this.end_Time = end_Time;
    }

    public Boolean getIs_Confirm() {
        return is_Confirm;
    }

    public void setIs_Confirm(Boolean is_Confirm) {
        this.is_Confirm = is_Confirm;
    }
}
