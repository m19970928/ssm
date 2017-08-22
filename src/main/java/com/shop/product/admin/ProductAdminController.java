package com.shop.product.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.shop.category.entity.CategorySecond;
import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.util.PageBean;

/**
 * 由于订单条目与商品之间有外键关系，所以无法直接删除商品
 * 	  在mysql数据库中，将外键级联删除设置为 SET NULL
 *   即删除商品时订单条目将其设置为空
 */


@Controller
@RequestMapping("/productAdmin")
public class ProductAdminController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	

	// 查询全部商品
	@RequestMapping("/selectProductAll")
	public String selectProductAll(HttpServletRequest request, int page){
		// 查询全部商品
		PageBean<Product> pageBean = productService.selectProductAll(page);
		request.setAttribute("pageBean", pageBean);
		
		return "admin/product/list";
	}
	
	// 添加商品
	@RequestMapping("/insertProduct")
	public String insertProduct(Product product, MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException{
		product.setPdate(new Date());
		if(file != null){
			// 获得上传图片的服务器端路径.
			String path = session.getServletContext().getRealPath("/products/2");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + file.getOriginalFilename());
			// 文件上传:
			//获取输出流
            OutputStream os=new FileOutputStream(diskFile);
			//获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
                os.flush();
            }
            os.close();
            is.close();
            
			
			product.setImage("products/2/" + file.getOriginalFilename());
			
		}
		productService.insertProduct(product);
		
		
		return "forward:/productAdmin/selectProductAll";
	}
	
	// 删除商品
	@RequestMapping("/deleteProduct")
	public String deleteProduct(Integer pid, HttpServletRequest request){
		productService.deleteProductByPid(pid);
		
		return "forward:/productAdmin/selectProductAll";
	}
	
	// 修改商品
	@RequestMapping("/updateProduct")
	public String updateProduct(Product product, HttpServletRequest request){
		productService.updateProduct(product);

		return "forward:/productAdmin/selectProductAll";
	}
	
	// 根据商品id查询商品详细信息
	@RequestMapping("/selectProductByPid")
	public String selectProductByPid(Integer pid, HttpServletRequest request){
		Product product = productService.selectProductByPid(pid);
		request.setAttribute("product", product);
		return "admin/product/edit";
	}
	
	// 跳转到添加页面
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request){
		// 查询二级分类
		List<CategorySecond> categorySecondList = categoryService.selectCategorySecondsAll();
		request.setAttribute("categorySecondList", categorySecondList);
		return "admin/product/add";
	}
}
