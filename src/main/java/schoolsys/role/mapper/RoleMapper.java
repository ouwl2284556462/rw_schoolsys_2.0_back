package schoolsys.role.mapper;

import java.util.List;

import schoolsys.user.bean.SysRoleBean;

public interface RoleMapper {

	/**
	 * 查询用户列表
	 * @param roleBean
	 * @return
	 */
	List<SysRoleBean> qryRoleList(SysRoleBean roleBean);

	/**
	 * 添加角色
	 * @param roleBean
	 */
	void addRole(SysRoleBean roleBean);

	/**
	 * 根据名字找角色
	 * @param name
	 * @return
	 */
	SysRoleBean qryRoleByName(String name);

	/**
	 * 插入角色权限
	 * @param roleId
	 * @param resId
	 * @param permission
	 */
	void insertRolePrivilege(Integer roleId, String resId, String permission);

	/**
	 * 根据Id查找
	 * @param id
	 * @return
	 */
	SysRoleBean qryRoleById(Integer id);

	/**
	 * 更新角色
	 * @param roleBean
	 */
	void updateRole(SysRoleBean roleBean);

	/**
	 * 删除角色全新
	 * @param roleId
	 */
	void deleteRolePrivilege(Integer roleId);

	void deleteRole(String[] roleIds);

}
