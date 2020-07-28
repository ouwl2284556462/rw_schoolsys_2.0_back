package schoolsys.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Controller
@RequestMapping("/CourseManage")
@PreAuthorize("hasPermission('courseAdmin', 'all')")
/**
 * 课程管理
 *
 */
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping("/toAddCourse.do")
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toAddCourse(Model model) {
		model.addAttribute("from", "adminAddCourse");
		return "course_manage/course_info";
	}
	
	@RequestMapping("/toChgCourse.do")
	/**
	 * 跳转到课程添加界面
	 * @return
	 */
	public String toChgCourse(Model model, int courseId) {
		CourseBean courseBean = courseService.findCourseById(courseId);
		model.addAttribute("from", "adminChgCourse");
		model.addAttribute("courseBean", courseBean);
		return "course_manage/course_info";
	}
	

	@RequestMapping("/toCourseManage.do")
	/**
	 * 跳转到课程管理
	 * @return
	 */
	public String toCourseManage() {
		return "course_manage/course_manage";
	}
	
	@RequestMapping("/qryCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public String qryCourseList(Model model, CourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<CourseBean> pageInfo = courseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		model.addAttribute("pageInfo", pageInfo);
		return "course_manage/course_list";
	}
	
	
	@RequestMapping("courseModify.do")
	@ResponseBody
	public String courseModify(CourseBean courseBean, Model model) {
		String returnMsg = "Success";
		
		String errMsg = null;
		if(courseBean.getId() != null) {
			//更新用户详细信息
			errMsg = courseService.updateCourse(courseBean);
			model.addAttribute("courseBean", courseBean);
		}else {
			//新增
			errMsg = courseService.addCourse(courseBean);
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
		courseService.deleteCourse(ids);
		return "Success";
	}
}
