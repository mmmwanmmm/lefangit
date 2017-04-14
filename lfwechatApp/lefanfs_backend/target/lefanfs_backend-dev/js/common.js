+function ($) {
    'use strict';
//    $(document).ajaxStart(function(){
//        alert("ajaxStart");
//    });
//    $(document).ajaxComplete(function(){
//        alert("ajaxComplete");
//    });
//    $(document).ajaxError(function(){
//        alert("ajaxError");
//    });
//    $(document).ajaxSend(function(){
//        alert("ajaxSend");
//    });
//    $(document).ajaxStop(function(){
//        alert("ajaxStop");
//    });
//    $(document).ajaxSuccess(function(){
//        alert("ajaxSuccess");
//    });
    var _ajax = $.ajax;

    function ajax() {
        var opt = arguments[0];
        this._setting = "[object Object]" === Object.prototype.toString.call(opt[0]) ? opt[0] : opt[1];
        var _beforeSend = undefined == this._setting["beforeSend"] ? function () {
        } : this._setting["beforeSend"];
        var _error = undefined == this._setting["error"] ? function () {
        } : this._setting["error"];
        var _dataFilter = undefined == this._setting["dataFilter"] ? function () {
        } : this._setting["dataFilter"];
        var _success = undefined == this._setting["success"] ? function () {
        } : this._setting["success"];
        var _complete = undefined == this._setting["complete"] ? function () {
        } : this._setting["complete"];

        $(document).unbind("private-beforeSend");
        $(document).bind("private-beforeSend", _beforeSend);
        $(document).unbind("private-error");
        $(document).bind("private-error", _error);
        $(document).unbind("private-dataFilter");
        $(document).bind("private-dataFilter", _dataFilter);
        $(document).unbind("private-success");
        $(document).bind("private-success", _success);
        $(document).unbind("private-complete");
        $(document).bind("private-complete", _complete);

        this._setting["beforeSend"] = this.beforeSend;
        this._setting["error"] = this.error;
        this._setting["dataFilter"] = this.dataFilter;
        this._setting["success"] = this.success;
        this._setting["complete"] = this.complete;

        var _options;
        if ("[object String]" === Object.prototype.toString.call(opt[0])) {
            _options = $.extend(true,{},$.ajax.privateDefault,opt[1]);
            return _ajax(opt[0], _options);
        } else {
            _options = $.extend(true,{},$.ajax.privateDefault,opt[0]);
            return _ajax(_options);
        }
    }

    /**
     * 在自定义 beforeSend 回调方法之前调用.
     * @param XMLHttpRequest
     * @param setting
     */
    ajax.prototype.beforeSend = function (XMLHttpRequest, setting) {
        /*根据需要在此加入自己的处理逻辑。*/
        $(document).trigger("private-beforeSend", {"XMLHttpRequest": XMLHttpRequest, "setting": setting});
        return this;
    };
    /**
     * 在自定义 error 回调方法之前调用.
     * @param XMLHttpRequest
     * @param textStatus
     * @param errorThrown
     */
    ajax.prototype.error = function (XMLHttpRequest, textStatus, errorThrown) {
        /*根据需要在此加入自己的处理逻辑。*/
        $(document).trigger("private-error", {"XMLHttpRequest": XMLHttpRequest, "textStatus": textStatus, "errorThrown": errorThrown});
        return this;
    };
    /**
     * 在自定义 dataFilter 回调方法之前调用.
     * @param data
     * @param type
     */
    ajax.prototype.dataFilter = function (data, type) {
        /*根据需要在此加入自己的处理逻辑。*/
        $(document).trigger("private-dataFilter", {"data": data, "type": type});
        return data;
    };
    /**
     * 在自定义 success 回调方法之前调用.
     * @param data
     * @param textStatus
     * @param XMLHttpRequest
     */
    ajax.prototype.success = function (data, textStatus, XMLHttpRequest) {
        /*根据需要在此加入自己的处理逻辑。*/
        if(navigator.userAgent.indexOf("MSIE 9.0") < 0 && navigator.userAgent.indexOf("MSIE 8.0") < 0){
            data = XMLHttpRequest.responseText;
            if("json"==this.dataType){
                try {
                    data = JSON.parse(data);
                } catch (e) {
                    //console.info(e.name + e.message);
                }
                if(data && data.needLogin){
                	if(data.loginUrl!=null && data.loginUrl!=''){
                	  	window.open(data.loginUrl,'_top');
                	}
                	return;
                }
                if(data && data.noAuthority){
                	if(data.authorityUrl!=null && data.authorityUrl!=''){
                		alert("抱歉，当前您没有访问权限，请联系管理员！");
                	}
                	return;
                }
            }
        }
        $(document).trigger("private-success", {"XMLHttpRequest": XMLHttpRequest,"data": data, "textStatus": textStatus});
        return this;
    };
    /**
     * 在自定义 complete 回调方法之前调用.
     * @param XMLHttpRequest
     * @param textStatus
     */
    ajax.prototype.complete = function (XMLHttpRequest, textStatus) {
        /*根据需要在此加入自己的处理逻辑。*/
        $(document).trigger("private-complete", {"XMLHttpRequest": XMLHttpRequest, "textStatus": textStatus});
        return this;
    };

    $.ajax = function () {
        return new ajax(arguments);
    };
    $.ajax.privateDefault = {
        "dataType":"json",
        "type":"post"
    };
    $.ajax.noConflict = function () {
        $.ajax = _ajax;
        return this;
    };
}(jQuery);


