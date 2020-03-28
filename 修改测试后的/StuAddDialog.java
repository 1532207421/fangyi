package 测;
 
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.*;
 
public class StuAddDialog extends JDialog implements ActionListener{
	//=========面板控件
	//......左侧标题栏
	private JLabel idLab,nameLab,sexLab,telLab,jgLab,deptLab;
	//......右侧信息选择填写栏
	private JTextField idTxt,nameTxt,sexTxt,telTxt,jgTxt,deptTxt;
	//......添加和取消按钮
	private JButton addBtn,cancelBtn;
	//......布局控件
	private JPanel left,center,bottom;

	
	//构造函数
	public StuAddDialog(Frame owner, String title, boolean modal) 
	{
		//========重写父类方法
		super(owner, title, modal);
		//========左侧标签栏
		idLab = new JLabel("学号: ");
		nameLab = new JLabel("姓名: ");
		sexLab = new JLabel("性别: ");
		telLab = new JLabel("联系方式: ");
		jgLab = new JLabel("是否健康: ");
		deptLab = new JLabel("学院: ");
		//========右侧信息填写栏
		idTxt = new JTextField();
		nameTxt = new JTextField();
		sexTxt = new JTextField();
		telTxt = new JTextField();
		jgTxt = new JTextField();
		deptTxt = new JTextField();
		//========添加和取消按钮
		addBtn = new JButton("添加");
		cancelBtn = new JButton("取消");
		//......添加监听
		addBtn.addActionListener(this);
		addBtn.setActionCommand("add");
		cancelBtn.addActionListener(this);
		cancelBtn.setActionCommand("cancel");
		//========创建布局
		//......创建左边栏
		left = new JPanel();
		left.setLayout(new GridLayout(6, 1));
		left.add(idLab);  left.add(nameLab); 
		left.add(sexLab); left.add(telLab); 
		left.add(jgLab);  left.add(deptLab); 
		//......创建右边栏
		center = new JPanel();
		center.setLayout(new GridLayout(6, 1));
		center.add(idTxt);  center.add(nameTxt);
		center.add(sexTxt); center.add(telTxt);
		center.add(jgTxt);  center.add(deptTxt);
		//========底层添加和取消按钮
		bottom = new JPanel();
		bottom.add(addBtn);
		bottom.add(cancelBtn);
		//========整体布局
		this.add(left,BorderLayout.WEST);
		this.add(center,BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
		//========设置窗口属性
		
		this.setSize(300, 250);
		this.setResizable(false);
		this.setVisible(true);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("add")) {
			/***********************添加信息**************************/
			StuModel tmp = new StuModel();
			String sql = "insert into test.dbo.stue2 values(?,?,?,?,?,?)";
			String []paras = {idTxt.getText(),nameTxt.getText(),sexTxt.getText(),
							telTxt.getText(),jgTxt.getText(),deptTxt.getText()};
			if(!tmp.cudStu(sql, paras))
				
				JOptionPane.showMessageDialog(this, "添加信息失败");
			//========关闭窗口
			this.dispose();
		} else if(e.getActionCommand().equals("cancel")) {
			//========关闭窗口
			
			this.dispose();
		}
	}

}