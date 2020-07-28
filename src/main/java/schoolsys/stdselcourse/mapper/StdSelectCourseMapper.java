package schoolsys.stdselcourse.mapper;

import java.util.List;

import schoolsys.stdselcourse.bean.StudentCourseBean;

public interface StdSelectCourseMapper {

	/**
	 * 查询学生课程
	 * @param courseBean
	 * @return
	 */
	List<StudentCourseBean> qryCourseList(StudentCourseBean courseBean);

	/**
	 * 添加课程
	 * @param accountName
	 * @param teacherCourseId
	 */
	void addCourse(String accountName, String teacherCourseId);

	/**
	 * 删除课程
	 * @param accountName
	 * @param ids
	 */
	void deleteCourse(String accountName, String[] ids);

	/**
	 * 更新分数
	 * @param courseBean
	 */
	void updateScore(StudentCourseBean courseBean);

}
