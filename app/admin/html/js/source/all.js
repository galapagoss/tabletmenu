// t: current time, b: begInnIng value, c: change In value, d: duration
jQuery.easing['jswing'] = jQuery.easing['swing'];

jQuery.extend( jQuery.easing,
{
	def: 'easeOutQuad',
	swing: function (x, t, b, c, d) {
		//alert(jQuery.easing.default);
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeInQuad: function (x, t, b, c, d) {
		return c*(t/=d)*t + b;
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	},
	easeInOutQuad: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	},
	easeInCubic: function (x, t, b, c, d) {
		return c*(t/=d)*t*t + b;
	},
	easeOutCubic: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t + 1) + b;
	},
	easeInOutCubic: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t + b;
		return c/2*((t-=2)*t*t + 2) + b;
	},
	easeInQuart: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t + b;
	},
	easeOutQuart: function (x, t, b, c, d) {
		return -c * ((t=t/d-1)*t*t*t - 1) + b;
	},
	easeInOutQuart: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
		return -c/2 * ((t-=2)*t*t*t - 2) + b;
	},
	easeInQuint: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t*t + b;
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	},
	easeInOutQuint: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
		return c/2*((t-=2)*t*t*t*t + 2) + b;
	},
	easeInSine: function (x, t, b, c, d) {
		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	},
	easeOutSine: function (x, t, b, c, d) {
		return c * Math.sin(t/d * (Math.PI/2)) + b;
	},
	easeInOutSine: function (x, t, b, c, d) {
		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	},
	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	},
	easeOutExpo: function (x, t, b, c, d) {
		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	},
	easeInOutExpo: function (x, t, b, c, d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	},
	easeInCirc: function (x, t, b, c, d) {
		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	},
	easeOutCirc: function (x, t, b, c, d) {
		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	},
	easeInOutCirc: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	},
	easeInElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	},
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},
	easeInOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	},
	easeInBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	},
	easeOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	},
	easeInOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158; 
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	},
	easeInBounce: function (x, t, b, c, d) {
		return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
	},
	easeOutBounce: function (x, t, b, c, d) {
		if ((t/=d) < (1/2.75)) {
			return c*(7.5625*t*t) + b;
		} else if (t < (2/2.75)) {
			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
		} else if (t < (2.5/2.75)) {
			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
		} else {
			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
		}
	},
	easeInOutBounce: function (x, t, b, c, d) {
		if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
		return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
	}
});
/*! Copyright (c) 2011 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Thanks to: http://adomas.org/javascript-mouse-wheel/ for some pointers.
 * Thanks to: Mathias Bank(http://www.mathias-bank.de) for a scope bug fix.
 * Thanks to: Seamus Leahy for adding deltaX and deltaY
 *
 * Version: 3.0.6
 * 
 * Requires: 1.2.2+
 */
