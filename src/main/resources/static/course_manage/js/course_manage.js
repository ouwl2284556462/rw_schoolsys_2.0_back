$(function(){
	$("#course-manage-qry-form").on('submit', function(){
		course_manage_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#course-manage-qry-list-btn").click();
});

function course_manage_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#course-manage-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "course-manage-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#course-manage-course-list-root-div").html(data);
	}
	
	comm_Ajax_submitForm("course-manage-qry-form", param);	
}