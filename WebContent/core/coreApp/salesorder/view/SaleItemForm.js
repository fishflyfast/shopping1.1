Ext.define("core.salesorder.view.SaleItemForm",{
	extend:"Ext.form.Panel",
	alias:"widget.saleitemform",
	frame:true,
	algin:'center',
	tbar:[{
		xtype:"button",
		ref:"check",
		iconCls:"table_save",
		text:"审核"
	},{
		xtype:"button",
		ref:"nullify",
		iconCls:"nullify",
		text:"作废"
	},{
		xtype:"button",
		ref:"return",
		iconCls:"return",
		text:"返回"
	}],
	items:[
		{
		 	bodyStyle:'padding:1px 0px 0px 0px;background:transparent',
		 	border:0,
		 	layout : "absolute",
			items : [{
						x : 400,
						y : 0,
						ref : "dingdanInfo",
						xtype : "displayfield",
						id:"dingdan",
						value : ""
					}]
		},
		{
			title:'定单基本信息',
			xtype:'fieldset',
			items:[
				{
					bodyStyle:'padding:10px 0px 0px 55px;background:transparent',
					border:0,
					//frame:true,
			        layout:'column',
				    items: [{
				        columnWidth: 0.33,
				        bodyStyle: 'background:transparent',//设置为透明,不不妨碍更换主题了
						border:0,
				        items:[{
					        xtype: 'textfield',
			                name : 'user',
			                fieldLabel: '下单人',
			                labelWidth:60,
			                readOnly : true
				        }]
				    },{
				        columnWidth: 0.33,
				        bodyStyle: 'background:transparent',//设置为透明,不不妨碍更换主题了
						border:0,
				        items:[{
					        xtype: 'textfield',
			                name : 'orderCode',
			                fieldLabel: '定单编号',
			                labelWidth:60,
			                readOnly : true
				        },{
					        xtype: 'textfield',
			                name : 'id',
			                fieldLabel: 'ID',
			                labelWidth:60,
			                hidden : true
				        }]
				        
				    },{
				        columnWidth: 0.33,
				        bodyStyle: 'background:transparent',//设置为透明,不不妨碍更换主题了
						border:0,
				        items:[{
					        xtype: 'textfield',
			                name : 'odate',
			                fieldLabel: '下单日期',
			                labelWidth:60,
			                readOnly : true
				        }]
				    }]
			    },{//备注和送货地址
			    	//layout:'form',
					border:0,
					bodyStyle:'padding:0px 90px 0px 20px;background:transparent',
					layout:'column',
			    	items:[{
			    		columnWidth: 0.5,
			    		xtype: 'textfield',
		                name : 'phone',
		                fieldLabel: '电话',
		                align:'right',
		                labelWidth:60
			    	},{
			    		columnWidth: 0.5,
			    		xtype: 'textfield',
		                name : 'QQ',
		                fieldLabel: 'QQ',
		                labelWidth:30
			    	}]
			    },{
			    	xtype: 'textfield',
	                name : 'addr',
	                fieldLabel: '送货地址',
	                labelWidth:60,
	                margin :'5 0 0 0',
	                width: 795 
			    }
			]
		},
		{//这里是一个grid
	    	title:'对应的商品信息',
	    	xtype:'fieldset',
	    	layout:'fit',
	    	items:[{
	    		xtype:'saleitemgrid'
	    	}]
	    }
	],
	initComponent:function(){
		this.callParent(arguments);
	}
});