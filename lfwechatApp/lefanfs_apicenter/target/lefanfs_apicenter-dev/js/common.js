+function(c) {
	var a = c.ajax;
	function b() {
		var h = arguments[0];
		this._setting = "[object Object]" === Object.prototype.toString(h[0]) ? h[0]
				: h[1];
		var e = undefined == this._setting.beforeSend ? function() {
		} : this._setting.beforeSend;
		var j = undefined == this._setting.error ? function() {
		} : this._setting.error;
		var g = undefined == this._setting.dataFilter ? function() {
		} : this._setting.dataFilter;
		var i = undefined == this._setting.success ? function() {
		} : this._setting.success;
		var f = undefined == this._setting.complete ? function() {
		} : this._setting.complete;
		c(document).unbind("private-beforeSend");
		c(document).bind("private-beforeSend", e);
		c(document).unbind("private-error");
		c(document).bind("private-error", j);
		c(document).unbind("private-dataFilter");
		c(document).bind("private-dataFilter", g);
		c(document).unbind("private-success");
		c(document).bind("private-success", i);
		c(document).unbind("private-complete");
		c(document).bind("private-complete", f);
		this._setting.beforeSend = this.beforeSend;
		this._setting.error = this.error;
		this._setting.dataFilter = this.dataFilter;
		this._setting.success = this.success;
		this._setting.complete = this.complete;
		var d;
		if ("[object String]" === Object.prototype.toString(h[0])) {
			d = c.extend(true, {}, c.ajax.privateDefault, h[1]);
			a(h[0], d)
		} else {
			d = c.extend(true, {}, c.ajax.privateDefault, h[0]);
			a(d)
		}
	}
	b.prototype.beforeSend = function(e, d) {
		c(document).trigger("private-beforeSend", {
			XMLHttpRequest : e,
			setting : d
		})
	};
	b.prototype.error = function(d, f, e) {
		c(document).trigger("private-error", {
			XMLHttpRequest : d,
			textStatus : f,
			errorThrown : e
		})
	};
	b.prototype.dataFilter = function(e, d) {
		c(document).trigger("private-dataFilter", {
			data : e,
			type : d
		});
		return e
	};
	b.prototype.success = function(f, h, d) {
		f = d.responseText;
		if ("json" == this.dataType) {
			try {
				f = JSON.parse(f)
			} catch (g) {
			}
		}
		if (f && f.needLogin) {
			window.location.href = f.loginUrl;
			return
		}
		if (f && f.needRedirect) {
			window.location.href = f.redirectUrl;
			return
		}
		c(document).trigger("private-success", {
			data : f,
			textStatus : h,
			XMLHttpRequest : d
		})
	};
	b.prototype.complete = function(d, e) {
		c(document).trigger("private-complete", {
			XMLHttpRequest : d,
			textStatus : e
		})
	};
	c.ajax = function() {
		return new b(arguments)
	};
	c.ajax.privateDefault = {
		dataType : "json",
		type : "post"
	};
	c.ajax.noConflict = function() {
		c.ajax = a;
		return this
	}
}(jQuery);
+function(a) {
	a.fn.ajaxForm = function(d) {
		var c = a(this).attr("action");
		var e = a(this).serialize();
		var f = {
			url : c,
			data : e,
			type : "post",
			dataType : "json"
		};
		var b = a.extend(f, d);
		a.ajax(b)
	};
	a.fn.selectAll = function() {
		a(this).click(function() {
			var c = a(this);
			var b = c.attr("name");
			if (c.is(":checked")) {
				a("input[name='" + b + "']").attr("checked", true)
			} else {
				a("input[name='" + b + "']").removeAttr("checked")
			}
			a("input[name='" + b + "']").click(function() {
				var d = a(this);
				if (!d.is(":checked")) {
					c.removeAttr("checked")
				}
			})
		})
	};
	a.fn.maxlength = function() {
		a("textarea[maxlength]").keypress(function(e) {
			var c = e.which;
			if (c >= 33 || c == 13) {
				var b = a(this).attr("maxlength");
				var d = this.value.length;
				if (d >= b) {
					e.preventDefault()
				}
			}
		})
	};
	a.fn.loadingMore = function(d) {
		var g = {
			loadingDivId : "loadingDiv",
			dataDivId : null
		};
		var c = a.extend(g, d);
		var b = a(this);
		var f = a("#" + c.loadingDivId);
		var e = a("#" + c.dataDivId);
		b.click(function() {
			f.attr("style", "display:");
			b.attr("style", "display:none");
			a.ajax({
				url : e.attr("nextUrl"),
				type : "post",
				dataType : "html",
				success : function(h, j) {
					var i = a("<div>" + j.data + "</div>").find(
							"#" + c.dataDivId);
					e.append(i.html());
					var k = i.attr("nextUrl");
					e.attr("nextUrl", k);
					if (k != "") {
						f.attr("style", "display:none");
						b.attr("style", "display:");
					} else {
						f.attr("style", "display:none");
						b.attr("style", "display:none");
					}
					$('.thumbnail a').touchTouch();
					a("#" + c.dataDivId + " img.lazy").scrollLoading({
						attr : "data-original"
					});
				},
				error : function() {
					f.attr("style", "display:none");
					b.attr("style", "display:");
				}
			});
		})
	}
}(jQuery);

