package schoolsys.stdselcourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import schoolsys.stdselcourse.bean.StudentCourseBean;
import schoolsys.stdselcourse.mapper.StdSelectCourseMapper;
import schoolsys.stdselcourse.service.StdSelectCourseService;

@Service
public class StdSelectCourseServiceImpl implements StdSelectCourseService {
	
	@Autowired
	private StdSelectCourseMapper stdSelectCourseMapper;

	@Override
	public void deleteCourse(String accountName, String[] ids) {
		stdSelectCourseMapper.deleteCourse(accountName, ids);
	}

	@Override
	public PageInfo<StudentCourseBean> qryCourseListByPage(StudentCourseBean courseBean, Integer pageNum,
			int pagePerNum) {
		PageHelper.startPage(pageNum, pagePerNum);
		//查找用户列表
		List<StudentCourseBean> result = stdSelectCourseMapper.qryCourseList(courseBean);
		PageInfo<StudentCourseBean> pageInfo = new PageInfo<StudentCourseBean>(result);
		return pageInfo;
	}

	@Override
	public void addCourse(String accountName, String teacherCourseId) {
		stdSelectCourseMapper.addCourse(accountName, teacherCourseId);
	}

	@Override
	public void updateScore(StudentCourseBean courseBean) {
		stdSelectCourseMapper.updateScore(courseBean);
	}

}
