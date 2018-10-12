
 Ext.define("core.salesorder.store.SalesOrderStore",{
 	extend:'Ext.data.Store',
	model:'core.salesorder.model.SalesOrderModel',
	pageSize:10,//每页显示5条记录
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./salesorder/list_salesorder.do",
		reader:{
			type:"json",
			root:"rows",
			totalProperty :'totalCount'		
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:true	
 });