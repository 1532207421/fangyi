package 测;
import java.sql.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class sqlchtxt {// JDBC 驱动名及数据库 URL

private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

private static String url = "jdbc:sqlserver://127.0.0.1:1433;database=test";
private static String user = "sa";
private static String passwd = "bulikai.0";

public static void wf(String file, String conent) {
BufferedWriter out = null;
try {
out = new BufferedWriter(new OutputStreamWriter(
new FileOutputStream(file, true)));
out.write(conent+"\r\n");
} catch (Exception e) {
e.printStackTrace();
} finally {
try {
out.close();
} catch (IOException e) {
e.printStackTrace();
}
}
}

public static void main(String[] args) {
Connection conn = null;
Statement stmt = null;
try{
// 注册 JDBC 驱动
	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载数据库驱动 

    // 打开链接
    System.out.println("连接数据库...");
    conn = DriverManager.getConnection(url, user, passwd);

    // 执行查询
    System.out.println(" 实例化Statement对象...");
    stmt = conn.createStatement();
    String sql;
    sql = "SELECT stuId, stuname, stuSex,stutel FROM test.dbo.stue2";
    ResultSet rs = stmt.executeQuery(sql);

    // 展开结果集数据库
    while(rs.next()){
        // 通过字段检索
        int id  = rs.getInt("stuId");
        String name = rs.getString("stuname");
        String Sex = rs.getString("stuSex"); 
        String stutel = rs.getString("stutel"); 
        // 输出数据
                    String fn = "E://1.txt"; //文件名
        System.out.print("ID: " + id +" name: " + name +"Sex: " + Sex+"stutel: "+stutel);
                    wf(fn, "ID: " + id +" name: " + name +"Sex: " + Sex+"stutel: "+stutel); //你要写什么，这里就加上什么
        System.out.print("\n");
    }
    // 完成后关闭
    rs.close();
    stmt.close();
    conn.close();
}catch(SQLException se){
    // 处理 JDBC 错误
    se.printStackTrace();
}catch(Exception e){
    // 处理 Class.forName 错误
    e.printStackTrace();
}finally{
    // 关闭资源
    try{
        if(stmt!=null) stmt.close();
    }catch(SQLException se2){
    }// 什么都不做
    try{
        if(conn!=null) conn.close();
    }catch(SQLException se){
        se.printStackTrace();
    }
}
System.out.println("Goodbye!");
}

}