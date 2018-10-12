/**
 * ClassName 流程挂接视图
 * */
Ext.define("core.user.view.UserGrid",{
	extend:"Ext.grid.Panel",
	alias:"widget.usergrid",
	store:"core.user.store.UserStore",
	selModel:{
		selType:"checkboxmodel"
	},
	border:0,
	multiSelect:true,
	frame:true,
	tbar:[
		{xtype:'button',text:'新增用户',ref:'addUser',iconCls:'table_add'},
		{xtype:'button',text:'修改用户',ref:'updateUser',iconCls:'table_edit'},
		{xtype:'button',text:'删除用户',ref:'removeUser',iconCls:'table_remove'},
		"->",
		"按用户名模糊查询:",
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
		}
	],
	bbar:{
		xtype:'pagingtoolbar',
		store:'core.user.store.UserStore',
		dock:'bottom',
		displayInfo:true
	},
	enableKeyNav:true,  //可以使用键盘控制上下
	columnLines:true, //展示竖线
	columns:[
		{xtype: 'rownumberer'},
		{text:"用户名称",dataIndex:"name",width:100,border: 50},
		{text:"用户密码",dataIndex:"password",width:100},
		{text:"性别",dataIndex:"sex",width:100,
			 xtype : 'booleancolumn',
			 trueText : "<font color=green>女</font>",
			 falseText : "<font color=red>男</font>"
		},
		{text:"电话",dataIndex:"phone",width:100},
		{text:"QQ",dataIndex:"QQ",width:100},
		{text:"Email",dataIndex:"email",width:100},
		{text:"地址",dataIndex:"addr",width:100},
		{text:"注册时间",dataIndex:"regDate",width:100,emptyCellText:'ggfd'},
		{text:"注册IP",dataIndex:"IP",width:100}
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});