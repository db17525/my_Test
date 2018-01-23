package test.entity;

import org.apache.log4j.Logger;


public class TTermAlarm {
	Logger logger = Logger.getLogger(getClass());
	 /// 目标号
    /// </summary>
      public String targetId;
      /// <summary>
      /// 报警时间
      /// </summary>
      public String AlarmTime;
      /// <summary>
      /// 1 劫警报警 0 无劫警报警
      /// </summary>
      public String RobAlarm;
      /// <summary>
      /// 1 盗警报警 0 无盗警报警
      /// </summary>
      public String StealAlarm;
      /// <summary>
      /// 1 碰撞报警 0 无碰撞报警
      /// </summary>
      public String CollisionAlarm;
      /// <summary>
      /// 1 进范围报警 0 无进范围报警
      /// </summary>
      public String IntoRangeAlarm;
      /// <summary>
      /// 1 出范围报警 0 无出范围报警
      /// </summary>
      public String OutRangeAlarm;
      /// <summary>
      /// 1 超速报警 0 无超速报警
      /// </summary>
      public String OverSpeedAlarm;
      /// <summary>
      /// 1 偏离路线报警 0 无偏离路线报警
      /// </summary>
      public String DivergeRoadAlarm;
      /// <summary>
      /// 1 非法时段行驶报警 0  无非法时段行驶报警
      /// </summary>
      public String RunOutOfRightTime;
      /// <summary>
      /// 1 停车休息时间不足报警 0 无停车休息时间报警
      /// </summary>
      public String NotFullTimeToRest;
      /// <summary>
      /// 1 疲劳驾驶报警 0 无疲劳驾驶报警
      /// </summary>
      public String FatigueDrivingAlarm;
      /// <summary>
      /// 1 非法开门报警 0 无非法开门报警
      /// </summary>
      public String OpenDoorlawlessAlarm;
      /// <summary>
      /// 1 设防 0 非设防
      /// </summary>
      public String SetDefence;
      /// <summary>
      /// 1 剪线报警 0 无剪线报警
      /// </summary>
      public String TrimThreadAlarm;
      /// <summary>
      /// 1 电瓶电压低报警 0 无电瓶电压低报警
      /// </summary>
      public String LowVoltageAlarm;
      /// <summary>
      /// 1 密码错误报警 0 无密码错误报警
      /// </summary>
      public String PasswrodErrorAlarm;
      /// <summary>
      /// 1 禁行报警 0 无禁行报警
      /// </summary>
      public String NoRunAlarm;
      /// <summary>
      /// 1 非法停车报警 0 无非法停车报警
      /// </summary>
      public String StopCarLawlessAlarm;

      public TTermAlarm()
      {
          CollisionAlarm = "0";
          IntoRangeAlarm = "0";
          OutRangeAlarm = "0";
          OverSpeedAlarm = "0";
          SetDefence = "0";
          TrimThreadAlarm = "0";
          LowVoltageAlarm = "0";
          DivergeRoadAlarm = "0";
          OpenDoorlawlessAlarm = "0";
          RunOutOfRightTime = "0";
          RobAlarm = "0";
          StealAlarm = "0";
          NotFullTimeToRest = "0";
          NoRunAlarm = "0";
          StopCarLawlessAlarm = "0";
          PasswrodErrorAlarm = "0";
      }

      /// <summary>
      /// 将得到的信息通过分解填充到TTargetAlarm中，如果类型不对应，返回false，如果类型对应，返回true
      /// </summary>
      /// <param name="msg"></param>
      /// <returns></returns>
      public boolean FromMsgStr(String msg)
      {
          try {
              String[] msgSplit = msg.split(",");
              targetId = msgSplit[3];
              AlarmTime = msgSplit[4];
              CollisionAlarm = msgSplit[17];
              IntoRangeAlarm = msgSplit[18];
              OutRangeAlarm = msgSplit[19];
              OverSpeedAlarm = msgSplit[20];
              SetDefence = msgSplit[21];
              TrimThreadAlarm = msgSplit[22];
              LowVoltageAlarm = msgSplit[23];
              StealAlarm = msgSplit[24];
              return true;
          } catch (Exception exp){
              logger.info("TCarPostion FromMsgStr(String msg)" + exp.getMessage());
              return false;
          }
      }
}
