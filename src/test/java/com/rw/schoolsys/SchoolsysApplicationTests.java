package com.rw.schoolsys;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schoolsys.SchoolsysApplication;
import schoolsys.user.bean.SysPrivilegeBean;
import schoolsys.user.bean.SysRoleBean;
import schoolsys.user.bean.UserBean;
import schoolsys.user.service.UserService;

@SpringBootTest(classes = SchoolsysApplication.class)
class SchoolsysApplicationTests {

	@Autowired
	private UserService userService;

	/**
	 * 创建管理帐号
	 */
	@Test
	void createAdminAcocunt() {
		UserBean bean = new UserBean();
		bean.setAccountName("myAdmin");
		bean.setIdentityNum("1122443355");
		bean.setIdentityType("1");
		bean.setName("管理员");
		bean.setPassword("a123456");
		bean.setTel("15813329111");
		bean.setSex("1");

		SysRoleBean role = new SysRoleBean();
		role.setName("admin");;
		bean.setRole(role);;
		
		List<SysPrivilegeBean> priList = new ArrayList<>();
		SysPrivilegeBean p1 = new SysPrivilegeBean();
		p1.setResId("userAdmin");
		p1.setPermission("all");
		p1.setMark("用户管理菜单");
		
		priList.add(p1);
		role.setPrivileges(priList);

		userService.addUser(bean);
	}

}
