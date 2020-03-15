package com.hrxb.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.hrxb.ssm.entity.Items;
import com.hrxb.ssm.service.ItemsService;
import com.hrxb.ssm.utils.MyException;
import com.hrxb.ssm.utils.Validator1;
import com.hrxb.ssm.vo.ItemsVO;

//@RequestMapping("items")
@Scope("prototype")
@Controller
public class ItemsController {

	@Autowired
	private ItemsService ItemsService;

	@RequestMapping("/findAllItems")
	public ModelAndView findAllItems(Integer pageNum, Integer pageSize) {
		if (null == pageNum) {
			pageNum = 1;
		}
		if (null == pageSize) {
			pageSize = 5;
		} // 执行查询
		PageInfo<Items> pageInfo = ItemsService.findAllItems(pageNum, pageSize);

		ModelAndView mv = new ModelAndView();
		// 封装model对象
		mv.addObject("items", pageInfo.getList());
		// 封装分页信息
		mv.addObject("pageInfo", pageInfo);
		// 封装view对象
		mv.setViewName("itemslist");
		return mv;

	}

	// 按条件查询
	@RequestMapping("/findItemsByCondition")
	public ModelAndView findItemsByCondition(ItemsVO itemsVO, Integer pageNum, Integer pageSize) {
		//System.out.println("======================");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		PageInfo<Items> pageInfo = ItemsService.findItemsByCondition(itemsVO, pageNum, pageSize);
		ModelAndView mv = new ModelAndView();
		// 封装model对象
		mv.addObject("items", pageInfo.getList());
		// 封装分页信息
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("itemsVO", itemsVO);
		mv.setViewName("itemslist");
		return mv;

	}

	// @Valid 表示在封装Items对象之前进行合法校验
	// BindingResult 把不合法的对象提示消息封装到BindingResult
	// 执行增加
	@RequestMapping("/addItems")
	public String addItems(Model model, @Validated(value = Validator1.class) Items items, BindingResult result) {
		// 如果有错误消息不在执行后续操作，转发到增加页面，并进行错误提示
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			// 转发到增加页面
			return "itemsAdd";
		}
		ItemsService.insert(items);
		return "redirect:findAllItems";

	}

	// 跳转到添加页面
	@RequestMapping("/toAddItems")
	public String toAddItems(Items items) {

		return "itemsAdd";
	}

	// 删除
	@RequestMapping(value = "/deleteItemsById", method = RequestMethod.GET)
	public String deleteItemsById(@RequestParam(value = "idx", required = true, defaultValue = "") Integer id)
			throws MyException {

		try {
			// int i =1/0;
			ItemsService.deleteByPrimaryKey(id);

		} catch (Exception e) {
			throw new MyException("该商品正在被使用，不能删除");
		}

		// ItemsService.deleteByPrimaryKey(id);
		return "redirect:findAllItems";

	}

	// 跳转到修改商品页面
	@RequestMapping("/toUpdateItems")
	public String toUpdateItems(Model model, Integer id) {
		Items items = ItemsService.selectByPrimaryKey(id);
		model.addAttribute("items", items);
		// 跳转到主页面
		return "itemsUpdate";

	}

	// 执行修改
	@RequestMapping("/updateItems")
	public String updateItems(Items items,MultipartFile items_pic) throws IllegalStateException, IOException {
		//获取上传文件的原始名称
		String oldFilename = items_pic.getOriginalFilename();
		
		if(items_pic!=null&&!"".equals(items_pic)){
			//给文件起名字   避免上传文件名重复 
			String newFilename = UUID.randomUUID()+oldFilename.substring(oldFilename.lastIndexOf("."));
			//文件保存位置
			String path="d:\\pic\\";
			File file = new File(path+newFilename);
			items_pic.transferTo(file);
			//存储文件名称
			items.setPic(newFilename);
		}		

		ItemsService.updateByPrimaryKeyWithBLOBs(items);
		// 重定向到主页面
		return "redirect:findAllItems";
	}

	// 多文件上传
		@RequestMapping("/updateItemsMoreFile")
		public String updateItemsMoreFile(Items items, @RequestParam CommonsMultipartFile items_pic[])
				throws IllegalStateException, IOException {
			for (int i = 0; i < items_pic.length; i++) {
				String oldFilename = items_pic[i].getOriginalFilename();
				if (!items_pic[i].isEmpty()) {
					String newFilename = UUID.randomUUID()
							+ oldFilename.substring(oldFilename.lastIndexOf("."), oldFilename.length());
					// 文件保存的路径
					String path = "d:\\pic\\";
					File file = new File(path + newFilename);
					// 把文件写入硬盘
					items_pic[i].transferTo(file);
					// 存储文件名称
					items.setPic(newFilename);
				}
				ItemsService.updateByPrimaryKeyWithBLOBs(items);
			}
			// 重定向到主页面
			return "redirect:findAllItems";

		}
	
	
	
	// 批量删除
	@RequestMapping("/deleteBatch")
	public String deleteBatch(Integer[] itemsids) {
		ItemsService.deleteBatch(itemsids);
		// 重定向到主页面
		return "redirect:findAllItems";
	}

	@ModelAttribute
	public void test() {
		System.out.println("@ModelAttribute..............................");
	}

	/**
	 * 设置这个注解之后可以直接在前端页面使用hb这个对象（List）集合
	 */
	@ModelAttribute("hb")
	public List<String> hobbiesList() {
		List<String> hobbise = new LinkedList<String>();
		hobbise.add("basketball");
		hobbise.add("football");
		hobbise.add("tennis");
		return hobbise;
	}
}
