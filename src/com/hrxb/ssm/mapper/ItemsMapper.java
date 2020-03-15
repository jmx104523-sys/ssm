package com.hrxb.ssm.mapper;

import java.util.List;

import com.hrxb.ssm.entity.Items;
import com.hrxb.ssm.vo.ItemsVO;

public interface ItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Items record);

    int insertSelective(Items record);

    Items selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKeyWithBLOBs(Items record);

    int updateByPrimaryKey(Items record);
  //查询所有
  	List<Items> findAllItems();
  //按条件查询
  	List<Items> findItemsByCondition(ItemsVO itemsVO);
  	
  	//批量删除
  	int deleteBatch(Integer[] itemsids);
}