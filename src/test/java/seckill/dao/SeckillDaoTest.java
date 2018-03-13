package seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 * @author Chen Weilin
 */

/*
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test, junit
 * 告诉junit spring配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SeckillDaoTest {

	//注解实现注入
	@Resource
	private SeckillDao seckilldao;
	
	@Test
	public void test1(){
		Seckill seckill = seckilldao.queryById(1000);
		System.out.println(seckill);
	}

	@Test
	public void test2(){
		List<Seckill> list = seckilldao.queryAll(0, 10);
		for(Seckill item : list){
			System.out.println(item);
		}
	}

	@Test
	public void test3(){
		System.out.println(seckilldao.reduceNumber(1000, new Date()));
	}
	
	
}
