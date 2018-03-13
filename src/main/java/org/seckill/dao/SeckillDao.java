package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

/**
 * 秒杀商品的dao
 * @author Chen Weilin
 */
public interface SeckillDao {

	/**
	 * 单条秒杀商品
	 * @param id
	 * @return 商品
	 */
	Seckill queryById(long id);
	
	/**
	 * 秒杀商品全部查询
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);
	
	/**
	 * 秒杀商品后减小库存
	 * @param id
	 * @param killTime
	 * @return 秒杀成功返回1 失败返回0 
	 */
	int reduceNumber(@Param("id")long id, @Param("killTime")Date killTime);
}
