package test.entity;

import java.util.HashMap;

public class TTermPosition {
	/// <summary>
    /// 手机号
    /// </summary>
    private String phoneNumber;
    /// <summary>
    /// 目标号
    /// </summary>
    public String targetNumber;
    /// <summary>
    /// 位置时间
    /// </summary>
    public String PosTime;
    /// <summary>
    /// 纬度
    /// </summary>
    public int Latitude;
    /// <summary>
    /// 经度
    /// </summary>
    public int Longitude;
    /// <summary>
    /// 星期
    /// </summary>
    public String week;
    /// <summary>
    /// 定位描述 定位或者非定位
    /// </summary>
    public int LocationDescription;
    /// <summary>
    /// 速度 单位0.001KM/H
    /// </summary>
    public int Speed;
    /// <summary>
    /// 角度  单位度，正北为0度，顺时针旋转
    /// </summary>
    public int Angle;
    /// <summary>
    /// 纬度的描述 南纬或者北纬
    /// </summary>
    public String LatDescription;
    /// <summary>
    /// 经度的描述 东经或者西经
    /// </summary>
    public String LongDescription;
    /// <summary>
    /// 创建星期的字典，方便解析星期的数据
    /// </summary>
    public HashMap<String, String> Weeks = new HashMap<String, String>();
    /// <summary>
    /// 创建经纬度描述的字典，方便解析经纬度描述
    /// </summary>
    public HashMap<String, String> Lat_Lons = new HashMap<String, String>();
    /// <summary>
    /// 当前位置下的报警状态
    /// </summary>
    public TTermAlarm tTermAlarm;
    /// 当前位置下的目标状态
    /// </summary>
    public TTermStatus termStatus;
    /// <summary>
    /// 精确的速度  0.001KM/H
    /// </summary>
    public int ISpeed = 0;
    /// <summary>
    /// 用于按月分表
    /// </summary>
    public String YearAndMonth;
    /// <summary>
    /// 用于按天分区
    /// </summary>
    public String day;
    /// <summary>
    /// 低电压报警
    /// </summary>
    private int lowPower = 0;
    /// <summary>
    /// 剪线报警
    /// </summary>
    private int trimWrie = 0;
    
    public TTermAlarm gettTermAlarm() {
		return tTermAlarm;
	}

	public void settTermAlarm(TTermAlarm tTermAlarm) {
		this.tTermAlarm = tTermAlarm;
	}

	public TTermStatus getTermStatus() {
		return termStatus;
	}

	public void setTermStatus(TTermStatus termStatus) {
		this.termStatus = termStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}

	public String getPosTime() {
		return PosTime;
	}

	public void setPosTime(String posTime) {
		PosTime = posTime;
	}

	public int getLatitude() {
		return Latitude;
	}

	public void setLatitude(int latitude) {
		Latitude = latitude;
	}

	public int getLongitude() {
		return Longitude;
	}

	public void setLongitude(int longitude) {
		Longitude = longitude;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public int getLocationDescription() {
		return LocationDescription;
	}

	public void setLocationDescription(int locationDescription) {
		LocationDescription = locationDescription;
	}

	public String getLatDescription() {
		return LatDescription;
	}

	public void setLatDescription(String latDescription) {
		LatDescription = latDescription;
	}

	public String getLongDescription() {
		return LongDescription;
	}

	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}

	public HashMap<String, String> getWeeks() {
		return Weeks;
	}

	public void setWeeks(HashMap<String, String> weeks) {
		Weeks = weeks;
	}

	public HashMap<String, String> getLat_Lons() {
		return Lat_Lons;
	}

	public void setLat_Lons(HashMap<String, String> lat_Lons) {
		Lat_Lons = lat_Lons;
	}

	public String getYearAndMonth() {
		return YearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		YearAndMonth = yearAndMonth;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getLowPower() {
		return lowPower;
	}

	public void setLowPower(int lowPower) {
		this.lowPower = lowPower;
	}

	public int getTrimWrie() {
		return trimWrie;
	}

	public void setTrimWrie(int trimWrie) {
		this.trimWrie = trimWrie;
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}

	public int getISpeed() {
		return ISpeed;
	}

	public void setISpeed(int iSpeed) {
		ISpeed = iSpeed;
	}

	public int getAngle() {
		return Angle;
	}

	public void setAngle(int angle) {
		Angle = angle;
	}

	public TTermPosition()
    {
        Weeks.put("", "星期日");
        Weeks.put("1", "星期一");
        Weeks.put("10", "星期二");
        Weeks.put("11", "星期三");
        Weeks.put("100", "星期四");
        Weeks.put("101", "星期五");
        Weeks.put("110", "星期六");

        Lat_Lons.put("0000", "西经、南纬、定位");
        Lat_Lons.put("0001", "西经、南纬、非定位");
        Lat_Lons.put("0010", "西经、北纬、定位");
        Lat_Lons.put("0011", "西经、北纬、非定位");
        Lat_Lons.put("0100", "东经、南纬、定位");
        Lat_Lons.put("0101", "东经、南纬、非定位");
        Lat_Lons.put("0110", "东经、北纬、定位");
        Lat_Lons.put("0111", "东经、北纬、非定位");
        /*target = new Target();*/
        tTermAlarm = new TTermAlarm();
        termStatus = new TTermStatus();
    }

    public String toString()
    {
        return week + "，定位状态：" + LocationDescription + "纬度" + Latitude + ",经度" + Longitude + "，位置时间" + PosTime + ",速度" + Speed + ",角度" + Angle + ",状态" + termStatus.toString() + ",报警" + termStatus.toString();
    }
}
