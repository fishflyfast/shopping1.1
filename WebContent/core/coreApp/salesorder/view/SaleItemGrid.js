/**
 * ClassName 人员数据列表视图
 * */
Ext.define("core.salesorder.view.SaleItemGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.saleitemgrid",
	store:"core.salesorder.store.SaleItemStore",
	height:270,
	border:0,
	frame:true,
	tbar:[
		'请认真核对客户定单信息,如果客户打钱就立即审核'
	],
	/*bbar:{
		xtype:'pagingtoolbar',
		store:'core.salesorder.store.ProductStore',
		dock:'bottom',
		displayInfo:true
	},*/
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"商品名称",dataIndex:"name",width:100,field:{
			xtype:"textfield"
		}},
		{text:"商品编号",dataIndex:"productId",width:100,field:{
			xtype:"textfield"
		}},
		{text:"数量",dataIndex:"count",width:100,field:{
			xtype:"textfield"
		}},
		{text:"单价",dataIndex:"price",width:110,field:{
			xtype:"textfield"
		}},
		{text:"金额",dataIndex:"sum",width:130,field:{
			xtype:"textfield"
		}}
	],
	initComponent:function(){
		//this.editing=Ext.create("Ext.grid.plugin.CellEditing");
		//this.plugins=[this.editing];
		this.callParent(arguments);
	}
});