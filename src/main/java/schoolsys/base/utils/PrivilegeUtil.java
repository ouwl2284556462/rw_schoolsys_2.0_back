package schoolsys.base.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;

import schoolsys.user.bean.SysPrivilegeBean;
import schoolsys.user.bean.SysRoleBean;

public class PrivilegeUtil {
	
	/**
	 * 判断是否有该资源的权限
	 * @param roleBean
	 * @param resId
	 * @return
	 */
	public static boolean hasResPermission(SysRoleBean roleBean, String resId) {
		if(null == roleBean) {
			return false;
		}
		
		
		List<SysPrivilegeBean> priList = roleBean.getPrivileges();
		if(CollectionUtils.isEmpty(priList)) {
			return false;
		}
		
		for(SysPrivilegeBean pri : priList) {
			if(resId.equals(pri.getResId())) {
				return true;
			}
		}
		return false;
	}
}
