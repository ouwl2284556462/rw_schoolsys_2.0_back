$(function(){
  	$("#user-info-dtl-from").on('submit', user_info_onUserDtlFormSubmit);
  	
  	
  	$("#user-info-dtl-form-returnBtn").click(user_info_onReturnBtnClick);

	//检查帐号输入焦点消失, 检查该邮箱是否注册过
	$("#accountName").on("blur", user_info_onAccountNameBlur);	
	
	//检查密码输入焦点消失
	$("#passwordConfirm").on("blur", user_info_onPasswordConfirmBlur);
});

function user_info_onReturnBtnClick(){
	user_info_checkAndChgToManageUserList();
	return false;
}

function user_info_checkAndChgToManageUserList(){
	comm_ui_show($("#user-manage-user-list-div"));
	$("#user-manage-second-page-div").empty();
}

function user_info_onUserDtlFormSubmit(){
	var from = $("#user-info-dtl-form-flag").val();
	//个人资料菜单的修改
	if(from != 'adminChgInfo' && from != 'adminAddUser'){
		user_info_chgSelfUserInfo();
	}else{
		//用户管理菜单的修改和添加操作
		user_info_adminManageUserInfo(from);
	}
	
	return false;
}

function user_info_onPasswordConfirmBlur(){
	var from = $("#user-info-dtl-form-flag").val();
	if(from != 'adminAddUser'){
		return;
	}
	
	var password = $("#password").val();
	var passwordConfirm = $("#passwordConfirm").val();
	
	if(password != passwordConfirm){
		$("#passwordConfirm")[0].setCustomValidity("两次密码不相同。");
	}else{
		$("#passwordConfirm")[0].setCustomValidity("");
	}
}

function user_info_onAccountNameBlur(){
	var from = $("#user-info-dtl-form-flag").val();
	if(from != 'adminAddUser'){
		return;
	}
	
	var accountObj = $("#accountName");
	
	var param = {};
	param.reqData = {acctName: accountObj.val()};
	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Y" == data){
				accountObj[0].setCustomValidity("该账号名已经注册过。");
			}else{
				accountObj[0].setCustomValidity("");
			}
		}
	};
	
	comm_Ajax_post("/login/isAcctNameUsed.do", param);
}

//用户管理菜单的修改和添加操作
function user_info_adminManageUserInfo(from){
	var param = {};
	param.submitBtnId = "user-info-dtl-form-submitBtn";
	var adminUrl = $("#user-info-dtl-form-submitBtn").data("admin-link");
	param.url = adminUrl;
	

	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Success" == data){
				comm_ui_showMessage("操作成功");
				user_info_checkAndChgToManageUserList();
				//刷新列表
				user_manage_onQryUserListFormSubmit();
			}else{
				comm_ui_showMessage(data);
			}
		}
	}

	comm_Ajax_submitForm("user-info-dtl-from", param);
}

//个人资料菜单的修改
function user_info_chgSelfUserInfo(){
	var param = {};
	param.submitBtnId = "user-info-dtl-form-submitBtn";
	param.callback = function(isSuccess, data){
		if(isSuccess){
			if("Success" == data){
				comm_ui_showMessage("修改成功");
				user_info_checkAndChgToManageUserList();
				
			}else{
				comm_ui_showMessage(data);
			}
		}
	}

	comm_Ajax_submitForm("user-info-dtl-from", param);
}
