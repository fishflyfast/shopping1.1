/*
 * ClassName 功能挂接表格
 */
 Ext.define("core.salesorder.model.SalesOrderModel",{
 	extend:"Ext.data.Model",
 	fields:[
 		{name:"user",type:"string",srotable:true},
 		{name:"orderCode",type:"string",srotable:true},
 		{name:"status",type:"boolean",srotable:true},
 		{name:"odate",type:"string",srotable:true},
 		{name:"phone",type:"string",srotable:true},
 		{name:"QQ",type:"string",srotable:true},
 		{name:"addr",type:"string",srotable:true},
 		{name:"totalmoney",type:"int",srotable:true},
 		{name:"remark",type:"string",srotable:true}
 	]
 });