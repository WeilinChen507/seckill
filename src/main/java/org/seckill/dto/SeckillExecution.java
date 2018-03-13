package org.seckill.dto;

import org.seckill.entity.Successkilled;
import org.seckill.enums.SeckillStateEnums;

/**
 * ��ɱ��������dto
 * @author Chen Weilin
 *
 */
public class SeckillExecution {
	
	private long seckillId;
	
	//״̬
	private int state; 
	
	//��ɱ���״̬˵��
	private String stateInfo;
	
	//�ɹ�ʱ����ɱ����
	private Successkilled successkilled;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Successkilled getSuccesskilled() {
		return successkilled;
	}

	public void setSuccesskilled(Successkilled successkilled) {
		this.successkilled = successkilled;
	}

	public SeckillExecution(long seckillId, SeckillStateEnums sse, Successkilled successkilled) {
		super();
		this.seckillId = seckillId;
		this.state = sse.getState();
		this.stateInfo = sse.getStateInfo();
		this.successkilled = successkilled;
	}

	public SeckillExecution(long seckillId,SeckillStateEnums sse) {
		super();
		this.seckillId = seckillId;
		this.state = sse.getState();
		this.stateInfo = sse.getStateInfo();
	}
	
	
	
	
	
}