(function(d){function e(a){var b=a||window.event,c=[].slice.call(arguments,1),f=0,e=0,g=0,a=d.event.fix(b);a.type="mousewheel";b.wheelDelta&&(f=b.wheelDelta/120);b.detail&&(f=-b.detail/3);g=f;b.axis!==void 0&&b.axis===b.HORIZONTAL_AXIS&&(g=0,e=-1*f);b.wheelDeltaY!==void 0&&(g=b.wheelDeltaY/120);b.wheelDeltaX!==void 0&&(e=-1*b.wheelDeltaX/120);c.unshift(a,f,e,g);return(d.event.dispatch||d.event.handle).apply(this,c)}var c=["DOMMouseScroll","mousewheel"];if(d.event.fixHooks)for(var h=c.length;h;)d.event.fixHooks[c[--h]]=
d.event.mouseHooks;d.event.special.mousewheel={setup:function(){if(this.addEventListener)for(var a=c.length;a;)this.addEventListener(c[--a],e,false);else this.onmousewheel=e},teardown:function(){if(this.removeEventListener)for(var a=c.length;a;)this.removeEventListener(c[--a],e,false);else this.onmousewheel=null}};d.fn.extend({mousewheel:function(a){return a?this.bind("mousewheel",a):this.trigger("mousewheel")},unmousewheel:function(a){return this.unbind("mousewheel",a)}})})(jQuery);
$(function() {
	$('#sidebar>ul>li:not([class*="current"])>ul').hide();
	$('#sidebar>ul>li:not([class*="nosubmenu"])>a').click(function(){
		e=$(this).parent();$('#sidebar>ul>li.current').removeClass('current').find('ul:first').slideUp();
		e.addClass('current').find('ul:first').slideDown();}
	);
	
	var htmlCollapse=$('#menucollapse').html();
	/*if($.cookie('isCollapsed')){
		$('body').addClass('collapsed');
		$('#menucollapse').html('&#9654;');
	}*/
	$('#menucollapse').click(function(){
		var body=$('body');
		body.toggleClass('collapsed');
		isCollapsed=body.hasClass('collapsed');
		if(isCollapsed){
			$(this).html('&#9654;');
		}else{
			$(this).html(htmlCollapse);
		}
	});
});
/*! fancyBox v2.0.6 fancyapps.com | fancyapps.com/fancybox/#license */
(function(s,l,d,t){var m=d(s),q=d(l),a=d.fancybox=function(){a.open.apply(this,arguments)},u=!1,k=l.createTouch!==t,o=function(a){return"string"===d.type(a)},n=function(b,c){c&&o(b)&&0<b.indexOf("%")&&(b=a.getViewport()[c]/100*parseInt(b,10));return Math.round(b)+"px"};d.extend(a,{version:"2.0.5",defaults:{padding:15,margin:20,width:800,height:600,minWidth:100,minHeight:100,maxWidth:9999,maxHeight:9999,autoSize:!0,autoResize:!k,autoCenter:!k,fitToView:!0,aspectRatio:!1,topRatio:0.5,fixed:!1,scrolling:"auto",
wrapCSS:"",arrows:!0,closeBtn:!0,closeClick:!1,nextClick:!1,mouseWheel:!0,autoPlay:!1,playSpeed:3E3,preload:3,modal:!1,loop:!0,ajax:{dataType:"html",headers:{"X-fancyBox":!0}},keys:{next:[13,32,34,39,40],prev:[8,33,37,38],close:[27]},tpl:{wrap:'<div class="fancybox-wrap"><div class="fancybox-skin"><div class="fancybox-outer"><div class="fancybox-inner"></div></div></div></div>',image:'<img class="fancybox-image" src="{href}" alt="" />',iframe:'<iframe class="fancybox-iframe" name="fancybox-frame{rnd}" frameborder="0" hspace="0"'+
(d.browser.msie?' allowtransparency="true"':"")+"></iframe>",swf:'<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%"><param name="wmode" value="transparent" /><param name="allowfullscreen" value="true" /><param name="allowscriptaccess" value="always" /><param name="movie" value="{href}" /><embed src="{href}" type="application/x-shockwave-flash" allowfullscreen="true" allowscriptaccess="always" width="100%" height="100%" wmode="transparent"></embed></object>',error:'<p class="fancybox-error">The requested content cannot be loaded.<br/>Please try again later.</p>',
closeBtn:'<div title="Close" class="fancybox-item fancybox-close"></div>',next:'<a title="Next" class="fancybox-nav fancybox-next"><span></span></a>',prev:'<a title="Previous" class="fancybox-nav fancybox-prev"><span></span></a>'},openEffect:"fade",openSpeed:300,openEasing:"swing",openOpacity:!0,openMethod:"zoomIn",closeEffect:"fade",closeSpeed:300,closeEasing:"swing",closeOpacity:!0,closeMethod:"zoomOut",nextEffect:"elastic",nextSpeed:300,nextEasing:"swing",nextMethod:"changeIn",prevEffect:"elastic",
prevSpeed:300,prevEasing:"swing",prevMethod:"changeOut",helpers:{overlay:{speedIn:0,speedOut:300,opacity:0.8,css:{cursor:"pointer"},closeClick:!0},title:{type:"float"}}},group:{},opts:{},coming:null,current:null,isOpen:!1,isOpened:!1,player:{timer:null,isActive:!1},ajaxLoad:null,imgPreload:null,transitions:{},helpers:{},open:function(b,c){a.close(!0);b&&!d.isArray(b)&&(b=b instanceof d?d(b).get():[b]);a.isActive=!0;a.opts=d.extend(!0,{},a.defaults,c);d.isPlainObject(c)&&c.keys!==t&&(a.opts.keys=c.keys?
d.extend({},a.defaults.keys,c.keys):!1);a.group=b;a._start(a.opts.index||0)},cancel:function(){a.coming&&!1===a.trigger("onCancel")||(a.coming=null,a.hideLoading(),a.ajaxLoad&&a.ajaxLoad.abort(),a.ajaxLoad=null,a.imgPreload&&(a.imgPreload.onload=a.imgPreload.onabort=a.imgPreload.onerror=null))},close:function(b){a.cancel();a.current&&!1!==a.trigger("beforeClose")&&(a.unbindEvents(),!a.isOpen||b&&!0===b[0]?(d(".fancybox-wrap").stop().trigger("onReset").remove(),a._afterZoomOut()):(a.isOpen=a.isOpened=
!1,d(".fancybox-item, .fancybox-nav").remove(),a.wrap.stop(!0).removeClass("fancybox-opened"),a.inner.css("overflow","hidden"),a.transitions[a.current.closeMethod]()))},play:function(b){var c=function(){clearTimeout(a.player.timer)},e=function(){c();a.current&&a.player.isActive&&(a.player.timer=setTimeout(a.next,a.current.playSpeed))},f=function(){c();d("body").unbind(".player");a.player.isActive=!1;a.trigger("onPlayEnd")};if(a.player.isActive||b&&!1===b[0])f();else if(a.current&&(a.current.loop||
a.current.index<a.group.length-1))a.player.isActive=!0,d("body").bind({"afterShow.player onUpdate.player":e,"onCancel.player beforeClose.player":f,"beforeLoad.player":c}),e(),a.trigger("onPlayStart")},next:function(){a.current&&a.jumpto(a.current.index+1)},prev:function(){a.current&&a.jumpto(a.current.index-1)},jumpto:function(b){a.current&&(b=parseInt(b,10),1<a.group.length&&a.current.loop&&(b>=a.group.length?b=0:0>b&&(b=a.group.length-1)),a.group[b]!==t&&(a.cancel(),a._start(b)))},reposition:function(b,
c){var e;a.isOpen&&(e=a._getPosition(c),b&&"scroll"===b.type?(delete e.position,a.wrap.stop(!0,!0).animate(e,200)):a.wrap.css(e))},update:function(b){a.isOpen&&(u||setTimeout(function(){var c=a.current,e=!b||b&&"orientationchange"===b.type;if(u&&(u=!1,c)){if(!b||"scroll"!==b.type||e)c.autoSize&&"iframe"!==c.type&&(a.inner.height("auto"),c.height=a.inner.height()),(c.autoResize||e)&&a._setDimension(),c.canGrow&&"iframe"!==c.type&&a.inner.height("auto");(c.autoCenter||e)&&a.reposition(b);a.trigger("onUpdate")}},
200),u=!0)},toggle:function(){a.isOpen&&(a.current.fitToView=!a.current.fitToView,a.update())},hideLoading:function(){q.unbind("keypress.fb");d("#fancybox-loading").remove()},showLoading:function(){a.hideLoading();q.bind("keypress.fb",function(b){27===b.keyCode&&(b.preventDefault(),a.cancel())});d('<div id="fancybox-loading"><div></div></div>').click(a.cancel).appendTo("body")},getViewport:function(){return{x:m.scrollLeft(),y:m.scrollTop(),w:k&&s.innerWidth?s.innerWidth:m.width(),h:k&&s.innerHeight?
s.innerHeight:m.height()}},unbindEvents:function(){a.wrap&&a.wrap.unbind(".fb");q.unbind(".fb");m.unbind(".fb")},bindEvents:function(){var b=a.current,c=b.keys;b&&(m.bind("resize.fb orientationchange.fb"+(b.autoCenter&&!b.fixed?" scroll.fb":""),a.update),c&&q.bind("keydown.fb",function(b){var f;f=b.target||b.srcElement;if(!b.ctrlKey&&!b.altKey&&!b.shiftKey&&!b.metaKey&&(!f||!f.type&&!d(f).is("[contenteditable]")))f=b.keyCode,-1<d.inArray(f,c.close)?(a.close(),b.preventDefault()):-1<d.inArray(f,c.next)?
(a.next(),b.preventDefault()):-1<d.inArray(f,c.prev)&&(a.prev(),b.preventDefault())}),d.fn.mousewheel&&b.mouseWheel&&1<a.group.length&&a.wrap.bind("mousewheel.fb",function(b,c){var d=b.target||null;if(0!==c&&(!d||0===d.clientHeight||d.scrollHeight===d.clientHeight&&d.scrollWidth===d.clientWidth))b.preventDefault(),a[0<c?"prev":"next"]()}))},trigger:function(b,c){var e,f=c||a[-1<d.inArray(b,["onCancel","beforeLoad","afterLoad"])?"coming":"current"];if(f){d.isFunction(f[b])&&(e=f[b].apply(f,Array.prototype.slice.call(arguments,
1)));if(!1===e)return!1;f.helpers&&d.each(f.helpers,function(c,e){if(e&&d.isPlainObject(a.helpers[c])&&d.isFunction(a.helpers[c][b]))a.helpers[c][b](e,f)});d.event.trigger(b+".fb")}},isImage:function(a){return o(a)&&a.match(/\.(jpe?g|gif|png|bmp)((\?|#).*)?$/i)},isSWF:function(a){return o(a)&&a.match(/\.(swf)((\?|#).*)?$/i)},_start:function(b){var c={},e=a.group[b]||null,f,g,i;if(e&&(e.nodeType||e instanceof d))f=!0,d.metadata&&(c=d(e).metadata());c=d.extend(!0,{},a.opts,{index:b,element:e},d.isPlainObject(e)?
e:c);d.each(["href","title","content","type"],function(b,g){c[g]=a.opts[g]||f&&d(e).attr(g)||c[g]||null});"number"===typeof c.margin&&(c.margin=[c.margin,c.margin,c.margin,c.margin]);c.modal&&d.extend(!0,c,{closeBtn:!1,closeClick:!1,nextClick:!1,arrows:!1,mouseWheel:!1,keys:null,helpers:{overlay:{css:{cursor:"auto"},closeClick:!1}}});a.coming=c;if(!1===a.trigger("beforeLoad"))a.coming=null;else{g=c.type;b=c.href||e;g||(f&&(g=d(e).data("fancybox-type"),g||(g=(g=e.className.match(/fancybox\.(\w+)/))?
g[1]:null)),!g&&o(b)&&(a.isImage(b)?g="image":a.isSWF(b)?g="swf":b.match(/^#/)&&(g="inline")),g||(g=f?"inline":"html"),c.type=g);if("inline"===g||"html"===g){if(c.content||(c.content="inline"===g?d(o(b)?b.replace(/.*(?=#[^\s]+$)/,""):b):e),!c.content||!c.content.length)g=null}else b||(g=null);"ajax"===g&&o(b)&&(i=b.split(/\s+/,2),b=i.shift(),c.selector=i.shift());c.href=b;c.group=a.group;c.isDom=f;switch(g){case "image":a._loadImage();break;case "ajax":a._loadAjax();break;case "inline":case "iframe":case "swf":case "html":a._afterLoad();
break;default:a._error("type")}}},_error:function(b){a.hideLoading();d.extend(a.coming,{type:"html",autoSize:!0,minWidth:0,minHeight:0,padding:15,hasError:b,content:a.coming.tpl.error});a._afterLoad()},_loadImage:function(){var b=a.imgPreload=new Image;b.onload=function(){this.onload=this.onerror=null;a.coming.width=this.width;a.coming.height=this.height;a._afterLoad()};b.onerror=function(){this.onload=this.onerror=null;a._error("image")};b.src=a.coming.href;(b.complete===t||!b.complete)&&a.showLoading()},
_loadAjax:function(){a.showLoading();a.ajaxLoad=d.ajax(d.extend({},a.coming.ajax,{url:a.coming.href,error:function(b,c){a.coming&&"abort"!==c?a._error("ajax",b):a.hideLoading()},success:function(b,c){"success"===c&&(a.coming.content=b,a._afterLoad())}}))},_preloadImages:function(){var b=a.group,c=a.current,e=b.length,f,g,i,h=Math.min(c.preload,e-1);if(c.preload&&!(2>b.length))for(i=1;i<=h;i+=1)if(f=b[(c.index+i)%e],g=f.href||d(f).attr("href")||f,"image"===f.type||a.isImage(g))(new Image).src=g},_afterLoad:function(){a.hideLoading();
!a.coming||!1===a.trigger("afterLoad",a.current)?a.coming=!1:(a.isOpened?(d(".fancybox-item, .fancybox-nav").remove(),a.wrap.stop(!0).removeClass("fancybox-opened"),a.inner.css("overflow","hidden"),a.transitions[a.current.prevMethod]()):(d(".fancybox-wrap").stop().trigger("onReset").remove(),a.trigger("afterClose")),a.unbindEvents(),a.isOpen=!1,a.current=a.coming,a.wrap=d(a.current.tpl.wrap).addClass("fancybox-"+(k?"mobile":"desktop")+" fancybox-type-"+a.current.type+" fancybox-tmp "+a.current.wrapCSS).appendTo("body"),
a.skin=d(".fancybox-skin",a.wrap).css("padding",n(a.current.padding)),a.outer=d(".fancybox-outer",a.wrap),a.inner=d(".fancybox-inner",a.wrap),a._setContent())},_setContent:function(){var b=a.current,c=b.content,e=b.type,f=b.minWidth,g=b.minHeight,i=b.maxWidth,h=b.maxHeight;switch(e){case "inline":case "ajax":case "html":b.selector?c=d("<div>").html(c).find(b.selector):c instanceof d&&(c.parent().hasClass("fancybox-inner")&&c.parents(".fancybox-wrap").unbind("onReset"),c=c.show().detach(),d(a.wrap).bind("onReset",
function(){c.appendTo("body").hide()}));b.autoSize&&(f=d('<div class="fancybox-wrap '+a.current.wrapCSS+' fancybox-tmp"></div>').appendTo("body").css({minWidth:n(f,"w"),minHeight:n(g,"h"),maxWidth:n(i,"w"),maxHeight:n(h,"h")}).append(c),b.width=f.width(),b.height=f.height(),f.width(a.current.width),f.height()>b.height&&(f.width(b.width+1),b.width=f.width(),b.height=f.height()),c=f.contents().detach(),f.remove());break;case "image":c=b.tpl.image.replace("{href}",b.href);b.aspectRatio=!0;break;case "swf":c=
b.tpl.swf.replace(/\{width\}/g,b.width).replace(/\{height\}/g,b.height).replace(/\{href\}/g,b.href);break;case "iframe":c=d(b.tpl.iframe.replace("{rnd}",(new Date).getTime())).attr("scrolling",b.scrolling).attr("src",b.href),b.scrolling=k?"scroll":"auto"}if("image"===e||"swf"===e)b.autoSize=!1,b.scrolling="visible";"iframe"===e&&b.autoSize?(a.showLoading(),a._setDimension(),a.inner.css("overflow",b.scrolling),c.bind({onCancel:function(){d(this).unbind();a._afterZoomOut()},load:function(){a.hideLoading();
try{this.contentWindow.document.location&&(a.current.height=d(this).contents().find("body").height())}catch(b){a.current.autoSize=!1}a[a.isOpen?"_afterZoomIn":"_beforeShow"]()}}).appendTo(a.inner)):(a.inner.append(c),a._beforeShow())},_beforeShow:function(){a.coming=null;a.trigger("beforeShow");a._setDimension();a.wrap.hide().removeClass("fancybox-tmp");a.bindEvents();a._preloadImages();a.transitions[a.isOpened?a.current.nextMethod:a.current.openMethod]()},_setDimension:function(){var b=a.wrap,c=
a.inner,e=a.current,f=a.getViewport(),g=e.margin,i=2*e.padding,h=e.width,j=e.height,r=e.maxWidth+i,k=e.maxHeight+i,l=e.minWidth+i,m=e.minHeight+i,p;f.w-=g[1]+g[3];f.h-=g[0]+g[2];o(h)&&0<h.indexOf("%")&&(h=(f.w-i)*parseFloat(h)/100);o(j)&&0<j.indexOf("%")&&(j=(f.h-i)*parseFloat(j)/100);g=h/j;h+=i;j+=i;e.fitToView&&(r=Math.min(f.w,r),k=Math.min(f.h,k));if(e.aspectRatio){if(h>r&&(h=r,j=(h-i)/g+i),j>k&&(j=k,h=(j-i)*g+i),h<l&&(h=l,j=(h-i)/g+i),j<m)j=m,h=(j-i)*g+i}else h=Math.max(l,Math.min(h,r)),j=Math.max(m,
Math.min(j,k));h=Math.round(h);j=Math.round(j);d(b.add(c)).width("auto").height("auto");c.width(h-i).height(j-i);b.width(h);p=b.height();if(h>r||p>k)for(;(h>r||p>k)&&h>l&&p>m;)j-=10,e.aspectRatio?(h=Math.round((j-i)*g+i),h<l&&(h=l,j=(h-i)/g+i)):h-=10,c.width(h-i).height(j-i),b.width(h),p=b.height();e.dim={width:n(h),height:n(p)};e.canGrow=e.autoSize&&j>m&&j<k;e.canShrink=!1;e.canExpand=!1;if(h-i<e.width||j-i<e.height)e.canExpand=!0;else if((h>f.w||p>f.h)&&h>l&&j>m)e.canShrink=!0;a.innerSpace=p-i-
c.height()},_getPosition:function(b){var c=a.current,e=a.getViewport(),f=c.margin,d=a.wrap.width()+f[1]+f[3],i=a.wrap.height()+f[0]+f[2],h={position:"absolute",top:f[0]+e.y,left:f[3]+e.x};c.autoCenter&&c.fixed&&!b&&i<=e.h&&d<=e.w&&(h={position:"fixed",top:f[0],left:f[3]});h.top=n(Math.max(h.top,h.top+(e.h-i)*c.topRatio));h.left=n(Math.max(h.left,h.left+0.5*(e.w-d)));return h},_afterZoomIn:function(){var b=a.current,c=b?b.scrolling:"no";if(b&&(a.isOpen=a.isOpened=!0,a.wrap.addClass("fancybox-opened"),
a.inner.css("overflow","yes"===c?"scroll":"no"===c?"hidden":c),a.trigger("afterShow"),a.update(),(b.closeClick||b.nextClick)&&a.inner.css("cursor","pointer").bind("click.fb",function(c){if(!d(c.target).is("a")&&!d(c.target).parent().is("a"))a[b.closeClick?"close":"next"]()}),b.closeBtn&&d(b.tpl.closeBtn).appendTo(a.skin).bind("click.fb",a.close),b.arrows&&1<a.group.length&&((b.loop||0<b.index)&&d(b.tpl.prev).appendTo(a.outer).bind("click.fb",a.prev),(b.loop||b.index<a.group.length-1)&&d(b.tpl.next).appendTo(a.outer).bind("click.fb",
a.next)),a.opts.autoPlay&&!a.player.isActive))a.opts.autoPlay=!1,a.play()},_afterZoomOut:function(){var b=a.current;a.wrap.trigger("onReset").remove();d.extend(a,{group:{},opts:{},current:null,isActive:!1,isOpened:!1,isOpen:!1,wrap:null,skin:null,outer:null,inner:null});a.trigger("afterClose",b)}});a.transitions={getOrigPosition:function(){var b=a.current,c=b.element,e=b.padding,f=d(b.orig),g={},i=50,h=50;!f.length&&b.isDom&&d(c).is(":visible")&&(f=d(c).find("img:first"),f.length||(f=d(c)));f.length?
(g=f.offset(),f.is("img")&&(i=f.outerWidth(),h=f.outerHeight())):(b=a.getViewport(),g.top=b.y+0.5*(b.h-h),g.left=b.x+0.5*(b.w-i));return g={top:n(g.top-e),left:n(g.left-e),width:n(i+2*e),height:n(h+2*e)}},step:function(b,c){var e=c.prop,d,g;if("width"===e||"height"===e)d=Math.ceil(b-2*a.current.padding),"height"===e&&(g=(b-c.start)/(c.end-c.start),c.start>c.end&&(g=1-g),d-=a.innerSpace*g),a.inner[e](d)},zoomIn:function(){var b=a.wrap,c=a.current,e=c.openEffect,f="elastic"===e,g=d.extend({},c.dim,
a._getPosition(f)),i=d.extend({opacity:1},g);delete i.position;f?(g=this.getOrigPosition(),c.openOpacity&&(g.opacity=0),a.outer.add(a.inner).width("auto").height("auto")):"fade"===e&&(g.opacity=0);b.css(g).show().animate(i,{duration:"none"===e?0:c.openSpeed,easing:c.openEasing,step:f?this.step:null,complete:a._afterZoomIn})},zoomOut:function(){var b=a.wrap,c=a.current,d=c.openEffect,f="elastic"===d,g={opacity:0};f&&("fixed"===b.css("position")&&b.css(a._getPosition(!0)),g=this.getOrigPosition(),c.closeOpacity&&
(g.opacity=0));b.animate(g,{duration:"none"===d?0:c.closeSpeed,easing:c.closeEasing,step:f?this.step:null,complete:a._afterZoomOut})},changeIn:function(){var b=a.wrap,c=a.current,d=c.nextEffect,f="elastic"===d,g=a._getPosition(f),i={opacity:1};g.opacity=0;f&&(g.top=n(parseInt(g.top,10)-200),i.top="+=200px");b.css(g).show().animate(i,{duration:"none"===d?0:c.nextSpeed,easing:c.nextEasing,complete:a._afterZoomIn})},changeOut:function(){var b=a.wrap,c=a.current,e=c.prevEffect,f={opacity:0};b.removeClass("fancybox-opened");
"elastic"===e&&(f.top="+=200px");b.animate(f,{duration:"none"===e?0:c.prevSpeed,easing:c.prevEasing,complete:function(){d(this).trigger("onReset").remove()}})}};a.helpers.overlay={overlay:null,update:function(){var a,c;this.overlay.width("100%").height("100%");d.browser.msie||k?(a=Math.max(l.documentElement.scrollWidth,l.body.scrollWidth),c=Math.max(l.documentElement.offsetWidth,l.body.offsetWidth),a=a<c?m.width():a):a=q.width();this.overlay.width(a).height(q.height())},beforeShow:function(b){this.overlay||
(b=d.extend(!0,{},a.defaults.helpers.overlay,b),this.overlay=d('<div id="fancybox-overlay"></div>').css(b.css).appendTo("body"),b.closeClick&&this.overlay.bind("click.fb",a.close),a.current.fixed&&!k?this.overlay.addClass("overlay-fixed"):(this.update(),this.onUpdate=function(){this.update()}),this.overlay.fadeTo(b.speedIn,b.opacity))},afterClose:function(a){this.overlay&&this.overlay.fadeOut(a.speedOut||0,function(){d(this).remove()});this.overlay=null}};a.helpers.title={beforeShow:function(b){var c;
if(c=a.current.title)c=d('<div class="fancybox-title fancybox-title-'+b.type+'-wrap">'+c+"</div>").appendTo("body"),"float"===b.type&&(c.width(c.width()),c.wrapInner('<span class="child"></span>'),a.current.margin[2]+=Math.abs(parseInt(c.css("margin-bottom"),10))),c.appendTo("over"===b.type?a.inner:"outside"===b.type?a.wrap:a.skin)}};d.fn.fancybox=function(b){var c=d(this),e=this.selector||"",f,g=function(g){var h=this,j=f,k;!g.ctrlKey&&!g.altKey&&!g.shiftKey&&!g.metaKey&&!d(h).is(".fancybox-wrap")&&
(g.preventDefault(),g=b.groupAttr||"data-fancybox-group",k=d(h).attr(g),k||(g="rel",k=h[g]),k&&""!==k&&"nofollow"!==k&&(h=e.length?d(e):c,h=h.filter("["+g+'="'+k+'"]'),j=h.index(this)),b.index=j,a.open(h,b))},b=b||{};f=b.index||0;e?q.undelegate(e,"click.fb-start").delegate(e,"click.fb-start",g):c.unbind("click.fb-start").bind("click.fb-start",g);return this};d(l).ready(function(){a.defaults.fixed=d.support.fixedPosition||!(d.browser.msie&&6>=d.browser.version)&&!k})})(window,document,jQuery);
 /*!
 * Buttons helper for fancyBox
 * version: 1.0.2
 * @requires fancyBox v2.0 or later
 *
 * Usage: 
 *     $(".fancybox").fancybox({
 *         buttons: {
 *             position : 'top'
 *         }
 *     });
 * 
 * Options:
 *     tpl - HTML template
 *     position - 'top' or 'bottom'
 * 
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.buttons = {
		tpl: '<div id="fancybox-buttons"><ul><li><a class="btnPrev" title="Previous" href="javascript:;"></a></li><li><a class="btnPlay" title="Start slideshow" href="javascript:;"></a></li><li><a class="btnNext" title="Next" href="javascript:;"></a></li><li><a class="btnToggle" title="Toggle size" href="javascript:;"></a></li><li><a class="btnClose" title="Close" href="javascript:jQuery.fancybox.close();"></a></li></ul></div>',
		list: null,
		buttons: {},

		update: function () {
			var toggle = this.buttons.toggle.removeClass('btnDisabled btnToggleOn');

			//Size toggle button
			if (F.current.canShrink) {
				toggle.addClass('btnToggleOn');

			} else if (!F.current.canExpand) {
				toggle.addClass('btnDisabled');
			}
		},

		beforeLoad: function (opts) {
			//Remove self if gallery do not have at least two items
			if (F.group.length < 2) {
				F.coming.helpers.buttons = false;
				F.coming.closeBtn = true;

				return;
			}

			//Increase top margin to give space for buttons
			F.coming.margin[ opts.position === 'bottom' ? 2 : 0 ] += 30;
		},

		onPlayStart: function () {
			if (this.list) {
				this.buttons.play.attr('title', 'Pause slideshow').addClass('btnPlayOn');
			}
		},

		onPlayEnd: function () {
			if (this.list) {
				this.buttons.play.attr('title', 'Start slideshow').removeClass('btnPlayOn');
			}
		},

		afterShow: function (opts) {
			var buttons;

			if (!this.list) {
				this.list = $(opts.tpl || this.tpl).addClass(opts.position || 'top').appendTo('body');

				this.buttons = {
					prev : this.list.find('.btnPrev').click( F.prev ),
					next : this.list.find('.btnNext').click( F.next ),
					play : this.list.find('.btnPlay').click( F.play ),
					toggle : this.list.find('.btnToggle').click( F.toggle )
				}
			}

			buttons = this.buttons;

			//Prev
			if (F.current.index > 0 || F.current.loop) {
				buttons.prev.removeClass('btnDisabled');
			} else {
				buttons.prev.addClass('btnDisabled');
			}

			//Next / Play
			if (F.current.loop || F.current.index < F.group.length - 1) {
				buttons.next.removeClass('btnDisabled');
				buttons.play.removeClass('btnDisabled');

			} else {
				buttons.next.addClass('btnDisabled');
				buttons.play.addClass('btnDisabled');
			}

			this.update();
		},

		onUpdate: function () {
			this.update();
		},

		beforeClose: function () {
			if (this.list) {
				this.list.remove();
			}

			this.list = null;
			this.buttons = {};
		}
	};

}(jQuery));
 /*!
 * Media helper for fancyBox
 * version: 1.0.0
 * @requires fancyBox v2.0 or later
 *
 * Usage:
 *     $(".fancybox").fancybox({
 *         media: {}
 *     });
 *
 *  Supports:
 *      Youtube
 *          http://www.youtube.com/watch?v=opj24KnzrWo
 *          http://youtu.be/opj24KnzrWo
 *      Vimeo
 *          http://vimeo.com/25634903
 *      Metacafe
 *          http://www.metacafe.com/watch/7635964/dr_seuss_the_lorax_movie_trailer/
 *          http://www.metacafe.com/watch/7635964/
 *      Dailymotion
 *          http://www.dailymotion.com/video/xoytqh_dr-seuss-the-lorax-premiere_people
 *      Twitvid
 *          http://twitvid.com/QY7MD
 *      Twitpic
 *          http://twitpic.com/7p93st
 *      Instagram
 *          http://instagr.am/p/IejkuUGxQn/
 *          http://instagram.com/p/IejkuUGxQn/
 *      Google maps
 *          http://maps.google.com/maps?q=Eiffel+Tower,+Avenue+Gustave+Eiffel,+Paris,+France&t=h&z=17
 *          http://maps.google.com/?ll=48.857995,2.294297&spn=0.007666,0.021136&t=m&z=16
 *          http://maps.google.com/?ll=48.859463,2.292626&spn=0.000965,0.002642&t=m&z=19&layer=c&cbll=48.859524,2.292532&panoid=YJ0lq28OOy3VT2IqIuVY0g&cbp=12,151.58,,0,-15.56
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.media = {
		beforeLoad : function(opts, obj) {
			var href = obj.href || '',
				type = false,
				rez;

			if ((rez = href.match(/(youtube\.com|youtu\.be)\/(v\/|u\/|embed\/|watch\?v=)?([^#\&\?]*).*/i))) {
				href = '//www.youtube.com/embed/' + rez[3] + '?autoplay=1&autohide=1&fs=1&rel=0&enablejsapi=1';
				type = 'iframe';

			} else if ((rez = href.match(/vimeo.com\/(\d+)\/?(.*)/))) {
				href = '//player.vimeo.com/video/' + rez[1] + '?hd=1&autoplay=1&show_title=1&show_byline=1&show_portrait=0&color=&fullscreen=1';
				type = 'iframe';

			} else if ((rez = href.match(/metacafe.com\/watch\/(\d+)\/?(.*)/))) {
				href = '//www.metacafe.com/fplayer/' + rez[1] + '/.swf?playerVars=autoPlay=yes';
				type = 'swf';

			} else if ((rez = href.match(/dailymotion.com\/video\/(.*)\/?(.*)/))) {
				href = '//www.dailymotion.com/swf/video/' + rez[1] + '?additionalInfos=0&autoStart=1';
				type = 'swf';

			} else if ((rez = href.match(/twitvid\.com\/([a-zA-Z0-9_\-\?\=]+)/i))) {
				href = '//www.twitvid.com/embed.php?autoplay=0&guid=' + rez[1];
				type = 'iframe';

			} else if ((rez = href.match(/twitpic\.com\/(?!(?:place|photos|events)\/)([a-zA-Z0-9\?\=\-]+)/i))) {
				href = '//twitpic.com/show/full/' + rez[1];
				type = 'image';

			} else if ((rez = href.match(/(instagr\.am|instagram\.com)\/p\/([a-zA-Z0-9_\-]+)\/?/i))) {
				href = '//' + rez[1] + '/p/' + rez[2] + '/media/?size=l';
				type = 'image';

			} else if ((rez = href.match(/maps\.google\.com\/(\?ll=|maps\/?\?q=)(.*)/i))) {
				href = '//maps.google.com/' + rez[1] + '' + rez[2] + '&output=' + (rez[2].indexOf('layer=c') ? 'svembed' : 'embed');
				type = 'iframe';
			}

			if (type) {
				obj.href = href;
				obj.type = type;
			}
		}
	}

}(jQuery));
 /*!
 * Thumbnail helper for fancyBox
 * version: 1.0.4
 * @requires fancyBox v2.0 or later
 *
 * Usage:
 *     $(".fancybox").fancybox({
 *         thumbs: {
 *             width  : 50,
 *             height : 50
 *         }
 *     });
 *
 * Options:
 *     width - thumbnail width
 *     height - thumbnail height
 *     source - function to obtain the URL of the thumbnail image
 *     position - 'top' or 'bottom'
 *
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.thumbs = {
		wrap: null,
		list: null,
		width: 0,

		//Default function to obtain the URL of the thumbnail image
		source: function (el) {
			var img;

			if ($.type(el) === 'string') {
				return el;
			}

			img = $(el).find('img');

			return img.length ? img.attr('src') : el.href;
		},

		init: function (opts) {
			var that = this,
				list,
				thumbWidth = opts.width || 50,
				thumbHeight = opts.height || 50,
				thumbSource = opts.source || this.source;

			//Build list structure
			list = '';

			for (var n = 0; n < F.group.length; n++) {
				list += '<li><a style="width:' + thumbWidth + 'px;height:' + thumbHeight + 'px;" href="javascript:jQuery.fancybox.jumpto(' + n + ');"></a></li>';
			}

			this.wrap = $('<div id="fancybox-thumbs"></div>').addClass(opts.position || 'bottom').appendTo('body');
			this.list = $('<ul>' + list + '</ul>').appendTo(this.wrap);

			//Load each thumbnail
			$.each(F.group, function (i) {
				$("<img />").load(function () {
					var width = this.width,
						height = this.height,
						widthRatio, heightRatio, parent;

					if (!that.list || !width || !height) {
						return;
					}

					//Calculate thumbnail width/height and center it
					widthRatio = width / thumbWidth;
					heightRatio = height / thumbHeight;
					parent = that.list.children().eq(i).find('a');

					if (widthRatio >= 1 && heightRatio >= 1) {
						if (widthRatio > heightRatio) {
							width = Math.floor(width / heightRatio);
							height = thumbHeight;

						} else {
							width = thumbWidth;
							height = Math.floor(height / widthRatio);
						}
					}

					$(this).css({
						width: width,
						height: height,
						top: Math.floor(thumbHeight / 2 - height / 2),
						left: Math.floor(thumbWidth / 2 - width / 2)
					});

					parent.width(thumbWidth).height(thumbHeight);

					$(this).hide().appendTo(parent).fadeIn(300);

				}).attr('src', thumbSource( F.group[ i ] ));
			});

			//Set initial width
			this.width = this.list.children().eq(0).outerWidth(true);

			this.list.width(this.width * (F.group.length + 1)).css('left', Math.floor($(window).width() * 0.5 - (F.current.index * this.width + this.width * 0.5)));
		},

		//Center list
		update: function (opts) {
			if (this.list) {
				this.list.stop(true).animate({
					'left': Math.floor($(window).width() * 0.5 - (F.current.index * this.width + this.width * 0.5))
				}, 150);
			}
		},

		beforeLoad: function (opts) {
			//Remove self if gallery do not have at least two items
			if (F.group.length < 2) {
				F.coming.helpers.thumbs = false;

				return;
			}

			//Increase bottom margin to give space for thumbs
			F.coming.margin[ opts.position === 'top' ? 0 : 2 ] = opts.height + 30;
		},

		afterShow: function (opts) {
			//Check if exists and create or update list
			if (this.list) {
				this.update(opts);

			} else {
				this.init(opts);
			}

			//Set active element
			this.list.children().removeClass('active').eq(F.current.index).addClass('active');
		},

		onUpdate: function () {
			this.update();
		},

		beforeClose: function () {
			if (this.wrap) {
				this.wrap.remove();
			}

			this.wrap = null;
			this.list = null;
			this.width = 0;
		}
	}

}(jQuery));

/* ----------------------------------------------------------------- */
//Chosen, a Select Box Enhancer for jQuery and Protoype
//by Patrick Filler for Harvest, http://getharvest.com
//
//Version 0.9.8
//Full source at https://github.com/harvesthq/chosen
//Copyright (c) 2011 Harvest http://getharvest.com

