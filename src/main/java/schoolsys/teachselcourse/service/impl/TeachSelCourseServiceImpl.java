package schoolsys.teachselcourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import schoolsys.base.constants.EnumConst;
import schoolsys.teachselcourse.bean.TeacherCourseBean;
import schoolsys.teachselcourse.mapper.TeachSelCourseMapper;
import schoolsys.teachselcourse.service.TeachSelCourseService;

@Service
public class TeachSelCourseServiceImpl implements TeachSelCourseService {

	@Autowired
	private TeachSelCourseMapper teachSelCourseMapper;
	
	@Override
	public PageInfo<TeacherCourseBean> qryTeacherCourseListByPage(TeacherCourseBean courseBean, Integer pageNum,
			int pagePerNum) {
		PageHelper.startPage(pageNum, pagePerNum);
		List<TeacherCourseBean> result = teachSelCourseMapper.qryTeacherCourseListByPage(courseBean);
		PageInfo<TeacherCourseBean> pageInfo = new PageInfo<TeacherCourseBean>(result);
		return pageInfo;
	}

	@Override
	public String updateCourse(TeacherCourseBean courseBean) {
		teachSelCourseMapper.updateCourse(courseBean);
		return null;
	}

	@Override
	public String addCourse(TeacherCourseBean courseBean) {
		courseBean.setStatus(EnumConst.COURSE_STATUS_NORMAL);
		teachSelCourseMapper.insertCourse(courseBean);
		return null;
	}

	@Override
	public TeacherCourseBean findCourseById(int myselcourseId) {
		return teachSelCourseMapper.findCourseById(myselcourseId);
	}

	@Override
	public void deleteCourse(String[] ids) {
		teachSelCourseMapper.deleteCourse(ids);
	}

}
