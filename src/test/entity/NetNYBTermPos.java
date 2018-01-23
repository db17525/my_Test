package test.entity;


public class NetNYBTermPos {
	/**
	 * �������кţ�������ϵͳ����Ψһ�ģ�������ͨ�ŷ����̴���(4λ10������,ϵͳ���䣩��
	 * ��ʾ�������ĵ��û�id��0000����UTC���ڴ���14λ10������,��ʽYYYYMMDDHH24MISS��
	 * ��5λΨһ��ʮ�������кŴ������ɣ��磺110100002009013011552311111
	 */
	private String msg_id;
	/**
	 * �ն˺���  
	 */
	private String terminal_code;
	/**
	 * ͨ�ŷ����̴��� 
	 * 1001 ����������Ӫ��
	 * 1101  ������ͨ��Ӫ��
	 * 1102  ���ʵ�����Ӫ��
	 * 1201 AIS
	 */
	private int comm_type;
	/**
	 * λ������  
	 * 0   ��ʱ�ش�λ��
	 * 1 ���λش�λ��
	 * 100 �����ش�λ��
	 * 200 ���ۻش�λ��
	 * 201���۱���λ��
	 */
	private int pos_type;
	/**
	 * λ��ʱ�䣬��1970��1��1�յ����ڵ�����
	 */
	private int UTC;
	/**
	 * ���ȣ������֮һ��
	 */
	private int longitude;
	/**
	 * γ�ȣ������֮һ��
	 */
	private int latitude;
	/**
	 * ���� 0-360��
	 */
	private int course;
	/**
	 * ������ 0-360��
	 */
	private int trueHeading;
	/**
	 * �ٶ�   1/10��
	 */
	private int speed;
	/**
	 * ״̬  0 ����  1  �豸����
	 */
	private int status;
	/**
	 * ��ע
	 */
	private String vDesc;
	/**
	 * ͨ�ŷ����̴���
	 */
	private int terminal_type;
	
	
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getTerminal_code() {
		return terminal_code;
	}
	public void setTerminal_code(String terminal_code) {
		this.terminal_code = terminal_code;
	}
	public int getComm_type() {
		return comm_type;
	}
	public void setComm_type(int comm_type) {
		this.comm_type = comm_type;
	}
	public int getPos_type() {
		return pos_type;
	}
	public void setPos_type(int pos_type) {
		this.pos_type = pos_type;
	}
	public int getUTC() {
		return UTC;
	}
	public void setUTC(int uTC) {
		UTC = uTC;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public int getTrueHeading() {
		return trueHeading;
	}
	public void setTrueHeading(int trueHeading) {
		this.trueHeading = trueHeading;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getvDesc() {
		return vDesc;
	}
	public void setvDesc(String vDesc) {
		this.vDesc = vDesc;
	}
	public int getTerminal_type() {
		return terminal_type;
	}
	public void setTerminal_type(int terminal_type) {
		this.terminal_type = terminal_type;
	}
	
	public static NetNYBTermPos FromStr(String posStr[]){
		NetNYBTermPos termPos = new NetNYBTermPos();
		try{
			termPos.msg_id = posStr[0];
			termPos.terminal_code = posStr[1];
			termPos.comm_type = Integer.parseInt(posStr[2]);
			termPos.pos_type = Integer.parseInt(posStr[3]);
			termPos.UTC = Integer.parseInt(posStr[4]);
			termPos.longitude = Integer.parseInt(posStr[5]);
			termPos.longitude = termPos.longitude * 10  / 6 ;
			termPos.latitude = Integer.parseInt(posStr[6]);
			termPos.latitude = termPos.latitude * 10  / 6 ;
			termPos.course = Integer.parseInt(posStr[7]);
			termPos.course = termPos.course / 20; 
			termPos.trueHeading = Integer.parseInt(posStr[8]);
			termPos.speed = Integer.parseInt(posStr[9]);
			termPos.speed = (int) (termPos.speed * 0.514);
			if(posStr.length>10){
				termPos.status = (posStr[10]== null||posStr[10].equals(""))?1:Integer.parseInt(posStr[10]);
			}
			if(posStr.length>11){
				termPos.vDesc = posStr[11]==null?"":posStr[11];
			}
			if(posStr.length>12){
				termPos.terminal_type = (posStr[12]==null||posStr[12].equals(""))?0:Integer.parseInt(posStr[12]);
			}
			return termPos;
		}catch(Exception e){
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
