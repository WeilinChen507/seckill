package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.KillClosedException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;

/**
 * 秒杀业务接口类
 * @author Chen Weilin
 */
public interface SeckillService {
	
	/**
	 * 获得所有秒杀商品列表
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 获得单个秒杀商品
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 暴露秒杀接口
	 * @param seckillId
	 * @return 封装好的对象
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 完成秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return 封装好的操作对象
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, KillClosedException, RepeatKillException;
}
