package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class Utils {
	
	
	/**
	 * 将度分秒转换为度
	 * @param DMS
	 * @return
	 */
    public static int DMS2D(String DMS)
    {
        int degree = DMS.indexOf('°');
        String strDegree = DMS.substring(0, degree);//度
        int fen = DMS.indexOf("′");
        String strfen = DMS.substring(degree+1, fen);//分
        int length = DMS.length();
        String strSecond = DMS.substring(fen + 1, length-1);//秒

        double Degree = Double.parseDouble(strDegree);
        double D = 0.0;//度
        int uD = 0;
        double M = Double.parseDouble(strfen);//分
        double S = Double.parseDouble(strSecond);//秒
        //S/60.0 秒转换为分 
        //(M+S/60.0)/60.0分转换为度 
        D = (Degree + (M + S / 60.0) / 60.0) * 1000000;//*100/100.0;
        uD = Integer.parseInt(new java.text.DecimalFormat("0").format(D));
        return uD; 
    }
    
    /**
     * 将经纬度转换为度分秒形式
     * @param d
     * @return
     */
    public static String D2DFM(int d)
    {
    	double fD = d / 1000000.0;
        String rst = "";
        boolean isNeg;
        int temp;
        if (fD < 0)
        {
            isNeg = true;
            fD = -fD;
        }
        else
        {
            isNeg = false;
        }
        temp = (int)fD;
        rst = rst + temp + "°";
        fD = (fD - temp) * 60.0;
        temp = (int)fD;
        rst = rst + temp + "'";
        fD = (fD - temp) * 60.0;
        temp = (int)fD;
        rst = rst + temp + ".";
        fD = (fD - temp) * 100.0;
        temp = (int)fD;
        rst = rst + temp + "″";

        if (isNeg)
        {
            rst = "-" + rst;
        }

        return rst;
    }
    
    /**
     * 度分秒(xx.xx,包含小数)转换成度
     * @param DMS
     * @return
     */
    public static int DMSS2D(String DMS)
    {
        int degree = DMS.indexOf('°');
        String strDegree = DMS.substring(0, degree);
        int fen = DMS.indexOf("'");
        String strfen = DMS.substring(degree + 1, fen);
        int second = DMS.indexOf(".");
        String strSecond = DMS.substring(fen + 1, second);
        int length = DMS.length();
        String strSecondS = DMS.substring(second + 1, length - 1);

        double Degree = Double.parseDouble(strDegree);
        double D = 0.0;//度
        int uD = 0;
        double M = Double.parseDouble(strfen); //分
        double S = Double.parseDouble(strSecond) + Double.parseDouble(strSecondS)/100.0;//秒
        //S/60.0 秒转换为分 
        //(M+S/60.0)/60.0分转换为度 
        D = (Degree + (M + S / 60.0) / 60.0 + 0.0000001) * 10000000;//*100/100.0;
        uD = Integer.parseInt(new java.text.DecimalFormat("0").format(D));
        return uD;
    }
    /**
	 * (1) Calendar转化为Date()
			Calendar cal=Calendar.getInstance();
			Date() date=cal.getTime();
			
			(2) Date转化为Calendar
			Date date=new Date(); ...
	 */

	public static int GetSecFrom1970(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
        	int result = (int) (sdf.parse(sdf.format(date)).getTime()/1000);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
	
	public static int GetNowSecFrom1970(){
		return (int)(Calendar.getInstance().getTimeInMillis()/1000);
    }
	
	public static int GetSecFrom1970(String time){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	int result = (int) (sdf.parse(time).getTime()/1000);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
	
	public static String GetStringFromSec(int second){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date((long)(second*1000.0));
        return sdf.format(date);
	}
	//单独获取年、月、日
	public static String GetStringYmdFromSec(int second, String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date date = new Date((long)(second*1000.0));
		return sdf.format(date);
	}
	/**
	 * 单独获取年、月、日
	 * @param second  秒数
	 * @param pattern 格式
	 * @return
	 */
	public static String GetStringYmdFromSec(long second, String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date date = new Date((long)(second*1000.0));
		return sdf.format(date);
	}
	
	/**
	 * 读取配置文件中参数
	 * @param param
	 * @return
	 */
	public static String getConf(String param){
		Properties prop = null;
		InputStream is = null;
		prop = new Properties();
		try {
			is = SqlHelper.class.getResourceAsStream("/config.properties");
			prop.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop.getProperty(param);
	}
	/**
	 * 判断某一个表是否存在
	 * @param name 表名
	 * @return
	 */
	 public static boolean hasTable(String name) {
        boolean result = false;
        Connection con = SqlHelper.getConn();
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet set = meta.getTables (null, null, name.toUpperCase(), null);
            while (set.next()) {
                result = true;
            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace ();
        } finally {
			SqlHelper.close(con);
		}
        return result;
    }
	 
	 /**
	     * 获取某年某周的起始时间和结束时间
	     * 
	     * @param year
	     * @param weekindex
	     * @return
     */
    public static String[] getDayOfWeek(int year, int weekindex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setWeekDate(year, weekindex, 1);
 
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 2;
        c.add(Calendar.DATE, -dayOfWeek); // 得到本周的第一天
        String begin = sdf.format(c.getTime());
        c.add(Calendar.DATE, 6); // 得到本周的最后一天
        String end = sdf.format(c.getTime());
        String[] range = new String[2];
        range[0] = begin;
        range[1] = end;
        return range;
    }
    
	/**
	 * blob转string
	 * @param blob
	 * @return
	 * @throws IOException
	 */
	public static String getBlob(Blob blob) throws IOException {
		InputStream in = null;
		String result = "";
		if(blob!=null){
			try {
				in = blob.getBinaryStream();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				int len = (int) blob.length();
				byte[] data = new byte[len];
				int count = -1;
				while ((count = in.read(data, 0, len)) != -1)
					outStream.write(data, 0, count);
				
				data = null;
				result = new String(outStream.toByteArray(), "utf-8");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * clob转string
	 * @param clob
	 * @return
	 * @throws IOException
	 */
	public static String getClob(Clob clob) throws IOException {
		String result = "";
		int i = 0;
		if(clob!=null){
			try {
				InputStream input = clob.getAsciiStream();
			     int len = (int)clob.length();
			     byte data[] = new byte[len];
			     while(-1 != (i = input.read(data, 0, data.length))){
			      input.read(data, 0, i);
			     }
			     result = new String(data, "utf-8");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 距离计算
	 * @param BStart
	 * @param LStart
	 * @param BEnd
	 * @param LEnd
	 * @return
	 */
	public static double CaluDistance(double BStart,double LStart,double BEnd,double LEnd){
		double EARTH_RADIUS = 6378.137;
		double PI = 3.141592653589793;
		double x1,x2,y1,y2,z1,z2,d1,b1,b2,l1,l2; 
		b1 = (0.5-BStart/180)*PI; 
		l1 = (LStart/180)*PI; 
		b2 = (0.5-BEnd/180)*PI; 
		l2 = (LEnd/180)*PI; 
		x1 = EARTH_RADIUS*Math.cos(l1)*Math.sin(b1); 
		y1 = EARTH_RADIUS*Math.sin(l1)*Math.sin(b1); 
		z1 = EARTH_RADIUS*Math.cos(b1); 
		x2 = EARTH_RADIUS*Math.cos(l2)*Math.sin(b2); 
		y2 = EARTH_RADIUS*Math.sin(l2)*Math.sin(b2); 
		z2 = EARTH_RADIUS*Math.cos(b2); 
		d1 = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)+(z1-z2)*(z1-z2)); 
		double theta = Math.acos((EARTH_RADIUS*EARTH_RADIUS+EARTH_RADIUS*EARTH_RADIUS-d1*d1)/(2*EARTH_RADIUS*EARTH_RADIUS)); 
		double distance = theta*EARTH_RADIUS; 
		return distance; //千米  *0.5399568
	}
	/**
	 * 获取项目路径
	 * @return
	 */
	public static String getWebrootPath() {
        String root = Utils.class.getResource("/").getFile();
        try {
            root = new File(root).getParentFile().getParentFile()
                    .getCanonicalPath();
            root += File.separator;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
		
	/**
	 * 从指定位置开始截取指定长度的字符串
	 * @param input 输入字符串
	 * @param index 截取位置，左侧第一个字符索引值是1
	 * @param count 截取长度
	 * @return  截取字符串
	 */
	public static String substr(String input, int index, int count) {
		if (input == null || input.equals("")) {
			return "";
		}
		count = (count > input.length() - index + 2) ? input.length() - index+1
				+ 1 : count;
		return input.substring(index , index + count);
	}
	
	//16进制转10进制
	public static int HexToInt(String strHex){
		int nResult = 0;
		if ( !IsHex(strHex) )
			return nResult;
		String str = strHex.toUpperCase();
		if ( str.length() > 2 ){
			if ( str.charAt(0) == '0' && str.charAt(1) == 'X' ){
				str = str.substring(2);
			}
		}
		int nLen = str.length();
		for ( int i=0; i<nLen; ++i ){
			char ch = str.charAt(nLen-i-1);
			try {
				nResult += (GetHex(ch)*GetPower(16, i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nResult;
	}
	
	//计算16进制对应的数值
	public static int GetHex(char ch) throws Exception{
		if ( ch>='0' && ch<='9' )
			return (int)(ch-'0');
		if ( ch>='a' && ch<='f' )
			return (int)(ch-'a'+10);
		if ( ch>='A' && ch<='F' )
			return (int)(ch-'A'+10);
		throw new Exception("error param");
	}
	
	//计算幂
	public static int GetPower(int nValue, int nCount) throws Exception{
		if ( nCount <0 )
			throw new Exception("nCount can't small than 1!");
		if ( nCount == 0 )
			return 1;
		int nSum = 1;
		for ( int i=0; i<nCount; ++i ){
			nSum = nSum*nValue;
		}
		return nSum;
	}
	
	//判断是否是16进制数
	public static boolean IsHex(String strHex){
		int i = 0;
		if ( strHex.length() > 2 ){
			if ( strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x') ){
			i = 2;
		}
		}
		for ( ; i<strHex.length(); ++i ){
			char ch = strHex.charAt(i);
			if ( (ch>='0' && ch<='9') || (ch>='A' && ch<='F') || (ch>='a' && ch<='f') )
				continue;
				return false;
		}
		return true;
	}
	
    //十进制转换成二进制
    public static String decimalToBinary(int decimalSource) {
    	BigInteger bi = new BigInteger(String.valueOf(decimalSource));	//转换成BigInteger类型
    	return bi.toString(2);	//参数2指定的是转化成X进制，默认10进制
    }
    
    //二进制转换成十进制 
    public static int binaryToDecimal(String binarySource) {
    	BigInteger bi = new BigInteger(binarySource, 2);	//转换为BigInteger类型
    	return Integer.parseInt(bi.toString());		//转换成十进制
    }
    
    //字符串前补0
    public static String binar(String str_m) {
    	String str ="0000000000";
    	str_m=str.substring(0, 8-str_m.length())+str_m;
    	return str_m;
    }
    
    //获取所属月的总天数
    public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
    
    //获取上一个月的总天数
    public static Date getLastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
    
    public static char Crc16Calc(byte[] data_arr, int data_len){
	    char crc16 = 0;
	    int i;
	    for(i =0; i < (data_len); i++){
		    crc16 = (char)(( crc16 >> 8) | (crc16 << 8));
		    crc16 ^= data_arr[i]& 0xFF;
		    crc16 ^= (char)(( crc16 & 0xFF) >> 4);
		    crc16 ^= (char)(( crc16 << 8) << 4);
		    crc16 ^= (char)((( crc16 & 0xFF) << 4) << 1);
	    }
	    return crc16;
    }
    
    public static String crcTable(byte[] bytes, int length) { 
        int[] table = { 
        		0X0000, 0X1189, 0X2312, 0X329B, 0X4624, 0X57AD, 0X6536, 0X74BF, 
        		0X8C48, 0X9DC1, 0XAF5A, 0XBED3, 0XCA6C, 0XDBE5, 0XE97E, 0XF8F7, 
        		0X1081, 0X0108, 0X3393, 0X221A, 0X56A5, 0X472C, 0X75B7, 0X643E, 
        		0X9CC9, 0X8D40, 0XBFDB, 0XAE52, 0XDAED, 0XCB64, 0XF9FF, 0XE876, 
        		0X2102, 0X308B, 0X0210, 0X1399, 0X6726, 0X76AF, 0X4434, 0X55BD, 
        		0XAD4A, 0XBCC3, 0X8E58, 0X9FD1, 0XEB6E, 0XFAE7, 0XC87C, 0XD9F5, 
        		0X3183, 0X200A, 0X1291, 0X0318, 0X77A7, 0X662E, 0X54B5, 0X453C, 
        		0XBDCB, 0XAC42, 0X9ED9, 0X8F50, 0XFBEF, 0XEA66, 0XD8FD, 0XC974, 
        		0X4204, 0X538D, 0X6116, 0X709F, 0X0420, 0X15A9, 0X2732, 0X36BB, 
        		0XCE4C, 0XDFC5, 0XED5E, 0XFCD7, 0X8868, 0X99E1, 0XAB7A, 0XBAF3, 
        		0X5285, 0X430C, 0X7197, 0X601E, 0X14A1, 0X0528, 0X37B3, 0X263A, 
        		0XDECD, 0XCF44, 0XFDDF, 0XEC56, 0X98E9, 0X8960, 0XBBFB, 0XAA72, 
        		0X6306, 0X728F, 0X4014, 0X519D, 0X2522, 0X34AB, 0X0630, 0X17B9, 
        		0XEF4E, 0XFEC7, 0XCC5C, 0XDDD5, 0XA96A, 0XB8E3, 0X8A78, 0X9BF1, 
        		0X7387, 0X620E, 0X5095, 0X411C, 0X35A3, 0X242A, 0X16B1, 0X0738, 
        		0XFFCF, 0XEE46, 0XDCDD, 0XCD54, 0XB9EB, 0XA862, 0X9AF9, 0X8B70, 
        		0X8408, 0X9581, 0XA71A, 0XB693, 0XC22C, 0XD3A5, 0XE13E, 0XF0B7, 
        		0X0840, 0X19C9, 0X2B52, 0X3ADB, 0X4E64, 0X5FED, 0X6D76, 0X7CFF, 
        		0X9489, 0X8500, 0XB79B, 0XA612, 0XD2AD, 0XC324, 0XF1BF, 0XE036, 
        		0X18C1, 0X0948, 0X3BD3, 0X2A5A, 0X5EE5, 0X4F6C, 0X7DF7, 0X6C7E, 
        		0XA50A, 0XB483, 0X8618, 0X9791, 0XE32E, 0XF2A7, 0XC03C, 0XD1B5, 
        		0X2942, 0X38CB, 0X0A50, 0X1BD9, 0X6F66, 0X7EEF, 0X4C74, 0X5DFD, 
        		0XB58B, 0XA402, 0X9699, 0X8710, 0XF3AF, 0XE226, 0XD0BD, 0XC134, 
        		0X39C3, 0X284A, 0X1AD1, 0X0B58, 0X7FE7, 0X6E6E, 0X5CF5, 0X4D7C, 
        		0XC60C, 0XD785, 0XE51E, 0XF497, 0X8028, 0X91A1, 0XA33A, 0XB2B3, 
        		0X4A44, 0X5BCD, 0X6956, 0X78DF, 0X0C60, 0X1DE9, 0X2F72, 0X3EFB, 
        		0XD68D, 0XC704, 0XF59F, 0XE416, 0X90A9, 0X8120, 0XB3BB, 0XA232, 
        		0X5AC5, 0X4B4C, 0X79D7, 0X685E, 0X1CE1, 0X0D68, 0X3FF3, 0X2E7A, 
        		0XE70E, 0XF687, 0XC41C, 0XD595, 0XA12A, 0XB0A3, 0X8238, 0X93B1, 
        		0X6B46, 0X7ACF, 0X4854, 0X59DD, 0X2D62, 0X3CEB, 0X0E70, 0X1FF9, 
        		0XF78F, 0XE606, 0XD49D, 0XC514, 0XB1AB, 0XA022, 0X92B9, 0X8330, 
        		0X7BC7, 0X6A4E, 0X58D5, 0X495C, 0X3DE3, 0X2C6A, 0X1EF1, 0X0F78,
        }; 

        int crc = 0xffff; 
        
        for (byte b : bytes) {
        		crc = (crc >>> 8) ^ table[(crc ^ b) & 0xff]; 
        } 
        System.out.println(~crc);
        return Integer.toHexString(~crc); 
    }
    
    public static int CRC16(byte[] Buf, int Len) {
    	int CRC;
    	int i, Temp;
    	CRC = 0xffff;
    	for (i = 0; i < Len; i++) {
	    	CRC = CRC ^ byteToInteger(Buf[i]);
	    	// System.out.println(byteToInteger(Buf[i]));
		    	for (Temp = 0; Temp < 8; Temp++) {
		    	if ((CRC & 0x01) == 1)
		    	CRC = (CRC >> 1) ^ 0xA001;
		    	else
		    	CRC = CRC >> 1;
	    	}
    	}
    	return CRC;
    }
    
    public static int byteToInteger(byte b) {
    	int value = b & 0xff;
    	return value;
    }
    
    public static int get_crc16 (byte[] bufData, int buflen, byte[] pcrc)
	{
		int ret = 0;
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;
		int i, j;


		if (buflen == 0)
		{
			return ret;
		}
		for (i = 0; i < buflen; i++)
		{
			CRC ^= ((int)bufData[i] & 0x000000ff);
			for (j = 0; j < 8; j++)
			{
				if ((CRC & 0x00000001) != 0)
				{
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				}
				else
				{
					CRC >>= 1;
				}
			}
			//System.out.println(Integer.toHexString(CRC));
		}
		
		System.out.println(Integer.toHexString(CRC));
		pcrc[0] = (byte)(CRC & 0x00ff);
		pcrc[1] = (byte)(CRC >> 8);

		return ret;
	}
    
    public static void getYears(int yearStart, int monthStart, int yearEnd, int monthEnd){
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfm = new SimpleDateFormat("MM");
        Date date = new Date();
        int year=yearStart;
        int nowYear = Integer.parseInt(sdfy.format(date));//当前年份
        int nowMonth =  Integer.parseInt(sdfm.format(date));//当前月份
        yearEnd = yearEnd < nowYear ? yearEnd:nowYear;
        int mons = monthStart;
        int mone = monthEnd;
        while(year<=yearEnd){
        	if(year>yearStart){
        		mons = 1;
        	}/*else{
        		mons = monthStart;
        	}*/
        	if(year!=yearEnd){
        		mone = 12;
        	}else{
        		mone = monthEnd;
        	}
        	if (year == nowYear && mone > nowMonth) {
				mone = nowMonth;
			}
        	for (int i = mons; i <= mone; i++) {
        		System.out.println(year+"---"+i);
			}
        	year++;
        }
	}
    
    private static double EARTH_RADIUS = 6378.137;  
    
    private static double rad(double d) {  
        return d * Math.PI / 180.0;  
    }  
  
    /** 
     * 通过经纬度获取距离(单位：米) 
     * @param lat1 纬度
     * @param lng1 经度
     * @param lat2 
     * @param lng2 
     * @return 
     */  
    public static double getDistance(double lat1, double lng1, double lat2,  
                                     double lng2) {  
        double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;  
        double b = rad(lng1) - rad(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2)  
                * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        //s = Math.round(s * 10000d) / 10000d;  
        s = s*1000;  
        return s;  
    }
    
    public static double[] getAround(double lat,double lon,int raidus){
    	double PI = 3.14159265;
        double EARTH_RADIUS = 6378.137;
        double RAD = Math.PI / 180.0;
        
        Double latitude = lat;  
        Double longitude = lon;  
          
        Double degree = (24901*1609)/360.0;  
        double raidusMile = raidus;  
          
        Double dpmLat = 1/degree;  
        Double radiusLat = dpmLat*raidusMile;  
        Double minLat = latitude - radiusLat;  
        Double maxLat = latitude + radiusLat;  
          
        Double mpdLng = degree*Math.cos(latitude * (PI/180));  
        Double dpmLng = 1 / mpdLng;  
        Double radiusLng = dpmLng*raidusMile;  
        Double minLng = longitude - radiusLng;  
        Double maxLng = longitude + radiusLng;  
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");  
        return new double[]{minLat,minLng,maxLat,maxLng};  
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		
		int b = Integer.parseInt(GetStringYmdFromSec(1472625987, "yyyy"));
		int c = Integer.parseInt(GetStringYmdFromSec(1472625987, "MM"));
		int d = Integer.parseInt(GetStringYmdFromSec(1472625987, "dd"));
		String e = GetStringFromSec(1454558552);
		//System.out.println(e);
		//System.out.println(b+"-"+c+"-"+d);
		Map<Integer,String> maps=new HashMap<Integer,String>();
		maps.put(1, "a");
		maps.put(2, "b");
		//System.out.println(maps);
		
		//System.out.println(GetSecFrom1970("2016-11-30 10:48:15"));
		/*int b = GetSecYdhFrom1970(a, "yyyy-MM-dd HH:mm:ss");
		
		int c = GetSecYdhFrom1970(a, "yyyy");
		int d = GetSecYdhFrom1970(a, "MM");
		int e = GetSecYdhFrom1970(a, "dd");
		
		int s = GetSecFrom1970("2016-08-31 14:46:27");
		System.out.println(a+":"+b+":"+c+":"+d+":"+e+"--"+s);*/
        //System.out.println(URLDecoder.decode("æç½","utf-8"));
       //System.out.println(new String("æç½".getBytes("ISO-8859-1"),"utf-8"));
       String msg = "(018012345678BP05865879027497958170326A4004.7204N11614.0835E000.2162713233.65FF01003497PFF00FF00)";
       //System.out.println(msg.substring(0, 32)+"ACK)");
       //System.out.println(msg);
       /*byte[] aa={0x22,0x31,0x01,0x00,0x1A,(byte)0xC3,0x11,0x11,
    		   0x00,0x00,0x00,0x00,0x08,0x1A,0x22,0x31,0x01,0x00,
    		   0x1A,(byte)0xC3,0x11,0x11,0x00,0x00,0x00,0x00,0x08,0x1A};
       byte[] bb={ 0x05, 0x01, 0x00, 0x02};
       System.out.println(Crc16Calc(bb,bb.length));
       System.out.println(crcTable(bb));
       System.out.println(CRC16(bb, bb.length));*/
       
       /*byte[] aa = {0x30,0x30,0x34,0x36,0x46,0x44,0x36,0x30,0x30,0x30,0x01,0x72,0x65,
				0x66,0x65,0x72,0x69,0x6E,0x66,0x6F,0x2E,0x63,0x73,0x76,0x00,0x00
				,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
				,0x00,0x00,0x00,0x01,(byte)0xf4,0x01};*/
       byte[] aa={0x05, 0x01, 0x00, 0x02};
       //byte[] bb={ 0x05, 0x01, 0x00, 0x02};
       byte[] bb = new byte[3];
       get_crc16(aa, aa.length, bb);
	
       //System.out.println(Integer.toHexString((int)bb[0] & 0x000000ff));
       //System.out.println(Integer.toHexString((int)bb[1] & 0x000000ff));
       
       //System.out.println(crcTable(aa, aa.length));
       String[] a = "78781234560d0a7878abcdefgh0d0a7878%&&&$$0d0a".replace("0d0a", "").split("7878");
       for (int i = 0; i < a.length; i++) {
		//System.out.println(a[i]);
	}
       int x = 0x1 & (Integer.parseInt("12AB", 16) >> 11);//将16进制字符串转换成2进制数，然后读取2进制数上的某一位值
       //System.out.println(x);
       String bin = Integer.toBinaryString(Integer.parseInt("12AB", 16));
       //System.out.println(bin);
       String stateStr = "1402";
       //System.out.println(Integer.parseInt(stateStr, 16));
       
       String intString = Integer.toBinaryString(Integer.parseInt(stateStr, 16));
       String str ="00000000000000000000000000";
       intString = str.substring(0, 16-intString.length())+intString;
       //System.out.println(intString);
       
       /*System.out.println(intString.substring(0));
       System.out.println(intString.substring(6));
       System.out.println(Integer.valueOf(intString.substring(6), 2));*/
       //getYears(2015, 1, 2017, 12);
       
       //getYears(2017, 1, 2017, 1);
       
	    //System.out.println(GetStringYmdFromSec(1481751341, "yyyy-MM-dd HH:mm:ss"));
		//getFileName();
		//System.out.println(System.currentTimeMillis());
		//System.out.println(GetSecFrom1970("2017-04-07 14:41:00"));  
	     //System.out.println(GetStringYmdFromSec(1481751341, "yyyy-MM-dd HH:mm:ss"));
		SimpleDateFormat sdfm = new SimpleDateFormat("MM");
	     Date date = new Date();
	     String endYear = sdfm.format(date);
	     //System.out.println(Integer.parseInt(endYear));
	     //System.out.println(String.format("%0" + 2 + "d", 11));
	     String a2 = String.format("%0" + 2 + "d", 1);
	     //System.out.println(a2);
	     System.out.println(MD5.MD5Encrypt("bdxt"));
	     System.out.println(GetStringYmdFromSec(1481751341, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(CaluDistance(106.724347,26.590299,106.703848,26.588635));
		System.out.println(URLDecoder.decode("%B9%F0%BA%CF%D3%E600688","gbk"));
		System.out.println(URLEncoder.encode("桂合渔00688", "gbk"));
		System.out.println(Double.parseDouble("109379719")/1000000);
		System.out.println(GetStringFromSec(1486170170));
		DecimalFormat df = new DecimalFormat("#.0000000");
		//System.out.println(df.format((Double.parseDouble("2.0"))/3+20));
		//System.out.println((int)(((Double.parseDouble("2.0"))/3+20)*10000000));
		//System.out.println((float)2/10);
		System.out.println(GetStringYmdFromSec(1481751341, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(CaluDistance(106.724347,26.590299,106.703848,26.588635));
		System.out.println(URLDecoder.decode("%B9%F0%BA%CF%D3%E600688","gbk"));
		System.out.println(URLEncoder.encode("桂合渔00688", "gbk"));
		System.out.println(Double.parseDouble("109379719")/1000000);
		//System.out.println(GetStringYmdFromSec(1481751341, "yyyy-MM-dd HH:mm:ss"));
		double a3= 0.57;
        double b3= 9.3;
        BigDecimal   b1   =   new   BigDecimal(Double.toString(a3));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(b3));   
        //System.out.println(b1.add(b2).doubleValue());
        double e1 = 12345.12;
        //System.out.println(e1/1000);
       //System.out.println(String.format("%0" + 2 + "d", 11));
        //String aa4 = String.format("%0" + 2 + "d", 1);
        //System.out.println(aa4);
        
        System.out.println(getDistance(106.724347,26.590299,106.703848,26.588635));
        System.out.println(getDistance(123.094026,32.757583,123.003413,32.802886));
        
       
	}
}
