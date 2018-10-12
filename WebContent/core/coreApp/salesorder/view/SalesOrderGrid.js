/**
 * ClassName 流程挂接视图
 * */
Ext.define("core.salesorder.view.SalesOrderGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.salesordergrid",
	store:"core.salesorder.store.SalesOrderStore",
	selModel:{
		selType:"checkboxmodel"
	},
	border:0,
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'刷新',ref:'refresh',iconCls:'table_refresh',
			handler:function(btn){
//				var store = btn.ownerCt.ownerCt.getStore();
//				store.clearFilter(false);
			}
		},
		{xtype:'panel',width:15,border:0},
		'按定客户查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("user",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("user",this.getValue());
		    }
		},
		
		{xtype:'panel',width:15,border:0},
		'按定单编号查询:',
		{
			xtype: 'triggerfield', 
			triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
			listeners:{
            	"change":function(_this,_new,_old,_opt){ 
            		var _store = _this.ownerCt.ownerCt.getStore();
            		_store.clearFilter(false);
            		_store.filter("orderCode",_new);
                }
            },
            onTriggerClick: function() {
            	var _store = this.ownerCt.ownerCt.getStore();
            	_store.clearFilter(false);
            	_store.filter("orderCode",this.getValue());
		    }
		},
		{xtype:'panel',width:15,border:0},
		{xtype:'combobox',width:175,
			fieldLabel: '请选择过滤条件',
		    store: Ext.create('Ext.data.Store', {
					    fields: ['abbr', 'name'],
					    data : [
					    	{"abbr":"quanbu", "name":"全部"},
					        {"abbr":"true", "name":"审核"},
					        {"abbr":"false", "name":"未审核"}
					        //...
					    ]
				  }),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'abbr',
		    value:'全部',
		    listeners:{
		         scope: this,
		         'select': function(combo,record){
		         	var store = combo.ownerCt.ownerCt.getStore();
		         	if(record[0].data.abbr == "quanbu"){
		         		store.clearFilter(false);
		         	}else{
			         	store.clearFilter(false);
			         	store.filter("status", record[0].data.abbr);
		         	}
		         	//store.clearFilter(false);
		         	//alert(record[0].data.name);
		         }
		    }

		},
		"->",
		'提示：查看详细情况请双击记录'
	],
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"客户名",dataIndex:"user",width:70},
		{text:"定单编号",dataIndex:"orderCode",width:100},
		{text:"状态",dataIndex:"status",width:80,
			xtype: 'booleancolumn',
        	trueText: '<font color=red>审核</font>',
        	falseText: '<font color=green>未审核</font>'
		},
		{text:"下定单日期",dataIndex:"odate",width:160},
		{text:"电话",dataIndex:"phone",width:100},
		{text:"QQ",dataIndex:"QQ",width:100},
		{text:"送货地址",dataIndex:"addr",width:100},
		{text:"总金额",dataIndex:"totalmoney",width:100,
			xtype: 'numbercolumn',
        	summaryType: 'sum',
        	summaryRenderer: function(value, summaryData, dataIndex){
        		return Ext.String.format('总和:'+ value);
        	},
        	renderer: function(v){
        		if(v > 1000.00){
        			return "<font color=red>"+v+"</font>";
        		}else{
        			return "<font color=blue>"+v+"</font>";
        		}	
        	},
        	format: '0.00'
		},
		{text:"备注",dataIndex:"remark",width:100}
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});