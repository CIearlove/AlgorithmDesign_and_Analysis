package Chapter03_DynamicPlanning;

public class Element implements Comparable{

	private int key;//ִ��ʱ��
	private int index;//��ҵ���
	private boolean job;//1�����һ��������0����ڶ�������
	
	public Element(int kk,int ii, boolean jj){
		
		key = kk;
		index = ii;
		job = jj;
	}
	@Override
	public int compareTo(Object x) {
		
		int xkey = ((Element)x).key;
		
		if(key<xkey)
			return -1;
		
		if(key == xkey)
			return 0;
		
		return 1;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isJob() {
		return job;
	}
	public void setJob(boolean job) {
		this.job = job;
	}
}
