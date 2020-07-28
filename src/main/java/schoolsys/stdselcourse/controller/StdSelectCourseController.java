package schoolsys.stdselcourse.controller;

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
import schoolsys.base.constants.EnumConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;
import schoolsys.teachselcourse.bean.TeacherCourseBean;
import schoolsys.teachselcourse.service.TeachSelCourseService;

@Controller
@RequestMapping("/StdSelectCourse")
@PreAuthorize("hasPermission('stdSelectCourse', 'all')")
public class StdSelectCourseController {
	
	@Autowired
	private StdSelectCourseService stdSelectCourseService;
	@Autowired
	private TeachSelCourseService teachSelCourseService;
	
	
	@RequestMapping("/toAddCourse.do")
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toAddCourse(Model model) {
		return "stdselcourse/course_qry";
	}
	

	@RequestMapping("/toCourseManage.do")
	/**
	 * 跳转到课程管理
	 * @return
	 */
	public String toCourseManage() {
		return "stdselcourse/stdselcourse_manage";
	}
	
	@RequestMapping("/qryMyCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public String qryMyCourseList(Model model, StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		courseBean.setAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "stdselcourse/myselcourse_list";
	}
	
	
	@RequestMapping("/toDelCourse.do")
	@ResponseBody
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String toDelCourse(@RequestParam(value = "ids[]") String[] ids, @AuthenticationPrincipal UserDetails userDetails) {
		stdSelectCourseService.deleteCourse(userDetails.getUsername(), ids);
		return "Success";
	}
	
	
	@RequestMapping("/qryTeacherCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public String qryTeacherCourseList(Model model, TeacherCourseBean teacherCourseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @AuthenticationPrincipal UserDetails userDetails) {
		//排除已经选择了的课程
		teacherCourseBean.setNotStudentAccountName(userDetails.getUsername());
		//只选择正常状态的
		teacherCourseBean.setStatus(EnumConst.COURSE_STATUS_NORMAL);
		PageInfo<TeacherCourseBean> pageInfo = teachSelCourseService.qryTeacherCourseListByPage(teacherCourseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "stdselcourse/course_list";
	}
	
	@RequestMapping("/dealAddCourse.do")
	@ResponseBody
	/**
	 * 添加课程
	 * @param ids
	 * @return
	 */
	public String dealAddCourse(String courseId, @AuthenticationPrincipal UserDetails userDetails) {
		stdSelectCourseService.addCourse(userDetails.getUsername(), courseId);
		return "Success";
	}
	

}
