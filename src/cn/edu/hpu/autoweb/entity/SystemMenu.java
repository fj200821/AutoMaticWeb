package cn.edu.hpu.autoweb.entity;

import java.io.Serializable;
import java.util.List;

public class SystemMenu implements Serializable{
	private String MenuId;
	private String PId;
	private String MenuName;
	private String PCode;
	private String MenuCode;
	private String RequestURL;
	private Integer Seq;
	private String IconName;
	private String Status;
	private boolean checked;
	private boolean chkDisabled;
	private boolean open;
	private List<SystemMenu> ChildrenMenus;

	public SystemMenu() {
		this.chkDisabled = false;
		this.checked = false;
		this.open = true;
	}

	public String getMenuId() {
		return MenuId;
	}

	public void setMenuId(String menuId) {
		MenuId = menuId;
	}

	public String getPId() {
		return PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public String getPCode() {
		return PCode;
	}

	public void setPCode(String PCode) {
		this.PCode = PCode;
	}

	public String getMenuCode() {
		return MenuCode;
	}

	public void setMenuCode(String menuCode) {
		MenuCode = menuCode;
	}

	public String getRequestURL() {
		return RequestURL;
	}

	public void setRequestURL(String requestURL) {
		RequestURL = requestURL;
	}

	public Integer getSeq() {
		return Seq;
	}

	public void setSeq(Integer seq) {
		Seq = seq;
	}

	public String getIconName() {
		return IconName;
	}

	public void setIconName(String iconName) {
		IconName = iconName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<SystemMenu> getChildrenMenus() {
		return ChildrenMenus;
	}

	public void setChildrenMenus(List<SystemMenu> childrenMenus) {
		ChildrenMenus = childrenMenus;
	}
}
