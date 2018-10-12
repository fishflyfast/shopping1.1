/**
 * 商品数据列表视图类
 * */
Ext.define("core.product.view.ProductGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.product_grid",
	store:"core.product.store.ProductStore",
	border:0,
	selModel:{
		selType:"checkboxmodel"
	},
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'添加',ref:'add',iconCls:'table_add'},'|',
		{xtype:'button',text:'删除',ref:'del',iconCls:'table_remove'},
		"->",
		'按名称查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("name",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("name",this.getValue());
		    }
		},
		'按编号查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("id",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("id",this.getValue());
		    }
		}
	],
	bbar:{
		xtype:'pagingtoolbar',
		store:'core.product.store.ProductStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"商品名称",dataIndex:"name",width:100,field:{
			xtype:"textfield"
		}},
		{text:"商品编号",dataIndex:"id",width:100,field:{
			xtype:"textfield"
		}},
		{text:"商品描述",dataIndex:"descr",width:100,field:{
			xtype:"textfield"
		}},
		{text:"市场价",dataIndex:"normaPrice",width:50,field:{
			xtype:"textfield"
		}},
		{text:"会员价",dataIndex:"memberPrice",width:50,field:{
			xtype:"textfield"
		}},
		{text:"上货日期",dataIndex:"pdate",width:150,field:{
			xtype:"datefield",
			width:150
		}},
		{text:"图片文件名",dataIndex:"photo",width:150,field:{
			xtype:"textfield"
		}},
		{text:"所属类别",dataIndex:"category",width:70,field:{
			xtype:"textfield"
		}}
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});