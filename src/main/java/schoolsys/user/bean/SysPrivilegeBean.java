package schoolsys.user.bean;

/**
 * 权限信息
 *
 */
public class SysPrivilegeBean {
	// 资源id
	private String resId;
	// 操作许可
	private String permission;
	// 描述
	private String mark;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}
