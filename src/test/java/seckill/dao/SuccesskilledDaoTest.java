package seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SuccesskilledDao;
import org.seckill.entity.Successkilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 成功秒杀表的dao单元测试
 * @author Chen Weilin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( {"classpath:spring/spring-dao.xml"} )
public class SuccesskilledDaoTest {

	@Resource
	SuccesskilledDao successkilledDao;
	
	@Test
	public void test1(){
		System.out.println(successkilledDao.insertSuccessKilled(1000, 13145780665l));
	}

	@Test
	public void test2(){
		Successkilled successkilled = successkilledDao.queryByIdWithSeckill(1000, 13145780665l);
		System.out.println(successkilled);
		System.out.println(successkilled.getSeckill());
	}


}
