package schoolsys.course.service;

import com.github.pagehelper.PageInfo;

import schoolsys.course.bean.CourseBean;

/**
 * 课程管理 
 *
 */
public interface CourseService {

	/**
	 * 分页查询课程列表
	 * @param courseBean
	 * @param pageNum
	 * @param courseListPagePerNum
	 * @return
	 */
	PageInfo<CourseBean> qryCourseListByPage(CourseBean courseBean, Integer pageNum, int pagePerNum);

	/**
	 * 更新课程
	 * @param courseBean
	 */
	String updateCourse(CourseBean courseBean);

	/**
	 * 新增课程
	 * @param courseBean
	 * @return
	 */
	String addCourse(CourseBean courseBean);

	/**
	 * 通过id找course
	 * @param courseId
	 * @return
	 */
	CourseBean findCourseById(int courseId);

	/**
	 * 删除课程
	 * @param ids
	 */
	void deleteCourse(String[] ids);

}
