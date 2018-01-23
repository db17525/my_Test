package test.entity;


public class NetNYBTermPos {
	/**
	 * 数据序列号，在整个系统中是唯一的，规则：由通信服务商代码(4位10进制数,系统分配）、
	 * 表示数据中心的用户id”0000”、UTC日期戳（14位10进制数,格式YYYYMMDDHH24MISS）
	 * 和5位唯一的十进制序列号串联而成，如：110100002009013011552311111
	 */
	private String msg_id;
	/**
	 * 终端号码  
	 */
	private String terminal_code;
	/**
	 * 通信服务商代码 
	 * 1001 海事卫星运营商
	 * 1101  北斗星通运营商
	 * 1102  普适导航运营商
	 * 1201 AIS
	 */
	private int comm_type;
	/**
	 * 位置类型  
	 * 0   定时回传位置
	 * 1 单次回传位置
	 * 100 报警回传位置
	 * 200 出港回传位置
	 * 201进港报告位置
	 */
	private int pos_type;
	/**
	 * 位置时间，从1970年1月1日到现在的描述
	 */
	private int UTC;
	/**
	 * 经度，百万分之一度
	 */
	private int longitude;
	/**
	 * 纬度，百万分之一度
	 */
	private int latitude;
	/**
	 * 航向 0-360度
	 */
	private int course;
	/**
	 * 船艏向 0-360度
	 */
	private int trueHeading;
	/**
	 * 速度   1/10节
	 */
	private int speed;
	/**
	 * 状态  0 正常  1  设备故障
	 */
	private int status;
	/**
	 * 备注
	 */
	private String vDesc;
	/**
	 * 通信服务商代码
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
