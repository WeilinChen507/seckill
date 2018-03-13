package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.dsig.DigestMethod;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccesskilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.Successkilled;
import org.seckill.enums.SeckillStateEnums;
import org.seckill.exception.KillClosedException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService{

	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccesskilledDao successkillDao;
	
	private String MD5 = "sfiroijg#*&&$%GF@F^)+_9075sdadf";
	
	@Override
	public List<Seckill> getSeckillList() {
		List<Seckill> list = seckillDao.queryAll(0, 4);
		return list;
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		//秒杀还未开始或已经结束
		if (now.getTime() <= startTime.getTime()
				|| now.getTime() >= endTime.getTime()) {
			return new Exposer(seckillId, false, now.getTime(), startTime.getTime(), endTime.getTime());
		}
		//秒杀正进行
		//获得该商品的md5
		String md5 = MD5(seckillId);
		return new Exposer(seckillId, md5, true);
	}

	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, KillClosedException, RepeatKillException {
		try{
			String rightMd5 = MD5(seckillId);
			//md5匹配失败
			if (md5 == null || !md5.equals(rightMd5)) {
				throw new SeckillException("seckill data rewrite");
			}
			
			Seckill seckill = seckillDao.queryById(seckillId);
			Date now = new Date();
			//插入成功秒杀表
			int insertResult = successkillDao.insertSuccessKilled(seckillId, userPhone);
			if (insertResult <= 0) { //插入失败, 重复秒杀
				throw new RepeatKillException("repead kill");
			} else {
				//减库存
				int reduceNumber = seckillDao.reduceNumber(seckillId, new Date());
				//减少库存失败
				if (reduceNumber <= 0) {
					throw new KillClosedException("kill is closed");
				} else {
					Successkilled successkilled = successkillDao.queryByIdWithSeckill(seckillId, userPhone);		
					return new SeckillExecution(seckillId, SeckillStateEnums.SUCCESS , successkilled);
				}
			}
		} catch (RepeatKillException e1) {
			throw new RepeatKillException("repead kill");
		} catch (KillClosedException e2) {
			throw new KillClosedException("kill is closed");
		} catch (SeckillException e3) {
			throw new SeckillException("seckill data rewrite");
		} catch (Exception e) {
            throw new SeckillException("seckill inner error :" + e.getMessage());
		}
	
		
	}

	/**
	 * 根据活动的id生成随记的字符串
	 * @param seckillId
	 * @return
	 */
	String MD5(long seckillId) {
		String md5 = this.MD5 + "/" + seckillId;
		return DigestUtils.md5DigestAsHex(md5.getBytes());
	}
	
}
