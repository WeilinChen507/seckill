package org.seckill.enums;

/**
 * 枚举类， 常量
 * @author Chen Weilin
 *
 */
public enum SeckillStateEnums {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");
	
	private int state;
	
	private String stateInfo;

	private SeckillStateEnums(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SeckillStateEnums stateOf (int index) {
		for (SeckillStateEnums state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	
}
