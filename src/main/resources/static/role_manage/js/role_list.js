$(function(){
	$("#role-list-delBtn").click(role_list_onDelBtnClick);
	$("#role-list-chgBtn").click(role_list_onChgBtnClick);
	$("#role-list-addBtn").click(role_list_onAddBtnClick);
	
	
	$(".role-list-table-col-selectCheckBox").click(function(even){
		//阻止事件冒泡
		event.stopPropagation();
	});
	
	$(".role-list-table-row").click(function(even){
		role_list_onTableRowClick(this);
	});
});

function role_list_onAddBtnClick(){
	var url = $("#role-list-addBtn").data("target-url");
	var param = {};
	param.submitBtnId = "role-list-addUserBtn";
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#role-manage-role-list-div"));
		$("#role-manage-second-page-div").html(data);
	};
	
	comm_Ajax_post(url, param);
}


function role_list_onChgBtnClick(){
	var targetItem = $(".role-list-table-col-selectCheckBox").filter(function(){
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
	
	var roleId = $(targetItem).data("role-id");
	
	var param = {};
	param.submitBtnId = "role-list-chgBtn";
	param.reqData = {roleId : roleId};
	param.callback = function(isSuccess, data){
		comm_ui_hide($("#role-manage-role-list-div"));
		$("#role-manage-second-page-div").html(data);
	};
	
	
	var url = $("#role-list-chgBtn").data("target-url");
	comm_Ajax_post(url, param);
}


function role_list_onTableRowClick(curRow){
	var checkBox = $(curRow).find(".role-list-table-col-selectCheckBox");
	checkBox.prop('checked', !checkBox.prop('checked'));
}

function role_list_onDelBtnClick(){
	var needRemoveItem = [];
	$(".role-list-table-col-selectCheckBox").each(function(){
	    if($(this).prop('checked')){
	    	needRemoveItem.push($(this).data("role-id"));
	    }
	});
	
	if(needRemoveItem.length < 1){
		comm_ui_showMessage("请选择");
		return;
	}
	
	var param = {};
	param.confirmCallback = function(){
		role_list_removeUser(needRemoveItem);
		return true;
	};
	
	comm_ui_showConfirm("删除数据后，将无法恢复，您确定删除？", param);
}


function role_list_removeUser(ids){
	var param = {};
	param.submitBtnId = "role-list-delBtn";
	param.reqData = {ids : ids};
	param.callback = function(isSuccess, data){
		if(data != "Success"){
			comm_ui_showMessage(data);
			return;
		}
		
		comm_ui_showMessage("删除成功");
		role_manage_onQryRoleListFormSubmit();
	};
	
	
	var url = $("#role-list-delBtn").data("target-url");
	comm_Ajax_post(url, param);
}
