Ext.define("core.product.controller.ProductController", {
	extend : "Ext.app.Controller",
	init : function() {
		var self = this;
		this.control({
			
			/**productgrid的添加按钮事件*/
			"product_grid button[ref='add']" : {
				click : function(_btn){
					var grid = _btn.up("productlayout").down("product_grid");
					var form = grid.up("productlayout").down("productform");
					form.show();
					grid.hide();
				}
			},
			/**productgrid的添加按钮事件*/
			"product_grid button[ref='del']" : {
				click : function(btn){
					var grid = btn.up("product_grid");
					var store = grid.getStore();
					//你选择的将要删除的记录
					var records = grid.getSelectionModel().getSelection();
					if (!records || records.length <= 0) {
						Ext.Msg.alert("提示", "请选择需要删除的数据!");
						return;
					}
					// 根据id删除多条记录
					var data = [];
					Ext.Array.each(records, function(model) {
						data.push(Ext.JSON.encode(model.get('id')));
					});
					
					Ext.MessageBox.confirm("重要提示",
					"是否要删除吗？",
					function(e){
						if(e == 'yes'){
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "product/remove_product.do",
								params : {
									ids : data.join(",")
								},// 根据id删除
								method : "POST",
								timeout : 4000,
								success : function(response, opts) {
									var resObj = Ext.decode(response.responseText);
									if (resObj.success) {
										// 不用查询，从grid中去掉对应的记录就OK了
										store.load();
										Ext.Msg.alert("提示", resObj.msg);
									} else {
										Ext.Msg.alert("提示", resObj.msg);
									}
								}
							});
						}
					});
					
					
				}
			},
			/**productgrid双击事件响应*/
			"product_grid" : {
				itemdblclick : function(_grid, record, item, index, e, eOpts) {
					var form = _grid.up("productlayout").down("productform");
					var grid = form.up("productlayout").down("product_grid");
					//把选择的数据加载到form中去
					var _record = grid.getSelectionModel().getSelection();
					form.loadRecord(_record[0]);
					grid.hide();
					form.down("image").setSrc("images/product/"+_record[0].get("photo"));
					form.show();
				}
			},
			
			/**===============================================*/
			"productform button[ref=return]" : {
				click : function(btn) {
					var form = btn.up("productform");
					var grid = form.up("productlayout").down("product_grid");
					form.hide();
					grid.show();
				}
			},
			
			/**productform的保存按钮事件*/
			"productform button[ref=save]" : {
				click : function(_btn){
					//1获得form
					var _form = _btn.ownerCt.ownerCt;
					var _grid = _btn.up("productlayout").down("product_grid");
					var id = _form.getForm().findField("id").getValue();
					var url = "";
					if(id == "" || null == id){
						url = "product/add_product.do";
					}else{
						url = "product/update_product.do";
					}
					//alert(url);
					//2.把数据保存到数据库中去
					_form.submit({
						clientValidation : false,
						waitMsg : '正在进行处理,请稍后...', 
						url : url,
						method : 'POST',
						success : function(form, action) {
							var resProductObj = Ext.decode(action.response.responseText);
							if (resProductObj.success) {
								
								if(url != "product/update_product.do"){
									_form.getForm().reset();
								}else{
									_form.down("image").setSrc("images/product/"+resProductObj.photo);
								}
								//3.把这条数据加到grid中
								_grid.getStore().load();

								Ext.Msg.alert("提示", resProductObj.msg);
							} else {
								Ext.Msg.alert("提示", resProductObj.msg);
							}
						},
						failure : function(form, action) {
							Ext.Msg.alert("提示","请求处理失败！");
						}
					});
				}
			}
		});
	},
	views : ["core.product.view.ProductLayout","core.product.view.ProductGrid","core.product.view.ProductForm"],
	stores : ["core.product.store.ProductStore"],
	models : ["core.product.model.ProductModel"]
});