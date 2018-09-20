/**应用程序入口
 * 相当于window.onload=function(){}
 * */
Ext.onReady(function(){
	Ext.QuickTips.init();/*打开Ext的提示功能*/
	/*启动动态加载*/
	Ext.Loader.setConfig({
		enabled : true
	});
	/*开始执行应用程序*/
	Ext.application({
		name : 'core',/*命名空间可以写成core.view.windowView*/
		appFolder : 'core/coreApp',//应用程序包的根目录
		launch : function(){
			Ext.create('Ext.container.Viewport',{
				layout : 'fit',
				border : 0,
				items : [{
					xtype : 'mainviewlayout'
				}]
			});
		},
		controllers : ['core.app.controller.MainController']
	});
});