package Stock.Server;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.awt.Font;

public class MainPanel extends JPanel implements ActionListener {
    private JButton btnswitch;
    private JButton btnupdate;
    private boolean isrun;
    private JLabel labeltime;
    private JLabel labelstatus;
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);
		
		isrun = false;
		JLabel lblNewLabel = new JLabel("StockTalent服务器");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 26));
		lblNewLabel.setBounds(118, 20, 260, 33);
		add(lblNewLabel);
		
		JLabel label = new JLabel("状态：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(128, 62, 59, 33);
		add(label);
		
		btnswitch = new JButton("启动");
		btnswitch.setBounds(67, 227, 93, 23);
		add(btnswitch);
		btnswitch.addActionListener(this);
		
		btnupdate = new JButton("更新");
		btnupdate.setBounds(285, 227, 93, 23);
		add(btnupdate);
		btnupdate.addActionListener(this);
		
		labelstatus = new JLabel("关闭");
		labelstatus.setFont(new Font("宋体", Font.PLAIN, 18));
		labelstatus.setBounds(249, 66, 54, 25);
		add(labelstatus);
		
		JLabel lblNewLabel_3 = new JLabel("更新时间：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(128, 130, 93, 33);
		add(lblNewLabel_3);
		
		labeltime = new JLabel("...");
		labeltime.setFont(new Font("宋体", Font.PLAIN, 18));
		labeltime.setBounds(249, 133, 200, 26);
		add(labeltime);

		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnswitch)){
			if(isrun){
				isrun = false;
				labelstatus.setText("关闭");
				btnswitch.setText("启动");
			}else{
				isrun = true;
				labelstatus.setText("运行中");
				btnswitch.setText("关闭");
			}
		}else if(e.getSource().equals(btnupdate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			System.out.println(time);
			labeltime.setText(time);
		}
		
	}
}
