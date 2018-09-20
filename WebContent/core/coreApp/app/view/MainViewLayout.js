/*系统主程序界面布局*/
Ext.define('core.app.view.MainViewLayout',{
	extend : 'Ext.panel.Panel',
	border : 0,
	layout : 'border',
	alias : 'widget.mainviewlayout',
	width : 1024,
	height : 768,
	items : [{
		region : 'north',
		xtype : 'topview'
	}, {
		xtype : 'westview',
		region : 'west'
	}, {
		xtype : 'panel',
		region : 'center',
		layout : 'fit',
		margins : '2 0 0 0',
		border : 0,
		items : [{
			xtype : 'centerview',
			border : 0
		}]
	}],
	initComponent : function() {
		this.callParent(arguments);
	}
});