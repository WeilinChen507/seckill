package org.seckill.dto;

/**
 * ��ɱ�ӿڱ�¶
 * ����ɱʱ�����������Ʒid��md5����exposedΪtrue
 * ����ʱ���������ʱ��
 * @author Chen Weilin
 */
public class Exposer {

	private long seckillId;
	
	private String md5;
	
	//�Ƿ񿪷�
	private boolean exposed;
	
	//��ǰϵͳʱ�� ����
	private long now;
	
	private long start;
	
	private long end;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public Exposer(long seckillId, String md5, boolean exposed) {
		super();
		this.seckillId = seckillId;
		this.md5 = md5;
		this.exposed = exposed;
	}

	public Exposer(long seckillId, boolean exposed, long now, long start, long end) {
		super();
		this.seckillId = seckillId;
		this.exposed = exposed;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return exposed==true? "�Ѿ�����" : "δ����";
	}
	
}
