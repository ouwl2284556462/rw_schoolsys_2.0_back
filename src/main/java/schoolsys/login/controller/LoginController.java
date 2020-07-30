package schoolsys.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import schoolsys.base.bean.CsrfTokenBean;
import schoolsys.base.bean.RespBean;
import schoolsys.login.bean.LoginUserBean;
import schoolsys.user.bean.UserBean;
import schoolsys.user.service.UserService;

@RestController
@RequestMapping("/login")
/**
 *登录相关 
 */
public class LoginController {
	
	@Autowired
	/**
	 * 用户信息服务
	 */
	private UserService userService;
	
	@RequestMapping("/noLogin.do")
	public RespBean noLogin() {
		return RespBean.fail("请先登录");
	}
	
	@RequestMapping("/loginFail.do")
	public RespBean loginFail() {
		return RespBean.fail("登录失败");
	}
	
	@RequestMapping("/logoutSuccess.do")
	public RespBean logoutSuccess() {
		return RespBean.success("注销成功");
	}
	
	
	@RequestMapping("/loginSuccuess.do")
	public RespBean loginSuccuess(CsrfToken _csrf) {
		LoginUserBean bean = new LoginUserBean();
		
		//token
		CsrfTokenBean token = new CsrfTokenBean();
		token.setHeaderName(_csrf.getHeaderName());
		token.setToken(_csrf.getToken());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取当前用户的详细信息
		UserBean userBean = userService.findUserByAcctName(user.getUsername());
		
		bean.setCsrfToken(token);
		bean.setUser(userBean);
		return RespBean.success(bean);
	}
	
	@RequestMapping("/getToken.do")
	public RespBean getToken(CsrfToken _csrf) {
		CsrfTokenBean bean = new CsrfTokenBean();
		bean.setHeaderName(_csrf.getHeaderName());
		bean.setToken(_csrf.getToken());
		return RespBean.success(bean);
	}
	
	@RequestMapping("/loginErr.do")
	/**
	 * 跳转到登录页面，并带有错误信息
	 * @param model
	 * @return
	 */
	public String loginErr(Model model) {
		model.addAttribute("loginError", true);
		return "/login/login";
	}
	
	
	@RequestMapping("/toCreateAccount.do")
	/**
	 * 跳转到创建帐号页面
	 * @return
	 */
	public String toCreateAccount() {
		return "login/create_account";
	}
	
	@RequestMapping("/createAccount.do")
	/**
	 * 注册账号
	 * @return
	 */
	public String createAccount(Model model, UserBean userBean) {
		String errMsg;
		try {
			//注册账号
			errMsg = userService.addUser(userBean);
		}catch (Exception e) {
			e.printStackTrace();
			errMsg = "创建帐号失败，请重试!";
		}
		
		if(null == errMsg) {
			errMsg = "-";
		}
		
		model.addAttribute("errMsg", errMsg);
		return "login/create_account";
	}
	
	
	@RequestMapping("/isAcctNameUsed.do")
	@ResponseBody
	/**
	 * 检查帐号是否已经被使用
	 * @param acctName
	 * @return
	 */
	public String isAcctNameUsed(String acctName) {
		if(userService.isAcctNameUsed(acctName)) {
			return "Y";
		}
		
		return "N";
	}
	
}
