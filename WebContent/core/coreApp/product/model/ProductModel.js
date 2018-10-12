/*
 * 商品实体类
 */
 Ext.define("core.product.model.ProductModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"id",type:"int",srotable:true},
 		{name:"name",type:"string",srotable:true},
 		{name:"descr",type:"string",srotable:true},
 		{name:"normaPrice",type:"float",srotable:true},
 		{name:"memberPrice",type:"float",srotable:true},
 		{name:"pdate",type:"string",srotable:true},
 		{name:"category",type:"string",srotable:true},
 		{name:"photo",type:"string",srotable:true}
 	]
 });