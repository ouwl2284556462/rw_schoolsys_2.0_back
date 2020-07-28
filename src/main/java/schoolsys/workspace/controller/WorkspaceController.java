package schoolsys.workspace.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import schoolsys.user.bean.UserBean;
import schoolsys.user.service.UserService;

@Controller
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
	

	@RequestMapping("/toMain.do")
	/**
	 * 跳转到主页
	 * @return
	 */
	public String toMain() {
		return "workspace/main";
	}
	
	
	@RequestMapping("/toCurUserInfo.do")
	/**
	 * 跳转到当前用户的详细信息页面
	 * @param principal
	 * @param model
	 * @return
	 */
	public String toCurUserInfo(Principal principal, Model model) {
		String acctName = principal.getName();
		//获取当前用户的详细信息
		UserBean userBean = userService.findUserByAcctName(acctName);
		model.addAttribute("userBean", userBean);
		return "workspace/user_info";
	}
	
	@RequestMapping("/updateUserDtl.do")
	@ResponseBody
	/**
	 * 更新用户详细信息
	 * @param userBean
	 * @param model
	 * @return
	 */
	public String updateUserDtl(UserBean userBean, Model model) {
		//更新用户详细信息
		userService.updateUserDtl(userBean);
		model.addAttribute("userBean", userBean);
		return "Success";
	}
	
	@RequestMapping("/toChgCurUserPassword.do")
	/**
	 * 跳转到改变用户密码的页面
	 * @return
	 */
	public String toChgCurUserPassword() {
		return "workspace/password_chg";
	}
	
	@RequestMapping("/updateCurUserPassword.do")
	@ResponseBody
	/**
	 * 更新密码
	 * @param authentication
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public String updateCurUserPassword(Authentication authentication, String oldPassword, String newPassword) {
		String acctName = authentication.getName();
		//执行更新密码
		String retMsg = userService.updatePasswordWithCheck(acctName, oldPassword, newPassword);
		
		if(StringUtils.isEmpty(retMsg)) {
			retMsg = "Success";
		}
		
		return retMsg;
	}
	
	
	@RequestMapping("/toCourseManager.do")
	/**
	 * 	跳转到课程管理
	 * @return
	 */
	public String toCourseManager() {
		return "workspace/course_manage";
	}
		
	
	
	
	
}
