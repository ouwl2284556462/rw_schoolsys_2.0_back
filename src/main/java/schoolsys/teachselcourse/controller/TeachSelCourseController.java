package schoolsys.teachselcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import schoolsys.base.bean.RespBean;
import schoolsys.base.constants.CommPageConst;
import schoolsys.course.bean.CourseBean;
import schoolsys.course.service.CourseService;
import schoolsys.teachselcourse.bean.TeacherCourseBean;
import schoolsys.teachselcourse.service.TeachSelCourseService;

@RestController
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

	
	
	@RequestMapping("/qryMyCourseList.do")
	/**
	 * 查询我的选课
	 * @return
	 */
	public RespBean qryMyCourseList(TeacherCourseBean teacherCourseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		teacherCourseBean.setAccountName(userDetails.getUsername());
		PageInfo<TeacherCourseBean> pageInfo = teachSelCourseService.qryTeacherCourseListByPage(teacherCourseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	
	@RequestMapping("/qryCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryCourseList(CourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<CourseBean> pageInfo = courseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	@RequestMapping("/addCourseModify.do")
	public RespBean addCourseModify(TeacherCourseBean courseBean, Model model, @AuthenticationPrincipal UserDetails userDetails) {
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
			return RespBean.fail(errMsg);
		}
		
		return RespBean.success("处理成功");
	}
	
	@RequestMapping("/toDelCourse.do")
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public RespBean toDelCourse(String[] ids) {
		teachSelCourseService.deleteCourse(ids);
		return RespBean.success("处理成功");
	}
	
}
