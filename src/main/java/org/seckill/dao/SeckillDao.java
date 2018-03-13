package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

/**
 * ��ɱ��Ʒ��dao
 * @author Chen Weilin
 */
public interface SeckillDao {

	/**
	 * ������ɱ��Ʒ
	 * @param id
	 * @return ��Ʒ
	 */
	Seckill queryById(long id);
	
	/**
	 * ��ɱ��Ʒȫ����ѯ
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);
	
	/**
	 * ��ɱ��Ʒ���С���
	 * @param id
	 * @param killTime
	 * @return ��ɱ�ɹ�����1 ʧ�ܷ���0 
	 */
	int reduceNumber(@Param("id")long id, @Param("killTime")Date killTime);
}
