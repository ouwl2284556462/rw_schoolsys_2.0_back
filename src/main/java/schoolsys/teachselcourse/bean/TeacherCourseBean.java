package schoolsys.teachselcourse.bean;

import java.sql.Timestamp;

import schoolsys.course.bean.CourseBean;

/**
 * 教师选课
 */
public class TeacherCourseBean {
	// id
	private Integer id;
	// 帐号名
	private String accountName;
	// 教师姓名
	private String userName;
	// 课程id
	private Integer courseId;
	// 学年
	private Integer year;
	// 学期
	private String semester;
	// 备注
	private String mark;
	// 状态
	private String status;
	// 创建时间
	private Timestamp createTime;
	// 课程信息
	private CourseBean course;
	// 用户模糊查询
	private String accountNameLike;
	// 排除该学生选过的课程
	private String notStudentAccountName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public CourseBean getCourse() {
		return course;
	}

	public void setCourse(CourseBean course) {
		this.course = course;
	}

	public String getCourseName() {
		if (course == null) {
			return null;
		}

		return course.getName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNameLike() {
		return accountNameLike;
	}

	public void setAccountNameLike(String accountNameLike) {
		this.accountNameLike = accountNameLike;
	}

	public String getNotStudentAccountName() {
		return notStudentAccountName;
	}

	public void setNotStudentAccountName(String notStudentAccountName) {
		this.notStudentAccountName = notStudentAccountName;
	}

}