//常用封装
+function ($) {
    'use strict';
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
}(jQuery);

(function($) {  
    $.fn  
            .extend({  
                insertContent : function(myValue, t) {  
                    var $t = $(this)[0];  
                    if (document.selection) { // ie  
                        this.focus();  
                        var sel = document.selection.createRange();  
                        sel.text = myValue;  
                        this.focus();  
                        sel.moveStart('character', -l);  
                        var wee = sel.text.length;  
                        if (arguments.length == 2) {  
                            var l = $t.value.length;  
                            sel.moveEnd("character", wee + t);  
                            t <= 0 ? sel.moveStart("character", wee - 2 * t  
                                    - myValue.length) : sel.moveStart(  
                                    "character", wee - t - myValue.length);  
                            sel.select();  
                        }  
                    } else if ($t.selectionStart  
                            || $t.selectionStart == '0') {  
                        var startPos = $t.selectionStart;  
                        var endPos = $t.selectionEnd;  
                        var scrollTop = $t.scrollTop;  
                        $t.value = $t.value.substring(0, startPos)  
                                + myValue  
                                + $t.value.substring(endPos,  
                                        $t.value.length);  
                        this.focus();  
                        $t.selectionStart = startPos + myValue.length;  
                        $t.selectionEnd = startPos + myValue.length;  
                        $t.scrollTop = scrollTop;  
                        if (arguments.length == 2) {  
                            $t.setSelectionRange(startPos - t,  
                                    $t.selectionEnd + t);  
                            this.focus();  
                        }  
                    } else {  
                        this.value += myValue;  
                        this.focus();  
                    }  
                }  
            }) ;
})(jQuery);

//图片预览
(function(){
	var overlay = $('<div id="galleryOverlay">'),
		slider = $('<div id="gallerySlider">'),
		prevArrow = $('<a id="prevArrow"></a>'),
		nextArrow = $('<a id="nextArrow"></a>'),
		pageSpan = $('<span id="pagelimit"></span'),
		closebutton= $('<div id="closebutton"></div>'),
		overlayVisible = false;

	$.fn.touchTouch = function(){
		
		var placeholders = $([]),
			pl1=[],
			index = 0,
			items = this;
		overlay.hide().appendTo('body');
		slider.appendTo(overlay);
		pageSpan.appendTo(overlay);
		closebutton.appendTo(overlay)
		items.each(function(){
			placeholders = placeholders.add($('<div class="placeholder">'));
		
		});
		slider.empty();
		slider.append(placeholders).on('click',
			function(e) {
				if (!$(e.target).is('img')) {
					hideOverlay();
				}
			});
			closebutton.on('click',
			function(e) {
				if (!$(e.target).is('img')) {
					hideOverlay();
				}
		});

		$('body').on('touchstart', '#gallerySlider img', function(e){
			
			var touch = e.originalEvent,
				startX = touch.changedTouches[0].pageX;
	
			slider.on('touchmove',function(e){
				
				e.preventDefault();
				
				touch = e.originalEvent.touches[0] ||
						e.originalEvent.changedTouches[0];
				
				if(touch.pageX - startX > 10){
					slider.off('touchmove');
					showPrevious();
				}
				else if (touch.pageX - startX < -10){
					slider.off('touchmove');
					showNext();
				}
				 
			});

			return false;
			
			
			
		}).on('touchend',function(){
			slider.off('touchmove');
		});

		items.on('click', function(e){
			e.preventDefault();

			
			index = items.index(this);
	
			
			showOverlay(index);
			showImage(index);
			
			calcPages(items,index);
			// Preload the next image
			preload(index+1);
			
			// Preload the previous
			preload(index-1);
			$(document).data("overlayVisible",true);
			e.cancelBubble = true;    //取消冒泡事件
			//e.stopPropagation(); 
				
		});
		
		
		
		function calcPages(items,index){
			pageSpan.text((index+1)+"/"+items.length);
		}
		// If the browser does not have support 
		// for touch, display the arrows
		if ( !("ontouchstart" in window) ){
			overlay.append(prevArrow).append(nextArrow);
			
			prevArrow.click(function(e){
				e.preventDefault();
				showPrevious();
			});
			
			nextArrow.click(function(e){
				e.preventDefault();
				showNext();
			});
		}
		
		// Listen for arrow keys
		var _window = $(window);
		_window.unbind('keydown');
		_window.bind('keydown', function(e){
		
			if (e.keyCode == 37){
				showPrevious();
			}
			else if (e.keyCode==39){
			
				showNext();
			}
	
		});
		
		
		/* Private functions */
		
	
		function showOverlay(index){
			
			// If the overlay is already shown, exit
			if (overlayVisible){
				return false;
			}
			
			// Show the overlay
			overlay.show();
			
			setTimeout(function(){
				// Trigger the opacity CSS transition
				overlay.addClass('visible');
			}, 100);
	
			// Move the slider to the correct image
			offsetSlider(index);
			
			// Raise the visible flag
			overlayVisible = true;
		}
	
		function hideOverlay(){
			// If the overlay is not shown, exit
			
			if(!overlayVisible){
				return false;
			}
			
			// Hide the overlay
			overlay.hide().removeClass('visible');
			overlayVisible = false;
			$(document).data("overlayVisible",overlayVisible);
		}
	
		function offsetSlider(index){
			// This will trigger a smooth css transition
			slider.css('left',(-index*100)+'%');
		}
	
		// Preload an image by its index in the items array
		function preload(index){
			setTimeout(function(){
				showImage(index);
			}, 1000);
		}
		
		// Show image in the slider
		function showImage(index){
	
			// If the index is outside the bonds of the array
			if(index < 0 || index >= items.length){
				return false;
			}
			
			// Call the load function with the href attribute of the item
			loadImage(items.eq(index).attr('href'), function(){
				placeholders.eq(index).html(this);
			});
		}
		
		// Load the image and execute a callback function.
		// Returns a jQuery object
		
		function loadImage(src, callback){
			var img = $('<img>').on('load', function(){
				callback.call(img);
			});
			
			img.attr('src',src);

		}
		
		function showNext(){
			// If this is not the last image
			
			
			
			if(index+1 < items.length){
				index++;
				offsetSlider(index);
				preload(index+1);
				calcPages(items,index);
			}
			else{
				// Trigger the spring animation
				
				slider.addClass('rightSpring');
				setTimeout(function(){
					slider.removeClass('rightSpring');
				},500);
			}
		}
		
		function showPrevious(){
			
			// If this is not the first image
			if(index>0){
				index--;
				offsetSlider(index);
				preload(index-1);
				calcPages(items,index);
			}
			else{
				// Trigger the spring animation
				
				slider.addClass('leftSpring');
				setTimeout(function(){
					slider.removeClass('leftSpring');
				},500);
			}
		}
	};
	
})(jQuery);
/**
 * 删除对话框
 */
