Ext.define("core.salesorder.view.SalesOrderLayout", {
			extend : 'Ext.panel.Panel',
			alias : 'widget.salesorderlayout',
			title : "<center height=40>定单列表</center>",
			//closable:true,
			defaults : {
				bodyStyle : 'padding:1px'
			},
			layout : 'fit',
			items:[{
				xtype:"salesordergrid"
			},{
				xtype:"saleitemform",
				hidden:true
			}]
});