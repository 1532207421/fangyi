
package ��;
 
import java.sql.*;  
import javax.swing.*;

import java.awt.*;  
import java.awt.event.*;  
import java.awt.Color; 
 
public class LLogin {
	
	 public static void main(String[] args) {  

	        new loginFrame();   
	    }  	  
	} 
 	
	class loginFrame extends JFrame implements ActionListener{  
	    Box box1,box2,baseBox;  
	    JLabel userName,userPwd,tubiao;  
	    JTextField nameField;  
	    JPasswordField pwdField;  
	    JButton button;  
	    JTabbedPane choose;  
	    JPanel panel1,panel2; 
		private AbstractButton mang;
		private AbstractButton stu;
		private String xinxi; 
	    loginFrame(){  
	        setBackground(Color.orange);  
	        tubiao=new JLabel(new ImageIcon("E:\\\\����\\\\1.jpg"));  
	        add(tubiao,BorderLayout.NORTH);  
	        userName=new JLabel("�˺�",JLabel.CENTER);  
	        userPwd=new JLabel("����",JLabel.CENTER);  
	        nameField=new JTextField(8);  
	        pwdField=new JPasswordField(8); 
	        
	        JRadioButton mang = new JRadioButton("�� ʦ");
	        JRadioButton stu = new JRadioButton("ѧ  ��");
	        
	        panel1=new JPanel();  
	        panel2=new JPanel();  
	        choose=new JTabbedPane();  
	        choose.add("��¼����",panel1);  
	        panel1.setLayout(new GridLayout(3,2));  
	        panel1.add(userName);panel1.add(nameField);  
	        panel1.add(userPwd);panel1.add(pwdField); 
	        panel1.add(mang);panel1.add(stu);
	        add(choose,BorderLayout.CENTER);  
	        button=new JButton("��½");  
	        add(button,BorderLayout.SOUTH);  
	        button.addActionListener(this);  
	        //Сͼ��
	        ImageIcon tubiao=new ImageIcon("E:\\����\\1.jpg"); 
	        setIconImage(tubiao.getImage());  
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        setVisible(true);  
	        setBounds(400,150,550,450);  
	        setTitle("�������ϵͳ");  
	        validate();  
	    }  
 
 
	    public void actionPerformed(ActionEvent e){  
	        String name,pwd;  
	        name=nameField.getText();  
	        pwd=pwdField.getText();  
	        try{  
	          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //�������ݿ����� 
	           
	        }  
	        catch(ClassNotFoundException ex){  
	        System.out.println(ex);  
	        }  
	        try{  
	            Connection con;  
	            Statement sql;  
	            ResultSet mang; 
	            ResultSet stu;
	            String url,userName,userPwd;  
	             // �������ݿ�����
	            url="jdbc:sqlserver://localhost:1433;DatabaseName=test";
	        
	            userName="sa";  
	            userPwd="bulikai.0";  
	            con=DriverManager.getConnection(url,userName,userPwd);  
	            sql=con.createStatement();  
	            mang=sql.executeQuery("select * from login where name ='"+name+"' and pwd='"+pwd+"'");//��Ӧ�Լ����ݿ⽨�ı���д
	            
	            int q=0;  
	            while(mang.next()){  
	                q++;  
	            }  
	           
	            if(q>0){  

	                JOptionPane.showMessageDialog(this, "��½�ɹ���","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
	                
	                this.dispose();  
                        new StudentManage();   
	                  
	            }  
	            
	            stu=sql.executeQuery("select * from test.dbo.lll where name ='"+name+"' and pwd='"+pwd+"'");//��Ӧ�Լ����ݿ⽨�ı���д 
	            while(stu.next()){  
	                q++;  
	            }  
	            if(q>0){  

	                JOptionPane.showMessageDialog(this, "��½�ɹ���","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
	                
	                this.dispose();  
                        new StuAddDialog(null, xinxi, rootPaneCheckingEnabled);   
	                  
	            }  
	            else  
	                JOptionPane.showMessageDialog(this, "�˺Ż����������!","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE); 
	        }  
	        catch(SQLException exp){  
	            System.out.println(exp);  
	        } 
	        
	    }  
}