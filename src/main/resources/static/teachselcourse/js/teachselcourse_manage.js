$(function(){
	//设置年选择
	comm_ui_setYearPicker("#teachselcourse-manage-courseYear");
	
	$("#teachselcourse-manage-qry-form").on('submit', function(){
		teachselcourse_manage_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#teachselcourse-manage-qry-list-btn").click();
});

function teachselcourse_manage_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#teachselcourse-manage-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "teachselcourse-manage-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#teachselcourse-qry-list-result-div").html(data);
	}
	
	comm_Ajax_submitForm("teachselcourse-manage-qry-form", param);	
}