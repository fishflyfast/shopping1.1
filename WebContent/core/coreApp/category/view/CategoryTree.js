/**
 * ClassName 部门树形视图
 */
Ext.define("core.category.view.CategoryTree", {
			extend : "Ext.tree.Panel",
			alias : "widget.categorytree",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			animate : false, // 去掉一些动画效果
			store : "core.category.store.CategoryStore",
			tools : [{
				type:'refresh',
				 qtip: '刷新',
				 handler: function(event, toolEl, header){
					//header.ownerCt.getStore().reload();
				 	alert("些功能还未实现");
					/*header.ownerCt.down('button[ref=treechildIns]').setDisabled(true);
					header.ownerCt.down('button[ref=treeDel]').setDisabled(true);
					var categoryForm=header.ownerCt.up("categorylayout").down("categoryform").getForm();
				 	categoryForm.findField("categoryId").setValue("");
					categoryForm.findField("categoryName").setValue("");
					categoryForm.findField("categoryCode").setValue("");
					categoryForm.findField("parentId").setValue("");
					categoryForm.findField("treeSign").setValue("");*/
				 }
			}],
			rbar : [{
				xtype : 'button',
				tooltip : '添加根类别',
				iconCls : 'tree_model_add',
				ref : 'treeIns'
			},{
				xtype : 'button',
				tooltip : '添加子类别',
				iconCls : 'tree_func_add',
				disabled : true,
				ref : 'treechildIns'
			}, {
				xtype : 'button',
				tooltip : '删除类别',
				iconCls : 'tree_delete',
				disabled : true,
				ref : 'treeDel'
			}] 		
		});