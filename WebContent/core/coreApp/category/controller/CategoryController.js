Ext.define("core.category.controller.CategoryController", {
//	mixins:{
//		gridUtils:"core.utils.GridUtils"
//	},
//	
	extend : "Ext.app.Controller",
			init : function() {
				var self = this;
				this.control({
					
					/**showbefor*/
					"categorylayout" : {
						beforeshow : function(layout,opt){
							//alert(layout.getXType());
							var grid = layout.down("product_grid");
							var store = grid.getStore();
							store.filter("id",0);
						}
					},
					/**
					 *删除商品
					 */
					"product_grid button[ref=delete]":{
						click:function(btn){
							var grid=btn.up("product_grid");
							var store = grid.getStore();
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
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...', 
								url : "./product/remove_product.do",
								params : {
									ids : data.join(",")
								},// 根据id删除
								method : "POST",
								timeout : 4000,
								success : function(response, opts) {
									var resObj = Ext
											.decode(response.responseText);
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
					},
					/**
					 * 类别树形节点点击
					 */
					"categorytree":{
						itemclick:function(tree,record,item,index,e,eOpts){
							//节点点击事件
							var treeForm=tree.up("categorylayout").down("categoryform").getForm();
							var store=tree.up("categorylayout").down("product_grid").getStore();
							if(record.raw){
								treeForm.findField("id").setValue(record.raw.id);
								treeForm.findField("text").setValue(record.raw.text);
								treeForm.findField("description").setValue(record.raw.description);
								treeForm.findField("parent").setValue(record.raw.parent);
								treeForm.findField("leaf").setValue(record.raw.leaf);
								var proxy = store.getProxy();
									whereSql = " and deptCode='"+record.raw.code+"'";
									proxy.extraParams = {
										whereSql : whereSql
								};
								store.load();
							}else{
								treeForm.findField("id").setValue(record.data.id);
								treeForm.findField("text").setValue(record.data.text);
								treeForm.findField("description").setValue("");
								treeForm.findField("parent").setValue(record.data.parentId);
								treeForm.findField("leaf").setValue(record.data.leaf);
							}
							tree=tree.up("categorylayout").down("categorytree");
							var addChildBtn=tree.down("button[ref=treechildIns]");
							var delBtn=tree.down("button[ref=treeDel]");
							addChildBtn.setDisabled(false);
							delBtn.setDisabled(false);
							/**加载类别对应的商品到grid中*/
							store.clearFilter(false);
							if(record.raw){
								store.filter("category", record.raw.text);
							}
						}
					},
					/**
					 * 为根节点添加类别
					 */
					"categorytree button[ref=treeIns]":{
						click:function(btn){
							//添加根类别
							var tree=btn.up("categorytree");
							var rootNode = tree.getStore().getRootNode(); // 得到根节点
							rootNode.appendChild({
													text :"",
													parent:"root",
													leaf : true
												});
												
						}
					},
					/**
					 * 为指定类别添加子类别
					 */
					"categorytree button[ref=treechildIns]":{
						//添加子类别
						click:function(btn){
							var tree=btn.up("categorytree");
							var records = tree.getSelectionModel().getSelection();
							if(records.length<1){
								Ext.Msg.alert("提示","选择父类别");
								return;
							}
							var parentId=records[0].data.id;
							var parentNode = tree.getStore().getNodeById(parentId);
							if(!parentNode){
								Ext.Msg.alert("提示","不能为未存在的类别添加")
								return;
							}
							// 将leaf属性改变
							parentNode.data["leaf"] = false;
							//parentNode.updateInfo(true,{});
							// 给它加一个孩子节点
							parentNode.appendChild({
														parent:parentId,
														leaf : true
													});
							parentNode.expand(); // 打开父节点
							}
					},
					/**
					 * 删除类别
					 */
					"categorytree button[ref=treeDel]":{
						//删除类别
						click:function(btn){
							var tree=btn.up("categorytree");
							var records = tree.getSelectionModel().getSelection();
							var id=records[0].data.id;
							if(records.length<1){
								Ext.Msg.alert("提示","请选择类别");
								return;
							}
							if(!records[0].raw){
								Ext.Msg.alert("提示","不能删除未存在类别");
								return;
							}
							var pid = records[0].data.parentId;
							Ext.MessageBox.confirm("重要提示",
								"如果删除些类别，同时也会删除些类别对应的所有商品信息，确定要删除吗？",
								function(e){
									if(e == 'yes'){
										Ext.Ajax.request({
											waitMsg : '正在进行处理,请稍后...',
											url:"./category/delete_category.do",
											params:{id:id,pid:pid},
											method:"POST",
											timeout:4000,
											success:function(response,opts){
												var resObj=Ext.decode(response.responseText);
												if(resObj.success){
													tree.getStore().load();
													var categoryForm=tree.up("categorylayout").down("categoryform").getForm();
													categoryForm.reset();
													btn.setDisabled(true);
													Ext.Msg.alert("提示",resObj.msg);
												}else{
													Ext.Msg.alert("提示",resObj.msg);
												}
											}
										});
									}
								}
							);
							
						}
					}
					/**
					 * 保存类别信息
					 */
					,"categoryform button[ref=save]":{
						//类信息保存
						click:function(btn){
							var category=btn.up("categoryform");
							var categoryForm=category.getForm();
							var categoryTree=category.up("categorylayout").down("categorytree");
							//var grid=category.up("categorylayout").down("productgrid");
							//var store = grid.getStore();
							//根据id来判断是保存还是更新，如果为空不就进行操作
							var treeId=categoryForm.findField("id").getValue();
							/*首先声明保存操作*/
							var actionName="";
							var params={};
							if(treeId && treeId!=""){
								//修改								
								actionName="./category/update_category.do";
								params.id=categoryForm.findField("id").getValue();
							}else{
								//新增加
								actionName = "./category/add_category.do";
							}
							var text=categoryForm.findField("text").getValue();
							var description=categoryForm.findField("description").getValue();
							var parent=categoryForm.findField("parent").getValue();
							var leaf=categoryForm.findField("leaf").getValue();
							params.text=text;
							params.description=description;
							params.parent=parent;
							params.leaf=leaf;
							Ext.Ajax.request({
								waitMsg : '正在进行处理,请稍后...',
								url:actionName,
								params:params,
								method:"POST",
								timeout:4000,
								success:function(response,opts){
									var resObj=Ext.decode(response.responseText);
									if(resObj.success){
										categoryForm.reset();
										categoryTree.getStore().load();
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
				"core.category.view.CategoryLayout",
				"core.category.view.CategoryTree",
				"core.category.view.CategoryForm",
				"core.product.view.ProductGrid"
			],
			stores : ["core.category.store.CategoryStore","core.product.store.ProductStore"],
			models : ["core.category.model.ProductModel"]
});