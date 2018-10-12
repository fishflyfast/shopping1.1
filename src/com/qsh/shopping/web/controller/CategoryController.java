package com.qsh.shopping.web.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qsh.shopping.model.Category;
import com.qsh.shopping.service.CategoryService;
import com.qsh.shopping.util.QshException;

/**
 * spring mvc 类别控制器
 * @author 邱盛华
 *
 */
@Controller
@RequestMapping("category")
@Scope("prototype")
public class CategoryController {
	private String str = "";
	CategoryService categoryService;
	
	public CategoryService getCategoryService() {
		return categoryService;
	}
	@Resource
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/**
	 * 类别查询,以树的形式展现到前台
	 */
	@RequestMapping(value="/list_category",method=RequestMethod.GET)
	public void listCategory(HttpServletRequest request, PrintWriter writer) {
		writer.write("["+this.outTreeJson(0)+"]");
	}
	//递归输入数据(一棵树)
	private String outTreeJson(int id){
		List<Category> categories =  categoryService.getCategories(id);
		for(Category category : categories){
			str += "{";
			if(!category.isLeaf()){//不是叶子的情况
				str +="id:'"+category.getId()+"',"+
				"text:'"+category.getText()+"',"+
				"leaf:'"+category.isLeaf()+"',"+
				"expandable:true,"+
				"description:'"+category.getDescription()+"',";
				if(id == 0){
					str += "parent:'root'";
				}else{
					str += "parent:'"+category.getParent()+"'";
				}
				str += ",children:[";
				//开始递归
				this.outTreeJson(category.getId());
			}else{//是叶子的情况
				str +="id:'"+category.getId()+"',"+
				"text:'"+category.getText()+"',"+
				"leaf:'"+category.isLeaf()+"',"+
				"expandable:true,"+
				"description:'"+category.getDescription()+"',";
				if(id == 0){
					str += "parent:'root'";
				}else{
					str += "parent:'"+category.getParent()+"'";
				}
				str += "},";
			}
		}
		//退出循环后
		if(id != 0){
			str = str.substring(0, str.length()-1);
			str += "]},";
		}else{
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	/**添加类别根节点./category/add_category.do*/
	@RequestMapping(value="/add_category",method=RequestMethod.POST)
	public void addCategory(HttpServletRequest request,PrintWriter writer) {
		
		String text = request.getParameter("text");
		String description = request.getParameter("description");
		String parent = request.getParameter("parent");
		String leaf = request.getParameter("leaf");
		
		if(parent.equals("root")){
			//添加根节点
			if(categoryService.addTopCategory(text, description)){
				writer.write("{success:true, msg:'根节点新增成功!'}");
			}else{
				writer.write("{success:true, msg:'根节点新增失败!'}");
			}
		}else{
			//添加子节点,把id=parent的节点变为非叶子,让新节点的parent=父节点的id
			Category category = new Category();
			category.setText(text);
			category.setDescription(description);
			
			if(parent.equals("root")){
				parent = "0";
			}
			
			if(categoryService.addChildCategory(Integer.parseInt(parent), category)){
				writer.write("{success:true, msg:'子节点新增成功!'}");
			}else{
				writer.write("{success:true, msg:'子节点新增失败!'}");
			}
		}
	}
	
	/**删除类别(根据id和pid)./category/delete_category.do*/
	@RequestMapping(value="/delete_category",method=RequestMethod.POST)
	public void deleteCategoryByIdAdnPid(HttpServletRequest request,PrintWriter writer) {
		
		String id = request.getParameter("id");
		String pid = request.getParameter("pid");
		if(pid.equals("root")){
			pid = "0";
		}
		try {
			if(categoryService.deleteById(Integer.parseInt(id), Integer.parseInt(pid))){
				writer.write("{success:true, msg:'类别删除成功!'}");
			}else{
				writer.write("{success:true, msg:'类别删除失败!'}");
			}
		}catch (QshException e) {
			// TODO Auto-generated catch block
			writer.write("{success:true, msg:'"+e+"'}");
		}
		return;
	}
	
	/*修改类别信息(根据id)./category/update_category.do*/
	@RequestMapping(value="/update_category",method=RequestMethod.POST)
	public void updateCategoryById(HttpServletRequest request,PrintWriter writer) {
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		String description = request.getParameter("description");
		String parent = request.getParameter("parent");
		String leaf = request.getParameter("leaf");
		Category category = new Category();
		category.setId(Integer.parseInt(id));
		category.setText(text);
		category.setDescription(description);
		if(categoryService.update(category)){
			writer.write("{success:true, msg:'类别修改成功!'}");
		}else{
			writer.write("{success:true, msg:'类别修改失败!'}");
		}
	}
	
	/**
	 * 显示到combobox的列表的store
	 * @param request
	 * @param writer
	 */
	@RequestMapping(value="/combo_category",method=RequestMethod.GET)
	public void listCategoryToCombobox(HttpServletRequest request, PrintWriter writer) {
		List<Category> categories = categoryService.findAll();
		int len = categories.size();
		String preStr = "{totalCount:" + len + ",rows:[";
		String cenStr = "";
		int i=0;
		String douhao = ",";
		for(Category c : categories) {
			i++;
			cenStr += "{categoryId:'"+c.getId()+"', " +
					"text:'"+c.getText()+"'" +
			"}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
	
}