// 常用封装
+function ($) {
    'use strict';
	/**
	 * 功能:默认序列化form表单，进行ajax提交,取得返回数据,调用设置的回调函数
	 */
	$.fn.ajaxForm=function(options){
		var url=$(this).attr("action");
		var data=$(this).serialize();
		var defaults = {
			url: url,
			data: data,
			type: "post",
			dataType: "json"
		};
		var config = $.extend(defaults, options);
		$.ajax(config);
	};
	
	$.fn.loadingMore=function(options){
		var defaults = {
			loadingDivId: "loadingDiv",
			dataDivId: null,
			successCallback:null
		};
		var config = $.extend(defaults, options);
		var moreDiv=$(this);
		var loadingDiv=$("#"+config.loadingDivId);
		var dataDiv=$("#"+config.dataDivId);
		moreDiv.click(function(){
			loadingDiv.attr("style","display:");
			moreDiv.attr("style","display:none");
			$.ajax({
				url : dataDiv.attr("nextUrl"),
				type: 'post',
				dataType : 'html',
				success : function(event,param) {
					var fContent = $("<div>"+param.data+"</div>").find("#"+config.dataDivId);
					dataDiv.append(fContent.html());
					var nextUrl = fContent.attr("nextUrl");
					dataDiv.attr("nextUrl",nextUrl);
					if(nextUrl != ""){
						loadingDiv.attr("style","display:none");
						moreDiv.attr("style","display:");
					}else{
						loadingDiv.attr("style","display:none");
						moreDiv.attr("style","display:none");
					}
					if(config.successCallback!=undefined && config.successCallback!=null && typeof(config.successCallback)=="function"){
						config.successCallback.call(this, event, param);
					}
				},
				error:function(){
					loadingDiv.attr("style","display:none");
					moreDiv.attr("style","display:");				
				}
			});
		});
	};
}(jQuery);

/**
 * 全选/反选
 */
function bindCheckAll(){
	var check_btn = document.getElementById("check-btn");
	var check_name = document.getElementsByName("list-checkbox");
	check_btn.onclick = function(){
		for(var i=1; i<=check_name.length; i+=1){
			if(check_name[i-1].checked){
				check_name[i-1].checked = false;
			}else{
				check_name[i-1].checked = true;
			}
		}
	};
}

/**
 * 分页
 */
function gotoPage(url,divId){
	if(divId!=null && divId!=""){
		$.ajax({
			url:url, 
			dataType:"html", 
			success:function(event,param){
				$("#"+divId).html(param.data);
			}				
		});
	}else{
		window.location.href=url;
	}
}

/**
 * 打开dialog
 * @param options
 */
