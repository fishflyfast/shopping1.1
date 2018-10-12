/*
 * ClassName 用户实体
 */
 Ext.define("core.salesorder.model.SaleItemModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"name",type:"string",srotable:true},
 		{name:"productId",type:"string",srotable:true},
 		{name:"count",type:"string",srotable:true},
 		{name:"price",type:"string",srotable:true},
 		{name:"sum",type:"string",srotable:true}
 	]
 });