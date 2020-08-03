package schoolsys.scoreimport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import schoolsys.base.bean.RespBean;
import schoolsys.base.constants.CommPageConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;

@RestController
@RequestMapping("/ScoreImport")
@PreAuthorize("hasPermission('scoreImport', 'all')")
public class ScoreImportController {
	@Autowired
	private StdSelectCourseService stdSelectCourseService;
	
	
	@RequestMapping("/qryStudentList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryStudentList(StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		//查询当前教师的账号
		courseBean.setTeacherAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	
	@RequestMapping("/doUpdateScore.do")
	/**
	 * 更新分数
	 * @return
	 */
	public RespBean doUpdateScore(StudentCourseBean courseBean, Model model) {
		stdSelectCourseService.updateScore(courseBean);
		return RespBean.success("处理成功");
	}
	
}
