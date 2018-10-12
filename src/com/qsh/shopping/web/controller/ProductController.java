package com.qsh.shopping.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.model.Product;
import com.qsh.shopping.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;
	
	
	public ProductService getProductService() {
		return productService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 分页查询商品信息./product/list_product.do
	 */
	@RequestMapping(value="/listall_product",method=RequestMethod.GET)
	public void listAllProduct(HttpServletRequest request, PrintWriter writer) {
		
		List<Product> products = productService.findAll();
		int len = products.size();
		String preStr = "{totalCount:" + productService.count() + ",rows:[";
		String cenStr = "";
		int i=0;
		String douhao = ",";
		for(Product product : products) {
			i++;
			cenStr += "{id:'"+product.getId()+"', " +
					"name:'"+product.getName()+"'," +
					"descr:'"+product.getDescr()+"'," +
					"normaPrice:'"+product.getNormaPrice()+"'," +
					"memberPrice:'"+product.getMemberPrice()+"'," +
					"pdate:'"+product.getPdate()+"'," +
					"photo:'"+product.getPhoto()+"'," +
					"category:'"+product.getCategory().getText()+"'" +
			"}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}

	/**
	 * 分页查询商品信息./product/list_product.do
	 */
	@RequestMapping(value="/list_product",method=RequestMethod.GET)
	public void listProduct(HttpServletRequest request, PrintWriter writer) {
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");
		
		List<Product> products = productService.findAll(Integer.parseInt(startStr), Integer.parseInt(limitStr));
		int len = products.size();
		String preStr = "{totalCount:" + productService.count() + ",rows:[";
		String cenStr = "";
		int i=0;
		String douhao = ",";
		for(Product product : products) {
			i++;
			cenStr += "{id:'"+product.getId()+"', " +
					"name:'"+product.getName()+"'," +
					"descr:'"+product.getDescr()+"'," +
					"normaPrice:'"+product.getNormaPrice()+"'," +
					"memberPrice:'"+product.getMemberPrice()+"'," +
					"pdate:'"+product.getPdate()+"'," +
					"photo:'"+product.getPhoto()+"'," +
					"category:'"+product.getCategory().getText()+"'" +
			"}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
	
	/**新增产品product/add_product.do*/
	@RequestMapping(value="/add_product",method=RequestMethod.POST)
	public void addProduct(@RequestParam MultipartFile photos,HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
		String name = request.getParameter("name");
		String categoryId = request.getParameter("categoryId");
		String memberPrice = request.getParameter("memberPrice");
		String normaPrice = request.getParameter("normaPrice");
		String descr = request.getParameter("descr");

		Product product = new Product();
		product.setName(name);
		product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100)/100.0);
		product.setNormaPrice(Math.round(Float.parseFloat(normaPrice) * 100)/100.0);
		product.setDescr(descr);
		Category category = new Category();
		category.setId(Integer.parseInt(categoryId));
		product.setCategory(category);
		product.setPdate(new Timestamp(System.currentTimeMillis()));
		
		
		if(photos.isEmpty()){
			System.out.println("文件未上传");
			product.setPhoto("default.jpg");
		}else{
			product.setPhoto(photos.getOriginalFilename());
			//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			String realPath = request.getSession().getServletContext().getRealPath("/images/product");
			//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
		}
		
		/**解决乱码问题*/
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;  charset=UTF-8");
		
		if(null != productService.add(product)){
			writer.write("{success:true, msg:'商品新增成功!'}");
		}else{
			writer.write("{success:false, msg:'商品新增失败!'}");
		}
	}
	/**删除商品product/remove_product.do*/
	@RequestMapping(value="/remove_product",method=RequestMethod.POST)
	public void deleteProduct(HttpServletRequest request, PrintWriter writer) {
		
		
		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
		
		if(productService.remove(ids)){
			writer.write("{success:true,msg:'删除成功!'}");
		}else{
			writer.write("{success:false,msg:'删除失败!'}");
		}
		
	}
	
	/**修改商品信息product/update_product.do*/
	@RequestMapping(value="/update_product",method=RequestMethod.POST)
	public void updateProduct(@RequestParam MultipartFile photos,HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String categoryId = request.getParameter("categoryId");
		String memberPrice = request.getParameter("memberPrice");
		String normaPrice = request.getParameter("normaPrice");
		String descr = request.getParameter("descr");

		Product product = new Product();
		product.setId(Integer.parseInt(id));
		if(null != name && !name.equals("")){
			product.setName(name);
		}
		product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100)/100.0);
		product.setNormaPrice(Math.round(Float.parseFloat(normaPrice) * 100)/100.0);
		product.setDescr(descr);
		Category category = new Category();
		category.setId(Integer.parseInt(categoryId));
		product.setCategory(category);
		product.setPdate(new Timestamp(System.currentTimeMillis()));
		
		
		if(photos.isEmpty()){
			//System.out.println("文件未上传");
			//product.setPhoto("default.jpg");
		}else{
			product.setPhoto(photos.getOriginalFilename());
			//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			String realPath = request.getSession().getServletContext().getRealPath("/images/product");
			//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
		}
		
		/**解决乱码问题*/
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;  charset=UTF-8");
		
		if(productService.modify(product)){
			writer.write("{success:true, msg:'商品修改成功!',photo:'"+photos.getOriginalFilename()+"'}");
		}else{
			writer.write("{success:false, msg:'商品修改失败!'}");
		}
	}
}
