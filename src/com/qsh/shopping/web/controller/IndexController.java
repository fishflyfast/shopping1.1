package com.qsh.shopping.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.qsh.shopping.model.Cart;
import com.qsh.shopping.model.CartItem;
import com.qsh.shopping.model.Category;
import com.qsh.shopping.model.Product;
import com.qsh.shopping.model.SaleItem;
import com.qsh.shopping.model.SalesOrder;
import com.qsh.shopping.model.User;
import com.qsh.shopping.model.dto.OrderFormDto;
import com.qsh.shopping.service.CategoryService;
import com.qsh.shopping.service.ProductService;
import com.qsh.shopping.service.SalesOrderService;
import com.qsh.shopping.service.UserService;

@Controller
public class IndexController {

	/**公共成员*/
	List<Product> productList;
	List<Product> productList25Last;
	List<Category> categoryList;
	
	private CategoryService categoryService;
	private UserService userService;
	private ProductService productService;
	private SalesOrderService salesOrderService;
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Resource
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SalesOrderService getSalesOrderService() {
		return salesOrderService;
	}

	@Resource
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception{
		/*展示分类*/
		List<Category> categoryList = categoryService.findAll();
		/*展示商品*/
		List<Product> productList = productService.findAll();
		/*展示最新的，依旧是最后的25条商品信息*/
		List<Product> productList25Last = new ArrayList<Product>();
		Collections.reverse(productList);
		int size = productList.size() >= 25 ? 25 : productList.size();
		for(int i = 0;i < size; i++){
			productList25Last.add(productList.get(i));
		}
		//购物车的数量放到首页
		Cart cart = (Cart)session.getAttribute("cart");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("categoryList", categoryList);
		mav.addObject("productList", productList);
		if(cart != null && cart.getList() != null){
			mav.addObject("size", cart.getList().size());
		}
		mav.addObject("productList25Last", productList25Last);
		return mav;
	} 
	
	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	public ModelAndView userLogin(HttpServletRequest request, HttpSession session) throws Exception{
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		ModelAndView mav = new ModelAndView();
		if(userName != null && !"".equals(userName) || password != null && !"".equals(password)){
			User user = userService.login(userName, password);
			if(null != user){
				mav.addObject("username", user.getName());
				session.setAttribute("user", user);
			}else{
				mav.addObject("username", "用户名或密码错误！");
			}
		}
		mav.setViewName("index");
		return mav;
	}
	
	/**注销*/
	@RequestMapping("/unlogin")
	public ModelAndView unLogin(HttpServletRequest request,HttpSession session) throws Exception {
		//这里去找用户
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("user");
		session.removeAttribute("orderCode");
		session.removeAttribute("serial");
		qsh(mav);
		mav.setViewName("index");
		return mav;
	}
	
	private void qsh(ModelAndView mav){
		//展示商品
		productList = productService.findAll();
		//展示最后的25要商品信息，也就是最新的商品
		productList25Last = new ArrayList<Product>();
		java.util.Collections.reverse(productList);
		for(int i=0;i<productList.size();i++){
			if(i<25){
				productList25Last.add(productList.get(i));
			}else{
				break;
			}
		}
		//展示分类
		categoryList = categoryService.findAll();
		mav.setViewName("index");
		mav.addObject("categoryList", categoryList);
		mav.addObject("productList", productList);
		mav.addObject("productList25Last", productList25Last);
	}
	/**
	 * 购买商品
	 */
	@RequestMapping("/buy")
	public ModelAndView buy(HttpServletRequest request, CartItem item, HttpSession session){
		//首先判断用户是否登录，如果没有登录就返回注册页面
		ModelAndView mav = new ModelAndView();
		User u = (User)session.getAttribute("user");
		if(null == u){
			mav.setViewName("register");
			return mav;
		}
		Cart cart = (Cart)session.getAttribute("cart");
		if(null == cart){
			cart = new Cart();
			session.setAttribute("cart", cart);//购物车放进Session中去
		}
		CartItem cartitem = new CartItem();
		cartitem.setProductId(item.getProductId());
		cartitem.setProductName(item.getProductName());
		cartitem.setCount(1);
		cartitem.setPrice(item.getPrice());
		cart.add(cartitem);
		
		mav.setViewName("cart");//返回的页面
		mav.addObject("cartList", cart.getList());
		return mav;
	}
	
	/**
	 * 修改数量
	 */
	@RequestMapping("/buy_update")
	public ModelAndView buyUpdate(HttpServletRequest request, CartItem item, HttpSession session){
		ModelAndView mav = new ModelAndView();
		List<CartItem> list;
		Cart cart = (Cart)session.getAttribute("cart");
		list = cart.getList();
		for(CartItem cItem : list){
			String count = request.getParameter("p" + cItem.getProductId());
			if(!StringUtils.isEmptyOrWhitespaceOnly(count)){
				cItem.setCount(Integer.parseInt(count));
			}
		}
		
		/**
		 * 下订单
		 */
		String confirm = request.getParameter("confirm");
		if(null != confirm && !"".equals(confirm)){
			User user = (User)session.getAttribute("user");
			mav.setViewName("confirm");
			mav.addObject("cartList", list);
			mav.addObject("size", list.size());
			mav.addObject("user", user);
			return mav;
		}
		mav.setViewName("cart");
		mav.addObject("cartList", list);
		mav.addObject("msg", "修改成功");
		mav.addObject("size", list.size());
		mav.addObject("totalPrice", cart.getTotalPrice());
		return mav;
	}
	
