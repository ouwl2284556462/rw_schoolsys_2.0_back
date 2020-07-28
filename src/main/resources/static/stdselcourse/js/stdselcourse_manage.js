$(function(){
	var moreConditionDiv = $("#stdselcourse-manage-more-condition");
	moreConditionDiv.on('hide.bs.collapse', function () {
		$("#stdselcourse-manage-more-condition-text").text('更多');
		$("#stdselcourse-manage-more-condition-icon").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		
	});
	
	moreConditionDiv.on('show.bs.collapse', function () {
		$("#stdselcourse-manage-more-condition-text").text('收起');
		$("#stdselcourse-manage-more-condition-icon").removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
	});
	
	
	//设置年选择
	comm_ui_setYearPicker("#stdselcourse-manage-courseYear");
	
	$("#stdselcourse-manage-qry-form").on('submit', function(){
		stdselcourse_manage_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#stdselcourse-manage-qry-list-btn").click();
});

function stdselcourse_manage_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#stdselcourse-manage-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "stdselcourse-manage-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#stdselcourse-qry-list-result-div").html(data);
	}
	
	comm_Ajax_submitForm("stdselcourse-manage-qry-form", param);	
}