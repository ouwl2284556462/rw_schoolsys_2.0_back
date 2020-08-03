package schoolsys.stdselcourse.controller;

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
import schoolsys.base.constants.EnumConst;
import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.service.StdSelectCourseService;
import schoolsys.teachselcourse.bean.TeacherCourseBean;
import schoolsys.teachselcourse.service.TeachSelCourseService;

@RestController
@RequestMapping("/StdSelectCourse")
@PreAuthorize("hasPermission('stdSelectCourse', 'all')")
public class StdSelectCourseController {
	
	@Autowired
	private StdSelectCourseService stdSelectCourseService;
	@Autowired
	private TeachSelCourseService teachSelCourseService;
	
	
	@RequestMapping("/qryMyCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryMyCourseList(StudentCourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,  @AuthenticationPrincipal UserDetails userDetails) {
		courseBean.setAccountName(userDetails.getUsername());
		PageInfo<StudentCourseBean> pageInfo = stdSelectCourseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	
	@RequestMapping("/toDelCourse.do")
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public RespBean toDelCourse(String[] ids, @AuthenticationPrincipal UserDetails userDetails) {
		stdSelectCourseService.deleteCourse(userDetails.getUsername(), ids);
		return RespBean.success("处理成功");
	}
	
	
	@RequestMapping("/qryTeacherCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryTeacherCourseList(TeacherCourseBean teacherCourseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @AuthenticationPrincipal UserDetails userDetails) {
		//排除已经选择了的课程
		teacherCourseBean.setNotStudentAccountName(userDetails.getUsername());
		//只选择正常状态的
		teacherCourseBean.setStatus(EnumConst.COURSE_STATUS_NORMAL);
		PageInfo<TeacherCourseBean> pageInfo = teachSelCourseService.qryTeacherCourseListByPage(teacherCourseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	@RequestMapping("/dealAddCourse.do")
	/**
	 * 添加课程
	 * @param ids
	 * @return
	 */
	public RespBean dealAddCourse(String courseId, @AuthenticationPrincipal UserDetails userDetails) {
		stdSelectCourseService.addCourse(userDetails.getUsername(), courseId);
		return RespBean.success("处理成功");
	}
	

}
