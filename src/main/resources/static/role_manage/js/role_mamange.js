$(function(){
	$("#role-manage-qry-form").on('submit', function(){
		role_manage_onQryRoleListFormSubmit(null);
		return false;
	});
	
	
	$("#role-manage-qry-list-btn").click();
});

function role_manage_onQryRoleListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#role-manage-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "role-manage-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#role-manage-role-list-root-div").html(data);
	}
	
	comm_Ajax_submitForm("role-manage-qry-form", param);	
}