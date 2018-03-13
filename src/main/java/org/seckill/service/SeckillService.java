package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.KillClosedException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;

/**
 * ��ɱҵ��ӿ���
 * @author Chen Weilin
 */
public interface SeckillService {
	
	/**
	 * ���������ɱ��Ʒ�б�
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��õ�����ɱ��Ʒ
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��¶��ɱ�ӿ�
	 * @param seckillId
	 * @return ��װ�õĶ���
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * �����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return ��װ�õĲ�������
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, KillClosedException, RepeatKillException;
}
