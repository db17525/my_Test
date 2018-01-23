package test.date;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test01 {

    /** .log */
    //private static final Logger logger = Logger.getLogger(GenerateSequenceUtil.class);

    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;

    private static final int MAX = 9999;

    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized String generateSequenceNo() {

        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

        numberFormat.format(seq, sb, HELPER_POSITION);

        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }

       // logger.info("THE SQUENCE IS :" + sb.toString());

        return sb.toString();
    }
    public static void main(String[] args) {
    	String ph = "0000001";
    	 DecimalFormat df=new DecimalFormat("0000000");
    	 String str2=df.format(Integer.parseInt(ph)+1);
    	 System.out.println(str2);
    	System.out.println(generateSequenceNo());
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);
    	String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    	System.out.println(nowTime);
    	JSONObject jsObj = new JSONObject();
    	jsObj.put("code", 500);
		jsObj.put("msg", "数据异常");
		//jsObj.put("details", JSONArray.fromObject("[]").toString());
		jsObj.put("details", "[]");
		System.out.println(jsObj.toString());
	}
}