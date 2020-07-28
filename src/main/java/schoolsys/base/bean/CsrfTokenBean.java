package schoolsys.base.bean;

public class CsrfTokenBean {
	private String token;
	private String headerName;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	
	
}
