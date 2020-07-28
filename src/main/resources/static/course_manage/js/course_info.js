$(function(){
  	$("#course-info-dtl-from").on('submit', course_info_onCourseDtlFormSubmit);
  	
  	
  	$("#course-info-dtl-form-returnBtn").click(course_info_onReturnBtnClick);
});

function course_info_onReturnBtnClick(){
	course_info_checkAndChgToManageCourseList();
	return false;
}

function course_info_checkAndChgToManageCourseList(){
	comm_ui_show($("#course-manage-course-list-div"));
	$("#course-manage-second-page-div").empty();
}

function course_info_onCourseDtlFormSubmit(){
	var param = {};
	param.submitBtnId = "course-info-dtl-form-submitBtn";

	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Success" == data){
				comm_ui_showMessage("操作成功");
				course_info_checkAndChgToManageCourseList();
				//刷新列表
				course_manage_onQryCourseListFormSubmit();
			}else{
				comm_ui_showMessage(data);
			}
		}
	}

	comm_Ajax_submitForm("course-info-dtl-from", param);
	return false;
}

