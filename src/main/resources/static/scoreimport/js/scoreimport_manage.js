$(function(){
	var moreConditionDiv = $("#scoreimport-manage-more-condition");
	moreConditionDiv.on('hide.bs.collapse', function () {
		$("#scoreimport-manage-more-condition-text").text('更多');
		$("#scoreimport-manage-more-condition-icon").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		
	});
	
	moreConditionDiv.on('show.bs.collapse', function () {
		$("#scoreimport-manage-more-condition-text").text('收起');
		$("#scoreimport-manage-more-condition-icon").removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
	});
	
	
	//设置年选择
	comm_ui_setYearPicker("#scoreimport-manage-courseYear");
	
	$("#scoreimport-manage-qry-form").on('submit', function(){
		scoreimport_manage_onQryCourseListFormSubmit(null);
		return false;
	});
	
	
	$("#scoreimport-manage-qry-list-btn").click();
});

function scoreimport_manage_onQryCourseListFormSubmit(pageNum){
	//pageNum等于是点击查询按钮出发
	$("#scoreimport-manage-qry-form-pageNum").val(pageNum);
	var param = {};
	param.submitBtnId = "scoreimport-manage-qry-list-btn";
	param.callback = function(isSuccess, data){
		if(!isSuccess){
			return;
		}
		
		$("#scoreimport-qry-list-result-div").html(data);
	}
	
	comm_Ajax_submitForm("scoreimport-manage-qry-form", param);	
}