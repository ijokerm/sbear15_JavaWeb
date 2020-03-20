package net;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class httpServer {
    private static final int PORT = 9999;
    //获取处理器核数
    private static final int COUNT = Runtime.getRuntime().availableProcessors();
    //线程数量和处理的任务量、CPU、内存等资源都相关，
    //一般推荐处理器核数数量的线程
    private static final ExecutorService EXE = Executors.newScheduledThreadPool(COUNT);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        while (true) {
            //多个请求，socket对象 阻塞式方法
            Socket socket = server.accept();//获取客户端请求数据：输入流
            EXE.submit(new httpTask(socket));
        }
    }
}

class httpTask implements Runnable{
    private Socket socket;
    public httpTask(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //获取
        PrintWriter pw = null;
        OutputStream os = null;
        try {
            try {
                is = socket.getInputStream();
                //转换流
                isr = new InputStreamReader(is, "UTF-8");
                //缓冲字符流
                br = new BufferedReader(isr);
                Request request = new Request();
                //请求数据的解析：http协议包的解包
                //1.解析请求行（第一行）：method URL version
                String requestLine = br.readLine();
                String[] requestLines = requestLine.split(" ");
                request.setMethod(requestLines[0]);
                String url = requestLines[1];
                if (url.contains("?")) {
                    String parameters = url.substring(url.indexOf("?") + 1);
                    url = url.substring(0, url.indexOf("?"));
                }
                request.setUrl(requestLines[1]);
                request.setVersion(requestLines[2]);
                //2.解析请求头：
                //key：value每个换行，以空行做结尾
                String header;
                while ((header = br.readLine()) != null && header.length() != 0) {
                    String key = header.substring(0, header.indexOf(":"));
                    String value = header.substring(0, header.indexOf(":") + 1);
                    request.addHeaders(key, value.trim());
                }
                if ("POST".equals(request.getMethod())) {
                    String len = request.getHeaders("Content-Length");
                    if (len != null) {
                        int l = Integer.parseInt(len);
                        char[] chars = new char[l];
                        br.read(chars, 0, 1);
                        String requestBody = new String(chars);
                        request.parseParameters(requestBody);
                    }
                }

                //readLine() 阻塞式方法（有可能造成阻塞--Java main主线程）
                System.out.println(request);
                os = socket.getOutputStream();
                pw = new PrintWriter(os,true);

                if("/302".equals(request.getUrl())) {
                    pw.println("HTTP/1.1 302 重定向");
                    pw.println("Content-Type: text/html;charset=utf-8");
                    pw.println("Location: https://www.baidu.com");
                }else if("/login".equals(request.getUrl())){
                    // 判断用户名密码是否满足条件
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html; charset=utf-8");
                    pw.println();
                    pw.println("<h2>欢迎用户["
                            +request.getParameters("username")
                            +"]登录</h2>");
                }else{
                    // 访问/login.html，转化为访问./login.html
                    InputStream htmlIs = HttpServer.class.getClassLoader()
                            .getResourceAsStream("."+request.getUrl());
                    if(htmlIs != null){
                        pw.println("HTTP/1.1 200 OK");
                        pw.println("Content-Type: text/html; charset=utf-8");
                        pw.println();
                        // 返回webapp下的静态资源文件内容
                        InputStreamReader htmlIsr = new InputStreamReader(htmlIs);
                        BufferedReader htmlBr = new BufferedReader(htmlIsr);
                        String content;
                        while((content=htmlBr.readLine()) != null){
                            pw.println(content);
                        }
                    }else{
                        // 返回404
                        pw.println("HTTP/1.1 404 Not Found");
                        pw.println("Content-Type: text/html; charset=utf-8");
                        pw.println();
                        pw.println("<h2>找不到资源</h2>");
                    }
                }
            } finally {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (br != null)
                    br.close();
                if (pw != null)
                    pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



