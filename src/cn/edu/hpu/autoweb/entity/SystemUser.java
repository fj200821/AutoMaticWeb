package cn.edu.hpu.autoweb.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemUser implements Serializable {

	private String UserId;
	private String RoleId;
	private String LoginId;
	private String Password;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Status;
	private Date EffectStartDate;
	private Date EffectDate;
	private String Note;
	private String CreatedBy;
	private String CreatedDate;
	private String ModifyBy;
	private String ModifyDate;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	public String getModifyBy() {
		return ModifyBy;
	}

	public void setModifyBy(String modifyBy) {
		ModifyBy = modifyBy;
	}

	public String getModifyDate() {
		return ModifyDate;
	}

	public void setModifyDate(String modifyDate) {
		ModifyDate = modifyDate;
	}

	public Date getEffectStartDate() {
		return EffectStartDate;
	}

	public void setEffectStartDate(Date effectStartDate) {
		EffectStartDate = effectStartDate;
	}

	public Date getEffectDate() {
		return EffectDate;
	}

	public void setEffectDate(Date effectDate) {
		EffectDate = effectDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
}
