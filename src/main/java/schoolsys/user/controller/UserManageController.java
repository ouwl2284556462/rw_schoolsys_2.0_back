package schoolsys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public RespBean deleteUserList(Integer[] userIds) {
		userService.deleteUser(userIds);
		return RespBean.success("删除成功");
	}
	
	
	@RequestMapping("/changePassword.do")
	/**
	 * 修改密码
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	public RespBean changePassword(int userId, String newPassword) {
		userService.updatePassword(userId, newPassword);
		return RespBean.success("修改成功");
	}
	
	
	@RequestMapping("/userInfoModify.do")
	/**
	 * 更新或新增用户
	 * @param userBean
	 * @param model
	 * @return
	 */
	public RespBean userInfoModify(UserBean userBean, Model model) {
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
			return RespBean.fail(errMsg);
		}
		
		
		return RespBean.success("添加成功");
	}
	
}
