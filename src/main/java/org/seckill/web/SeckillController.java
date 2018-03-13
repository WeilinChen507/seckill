package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.compiler.MemberResolver.Method;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnums;
import org.seckill.exception.KillClosedException;
import org.seckill.exception.RepeatKillException;
import org.seckill.service.SeckillService;
import org.seckill.service.impl.SeckillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 秒杀系统控制器
 * @author Chen Weilin
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	private SeckillService seckillService;
	
	/**
	 * 获取列表
	 * url /seckill/list
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model){
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	/**
	 * 详情页
	 * url /seckill/{id}/detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId")Long seckillId,Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	/**
	 * 获得当前系统时间
	 * url /seckill/time/now
	 * @return
	 */
	@RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody //转换成json格式
	public SeckillResult<Long> time() {
		long now =  new Date().getTime();
		return new SeckillResult<Long>(true, now);
	}
	
	/**
	 * 暴露秒杀
	 * url /seckill/{seckillId}/exposer
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId) {
		SeckillResult<Exposer> seckillResult = null;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			seckillResult = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			return new SeckillResult<Exposer>(false, e.getMessage());
		}
		return seckillResult;
	}
	/**
	 * 执行秒杀操作
	 * 此处md5并非md5加密，而是采用自行加密方法
	 * url /seckill/{seckillId}/{md5}/execution
	 * @param seckillId
	 * @param md5
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execution(@PathVariable("seckillId")Long seckillId, 
													@PathVariable("md5")String md5,
													@CookieValue(value = "userPhone",required = false)Long userPhone) {
		SeckillResult<SeckillExecution> seckillResult = null;
		SeckillExecution seckillExcution = null;
		try {
			seckillExcution = seckillService.executeSeckill(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExcution);
		} catch (KillClosedException e) {
			seckillExcution = new SeckillExecution(seckillId, SeckillStateEnums.END);
			return new SeckillResult<SeckillExecution>(true,seckillExcution);
		} catch (RepeatKillException e) {
			seckillExcution = new SeckillExecution(seckillId, SeckillStateEnums.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true,seckillExcution);
		} catch (Exception e) {
			seckillExcution = new SeckillExecution(seckillId, SeckillStateEnums.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true,seckillExcution);
		}
	}
	
	
}
