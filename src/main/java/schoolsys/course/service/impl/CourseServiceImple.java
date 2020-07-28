package schoolsys.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import schoolsys.course.bean.CourseBean;
import schoolsys.course.mapper.CourseMapper;
import schoolsys.course.service.CourseService;

@Service
public class CourseServiceImple implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public PageInfo<CourseBean> qryCourseListByPage(CourseBean courseBean, Integer pageNum, int pagePerNum) {
		PageHelper.startPage(pageNum, pagePerNum);
		//查找用户列表
		List<CourseBean> result = courseMapper.qryCourseList(courseBean);
		PageInfo<CourseBean> pageInfo = new PageInfo<CourseBean>(result);
		return pageInfo;
	}

	@Override
	public String updateCourse(CourseBean courseBean) {
		CourseBean oldBean = findCourseById(courseBean.getId());
		if(!oldBean.getName().equals(courseBean.getName()) && isCourseNameUsed(courseBean.getName())) {
			return "该课程名已存在";
		}
		
		courseMapper.updateCourse(courseBean);
		return null;
	}

	@Override
	public String addCourse(CourseBean courseBean) {
		if(isCourseNameUsed(courseBean.getName())) {
			return "该课程名已存在";
		}
		
		courseMapper.addCourse(courseBean);
		return null;
	}

	private boolean isCourseNameUsed(String name) {
		return null != courseMapper.qryCourseByName(name);
	}

	@Override
	public CourseBean findCourseById(int courseId) {
		return courseMapper.qryCourseById(courseId);
	}

	
	@Override
	public void deleteCourse(String[] ids) {
		courseMapper.deleteCourse(ids);
	}
}
