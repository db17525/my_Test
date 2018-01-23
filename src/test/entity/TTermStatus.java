package test.entity;

import org.apache.log4j.Logger;

public class TTermStatus {
	Logger logger = Logger.getLogger(getClass());
	/// <summary>
    /// 目标号
    /// </summary>
    public String targetId;
    /// <summary>
    /// 报警时间
    /// </summary>
    public String StatusTime;
    /// <summary>
    /// 1 总线故障 0 总线无故障
    /// </summary>
    public String BusErr;
    /// <summary>
    /// 1 锁车状态 0 非锁车状态
    /// </summary>
    public String CarLockStatus;
    /// <summary>
    /// 1 GPS模块异常 0 GPS模块正常
    /// </summary>
    //public String GPSErr;
    /// <summary>
    /// 1 锁车电路故障 0 锁车电路正常
    /// </summary>
    public String CarLockCircuitErr;
    /// <summary>
    /// 1 ACC开 0 ACC关
    /// </summary>
    public String ACCOpen;
    /// <summary>
    /// 1 是重车  0 非重车
    /// </summary>
    public String isFullCar;
    /// <summary>
    /// 1 车门开 0 车门关
    /// </summary>
    public String CarDoorOpen;
    /// <summary>
    /// 1 备用输出开 0 备用输出关
    /// </summary>
    public String SpareOutputOpen;
    /// <summary>
    /// 1 处于秘密状态 0 处于非秘密状态
    /// </summary>
    public String SecretStatus;
    /// <summary>
    /// GPS天线状态 1 正常 2  天线短路 3 天线断路 4 未知错误
    /// </summary>
    public String GPSStatus;
    /// <summary>
    /// 1 远光灯开 0 远光灯关
    /// </summary>
    public String FarLightOpen;
    /// <summary>
    /// 1 右转向灯开 0 右转向灯关
    /// </summary>
    public String RightLightOpen;
    /// <summary>
    /// 1 左转向灯开 0 左转向灯关
    /// </summary>
    public String LeftLightOpen;
    /// <summary>
    /// 1 刹车灯开 0 刹车灯关
    /// </summary>
    public String StopLightOpen;
    /// <summary>
    /// 1 倒车灯或者后雾灯开 0 倒车灯或者后雾灯关
    /// </summary>
    public String AfterLightOpen;
    /// <summary>
    /// 1 前雾灯开 0 前雾灯关
    /// </summary>
    public String FogLightOpen;
    /// <summary>
    /// 1 车门关 0 车门开  （note:V81、X80终端的车门状态以此为准）
    /// </summary>
    public String CarDoorClose;
    /// <summary>
    /// 1 近光灯开 0 近光灯关
    /// </summary>
    public String NearLightOpen;
    /// <summary>
    /// 1 GNSS模块异常  0：GNSS模块正常
    /// </summary>
    public String GNSSErr;
    /// <summary>
    /// 北斗天线状态 1 正常 2  天线短路 3 天线断路 4 未知错误
    /// </summary>
    public String BDStatus;

    public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getStatusTime() {
		return StatusTime;
	}

	public void setStatusTime(String statusTime) {
		StatusTime = statusTime;
	}

	public String getBusErr() {
		return BusErr;
	}

	public void setBusErr(String busErr) {
		BusErr = busErr;
	}

	public String getCarLockStatus() {
		return CarLockStatus;
	}

	public void setCarLockStatus(String carLockStatus) {
		CarLockStatus = carLockStatus;
	}

	public String getCarLockCircuitErr() {
		return CarLockCircuitErr;
	}

	public void setCarLockCircuitErr(String carLockCircuitErr) {
		CarLockCircuitErr = carLockCircuitErr;
	}

	public String getACCOpen() {
		return ACCOpen;
	}

	public void setACCOpen(String aCCOpen) {
		ACCOpen = aCCOpen;
	}

	public String getIsFullCar() {
		return isFullCar;
	}

	public void setIsFullCar(String isFullCar) {
		this.isFullCar = isFullCar;
	}

	public String getCarDoorOpen() {
		return CarDoorOpen;
	}

	public void setCarDoorOpen(String carDoorOpen) {
		CarDoorOpen = carDoorOpen;
	}

	public String getSpareOutputOpen() {
		return SpareOutputOpen;
	}

	public void setSpareOutputOpen(String spareOutputOpen) {
		SpareOutputOpen = spareOutputOpen;
	}

	public String getSecretStatus() {
		return SecretStatus;
	}

	public void setSecretStatus(String secretStatus) {
		SecretStatus = secretStatus;
	}

	public String getGPSStatus() {
		return GPSStatus;
	}

	public void setGPSStatus(String gPSStatus) {
		GPSStatus = gPSStatus;
	}

	public String getFarLightOpen() {
		return FarLightOpen;
	}

	public void setFarLightOpen(String farLightOpen) {
		FarLightOpen = farLightOpen;
	}

	public String getRightLightOpen() {
		return RightLightOpen;
	}

	public void setRightLightOpen(String rightLightOpen) {
		RightLightOpen = rightLightOpen;
	}

	public String getLeftLightOpen() {
		return LeftLightOpen;
	}

	public void setLeftLightOpen(String leftLightOpen) {
		LeftLightOpen = leftLightOpen;
	}

	public String getStopLightOpen() {
		return StopLightOpen;
	}

	public void setStopLightOpen(String stopLightOpen) {
		StopLightOpen = stopLightOpen;
	}

	public String getAfterLightOpen() {
		return AfterLightOpen;
	}

	public void setAfterLightOpen(String afterLightOpen) {
		AfterLightOpen = afterLightOpen;
	}

	public String getFogLightOpen() {
		return FogLightOpen;
	}

	public void setFogLightOpen(String fogLightOpen) {
		FogLightOpen = fogLightOpen;
	}

	public String getCarDoorClose() {
		return CarDoorClose;
	}

	public void setCarDoorClose(String carDoorClose) {
		CarDoorClose = carDoorClose;
	}

	public String getNearLightOpen() {
		return NearLightOpen;
	}

	public void setNearLightOpen(String nearLightOpen) {
		NearLightOpen = nearLightOpen;
	}

	public String getGNSSErr() {
		return GNSSErr;
	}

	public void setGNSSErr(String gNSSErr) {
		GNSSErr = gNSSErr;
	}

	public String getBDStatus() {
		return BDStatus;
	}

	public void setBDStatus(String bDStatus) {
		BDStatus = bDStatus;
	}

	public TTermStatus()
    {
        BusErr = "0";
        GPSStatus = "0";
        ACCOpen = "0";
        GNSSErr = "0";
        BDStatus = "0";
    }

    /// <summary>
    /// 将得到的信息通过分解填充到TTargetStatus中，如果类型不对应，返回false，如果类型对应，返回true
    /// </summary>
    /// <param name="msg"></param>
    /// <returns></returns>
    public boolean FromMsgStr(String msg)
    {
        try{
            String[] msgSplit = msg.split(",");
            targetId = msgSplit[3];
            StatusTime = msgSplit[4];
            BusErr = msgSplit[12];
            GNSSErr = msgSplit[13];
            ACCOpen = msgSplit[14];
            GPSStatus = msgSplit[15];
            BDStatus = msgSplit[15];
            return true;

        }catch (Exception exp){
            logger.info("TCarPostion FromMsgStr(String msg)" + exp.getMessage());
            return false;
        }
    }
}
