package com.monkey;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class JavaMail {

    //配置文件的路径
    private static final String propertiespath = "/Users/Shared/test/monkey/element.properties1";

    //读取配置文件
    static ProUtil properties = new ProUtil(propertiespath);// 引入指定目录配置文件
    // 读取key值
    static String recipientslist = properties.getPro("recipientslist1");

    // 收件人列表
    static String[] recipients = recipientslist.split(",");//字符串转数组


    public static void postMail(String recipients[], String subject, String message, String from)
            throws MessagingException {

        // 设置主机smtp地址
        Properties props = new Properties();
        // props.put("smtp.creditease.cn", "pop3.creditease.cn");
        // props.put("mail.smtp.host", "smtp.creditease.cn");
        props.put("mail.smtp.host", "smtp.qiye.163.com");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");

        // 创建一些属性并获取默认的Session
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mengfanting@babyfs.cn", "mft666666.");
            }
        });
        session.setDebug(false);// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态

        // 创建一条消息
        Message msg = new MimeMessage(session);

        // 设置发件人地址
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        // 设置收件人地址
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // 可选：如果需要，您还可以在电子邮件中设置自定义标题
        msg.addHeader("MyHeaderName", "myHeaderValue");

        // 设置主题类型
        try {
            msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置内容类型
        msg.setContent(message, "text/plain;charset = UTF-8"); //txt普通文件
//		msg.setContent(message, "text/html;charset = UTF-8"); // html文件
        Transport.send(msg);
        System.out.println("邮件发送成功: " + Arrays.toString(recipients));


    }

}
