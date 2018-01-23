package test.smgp;

import com.zjhc.smproxy.SMGPSMProxy;
import com.zjhc.smproxy.comm.smgp.message.*;
import com.zjhc.smproxy.util.Args;
import com.zjhc.smproxy.util.TypeConvert;

import java.io.IOException;

/**
 * Date: 16-10-20
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company:浙江鸿程 </p>
 *
 * @author SRH
 * @version 1.0
 */
public class SmsProxy extends SMGPSMProxy {
    public SmsProxy(Args args) {
        super(args);
    }

    public void onTerminate() {
        super.close();
        //todo 连接终止时你的逻辑
    }

    public SMGPMessage onDeliver(SMGPDeliverMessage msg) {
        System.out.println(msg.toString());
        if (msg.getIsReport() == 1) {//判断是回执报告 还是 上行短信
            System.out.println("收到报告");
        } else {
            System.out.println("收到上行");
        }
        //todo 你的逻辑

        return new SMGPDeliverRespMessage(msg.getMsgId(), 0);
    }

    public static void main(String[] args) throws Exception {
        Args argsMap = new Args();
        argsMap.set("host", "127.0.0.1");//服务器IP
        argsMap.set("port", "9890");//服务器端口
        argsMap.set("clientid", "333");//帐号
        argsMap.set("shared-secret", "0555");//密码
        argsMap.set("heartbeat-interval", "30");//心跳间隔 单位秒
        argsMap.set("reconnect-interval", "30");//重连间隔 单位秒
        argsMap.set("heartbeat-noresponseout", "5");//心跳无响应重发间隔 单位秒
        argsMap.set("transaction-timeout", "10");//事务(短信发送)超时
        argsMap.set("version", "48");//版本号 十六进制
        argsMap.set("debug", "true");//是否打印日志
        SmsProxy mySMProxy = new SmsProxy(argsMap);
        SMGPSubmitRespMessage resp = null;
        String[] rcvMobile = new String[1];
        rcvMobile[0] = "13355667788";
        String access = "99999";//接入号
        int msgformat = 15;
        String content = "hello world!你好世界";
        //发送普通短信
        SMGPSubmitMessage msg = new SMGPSubmitMessage(
                6,
                1,
                1,
                "",
                "00",
                "000",
                "000",
                msgformat,
                "", "",
                access,
                "",
                rcvMobile,
                content,
                "",
                "");
        try {
            resp = (SMGPSubmitRespMessage) mySMProxy.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--------------------------------------华丽分割线--------------------------------------
        //发送闪信
        short msgSrcTag = 0x0010;
        short msgSrcLen = 8;

//        byte[] content = message.getBytes("iso-10646-ucs-2");
        byte[] TlvBuf = null;
        TlvBuf = new byte[4 + msgSrcLen + 5];
        int loc = 0;
        TypeConvert.short2byte(msgSrcTag, TlvBuf, loc);
        loc += 2;
        TypeConvert.short2byte(msgSrcLen, TlvBuf, loc);
        loc += 2;
        System.arraycopy("12164645".getBytes(), 0, TlvBuf, loc, msgSrcLen);

        // 闪信
        loc += 8;
        TypeConvert.short2byte(0x0013, TlvBuf, loc);
        loc += 2;
        TypeConvert.short2byte(1, TlvBuf, loc);
        loc += 2;
        TlvBuf[loc] = 0;

        String TlvString = new String(TlvBuf);

        //构造submit消息
        content = "闪信一枚hi";
        msg = new SMGPSubmitMessage(
                6,
                1,
                1,
                "",
                "00",
                "000",
                "000",
                msgformat,
                "", "",
                access,
                "",
                rcvMobile,
                content,
                "",
                TlvString);

        try {
            resp = (SMGPSubmitRespMessage) mySMProxy.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //具体项目中使用时，创建SmsProxy单例 ，填入逻辑代码即可
        Thread.sleep(1000*60*60*24);
    }
}
