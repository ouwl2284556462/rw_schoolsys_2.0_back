$(function() {
	//设置年选择
	comm_ui_setYearPicker("#courseYear");

	$("#teacher-add-courseName").click(teacher_add_course_courseNameClick);
	$("#teacher-add-course-info-dtl-form-returnBtn").click(teacher_add_course_onReturnBtnClick);
	
	$("#teacher-add-course-info-dtl-from").on('submit', teacher_add_course_onCourseDtlFormSubmit);
});

//新增课程
function teacher_add_course_onCourseDtlFormSubmit(){
	var param = {};
	param.submitBtnId = "teacher-add-course-info-dtl-form-submitBtn";

	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Success" == data){
				comm_ui_showMessage("操作成功");
				teacher_add_course_ChgToManageCourseList();
				//刷新列表
				teachselcourse_manage_onQryCourseListFormSubmit();
			}else{
				comm_ui_showMessage(data);
			}
		}
	}

	comm_Ajax_submitForm("teacher-add-course-info-dtl-from", param);
	return false;
}


function teacher_add_course_onReturnBtnClick(){
	teacher_add_course_ChgToManageCourseList();
	return false;
}

function teacher_add_course_ChgToManageCourseList(){
	comm_ui_show($("#teachselcourse-qry-list-div"));
	$("#teachselcourse-manage-second-page-div").empty();
}


//课程名被点击
function teacher_add_course_courseNameClick() {
	var param = {};
	param.callback = function(isSuccess, data) {
		if (!isSuccess) {
			return;
		}

		var confirmWinParam = {};
		confirmWinParam.title = "请选择课程";
		confirmWinParam.confirmCallback = function(){
			var selectedCourseInfo = teacher_course_qry_sel_course_list_getSelectCourseItem();
			if(selectedCourseInfo == null){
				comm_ui_showPopoverMessage("teacher-course-qry-sel-course-list-table-head", '<span class="text-danger">请选择一条记录！</span>');
				return false;
			}
			
			$("#teacher-add-courseName").val(selectedCourseInfo.courseName);
			$("#teacher-add-courseId").val(selectedCourseInfo.courseId);
			return true;
		};
		comm_ui_showConfirm(data, confirmWinParam);
	}

	var url = $("#teacher-add-courseName").data("qry-url");
	comm_Ajax_post(url, param);
}

