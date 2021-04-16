package com.example.myapplication.utils;

import android.database.SQLException;

import com.example.myapplication.Bean.CommodityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlServer {

    Connection con = null;
    //超时时间
    int timeOut=3000;
    String _ip,_database,_username,_password;

    /**
     *构造函数
     * @param ip 服务器ip地址
     * @param database 连接数据库名称
     * @param username 数据库用户名
     * @param password 数据库密码
     */
    public SqlServer(String ip, String database, String username, String password)
    {
        _ip=ip;
        _database=database;
        _username=username;
        _password=password;
    }

    /**
     * 启动连接
     * @return 返回连接数据库成功或失败
     */

    public boolean connect()
    {
        try { // 加载驱动程序
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://"+_ip+":1433/"+_database, _username,
                    _password);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序出错");
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * 删除或修改
     * @param sql sql语句
     * @return 返回影响行数
     */
    public int getNum(String sql){
        int rs = 0;
        try
        {
            Statement stmt = con.createStatement();//创建Statement
            stmt.setQueryTimeout(timeOut);
            rs = stmt.executeUpdate(sql);//ResultSet类似Cursor
            stmt.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        } catch (java.sql.SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    /**
     * 查询第一行第一列
     * @param sql sql语句
     * @return 返回字符串（第一行第一列）
     */
    public String getString(String sql){
        String reauslt="";
        try
        {
            Statement stmt = con.createStatement();//创建Statement
            stmt.setQueryTimeout(timeOut);
            ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Cursor
            while (rs.next()) {
                reauslt=rs.getString(1);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        } catch (java.sql.SQLException e) {
            System.out.println(e);
        }
        return reauslt;
    }

    /**
     * 查询多行多列
     * @param sql sql语句
     * @return 返回ResultSet
     */
    public ResultSet getResultSet(String sql)
    {
        ResultSet rs = null;
        try
        {
            Statement stmt = con.createStatement();
            stmt.setQueryTimeout(timeOut);
            rs = stmt.executeQuery(sql);
        }
        catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
        return rs;
    }

    /**
     * 查询指定列
     * @param sql sql语句
     * @param columnName 要输出的列名
     * @return 返回动态数组
     */
    public List<CommodityBean> getList(String sql)
    {
        ArrayList<CommodityBean> rslist = new ArrayList<CommodityBean>();
        try
        {
            Statement stmt = con.createStatement();
            stmt.setQueryTimeout(timeOut);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rslist.add(new CommodityBean(rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        } catch (java.sql.SQLException e)
        {
            System.out.println(e);
        }
        return rslist;
    }
}