var MsgBox = (function(){
	return{
		tipsDiv : '<div id="promp" class=\"delete-reply-box op\"><div class=\"g-mask\"></div><div class=\"delete-prompt\">'
				  +'<p>{msg}</p><div class=\"btn-box\"><a href=\"javascript:MsgBox.hidden();\" class=\"Btn cancelNewBtn\">取消</a>'
				  +'<a href="javascript:MsgBox.doRemove(\'{url}\',\'{tagId}\',\'{targetUrl}\')" class=\"Btn delete-reply\">删除</a></div></div>',
		msg:"",
		tagId:'',
		url:'',
		targetUrl:'',
		remove : function(msg,tagId,url,targetUrl) {
			try{
		        var token=window.lionJs.getToken(true);
		        if(token!=""){
					this.msg=msg;
					this.tagId=tagId;
					this.url=url;
					this.targetUrl=targetUrl;
					this.show();
		        }
			}catch(e){
				alert("非法用户");
			}
		},
		doRemove : function(url,tagId,targetUrl) {
			$.ajax({
				url: url,
				dataType: 'json',
				cache: false,
				type: 'GET',
				success: function(event, param) {
					var data=param.data;
					if(data && data.isSuccess){
						   if(targetUrl!=''){
							   MsgBox.hidden();
							   location.href=targetUrl;
						   }else{
							   $("#"+tagId).remove();
							   MsgBox.hidden();
						   }
					}else{
						 alert(data.msg);
					}
				}
			});
		},
		
		show : function() {
		    var div =  this.tipsDiv.replace("{msg}",this.msg).replace("{url}",this.url).replace("{tagId}",this.tagId).replace("{targetUrl}",this.targetUrl);
			$("body").append(div);
			$("#promp").toggleClass("op");
		},
		
		hidden : function() {
			$("#promp").toggleClass("op").remove();
			$("#promp").remove();
		},
		
	};
})();

var LoadUtil = (function(){
	return{
		loadToTarget : function(targetId,url,position) {
			this.loadData(targetId,url,position);
		},
		
		loadData : function(targetId,url,position) {
			$.ajax({
				url: url,
				dataType : "html",
				cache: false,
				type: 'GET',
				success: function(event, param) {
					var data=param.data;
					if(data){
						if('before'==position){
							$("#"+targetId).prepend(data);
						}else if('after'==position){
							$("#"+targetId).append(data);
						}else if('all'==position){
							$("#"+targetId).html(data);
						}else if('remove'==position){
							$("#"+targetId).remove();
						}
					}else{
						
					}
				}
			});
		}
		
	};
})();
