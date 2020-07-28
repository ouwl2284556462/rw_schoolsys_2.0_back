package schoolsys.teachselcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import schoolsys.base.constants.CommPageConst;
import schoolsys.course.bean.CourseBean;
import schoolsys.course.service.CourseService;
import schoolsys.teachselcourse.bean.TeacherCourseBean;
import schoolsys.teachselcourse.service.TeachSelCourseService;

@Controller
@RequestMapping("/TeacherSelectCourse")
@PreAuthorize("hasPermission('tchSelectCourse', 'all')")
/**
 * 教师选课
 *
 */
public class TeachSelCourseController {
	
	@Autowired
	private TeachSelCourseService teachSelCourseService;
	@Autowired
	private CourseService courseService;

	@RequestMapping("/toSelectCourse.do")
	/**
	 * 跳转到教师选课界面
	 * @return
	 */
	public String toSelectCourse() {
		return "teachselcourse/teachselcourse_manage";
	}
	
	
	@RequestMapping("/qryMyCourseList.do")
	/**
	 * 查询我的选课
	 * @return
	 */
	public String qryMyCourseList(TeacherCourseBean teacherCourseBean, Model model, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		teacherCourseBean.setAccountName(userDetails.getUsername());
		PageInfo<TeacherCourseBean> pageInfo = teachSelCourseService.qryTeacherCourseListByPage(teacherCourseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "teachselcourse/myselcourse_list";
	}
	
	
	@RequestMapping("/toAddCourse.do")
	/**
	 * 跳转到添加课程
	 * @return
	 */
	public String toAddCourse(Model model) {
		model.addAttribute("from", "teacherAddCourse");
		return "teachselcourse/teacher_add_course";
	}
	
	@RequestMapping("/toChgCourse.do")
	/**
	 * 跳转到添加课程
	 * @return
	 */
	public String toChgCourse(Model model, int myselcourseId) {
		TeacherCourseBean courseBean = teachSelCourseService.findCourseById(myselcourseId);
		model.addAttribute("from", "teacherChgCourse");
		model.addAttribute("courseBean", courseBean);
		return "teachselcourse/teacher_add_course";
	}
	
	
	
	@RequestMapping("/toQryCourse.do")
	/**
	 * 跳转到查询课程
	 * @return
	 */
	public String toQryCourse() {
		return "teachselcourse/course_qry";
	}
	
	@RequestMapping("/qryCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public String qryCourseList(Model model, CourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<CourseBean> pageInfo = courseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "teachselcourse/course_list";
	}
	
	@RequestMapping("/addCourseModify.do")
	@ResponseBody
	public String addCourseModify(TeacherCourseBean courseBean, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		String returnMsg = "Success";
		courseBean.setAccountName(userDetails.getUsername());
		
		String errMsg = null;
		if(courseBean.getId() != null) {
			//更新课程信息
			errMsg = teachSelCourseService.updateCourse(courseBean);
		}else {
			//新增
			errMsg = teachSelCourseService.addCourse(courseBean);
		}
		
		if(!StringUtils.isEmpty(errMsg)) {
			returnMsg = errMsg;
		}
		
		return returnMsg;
	}
	
	@RequestMapping("/toDelCourse.do")
	@ResponseBody
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String toDelCourse(@RequestParam(value = "ids[]") String[] ids) {
		teachSelCourseService.deleteCourse(ids);
		return "Success";
	}
	
}
