package schoolsys.workspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import schoolsys.base.bean.RespBean;
import schoolsys.user.bean.UserBean;
import schoolsys.user.service.UserService;

@RestController
@RequestMapping("/Workspace")
/**
 * 主页面
 */
public class WorkspaceController {
	
	@Autowired
	/**
	 * 用户管理的服务类
	 */
	private UserService userService;
	
	@RequestMapping("/updateUserDtl.do")
	/**
	 * 更新用户详细信息
	 * @param userBean
	 * @param model
	 * @return
	 */
	public RespBean updateUserDtl(UserBean userBean) {
		//更新用户详细信息
		userService.updateUserDtl(userBean);
		return RespBean.success("处理成功");
	}
	
	
	@RequestMapping("/updateCurUserPassword.do")
	/**
	 * 更新密码
	 * @param authentication
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public RespBean updateCurUserPassword(Authentication authentication, String oldPassword, String newPassword) {
		String acctName = authentication.getName();
		//执行更新密码
		String retMsg = userService.updatePasswordWithCheck(acctName, oldPassword, newPassword);
		if(!StringUtils.isEmpty(retMsg)) {
			return RespBean.fail(retMsg);
		}
		
		return RespBean.success("处理成功");
	}
	
	
	
}
