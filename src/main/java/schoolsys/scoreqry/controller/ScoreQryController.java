package schoolsys.scoreqry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import schoolsys.base.constants.CommPageConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;

@Controller
@RequestMapping("/ScoreQry")
@PreAuthorize("hasPermission('scoreQry', 'all')")
public class ScoreQryController {
	@Autowired
	private StdSelectCourseService stdSelectCourseService;
	
	@RequestMapping("/toScoreQry.do")
	/**
	 * 跳转到成绩查询页面
	 * @return
	 */
	public String toScoreQry() {
		return "scoreqry/score_qry";
	}
	
	@RequestMapping("/qryScoreList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public String qryScoreList(Model model, StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		//查询当前学生的账号
		courseBean.setAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "scoreqry/myselcourse_list";
	}
}
