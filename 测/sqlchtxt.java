package ��;
import java.sql.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class sqlchtxt {// JDBC �����������ݿ� URL

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
// ע�� JDBC ����
	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //�������ݿ����� 

    // ������
    System.out.println("�������ݿ�...");
    conn = DriverManager.getConnection(url, user, passwd);

    // ִ�в�ѯ
    System.out.println(" ʵ����Statement����...");
    stmt = conn.createStatement();
    String sql;
    sql = "SELECT stuId, stuname, stuSex,stutel FROM test.dbo.stue2";
    ResultSet rs = stmt.executeQuery(sql);

    // չ����������ݿ�
    while(rs.next()){
        // ͨ���ֶμ���
        int id  = rs.getInt("stuId");
        String name = rs.getString("stuname");
        String Sex = rs.getString("stuSex"); 
        String stutel = rs.getString("stutel"); 
        // �������
                    String fn = "E://1.txt"; //�ļ���
        System.out.print("ID: " + id +" name: " + name +"Sex: " + Sex+"stutel: "+stutel);
                    wf(fn, "ID: " + id +" name: " + name +"Sex: " + Sex+"stutel: "+stutel); //��Ҫдʲô������ͼ���ʲô
        System.out.print("\n");
    }
    // ��ɺ�ر�
    rs.close();
    stmt.close();
    conn.close();
}catch(SQLException se){
    // ���� JDBC ����
    se.printStackTrace();
}catch(Exception e){
    // ���� Class.forName ����
    e.printStackTrace();
}finally{
    // �ر���Դ
    try{
        if(stmt!=null) stmt.close();
    }catch(SQLException se2){
    }// ʲô������
    try{
        if(conn!=null) conn.close();
    }catch(SQLException se){
        se.printStackTrace();
    }
}
System.out.println("Goodbye!");
}

}