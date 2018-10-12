Ext.define("core.user.view.UserForm",{
	extend:"Ext.form.Panel",
	alias:"widget.userform",
	layout : {
		type : "table",
		columns : 2
	},
	align:"left",
	defaults:{
		margin:"10 0 0 15",
		selectOnFocus:true,
		width:300,
		msgTarget:"side" //提示信息现在的位置
	},
	tbar:[{
		xtype:"button",
		ref:"save",
		iconCls:"table_save",
		text:"保存"
	},{
		xtype:"button",
		ref:"return",
		iconCls:"return",
		text:"返回"
	}],
	items:[{
		xtype:"textfield",
		fieldLabel:"主键",
		name:"id",
		hidden:true
	},{
		xtype:"textfield",
		fieldLabel:"用户名",
		name:"name",
		allowBlank : false,//不允许为空
		blankText : '用户名不能为空',//错误提示内容 
		readOnly:false
	},{
		xtype:"textfield",
		fieldLabel:"密码",
		inputType : 'password',  
		name:"password",
		allowBlank : false,//不允许为空
		blankText : '密码不能为空',//错误提示内容 
		readOnly:false
	},{
		xtype:"numberfield",
		fieldLabel:"电话",
		name:"phone",
		allowBlank : false,//不允许为空
		blankText : '电话不能为空',//错误提示内容
		readOnly:false
	},{
		xtype:"numberfield",
		fieldLabel:"QQ",
		name:"QQ",
		readOnly:false
	},{
		xtype:"textareafield",
		fieldLabel:"地址",
		name:"addr",
		readOnly:false
	}],
	initComponent:function(){
		this.callParent(arguments);
	}
});