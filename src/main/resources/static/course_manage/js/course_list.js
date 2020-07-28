$(function(){
	$("#course-list-delBtn").click(course_list_onDelBtnClick);
	$("#course-list-chgBtn").click(course_list_onChgBtnClick);
	$("#course-list-addBtn").click(course_list_onAddBtnClick);
	
	
	$(".course-list-table-col-selectCheckBox").click(function(even){
		//阻止事件冒泡
		event.stopPropagation();
	});
	
	$(".course-list-table-row").click(function(even){
		course_list_onTableRowClick(this);
	});
});

function course_list_onAddBtnClick(){
	var url = $("#course-list-addBtn").data("target-url");
	var param = {};
	param.submitBtnId = "course-list-addUserBtn";
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#course-manage-course-list-div"));
		$("#course-manage-second-page-div").html(data);
	};
	
	comm_Ajax_post(url, param);
}


function course_list_onChgBtnClick(){
	var targetItem = $(".course-list-table-col-selectCheckBox").filter(function(){
							return $(this).prop('checked');
						}); 
	
	if(targetItem.length < 1){
		comm_ui_showMessage("请选择");
		return;
	}
	
	if(targetItem.length > 1){
		comm_ui_showMessage("只能选择一个。");
		return;
	}
	
	var courseId = $(targetItem).data("course-id");
	
	var param = {};
	param.submitBtnId = "course-list-chgBtn";
	param.reqData = {courseId : courseId};
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#course-manage-course-list-div"));
		$("#course-manage-second-page-div").html(data);
	};
	
	
	var url = $("#course-list-chgBtn").data("target-url");
	comm_Ajax_post(url, param);
}


function course_list_onTableRowClick(curRow){
	var checkBox = $(curRow).find(".course-list-table-col-selectCheckBox");
	checkBox.prop('checked', !checkBox.prop('checked'));
}

function course_list_onDelBtnClick(){
	var needRemoveItem = [];
	$(".course-list-table-col-selectCheckBox").each(function(){
	    if($(this).prop('checked')){
	    	needRemoveItem.push($(this).data("course-id"));
	    }
	});
	
	if(needRemoveItem.length < 1){
		comm_ui_showMessage("请选择");
		return;
	}
	
	var param = {};
	param.confirmCallback = function(){
		course_list_removeUser(needRemoveItem);
		return true;
	};
	
	comm_ui_showConfirm("删除数据后，将无法恢复，您确定删除？", param);
}


function course_list_removeUser(ids){
	var param = {};
	param.submitBtnId = "course-list-delBtn";
	param.reqData = {ids : ids};
	param.callback = function(isSuccess, data){
		if(data != "Success"){
			comm_ui_showMessage(data);
			return;
		}
		
		comm_ui_showMessage("删除成功");
		course_manage_onQryCourseListFormSubmit();
	};
	
	
	var url = $("#course-list-delBtn").data("target-url");
	comm_Ajax_post(url, param);
}
