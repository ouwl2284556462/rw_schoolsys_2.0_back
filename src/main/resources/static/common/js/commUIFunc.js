/**
 * 弹出提示
 * @param msg
 * @param param
 * @returns
 */
function comm_ui_showMessage(msg, param){
	if(param == null){
		param = {}
	}
	
	var title = param.title;
	if(title == null){
		title = "通知";
	}
	
	
	var noticeWin = $('#comm-notice-window');
	
	noticeWin.unbind('hide.bs.modal').on('hide.bs.modal', function () {
		if(param.closeCallback != null){
			param.closeCallback();
		}
	});
	
	
	$("#comm-notice-window-title").text(title);
	$("#comm-notice-window-content").html(msg);
	noticeWin.modal();
}

//冒泡弹出框
function comm_ui_showPopoverMessage(targetId, msg){
	$('#' + targetId).popover({
		content:msg,
		placement: 'top',
		trigger:'manual',
		html:true
	});
	$('#' + targetId).popover('show');
}

function comm_ui_hidePopoverMessage(targetId){
	$('#' + targetId).popover('hide');
}



function comm_ui_showConfirm(msg, param){
	if(param == null){
		param = {}
	}
	
	var title = param.title;
	if(title == null){
		title = "请确认";
	}
	
	
	var confirmWin = $('#comm-confirm-window');
	confirmWin.unbind('hide.bs.modal').on('hide.bs.modal', function () {
		if(param.closeCallback != null){
			param.closeCallback();
		}
	});
	
	$("#comm-confirm-window-okBtn").unbind("click").click(function(){
		var canClose = true;
		if(param.confirmCallback != null){
			canClose = param.confirmCallback();
		}
		
		if(canClose){
			confirmWin.modal('hide')
		}
	});
	
	
	$("#comm-confirm-window-title").text(title);
	$("#comm-confirm-window-content").html(msg);
	confirmWin.modal();
}

/**
 * 动态加载css
 * @param href
 * @returns
 */
function comm_ui_loadCSS(href){
	if($("link[href='" + href + "']").length > 0){
		return;
	}
	
	
	var linkObj = $("<link>");
	linkObj.attr("rel", "stylesheet");
	linkObj.attr("type", "text/css");
	linkObj.attr("href", href);
	linkObj.appendTo("head"); 
}

function comm_ui_show(obj){
	obj.removeClass("hidden");
}

function comm_ui_hide(obj){
	obj.addClass("hidden");
}

function comm_ui_setYearPicker(selectorStr){
	$(selectorStr).datepicker({
                language: 'zh-CN', //语言
                autoclose: true, //选择后自动关闭
                clearBtn: true,//清除按钮
				startView: 2,
				minViewMode: 2,
			    maxViewMode: 2,
                format: "yyyy"//日期格式
            });
}



