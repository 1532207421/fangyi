package ��;
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class StudentManage extends JFrame implements ActionListener 
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new StudentManage();
	}

	//========���ؼ�
	private JLabel queryLab = null;
	private JTextField queryTxt = null;
	private JButton queryBtn = null;
	private JButton allBtn = null;
	private JTable resultTb = null;
	private JScrollPane jsp = null;
	private JButton addBtn = null;
	private JButton txtBtn = null;
	private JButton deleteBtn = null;
	private JButton updateBtn = null;
	private JPanel top = null;
	private JPanel bottom = null;
	//========
	private StuModel sm = null;
	 private PrintWriter out = null;
	
	//���캯��
	public StudentManage()
	{
		/***************************��ʼ�����ؼ�***********************/
		//========��ѯ��
		queryLab = new JLabel("������tel:");
		queryTxt = new JTextField(10);
		queryBtn = new JButton("��ѯ");
		allBtn = new JButton("ȫ��");

		//......��Ӳ�ѯ������
		queryBtn.addActionListener(this);
		queryBtn.setActionCommand("query");
		allBtn.addActionListener(this);
		allBtn.setActionCommand("all");
 
  
		//========��ɾ����
		addBtn = new JButton("���");
		deleteBtn = new JButton("ɾ��");
		updateBtn = new JButton("�޸�");
		//......�����ɾ��������
		addBtn.addActionListener(this);
		addBtn.setActionCommand("add");
		deleteBtn.addActionListener(this);
		deleteBtn.setActionCommand("delete");
		updateBtn.addActionListener(this);
		updateBtn.setActionCommand("update");


		//========�����������岼��
		//......�����ѯ��
		top = new JPanel();
		top.add(queryLab);
		top.add(queryTxt);
		top.add(queryBtn);
		top.add(allBtn);

		//......�ײ���ɾ����
		bottom = new JPanel();
		bottom.add(addBtn);
		bottom.add(deleteBtn);
		bottom.add(updateBtn);
		//......�м����ʾ��
		sm = new StuModel();
		String sql = "select * from test.dbo.stue2";
		sm.queryStu(sql, null);
		resultTb = new JTable(sm);
		jsp = new JScrollPane(resultTb);
		//......�������岼��
		this.add(top,BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
		//========���ô�������
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	//����
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("query")) {
			/*********************��ѯ***********************/
			//========��ȡ����绰
			String tel = queryTxt.getText().trim();
			if(tel.length() != 0) {
				//========������Чʱ��ִ�в�ѯ
				//......�������
				String sql = "select * from test.dbo.stue2 where stutel=?";
				String []paras = {tel};
				//......����ģ��
				jtableUpdate(sql, paras);
			} else {
			
				JOptionPane.showMessageDialog(this, "��ϵ��ʽ���벻��Ϊ��");
			}
		} 
		
		else if(e.getActionCommand().equals("add")) {
			/*********************���***********************/
			new StuAddDialog(this, "�����Ϣ", true);
			String sql = "select * from test.dbo.stue2";
			jtableUpdate(sql, null);
		} else if(e.getActionCommand().equals("all")) {
			/*********************ȫ����ʾ***********************/
			String sql = "select * from test.dbo.stue2";
			jtableUpdate(sql, null);
		} 
		else if(e.getActionCommand().equals("delete")) {
			/*********************ɾ��***********************/
			//========��ȡѡ���к�
			int rowNum = this.resultTb.getSelectedRow();
			if(rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			//========��ȡID��
			String stuId = (String)sm.getValueAt(rowNum, 0);
			//========ɾ��
			String sql = "delete from test.dbo.stue2 where stuId=?";
			String []paras = {stuId};
			StuModel tmp = new StuModel();
			tmp.cudStu(sql, paras);
			//========����ģ��
			sql = "select * from test.dbo.stue2";
			jtableUpdate(sql, null);
		} else if(e.getActionCommand().equals("update")) {
			/*********************�޸�***********************/
			//========��ȡѡ���к�
			int rowNum = this.resultTb.getSelectedRow();
			if(rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			new StuUpdateDialog(this, "�޸���Ϣ", true, sm, rowNum);
			String sql = "select * from test.dbo.stue2";
			jtableUpdate(sql, null);
		}
	}
  
	//========����JTable������
	public void jtableUpdate(String sql, String[] paras)
	{
		//......����ģ��
		sm = new StuModel();
		sm.queryStu(sql, paras);
		//......������ʾ
		resultTb.setModel(sm);
	}



}