//MIT License, https://github.com/harvesthq/chosen/blob/master/LICENSE.md
//This file is generated by `cake build`, do not edit it by hand.
(function() {
var SelectParser;

SelectParser = (function() {

 function SelectParser() {
   this.options_index = 0;
   this.parsed = [];
 }

 SelectParser.prototype.add_node = function(child) {
   if (child.nodeName === "OPTGROUP") {
     return this.add_group(child);
   } else {
     return this.add_option(child);
   }
 };

 SelectParser.prototype.add_group = function(group) {
   var group_position, option, _i, _len, _ref, _results;
   group_position = this.parsed.length;
   this.parsed.push({
     array_index: group_position,
     group: true,
     label: group.label,
     children: 0,
     disabled: group.disabled
   });
   _ref = group.childNodes;
   _results = [];
   for (_i = 0, _len = _ref.length; _i < _len; _i++) {
     option = _ref[_i];
     _results.push(this.add_option(option, group_position, group.disabled));
   }
   return _results;
 };

 SelectParser.prototype.add_option = function(option, group_position, group_disabled) {
   if (option.nodeName === "OPTION") {
     if (option.text !== "") {
       if (group_position != null) this.parsed[group_position].children += 1;
       this.parsed.push({
         array_index: this.parsed.length,
         options_index: this.options_index,
         value: option.value,
         text: option.text,
         html: option.innerHTML,
         selected: option.selected,
         disabled: group_disabled === true ? group_disabled : option.disabled,
         group_array_index: group_position,
         classes: option.className,
         style: option.style.cssText
       });
     } else {
       this.parsed.push({
         array_index: this.parsed.length,
         options_index: this.options_index,
         empty: true
       });
     }
     return this.options_index += 1;
   }
 };

 return SelectParser;

})();

SelectParser.select_to_array = function(select) {
 var child, parser, _i, _len, _ref;
 parser = new SelectParser();
 _ref = select.childNodes;
 for (_i = 0, _len = _ref.length; _i < _len; _i++) {
   child = _ref[_i];
   parser.add_node(child);
 }
 return parser.parsed;
};

this.SelectParser = SelectParser;

}).call(this);

/*
Chosen source: generate output using 'cake build'
Copyright (c) 2011 by Harvest
*/

(function() {
var AbstractChosen, root;

root = this;

AbstractChosen = (function() {

 function AbstractChosen(form_field, options) {
   this.form_field = form_field;
   this.options = options != null ? options : {};
   this.set_default_values();
   this.is_multiple = this.form_field.multiple;
   this.set_default_text();
   this.setup();
   this.set_up_html();
   this.register_observers();
   this.finish_setup();
 }

 AbstractChosen.prototype.set_default_values = function() {
   var _this = this;
   this.click_test_action = function(evt) {
     return _this.test_active_click(evt);
   };
   this.activate_action = function(evt) {
     return _this.activate_field(evt);
   };
   this.active_field = false;
   this.mouse_on_container = false;
   this.results_showing = false;
   this.result_highlighted = null;
   this.result_single_selected = null;
   this.allow_single_deselect = (this.options.allow_single_deselect != null) && (this.form_field.options[0] != null) && this.form_field.options[0].text === "" ? this.options.allow_single_deselect : false;
   this.disable_search_threshold = this.options.disable_search_threshold || 0;
   this.search_contains = this.options.search_contains || false;
   this.choices = 0;
   this.single_backstroke_delete = this.options.single_backstroke_delete || false;
   return this.max_selected_options = this.options.max_selected_options || Infinity;
 };

 AbstractChosen.prototype.set_default_text = function() {
   if (this.form_field.getAttribute("data-placeholder")) {
     this.default_text = this.form_field.getAttribute("data-placeholder");
   } else if (this.is_multiple) {
     this.default_text = this.options.placeholder_text_multiple || this.options.placeholder_text || "Select Some Options";
   } else {
     this.default_text = this.options.placeholder_text_single || this.options.placeholder_text || "Select an Option";
   }
   this.addTagOption = "";
   if (this.form_field.getAttribute("data-add-option")) {
  	 this.addTagOption = this.form_field.getAttribute("data-add-option");
   }
   return this.results_none_found = this.form_field.getAttribute("data-no_results_text") || this.options.no_results_text || "No results match";
 };

 AbstractChosen.prototype.mouse_enter = function() {
   return this.mouse_on_container = true;
 };

 AbstractChosen.prototype.mouse_leave = function() {
   return this.mouse_on_container = false;
 };

 AbstractChosen.prototype.input_focus = function(evt) {
   var _this = this;
   if (!this.active_field) {
     return setTimeout((function() {
       return _this.container_mousedown();
     }), 50);
   }
 };

 AbstractChosen.prototype.input_blur = function(evt) {
   var _this = this;
   if (!this.mouse_on_container) {
     this.active_field = false;
     return setTimeout((function() {
       return _this.blur_test();
     }), 100);
   }
 };

 AbstractChosen.prototype.result_add_option = function(option) {
   var classes, style;
   if (!option.disabled) {
     option.dom_id = this.container_id + "_o_" + option.array_index;
     classes = option.selected && this.is_multiple ? [] : ["active-result"];
     if (option.selected) classes.push("result-selected");
     if (option.group_array_index != null) classes.push("group-option");
     if (option.classes !== "") classes.push(option.classes);
     style = option.style.cssText !== "" ? " style=\"" + option.style + "\"" : "";
     return '<li id="' + option.dom_id + '" class="' + classes.join(' ') + '"' + style + '>' + option.html + '</li>';
   } else {
     return "";
   }
 };

 AbstractChosen.prototype.results_update_field = function() {
   if (!this.is_multiple) this.results_reset_cleanup();
   this.result_clear_highlight();
   this.result_single_selected = null;
   return this.results_build();
 };

 AbstractChosen.prototype.results_toggle = function() {
   if (this.results_showing) {
     return this.results_hide();
   } else {
     return this.results_show();
   }
 };

 AbstractChosen.prototype.results_search = function(evt) {
   if (this.results_showing) {
     return this.winnow_results();
   } else {
     return this.results_show();
   }
 };

 AbstractChosen.prototype.keyup_checker = function(evt) {
   var stroke, _ref;
   stroke = (_ref = evt.which) != null ? _ref : evt.keyCode;
   this.search_field_scale();
   switch (stroke) {
     case 8:
       if (this.is_multiple && this.backstroke_length < 1 && this.choices > 0) {
         return this.keydown_backstroke();
       } else if (!this.pending_backstroke) {
         this.result_clear_highlight();
         return this.results_search();
       }
       break;
     case 13:
       evt.preventDefault();
       if (this.results_showing) return this.result_select(evt);
       break;
     case 27:
       if (this.results_showing) this.results_hide();
       return true;
     case 9:
     case 38:
     case 40:
     case 16:
     case 91:
     case 17:
       break;
     default:
       return this.results_search();
   }
 };

 AbstractChosen.prototype.generate_field_id = function() {
   var new_id;
   new_id = this.generate_random_id();
   this.form_field.id = new_id;
   return new_id;
 };

 AbstractChosen.prototype.generate_random_char = function() {
   var chars, newchar, rand;
   chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   rand = Math.floor(Math.random() * chars.length);
   return newchar = chars.substring(rand, rand + 1);
 };

 return AbstractChosen;

})();

root.AbstractChosen = AbstractChosen;

}).call(this);

/*----------------------------------------------------------------------*/
/*
Chosen source: generate output using 'cake build'
Copyright (c) 2011 by Harvest
*/

