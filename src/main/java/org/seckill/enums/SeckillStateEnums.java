package org.seckill.enums;

/**
 * ö���࣬ ����
 * @author Chen Weilin
 *
 */
public enum SeckillStateEnums {

    SUCCESS(1,"��ɱ�ɹ�"),
    END(0,"��ɱ����"),
    REPEAT_KILL(-1,"�ظ���ɱ"),
    INNER_ERROR(-2,"ϵͳ�쳣"),
    DATE_REWRITE(-3,"���ݴ۸�");
	
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
