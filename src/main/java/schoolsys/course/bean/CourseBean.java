package schoolsys.course.bean;

import java.sql.Timestamp;

/**
 * 课程信息
 */
public class CourseBean {
	private Integer id;
	// 课程名
	private String name;
	// 描述
	private String description;
	// 创建时间
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
