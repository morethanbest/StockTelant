package ui.work1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.APIController;
import vo.Exchange;
import vo.OriginInfoVO;

public class SearchPanel extends JPanel implements ActionListener {
	private JTable table;
	private List<OriginInfoVO> list; 
	private JButton btnbefore1;
	private JButton btnbefore2;
	private JButton btnafter1;
	private JButton btnafter2;
	private JButton btna;
	private JButton btnb;
	private JButton btnc;
	private JButton btnd;
	private JButton btne;
	private JButton btnf;
	private long allpage;
	private long thispage;
	private APIController controller; 
	private Exchange exchange;
	private MainPanel mainPanel;
	/**
	 * Create the panel.
	 */
	public SearchPanel(MainPanel mainPanel) {
		setLayout(null);
		
		this.mainPanel = mainPanel;
        //股票列表
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 30, 917, 418);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"日期", "股票代码","股票名称","开盘价","收盘价","最高价","最低价","成交量"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		tableModel.setColumnCount(8);
		tableModel.setRowCount(15);
		table.setRowHeight(25);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false); 
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(),30)); 
		scrollPane.setViewportView(table);

		table.addMouseListener(new java.awt.event.MouseAdapter(){

			public void mouseClicked(MouseEvent e){
				// TODO Auto-generated method stub
				int row=table.getSelectedRow();
				if(list != null&&row < list.size()){
					OriginInfoVO vo = list.get(row);
					mainPanel.skipToInfo(vo);
				}
				
			}

		});
        //翻页键		
		btnbefore1 = new JButton("<<");
		btnbefore1.setBounds(30, 460, 45, 30);
		add(btnbefore1);
		
		btnbefore2 = new JButton("<");
		btnbefore2.setBounds(75, 460, 45, 30);
		add(btnbefore2);
		
		btna = new JButton("1");
		btna.setBounds(120, 460, 50, 30);
		add(btna);
		
		btnb = new JButton("2");
		btnb.setBounds(170, 460, 50, 30);
		add(btnb);
		
		btnc = new JButton("3");
		btnc.setBounds(220, 460, 50, 30);
		add(btnc);
		
		btnd = new JButton("4");
		btnd.setBounds(270, 460, 50, 30);
		add(btnd);
		
		btne = new JButton("5");
		btne.setBounds(320, 460, 50, 30);
		add(btne);
		
		btnf = new JButton("6");
		btnf.setBounds(370, 460, 50, 30);
		add(btnf);

		btnafter1 = new JButton(">");
		btnafter1.setBounds(420, 460, 45, 30);
		add(btnafter1);
		
		btnafter2 = new JButton(">>");
		btnafter2.setBounds(465, 460, 45, 30);
		add(btnafter2);
		
		btnbefore1.addActionListener(this);
		btnbefore2.addActionListener(this);
		btna.addActionListener(this);
		btnb.addActionListener(this);
		btnc.addActionListener(this);
		btnd.addActionListener(this);
		btne.addActionListener(this);
		btnf.addActionListener(this);
		btnafter1.addActionListener(this);
		btnafter2.addActionListener(this);
		btna.setSelected(true);
		
        exchangeturning(Exchange.sh);
	}
	
	public void displayInTable(){
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		list = controller.getInfoByCity(thispage*15-14, thispage*15, exchange);
		if(list == null){
			for(int j=0;j<15;j++){
				String[] rowString = {"","","","","","","",""};
				tableModel.addRow(rowString);
			}
			return;
		}
		try{
			for(OriginInfoVO vo:list){
				String[] rowString = new String[8];
				rowString[0]=vo.getDate();
				rowString[1]=vo.getCode();
				rowString[2]=vo.getName();
				rowString[3]=vo.getOpen();
				rowString[4]=vo.getClose();
				rowString[5]=vo.getHighest();
				rowString[6]=vo.getLowest();
				rowString[7]=vo.getVolume();
				tableModel.addRow(rowString);
			}
			int i=list.size();
			if(list.size()<15){
				for(;i<15;i++){
					String[] rowString = {"","","","","","","",""};
					tableModel.addRow(rowString);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
	public void pageturning(){
		if(thispage<1){
			thispage = 1;
		}else if(thispage>allpage){
			thispage = allpage;
		}else if(thispage<4){
			btna.setText("1");
			btnb.setText("2");
			btnc.setText("3");
			btnd.setText("4");
			btne.setText("5");
			btnf.setText("6");
		}else if(thispage>allpage-4){
			btna.setText(Long.toString(allpage-5));
			btnb.setText(Long.toString(allpage-4));
			btnc.setText(Long.toString(allpage-3));
			btnd.setText(Long.toString(allpage-2));
			btne.setText(Long.toString(allpage-1));
			btnf.setText(Long.toString(allpage));
		}else{
			btna.setText(Long.toString(thispage-2));
			btnb.setText(Long.toString(thispage-1));
			btnc.setText(Long.toString(thispage));
			btnd.setText(Long.toString(thispage+1));
			btne.setText(Long.toString(thispage+2));
			btnf.setText(Long.toString(thispage+3));
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		btna.setSelected(false);
		btnb.setSelected(false);
		btnc.setSelected(false);
		btnd.setSelected(false);
		btne.setSelected(false);
		btnf.setSelected(false);
		if(e.getSource().equals(btnbefore1)){
			thispage = 1;
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnbefore2)){
			thispage--;
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btna)){
			thispage = Long.parseLong(btna.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnb)){
			thispage = Long.parseLong(btnb.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnc)){
			thispage = Long.parseLong(btnc.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnd)){
			thispage = Long.parseLong(btnd.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btne)){
			thispage = Long.parseLong(btne.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnf)){
			thispage = Long.parseLong(btnf.getText());
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnafter1)){
			thispage++;
			pageturning();
			displayInTable();
		}else if(e.getSource().equals(btnafter2)){
			thispage = allpage;
			pageturning();
			displayInTable();
		}
		if (btna.getText().equals(Long.toString(thispage))) {
			btna.setSelected(true);
		} else if (btnb.getText().equals(Long.toString(thispage))) {
			btnb.setSelected(true);
		} else if (btnc.getText().equals(Long.toString(thispage))) {
			btnc.setSelected(true);
		} else if (btnd.getText().equals(Long.toString(thispage))) {
			btnd.setSelected(true);
		} else if (btne.getText().equals(Long.toString(thispage))) {
			btne.setSelected(true);
		} else if (btnf.getText().equals(Long.toString(thispage))) {
			btnf.setSelected(true);
		}
	}
	
	public void exchangeturning(Exchange change){
		//计算总共页数
		exchange = change;
		controller = new APIController();
		long num = controller.getNumByCity(exchange);
		allpage = num/15;
		if(num%15!=0){
			allpage++;
		}
		thispage = 1;
		
		btna.setSelected(true);
		btnb.setSelected(false);
		btnc.setSelected(false);
		btnd.setSelected(false);
		btne.setSelected(false);
		btnf.setSelected(false);
		
		//展现股票列表JTable
		pageturning();
		displayInTable();
	}
}
