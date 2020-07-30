package schoolsys.base.bean;

import schoolsys.base.constants.RespCodeConst;

public class RespBean {
	private String code;
	private Object data;
	
	private RespBean (){
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static RespBean success(Object data){
		return build(RespCodeConst.SUCCESS, data);
	}

	public static RespBean fail(Object data) {
		return build(RespCodeConst.FAIL, data);
	}
	
	public static RespBean build(String code, Object data) {
		RespBean result = new RespBean();
		result.setCode(code);
		result.setData(data);
		return result;
	}

}
