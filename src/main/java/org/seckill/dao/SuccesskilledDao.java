package org.seckill.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Successkilled;

/**
 * 成功秒杀记录dao
 * @author Chen Weilin
 */
public interface SuccesskilledDao {
	
	/**
	 * 插入成功秒杀记录
	 * @param seckillId
	 * @param phone
	 * @return 影响行数， 1插入成功 0失败
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("phone")long phone);
	
	/**
	 * 查询秒杀记录
	 * @param seckillId
	 * @param phone
	 * @return 秒杀成功实体类，其实体类的成员变量中含有秒杀商品
	 */
	Successkilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("phone")long phone);
}
