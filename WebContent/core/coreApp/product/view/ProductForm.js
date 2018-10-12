Ext.define('core.product.view.ProductForm', {
    extend: 'Ext.form.Panel',
    alias : 'widget.productform',
    //requires: ['Ext.form.Panel'],
    //height: 380,
   // width: 300,
	bodyStyle: 'padding:5px 5px 5px 5px',
	border:0,
	//frame:true,
	enctype: 'multipart/form-data',//把文件以二进制流的方式传递到服务器
	fileUpload : true,
	tbar:[{
		xtype:"button",
		ref:"save",
		iconCls:"table_save",
		text:"保存"
	},{
        text: '重置',
        ref:"reset"
    },{
		xtype:"button",
		ref:"return",
		iconCls:"return",
		text:"返回"
	}],
	items:[{
	    bodyStyle: 'background:transparent',//设置为透明,不妨碍更换主题了
		border:0,
	    layout:'column',
	    items: [{
	        columnWidth: 0.5,
	        xtype:'fieldset',
			title:'商品基本信息',
	        items:[{
		        xtype: 'textfield',
                name : 'id',
                fieldLabel: 'id',
                labelWidth:60,
                allowBlank: false,
                hidden : true
	        },{
		        xtype: 'textfield',
                name : 'name',
                fieldLabel: '商品名称',
                labelWidth:60,
                allowBlank: false
	        },{
		        xtype:'combobox',
				fieldLabel: '商品类别',
				name : 'categoryId',
				//editable: false,
			    store:Ext.create("core.product.store.CategoryStore",{}),
			    forceSelection: true,
			    queryMode: 'remote',
			    displayField: 'text',
			    valueField: 'categoryId',
			    labelWidth:60
	        },{
		        xtype: 'numberfield',
                 name : 'normaPrice',
                 fieldLabel: '市场价',
                 labelWidth:60
	        },{
		        xtype: 'numberfield',
                name : 'memberPrice',
                fieldLabel: '会员价',
                labelWidth:60
	        },{
		        xtype: 'textareafield',
                name : 'descr',
                fieldLabel: '描述',
                labelWidth:60
	        },{
	        	height:153,
	        	bodyStyle: 'background:transparent',//设置为透明,不妨碍更换主题了
				border:0
	        }]
	    },{width:5,bodyStyle: 'background:transparent',border:0},{
	        columnWidth: 0.5,
	        xtype:'fieldset',
			title:'照片上传',
	        items:[{
	        	xtype:'filefield',
				name:'photos',
		       	labelWidth:60,
		       	msgTarget: 'side',
		       	anchor: '100%',
		       	buttonText: '选择照片',
		       	fieldLabel : '上传图片'
			},{
			   xtype : 'image',   
		       width:400,
		       height:300
			}]
	    }]

                
    }],
    initComponent: function() {
        this.callParent(arguments);
    }
});
