package schoolsys.teachselcourse.mapper;

import java.util.List;

import schoolsys.teachselcourse.bean.TeacherCourseBean;

public interface TeachSelCourseMapper {

	/**
	 * 查询选课
	 * @param courseBean
	 * @return
	 */
	List<TeacherCourseBean> qryTeacherCourseListByPage(TeacherCourseBean courseBean);

	/**
	 * 插入课程
	 * @param courseBean
	 */
	void insertCourse(TeacherCourseBean courseBean);

	/**
	 * 通过id找课程
	 * @param myselcourseId
	 * @return
	 */
	TeacherCourseBean findCourseById(int myselcourseId);

	/**
	 * 更新课程
	 * @param courseBean
	 */
	void updateCourse(TeacherCourseBean courseBean);

	/**
	 * 删除课程
	 * @param ids
	 */
	void deleteCourse(String[] ids);


}
