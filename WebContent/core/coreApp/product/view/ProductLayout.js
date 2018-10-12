Ext.define("core.product.view.ProductLayout", {
			extend : 'Ext.panel.Panel',
			alias : 'widget.productlayout',
			title : "<center height=40>商品管理</center>",
			defaults : {
				bodyStyle : 'padding:1px'
			},
			layout : 'fit',
			closable : true,
			closeAction : 'hide',
			items:[{
				xtype:"product_grid"
			},{
				xtype:"productform",
				hidden:true
			}]
});