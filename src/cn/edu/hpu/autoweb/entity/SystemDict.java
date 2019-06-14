package cn.edu.hpu.autoweb.entity;

/**
 * Created by Aries on 2017/3/16.
 */
public class SystemDict {
    private String DictId;
    private String PId;
    private String Name;
    private String Code;
    private String PCode;
    private Integer Seq;

    public String getDictId() {
        return DictId;
    }

    public void setDictId(String dictId) {
        DictId = dictId;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPCode() {
        return PCode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public Integer getSeq() {
        return Seq;
    }

    public void setSeq(Integer seq) {
        Seq = seq;
    }
}
