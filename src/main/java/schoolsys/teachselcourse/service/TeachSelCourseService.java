package schoolsys.teachselcourse.service;

import com.github.pagehelper.PageInfo;

import schoolsys.teachselcourse.bean.TeacherCourseBean;

/**
 * 教师选课
 */
public interface TeachSelCourseService {

	/**
	 * 查找我的选课
	 * @param courseBean
	 * @param pageNum
	 * @param pagePerNum
	 * @return
	 */
	PageInfo<TeacherCourseBean> qryTeacherCourseListByPage(TeacherCourseBean courseBean, Integer pageNum, int pagePerNum);

	/**
	 * 更新课程
	 * @param courseBean
	 * @return
	 */
	String updateCourse(TeacherCourseBean courseBean);

	/**
	 * 插入课程
	 * @param courseBean
	 * @return
	 */
	String addCourse(TeacherCourseBean courseBean);

	/**
	 * 通过id找课程
	 * @param myselcourseId
	 * @return
	 */
	TeacherCourseBean findCourseById(int myselcourseId);

	/**
	 * 删除课程
	 * @param ids
	 */
	void deleteCourse(String[] ids);

}
