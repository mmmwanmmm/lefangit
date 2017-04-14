/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

KindEditor.plugin('cdnimage', function(K) {
	var editor = this, 
	name = 'cdnimage',
	cdnListUrl= K.undef(editor.cdnListUrl, '');
	editor.clickToolbar(name, function(){
		openCommonDialog("选择图片",cdnListUrl);
	});
});
