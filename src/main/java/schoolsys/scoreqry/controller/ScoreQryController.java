package schoolsys.scoreqry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import schoolsys.base.bean.RespBean;
import schoolsys.base.constants.CommPageConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;

@RestController
@RequestMapping("/ScoreQry")
@PreAuthorize("hasPermission('scoreQry', 'all')")
public class ScoreQryController {
	@Autowired
	private StdSelectCourseService stdSelectCourseService;
	
	
	@RequestMapping("/qryScoreList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryScoreList(StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		//查询当前学生的账号
		courseBean.setAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
}