function openDialog(options){
	var dialog =new dialogBox("dialogId");
	dialog.initComponent(options);
	dialog.show();
}

/**
 * 打开dialog
 * @param title
 * @param url
 */
function openCommonDialog(title,url){
	openDialog({
		frame:true,
		title:title,
	    height:650,
	    width:900,
		url:url
	});
}

function alertDialog(title,content){
	openDialog({
		frame:false,
		title:title,
		content:content,
	    height:500,
	    width:500
	});	
}

/**
 * 关闭dialog
 */
function closeDialog(){
	var closeBtn = $("#diglog_close_btn");
	if(closeBtn.size() == 0){
		closeBtn = $("#diglog_close_btn",window.parent.document);
	}
	closeBtn.click();
}

/**
 * 刷新父窗体
 */
function reloadParent(){
	parent.location.reload();
}

/**
 * 刷新当前窗体
 */
function reload(){
	window.location.reload();
}

/**
 * 处理API接口直接返回的JSON
 * @param data
 * @returns
 */
function getApiJson(data){
	if(data==null || !data){
		return null;
	}
	/*for(var index in data){
		var apiRsp=data[index];
		return apiRsp;
	}*/
	return data;
}

/**
 * ajax form提交
 * @param formObj form对象
 * @param successCallback 成功回调
 * @param tipTitle 成功提示
 * @param confirmTitle 确认提示
 * @param failCallback 失败回调
 */
function ajaxFormSubmit(formObj,successCallback,tipTitle,confirmTitle,failCallback){
	if(confirmTitle && !confirm(confirmTitle)){
		return;
	}
	$(formObj).ajaxForm({
		success:function(event,param){
			var apiRsp=getApiJson(param.data);
			if(apiRsp && apiRsp.isSuccess){
				if(tipTitle){
					alert(tipTitle);
				}
				if(successCallback){
					successCallback.call(this, event, param);
				}
			}else{
				if(failCallback){
					failCallback.call(this, event, param);
				}else{
					alert(apiRsp.msg);
				}
			}
		}
	});
}

/**
 * ajax提交
 * @param url URL
 * @param data 数据
 * @param successCallback 成功回调
 * @param tipTitle 成功提示
 * @param confirmTitle 确认提示
 * @param failCallback 失败回调
 */
function ajaxSubmit(url,data,successCallback,tipTitle,confirmTitle,failCallback){
	if(confirmTitle && !confirm(confirmTitle)){
		return;
	}
	$.ajax({
		url:url,
		data:data,
		success:function(event,param){
			var apiRsp=getApiJson(param.data);
			if(apiRsp && apiRsp.isSuccess){
				if(tipTitle){
					alert(tipTitle);
				}
				if(successCallback){
					successCallback.call(this, event, param);
				}
			}else{
				if(failCallback){
					failCallback.call(this, event, param);
				}else{
					alert(apiRsp.msg);
				}
			}			
		}
	});
}

/**
 * selectize控件：从后台加载JSON
 * @param url URL
 * @param callback selectize控件回调
 * @param dataFromApi 数据来源于API
 */
function loadForSelectize(url,callback,dataFromApi){
	$.ajax({
		url: url,
		dataType: 'json',
		type: 'GET',
		error: function() {
			callback.call(this);
		},
		success: function(event, param) {
			var data=param.data;
			if(data && dataFromApi){
				var apiRsp=getApiJson(data);
				if(apiRsp && apiRsp.results){
					data=apiRsp.results;
				}else{
					data=null;
				}
			}
			if(data){
				callback.call(this, data);
			}else{
				callback.call(this);
			}
		}
	});
}

/**
 * 数字格式化
 * */
function toDecimal(x,i) {   
    var f = parseFloat(x);              
	if (isNaN(f)) {   
	        return;              
	}              
	f = Math.round(x*10*i)/10*i;
	
	var s = f.toString();              

	var rs = s.indexOf('.');              

	if (rs < 0) {   
		rs = s.length;                  
		s += '.';   
	}              
	while (s.length <= rs + i) {
	   s += '0';            
	}              

	return s;   
}       