(function() {
var $, Chosen, get_side_border_padding, root,
 __hasProp = Object.prototype.hasOwnProperty,
 __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor; child.__super__ = parent.prototype; return child; };

root = this;

$ = jQuery;

$.fn.extend({
 chosen: function(options) {
   if ($.browser.msie && ($.browser.version === "6.0" || $.browser.version === "7.0")) {
     return this;
   }
   return this.each(function(input_field) {
     var $this;
     $this = $(this);
     if (!$this.hasClass("chzn-done")) {
       return $this.data('chosen', new Chosen(this, options));
     }
   });
 }
});

Chosen = (function(_super) {

 __extends(Chosen, _super);

 function Chosen() {
   Chosen.__super__.constructor.apply(this, arguments);
 }

 Chosen.prototype.setup = function() {
   this.form_field_jq = $(this.form_field);
   this.current_value = this.form_field_jq.val();
   return this.is_rtl = this.form_field_jq.hasClass("chzn-rtl");
 };

 Chosen.prototype.finish_setup = function() {
   return this.form_field_jq.addClass("chzn-done");
 };

 Chosen.prototype.set_up_html = function() {
   var container_div, dd_top, dd_width, sf_width;
   this.container_id = this.form_field.id.length ? this.form_field.id.replace(/[^\w]/g, '_') : this.generate_field_id();
   this.container_id += "_chzn";
   this.f_width = this.form_field_jq.outerWidth();
   container_div = $("<div />", {
     id: this.container_id,
     "class": "chzn-container" + (this.is_rtl ? ' chzn-rtl' : ''),
     style: 'width: ' + this.f_width + 'px;'
   });
   if (this.is_multiple) {
     container_div.html('<ul class="chzn-choices"><li class="search-field"><input type="text" value="' + this.default_text + '" class="default" autocomplete="off" style="width:25px;" /></li></ul><div class="chzn-drop" style="left:-9000px;"><ul class="chzn-results"></ul></div>');
   } else {
     container_div.html('<a href="javascript:void(0)" class="chzn-single chzn-default"><span>' + this.default_text + '</span><div><b></b></div></a><div class="chzn-drop" style="left:-9000px;"><div class="chzn-search"><input type="text" autocomplete="off" /></div><ul class="chzn-results"></ul></div>');
   }
   this.form_field_jq.hide().after(container_div);
   this.container = $('#' + this.container_id);
   this.container.addClass("chzn-container-" + (this.is_multiple ? "multi" : "single"));
   this.dropdown = this.container.find('div.chzn-drop').first();
   dd_top = this.container.height();
   dd_width = this.f_width - get_side_border_padding(this.dropdown);
   this.dropdown.css({
     "width": dd_width + "px",
     "top": dd_top + "px"
   });
   this.search_field = this.container.find('input').first();
   this.search_results = this.container.find('ul.chzn-results').first();
   this.search_field_scale();
   this.search_no_results = this.container.find('li.no-results').first();
   if (this.is_multiple) {
     this.search_choices = this.container.find('ul.chzn-choices').first();
     this.search_container = this.container.find('li.search-field').first();
   } else {
     this.search_container = this.container.find('div.chzn-search').first();
     this.selected_item = this.container.find('.chzn-single').first();
     sf_width = dd_width - get_side_border_padding(this.search_container) - get_side_border_padding(this.search_field);
     this.search_field.css({
       "width": sf_width + "px"
     });
   }
   this.results_build();
   this.set_tab_index();
   return this.form_field_jq.trigger("liszt:ready", {
     chosen: this
   });
 };

 Chosen.prototype.register_observers = function() {
   var _this = this;
   this.container.mousedown(function(evt) {
     return _this.container_mousedown(evt);
   });
   this.container.mouseup(function(evt) {
     return _this.container_mouseup(evt);
   });
   this.container.mouseenter(function(evt) {
     return _this.mouse_enter(evt);
   });
   this.container.mouseleave(function(evt) {
     return _this.mouse_leave(evt);
   });
   this.search_results.mouseup(function(evt) {
     return _this.search_results_mouseup(evt);
   });
   this.search_results.mouseover(function(evt) {
     return _this.search_results_mouseover(evt);
   });
   this.search_results.mouseout(function(evt) {
     return _this.search_results_mouseout(evt);
   });
   this.form_field_jq.bind("liszt:updated", function(evt) {
     return _this.results_update_field(evt);
   });
   this.search_field.blur(function(evt) {
     return _this.input_blur(evt);
   });
   this.search_field.keyup(function(evt) {
     return _this.keyup_checker(evt);
   });
   this.search_field.keydown(function(evt) {
     return _this.keydown_checker(evt);
   });
   if (this.is_multiple) {
     this.search_choices.click(function(evt) {
       return _this.choices_click(evt);
     });
     return this.search_field.focus(function(evt) {
       return _this.input_focus(evt);
     });
   } else {
     return this.container.click(function(evt) {
       return evt.preventDefault();
     });
   }
 };

 Chosen.prototype.search_field_disabled = function() {
   this.is_disabled = this.form_field_jq[0].disabled;
   if (this.is_disabled) {
     this.container.addClass('chzn-disabled');
     this.search_field[0].disabled = true;
     if (!this.is_multiple) {
       this.selected_item.unbind("focus", this.activate_action);
     }
     return this.close_field();
   } else {
     this.container.removeClass('chzn-disabled');
     this.search_field[0].disabled = false;
     if (!this.is_multiple) {
       return this.selected_item.bind("focus", this.activate_action);
     }
   }
 };
 
 
 Chosen.prototype.container_addTag_click = function(evt) {
	 this.form_field.options[this.form_field.options.length] = new Option(this.search_results.find(".no-results SPAN").html().replace(",","."),0,true);
	 this.results_update_field(evt);
 };

 Chosen.prototype.container_mousedown = function(evt) {
   var target_closelink;
   if (!this.is_disabled) {
     target_closelink = evt != null ? ($(evt.target)).hasClass("search-choice-close") : false;
     if (evt && evt.type === "mousedown" && !this.results_showing) {
       evt.stopPropagation();
     }
     if (!this.pending_destroy_click && !target_closelink) {
       if (!this.active_field) {
         if (this.is_multiple) this.search_field.val("");
         $(document).click(this.click_test_action);
         this.results_show();
       } else if (!this.is_multiple && evt && (($(evt.target)[0] === this.selected_item[0]) || $(evt.target).parents("a.chzn-single").length)) {
         evt.preventDefault();
         this.results_toggle();
       }
       return this.activate_field();
     } else {
       return this.pending_destroy_click = false;
     }
   }
 };

 Chosen.prototype.container_mouseup = function(evt) {
   if (evt.target.nodeName === "ABBR" && !this.is_disabled) {
     return this.results_reset(evt);
   }
 };

 Chosen.prototype.blur_test = function(evt) {
   if (!this.active_field && this.container.hasClass("chzn-container-active")) {
     return this.close_field();
   }
 };

 Chosen.prototype.close_field = function() {
   $(document).unbind("click", this.click_test_action);
   if (!this.is_multiple) {
     this.selected_item.attr("tabindex", this.search_field.attr("tabindex"));
     this.search_field.attr("tabindex", -1);
   }
   this.active_field = false;
   this.results_hide();
   this.container.removeClass("chzn-container-active");
   this.winnow_results_clear();
   this.clear_backstroke();
   this.show_search_field_default();
   return this.search_field_scale();
 };

 Chosen.prototype.activate_field = function() {
   if (!this.is_multiple && !this.active_field) {
     this.search_field.attr("tabindex", this.selected_item.attr("tabindex"));
     this.selected_item.attr("tabindex", -1);
   }
   this.container.addClass("chzn-container-active");
   this.active_field = true;
   this.search_field.val(this.search_field.val());
   return this.search_field.focus();
 };

 Chosen.prototype.test_active_click = function(evt) {
   if ($(evt.target).parents('#' + this.container_id).length) {
     return this.active_field = true;
   } else {
     return this.close_field();
   }
 };

 Chosen.prototype.results_build = function() {
   var content, data, _i, _len, _ref;
   this.parsing = true;
   this.results_data = root.SelectParser.select_to_array(this.form_field);
   if (this.is_multiple && this.choices > 0) {
     this.search_choices.find("li.search-choice").remove();
     this.choices = 0;
   } else if (!this.is_multiple) {
     this.selected_item.addClass("chzn-default").find("span").text(this.default_text);
     if (this.form_field.options.length <= this.disable_search_threshold) {
       this.container.addClass("chzn-container-single-nosearch");
     } else {
       this.container.removeClass("chzn-container-single-nosearch");
     }
   }
   content = '';
   _ref = this.results_data;
   for (_i = 0, _len = _ref.length; _i < _len; _i++) {
     data = _ref[_i];
     if (data.group) {
       content += this.result_add_group(data);
     } else if (!data.empty) {
       content += this.result_add_option(data);
       if (data.selected && this.is_multiple) {
         this.choice_build(data);
       } else if (data.selected && !this.is_multiple) {
         this.selected_item.removeClass("chzn-default").find("span").text(data.text);
         if (this.allow_single_deselect) this.single_deselect_control_build();
       }
     }
   }
   this.search_field_disabled();
   this.show_search_field_default();
   this.search_field_scale();
   this.search_results.html(content);
   return this.parsing = false;
 };

 Chosen.prototype.result_add_group = function(group) {
   if (!group.disabled) {
     group.dom_id = this.container_id + "_g_" + group.array_index;
     return '<li id="' + group.dom_id + '" class="group-result">' + $("<div />").text(group.label).html() + '</li>';
   } else {
     return "";
   }
 };

 Chosen.prototype.result_do_highlight = function(el) {
   var high_bottom, high_top, maxHeight, visible_bottom, visible_top;
   if (el.length) {
     this.result_clear_highlight();
     this.result_highlight = el;
     this.result_highlight.addClass("highlighted");
     maxHeight = parseInt(this.search_results.css("maxHeight"), 10);
     visible_top = this.search_results.scrollTop();
     visible_bottom = maxHeight + visible_top;
     high_top = this.result_highlight.position().top + this.search_results.scrollTop();
     high_bottom = high_top + this.result_highlight.outerHeight();
     if (high_bottom >= visible_bottom) {
       return this.search_results.scrollTop((high_bottom - maxHeight) > 0 ? high_bottom - maxHeight : 0);
     } else if (high_top < visible_top) {
       return this.search_results.scrollTop(high_top);
     }
   }
 };

 Chosen.prototype.result_clear_highlight = function() {
   if (this.result_highlight) this.result_highlight.removeClass("highlighted");
   return this.result_highlight = null;
 };

 Chosen.prototype.results_show = function() {
   var dd_top;
   if (!this.is_multiple) {
     this.selected_item.addClass("chzn-single-with-drop");
     if (this.result_single_selected) {
       this.result_do_highlight(this.result_single_selected);
     }
   } else if (this.max_selected_options <= this.choices) {
     this.form_field_jq.trigger("liszt:maxselected", {
       chosen: this
     });
     return false;
   }
   dd_top = this.is_multiple ? this.container.height() : this.container.height() - 1;
   this.form_field_jq.trigger("liszt:showing_dropdown", {
     chosen: this
   });
   this.dropdown.css({
     "top": dd_top + "px",
     "left": 0
   });
   this.results_showing = true;
   this.search_field.focus();
   this.search_field.val(this.search_field.val());
   return this.winnow_results();
 };

 Chosen.prototype.results_hide = function() {
   if (!this.is_multiple) {
     this.selected_item.removeClass("chzn-single-with-drop");
   }
   this.result_clear_highlight();
   this.form_field_jq.trigger("liszt:hiding_dropdown", {
     chosen: this
   });
   this.dropdown.css({
     "left": "-9000px"
   });
   return this.results_showing = false;
 };

 Chosen.prototype.set_tab_index = function(el) {
   var ti;
   if (this.form_field_jq.attr("tabindex")) {
     ti = this.form_field_jq.attr("tabindex");
     this.form_field_jq.attr("tabindex", -1);
     if (this.is_multiple) {
       return this.search_field.attr("tabindex", ti);
     } else {
       this.selected_item.attr("tabindex", ti);
       return this.search_field.attr("tabindex", -1);
     }
   }
 };

 Chosen.prototype.show_search_field_default = function() {
   if (this.is_multiple && this.choices < 1 && !this.active_field) {
     this.search_field.val(this.default_text);
     return this.search_field.addClass("default");
   } else {
     this.search_field.val("");
     return this.search_field.removeClass("default");
   }
 };

 Chosen.prototype.search_results_mouseup = function(evt) {
   var target;
   target = $(evt.target).hasClass("active-result") ? $(evt.target) : $(evt.target).parents(".active-result").first();
   if (target.length) {
     this.result_highlight = target;
     return this.result_select(evt);
   }
 };

 Chosen.prototype.search_results_mouseover = function(evt) {
   var target;
   target = $(evt.target).hasClass("active-result") ? $(evt.target) : $(evt.target).parents(".active-result").first();
   if (target) return this.result_do_highlight(target);
 };

 Chosen.prototype.search_results_mouseout = function(evt) {
   if ($(evt.target).hasClass("active-result" || $(evt.target).parents('.active-result').first())) {
     return this.result_clear_highlight();
   }
 };

 Chosen.prototype.choices_click = function(evt) {
   evt.preventDefault();
   if (this.active_field && !($(evt.target).hasClass("search-choice" || $(evt.target).parents('.search-choice').first)) && !this.results_showing) {
     return this.results_show();
   }
 };

 Chosen.prototype.choice_build = function(item) {
   var choice_id, link,
     _this = this;
   if (this.is_multiple && this.max_selected_options <= this.choices) {
     this.form_field_jq.trigger("liszt:maxselected", {
       chosen: this
     });
     return false;
   }
   choice_id = this.container_id + "_c_" + item.array_index;
   this.choices += 1;
   this.search_container.before('<li class="search-choice'+(item.value==0 ? ' search-choice-add' : '')+'" id="' + choice_id + '"><span>' +item.html + '</span><a href="javascript:void(0)" class="search-choice-close" rel="' + item.array_index + '"></a></li>');
   link = $('#' + choice_id).find("a").first();
   return link.click(function(evt) {
     return _this.choice_destroy_link_click(evt);
   });
 };

 Chosen.prototype.choice_destroy_link_click = function(evt) {
   evt.preventDefault();
   if (!this.is_disabled) {
     this.pending_destroy_click = true;
     return this.choice_destroy($(evt.target));
   } else {
     return evt.stopPropagation;
   }
 };

 Chosen.prototype.choice_destroy = function(link) {
   this.choices -= 1;
   this.show_search_field_default();
   if (this.is_multiple && this.choices > 0 && this.search_field.val().length < 1) {
     this.results_hide();
   }
   this.result_deselect(link.attr("rel"));
   return link.parents('li').first().remove();
 };

 Chosen.prototype.results_reset = function() {
   this.form_field.options[0].selected = true;
   this.selected_item.find("span").text(this.default_text);
   if (!this.is_multiple) this.selected_item.addClass("chzn-default");
   this.show_search_field_default();
   this.results_reset_cleanup();
   this.form_field_jq.trigger("change");
   if (this.active_field) return this.results_hide();
 };

 Chosen.prototype.results_reset_cleanup = function() {
   return this.selected_item.find("abbr").remove();
 };

 Chosen.prototype.result_select = function(evt) {
   var high, high_id, item, position;
   if (this.result_highlight) {
     high = this.result_highlight;
     high_id = high.attr("id");
     this.result_clear_highlight();
     if (this.is_multiple) {
       this.result_deactivate(high);
     } else {
       this.search_results.find(".result-selected").removeClass("result-selected");
       this.result_single_selected = high;
       this.selected_item.removeClass("chzn-default");
     }
     high.addClass("result-selected");
     position = high_id.substr(high_id.lastIndexOf("_") + 1);
     item = this.results_data[position];
     item.selected = true;
     this.form_field.options[item.options_index].selected = true;
     if (this.is_multiple) {
       this.choice_build(item);
     } else {
       this.selected_item.find("span").first().text(item.text);
       if (this.allow_single_deselect) this.single_deselect_control_build();
     }
     if (!(evt.metaKey && this.is_multiple)) this.results_hide();
     this.search_field.val("");
     if (this.is_multiple || this.form_field_jq.val() !== this.current_value) {
       this.form_field_jq.trigger("change", {
         'selected': this.form_field.options[item.options_index].value
       });
     }
     this.current_value = this.form_field_jq.val();
     return this.search_field_scale();
   }
 };

 Chosen.prototype.result_activate = function(el) {
   return el.addClass("active-result");
 };

 Chosen.prototype.result_deactivate = function(el) {
   return el.removeClass("active-result");
 };

 Chosen.prototype.result_deselect = function(pos) {
   var result, result_data;
   result_data = this.results_data[pos];
   result_data.selected = false;
   this.form_field.options[result_data.options_index].selected = false;
   result = $("#" + this.container_id + "_o_" + pos);
   result.removeClass("result-selected").addClass("active-result").show();
   this.result_clear_highlight();
   this.winnow_results();
   this.form_field_jq.trigger("change", {
     deselected: this.form_field.options[result_data.options_index].value
   });
   return this.search_field_scale();
 };

 Chosen.prototype.single_deselect_control_build = function() {
   if (this.allow_single_deselect && this.selected_item.find("abbr").length < 1) {
     return this.selected_item.find("span").first().after("<abbr class=\"search-choice-close\"></abbr>");
   }
 };

 Chosen.prototype.winnow_results = function() {
   var found, option, part, parts, regex, regexAnchor, result, result_id, results, searchText, startpos, text, zregex, _i, _j, _len, _len2, _ref;
   this.no_results_clear();
   results = 0;
   searchText = this.search_field.val() === this.default_text ? "" : $('<div/>').text($.trim(this.search_field.val())).html();
   regexAnchor = this.search_contains ? "" : "^";
   regex = new RegExp(regexAnchor + searchText.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&"), 'i');
   zregex = new RegExp(searchText.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&"), 'i');
   _ref = this.results_data;
   for (_i = 0, _len = _ref.length; _i < _len; _i++) {
     option = _ref[_i];
     if (!option.disabled && !option.empty) {
       if (option.group) {
         $('#' + option.dom_id).css('display', 'none');
       } else if (!(this.is_multiple && option.selected)) {
         found = false;
         result_id = option.dom_id;
         result = $("#" + result_id);
         if (regex.test(option.html)) {
           found = true;
           results += 1;
         } else if (option.html.indexOf(" ") >= 0 || option.html.indexOf("[") === 0) {
           parts = option.html.replace(/\[|\]/g, "").split(" ");
           if (parts.length) {
             for (_j = 0, _len2 = parts.length; _j < _len2; _j++) {
               part = parts[_j];
               if (regex.test(part)) {
                 found = true;
                 results += 1;
               }
             }
           }
         }
         if (found) {
           if (searchText.length) {
             startpos = option.html.search(zregex);
             text = option.html.substr(0, startpos + searchText.length) + '</em>' + option.html.substr(startpos + searchText.length);
             text = text.substr(0, startpos) + '<em>' + text.substr(startpos);
           } else {
             text = option.html;
           }
           result.html(text);
           this.result_activate(result);
           if (option.group_array_index != null) {
             $("#" + this.results_data[option.group_array_index].dom_id).css('display', 'list-item');
           }
         } else {
           if (this.result_highlight && result_id === this.result_highlight.attr('id')) {
             this.result_clear_highlight();
           }
           this.result_deactivate(result);
         }
       }
     }
   }
   if (results < 1 && searchText.length) {
     return this.no_results(searchText);
   } else {
     return this.winnow_results_set_highlight();
   }
 };

 Chosen.prototype.winnow_results_clear = function() {
   var li, lis, _i, _len, _results;
   this.search_field.val("");
   lis = this.search_results.find("li");
   _results = [];
   for (_i = 0, _len = lis.length; _i < _len; _i++) {
     li = lis[_i];
     li = $(li);
     if (li.hasClass("group-result")) {
       _results.push(li.css('display', 'auto'));
     } else if (!this.is_multiple || !li.hasClass("result-selected")) {
       _results.push(this.result_activate(li));
     } else {
       _results.push(void 0);
     }
   }
   return _results;
 };

 Chosen.prototype.winnow_results_set_highlight = function() {
   var do_high, selected_results;
   if (!this.result_highlight) {
     selected_results = !this.is_multiple ? this.search_results.find(".result-selected.active-result") : [];
     do_high = selected_results.length ? selected_results.first() : this.search_results.find(".active-result").first();
     if (do_high != null) return this.result_do_highlight(do_high);
   }
 };

 Chosen.prototype.no_results = function(terms) {
   var no_results_html;
   no_results_html = $('<li class="no-results">' + this.results_none_found + ' "<span></span>" '+((this.addTagOption!="") ? "<div class=\"chzn-addTag\"><a> INSERISCI<a/></div>" : "")+'</li>');
   no_results_html.find("span").first().html(terms);
   if (terms.length>2)
  	 no_results_html.find(".chzn-addTag").show();
   else
  	 no_results_html.find(".chzn-addTag").hide();
   var _this2 = this;
   no_results_html.find('.chzn-addTag A').click(function(evt) {
     return _this2.container_addTag_click(evt);
   });
   return this.search_results.append(no_results_html);
 };

 Chosen.prototype.no_results_clear = function() {
   return this.search_results.find(".no-results").remove();
 };

 Chosen.prototype.keydown_arrow = function() {
   var first_active, next_sib;
   if (!this.result_highlight) {
     first_active = this.search_results.find("li.active-result").first();
     if (first_active) this.result_do_highlight($(first_active));
   } else if (this.results_showing) {
     next_sib = this.result_highlight.nextAll("li.active-result").first();
     if (next_sib) this.result_do_highlight(next_sib);
   }
   if (!this.results_showing) return this.results_show();
 };

 Chosen.prototype.keyup_arrow = function() {
   var prev_sibs;
   if (!this.results_showing && !this.is_multiple) {
     return this.results_show();
   } else if (this.result_highlight) {
     prev_sibs = this.result_highlight.prevAll("li.active-result");
     if (prev_sibs.length) {
       return this.result_do_highlight(prev_sibs.first());
     } else {
       if (this.choices > 0) this.results_hide();
       return this.result_clear_highlight();
     }
   }
 };

 Chosen.prototype.keydown_backstroke = function() {
   if (this.pending_backstroke) {
     this.choice_destroy(this.pending_backstroke.find("a").first());
     return this.clear_backstroke();
   } else {
     this.pending_backstroke = this.search_container.siblings("li.search-choice").last();
     if (this.single_backstroke_delete) {
       return this.keydown_backstroke();
     } else {
       return this.pending_backstroke.addClass("search-choice-focus");
     }
   }
 };

 Chosen.prototype.clear_backstroke = function() {
   if (this.pending_backstroke) {
     this.pending_backstroke.removeClass("search-choice-focus");
   }
   return this.pending_backstroke = null;
 };

 Chosen.prototype.keydown_checker = function(evt) {
   var stroke, _ref;
   stroke = (_ref = evt.which) != null ? _ref : evt.keyCode;
   this.search_field_scale();
   if (stroke !== 8 && this.pending_backstroke) this.clear_backstroke();
   switch (stroke) {
     case 8:
       this.backstroke_length = this.search_field.val().length;
       break;
     case 9:
       if (this.results_showing && !this.is_multiple) this.result_select(evt);
       this.mouse_on_container = false;
       break;
     case 13:
       evt.preventDefault();
       if (this.addTagOption!="")
      	 this.container_addTag_click(evt);
       break;
     case 38:
       evt.preventDefault();
       this.keyup_arrow();
       break;
     case 40:
       this.keydown_arrow();
       break;
   }
 };

 Chosen.prototype.search_field_scale = function() {
   var dd_top, div, h, style, style_block, styles, w, _i, _len;
   if (this.is_multiple) {
     h = 0;
     w = 0;
     style_block = "position:absolute; left: -1000px; top: -1000px; display:none;";
     styles = ['font-size', 'font-style', 'font-weight', 'font-family', 'line-height', 'text-transform', 'letter-spacing'];
     for (_i = 0, _len = styles.length; _i < _len; _i++) {
       style = styles[_i];
       style_block += style + ":" + this.search_field.css(style) + ";";
     }
     div = $('<div />', {
       'style': style_block
     });
     div.text(this.search_field.val());
     $('body').append(div);
     w = div.width() + 25;
     div.remove();
     if (w > this.f_width - 10) w = this.f_width - 10;
     this.search_field.css({
       'width': w + 'px'
     });
     dd_top = this.container.height();
     return this.dropdown.css({
       "top": dd_top + "px"
     });
   }
 };

 Chosen.prototype.generate_random_id = function() {
   var string;
   string = "sel" + this.generate_random_char() + this.generate_random_char() + this.generate_random_char();
   while ($("#" + string).length > 0) {
     string += this.generate_random_char();
   }
   return string;
 };

 return Chosen;

})(AbstractChosen);

get_side_border_padding = function(elmt) {
 var side_border_padding;
 return side_border_padding = elmt.outerWidth() - elmt.width();
};

root.get_side_border_padding = get_side_border_padding;

}).call(this);


/* ----------------------------------------------------------------- */
/*
// msDropDown is free jQuery Plugin: you can redistribute it and/or modify
// it under the terms of the either the MIT License or the Gnu General Public License (GPL) Version 2
*/
;eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}(';(6($){3 1N="";3 3B=6(s,u){3 v=s;3 x=1b;3 u=$.3C({1c:4s,2w:7,2S:23,1O:9,1P:4t,3D:\'2g\',1J:10,3E:\'4u\',2T:\'\',2U:9,1k:\'\'},u);1b.1Z=21 3F();3 y="";3 z={};z.2V=9;z.2x=10;z.2y=1r;3 A=10;3 B={2W:\'4v\',1Q:\'4w\',1K:\'4x\',2h:\'4y\',1g:\'4z\',2X:\'4A\',2Y:\'4B\',4C:\'4D\',2z:\'4E\',3G:\'4F\'};3 C={2g:u.3D,2Z:\'2Z\',31:\'31\',32:\'32\',1w:\'1w\',1l:.30,1R:\'1R\',2A:\'2A\',2B:\'2B\',15:\'15\'};3 D={3H:"2C,33,34,1S,2D,2E,1s,1A,2F,1T,4G,22,35",1a:"1B,1x,1l,4H"};1b.1U=21 3F();3 E=$(v).1a("1d");5(1e(E)=="14"||E.18<=0){E="4I"+$.1V.3I++;$(v).2i("1d",E)};3 F=$(v).1a("1k");u.1k+=(F==14)?"":F;3 G=$(v).3J();A=($(v).1a("1B")>1||$(v).1a("1x")==9)?9:10;5(A){u.2w=$(v).1a("1B")};3 H={};3 I=0;3 J=10;3 K;3 L=10;3 M={};3 N="";3 O=6(a){5(1e(M[a])=="14"){M[a]=1o.4J(a)}11 M[a]};3 P=6(a){11 E+B[a]};3 Q=6(a){3 b=a;3 c=$(b).1a("1k");11(1e c=="14")?"":c.4K};3 R=6(a){3 b=$("#"+E+" 36:15");5(b.18>1){1C(3 i=0;i<b.18;i++){5(a==b[i].1j){11 9}}}19 5(b.18==1){5(b[0].1j==a){11 9}};11 10};3 S=6(a,b,c,d){3 e="";3 f=(d=="3a")?P("2Y"):P("2X");3 g=(d=="3a")?f+"3b"+(b)+"3b"+(c):f+"3b"+(b);3 h="";3 t="";3 i="";3 j="";5(u.1J!=10){i=\' \'+u.1J+\' \'+a.3K}19{h=$(a).1a("1p");3 k=21 3L(/^\\{.*\\}$/);3 l=k.3M(h);5(u.2U==9&&l==9){5(h.18!=0){3 m=24("["+h+"]");1W=(1e m[0].2j=="14")?"":m[0].2j;t=(1e m[0].1p=="14")?"":m[0].1p;j=(1e m[0].3N=="14")?"":m[0].3N;h=(1W.18==0)?"":\'<1W 2G="\'+1W+\'" 2H="2I" /> \'}}19{h=(h.18==0)?"":\'<1W 2G="\'+h+\'" 2H="2I" /> \'}};3 n=$(a).1t();3 o=$(a).4L();3 p=($(a).1a("1l")==9)?"1l":"3c";H[g]={1L:h+n,2k:o,1t:n,1j:a.1j,1d:g,1p:t};3 q=Q(a);5(R(a.1j)==9){e+=\'<a 3O="3P:3Q(0);" 1u="\'+C.15+\' \'+p+i+\'"\'}19{e+=\'<a  3O="3P:3Q(0);" 1u="\'+p+i+\'"\'};5(q!==10&&q!==14&&q.18!=0){e+=" 1k=\'"+q+"\'"};5(t!==""){e+=" 1p=\'"+t+"\'"};e+=\' 1d="\'+g+\'">\';e+=h+\'<1y 1u="\'+C.1w+\'">\'+n+\'</1y>\';5(j!==""){e+=j};e+=\'</a>\';11 e};3 T=6(t){3 b=t.3d();5(b.18==0)11-1;3 a="";1C(3 i 2l H){3 c=H[i].1t.3d();5(c.3R(0,b.18)==b){a+="#"+H[i].1d+", "}};11(a=="")?-1:a};3 U=6(){3 f=G;5(f.18==0)11"";3 g="";3 h=P("2X");3 i=P("2Y");f.3e(6(c){3 d=f[c];5(d.4M.4N().3d()=="4O"){g+="<1z 1u=\'4P\'>";g+="<1y 1k=\'3S-4Q:4R;3S-1k:4S;4T:4U;\'>"+$(d).1a("4V")+"</1y>";3 e=$(d).3J();e.3e(6(a){3 b=e[a];g+=S(b,c,a,"3a")});g+="</1z>"}19{g+=S(d,c,"","")}});11 g};3 V=6(){3 a=P("1Q");3 b=P("1g");3 c=u.1k;25="";25+=\'<1z 1d="\'+b+\'" 1u="\'+C.32+\'"\';5(!A){25+=(c!="")?\' 1k="\'+c+\'"\':\'\'}19{25+=(c!="")?\' 1k="2J-1D:4W 4X #4Y;1q:2m;1m:2K;\'+c+\'"\':\'\'};25+=\'>\';11 25};3 W=6(){3 a=P("1K");3 b=P("2z");3 c=P("2h");3 d=P("3G");3 e="";3 f="";5(O(E).1E.18>0){e=$("#"+E+" 36:15").1t();f=$("#"+E+" 36:15").1a("1p")};3 g="";3 t="";3 h=21 3L(/^\\{.*\\}$/);3 i=h.3M(f);5(u.2U==9&&i==9){5(f.18!=0){3 j=24("["+f+"]");g=(1e j[0].2j=="14")?"":j[0].2j;t=(1e j[0].1p=="14")?"":j[0].1p;f=(g.18==0||u.1O==10||u.1J!=10)?"":\'<1W 2G="\'+g+\'" 2H="2I" /> \'}}19{f=(f.18==0||f==14||u.1O==10||u.1J!=10)?"":\'<1W 2G="\'+f+\'" 2H="2I" /> \'};3 k=\'<1z 1d="\'+a+\'" 1u="\'+C.2Z+\'"\';k+=\'>\';k+=\'<1y 1d="\'+b+\'" 1u="\'+C.31+\'"></1y><1y 1u="\'+C.1w+\'" 1d="\'+c+\'">\'+f+\'<1y 1u="\'+C.1w+\'">\'+e+\'</1y></1y></1z>\';11 k};3 X=6(){3 c=P("1g");$("#"+c+" a.3c").1F("1S");$("#"+c+" a.3c").1f("1S",6(a){a.26();3f(1b);28();5(!A){$("#"+c).1F("1A");29(10);3 b=(u.1O==10)?$(1b).1t():$(1b).1L();1X(b);x.2n()}})};3 Y=6(){3 d=10;3 e=P("1Q");3 f=P("1K");3 g=P("2h");3 h=P("1g");3 i=P("2z");3 j=$("#"+E).4Z();3 k=u.1k;5($("#"+e).18>0){$("#"+e).2L();d=9};3 l=\'<1z 1d="\'+e+\'" 1u="\'+C.2g+\'"\';l+=(k!="")?\' 1k="\'+k+\'"\':\'\';l+=\'>\';l+=W();l+=V();l+=U();l+="</1z>";l+="</1z>";5(d==9){3 m=P("2W");$("#"+m).3g(l)}19{$("#"+E).3g(l)};5(A){3 f=P("1K");$("#"+f).2o()};$("#"+e).12("3T",j+"1v");$("#"+h).12("3T",(j-2)+"1v");5(G.18>u.2w){3 n=2p($("#"+h+" a:3h").12("2q-3U"))+2p($("#"+h+" a:3h").12("2q-1D"));3 o=((u.2S)*u.2w)-n;$("#"+h).12("1c",o+"1v")}19 5(A){3 o=$("#"+E).1c();$("#"+h).12("1c",o+"1v")};5(d==10){3V();3W(E)};5($("#"+E).1a("1l")==9){$("#"+e).12("2M",C.1l)};3X();$("#"+f).1f("1A",6(a){3i(1)});$("#"+f).1f("1T",6(a){3i(0)});X();$("#"+h+" a.1l").12("2M",C.1l);5(A){$("#"+h).1f("1A",6(c){5(!z.2x){z.2x=9;$(1o).1f("22",6(a){3 b=a.3Y;z.2y=b;5(b==39||b==40){a.26();a.2r();3j();28()};5(b==37||b==38){a.26();a.2r();3k();28()}})}})};$("#"+h).1f("1T",6(a){29(10);$(1o).1F("22",2N);z.2x=10;z.2y=1r});$("#"+f).1f("1S",6(b){29(10);5($("#"+h+":2a").18==1){$("#"+h).1F("1A")}19{$("#"+h).1f("1A",6(a){29(9)});x.3Z()}});$("#"+f).1f("1T",6(a){29(10)});5(u.1O&&u.1J!=10){2s()}};3 Z=6(a){1C(3 i 2l H){5(H[i].1j==a){11 H[i]}};11-1};3 3f=6(a){3 b=P("1g");5($("#"+b+" a."+C.15).18==1){y=$("#"+b+" a."+C.15).1t()};5(!A){$("#"+b+" a."+C.15).1M(C.15)};3 c=$("#"+b+" a."+C.15).1a("1d");5(c!=14){3 d=(z.2b==14||z.2b==1r)?H[c].1j:z.2b};5(a&&!A){$(a).1G(C.15)};5(A){3 e=z.2y;5($("#"+E).1a("1x")==9){5(e==17){z.2b=H[$(a).1a("1d")].1j;$(a).50(C.15)}19 5(e==16){$("#"+b+" a."+C.15).1M(C.15);$(a).1G(C.15);3 f=$(a).1a("1d");3 g=H[f].1j;1C(3 i=3l.51(d,g);i<=3l.52(d,g);i++){$("#"+Z(i).1d).1G(C.15)}}19{$("#"+b+" a."+C.15).1M(C.15);$(a).1G(C.15);z.2b=H[$(a).1a("1d")].1j}}19{$("#"+b+" a."+C.15).1M(C.15);$(a).1G(C.15);z.2b=H[$(a).1a("1d")].1j}}};3 3W=6(a){3 b=a;O(b).53=6(e){$("#"+b).1V(u)}};3 29=6(a){z.2V=a};3 41=6(){11 z.2V};3 3X=6(){3 b=P("1Q");3 c=D.3H.54(",");1C(3 d=0;d<c.18;d++){3 e=c[d];3 f=2c(e);5(f==9){2O(e){1h"2C":$("#"+b).1f("55",6(a){O(E).2C()});1i;1h"1S":$("#"+b).1f("1S",6(a){$("#"+E).1H("1S")});1i;1h"2D":$("#"+b).1f("2D",6(a){$("#"+E).1H("2D")});1i;1h"2E":$("#"+b).1f("2E",6(a){$("#"+E).1H("2E")});1i;1h"1s":$("#"+b).1f("1s",6(a){$("#"+E).1H("1s")});1i;1h"1A":$("#"+b).1f("1A",6(a){$("#"+E).1H("1A")});1i;1h"2F":$("#"+b).1f("2F",6(a){$("#"+E).1H("2F")});1i;1h"1T":$("#"+b).1f("1T",6(a){$("#"+E).1H("1T")});1i}}}};3 3V=6(){3 a=P("2W");$("#"+E).3g("<1z 1u=\'"+C.1R+"\' 1k=\'1c:3m;42:43;1m:2P;\' 1d=\'"+a+"\'></1z>");$("#"+E).56($("#"+a))};3 1X=6(a){3 b=P("2h");$("#"+b).1L(a)};3 3n=6(w){3 a=w;3 b=P("1g");3 c=$("#"+b+" a:2a");3 d=c.18;3 e=$("#"+b+" a:2a").1j($("#"+b+" a.15:2a"));3 f;2O(a){1h"3o":5(e<d-1){e++;f=c[e]};1i;1h"44":5(e<d&&e>0){e--;f=c[e]};1i};5(1e(f)=="14"){11 10};$("#"+b+" a."+C.15).1M(C.15);$(f).1G(C.15);3 g=f.1d;5(!A){3 h=(u.1O==10)?H[g].1t:$("#"+g).1L();1X(h);2s(H[g].1j)};5(a=="3o"){5(2p(($("#"+g).1m().1D+$("#"+g).1c()))>=2p($("#"+b).1c())){$("#"+b).2t(($("#"+b).2t())+$("#"+g).1c()+$("#"+g).1c())}}19{5(2p(($("#"+g).1m().1D+$("#"+g).1c()))<=0){$("#"+b).2t(($("#"+b).2t()-$("#"+b).1c())-$("#"+g).1c())}}};3 3j=6(){3n("3o")};3 3k=6(){3n("44")};3 2s=6(i){5(u.1J!=10){3 a=P("2h");3 b=(1e(i)=="14")?O(E).1n:i;3 c=O(E).1E[b].3K;5(c.18>0){3 d=P("1g");3 e=$("#"+d+" a."+c).1a("1d");3 f=$("#"+e).12("1Y-2j");3 g=$("#"+e).12("1Y-1m");5(g==14){g=$("#"+e).12("1Y-1m-x")+" "+$("#"+e).12("1Y-1m-y")};3 h=$("#"+e).12("2q-45");5(f!=14){$("#"+a).2u("."+C.1w).2i(\'1k\',"1Y:"+f)};5(g!=14){$("#"+a).2u("."+C.1w).12(\'1Y-1m\',g)};5(h!=14){$("#"+a).2u("."+C.1w).12(\'2q-45\',h)};$("#"+a).2u("."+C.1w).12(\'1Y-47\',\'57-47\');$("#"+a).2u("."+C.1w).12(\'2q-3U\',\'58\')}}};3 28=6(){3 a=P("1g");3 b=$("#"+a+" a."+C.15);5(b.18==1){3 c=$("#"+a+" a."+C.15).1t();3 d=$("#"+a+" a."+C.15).1a("1d");5(d!=14){3 e=H[d].2k;O(E).1n=H[d].1j};5(u.1O&&u.1J!=10)2s()}19 5(b.18>1){1C(3 i=0;i<b.18;i++){3 d=$(b[i]).1a("1d");3 f=H[d].1j;O(E).1E[f].15="15"}};3 g=O(E).1n;x.1Z["1n"]=g};3 2c=6(a){5($("#"+E).1a("59"+a)!=14){11 9};3 b=$("#"+E).3p("5a");5(b&&b[a]){11 9};11 10};3 3q=6(a){$("#"+E).2C();$("#"+E)[0].33();28();$(1o).1F("1s",2Q);$(1o).1F("1s",3q)};3 48=6(){3 a=P("1g");5(2c(\'34\')==9){3 b=H[$("#"+a+" a.15").1a("1d")].1t;5($.49(y)!==$.49(b)&&y!==""){$("#"+E).1H("34")}};5(2c(\'1s\')==9){$("#"+E).1H("1s")};5(2c(\'33\')==9){$(1o).1f("1s",3q)};11 10};3 3i=6(a){3 b=P("2z");5(a==1)$("#"+b).12({4a:\'0 5b%\'});19 $("#"+b).12({4a:\'0 0\'})};3 4b=6(){1C(3 i 2l O(E)){5(1e(O(E)[i])!==\'6\'&&1e(O(E)[i])!=="14"&&1e(O(E)[i])!=="1r"){x.1I(i,O(E)[i],9)}}};3 4c=6(a,b){5(Z(b)!=-1){O(E)[a]=b;3 c=P("1g");$("#"+c+" a."+C.15).1M(C.15);$("#"+Z(b).1d).1G(C.15);3 d=Z(O(E).1n).1L;1X(d)}};3 4d=6(i,a){5(a==\'d\'){1C(3 b 2l H){5(H[b].1j==i){5c H[b];1i}}};3 c=0;1C(3 b 2l H){H[b].1j=c;c++}};3 2R=6(){3 a=P("1g");3 b=P("1Q");3 c=$("#"+b).5d();3 d=$("#"+b).1c();3 e=$(4e).1c();3 f=$(4e).2t();3 g=$("#"+a).1c();3 h={1P:u.1P,1D:(d)+"1v",1q:"2d"};3 i=u.3E;3 j=10;3 k=C.2B;$("#"+a).1M(C.2B);$("#"+a).1M(C.2A);5((e+f)<3l.5e(g+d+c.1D)){3 l=g;h={1P:u.1P,1D:"-"+l+"1v",1q:"2d"};i="2e";j=9;k=C.2A};11{3r:j,4f:i,12:h,2J:k}};3 3s=6(){5(x.1U["4g"]!=1r){24(x.1U["4g"])(x)}};3 3t=6(){48();5(x.1U["4h"]!=1r){24(x.1U["4h"])(x)}};3 2N=6(a){3 b=P("1g");3 c=a.3Y;5(c==8){a.26();a.2r();N=(N.18==0)?"":N.3R(0,N.18-1)};2O(c){1h 39:1h 40:a.26();a.2r();3j();1i;1h 37:1h 38:a.26();a.2r();3k();1i;1h 27:1h 13:x.2n();28();1i;4i:5(c>46){N+=5f.5g(c)};3 d=T(N);5(d!=-1){$("#"+b).12({1c:\'5h\'});$("#"+b+" a").2o();$(d).2e();3 e=2R();$("#"+b).12(e.12);$("#"+b).12({1q:\'2m\'})}19{$("#"+b+" a").2e();$("#"+b).12({1c:K+\'1v\'})};1i};5(2c("22")==9){O(E).5i()};11 10};3 2Q=6(a){5(41()==10){x.2n()};11 10};3 3u=6(a){5($("#"+E).1a("4j")!=14){O(E).4j()};11 10};1b.3Z=6(){5((x.2f("1l",9)==9)||(x.2f("1E",9).18==0))11;3 a=P("1g");5(1N!=""&&a!=1N){$("#"+1N).4k("3v");$("#"+1N).12({1P:\'0\'})};5($("#"+a).12("1q")=="2d"){y=H[$("#"+a+" a.15").1a("1d")].1t;N="";K=$("#"+a).1c();$("#"+a+" a").2e();$(1o).1f("22",2N);$(1o).1f("35",3u);$(1o).1f("1s",2Q);3 b=2R();$("#"+a).12(b.12);5(b.3r==9){$("#"+a).12({1q:\'2m\'});$("#"+a).1G(b.2J);3s()}19{$("#"+a)[b.4f]("3v",6(){$("#"+a).1G(b.2J);3s()})};5(a!=1N){1N=a}}};1b.2n=6(){3 b=P("1g");5(!$("#"+b).4l(":2a")||L)11;L=9;5($("#"+b).12("1q")=="2d"){11 10};3 c=$("#"+P("1K")).1m().1D;3 d=2R();J=10;5(d.3r==9){$("#"+b).5j({1c:0,1D:c},6(){$("#"+b).12({1c:K+\'1v\',1q:\'2d\'});3t();L=10})}19{$("#"+b).4k("3v",6(a){3t();$("#"+b).12({1P:\'0\'});$("#"+b).12({1c:K+\'1v\'});L=10})};2s();$(1o).1F("22",2N);$(1o).1F("35",3u);$(1o).1F("1s",2Q)};1b.1n=6(i){5(1e(i)=="14"){11 x.2f("1n")}19{x.1I("1n",i)}};1b.4m=6(a){5(1e(a)=="14"||a==9){$("."+C.1R).5k("1k")}19{$("."+C.1R).2i("1k","1c:3m;42:43;1m:2P")}};1b.1I=6(a,b,c){5(1e a=="14"||1e b=="14")11 10;x.1Z[a]=b;5(c!=9){2O(a){1h"1n":4c(a,b);1i;1h"1l":x.1l(b,9);1i;1h"1x":O(E)[a]=b;A=($(v).1a("1B")>0||$(v).1a("1x")==9)?9:10;5(A){3 d=$("#"+E).1c();3 f=P("1g");$("#"+f).12("1c",d+"1v");3 g=P("1K");$("#"+g).2o();3 f=P("1g");$("#"+f).12({1q:\'2m\',1m:\'2K\'});X()};1i;1h"1B":O(E)[a]=b;5(b==0){O(E).1x=10};A=($(v).1a("1B")>0||$(v).1a("1x")==9)?9:10;5(b==0){3 g=P("1K");$("#"+g).2e();3 f=P("1g");$("#"+f).12({1q:\'2d\',1m:\'2P\'});3 h="";5(O(E).1n>=0){3 i=Z(O(E).1n);h=i.1L;3f($("#"+i.1d))};1X(h)}19{3 g=P("1K");$("#"+g).2o();3 f=P("1g");$("#"+f).12({1q:\'2m\',1m:\'2K\'})};1i;4i:4n{O(E)[a]=b}4o(e){};1i}}};1b.2f=6(a,b){5(a==14&&b==14){11 x.1Z};5(a!=14&&b==14){11(x.1Z[a]!=14)?x.1Z[a]:1r};5(a!=14&&b!=14){11 O(E)[a]}};1b.2a=6(a){3 b=P("1Q");5(a==9){$("#"+b).2e()}19 5(a==10){$("#"+b).2o()}19{11 $("#"+b).12("1q")}};1b.5l=6(a,b){3 c=a;3 d=c.1t;3 e=(c.2k==14||c.2k==1r)?d:c.2k;3 f=(c["1p"]==14||c["1p"]==1r)?\'\':c["1p"];3 i=(b==14||b==1r)?O(E).1E.18:b;O(E).1E[i]=21 5m(d,e);5(f!=\'\')O(E).1E[i]["1p"]=f;3 g=Z(i);5(g!=-1){3 h=S(O(E).1E[i],i,"","");$("#"+g.1d).1L(h)}19{3 h=S(O(E).1E[i],i,"","");3 j=P("1g");$("#"+j).5n(h);X()}};1b.2L=6(i){O(E).2L(i);5((Z(i))!=-1){$("#"+Z(i).1d).2L();4d(i,\'d\')};5(O(E).18==0){1X("")}19{3 a=Z(O(E).1n).1L;1X(a)};x.1I("1n",O(E).1n)};1b.1l=6(a,b){O(E).1l=a;3 c=P("1Q");5(a==9){$("#"+c).12("2M",C.1l);x.2n()}19 5(a==10){$("#"+c).12("2M",1)};5(b!=9){x.1I("1l",a)}};1b.3w=6(){11(O(E).3w==14)?1r:O(E).3w};1b.3x=6(){5(2v.18==1){11 O(E).3x(2v[0])}19 5(2v.18==2){11 O(E).3x(2v[0],2v[1])}19{5o{5p:"5q 1j 4l 5r!"}}};1b.4p=6(a){11 O(E).4p(a)};1b.1x=6(a){5(1e(a)=="14"){11 x.2f("1x")}19{x.1I("1x",a)}};1b.1B=6(a){5(1e(a)=="14"){11 x.2f("1B")}19{x.1I("1B",a)}};1b.5s=6(a,b){x.1U[a]=b};1b.5t=6(a){24(x.1U[a])(x)};1b.5u=6(r){5(1e r=="14"||r==0){11 10};3 a=P("1g");3 b=$("#"+a+" a:3h").1c();3 c=(b==0)?u.2S:b;3 d=r*c;$("#"+a).12("1c",d+"1v")};3 4q=6(){x.1I("3y",$.1V.3y);x.1I("3z",$.1V.3z)};3 4r=6(){Y();4b();4q();5(u.2T!=\'\'){24(u.2T)(x)}};4r()};$.1V={3y:\'2.38.4\',3z:"5v 5w",3I:20,4m:6(v){5(v==9){$(".1R").12({1c:\'5x\',1m:\'2K\'})}19{$(".1R").12({1c:\'3m\',1m:\'2P\'})}},5y:6(a,b){11 $(a).1V(b).3p("2g")}};$.3A.3C({1V:6(b){11 1b.3e(6(){3 a=21 3B(1b,b);$(1b).3p(\'2g\',a)})}});5(1e($.3A.1a)==\'14\'){$.3A.1a=6(w,v){5(1e v=="14"){11 $(1b).2i(w)};4n{$(1b).2i(w,v)}4o(e){}}}})(5z);',62,346,'|||var||if|function|||true|||||||||||||||||||||||||||||||||||||||||||||||||||||false|return|css||undefined|selected|||length|else|prop|this|height|id|typeof|bind|postChildID|case|break|index|style|disabled|position|selectedIndex|document|title|display|null|mouseup|text|class|px|ddTitleText|multiple|span|div|mouseover|size|for|top|options|unbind|addClass|trigger|set|useSprite|postTitleID|html|removeClass|bB|showIcon|zIndex|postID|ddOutOfVision|click|mouseout|onActions|msDropDown|img|bJ|background|ddProp||new|keydown||eval|sDiv|preventDefault||bO|bF|visible|oldIndex|bP|none|show|get|dd|postTitleTextID|attr|image|value|in|block|close|hide|parseInt|padding|stopPropagation|bN|scrollTop|find|arguments|visibleRows|keyboardAction|currentKey|postArrowID|borderTop|noBorderTop|focus|dblclick|mousedown|mousemove|src|align|absmiddle|border|relative|remove|opacity|bZ|switch|absolute|ca|bW|rowHeight|onInit|jsonTitle|insideWindow|postElementHolder|postAID|postOPTAID|ddTitle||arrow|ddChild|blur|change|keyup|option||||opt|_|enabled|toLowerCase|each|bD|after|first|bS|bL|bM|Math|0px|bK|next|data|bQ|opp|bX|bY|cb|fast|form|item|version|author|fn|bC|extend|mainCSS|animStyle|Object|postInputhidden|actions|counter|children|className|RegExp|test|postHTML|href|javascript|void|substr|font|width|bottom|bI|bE|bH|keyCode|open||bG|overflow|hidden|previous|left||repeat|bR|trim|backgroundPosition|bT|bU|bV|window|ani|onOpen|onClose|default|onkeyup|slideUp|is|debug|try|catch|namedItem|cc|cd|120|9999|slideDown|_msddHolder|_msdd|_title|_titletext|_child|_msa|_msopta|postInputID|_msinput|_arrow|_inp|keypress|tabindex|msdrpdd|getElementById|cssText|val|nodeName|toString|optgroup|opta|weight|bold|italic|clear|both|label|1px|solid|c3c3c3|outerWidth|toggleClass|min|max|refresh|split|mouseenter|appendTo|no|2px|on|events|100|delete|offset|floor|String|fromCharCode|auto|onkeydown|animate|removeAttr|add|Option|append|throw|message|An|required|addMyEvent|fireEvent|showRows|Marghoob|Suleman|20px|create|jQuery'.split('|'),0,{}));
var hasTouch='ontouchstart'in document.documentElement,startEvent=hasTouch?'touchstart':'mousedown',moveEvent=hasTouch?'touchmove':'mousemove',endEvent=hasTouch?'touchend':'mouseup';if(hasTouch){$.each("touchstart touchmove touchend".split(" "),function(i,name){jQuery.event.fixHooks[name]=jQuery.event.mouseHooks});}jQuery.tableDnD={currentTable:null,dragObject:null,mouseOffset:null,oldY:0,build:function(options){this.each(function(){this.tableDnDConfig=jQuery.extend({onDragStyle:null,onDropStyle:null,onDragClass:"tDnD_whileDrag",onDrop:null,onDragStart:null,scrollAmount:5,serializeRegexp:/[^\-]*$/,serializeParamName:null,dragHandle:null},options||{});jQuery.tableDnD.makeDraggable(this)});return this},makeDraggable:function(table){var config=table.tableDnDConfig;if(config.dragHandle){var cells=jQuery(table.tableDnDConfig.dragHandle,table);cells.each(function(){jQuery(this).bind(startEvent,function(ev){jQuery.tableDnD.initialiseDrag(jQuery(this).parents('tr')[0],table,this,ev,config);return false})})}else{var rows=jQuery("tr",table);rows.each(function(){var row=jQuery(this);if(!row.hasClass("nodrag")){row.bind(startEvent,function(ev){if(ev.target.tagName=="TD"){jQuery.tableDnD.initialiseDrag(this,table,this,ev,config);return false}}).css("cursor","move")}})}},initialiseDrag:function(dragObject,table,target,evnt,config){jQuery.tableDnD.dragObject=dragObject;jQuery.tableDnD.currentTable=table;jQuery.tableDnD.mouseOffset=jQuery.tableDnD.getMouseOffset(target,evnt);jQuery.tableDnD.originalOrder=jQuery.tableDnD.serialize();jQuery(document).bind(moveEvent,jQuery.tableDnD.mousemove).bind(endEvent,jQuery.tableDnD.mouseup);if(config.onDragStart){config.onDragStart(table,target)}},updateTables:function(){this.each(function(){if(this.tableDnDConfig){jQuery.tableDnD.makeDraggable(this)}})},mouseCoords:function(ev){if(ev.pageX||ev.pageY){return{x:ev.pageX,y:ev.pageY}}return{x:ev.clientX+document.body.scrollLeft-document.body.clientLeft,y:ev.clientY+document.body.scrollTop-document.body.clientTop}},getMouseOffset:function(target,ev){ev=ev||window.event;var docPos=this.getPosition(target);var mousePos=this.mouseCoords(ev);return{x:mousePos.x-docPos.x,y:mousePos.y-docPos.y}},getPosition:function(e){var left=0;var top=0;if(e.offsetHeight==0){e=e.firstChild}while(e.offsetParent){left+=e.offsetLeft;top+=e.offsetTop;e=e.offsetParent}left+=e.offsetLeft;top+=e.offsetTop;return{x:left,y:top}},mousemove:function(ev){if(jQuery.tableDnD.dragObject==null){return}if(ev.type=='touchmove'){event.preventDefault()}var dragObj=jQuery(jQuery.tableDnD.dragObject);var config=jQuery.tableDnD.currentTable.tableDnDConfig;var mousePos=jQuery.tableDnD.mouseCoords(ev);var y=mousePos.y-jQuery.tableDnD.mouseOffset.y;var yOffset=window.pageYOffset;if(document.all){if(typeof document.compatMode!='undefined'&&document.compatMode!='BackCompat'){yOffset=document.documentElement.scrollTop}else if(typeof document.body!='undefined'){yOffset=document.body.scrollTop}}if(mousePos.y-yOffset<config.scrollAmount){window.scrollBy(0,-config.scrollAmount)}else{var windowHeight=window.innerHeight?window.innerHeight:document.documentElement.clientHeight?document.documentElement.clientHeight:document.body.clientHeight;if(windowHeight-(mousePos.y-yOffset)<config.scrollAmount){window.scrollBy(0,config.scrollAmount)}}if(y!=jQuery.tableDnD.oldY){var movingDown=y>jQuery.tableDnD.oldY;jQuery.tableDnD.oldY=y;if(config.onDragClass){dragObj.addClass(config.onDragClass)}else{dragObj.css(config.onDragStyle)}var currentRow=jQuery.tableDnD.findDropTargetRow(dragObj,y);if(currentRow){if(movingDown&&jQuery.tableDnD.dragObject!=currentRow){jQuery.tableDnD.dragObject.parentNode.insertBefore(jQuery.tableDnD.dragObject,currentRow.nextSibling)}else if(!movingDown&&jQuery.tableDnD.dragObject!=currentRow){jQuery.tableDnD.dragObject.parentNode.insertBefore(jQuery.tableDnD.dragObject,currentRow)}}}return false},findDropTargetRow:function(draggedRow,y){var rows=jQuery.tableDnD.currentTable.rows;for(var i=0;i<rows.length;i++){var row=rows[i];var rowY=this.getPosition(row).y;var rowHeight=parseInt(row.offsetHeight)/2;if(row.offsetHeight==0){rowY=this.getPosition(row.firstChild).y;rowHeight=parseInt(row.firstChild.offsetHeight)/2}if((y>rowY-rowHeight)&&(y<(rowY+rowHeight))){if(row==draggedRow){return null}var config=jQuery.tableDnD.currentTable.tableDnDConfig;if(config.onAllowDrop){if(config.onAllowDrop(draggedRow,row)){return row}else{return null}}else{var nodrop=jQuery(row).hasClass("nodrop");if(!nodrop){return row}else{return null}}return row}}return null},mouseup:function(e){if(jQuery.tableDnD.currentTable&&jQuery.tableDnD.dragObject){jQuery(document).unbind(moveEvent,jQuery.tableDnD.mousemove).unbind(endEvent,jQuery.tableDnD.mouseup);var droppedRow=jQuery.tableDnD.dragObject;var config=jQuery.tableDnD.currentTable.tableDnDConfig;if(config.onDragClass){jQuery(droppedRow).removeClass(config.onDragClass)}else{jQuery(droppedRow).css(config.onDropStyle)}jQuery.tableDnD.dragObject=null;var newOrder=jQuery.tableDnD.serialize();if(config.onDrop&&(jQuery.tableDnD.originalOrder!=newOrder)){config.onDrop(jQuery.tableDnD.currentTable,droppedRow)}jQuery.tableDnD.currentTable=null}},serialize:function(){if(jQuery.tableDnD.currentTable){return jQuery.tableDnD.serializeTable(jQuery.tableDnD.currentTable)}else{return"Error: No Table id set, you need to set an id on your table and every row"}},serializeTable:function(table){var result="";var tableId=table.id;var rows=table.rows;for(var i=0;i<rows.length;i++){if(result.length>0)result+="&";var rowId=rows[i].id;if(rowId&&rowId&&table.tableDnDConfig&&table.tableDnDConfig.serializeRegexp){rowId=rowId.match(table.tableDnDConfig.serializeRegexp)[0]}result+=tableId+'[]='+rowId}return result},serializeTables:function(){var result="";this.each(function(){result+=jQuery.tableDnD.serializeTable(this)});return result}};jQuery.fn.extend({tableDnD:jQuery.tableDnD.build,tableDnDUpdate:jQuery.tableDnD.updateTables,tableDnDSerialize:jQuery.tableDnD.serializeTables});
/**
 * --------------------------------------------------------------------
 * jQuery customfileinput plugin
 * Author: Scott Jehl, scott@filamentgroup.com
 * Copyright (c) 2009 Filament Group 
 * licensed under MIT (filamentgroup.com/examples/mit-license.txt)
 * --------------------------------------------------------------------
 */
