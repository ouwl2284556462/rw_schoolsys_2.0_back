$(function(){
	$("#myselcourse-list-delBtn").click(myselcourse_list_onDelBtnClick);
	$("#myselcourse-list-chgBtn").click(myselcourse_list_onChgBtnClick);
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
	var url = $("#myselcourse-list-addBtn").data("target-url");
	var param = {};
	param.submitBtnId = "myselcourse-list-addUserBtn";
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#teachselcourse-qry-list-div"));
		$("#teachselcourse-manage-second-page-div").html(data);
	};
	
	comm_Ajax_post(url, param);
}


function myselcourse_list_onChgBtnClick(){
	var targetItem = $(".myselcourse-list-table-col-selectCheckBox").filter(function(){
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
	
	var myselcourseId = $(targetItem).data("myselcourse-id");
	
	var param = {};
	param.submitBtnId = "myselcourse-list-chgBtn";
	param.reqData = {myselcourseId : myselcourseId};
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#teachselcourse-qry-list-div"));
		$("#teachselcourse-manage-second-page-div").html(data);
	};
	
	var url = $("#myselcourse-list-chgBtn").data("target-url");
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
	    	needRemoveItem.push($(this).data("myselcourse-id"));
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
		teachselcourse_manage_onQryCourseListFormSubmit();
	};
	
	
	var url = $("#myselcourse-list-delBtn").data("target-url");
	comm_Ajax_post(url, param);
}
