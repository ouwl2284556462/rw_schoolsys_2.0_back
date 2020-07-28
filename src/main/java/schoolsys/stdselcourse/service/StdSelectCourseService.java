package schoolsys.stdselcourse.service;

import com.github.pagehelper.PageInfo;

import schoolsys.stdselcourse.bean.StudentCourseBean;

public interface StdSelectCourseService {

	/**
	 * 删除课程
	 * @param accountName 
	 * @param ids
	 */
	void deleteCourse(String accountName, String[] ids);

	/**
	 * 分页查询
	 * @param courseBean
	 * @param pageNum
	 * @param pagePerNum
	 * @return
	 */
	PageInfo<StudentCourseBean> qryCourseListByPage(StudentCourseBean courseBean, Integer pageNum, int pagePerNum);

	/**
	 * 添加课程
	 * @param username
	 * @param teacherCourseId
	 */
	void addCourse(String username, String teacherCourseId);

	/**
	 * 更新分数
	 * @param courseBean
	 */
	void updateScore(StudentCourseBean courseBean);

}