$.fn.customFileInput = function(){
	//apply events and styles for file input element
	var fileInput = $(this)
		.addClass('customfile-input') //add class for CSS
		.mouseover(function(){ upload.addClass('customfile-hover'); })
		.mouseout(function(){ upload.removeClass('customfile-hover'); })
		.focus(function(){
			upload.addClass('customfile-focus'); 
			fileInput.data('val', fileInput.val());
		})
		.blur(function(){ 
			upload.removeClass('customfile-focus');
			$(this).trigger('checkChange');
		 })
		 .bind('disable',function(){
		 	fileInput.attr('disabled',true);
			upload.addClass('customfile-disabled');
		})
		.bind('enable',function(){
			fileInput.removeAttr('disabled');
			upload.removeClass('customfile-disabled');
		})
		.bind('checkChange', function(){
			if(fileInput.val() && fileInput.val() != fileInput.data('val')){
				fileInput.trigger('change');
			}
		})
		.bind('change',function(){
			//get file name
			var fileName = $(this).val().split(/\\/).pop();
			//get file extension
			var fileExt = 'customfile-ext-' + fileName.split('.').pop().toLowerCase();
			//update the feedback
			uploadFeedback
				.text(fileName) //set feedback text to filename
				.removeClass(uploadFeedback.data('fileExt') || '') //remove any existing file extension class
				.addClass(fileExt) //add file extension class
				.data('fileExt', fileExt) //store file extension for class removal on next change
				.addClass('customfile-feedback-populated'); //add class to show populated state
			//change text of button	
			uploadButton.text('Change');	
		})
		.click(function(){ //for IE and Opera, make sure change fires after choosing a file, using an async callback
			fileInput.data('val', fileInput.val());
			setTimeout(function(){
				fileInput.trigger('checkChange');
			},100);
		});
		
	//create custom control container
	var upload = $('<div class="customfile"></div>');
	//create custom control button
	var uploadButton = $('<span class="customfile-button" aria-hidden="true">Browse</span>').appendTo(upload);
	//create custom control feedback
	var uploadFeedback = $('<span class="customfile-feedback" aria-hidden="true">No file selected...</span>').appendTo(upload);
	
	//match disabled state
	if(fileInput.is('[disabled]')){
		fileInput.trigger('disable');
	}
		
	
	//on mousemove, keep file input under the cursor to steal click
	upload
		.mousemove(function(e){
			fileInput.css({
				'left': e.pageX - upload.offset().left - fileInput.outerWidth() + 20, //position right side 20px right of cursor X)
				'top': e.pageY - upload.offset().top - $(window).scrollTop() - 3
			});	
		})
		.insertAfter(fileInput); //insert after the input
	
	fileInput.appendTo(upload);
		
	//return jQuery
	return $(this);
};

