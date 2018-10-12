Ext.define("core.salesorder.controller.SalesOrderController", {
	extend : "Ext.app.Controller",
	mixins : {
		formUtils : "core.util.FormUtils"
	},
	init : function() {
		var self = this;
		this.control({
			
			/**双击事件查询详细信息*/
			"salesordergrid":{
				itemdblclick:function(grid, record, item, index, e, eOpts){
					var form = grid.up("salesorderlayout").down("saleitemform");
					// 还原表单内容
					self.resetFormInfo(form);
					form = grid.up("salesorderlayout").down("saleitemform");
					var formObj = form.getForm();
					grid = form.up("salesorderlayout").down("salesordergrid");
					var records = grid.getSelectionModel().getSelection();
					var obj = records[0].data;
					// 把对象值放入form中
					self.setFormValue(formObj, obj);
					
					
					/**条件让数据放到productgrid中去*/
					var saleitemgrid = form.down("saleitemgrid");
					var saleitemStore = saleitemgrid.getStore();
					var id = records[0].data.id;
					var url = "./salesorder/show_salesorderdetail.do?id="+id;
					saleitemStore.getProxy().url = url; 
					//store加载提示
					//self.storeTip(saleitemStore);
					saleitemStore.load();
					//设置头
					var tit = "";
					if(records[0].data.status){
						tit = "<font color=red>已审核</font>"
					}else{
						tit = "<font color=green>未审核</font>"
					}
					form.down('displayfield').setValue("<h1><center><font size=5>定   单("+tit+")</font></center></h1>");
					
					//判断是否审核过!设置审核按钮
					if(records[0].data.status){
						var btn = form.down("button[ref=check]");
						btn.disable(true);
						btn.setText("已审核");
					}else{
						
					}
					grid.hide();
					form.show();
				}
			},
			
			/**form的返回*/
			"saleitemform button[ref=return]" : {
				click : function(btn) {
					var form = btn.up("saleitemform");
					var grid = form.up("salesorderlayout").down("salesordergrid");
					form.hide();
					grid.show();
				}
			},
			
			/**form的审核*/
			"saleitemform button[ref=check]" : {
				click : function(btn) {
					var form = btn.up("saleitemform");
					var grid = form.up("salesorderlayout").down("salesordergrid");
					
					//成功就返回
					var id = form.getForm().findField("id").getValue();
					var url = "./salesorder/check_salesorder.do";
					Ext.Ajax.request({
						waitMsg : '正在进行处理,请稍后...',
						url:url,
						params:{id:id},
						method:"POST",
						timeout:4000,
						success:function(response,opts){
							var resObj=Ext.decode(response.responseText);
							if(resObj.success){
								form.hide();
								grid.getStore().load();
								grid.show();
								Ext.Msg.alert("提示",resObj.msg);
							}else{
								Ext.Msg.alert("提示",resObj.msg);
							}
						}
					});
				}
			}

		});
	},
	views : [
		"core.salesorder.view.SalesOrderLayout",
		"core.salesorder.view.SalesOrderGrid",
		"core.salesorder.view.SaleItemForm",
		"core.salesorder.view.SaleItemGrid"
	],
	stores : ["core.salesorder.store.SalesOrderStore","core.salesorder.store.SaleItemStore"],
	models : ["core.salesorder.model.SalesOrderModel","core.salesorder.model.SaleItemModel"]
});