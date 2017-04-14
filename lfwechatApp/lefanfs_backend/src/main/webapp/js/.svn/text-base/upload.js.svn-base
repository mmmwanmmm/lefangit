(function ($) {
	UploadComponent= function (uploadFileId) {
		var uploadObject=null;
        var config={
	    	height        : 27, 
	    	width         : 80,  
	    	buttonText    : '浏 览',
	        swf           : '/js/uploadify/uploadify.swf',
	        uploader      : '',
	        auto          : false,
	        fileTypeExts  : '*.*',
	        buttonCursor: 'pointer',
	        cancelImage: '/js/uploadify/uploadify-cancel.png',
	        debug: false,
	        fileObjName:'file',
	        fileSizeLimit : 0,
	        fileTypeDesc: '图片',
	        fileTypeExts: '*.*',
	        method: 'post',
	        multi:true,
	        //queueID: 'fileQueue',//显示上传文件队列的元素id，可以简单用一个div来显示
	        queueSizeLimit : 999,//队列中允许的最大文件数目
	        progressData : 'all', // 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
	        removeCompleted : true,//上传成功后的文件，是否在队列中自动删除
	        requeueErrors : true,
	        formData: {},//和后台交互时，附加的参数
	        successTimeout : 30,//上传时的
	        timeoutuploadLimit:999,//能同时上传的文件数目
	        onDialogClose : function(swfuploadifyQueue){
	        	//当文件选择对话框关闭时触发
	        },
	        onDialogOpen : function(){
	        	//当选择文件对话框打开时触发
	        },
	        onSelect : function(file){
	        	//当每个文件添加至队列后触发

	        },
	        onSelectError : function(file,errorCode,errorMsg){
	        	//当文件选定发生错误时触发
	        },
	        onQueueComplete : function(stats){
	        	//当队列中的所有文件全部完成上传时触发

	        },
	        onUploadComplete : function(file,swfuploadifyQueue){
	        	//队列中的每个文件上传完成时触发一次
	        },
	        onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue){
	        	//上传文件出错是触发（每个出错文件触发一次）
	        },
	        onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize){
	        	//上传进度发生变更时触发
	        },
	        onUploadStart: function(file){
	        	//上传开始时触发（每个文件触发一次）
	        },
	        onUploadSuccess:function(file,data,response){
	        	//上传完成时触发（每个文件触发一次）
                //alert(data);
            }
	    };     //这个是私有变量，外部无法访问
		this.initComponent=function(options){
			 var settings = $.extend(config,options);
			 settings.uploader=options.url;
			 settings.swf=options.contextPath+settings.swf;
			 settings.cancelImage=options.contextPath+settings.cancelImage;
			 uploadObject=$("#"+uploadFileId);
			 uploadObject.uploadify(settings);
		 };
		this.onUplaod=function(){
			uploadObject.uploadify('upload','*');
		};
		this.onCancel=function(){
			uploadObject.uploadify('cancel','*');
		};
    };
})(jQuery);