/* NicEdit - Micro Inline WYSIWYG
 * Copyright 2007-2008 Brian Kirchoff
 *
 * NicEdit is distributed under the terms of the MIT license
 * For more information visit http://nicedit.com/
 * Do not remove this copyright message
 */
var bkExtend=function(){var A=arguments;if(A.length==1){A=[this,A[0]]}for(var B in A[1]){A[0][B]=A[1][B]}return A[0]};function bkClass(){}bkClass.prototype.construct=function(){};bkClass.extend=function(C){var A=function(){if(arguments[0]!==bkClass){return this.construct.apply(this,arguments)}};var B=new this(bkClass);bkExtend(B,C);A.prototype=B;A.extend=this.extend;return A};var bkElement=bkClass.extend({construct:function(B,A){if(typeof (B)=="string"){B=(A||document).createElement(B)}B=$BK(B);return B},appendTo:function(A){A.appendChild(this);return this},appendBefore:function(A){A.parentNode.insertBefore(this,A);return this},addEvent:function(B,A){bkLib.addEvent(this,B,A);return this},setContent:function(A){this.innerHTML=A;return this},pos:function(){var C=curtop=0;var B=obj=this;if(obj.offsetParent){do{C+=obj.offsetLeft;curtop+=obj.offsetTop}while(obj=obj.offsetParent)}var A=(!window.opera)?parseInt(this.getStyle("border-width")||this.style.border)||0:0;return[C+A,curtop+A+this.offsetHeight]},noSelect:function(){bkLib.noSelect(this);return this},parentTag:function(A){var B=this;do{if(B&&B.nodeName&&B.nodeName.toUpperCase()==A){return B}B=B.parentNode}while(B);return false},hasClass:function(A){return this.className.match(new RegExp("(\\s|^)nicEdit-"+A+"(\\s|$)"))},addClass:function(A){if(!this.hasClass(A)){this.className+=" nicEdit-"+A}return this},removeClass:function(A){if(this.hasClass(A)){this.className=this.className.replace(new RegExp("(\\s|^)nicEdit-"+A+"(\\s|$)")," ")}return this},setStyle:function(A){var B=this.style;for(var C in A){switch(C){case"float":B.cssFloat=B.styleFloat=A[C];break;case"opacity":B.opacity=A[C];B.filter="alpha(opacity="+Math.round(A[C]*100)+")";break;case"className":this.className=A[C];break;default:B[C]=A[C]}}return this},getStyle:function(A,C){var B=(!C)?document.defaultView:C;if(this.nodeType==1){return(B&&B.getComputedStyle)?B.getComputedStyle(this,null).getPropertyValue(A):this.currentStyle[bkLib.camelize(A)]}},remove:function(){this.parentNode.removeChild(this);return this},setAttributes:function(A){for(var B in A){this[B]=A[B]}return this}});var bkLib={isMSIE:(navigator.appVersion.indexOf("MSIE")!=-1),addEvent:function(C,B,A){(C.addEventListener)?C.addEventListener(B,A,false):C.attachEvent("on"+B,A)},toArray:function(C){var B=C.length,A=new Array(B);while(B--){A[B]=C[B]}return A},noSelect:function(B){if(B.setAttribute&&B.nodeName.toLowerCase()!="input"&&B.nodeName.toLowerCase()!="textarea"){B.setAttribute("unselectable","on")}for(var A=0;A<B.childNodes.length;A++){bkLib.noSelect(B.childNodes[A])}},camelize:function(A){return A.replace(/\-(.)/g,function(B,C){return C.toUpperCase()})},inArray:function(A,B){return(bkLib.search(A,B)!=null)},search:function(A,C){for(var B=0;B<A.length;B++){if(A[B]==C){return B}}return null},cancelEvent:function(A){A=A||window.event;if(A.preventDefault&&A.stopPropagation){A.preventDefault();A.stopPropagation()}return false},domLoad:[],domLoaded:function(){if(arguments.callee.done){return }arguments.callee.done=true;for(i=0;i<bkLib.domLoad.length;i++){bkLib.domLoad[i]()}},onDomLoaded:function(A){this.domLoad.push(A);if(document.addEventListener){document.addEventListener("DOMContentLoaded",bkLib.domLoaded,null)}else{if(bkLib.isMSIE){document.write("<style>.nicEdit-main p { margin: 0; }</style><script id=__ie_onload defer "+((location.protocol=="https:")?"src='javascript:void(0)'":"src=//0")+"><\/script>");$BK("__ie_onload").onreadystatechange=function(){if(this.readyState=="complete"){bkLib.domLoaded()}}}}window.onload=bkLib.domLoaded}};function $BK(A){if(typeof (A)=="string"){A=document.getElementById(A)}return(A&&!A.appendTo)?bkExtend(A,bkElement.prototype):A}var bkEvent={addEvent:function(A,B){if(B){this.eventList=this.eventList||{};this.eventList[A]=this.eventList[A]||[];this.eventList[A].push(B)}return this},fireEvent:function(){var A=bkLib.toArray(arguments),C=A.shift();if(this.eventList&&this.eventList[C]){for(var B=0;B<this.eventList[C].length;B++){this.eventList[C][B].apply(this,A)}}}};function __(A){return A}Function.prototype.closure=function(){var A=this,B=bkLib.toArray(arguments),C=B.shift();return function(){if(typeof (bkLib)!="undefined"){return A.apply(C,B.concat(bkLib.toArray(arguments)))}}};Function.prototype.closureListener=function(){var A=this,C=bkLib.toArray(arguments),B=C.shift();return function(E){E=E||window.event;if(E.target){var D=E.target}else{var D=E.srcElement}return A.apply(B,[E,D].concat(C))}};



