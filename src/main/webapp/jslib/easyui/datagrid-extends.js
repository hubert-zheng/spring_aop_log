/**
 * 拓展Jquery DataGrid使得每次load时的URL都添加__ti参数
 * 避免IE浏览器缓存带来的问题.
 * author: ChenJingXiong 20160824
 */
(function($){
	$.extend($.fn.datagrid.defaults, {

		loader:function(data,callback,errorHandler){
			var opts=$(this).datagrid("options");
			if(!opts.url){
				return false;
			}
			
			var url = opts.url;
			
			if (url.indexOf("?") > -1) {
				url = url + "&__ti=" +  (new Date()).getTime();
			} else {
				url = url + "?__ti=" +  (new Date()).getTime();
			}
			
			$.ajax({type:opts.method,url:url,data:data,dataType:"json",success:function(data){
				callback(data);
			},error:function(){
				errorHandler.apply(this,arguments);
			}});
		}
	});
	
})(jQuery);