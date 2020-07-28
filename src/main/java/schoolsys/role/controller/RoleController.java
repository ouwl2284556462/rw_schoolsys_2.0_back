package schoolsys.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import schoolsys.base.constants.CommPageConst;
import schoolsys.role.service.RoleService;
import schoolsys.user.bean.SysRoleBean;


@Controller
@RequestMapping("/RoleManage")
@PreAuthorize("hasPermission('roleAdmin', 'all')")
/**
 * 角色管理
 */
public class RoleController {
	@Autowired
	private RoleService roleService;

	
	@RequestMapping("/toAddRole.do")
	/**
	 * 跳转到角色添加界面
	 * @return
	 */
	public String toAddRole(Model model) {
		model.addAttribute("from", "adminAddRole");
		return "role_manage/role_info";
	}
	
	@RequestMapping("/toChgRole.do")
	/**
	 * 跳转到角色添加界面
	 * @return
	 */
	public String toChgRole(Model model, int roleId) {
		SysRoleBean roleBean = roleService.findRoleById(roleId);
		model.addAttribute("from", "adminChgRole");
		model.addAttribute("roleBean", roleBean);
		return "role_manage/role_info";
	}
	

	@RequestMapping("/toRoleManage.do")
	/**
	 * 跳转到角色管理
	 * @return
	 */
	public String toRoleManage() {
		return "role_manage/role_manage";
	}
	
	
	@RequestMapping("/qryRoleList.do")
	/**
	 * 查询角色列表
	 * @return
	 */
	public String qryRoleList(Model model, SysRoleBean roleBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<SysRoleBean> pageInfo = roleService.qryRoleListByPage(roleBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "role_manage/role_list";
	}
	
	
	@RequestMapping("roleModify.do")
	@ResponseBody
	public String roleModify(SysRoleBean roleBean, Model model) {
		String returnMsg = "Success";
		
		String errMsg = null;
		if(roleBean.getId() != null) {
			//更新用户详细信息
			errMsg = roleService.updateRole(roleBean);
		}else {
			//新增
			errMsg = roleService.addRole(roleBean);
		}
		
		if(!StringUtils.isEmpty(errMsg)) {
			returnMsg = errMsg;
		}
		
		return returnMsg;
	}
	
	@RequestMapping("/toDelRole.do")
	@ResponseBody
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String toDelRole(@RequestParam(value = "ids[]") String[] ids) {
		roleService.deleteRole(ids);
		return "Success";
	}
}
