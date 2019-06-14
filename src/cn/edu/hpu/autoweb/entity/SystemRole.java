package cn.edu.hpu.autoweb.entity;

public class SystemRole {

	private String RoleId;
	private String RoleName;
	private String RoleCode;
	private Integer Seq;

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getRoleCode() {
		return RoleCode;
	}

	public void setRoleCode(String roleCode) {
		RoleCode = roleCode;
	}

	public Integer getSeq() {
		return Seq;
	}

	public void setSeq(Integer seq) {
		Seq = seq;
	}
}
