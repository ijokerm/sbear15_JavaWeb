package util;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import task.DBInit;

import javax.sql.DataSource;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static volatile DataSource DATA_SOURCE;

    /**
     * 提供获取数据库连接池的功能
     * 使用单例模式（多线程安全版本）
     * @return
     * 回顾多线程安全版本的单例模式：
     * 1.为什么在外层判断是否==null
     * 2.synchronized加锁以后，为什么还要判断是否==null
     * 3.为什么DataSource类变量要使用volatile关键字修饰
     * 多线程操作：原子性、可见性（主存拷贝到工作内存），有序性
     * synchronized保证三个特性;volatile保证可见性 有序性
     */
    private static DataSource getDataSource(){
        if(DATA_SOURCE==null){
            synchronized (DBUtil.class){//刚开始时DATA_SOURCE对象都是null
                if(DATA_SOURCE==null){//提高效率 （加锁提高安全，保证不了效率）
                    //初始化操作
                    SQLiteConfig config=new SQLiteConfig();
                    config.setDateStringFormat(Util.DATE_PATTERN);
                    DATA_SOURCE=new SQLiteDataSource();
                    ((SQLiteDataSource)DATA_SOURCE).setUrl(getUrl());
                }
            }
        }
        return DATA_SOURCE;
    }

    /**
     * 获取sqlite数据库文件url的方法
     * @return
     */
    private static String getUrl(){
        //获取target编译文件夹的路径
        URL classesURL= DBInit.class.getClassLoader().getResource("./");
        //获取其父目录路径
        String dir=new File(classesURL.getPath()).getParent();
        String url="jdbc:sqlite://"+dir+File.separator+"Fo.db";
        System.out.println(url);
        return url;
    }
    /**
     * 提供数据库连接的方法
     * 从数据库连接池DataSource.getConnection()来获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    public static void close (Connection connection, Statement statement){
        close(connection,statement,null);
    }

    /**
     * 释放数据库资源
     * @param connection 数据库连接
     * @param statement sql执行对象
     * @param resultSet 结果集
     */
    public static void close (Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("释放数据库资源错误",e);
        }

    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
