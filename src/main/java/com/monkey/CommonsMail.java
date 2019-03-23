package com.monkey;


import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class CommonsMail {

    //配置文件的路径
    private static final String propertiespath = "/Users/Shared/test/monkey/element.properties";

    //读取配置文件
    private static ProUtil properties = new ProUtil(propertiespath);// 引入指定目录配置文件
    // 读取key值:收件人
//    private static String recipientslist = properties.getPro("recipientslist");
    private static String recipientslist = properties.getPro("recipientslist");
    // 读取key值：抄送人
    private static String cclist = properties.getPro("cclist");
    // 读取key值：附件的路径
    private static String picturePath = properties.getPro("picturePath");


    // 收件人列表
    private static String[] recipients = recipientslist.split(",");//字符串转数组
    // 抄送人列表
    private static String[] cc = cclist.split(",");//字符串转数组
    // 附件的路径
    private static String setPath = picturePath;


    public static void send(String msg) throws Exception {
        attach(recipients, cc, setPath, msg);

    }

    private static void attach(String[] to, String[] tocc, String path, String txt) throws Exception {
        HtmlEmail email = new HtmlEmail();
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path); // 邮件附件路径
        attachment.setName("");// 邮件发送出去时附件名
        email.attach(attachment);
        email.setHostName("smtp.qiye.163.com"); // 主机名
        email.setFrom("mengfanting@babyfs.cn"); // 发送时发件人名称
        email.setCharset("UTF-8");
        if (to != null) {
            for (String email2 : to) {
                if (email2 != null && email2.trim().length() > 0) {
                    email.addTo(email2.trim());
                }
            }
        }
        if (tocc != null) {
            for (String email2 : tocc) {
                if (email2 != null && email2.trim().length() > 0) {
                    email.addCc(email2.trim());
                }
            }
        }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 定义时间格式
        String time = sdf.format(new Date());// 获取当前时间

        email.setSubject("AndroidApp稳定性脚本测试结果 " + time);// 邮件主题
        email.setAuthentication("mengfanting@babyfs.cn", "mft666666.");//密码或者授权码
        email.setTextMsg(txt);// 邮件内容
//        email.setHtmlMsg(txt);// 邮件内容
        email.send();
        System.out.println("邮件发送成功！\n" + "收件人：\n" + Arrays.toString(recipients) + "\n" + "抄送人：\n" + Arrays.toString(cc));

    }
}