var nicEditorConfig = bkClass.extend({
	buttons : {
		'bold' : {name : __('Click to Bold'), command : 'Bold', tags : ['B','STRONG'], css : {'font-weight' : 'bold'}, key : 'b'},
		'italic' : {name : __('Click to Italic'), command : 'Italic', tags : ['EM','I'], css : {'font-style' : 'italic'}, key : 'i'},
		'underline' : {name : __('Click to Underline'), command : 'Underline', tags : ['U'], css : {'text-decoration' : 'underline'}, key : 'u'},
		'left' : {name : __('Left Align'), command : 'justifyleft', noActive : true},
		'center' : {name : __('Center Align'), command : 'justifycenter', noActive : true},
		'right' : {name : __('Right Align'), command : 'justifyright', noActive : true},
		'justify' : {name : __('Justify Align'), command : 'justifyfull', noActive : true},
		'ol' : {name : __('Insert Ordered List'), command : 'insertorderedlist', tags : ['OL']},
		'ul' : 	{name : __('Insert Unordered List'), command : 'insertunorderedlist', tags : ['UL']},
		'subscript' : {name : __('Click to Subscript'), command : 'subscript', tags : ['SUB']},
		'superscript' : {name : __('Click to Superscript'), command : 'superscript', tags : ['SUP']},
		'strikethrough' : {name : __('Click to Strike Through'), command : 'strikeThrough', css : {'text-decoration' : 'line-through'}},
		'removeformat' : {name : __('Remove Formatting'), command : 'removeformat', noActive : true},
		'indent' : {name : __('Indent Text'), command : 'indent', noActive : true},
		'outdent' : {name : __('Remove Indent'), command : 'outdent', noActive : true},
		'hr' : {name : __('Horizontal Rule'), command : 'insertHorizontalRule', noActive : true}
	},
	iconsPath : '../nicEditorIcons.gif',
	buttonList : ['save','bold','italic','underline','left','center','right','justify','ol','ul','fontSize','fontFamily','fontFormat','indent','outdent','image','upload','link','unlink','forecolor','bgcolor'],
	iconList : {"bold":1,"center":2,"hr":3,"indent":4,"italic":5,"justify":6,"left":7,"ol":8,"outdent":9,"removeformat":10,"right":11,"save":12,"strikethrough":13,"subscript":14,"superscript":15,"ul":16,"underline":17,"close":18,"arrow":19}
	
});
;
var nicEditors={nicPlugins:[],editors:[],registerPlugin:function(B,A){this.nicPlugins.push({p:B,o:A})},allTextAreas:function(C){var A=document.getElementsByTagName("textarea");for(var B=0;B<A.length;B++){nicEditors.editors.push(new nicEditor(C).panelInstance(A[B]))}return nicEditors.editors},findEditor:function(C){var B=nicEditors.editors;for(var A=0;A<B.length;A++){if(B[A].instanceById(C)){return B[A].instanceById(C)}}}};var nicEditor=bkClass.extend({construct:function(C){this.options=new nicEditorConfig();bkExtend(this.options,C);this.nicInstances=new Array();this.loadedPlugins=new Array();var A=nicEditors.nicPlugins;for(var B=0;B<A.length;B++){this.loadedPlugins.push(new A[B].p(this,A[B].o))}nicEditors.editors.push(this);bkLib.addEvent(document.body,"mousedown",this.selectCheck.closureListener(this))},panelInstance:function(B,C){B=this.checkReplace($BK(B));var A=new bkElement("DIV").setStyle({width:(parseInt(B.getStyle("width"))||B.clientWidth)+"px"}).appendBefore(B);this.setPanel(A);return this.addInstance(B,C)},checkReplace:function(B){var A=nicEditors.findEditor(B);if(A){A.removeInstance(B);A.removePanel()}return B},addInstance:function(B,C){B=this.checkReplace($BK(B));if(B.contentEditable||!!window.opera){var A=new nicEditorInstance(B,C,this)}else{var A=new nicEditorIFrameInstance(B,C,this)}this.nicInstances.push(A);return this},removeInstance:function(C){C=$BK(C);var B=this.nicInstances;for(var A=0;A<B.length;A++){if(B[A].e==C){B[A].remove();this.nicInstances.splice(A,1)}}},removePanel:function(A){if(this.nicPanel){this.nicPanel.remove();this.nicPanel=null}},instanceById:function(C){C=$BK(C);var B=this.nicInstances;for(var A=0;A<B.length;A++){if(B[A].e==C){return B[A]}}},setPanel:function(A){this.nicPanel=new nicEditorPanel($BK(A),this.options,this);this.fireEvent("panel",this.nicPanel);return this},nicCommand:function(B,A){if(this.selectedInstance){this.selectedInstance.nicCommand(B,A)}},getIcon:function(D,A){var C=this.options.iconList[D];var B=(A.iconFiles)?A.iconFiles[D]:"";return{backgroundImage:"url('"+((C)?this.options.iconsPath:B)+"')",backgroundPosition:((C)?((C-1)*-18):0)+"px 0px"}},selectCheck:function(C,A){var B=false;do{if(A.className&&A.className.indexOf("nicEdit")!=-1){return false}}while(A=A.parentNode);this.fireEvent("blur",this.selectedInstance,A);this.lastSelectedInstance=this.selectedInstance;this.selectedInstance=null;return false}});nicEditor=nicEditor.extend(bkEvent);
var nicEditorInstance=bkClass.extend({isSelected:false,construct:function(G,D,C){this.ne=C;this.elm=this.e=G;this.options=D||{};newX=parseInt(G.getStyle("width"))||G.clientWidth;newY=parseInt(G.getStyle("height"))||G.clientHeight;this.initialHeight=newY-8;var H=(G.nodeName.toLowerCase()=="textarea");if(H||this.options.hasPanel){var B=(bkLib.isMSIE&&!((typeof document.body.style.maxHeight!="undefined")&&document.compatMode=="CSS1Compat"));var E={width:newX+"px",border:"1px solid #ccc",borderTop:0,overflowY:"auto",overflowX:"hidden"};E[(B)?"height":"maxHeight"]=(this.ne.options.maxHeight)?this.ne.options.maxHeight+"px":null;this.editorContain=new bkElement("DIV").setStyle(E).appendBefore(G);var A=new bkElement("DIV").setStyle({width:(newX-8)+"px",margin:"4px",minHeight:newY+"px"}).addClass("main").appendTo(this.editorContain);G.setStyle({display:"none"});A.innerHTML=G.innerHTML;if(H){A.setContent(G.value);this.copyElm=G;var F=G.parentTag("FORM");if(F){bkLib.addEvent(F,"submit",this.saveContent.closure(this))}}A.setStyle((B)?{height:newY+"px"}:{overflow:"hidden"});this.elm=A}this.ne.addEvent("blur",this.blur.closure(this));this.init();this.blur()},init:function(){this.elm.setAttribute("contentEditable","true");if(this.getContent()==""){this.setContent("<br />")}this.instanceDoc=document.defaultView;this.elm.addEvent("mousedown",this.selected.closureListener(this)).addEvent("keypress",this.keyDown.closureListener(this)).addEvent("focus",this.selected.closure(this)).addEvent("blur",this.blur.closure(this)).addEvent("keyup",this.selected.closure(this));this.ne.fireEvent("add",this)},remove:function(){this.saveContent();if(this.copyElm||this.options.hasPanel){this.editorContain.remove();this.e.setStyle({display:"block"});this.ne.removePanel()}this.disable();this.ne.fireEvent("remove",this)},disable:function(){this.elm.setAttribute("contentEditable","false")},getSel:function(){return(window.getSelection)?window.getSelection():document.selection},getRng:function(){var A=this.getSel();if(!A||A.rangeCount===0){return }return(A.rangeCount>0)?A.getRangeAt(0):A.createRange()},selRng:function(A,B){if(window.getSelection){B.removeAllRanges();B.addRange(A)}else{A.select()}},selElm:function(){var C=this.getRng();if(!C){return }if(C.startContainer){var D=C.startContainer;if(C.cloneContents().childNodes.length==1){for(var B=0;B<D.childNodes.length;B++){var A=D.childNodes[B].ownerDocument.createRange();A.selectNode(D.childNodes[B]);if(C.compareBoundaryPoints(Range.START_TO_START,A)!=1&&C.compareBoundaryPoints(Range.END_TO_END,A)!=-1){return $BK(D.childNodes[B])}}}return $BK(D)}else{return $BK((this.getSel().type=="Control")?C.item(0):C.parentElement())}},saveRng:function(){this.savedRange=this.getRng();this.savedSel=this.getSel()},restoreRng:function(){if(this.savedRange){this.selRng(this.savedRange,this.savedSel)}},keyDown:function(B,A){if(B.ctrlKey){this.ne.fireEvent("key",this,B)}},selected:function(C,A){if(!A&&!(A=this.selElm)){A=this.selElm()}if(!C.ctrlKey){var B=this.ne.selectedInstance;if(B!=this){if(B){this.ne.fireEvent("blur",B,A)}this.ne.selectedInstance=this;this.ne.fireEvent("focus",B,A)}this.ne.fireEvent("selected",B,A);this.isFocused=true;this.elm.addClass("selected")}return false},blur:function(){this.isFocused=false;this.elm.removeClass("selected")},saveContent:function(){if(this.copyElm||this.options.hasPanel){this.ne.fireEvent("save",this);(this.copyElm)?this.copyElm.value=this.getContent():this.e.innerHTML=this.getContent()}},getElm:function(){return this.elm},getContent:function(){this.content=this.getElm().innerHTML;this.ne.fireEvent("get",this);return this.content},setContent:function(A){this.content=A;this.ne.fireEvent("set",this);this.elm.innerHTML=this.content},nicCommand:function(B,A){document.execCommand(B,false,A)}});
var nicEditorIFrameInstance=nicEditorInstance.extend({savedStyles:[],init:function(){var B=this.elm.innerHTML.replace(/^\s+|\s+$/g,"");this.elm.innerHTML="";(!B)?B="<br />":B;this.initialContent=B;this.elmFrame=new bkElement("iframe").setAttributes({src:"javascript:;",frameBorder:0,allowTransparency:"true",scrolling:"no"}).setStyle({height:"100px",width:"100%"}).addClass("frame").appendTo(this.elm);if(this.copyElm){this.elmFrame.setStyle({width:(this.elm.offsetWidth-4)+"px"})}var A=["font-size","font-family","font-weight","color"];for(itm in A){this.savedStyles[bkLib.camelize(itm)]=this.elm.getStyle(itm)}setTimeout(this.initFrame.closure(this),50)},disable:function(){this.elm.innerHTML=this.getContent()},initFrame:function(){var B=$BK(this.elmFrame.contentWindow.document);B.designMode="on";B.open();var A=this.ne.options.externalCSS;B.write("<html><head>"+((A)?'<link href="'+A+'" rel="stylesheet" type="text/css" />':"")+'</head><body id="nicEditContent" style="margin: 0 !important; background-color: transparent !important;">'+this.initialContent+"</body></html>");B.close();this.frameDoc=B;this.frameWin=$BK(this.elmFrame.contentWindow);this.frameContent=$BK(this.frameWin.document.body).setStyle(this.savedStyles);this.instanceDoc=this.frameWin.document.defaultView;this.heightUpdate();this.frameDoc.addEvent("mousedown",this.selected.closureListener(this)).addEvent("keyup",this.heightUpdate.closureListener(this)).addEvent("keydown",this.keyDown.closureListener(this)).addEvent("keyup",this.selected.closure(this));this.ne.fireEvent("add",this)},getElm:function(){return this.frameContent},setContent:function(A){this.content=A;this.ne.fireEvent("set",this);this.frameContent.innerHTML=this.content;this.heightUpdate()},getSel:function(){return(this.frameWin)?this.frameWin.getSelection():this.frameDoc.selection},heightUpdate:function(){this.elmFrame.style.height=Math.max(this.frameContent.offsetHeight,this.initialHeight)+"px"},nicCommand:function(B,A){this.frameDoc.execCommand(B,false,A);setTimeout(this.heightUpdate.closure(this),100)}});
var nicEditorPanel=bkClass.extend({construct:function(E,B,A){this.elm=E;this.options=B;this.ne=A;this.panelButtons=new Array();this.buttonList=bkExtend([],this.ne.options.buttonList);this.panelContain=new bkElement("DIV").setStyle({overflow:"hidden",width:"100%",border:"1px solid #cccccc",backgroundColor:"#efefef"}).addClass("panelContain");this.panelElm=new bkElement("DIV").setStyle({margin:"2px",marginTop:"0px",zoom:1,overflow:"hidden"}).addClass("panel").appendTo(this.panelContain);this.panelContain.appendTo(E);var C=this.ne.options;var D=C.buttons;for(button in D){this.addButton(button,C,true)}this.reorder();E.noSelect()},addButton:function(buttonName,options,noOrder){var button=options.buttons[buttonName];var type=(button.type)?eval("(typeof("+button.type+') == "undefined") ? null : '+button.type+";"):nicEditorButton;var hasButton=bkLib.inArray(this.buttonList,buttonName);if(type&&(hasButton||this.ne.options.fullPanel)){this.panelButtons.push(new type(this.panelElm,buttonName,options,this.ne));if(!hasButton){this.buttonList.push(buttonName)}}},findButton:function(B){for(var A=0;A<this.panelButtons.length;A++){if(this.panelButtons[A].name==B){return this.panelButtons[A]}}},reorder:function(){var C=this.buttonList;for(var B=0;B<C.length;B++){var A=this.findButton(C[B]);if(A){this.panelElm.appendChild(A.margin)}}},remove:function(){this.elm.remove()}});
var nicEditorButton=bkClass.extend({construct:function(D,A,C,B){this.options=C.buttons[A];this.name=A;this.ne=B;this.elm=D;this.margin=new bkElement("DIV").setStyle({"float":"left",marginTop:"2px"}).appendTo(D);this.contain=new bkElement("DIV").setStyle({width:"20px",height:"20px"}).addClass("buttonContain").appendTo(this.margin);this.border=new bkElement("DIV").setStyle({backgroundColor:"#efefef",border:"1px solid #efefef"}).appendTo(this.contain);this.button=new bkElement("DIV").setStyle({width:"18px",height:"18px",overflow:"hidden",zoom:1,cursor:"pointer"}).addClass("button").setStyle(this.ne.getIcon(A,C)).appendTo(this.border);this.button.addEvent("mouseover",this.hoverOn.closure(this)).addEvent("mouseout",this.hoverOff.closure(this)).addEvent("mousedown",this.mouseClick.closure(this)).noSelect();if(!window.opera){this.button.onmousedown=this.button.onclick=bkLib.cancelEvent}B.addEvent("selected",this.enable.closure(this)).addEvent("blur",this.disable.closure(this)).addEvent("key",this.key.closure(this));this.disable();this.init()},init:function(){},hide:function(){this.contain.setStyle({display:"none"})},updateState:function(){if(this.isDisabled){this.setBg()}else{if(this.isHover){this.setBg("hover")}else{if(this.isActive){this.setBg("active")}else{this.setBg()}}}},setBg:function(A){switch(A){case"hover":var B={border:"1px solid #666",backgroundColor:"#ddd"};break;case"active":var B={border:"1px solid #666",backgroundColor:"#ccc"};break;default:var B={border:"1px solid #efefef",backgroundColor:"#efefef"}}this.border.setStyle(B).addClass("button-"+A)},checkNodes:function(A){var B=A;do{if(this.options.tags&&bkLib.inArray(this.options.tags,B.nodeName)){this.activate();return true}}while(B=B.parentNode&&B.className!="nicEdit");B=$BK(A);while(B.nodeType==3){B=$BK(B.parentNode)}if(this.options.css){for(itm in this.options.css){if(B.getStyle(itm,this.ne.selectedInstance.instanceDoc)==this.options.css[itm]){this.activate();return true}}}this.deactivate();return false},activate:function(){if(!this.isDisabled){this.isActive=true;this.updateState();this.ne.fireEvent("buttonActivate",this)}},deactivate:function(){this.isActive=false;this.updateState();if(!this.isDisabled){this.ne.fireEvent("buttonDeactivate",this)}},enable:function(A,B){this.isDisabled=false;this.contain.setStyle({opacity:1}).addClass("buttonEnabled");this.updateState();this.checkNodes(B)},disable:function(A,B){this.isDisabled=true;this.contain.setStyle({opacity:0.6}).removeClass("buttonEnabled");this.updateState()},toggleActive:function(){(this.isActive)?this.deactivate():this.activate()},hoverOn:function(){if(!this.isDisabled){this.isHover=true;this.updateState();this.ne.fireEvent("buttonOver",this)}},hoverOff:function(){this.isHover=false;this.updateState();this.ne.fireEvent("buttonOut",this)},mouseClick:function(){if(this.options.command){this.ne.nicCommand(this.options.command,this.options.commandArgs);if(!this.options.noActive){this.toggleActive()}}this.ne.fireEvent("buttonClick",this)},key:function(A,B){if(this.options.key&&B.ctrlKey&&String.fromCharCode(B.keyCode||B.charCode).toLowerCase()==this.options.key){this.mouseClick();if(B.preventDefault){B.preventDefault()}}}});
var nicPlugin=bkClass.extend({construct:function(B,A){this.options=A;this.ne=B;this.ne.addEvent("panel",this.loadPanel.closure(this));this.init()},loadPanel:function(C){var B=this.options.buttons;for(var A in B){C.addButton(A,this.options)}C.reorder()},init:function(){}});


var nicPaneOptions = { };

var nicEditorPane=bkClass.extend({construct:function(D,C,B,A){this.ne=C;this.elm=D;this.pos=D.pos();this.contain=new bkElement("div").setStyle({zIndex:"99999",overflow:"hidden",position:"absolute",left:this.pos[0]+"px",top:this.pos[1]+"px"});this.pane=new bkElement("div").setStyle({fontSize:"12px",border:"1px solid #ccc",overflow:"hidden",padding:"4px",textAlign:"left",backgroundColor:"#ffffc9"}).addClass("pane").setStyle(B).appendTo(this.contain);if(A&&!A.options.noClose){this.close=new bkElement("div").setStyle({"float":"right",height:"16px",width:"16px",cursor:"pointer"}).setStyle(this.ne.getIcon("close",nicPaneOptions)).addEvent("mousedown",A.removePane.closure(this)).appendTo(this.pane)}this.contain.noSelect().appendTo(document.body);this.position();this.init()},init:function(){},position:function(){if(this.ne.nicPanel){var B=this.ne.nicPanel.elm;var A=B.pos();var C=A[0]+parseInt(B.getStyle("width"))-(parseInt(this.pane.getStyle("width"))+8);if(C<this.pos[0]){this.contain.setStyle({left:C+"px"})}}},toggle:function(){this.isVisible=!this.isVisible;this.contain.setStyle({display:((this.isVisible)?"block":"none")})},remove:function(){if(this.contain){this.contain.remove();this.contain=null}},append:function(A){A.appendTo(this.pane)},setContent:function(A){this.pane.setContent(A)}});

var nicButtonTips=bkClass.extend({construct:function(A){this.ne=A;A.addEvent("buttonOver",this.show.closure(this)).addEvent("buttonOut",this.hide.closure(this))},show:function(A){this.timer=setTimeout(this.create.closure(this,A),400)},create:function(A){this.timer=null;if(!this.pane){this.pane=new nicEditorPane(A.button,this.ne,{fontSize:"12px",marginTop:"5px"});this.pane.setContent(A.options.name)}},hide:function(A){if(this.timer){clearTimeout(this.timer)}if(this.pane){this.pane=this.pane.remove()}}});nicEditors.registerPlugin(nicButtonTips);


var nicSelectOptions = {
	buttons : {
		'fontSize' : {name : __('Select Font Size'), type : 'nicEditorFontSizeSelect', command : 'fontsize'},
		'fontFamily' : {name : __('Select Font Family'), type : 'nicEditorFontFamilySelect', command : 'fontname'},
		'fontFormat' : {name : __('Select Font Format'), type : 'nicEditorFontFormatSelect', command : 'formatBlock'}
	}
};

