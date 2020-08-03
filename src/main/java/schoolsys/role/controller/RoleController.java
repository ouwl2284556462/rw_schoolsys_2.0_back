package schoolsys.role.controller;

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
import schoolsys.role.service.RoleService;
import schoolsys.user.bean.SysRoleBean;


@RestController
@RequestMapping("/RoleManage")
@PreAuthorize("hasPermission('roleAdmin', 'all')")
/**
 * 角色管理
 */
public class RoleController {
	@Autowired
	private RoleService roleService;

	
	@RequestMapping("/qryRoleList.do")
	/**
	 * 查询角色列表
	 * @return
	 */
	public RespBean qryRoleList(SysRoleBean roleBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<SysRoleBean> pageInfo = roleService.qryRoleListByPage(roleBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	
	@RequestMapping("roleModify.do")
	public RespBean roleModify(SysRoleBean roleBean, Model model) {
		String errMsg = null;
		if(roleBean.getId() != null) {
			//更新用户详细信息
			errMsg = roleService.updateRole(roleBean);
		}else {
			//新增
			errMsg = roleService.addRole(roleBean);
		}
		
		if(!StringUtils.isEmpty(errMsg)) {
			return RespBean.fail(errMsg);
		}
		
		return RespBean.success("操作成功");
	}
	
	@RequestMapping("/toDelRole.do")
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public RespBean toDelRole(String[] ids) {
		roleService.deleteRole(ids);
		return RespBean.success("操作成功");
	}
}
