package com.qsh.shopping.model;

import java.util.LinkedList;
import java.util.List;

/**
 * 购物车
 * @author 邱盛华
 */
public class Cart {
	List<CartItem> list = new LinkedList<CartItem>();

	public List<CartItem> getList() {
		return list;
	}

	public void setList(List<CartItem> list) {
		this.list = list;
	}
	
	/**往购物车哩放东西*/
	public void add(CartItem cartItem){
		for(CartItem item : list){
			if(item.getProductId() == cartItem.getProductId()){
				item.setCount(item.getCount()+1);
				//相同和产品只改变一下它的数量,不再往往做添加操作;直接返回
				return;
			}
		}
		list.add(cartItem);
	}
	/**删除购物车中不要的商品*/
	public boolean remove(CartItem cartItem){
		return list.remove(cartItem);
	}
	
	public double getTotalPrice(){
		double total = 0.0;
		for(CartItem item : list){
			total +=item.getTotalPrice();
		}
		return total;
	}
}
