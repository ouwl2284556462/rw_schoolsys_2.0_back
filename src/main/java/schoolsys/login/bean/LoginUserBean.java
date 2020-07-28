package schoolsys.login.bean;

import schoolsys.base.bean.CsrfTokenBean;
import schoolsys.user.bean.UserBean;

public class LoginUserBean {

	private UserBean user;

	private CsrfTokenBean csrfToken;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public CsrfTokenBean getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(CsrfTokenBean csrfToken) {
		this.csrfToken = csrfToken;
	}

}
