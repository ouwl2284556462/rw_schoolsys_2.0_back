$(function(){
  	$("#role-info-dtl-from").on('submit', role_info_onRoleDtlFormSubmit);
  	
  	
  	$("#role-info-dtl-form-returnBtn").click(role_info_onReturnBtnClick);
});

function role_info_onReturnBtnClick(){
	role_info_checkAndChgToManageRoleList();
	return false;
}

function role_info_checkAndChgToManageRoleList(){
	comm_ui_show($("#role-manage-role-list-div"));
	$("#role-manage-second-page-div").empty();
}

function role_info_onRoleDtlFormSubmit(){
	var param = {};
	param.submitBtnId = "role-info-dtl-form-submitBtn";

	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Success" == data){
				comm_ui_showMessage("操作成功");
				role_info_checkAndChgToManageRoleList();
				//刷新列表
				role_manage_onQryRoleListFormSubmit();
			}else{
				comm_ui_showMessage(data);
			}
		}
	}

	comm_Ajax_submitForm("role-info-dtl-from", param);
	return false;
}

