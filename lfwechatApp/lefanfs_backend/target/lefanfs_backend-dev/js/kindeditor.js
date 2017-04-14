(function ($) {
	KindEditor.lang({
	    cdnimage : '从CDN选择图片'
	});	
	EditorComponent= function (editorElement) {
		var editorObject=null;
		var config={
			items : [
			 		  'source','fullscreen','|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			 		  'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			 		  'insertunorderedlist','indent','outdent','quickformat','|', 'emoticons', 'link', 'cdnimage'],			
			imageTabIndex:0,
			allowImageUpload:false,
			allowFileUpload:false,
			uploadJson:"",
			extraFileUploadParams:{},
			cdnListUrl:""
		};
		this.initComponent=function(options){
			var settings = $.extend(config,options);
			settings.uploadJson=options.uploadUrl;
			settings.extraFileUploadParams=options.uploadParams;
			KindEditor.ready(function(K){
				editorObject=$.extend(editorObject,K.create(editorElement,settings));
			});
		};
		this.html=function(){
			return editorObject.html();
		};
		this.text=function(){
			return editorObject.text().replace(/<(img|embed).*?>/ig, '').replace(/&nbsp;/ig, ' ');
		};
		this.insertHtml=function(html){
			editorObject.insertHtml(html);
		};
	};
})(jQuery);