	@RequestMapping("searchCategory")
	public ModelAndView searchCategory(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String categoryId = request.getParameter("categoryId");
		if(productList25Last == null){
			productList25Last = new ArrayList<Product>();
		}
		productList25Last.clear();//为什么报了个空指针异常
		//展示分类
		categoryList = categoryService.findAll();
		//展示商品
		productList = productService.findAll();
		for(Product p : productList){
			if(p.getId() == Integer.parseInt(categoryId)){
				productList25Last.add(p);
			}
			
		}
		mav.setViewName("index");
		mav.addObject("categoryList", categoryList);
		mav.addObject("productList", productList);
		mav.addObject("productList25Last", productList25Last);
		return mav;
	}
	
	/**确定定单confirm_order.do*/
	@RequestMapping("/confirm_order")
	public ModelAndView confirmOrder(HttpServletRequest request,OrderFormDto orderFormDto,HttpSession session) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		
		SalesOrder order = new SalesOrder();
		order.setUser(request.getParameter("user"));
		order.setOrderCode(request.getParameter("orderCode"));
		order.setOdate(new Timestamp(System.currentTimeMillis()));
		order.setPhone(request.getParameter("phone"));
		order.setQQ(request.getParameter("qq"));
		order.setAddr(request.getParameter("address"));
		order.setRemark(request.getParameter("remark"));
		
		Set<SaleItem> saleItems = new HashSet<SaleItem>();
		List<SaleItem> items = orderFormDto.getSaleitems();
		for(SaleItem item : items){
			//item.setProductId(item.g)
			saleItems.add(item);
		}
		
		order.setSaleItems(saleItems);
		
		if(salesOrderService.add(order)){
			mav.setViewName("redirect:/orderend.jsp");
			//那么就清空购物车
			session.removeAttribute("cart");
			session.removeAttribute("serial");
		}else{
			mav.setViewName("confirm");
			mav.addObject("msg", "定单没有提交成功！请检查你的网络再试");
		}
		return mav;
	}
	/**用户名检查usernameValidate.do
	 * @throws IOException */
	@RequestMapping("/usernameValidate")
	public void usernameValidate(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException {
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-store");//HTTP 1.1
		response.setHeader("Pragma", "no-store");//HTTP 1.0
		response.setDateHeader("Expires", 0);//prevents caching at the proxy server
		//true就是用户名存在，false就是不存在
		String username = request.getParameter("username");
		
		if(userService.checkUserName(username)){
			response.getWriter().write("<msg>true</msg>");
		}else{
			response.getWriter().write("<msg>false</msg>");
		}
	}
	
	/**用户注册user_register.do
	 * @throws Exception */
	@RequestMapping("/user_register")
	public ModelAndView userRegister(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(Long.parseLong(request.getParameter("phone")));
		if(!request.getParameter("email").equals("")){
			user.setEmail(request.getParameter("email"));
		}
		if(!request.getParameter("QQ").equals("")){
			user.setQQ(Long.parseLong(request.getParameter("QQ")));
		}
		user.setSex(Boolean.parseBoolean(request.getParameter("sex")));
		user.setIP(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		if(userService.checkUserName(request.getParameter("username"))){
			mav.setViewName("register");
			mav.addObject("msg", "此用户名已存在！注册失败！");
			return mav;
		}
		
		if(null != userService.register(user)){
			mav.setViewName("redirect:/index.do");
			session.setAttribute("user", user);
		}else{
			mav.setViewName("register");
			mav.addObject("msg", "注册失败");
		}
		return mav;
	}
	/**根据用户名查询对应的定单searchOrderByUsername.do?username=${user.name }*/
	@RequestMapping("/searchOrderByUsername")
	public ModelAndView searchOrderByUsername(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String username = request.getParameter("username");
		List<SalesOrder> orders = this.salesOrderService.findByOderUser(username);
		try{
			SalesOrder order = orders.get(0);
			Set<SaleItem> items =order.getSaleItems();
			double totalPrice=0.0;
			for(SaleItem item:items){
				totalPrice += item.getNumber()*item.getPrice();
			}
			
			if(orders != null){
				mav.addObject("orders", orders);
				mav.addObject("totalPrice", totalPrice);
				mav.setViewName("order");
			}
		}catch(IndexOutOfBoundsException e){
			mav.setViewName("order");
			return mav;
		}
		return mav;
	}
	
	/**查询定单详细信息orderDetail.do*/
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String orderCode = request.getParameter("orderCode");
		List<SalesOrder> orders = salesOrderService.findByOderCode(orderCode);
		
		SalesOrder order = orders.get(0);
		Set<SaleItem> items =order.getSaleItems();
		double totalPrice=0.0;
		for(SaleItem item:items){
			totalPrice += item.getNumber()*item.getPrice();
		}
		if(orders != null){
			mav.addObject("order", order);
			mav.addObject("items", items);
			mav.addObject("totalPrice", totalPrice);
			mav.setViewName("orderdetail");
		}
		return mav;
	}
	
	/**根据关键字查询searchKeyword.do*/
	@RequestMapping("/searchKeyword")
	public ModelAndView searchKeyword(HttpServletRequest request,OrderFormDto orderFormDto,HttpSession session) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		
		String keyword = request.getParameter("keyword");
		List<Product> productList25Last=productService.findByKeyword(keyword);
		//展示分类
		categoryList = categoryService.findAll();
		//展示商品
		productList = productService.findAll();
		mav.setViewName("index");
		mav.addObject("categoryList", categoryList);
		mav.addObject("productList", productList);
		mav.addObject("productList25Last", productList25Last);
		return mav;
	}
}
