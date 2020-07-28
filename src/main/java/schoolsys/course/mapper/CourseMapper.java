package schoolsys.course.mapper;

import java.util.List;

import schoolsys.course.bean.CourseBean;

public interface CourseMapper {

	/**
	 * 查询用户列表
	 * @param courseBean
	 * @return
	 */
	List<CourseBean> qryCourseList(CourseBean courseBean);

	/**
	 * 更新课程
	 * @param courseBean
	 */
	void updateCourse(CourseBean courseBean);

	/**
	 * 新增课程
	 * @param courseBean
	 */
	void addCourse(CourseBean courseBean);

	/**
	 * 根据课程名称查找数据
	 * @param name
	 * @return
	 */
	CourseBean qryCourseByName(String name);

	/**
	 * 通过Id找course
	 * @param courseId
	 * @return
	 */
	CourseBean qryCourseById(int courseId);

	/**
	 * 删除课程
	 * @param ids
	 */
	void deleteCourse(String[] ids);

}
