package schoolsys.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import schoolsys.user.bean.SysRoleBean;
import schoolsys.user.bean.UserBean;
import schoolsys.user.mapper.UserMapper;
import schoolsys.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	//用户信息Mapper，用于与数据库交互
	private UserMapper userMapper;	
	
	@Autowired
	//密码加密工具
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	/**
	 * 添加用户
	 */
	public String addUser(UserBean bean) {
		if(isAcctNameUsed(bean.getAccountName())) {
			return "该账号名已经注册过";
		}
		
		//密码加密
		bean.setPassword(passwordEncoder.encode(bean.getPassword()));
		//插入账户信息
		userMapper.addAcctInfo(bean);
		//插入用户信息
		userMapper.addUseDtlInfo(bean);
		
		SysRoleBean role = bean.getRole();
		//插入用户角色关系表
		userMapper.insertSysUserRole(role.getName(), bean.getId());
		return null;
	}

	@Override
	/**
	 * 用户名是否已经使用过
	 */
	public boolean isAcctNameUsed(String acctName) {
		return null != userMapper.findUserInfoByAccountName(acctName);
	}
	
	@Override
	/**
	 * 根据用户名查找用户信息
	 */
	public UserBean findUserByAcctName(String acctName) {
		return userMapper.findUserInfoByAccountName(acctName);
	}

	@Override
	/**
	 * 登录时使用用户名查找用户信息
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//使用用户名查找用户信息
		UserBean userBean = findUserByAcctName(username);
        if (null == userBean) {
            throw new UsernameNotFoundException(username);
        }
        
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SysRoleBean role = userBean.getRole();
        Optional.ofNullable(role.getPrivileges()).ifPresent(prisList ->{
        	prisList.forEach(pri -> {
        		 authorities.add(new SimpleGrantedAuthority(String.format("%s-%s", pri.getResId(), pri.getPermission())));
        	});
        });
       
        return new User(userBean.getAccountName(), userBean.getPassword(), authorities);
	}

	@Transactional
	@Override
	/**
	 * 更新用户信息
	 */
	public void updateUserDtl(UserBean bean) {
		userMapper.updateUserDtl(bean);
		
		SysRoleBean role = bean.getRole();
		if(role != null) {
			userMapper.updateUserRoleByName(bean.getId(), role.getName());
		}
	}

	@Transactional
	@Override
	/**
	 * 更新用户密码
	 */
	public String updatePasswordWithCheck(String username, String oldPassword, String newPassword) {
		//根据帐号名查找用户信息
		UserBean userBean = findUserByAcctName(username);
		
		//判断输入的旧密码是否正确
		if(!passwordEncoder.matches(oldPassword, userBean.getPassword())) {
			return "旧密码输入不正确。";
		}
		
		//更新密码
		userBean.setPassword(passwordEncoder.encode(newPassword));
		userMapper.updateUserPassword(userBean);
		return null;
	}

	@Override
	/**
	 * 查找用户列表
	 */
	public PageInfo<UserBean> qryUserListByPage(UserBean userBean, int pageNum, int pagePerNum) {
		PageHelper.startPage(pageNum, pagePerNum);
		//查找用户列表
		List<UserBean> result = userMapper.qryUserList(userBean);
		PageInfo<UserBean> pageInfo = new PageInfo<UserBean>(result);
		return pageInfo;
	}

	@Transactional
	@Override
	/**
	 * 删除用户
	 */
	public void deleteUser(Integer[] ids) {
		userMapper.deleteAccount(ids);
	}

	@Override
	/**
	 * 根据id查找用户信息
	 */
	public UserBean findUserById(int id) {
		return userMapper.findUserById(id);
	}

	@Override
	/**
	 * 更新密码
	 */
	public void updatePassword(int userId, String newPassword) {
		newPassword = passwordEncoder.encode(newPassword);
		userMapper.updateUserPasswordById(userId, newPassword);
	}


}
