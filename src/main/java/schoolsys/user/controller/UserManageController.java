package schoolsys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import schoolsys.base.bean.RespBean;
import schoolsys.base.constants.CommPageConst;
import schoolsys.user.bean.UserBean;
import schoolsys.user.service.UserService;

@RestController
@RequestMapping("/UserManage")
@PreAuthorize("hasPermission('userAdmin', 'all')")
/**
 * 用户管理
 *
 */
public class UserManageController {
	@Autowired
	/**
	 * 用户管理的服务类
	 */
	private UserService userService;
	
	
	@RequestMapping("/qryUserList.do")
	/**
	 * 获取用户列表
	 * @param model
	 * @param userBean
	 * @param pageNum
	 * @return
	 */
	public RespBean qryUserList(UserBean userBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum) {
		PageInfo<UserBean> pageInfo = userService.qryUserListByPage(userBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	@RequestMapping("/deleteUserList.do")
	@ResponseBody
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String deleteUserList(@RequestParam(value = "ids[]") String[] ids) {
		userService.deleteUser(ids);
		return "Success";
	}
	
	@RequestMapping("/toChgUserInfo.do")
	/**
	 * 获取用户详细信息，并跳转到用户详细信息页面
	 * @param model
	 * @param userId
	 * @return
	 */
	public String toChgUserInfo(Model model, int userId) {
		//获取用户详细信息
		UserBean userBean = userService.findUserById(userId);
		model.addAttribute("userBean", userBean);
		model.addAttribute("from", "adminChgInfo");
		return "workspace/user_info";
	}
	
	
	@RequestMapping("/toAddUser.do")
	/**
	 * 获取用户详细信息，并跳转到用户详细信息页面
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	public String toAddUser(Model model) {
		model.addAttribute("from", "adminAddUser");
		return "workspace/user_info";
	}
		
		
	
	@RequestMapping("/toChgPassword.do")
	/**
	 * 跳转到密码修改页面
	 * @param model
	 * @param userId
	 * @return
	 */
	public String toChgPassword(Model model, int userId) {
		model.addAttribute("userId", userId);
		model.addAttribute("from", "adminChgInfo");
		return "workspace/password_chg";
	}
	
	
	@RequestMapping("/changePassword.do")
	@ResponseBody
	/**
	 * 修改密码
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	public String changePassword(int userId, String newPassword) {
		userService.updatePassword(userId, newPassword);
		return "Success";
	}
	
	
	@RequestMapping("/userInfoModify.do")
	@ResponseBody
	/**
	 * 更新或新增用户
	 * @param userBean
	 * @param model
	 * @return
	 */
	public String userInfoModify(UserBean userBean, Model model) {
		String returnMsg = "Success";
		
		String errMsg = null;
		if(userBean.getId() != null) {
			//更新用户详细信息
			userService.updateUserDtl(userBean);
			model.addAttribute("userBean", userBean);
		}else {
			//新增
			errMsg = userService.addUser(userBean);
		}
		
		
		if(!StringUtils.isEmpty(errMsg)) {
			returnMsg = errMsg;
		}
		
		
		return returnMsg;
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
}
