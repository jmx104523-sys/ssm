package com.hrxb.ssm.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hrxb.ssm.entity.Items;
import com.hrxb.ssm.vo.ItemsVO;

public interface ItemsService {

	/**
	 * 
	 * @param pageNum �ڼ�ҳ
	 * @param pageSize   ÿҳ��ʾ������
	 * @return
	 */
	// ��ѯ����
	PageInfo<Items> findAllItems(Integer pageNum,Integer pageSize);

	/**
	 *	
	 * @param pageNum	�ڼ�ҳ
	 * @param pageSize	ÿҳ��ʾ������
	 * @return
	 */
	// ��������ѯ
	PageInfo<Items> findItemsByCondition(ItemsVO itemsVO,Integer pageNum,Integer pageSize);

	// ���
	int insert(Items record);

	// ɾ��
	int deleteByPrimaryKey(Integer id);

	// �޸�
	int updateByPrimaryKeyWithBLOBs(Items record);

	// ��ѯ��Ʒ
	Items selectByPrimaryKey(Integer id);

	//����ɾ��
  	int deleteBatch(Integer[] itemsids);
  	

}
