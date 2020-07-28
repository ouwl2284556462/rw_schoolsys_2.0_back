package schoolsys.user.bean;

import java.sql.Timestamp;

import schoolsys.base.bean.CsrfTokenBean;
/**
 * 用户信息
 *
 */
public class UserBean {
	private Integer id;
	//帐号名
	private String accountName;
	//密码
	private String password;
	//名字
	private String name;
	//电话号码
	private String tel;
	//证件号码
	private String identityNum;
	//证件类型
	private String identityType;
	//创建时间
	private Timestamp createTime;
	//角色
	private SysRoleBean role;
	//性别
	private String sex;
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public SysRoleBean getRole() {
		return role;
	}

	public void setRole(SysRoleBean role) {
		this.role = role;
	}

}
