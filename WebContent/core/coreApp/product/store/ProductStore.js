/*
 * 商品数据集类
 */
 Ext.define("core.product.store.ProductStore",{
 	extend:'Ext.data.Store',
	model:'core.product.model.ProductModel',
	pageSize:10,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./product/list_product.do",
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