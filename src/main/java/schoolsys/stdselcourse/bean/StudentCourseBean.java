package schoolsys.stdselcourse.bean;

import java.sql.Timestamp;

/**
 * 学生选课信息
 */
public class StudentCourseBean {
	// 学生帐号
	private String accountName;
	// 学生姓名
	private String studentName;
	// id
	private Integer teacherCourseId;
	// 教师帐号名
	private String teacherAccountName;
	// 教师帐号名模糊查询
	private String teacherAccountNameLike;
	// 教师姓名
	private String teacherName;
	// 课程id
	private Integer courseId;
	// 课程名
	private String courseName;
	// 学年
	private Integer year;
	// 学期
	private String semester;
	//分数
	private Integer score;
	// 备注
	private String teacherCourseMark;
	// 状态
	private String status;
	// 创建时间
	private Timestamp createTime;
	// 用于模糊查询学生帐号
	private String accountNameLike;
	
	//是否已评分，0-否，用于查询
	private String scoreStatus;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}

	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
	}

	public String getTeacherAccountName() {
		return teacherAccountName;
	}

	public void setTeacherAccountName(String teacherAccountName) {
		this.teacherAccountName = teacherAccountName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getTeacherCourseMark() {
		return teacherCourseMark;
	}

	public void setTeacherCourseMark(String teacherCourseMark) {
		this.teacherCourseMark = teacherCourseMark;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAccountNameLike() {
		return accountNameLike;
	}

	public void setAccountNameLike(String accountNameLike) {
		this.accountNameLike = accountNameLike;
	}

	public String getTeacherAccountNameLike() {
		return teacherAccountNameLike;
	}

	public void setTeacherAccountNameLike(String teacherAccountNameLike) {
		this.teacherAccountNameLike = teacherAccountNameLike;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

}
