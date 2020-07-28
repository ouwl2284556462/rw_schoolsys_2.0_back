package schoolsys.role.service;

import com.github.pagehelper.PageInfo;

import schoolsys.user.bean.SysRoleBean;

/**
 * 角色服务类 
 */
public interface RoleService {

	/**
	 * 通过Id查找Role
	 * @param roleId
	 * @return
	 */
	SysRoleBean findRoleById(int roleId);

	/**
	 * 分页查找role
	 * @param roleBean
	 * @param pageNum
	 * @param pagePerNum
	 * @return
	 */
	PageInfo<SysRoleBean> qryRoleListByPage(SysRoleBean roleBean, Integer pageNum, int pagePerNum);

	/**
	 * 更新角色信息
	 * @param roleBean
	 * @return
	 */
	String updateRole(SysRoleBean roleBean);

	/**
	 * 添加角色
	 * @param roleBean
	 * @return
	 */
	String addRole(SysRoleBean roleBean);

	/**
	 * 删除角色
	 * @param ids
	 */
	void deleteRole(String[] ids);

}
