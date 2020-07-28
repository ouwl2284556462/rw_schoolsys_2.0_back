package schoolsys.role.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import schoolsys.dict.bean.SysDictItemBean;
import schoolsys.dict.service.SysDictItemService;
import schoolsys.role.mapper.RoleMapper;
import schoolsys.role.service.RoleService;
import schoolsys.user.bean.SysRoleBean;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private SysDictItemService sysDictItemService;

	@Override
	public SysRoleBean findRoleById(int roleId) {
		return roleMapper.qryRoleById(roleId);
	}

	@Override
	public PageInfo<SysRoleBean> qryRoleListByPage(SysRoleBean roleBean, Integer pageNum, int pagePerNum) {
		PageHelper.startPage(pageNum, pagePerNum);
		//查找用户列表
		List<SysRoleBean> result = roleMapper.qryRoleList(roleBean);
		PageInfo<SysRoleBean> pageInfo = new PageInfo<SysRoleBean>(result);
		return pageInfo;
	}

	@Transactional
	@Override
	public String updateRole(SysRoleBean roleBean) {
		SysRoleBean oldRole = roleMapper.qryRoleById(roleBean.getId());
		if(!oldRole.getName().equals(roleBean.getName()) && isRoleNameExist(roleBean.getName())) {
			return "该角色ID已存在";
		}
		
		//更新角色表
		roleMapper.updateRole(roleBean);
		
		Integer roleId = roleBean.getId();
		//更新角色权限
		roleMapper.deleteRolePrivilege(roleId);
		insertRolePrivilegeArray(roleBean.getPrivilegeArray(), roleId);
		
		//更新字典表
		SysDictItemBean dict = new SysDictItemBean();
		dict.setGroupId("sys_role");
		dict.setItemId(roleBean.getName());
		dict.setItemName(roleBean.getChName());
		dict.setOldItemId(oldRole.getName());
		sysDictItemService.updateDictItem(dict);
		return null;
	}

	@Transactional
	@Override
	public String addRole(SysRoleBean roleBean) {
		if(isRoleNameExist(roleBean.getName())) {
			return "该角色ID已存在";
		}
		
		//插入角色
		roleMapper.addRole(roleBean);
		
		//插入角色权限
		Integer roleId = roleBean.getId();
		insertRolePrivilegeArray(roleBean.getPrivilegeArray(), roleId);
		
		SysDictItemBean dict = new SysDictItemBean();
		dict.setGroupId("sys_role");
		dict.setItemId(roleBean.getName());
		dict.setItemName(roleBean.getChName());
		dict.setSort(roleBean.getId());
		//插入字典表
		sysDictItemService.insertDictItem(dict);
		return null;
	}

	private void insertRolePrivilegeArray(String[] privilegeArray, Integer roleId) {
		Optional.ofNullable(privilegeArray).ifPresent(priArray ->{
			Arrays.stream(priArray).forEach(pri ->{
				roleMapper.insertRolePrivilege(roleId, pri, "all");
			});
		});
	}

	/**
	 * 检查roleName是否存在
	 * @param roleName
	 * @return
	 */
	private boolean isRoleNameExist(String roleName) {
		return roleMapper.qryRoleByName(roleName) != null;
	}

	@Transactional
	@Override
	public void deleteRole(String[] roleIds) {

		for(String roleId : roleIds) {
			int roleIdNum = Integer.parseInt(roleId);
			//删除用户权限
			roleMapper.deleteRolePrivilege(roleIdNum);
			
			//删除字典表数据
			SysRoleBean roleBean = roleMapper.qryRoleById(roleIdNum);
			sysDictItemService.deleteDictItem("sys_role", roleBean.getName());
		}
		
		//删除角色
		roleMapper.deleteRole(roleIds);
	}

}
