$(function(){
	$("#myselcourse-list-scoreUpdateBtn").click(myselcourse_list_onScoreUpdateBtnClick);
	
	
	$(".myselcourse-list-table-col-selectRadio").click(function(even){
		//阻止事件冒泡
		event.stopPropagation();
	});
	
	$(".myselcourse-list-table-row").click(function(even){
		myselcourse_list_onTableRowClick(this);
	});
});

function myselcourse_list_onTableRowClick(curRow){
	var checkBox = $(curRow).find(".myselcourse-list-table-col-selectRadio");
	checkBox.prop('checked', !checkBox.prop('checked'));
}

//更新分数
function myselcourse_list_onScoreUpdateBtnClick(){
	var radioList = $('.myselcourse-list-table-col-selectRadio');
	var selectedRadio = null;
	for(var i = 0; i < radioList.length; ++i){
		if(radioList.eq(i).prop('checked')){
			selectedRadio = radioList.eq(i);
		}
	}
	
	if(selectedRadio == null){
		comm_ui_showMessage("请选择");
		return;
	}
	
	var teacherCourseId = selectedRadio.data("teacher-course-id");
	var studentAccountName = selectedRadio.data("student-account-name");
	var studentName = selectedRadio.data("student-name");
	var courseName = selectedRadio.data("course-name");
	var score = selectedRadio.data("score");
	
	
	var param = {};
	param.reqData = {
		studentName :studentName,
		courseName : courseName,
		score : score
	};
	
	param.callback = function(isSuccess, data) {
		if (!isSuccess) {
			return;
		}

		var confirmWinParam = {};
		confirmWinParam.title = "请填写分数";
		confirmWinParam.confirmCallback = function(){
			var score = $("#update-score-page-score").val();
			if(score == null || score ==""){
				comm_ui_showPopoverMessage("update-score-page-score", '<span class="text-danger">请填写！</span>');
				return false;
			}
			
			myselcourse_list_dealScoreUpdate(score, teacherCourseId, studentAccountName);
			return true;
		};
		comm_ui_showConfirm(data, confirmWinParam);
	}

	var url = $("#myselcourse-list-scoreUpdateBtn").data("target-url");
	comm_Ajax_post(url, param);
}

//更新分数
function myselcourse_list_dealScoreUpdate(score, teacherCourseId, studentAccountName){
	var param = {};
	param.reqData = {
		accountName :studentAccountName,
		teacherCourseId : teacherCourseId,
		score : score
	};
	param.submitBtnId = "myselcourse-list-scoreUpdateBtn";
	
	param.callback = function(isSuccess, data) {
		if (!isSuccess) {
			return;
		}

		if(data != "Success"){
			comm_ui_showMessage(data);
			return;
		}
		
		comm_ui_showMessage("操作成功");
		scoreimport_manage_onQryCourseListFormSubmit();
	}

	var url = $("#myselcourse-list-scoreUpdateBtn").data("updata-score-url");
	comm_Ajax_post(url, param);
}
