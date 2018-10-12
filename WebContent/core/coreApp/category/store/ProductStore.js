/*
 * 商品数据集类
 */
 Ext.define("core.category.store.ProductStore",{
 	extend:'Ext.data.Store',
	model:'core.category.model.ProductModel',
	pageSize:10,
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./product/listall_product.do",
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