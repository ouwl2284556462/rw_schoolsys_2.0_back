package schoolsys.scoreimport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import schoolsys.base.constants.CommPageConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;

@Controller
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
	public String qryStudentList(Model model, StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		//查询当前教师的账号
		courseBean.setTeacherAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "scoreimport/myselcourse_list";
	}
	
	@RequestMapping("/toScoreImport.do")
	/**
	 * 跳转到成绩录入页面
	 * @return
	 */
	public String toScoreImport() {
		return "scoreimport/scoreimport_manage";
	}
	
	@RequestMapping("/toUpdateScore.do")
	/**
	 * 跳转到成绩更新页面
	 * @return
	 */
	public String toUpdateScore(StudentCourseBean courseBean, Model model) {
		model.addAttribute("courseBean", courseBean);
		return "scoreimport/update_score";
	}
	
	
	@RequestMapping("/doUpdateScore.do")
	@ResponseBody
	/**
	 * 更新分数
	 * @return
	 */
	public String doUpdateScore(StudentCourseBean courseBean, Model model) {
		stdSelectCourseService.updateScore(courseBean);
		return "Success";
	}
	
}
