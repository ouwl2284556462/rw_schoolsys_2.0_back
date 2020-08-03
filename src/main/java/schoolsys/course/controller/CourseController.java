package schoolsys.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import schoolsys.base.bean.RespBean;
import schoolsys.base.constants.CommPageConst;
import schoolsys.course.bean.CourseBean;
import schoolsys.course.service.CourseService;

@RestController
@RequestMapping("/CourseManage")
@PreAuthorize("hasPermission('courseAdmin', 'all')")
/**
 * 课程管理
 *
 */
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	
	
	@RequestMapping("/qryCourseList.do")
	/**
	 * 查询课程列表
	 * @return
	 */
	public RespBean qryCourseList(CourseBean courseBean, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum ) {
		PageInfo<CourseBean> pageInfo = courseService.qryCourseListByPage(courseBean, pageNum, CommPageConst.PAGE_PER_NUM);
		return RespBean.success(pageInfo);
	}
	
	
	@RequestMapping("courseModify.do")
	public RespBean courseModify(CourseBean courseBean) {
		
		String errMsg = null;
		if(courseBean.getId() != null) {
			//更新用户详细信息
			errMsg = courseService.updateCourse(courseBean);
		}else {
			//新增
			errMsg = courseService.addCourse(courseBean);
		}
		
		if(!StringUtils.isEmpty(errMsg)) {
			return RespBean.fail(errMsg);
		}
		
		return RespBean.success("操作成功");
	}
	
	@RequestMapping("/toDelCourse.do")
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public RespBean toDelCourse(String[] ids) {
		courseService.deleteCourse(ids);
		return RespBean.success("操作成功");
	}
}
