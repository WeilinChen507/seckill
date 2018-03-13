package seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.exception.KillClosedException;
import org.seckill.exception.RepeatKillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 集成测试秒杀业务逻辑
 * @author Chen Weilin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( {
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"} )
public class SeckillServiceTest {
	
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testgetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		for(Seckill se : list) {
			System.out.println(se);
		}
	}
	
	@Test
	public void testgetById() {
		Seckill seckill = seckillService.getById(1001);
		System.out.println(seckill);
	}
	
	@Test
	public void test() throws Exception {
		long seckillId=1000;
        org.seckill.dto.Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {

            System.out.println(exposer);

            long userPhone=13472192876L;
            String md5=exposer.getMd5();

            try {
                org.seckill.dto.SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution.getStateInfo());
            } catch (RepeatKillException e) {
                e.printStackTrace();
            } catch (KillClosedException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println(exposer);
        }
	}
	
	
	
}
