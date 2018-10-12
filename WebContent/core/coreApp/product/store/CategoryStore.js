/*
 * ClassName 用户数据集
 */
 Ext.define("core.product.store.CategoryStore",{
 	extend:'Ext.data.Store',
	model:'core.product.model.CategoryModel',
	//autoSync:true,//与服务器同步
	proxy:{
		type:"ajax",
		url:"./category/combo_category.do",
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