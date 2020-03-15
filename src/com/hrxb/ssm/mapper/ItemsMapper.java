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
  //��ѯ����
  	List<Items> findAllItems();
  //��������ѯ
  	List<Items> findItemsByCondition(ItemsVO itemsVO);
  	
  	//����ɾ��
  	int deleteBatch(Integer[] itemsids);
}