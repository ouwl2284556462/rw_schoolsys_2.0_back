package schoolsys.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import schoolsys.user.bean.UserBean;

public interface UserMapper {
	/**
	 * 插入账户信息
	 * @param bean
	 */
	public void addAcctInfo(UserBean bean);
	
	/**
	 * 插入用户详细信息
	 * @param bean
	 */
	public void addUseDtlInfo(UserBean bean);
	
	/**
	 * 根据ID找用户信息
	 * @param id
	 * @return
	 */
	public UserBean findUserInfoByAccountName(String accountName);

	/**
	 * 更新用户详细信息
	 * @param bean
	 */
	public void updateUserDtl(UserBean bean);

	/**
	 * 更新密码
	 * @param userBean
	 */
	public void updateUserPassword(UserBean userBean);

	/**
	 * 插入角色
	 * @param roleName
	 * @param userId
	 */
	public void insertSysUserRole(@Param("roleName") String roleName, @Param("userId")  Integer userId);

	/**
	 * 查询用户列表
	 * @param userBean
	 * @return
	 */
	public List<UserBean> qryUserList(UserBean userBean);

	/**
	 * 删除用户
	 * @param ids
	 */
	public void deleteAccount(Integer[] ids);

	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public UserBean findUserById(int id);

	/**
	 * 更新密码
	 * @param userId
	 * @param newPassword
	 */
	public void updateUserPasswordById(@Param("userId") int userId, @Param("newPassword")  String newPassword);

	/**
	 * 根据角色名
	 * @param id
	 * @param name
	 */
	public void updateUserRoleByName(@Param("userId") Integer id, @Param("roleName")  String name);

	
}
