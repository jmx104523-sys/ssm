package com.hrxb.ssm.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hrxb.ssm.entity.Items;
import com.hrxb.ssm.vo.ItemsVO;

public interface ItemsService {

	/**
	 * 
	 * @param pageNum 第几页
	 * @param pageSize   每页显示多少条
	 * @return
	 */
	// 查询所有
	PageInfo<Items> findAllItems(Integer pageNum,Integer pageSize);

	/**
	 *	
	 * @param pageNum	第几页
	 * @param pageSize	每页显示多少条
	 * @return
	 */
	// 按条件查询
	PageInfo<Items> findItemsByCondition(ItemsVO itemsVO,Integer pageNum,Integer pageSize);

	// 添加
	int insert(Items record);

	// 删除
	int deleteByPrimaryKey(Integer id);

	// 修改
	int updateByPrimaryKeyWithBLOBs(Items record);

	// 查询商品
	Items selectByPrimaryKey(Integer id);

	//批量删除
  	int deleteBatch(Integer[] itemsids);
  	

}
