(function(b,c){var $=b.jQuery||b.Cowboy||(b.Cowboy={}),a;$.throttle=a=function(e,f,j,i){var h,d=0;if(typeof f!=="boolean"){i=j;j=f;f=c}function g(){var o=this,m=+new Date()-d,n=arguments;function l(){d=+new Date();j.apply(o,n)}function k(){h=c}if(i&&!h){l()}h&&clearTimeout(h);if(i===c&&m>e){l()}else{if(f!==true){h=setTimeout(i?k:l,i===c?e-m:e)}}}if($.guid){g.guid=j.guid=j.guid||$.guid++}return g};$.debounce=function(d,e,f){return f===c?a(d,e,false):a(d,f,e!==false)}})(this);

$(function(){
	$('.nowrap').each(function() {
		if($(this).find('thead').length > 0 && $(this).find('th').length > 0) {
			var $w	   = $(window),
				$t	   = $(this),
				$thead = $t.find('thead').clone(),
				$col   = $t.find('thead, tbody').clone();

			$t
			.addClass('sticky-enabled')
			.wrap('<div class="sticky-wrap" />');

			if($t.hasClass('overflow-y')) $t.removeClass('overflow-y').parent().addClass('overflow-y');

			$t.before('<table class="sticky-thead" />');

			if($t.find('tbody th').length > 0) {
				$t.before('<table class="sticky-col" /><table class="sticky-intersect" />');
			}

			var $stickyHead  = $(this).siblings('.sticky-thead'),
				$stickyCol   = $(this).siblings('.sticky-col'),
				$stickyInsct = $(this).siblings('.sticky-intersect'),
				$stickyWrap  = $(this).parent('.sticky-wrap');

			$stickyHead.append($thead);

			$stickyCol
			.append($col)
				.find('thead th:gt(0)').remove()
				.end()
				.find('tbody td').remove();

			$stickyInsct.html('<thead><tr><th>'+$t.find('thead th:first-child').html()+'</th></tr></thead>');
			var setWidths = function () {
					$t
					.find('thead th').each(function (i) {
						$stickyHead.find('th').eq(i).width($(this).width());
					})
					.end()
					.find('tr').each(function (i) {
						$stickyCol.find('tr').eq(i).height($(this).height());
					});
					$stickyCol.find('th').add($stickyInsct.find('th')).width($t.find('thead th').width())
				},
				repositionStickyHead = function () {
					var allowance = calcAllowance();
					if($t.height() > $stickyWrap.height()) {
						if($stickyWrap.scrollTop() > 0) {
							$stickyHead.add($stickyInsct).css({
								display: 'block',
								top: $stickyWrap.scrollTop()
							});
						} else {
							$stickyHead.add($stickyInsct).css({
								display: 'none',
								top: 0
							});
						}
					} else {
						if($w.scrollTop() > $t.offset().top && $w.scrollTop() < $t.offset().top + $t.outerHeight() - allowance) {
							$stickyHead.add($stickyInsct).css({
								display: 'block',
								top: $w.scrollTop() - $t.offset().top
							});
						} else {
							$stickyHead.add($stickyInsct).css({
								display: 'none',
								top: 0
							});
						}
					}
				},
				repositionStickyCol = function () {
					if($stickyWrap.scrollLeft() > 0) {
						$stickyCol.add($stickyInsct).css({
							display: 'block',
							left: $stickyWrap.scrollLeft()
						});
					} else {
						$stickyCol
						.css({ opacity: 0, display: 'none'})
						.add($stickyInsct).css({ left: 0 });
					}
				},
				calcAllowance = function () {
					var a = 0;
					$t.find('tbody tr:lt(3)').each(function () {
						a += $(this).height();
					});
					
					if(a > $w.height()*0.25) {
						a = $w.height()*0.25;
					}
					a += $stickyHead.height();
					return a;
				};

			setWidths();

			$t.parent('.sticky-wrap').scroll($.throttle(250, function() {
				repositionStickyHead();
				repositionStickyCol();
			}));

			$w
			.load(setWidths)
			.resize($.debounce(250, function () {
				setWidths();
				repositionStickyHead();
				repositionStickyCol();
			}))
			.scroll($.throttle(250, repositionStickyHead));
		}
	});
	
	//DIV 宽 高 自适应
	var B_Height=$(window).outerHeight(true);
	var head_height = $('.main-top').outerHeight(true); 
	var foot_height = $('.loading-btn').outerHeight(true); 
	$(".sticky-wrap").height(B_Height - head_height - foot_height  - 17);
	
	var T_Width=$(".nowrap table").outerWidth(true);
	$(".sticky-thead").width(T_Width);
	
	
});