package com.monkey;

import java.io.*;

public class RunMain {

    // error文件的路径
    private static final String resultpath = "/Users/Shared/test/monkey/result.txt";
    private static final String errorpath = "/Users/Shared/test/monkey/error.txt";
    private static final String detailpath = "/Users/Shared/test/monkey/detail.txt";


    //读取txt文件，返回字符串
    public static String txt2String(String file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator()).append(s);//换行+内容
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }


    // 重新写入字符串到文件
    public static void WritePrintln(String string, String path) {
        try {
            File file = new File(path);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(string);// 换行
//            ps.append(string);// 不换行
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 追加写入字符串到文件
    public static void WriteAppend(String string, String path) {
        try {
            File file = new File(path);
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            ps.println(string);// 换行
//            ps.append(string);// 不换行
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 读取文件内容打印到控制台
    public static void PrinToConsole(String path) {
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String str = reader.readLine();
            if (str == null) {
                System.out.println("没有错误日志");
            }
            while (str != null) {
                System.out.println(str);
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查找文件中字符串的数量
    public static int count(String filename, String word) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);//// 创建一个文件读取流对象，和文件相关联。
        BufferedReader br = new BufferedReader(fr);// 从fr中读取文本，缓冲各个字符
        StringBuilder strb = new StringBuilder();// 字符串连接
        while (true) {
            String line = br.readLine();// 将br里的字符串逐行返回给line
            if (line == null) {
                break;
            }
            strb.append(line);
        }

        String result = strb.toString();
        int count = 0;
        int index = 0;
        while (true) {
            index = result.indexOf(word, index);
            // index = result.indexOf(word, index + 1);
            if (index >= 0) {
                index = index + word.length();
                count++;
            } else {
                break;
            }
        }
        br.close();
        return count;
    }

    //打印到控制台
    public static void testcase() throws FileNotFoundException, IOException {
        PrinToConsole(detailpath);
        System.out.println();
        System.out.println("---------------错误日志统计--------------");
        System.out.println();
        System.out.println("CRASH  " + count(errorpath, "CRASH: cn.babyfs.android"));
        System.out.println("error  " + count(errorpath, "error"));
        System.out.println("anr  " + count(errorpath, "anr"));
        System.out.println("NullPointerException 空指针  " + count(errorpath, "Short Msg: java.lang.NullPointerException"));
        System.out.println("ArrayIndexOutOfBoundsException 数据溢出  " + count(errorpath, "Short Msg: java.lang.ArrayIndexOutOfBoundsException"));
        System.out.println("ClassNotFoundException 类不存在  " + count(errorpath, "CShort Msg: java.lang.lassNotFoundException"));
        System.out.println("ClassCastException 类型转换出错  " + count(errorpath, "Short Msg: java.lang.ClassCastException"));
        System.out.println("ArithmeticException 数学运算异常 " + count(errorpath, "Short Msg: java.lang.ArithmeticException"));
        System.out.println("IllegalArgumentException 方法参数异常  " + count(errorpath, "Short Msg: java.lang.IllegalArgumentException"));
        System.out.println("FileNotFoundException 文件未找到  " + count(errorpath, "Short Msg: java.lang.FileNotFoundException"));
        System.out.println("NumberFormatException 数值转换异常  " + count(errorpath, "Short Msg: java.lang.NumberFormatException"));
        System.out.println("StackOverflowError 线程栈满  " + count(errorpath, "Short Msg: java.lang.StackOverflowError"));
        System.out.println("OutOfMemoryError 内存溢出  " + count(errorpath, "Short Msg: java.lang.OutOfMemoryError"));
        System.out.println("IllegalStateException 无效状态异常  " + count(errorpath, "Short Msg: java.lang.IllegalStateException"));
        System.out.println("UnsatisfiedLinkError 未获取到so库包  " + count(errorpath, "Short Msg: java.lang.UnsatisfiedLinkError"));
        System.out.println("Native crash 底层崩溃  " + count(errorpath, "Short Msg: Native crash"));
        System.out.println();
        System.out.println();
        System.out.println("---------------错误日志内容--------------");
        System.out.println();
        PrinToConsole(errorpath);
    }


    //把结果写入result文件
    public static void testcase1() throws FileNotFoundException, IOException {
        WritePrintln("---------------错误日志统计--------------", resultpath);
        WriteAppend("", resultpath);
        WriteAppend("CRASH  " + count(errorpath, "CRASH: cn.babyfs.android"), resultpath);
        WriteAppend("error  " + count(errorpath, "error"), resultpath);
        WriteAppend("anr  " + count(errorpath, "anr"), resultpath);
        WriteAppend("NullPointerException 空指针  " + count(errorpath, "Short Msg: java.lang.NullPointerException"), resultpath);
        WriteAppend("ArrayIndexOutOfBoundsException 数据溢出  " + count(errorpath, "Short Msg: java.lang.ArrayIndexOutOfBoundsException"), resultpath);
        WriteAppend("ClassNotFoundException 类不存在  " + count(errorpath, "CShort Msg: java.lang.lassNotFoundException"), resultpath);
        WriteAppend("ClassCastException 类型转换出错  " + count(errorpath, "Short Msg: java.lang.ClassCastException"), resultpath);
        WriteAppend("ArithmeticException 数学运算异常 " + count(errorpath, "Short Msg: java.lang.ArithmeticException"), resultpath);
        WriteAppend("IllegalArgumentException 方法参数异常  " + count(errorpath, "Short Msg: java.lang.IllegalArgumentException"), resultpath);
        WriteAppend("FileNotFoundException 文件未找到  " + count(errorpath, "Short Msg: java.lang.FileNotFoundException"), resultpath);
        WriteAppend("NumberFormatException 数值转换异常  " + count(errorpath, "Short Msg: java.lang.NumberFormatException"), resultpath);
        WriteAppend("StackOverflowError 线程栈满  " + count(errorpath, "Short Msg: java.lang.StackOverflowError"), resultpath);
        WriteAppend("OutOfMemoryError 内存溢出  " + count(errorpath, "Short Msg: java.lang.OutOfMemoryError"), resultpath);
        WriteAppend("IllegalStateException 无效状态异常  " + count(errorpath, "Short Msg: java.lang.IllegalStateException"), resultpath);
        WriteAppend("UnsatisfiedLinkError 未获取到so库包  " + count(errorpath, "Short Msg: java.lang.UnsatisfiedLinkError"), resultpath);
        WriteAppend("Native crash 底层崩溃  " + count(errorpath, "Short Msg: Native crash"), resultpath);
    }


    public static void main(String[] args) throws Exception {
        testcase();
        testcase1();
    }
}
