package org.seckill.exception;

/**
 * �쳣�ܽӿ�
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
