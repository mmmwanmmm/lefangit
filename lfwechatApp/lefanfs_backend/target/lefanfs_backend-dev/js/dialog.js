(function ($) {
	dialogBox= function (dialogId) {
		var dialog=null;
		var settings=null;
		var box=this;
        var config={
  		      keyboard: true,
		      show:false,
		      backdrop:false,
		      title:'新窗口',
		      content:'',
		      buttonName:'确认提交',
		      frame:false,
		      footer:false,
		      height:650,
		      width:650
	    };     //这个是私有变量，外部无法访问
		this.initComponent=function(options){
			 settings = $.extend(config,options);
			 var html='<div class="modal fade" id="dialogBoxID" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">'+
				'<div class="modal-dialog" id="modal-content">'+
			      '<div class="modal-content" >'+
			         '<div class="modal-header">'+
			            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="diglog_close_btn">×</button>'+
			            '<h4 class="modal-title" id="dialogBoxTitle">'+settings.title+'</h4>'+
			        '</div>'+
			         '<div id="dialogBox_data_content">'+
			         '</div>';
			 if(!settings.frame&&settings.footer){
				 html+= '<div class="modal-footer">'+
		          '<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js">关闭</button>'+
		          '<button type="button" autocomplete="off" data-loading-text="Loading..." class="btn btn-success loading-btn"><span class="glyphicon glyphicon-ok"></span>'+settings.buttonName+'</button>'+
		           '</div>';
			 }
			 html+='</div></div></div>';
			 $('#'+dialogId).html(html);
			 dialog=$('#dialogBoxID').modal(settings);
			 dialog.on('shown.bs.modal', function () {
				 
			 });
			 dialog.on('hide.bs.modal', function () {

			 });
			 dialog.on('show.bs.modal', function () {
				box.load(null);
			 });
			 dialog.on('hidden.bs.modal', function () {
				 $('#'+dialogId).html("");
			 });
		 };
		this.load=function(url){
			 if(url!=null&&url!=''){
				 settings.url=url; 
			 }
			 var modalContent=$("#modal-content");
			 modalContent.css({"height":settings.height+"px","width":settings.width+"px"});
			 	if(settings.frame){
					var s = '<iframe name="mainFrame" frameborder="0"  src="' + settings.url + '" id="iframe" style="width: '+(settings.width-20)+'px; height: '+settings.height+'px;"></iframe>';
					var content = $("#dialogBox_data_content");
					content.html(s);
				 }else{
					 if(settings.url!=null && settings.url!=''){
						 $.ajax({
							 type:settings.type,
							 url:settings.url,
							 dataType:'html',
							 success:function(event,param){
								 $("#dialogBox_data_content").html(param.data);
							 }
						 });
					 }else{
						 $("#dialogBox_data_content").html(settings.content);
					 }
				 }
		};
		this.reload=function(url){
			this.load(url);
		};
		this.setTitle=function(title){
			$("#dialogBoxTitle").html(title);
			settings.title=title;
		};
		this.setUrl=function(url){
			settings.url=url;
		};
		this.show=function(){
			dialog.modal('show');
		};
		this.hide=function(){
			dialog.modal('hide');
		};
    };
})(jQuery);