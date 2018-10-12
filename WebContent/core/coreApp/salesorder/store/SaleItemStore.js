
 Ext.define("core.salesorder.store.SaleItemStore",{
 	extend:'Ext.data.Store',
	model:'core.salesorder.model.SaleItemModel',
	pageSize:10,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"",
		reader:{
			type:"json",
			root:"rows",
			totalProperty :'totalCount'		
		},
		writer:{
			type:"json"
		}
	},
	autoLoad:false
 });