$(function(){
	$("#teacher-course-qry-sel-qry-form").on('submit', function(){
		teacher_course_qry_sel_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#teacher-course-qry-sel-qry-list-btn").click();
});

function teacher_course_qry_sel_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#teacher-course-qry-sel-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "teacher-course-qry-sel-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#teacher-course-qry-sel-list-root-div").html(data);
	}
	
	comm_Ajax_submitForm("teacher-course-qry-sel-qry-form", param);	
}