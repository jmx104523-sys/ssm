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
		} // ִ�в�ѯ
		PageInfo<Items> pageInfo = ItemsService.findAllItems(pageNum, pageSize);

		ModelAndView mv = new ModelAndView();
		// ��װmodel����
		mv.addObject("items", pageInfo.getList());
		// ��װ��ҳ��Ϣ
		mv.addObject("pageInfo", pageInfo);
		// ��װview����
		mv.setViewName("itemslist");
		return mv;

	}

	// ��������ѯ
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
		// ��װmodel����
		mv.addObject("items", pageInfo.getList());
		// ��װ��ҳ��Ϣ
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("itemsVO", itemsVO);
		mv.setViewName("itemslist");
		return mv;

	}

	// @Valid ��ʾ�ڷ�װItems����֮ǰ���кϷ�У��
	// BindingResult �Ѳ��Ϸ��Ķ�����ʾ��Ϣ��װ��BindingResult
	// ִ������
	@RequestMapping("/addItems")
	public String addItems(Model model, @Validated(value = Validator1.class) Items items, BindingResult result) {
		// ����д�����Ϣ����ִ�к���������ת��������ҳ�棬�����д�����ʾ
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			// ת��������ҳ��
			return "itemsAdd";
		}
		ItemsService.insert(items);
		return "redirect:findAllItems";

	}

	// ��ת�����ҳ��
	@RequestMapping("/toAddItems")
	public String toAddItems(Items items) {

		return "itemsAdd";
	}

	// ɾ��
	@RequestMapping(value = "/deleteItemsById", method = RequestMethod.GET)
	public String deleteItemsById(@RequestParam(value = "idx", required = true, defaultValue = "") Integer id)
			throws MyException {

		try {
			// int i =1/0;
			ItemsService.deleteByPrimaryKey(id);

		} catch (Exception e) {
			throw new MyException("����Ʒ���ڱ�ʹ�ã�����ɾ��");
		}

		// ItemsService.deleteByPrimaryKey(id);
		return "redirect:findAllItems";

	}

	// ��ת���޸���Ʒҳ��
	@RequestMapping("/toUpdateItems")
	public String toUpdateItems(Model model, Integer id) {
		Items items = ItemsService.selectByPrimaryKey(id);
		model.addAttribute("items", items);
		// ��ת����ҳ��
		return "itemsUpdate";

	}

	// ִ���޸�
	@RequestMapping("/updateItems")
	public String updateItems(Items items,MultipartFile items_pic) throws IllegalStateException, IOException {
		//��ȡ�ϴ��ļ���ԭʼ����
		String oldFilename = items_pic.getOriginalFilename();
		
		if(items_pic!=null&&!"".equals(items_pic)){
			//���ļ�������   �����ϴ��ļ����ظ� 
			String newFilename = UUID.randomUUID()+oldFilename.substring(oldFilename.lastIndexOf("."));
			//�ļ�����λ��
			String path="d:\\pic\\";
			File file = new File(path+newFilename);
			items_pic.transferTo(file);
			//�洢�ļ�����
			items.setPic(newFilename);
		}		

		ItemsService.updateByPrimaryKeyWithBLOBs(items);
		// �ض�����ҳ��
		return "redirect:findAllItems";
	}

	// ���ļ��ϴ�
		@RequestMapping("/updateItemsMoreFile")
		public String updateItemsMoreFile(Items items, @RequestParam CommonsMultipartFile items_pic[])
				throws IllegalStateException, IOException {
			for (int i = 0; i < items_pic.length; i++) {
				String oldFilename = items_pic[i].getOriginalFilename();
				if (!items_pic[i].isEmpty()) {
					String newFilename = UUID.randomUUID()
							+ oldFilename.substring(oldFilename.lastIndexOf("."), oldFilename.length());
					// �ļ������·��
					String path = "d:\\pic\\";
					File file = new File(path + newFilename);
					// ���ļ�д��Ӳ��
					items_pic[i].transferTo(file);
					// �洢�ļ�����
					items.setPic(newFilename);
				}
				ItemsService.updateByPrimaryKeyWithBLOBs(items);
			}
			// �ض�����ҳ��
			return "redirect:findAllItems";

		}
	
	
	
	// ����ɾ��
	@RequestMapping("/deleteBatch")
	public String deleteBatch(Integer[] itemsids) {
		ItemsService.deleteBatch(itemsids);
		// �ض�����ҳ��
		return "redirect:findAllItems";
	}

	@ModelAttribute
	public void test() {
		System.out.println("@ModelAttribute..............................");
	}

	/**
	 * �������ע��֮�����ֱ����ǰ��ҳ��ʹ��hb�������List������
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