var nicEditorSelect=bkClass.extend({construct:function(D,A,C,B){this.options=C.buttons[A];this.elm=D;this.ne=B;this.name=A;this.selOptions=new Array();this.margin=new bkElement("div").setStyle({"float":"left",margin:"2px 1px 0 1px"}).appendTo(this.elm);this.contain=new bkElement("div").setStyle({width:"90px",height:"20px",cursor:"pointer",overflow:"hidden"}).addClass("selectContain").addEvent("click",this.toggle.closure(this)).appendTo(this.margin);this.items=new bkElement("div").setStyle({overflow:"hidden",zoom:1,border:"1px solid #ccc",paddingLeft:"3px",backgroundColor:"#fff"}).appendTo(this.contain);this.control=new bkElement("div").setStyle({overflow:"hidden","float":"right",height:"18px",width:"16px"}).addClass("selectControl").setStyle(this.ne.getIcon("arrow",C)).appendTo(this.items);this.txt=new bkElement("div").setStyle({overflow:"hidden","float":"left",width:"66px",height:"14px",marginTop:"1px",fontFamily:"sans-serif",textAlign:"center",fontSize:"12px"}).addClass("selectTxt").appendTo(this.items);if(!window.opera){this.contain.onmousedown=this.control.onmousedown=this.txt.onmousedown=bkLib.cancelEvent}this.margin.noSelect();this.ne.addEvent("selected",this.enable.closure(this)).addEvent("blur",this.disable.closure(this));this.disable();this.init()},disable:function(){this.isDisabled=true;this.close();this.contain.setStyle({opacity:0.6})},enable:function(A){this.isDisabled=false;this.close();this.contain.setStyle({opacity:1})},setDisplay:function(A){this.txt.setContent(A)},toggle:function(){if(!this.isDisabled){(this.pane)?this.close():this.open()}},open:function(){this.pane=new nicEditorPane(this.items,this.ne,{width:"88px",padding:"0px",borderTop:0,borderLeft:"1px solid #ccc",borderRight:"1px solid #ccc",borderBottom:"0px",backgroundColor:"#fff"});for(var C=0;C<this.selOptions.length;C++){var B=this.selOptions[C];var A=new bkElement("div").setStyle({overflow:"hidden",borderBottom:"1px solid #ccc",width:"88px",textAlign:"left",overflow:"hidden",cursor:"pointer"});var D=new bkElement("div").setStyle({padding:"0px 4px"}).setContent(B[1]).appendTo(A).noSelect();D.addEvent("click",this.update.closure(this,B[0])).addEvent("mouseover",this.over.closure(this,D)).addEvent("mouseout",this.out.closure(this,D)).setAttributes("id",B[0]);this.pane.append(A);if(!window.opera){D.onmousedown=bkLib.cancelEvent}}},close:function(){if(this.pane){this.pane=this.pane.remove()}},over:function(A){A.setStyle({backgroundColor:"#ccc"})},out:function(A){A.setStyle({backgroundColor:"#fff"})},add:function(B,A){this.selOptions.push(new Array(B,A))},update:function(A){this.ne.nicCommand(this.options.command,A);this.close()}});var nicEditorFontSizeSelect=nicEditorSelect.extend({sel:{1:"1&nbsp;(8pt)",2:"2&nbsp;(10pt)",3:"3&nbsp;(12pt)",4:"4&nbsp;(14pt)",5:"5&nbsp;(18pt)",6:"6&nbsp;(24pt)"},init:function(){this.setDisplay("Font&nbsp;Size...");for(itm in this.sel){this.add(itm,'<font size="'+itm+'">'+this.sel[itm]+"</font>")}}});var nicEditorFontFamilySelect=nicEditorSelect.extend({sel:{arial:"Arial","comic sans ms":"Comic Sans","courier new":"Courier New",georgia:"Georgia",helvetica:"Helvetica",impact:"Impact","times new roman":"Times","trebuchet ms":"Trebuchet",verdana:"Verdana"},init:function(){this.setDisplay("Font&nbsp;Family...");for(itm in this.sel){this.add(itm,'<font face="'+itm+'">'+this.sel[itm]+"</font>")}}});var nicEditorFontFormatSelect=nicEditorSelect.extend({sel:{p:"Paragraph",pre:"Pre",h6:"Heading&nbsp;6",h5:"Heading&nbsp;5",h4:"Heading&nbsp;4",h3:"Heading&nbsp;3",h2:"Heading&nbsp;2",h1:"Heading&nbsp;1"},init:function(){this.setDisplay("Font&nbsp;Format...");for(itm in this.sel){var A=itm.toUpperCase();this.add("<"+A+">","<"+itm+' style="padding: 0px; margin: 0px;">'+this.sel[itm]+"</"+A+">")}}});nicEditors.registerPlugin(nicPlugin,nicSelectOptions);


/*!
 * jQuery Tools v1.2.7 - The missing UI library for the Web
 * 
 * rangeinput/rangeinput.js
 * toolbox/toolbox.expose.js
 * 
 * NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.
 * 
 * http://flowplayer.org/tools/
 * 
 */
(function(a){a.tools=a.tools||{version:"v1.2.7"};var b;b=a.tools.rangeinput={conf:{min:0,max:100,step:"any",steps:0,value:0,precision:undefined,vertical:0,keyboard:!0,progress:!1,speed:100,css:{input:"range",slider:"slider",progress:"progress",handle:"handle"}}};var c,d;a.fn.drag=function(b){document.ondragstart=function(){return!1},b=a.extend({x:!0,y:!0,drag:!0},b),c=c||a(document).on("mousedown mouseup",function(e){var f=a(e.target);if(e.type=="mousedown"&&f.data("drag")){var g=f.position(),h=e.pageX-g.left,i=e.pageY-g.top,j=!0;c.on("mousemove.drag",function(a){var c=a.pageX-h,e=a.pageY-i,g={};b.x&&(g.left=c),b.y&&(g.top=e),j&&(f.trigger("dragStart"),j=!1),b.drag&&f.css(g),f.trigger("drag",[e,c]),d=f}),e.preventDefault()}else try{d&&d.trigger("dragEnd")}finally{c.off("mousemove.drag"),d=null}});return this.data("drag",!0)};function e(a,b){var c=Math.pow(10,b);return Math.round(a*c)/c}function f(a,b){var c=parseInt(a.css(b),10);if(c)return c;var d=a[0].currentStyle;return d&&d.width&&parseInt(d.width,10)}function g(a){var b=a.data("events");return b&&b.onSlide}function h(b,c){var d=this,h=c.css,i=a("<div><div/><a href='#'/></div>").data("rangeinput",d),j,k,l,m,n;b.before(i);var o=i.addClass(h.slider).find("a").addClass(h.handle),p=i.find("div").addClass(h.progress);a.each("min,max,step,value".split(","),function(a,d){var e=b.attr(d);parseFloat(e)&&(c[d]=parseFloat(e,10))});var q=c.max-c.min,r=c.step=="any"?0:c.step,s=c.precision;s===undefined&&(s=r.toString().split("."),s=s.length===2?s[1].length:0);if(b.attr("type")=="range"){var t=b.clone().wrap("<div/>").parent().html(),u=a(t.replace(/type/i,"type=text data-orig-type"));u.val(c.value),b.replaceWith(u),b=u}b.addClass(h.input);var v=a(d).add(b),w=!0;function x(a,f,g,h){g===undefined?g=f/m*q:h&&(g-=c.min),r&&(g=Math.round(g/r)*r);if(f===undefined||r)f=g*m/q;if(isNaN(g))return d;f=Math.max(0,Math.min(f,m)),g=f/m*q;if(h||!j)g+=c.min;j&&(h?f=m-f:g=c.max-g),g=e(g,s);var i=a.type=="click";if(w&&k!==undefined&&!i){a.type="onSlide",v.trigger(a,[g,f]);if(a.isDefaultPrevented())return d}var l=i?c.speed:0,t=i?function(){a.type="change",v.trigger(a,[g])}:null;j?(o.animate({top:f},l,t),c.progress&&p.animate({height:m-f+o.height()/2},l)):(o.animate({left:f},l,t),c.progress&&p.animate({width:f+o.width()/2},l)),k=g,n=f,b.val(g);return d}a.extend(d,{getValue:function(){return k},setValue:function(b,c){y();return x(c||a.Event("api"),undefined,b,!0)},getConf:function(){return c},getProgress:function(){return p},getHandle:function(){return o},getInput:function(){return b},step:function(b,e){e=e||a.Event();var f=c.step=="any"?1:c.step;d.setValue(k+f*(b||1),e)},stepUp:function(a){return d.step(a||1)},stepDown:function(a){return d.step(-a||-1)}}),a.each("onSlide,change".split(","),function(b,e){a.isFunction(c[e])&&a(d).on(e,c[e]),d[e]=function(b){b&&a(d).on(e,b);return d}}),o.drag({drag:!1}).on("dragStart",function(){y(),w=g(a(d))||g(b)}).on("drag",function(a,c,d){if(b.is(":disabled"))return!1;x(a,j?c:d)}).on("dragEnd",function(a){a.isDefaultPrevented()||(a.type="change",v.trigger(a,[k]))}).click(function(a){return a.preventDefault()}),i.click(function(a){if(b.is(":disabled")||a.target==o[0])return a.preventDefault();y();var c=j?o.height()/2:o.width()/2;x(a,j?m-l-c+a.pageY:a.pageX-l-c)}),c.keyboard&&b.keydown(function(c){if(!b.attr("readonly")){var e=c.keyCode,f=a([75,76,38,33,39]).index(e)!=-1,g=a([74,72,40,34,37]).index(e)!=-1;if((f||g)&&!(c.shiftKey||c.altKey||c.ctrlKey)){f?d.step(e==33?10:1,c):g&&d.step(e==34?-10:-1,c);return c.preventDefault()}}}),b.blur(function(b){var c=a(this).val();c!==k&&d.setValue(c,b)}),a.extend(b[0],{stepUp:d.stepUp,stepDown:d.stepDown});function y(){j=c.vertical||f(i,"height")>f(i,"width"),j?(m=f(i,"height")-f(o,"height"),l=i.offset().top+m):(m=f(i,"width")-f(o,"width"),l=i.offset().left)}function z(){y(),d.setValue(c.value!==undefined?c.value:c.min)}z(),m||a(window).load(z)}a.expr[":"].range=function(b){var c=b.getAttribute("type");return c&&c=="range"||a(b).filter("input").data("rangeinput")},a.fn.rangeinput=function(c){if(this.data("rangeinput"))return this;c=a.extend(!0,{},b.conf,c);var d;this.each(function(){var b=new h(a(this),a.extend(!0,{},c)),e=b.getInput().data("rangeinput",b);d=d?d.add(e):e});return d?d:this}})(jQuery);
(function(a){a.tools=a.tools||{version:"v1.2.7"};var b;b=a.tools.expose={conf:{maskId:"exposeMask",loadSpeed:"slow",closeSpeed:"fast",closeOnClick:!0,closeOnEsc:!0,zIndex:9998,opacity:.8,startOpacity:0,color:"#fff",onLoad:null,onClose:null}};function c(){if(a.browser.msie){var b=a(document).height(),c=a(window).height();return[window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth,b-c<20?c:b]}return[a(document).width(),a(document).height()]}function d(b){if(b)return b.call(a.mask)}var e,f,g,h,i;a.mask={load:function(j,k){if(g)return this;typeof j=="string"&&(j={color:j}),j=j||h,h=j=a.extend(a.extend({},b.conf),j),e=a("#"+j.maskId),e.length||(e=a("<div/>").attr("id",j.maskId),a("body").append(e));var l=c();e.css({position:"absolute",top:0,left:0,width:l[0],height:l[1],display:"none",opacity:j.startOpacity,zIndex:j.zIndex}),j.color&&e.css("backgroundColor",j.color);if(d(j.onBeforeLoad)===!1)return this;j.closeOnEsc&&a(document).on("keydown.mask",function(b){b.keyCode==27&&a.mask.close(b)}),j.closeOnClick&&e.on("click.mask",function(b){a.mask.close(b)}),a(window).on("resize.mask",function(){a.mask.fit()}),k&&k.length&&(i=k.eq(0).css("zIndex"),a.each(k,function(){var b=a(this);/relative|absolute|fixed/i.test(b.css("position"))||b.css("position","relative")}),f=k.css({zIndex:Math.max(j.zIndex+1,i=="auto"?0:i)})),e.css({display:"block"}).fadeTo(j.loadSpeed,j.opacity,function(){a.mask.fit(),d(j.onLoad),g="full"}),g=!0;return this},close:function(){if(g){if(d(h.onBeforeClose)===!1)return this;e.fadeOut(h.closeSpeed,function(){d(h.onClose),f&&f.css({zIndex:i}),g=!1}),a(document).off("keydown.mask"),e.off("click.mask"),a(window).off("resize.mask")}return this},fit:function(){if(g){var a=c();e.css({width:a[0],height:a[1]})}},getMask:function(){return e},isLoaded:function(a){return a?g=="full":g},getConf:function(){return h},getExposed:function(){return f}},a.fn.mask=function(b){a.mask.load(b);return this},a.fn.expose=function(b){a.mask.load(b,this);return this}})(jQuery);

/*
 * iButton jQuery Plug-in
 *
 * Copyright 2011 Giva, Inc. (http://www.givainc.com/labs/) 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Date: 2011-07-26
 * Rev:  1.0.03
 */
(function(E){E.iButton={version:"1.0.03",setDefaults:function(G){E.extend(F,G)}};E.fn.iButton=function(J){var K=typeof arguments[0]=="string"&&arguments[0];var I=K&&Array.prototype.slice.call(arguments,1)||arguments;var H=(this.length==0)?null:E.data(this[0],"iButton");if(H&&K&&this.length){if(K.toLowerCase()=="object"){return H}else{if(H[K]){var G;this.each(function(L){var M=E.data(this,"iButton")[K].apply(H,I);if(L==0&&M){if(!!M.jquery){G=E([]).add(M)}else{G=M;return false}}else{if(!!M&&!!M.jquery){G=G.add(M)}}});return G||this}else{return this}}}else{return this.each(function(){new C(this,J)})}};var A=0;E.browser.iphone=(navigator.userAgent.toLowerCase().indexOf("iphone")>-1);var C=function(N,I){var S=this,H=E(N),T=++A,K=false,U={},O={dragging:false,clicked:null},W={position:null,offset:null,time:null},I=E.extend({},F,I,(!!E.metadata?H.metadata():{})),Y=(I.labelOn==B&&I.labelOff==D),Z=":checkbox, :radio";if(!H.is(Z)){return H.find(Z).iButton(I)}else{if(E.data(H[0],"iButton")){return }}E.data(H[0],"iButton",S);if(I.resizeHandle=="auto"){I.resizeHandle=!Y}if(I.resizeContainer=="auto"){I.resizeContainer=!Y}this.toggle=function(b){var a=(arguments.length>0)?b:!H[0].checked;H.attr("checked",a).trigger("change")};this.disable=function(b){var a=(arguments.length>0)?b:!K;K=a;H.attr("disabled",a);V[a?"addClass":"removeClass"](I.classDisabled);if(E.isFunction(I.disable)){I.disable.apply(S,[K,H,I])}};this.repaint=function(){X()};this.destroy=function(){E([H[0],V[0]]).unbind(".iButton");E(document).unbind(".iButton_"+T);V.after(H).remove();E.data(H[0],"iButton",null);if(E.isFunction(I.destroy)){I.destroy.apply(S,[H,I])}};H.wrap('<div class="'+E.trim(I.classContainer+" "+I.className)+'" />').after('<div class="'+I.classHandle+'"><div class="'+I.classHandleRight+'"><div class="'+I.classHandleMiddle+'" /></div></div><div class="'+I.classLabelOff+'"><span><label>'+I.labelOff+'</label></span></div><div class="'+I.classLabelOn+'"><span><label>'+I.labelOn+'</label></span></div><div class="'+I.classPaddingLeft+'"></div><div class="'+I.classPaddingRight+'"></div>');var V=H.parent(),G=H.siblings("."+I.classHandle),P=H.siblings("."+I.classLabelOff),M=P.children("span"),J=H.siblings("."+I.classLabelOn),L=J.children("span");if(I.resizeHandle||I.resizeContainer){U.onspan=L.outerWidth();U.offspan=M.outerWidth()}if(I.resizeHandle){U.handle=Math.min(U.onspan,U.offspan);G.css("width",U.handle)}else{U.handle=G.width()}if(I.resizeContainer){U.container=(Math.max(U.onspan,U.offspan)+U.handle+20);V.css("width",U.container);P.css("width",U.container-5)}else{U.container=V.width()}var R=U.container-U.handle-6;var X=function(b){var c=H[0].checked,a=(c)?R:0,b=(arguments.length>0)?arguments[0]:true;if(b&&I.enableFx){G.stop().animate({left:a},I.duration,I.easing);J.stop().animate({width:a+4},I.duration,I.easing);L.stop().animate({marginLeft:a-R},I.duration,I.easing);M.stop().animate({marginRight:-a},I.duration,I.easing)}else{G.css("left",a);J.css("width",a+4);L.css("marginLeft",a-R);M.css("marginRight",-a)}};X(false);var Q=function(a){return a.pageX||((a.originalEvent.changedTouches)?a.originalEvent.changedTouches[0].pageX:0)};V.bind("mousedown.iButton touchstart.iButton",function(a){if(E(a.target).is(Z)||K||(!I.allowRadioUncheck&&H.is(":radio:checked"))){return }a.preventDefault();O.clicked=G;W.position=Q(a);W.offset=W.position-(parseInt(G.css("left"),10)||0);W.time=(new Date()).getTime();return false});if(I.enableDrag){E(document).bind("mousemove.iButton_"+T+" touchmove.iButton_"+T,function(c){if(O.clicked!=G){return }c.preventDefault();var a=Q(c);if(a!=W.offset){O.dragging=true;V.addClass(I.classHandleActive)}var b=Math.min(1,Math.max(0,(a-W.offset)/R));G.css("left",b*R);J.css("width",b*R+4);M.css("marginRight",-b*R);L.css("marginLeft",-(1-b)*R);return false})}E(document).bind("mouseup.iButton_"+T+" touchend.iButton_"+T,function(d){if(O.clicked!=G){return false}d.preventDefault();var f=true;if(!O.dragging||(((new Date()).getTime()-W.time)<I.clickOffset)){var b=H[0].checked;H.attr("checked",!b);if(E.isFunction(I.click)){I.click.apply(S,[!b,H,I])}}else{var a=Q(d);var c=(a-W.offset)/R;var b=(c>=0.5);if(H[0].checked==b){f=false}H.attr("checked",b)}V.removeClass(I.classHandleActive);O.clicked=null;O.dragging=null;if(f){H.trigger("change")}else{X()}return false});H.bind("change.iButton",function(){X();if(H.is(":radio")){var b=H[0];var a=E(b.form?b.form[b.name]:":radio[name="+b.name+"]");a.filter(":not(:checked)").iButton("repaint")}if(E.isFunction(I.change)){I.change.apply(S,[H,I])}}).bind("focus.iButton",function(){V.addClass(I.classFocus)}).bind("blur.iButton",function(){V.removeClass(I.classFocus)});if(E.isFunction(I.click)){H.bind("click.iButton",function(){I.click.apply(S,[H[0].checked,H,I])})}if(H.is(":disabled")){this.disable(true)}if(E.browser.msie){V.find("*").andSelf().attr("unselectable","on");H.bind("click.iButton",function(){H.triggerHandler("change.iButton")})}if(E.isFunction(I.init)){I.init.apply(S,[H,I])}};var F={duration:200,easing:"swing",labelOn:"ON",labelOff:"OFF",resizeHandle:"auto",resizeContainer:"auto",enableDrag:true,enableFx:true,allowRadioUncheck:false,clickOffset:120,className:"",classContainer:"ibutton-container",classDisabled:"ibutton-disabled",classFocus:"ibutton-focus",classLabelOn:"ibutton-label-on",classLabelOff:"ibutton-label-off",classHandle:"ibutton-handle",classHandleMiddle:"ibutton-handle-middle",classHandleRight:"ibutton-handle-right",classHandleActive:"ibutton-active-handle",classPaddingLeft:"ibutton-padding-left",classPaddingRight:"ibutton-padding-right",init:null,change:null,click:null,disable:null,destroy:null},B=F.labelOn,D=F.labelOff})(jQuery);