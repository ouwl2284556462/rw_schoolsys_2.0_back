$(function(){
	var moreConditionDiv = $("#student-course-qry-sel-more-condition");
	moreConditionDiv.on('hide.bs.collapse', function () {
		$("#student-course-qry-sel-more-condition-text").text('更多');
		$("#student-course-qry-sel-more-condition-icon").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		
	});
	
	moreConditionDiv.on('show.bs.collapse', function () {
		$("#student-course-qry-sel-more-condition-text").text('收起');
		$("#student-course-qry-sel-more-condition-icon").removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
	});
	
	
	//设置年选择
	comm_ui_setYearPicker("#student-course-qry-sel-courseYear");
	
	
	
	$("#student-course-qry-sel-qry-form").on('submit', function(){
		student_course_qry_sel_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#student-course-qry-sel-qry-list-btn").click();
});

function student_course_qry_sel_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#student-course-qry-sel-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "student-course-qry-sel-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#student-course-qry-sel-list-root-div").html(data);
	}
	
	comm_Ajax_submitForm("student-course-qry-sel-qry-form", param);	
}