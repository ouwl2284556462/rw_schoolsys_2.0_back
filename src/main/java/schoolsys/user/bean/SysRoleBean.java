package schoolsys.user.bean;

import java.util.List;

/**
 * 用户角色信息
 *
 */
public class SysRoleBean {
	private Integer id;
	// 角色名
	private String name;
	// 中文名
	private String chName;
	// 备注
	private String mark;

	private List<SysPrivilegeBean> privileges;

	private String[] privilegeArray;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public List<SysPrivilegeBean> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<SysPrivilegeBean> privileges) {
		this.privileges = privileges;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String[] getPrivilegeArray() {
		return privilegeArray;
	}

	public void setPrivilegeArray(String[] privilegeArray) {
		this.privilegeArray = privilegeArray;
	}

}
