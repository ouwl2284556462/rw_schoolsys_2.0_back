$(function(){
	$("#myselcourse-list-delBtn").click(myselcourse_list_onDelBtnClick);
	$("#myselcourse-list-addBtn").click(myselcourse_list_onAddBtnClick);
	
	
	$(".myselcourse-list-table-col-selectCheckBox").click(function(even){
		//阻止事件冒泡
		event.stopPropagation();
	});
	
	$(".myselcourse-list-table-row").click(function(even){
		myselcourse_list_onTableRowClick(this);
	});
});

function myselcourse_list_onAddBtnClick(){
	var param = {};
	param.callback = function(isSuccess, data) {
		if (!isSuccess) {
			return;
		}

		var confirmWinParam = {};
		confirmWinParam.title = "请选择课程";
		confirmWinParam.confirmCallback = function(){
			var selectedCourseInfo = student_course_qry_sel_course_list_getSelectCourseItem();
			if(selectedCourseInfo == null){
				comm_ui_showPopoverMessage("student-course-qry-sel-course-list-table-head", '<span class="text-danger">请选择一条记录！</span>');
				return false;
			}
			
			myselcourse_list_addStudentCourse(selectedCourseInfo.courseId);
			return true;
		};
		comm_ui_showConfirm(data, confirmWinParam);
	}

	var url = $("#myselcourse-list-addBtn").data("target-url");
	comm_Ajax_post(url, param);
}

//添加选课
function myselcourse_list_addStudentCourse(courseId){
	var param = {};
	param.submitBtnId = "myselcourse-list-addBtn";
	param.reqData = {courseId : courseId};
	param.callback = function(isSuccess, data){
		if(data != "Success"){
			comm_ui_showMessage(data);
			return;
		}
		
		comm_ui_showMessage("添加成功");
		stdselcourse_manage_onQryCourseListFormSubmit();
	};
	
	var url = $("#myselcourse-list-addBtn").data("add-course-url");
	comm_Ajax_post(url, param);
}



function myselcourse_list_onTableRowClick(curRow){
	var checkBox = $(curRow).find(".myselcourse-list-table-col-selectCheckBox");
	checkBox.prop('checked', !checkBox.prop('checked'));
}

function myselcourse_list_onDelBtnClick(){
	var needRemoveItem = [];
	$(".myselcourse-list-table-col-selectCheckBox").each(function(){
	    if($(this).prop('checked')){
	    	needRemoveItem.push($(this).data("teacher-course-id"));
	    }
	});
	
	if(needRemoveItem.length < 1){
		comm_ui_showMessage("请选择");
		return;
	}
	
	var param = {};
	param.confirmCallback = function(){
		myselcourse_list_removeUser(needRemoveItem);
		return true;
	};
	
	comm_ui_showConfirm("删除数据后，将无法恢复，您确定删除？", param);
}


function myselcourse_list_removeUser(ids){
	var param = {};
	param.submitBtnId = "myselcourse-list-delBtn";
	param.reqData = {ids : ids};
	param.callback = function(isSuccess, data){
		if(data != "Success"){
			comm_ui_showMessage(data);
			return;
		}
		
		comm_ui_showMessage("删除成功");
		stdselcourse_manage_onQryCourseListFormSubmit();
	};
	
	
	var url = $("#myselcourse-list-delBtn").data("target-url");
	console.log(param);
	comm_Ajax_post(url, param);
}
