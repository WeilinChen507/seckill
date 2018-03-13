package org.seckill.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Successkilled;

/**
 * �ɹ���ɱ��¼dao
 * @author Chen Weilin
 */
public interface SuccesskilledDao {
	
	/**
	 * ����ɹ���ɱ��¼
	 * @param seckillId
	 * @param phone
	 * @return Ӱ�������� 1����ɹ� 0ʧ��
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("phone")long phone);
	
	/**
	 * ��ѯ��ɱ��¼
	 * @param seckillId
	 * @param phone
	 * @return ��ɱ�ɹ�ʵ���࣬��ʵ����ĳ�Ա�����к�����ɱ��Ʒ
	 */
	Successkilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("phone")long phone);
}
