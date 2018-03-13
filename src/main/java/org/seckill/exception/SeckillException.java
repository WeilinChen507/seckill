package org.seckill.exception;

/**
 * 异常总接口
 * @author Chen Weilin
 *
 */
public class SeckillException extends RuntimeException{
	
	public SeckillException(String msg) {
		super(msg);
	}

	public SeckillException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
