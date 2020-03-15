package com.hrxb.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrxb.ssm.entity.Items;
import com.hrxb.ssm.mapper.ItemsMapper;
import com.hrxb.ssm.vo.ItemsVO;


@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapper itemsMapper;
	
	/**pageNum  第几页
	 * pageSize  每页显示多少条
	 */
	@Override
	public PageInfo<Items> findAllItems(Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		//分页处理
		PageHelper.startPage(pageNum,pageSize);
		List<Items> items = itemsMapper.findAllItems();
		PageInfo<Items> pageInfo = new PageInfo<Items>(items);
		
		return pageInfo;
	}

	@Override
	public PageInfo<Items> findItemsByCondition(ItemsVO itemsVO,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		//分页处理
		PageHelper.startPage(pageNum,pageSize);
		List<Items> items = itemsMapper.findItemsByCondition(itemsVO);
		PageInfo<Items> pageInfo = new PageInfo<Items>(items);
		return pageInfo;
	}

	@Override
	public int insert(Items record) {
		// TODO Auto-generated method stub
		return itemsMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return itemsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Items record) {
		// TODO Auto-generated method stub
		return itemsMapper.updateByPrimaryKeyWithBLOBs(record);
		
	}

	@Override
	public Items selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return itemsMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public int deleteBatch(Integer[] itemsids) {
		// TODO Auto-generated method stub
		return itemsMapper.deleteBatch(itemsids);
	}


}
