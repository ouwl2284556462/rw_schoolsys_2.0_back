package schoolsys.base.bean;

import schoolsys.base.constants.RespCodeConst;

public class RespBean<T> {
	private String code;
	private T data;
	
	private RespBean (){
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static <E>RespBean<E> success(E data){
		RespBean<E> result = new RespBean<>();
		result.setCode(RespCodeConst.SUCCESS);
		result.setData(data);
		return result;
	}

}
