$(function(){
	$(".teacher-course-qry-sel-course-list-table-col-selectRadio").click(function(even){
		//阻止事件冒泡
		event.stopPropagation();
	});
	
	$(".teacher-course-qry-sel-course-list-table-row").click(function(even){
		teacher_course_qry_sel_course_list_onTableRowClick(this);
	});
});

function teacher_course_qry_sel_course_list_onTableRowClick(curRow){
	var radio = $(curRow).find(".teacher-course-qry-sel-course-list-table-col-selectRadio");
	radio.prop('checked', true);
}


function teacher_course_qry_sel_course_list_getSelectCourseItem(){
	var radioList = $('.teacher-course-qry-sel-course-list-table-col-selectRadio');
	var selectedRadio = null;
	for(var i = 0; i < radioList.length; ++i){
		if(radioList.eq(i).prop('checked')){
			selectedRadio = radioList.eq(i);
		}
	}
	
	if(selectedRadio == null){
		return null;
	}
	
	var result = {};
	result.courseId = selectedRadio.data("course-id");
	result.courseName = selectedRadio.data("course-name");
	return result;
}

function teacher_course_qry_sel_course_list_showErrMsg(msg){
	comm_ui_show($("#teacher-course-qry-sel-course-list-err-msg"));
}

