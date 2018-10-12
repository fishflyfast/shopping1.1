package com.qsh.shopping.web.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qsh.shopping.model.SaleItem;
import com.qsh.shopping.model.SalesOrder;
import com.qsh.shopping.service.SalesOrderService;

@Controller
@RequestMapping("/salesorder")
public class SalesOrderController {
	private SalesOrderService salesOrderService;
	
	public SalesOrderService getSalesOrderService() {
		return salesOrderService;
	}
	@Resource
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	/**定单列表*/
	@RequestMapping(value="/list_salesorder",method=RequestMethod.GET)
	public void listSalesOrder(HttpServletRequest request, PrintWriter writer) {
		
		List<SalesOrder> orders = salesOrderService.findALL();
		int len = orders.size();
		String preStr = "{totalCount:" + orders.size() + ",rows:[";
		String cenStr = "";
		int i=0;
		String douhao = ",";
		for(SalesOrder order : orders) {
			double totalPrice=0.0;
			Set<SaleItem> items=order.getSaleItems();
			
			for(SaleItem ie:items){
				totalPrice += ie.getPrice()*ie.getNumber();
			}
			i++;
			cenStr += "{user:'"+order.getUser()+"', " +
					"id:'"+order.getId()+"'," +
					"orderCode:'"+order.getOrderCode()+"'," +
					"status:'"+order.isStatus()+"'," +
					"odate:'"+order.getOdate()+"'," +
					"phone:'"+order.getPhone()+"'," +
					"QQ:'"+order.getQQ()+"'," +
					"addr:'"+order.getAddr()+"'," +
					"totalmoney:'"+Math.round(totalPrice * 100)/100.0+"'," +
					"remark:'"+order.getRemark()+"'" +
			"}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
	
	/**根据id显示定单详细信息./salesorder/show_salesorderdetail.do?id="+id;*/
	@RequestMapping(value="/show_salesorderdetail",method=RequestMethod.GET)
	public void showSalesOrderDetail(HttpServletRequest request, PrintWriter writer) {
		String id = request.getParameter("id");
		
		SalesOrder salesOrder = salesOrderService.findALL(id).get(0);
		int len = salesOrder.getSaleItems().size();
		
		Set<SaleItem> items=salesOrder.getSaleItems();
		
		String preStr = "{totalCount:" + items.size() + ",rows:[";
		String cenStr = "";
		int i=1;
		String douhao = ",";
		for(SaleItem item : items){
			
			cenStr += "{" +
					"id:'"+item.getId()+"', " +
					"name:'"+item.getProductName()+"'," +
					"productId:'"+item.getProductId()+"', " +
					"count:'"+item.getNumber()+"'," +
					"price:'"+item.getPrice()+"'," +
					"sum:'"+Math.round((item.getNumber()*item.getPrice()) * 100)/100.0+"'" +
			"}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
	
	/**审核定单./salesorder/check_salesorder.do*/
	@RequestMapping(value="/check_salesorder",method=RequestMethod.POST)
	public void checkSalesOrderDetail(HttpServletRequest request, PrintWriter writer) {
		String id = request.getParameter("id");
		if(salesOrderService.verify(Integer.parseInt(id))){
			writer.write("{success:true, msg:'审核成功!'}");
		}else{
			writer.write("{success:false, msg:'审核失败!'}");
		}
	}
}
