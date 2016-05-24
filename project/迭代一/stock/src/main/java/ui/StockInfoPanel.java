package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.APIController;
import ui.dataselect.CalendarPop;
import ui.dataselect.DayClickListener;
import ui.dataselect.DayPanel;
import vo.ConcreteInfoVO;
import java.awt.Font;

public class StockInfoPanel extends JPanel{
	private JTable table;
	private JButton btnStart;
	private JButton btnEnd;
	private String code;
	private String name;
	private String startTime;
	private String endTime;
	private APIController controller;
	List<ConcreteInfoVO> list;
	private JLabel lblCode;
	private JLabel lblName;
	/**
	 * Create the panel.
	 * @param card 
	 * @param panel 
	 */
	public StockInfoPanel(CardLayout card, JPanel panel) {
		this.controller = new APIController();
		setLayout(null);
		
		lblCode = new JLabel(code);
		lblCode.setFont(new Font("黑体", Font.PLAIN, 20));
		lblCode.setBounds(44, 13, 162, 42);
		add(lblCode);
		
		Calendar end = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		start.clear();
		long l = end.getTimeInMillis() - (long)30 * 86400000;
		start.setTimeInMillis(l);
		
		lblName = new JLabel(name);
		lblName.setFont(new Font("黑体", Font.PLAIN, 21));
		lblName.setBounds(229, 13, 162, 42);
		add(lblName);
		
		btnEnd = new JButton("截止日期");
		setDateText(btnEnd, end);
		endTime = setTime(btnEnd.getText());
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalendarPop calendarPop  = new CalendarPop();
				calendarPop.addDayClickListener(new DayClickListener() {
					
					@Override
					public void dayClicked(DayPanel day, MouseEvent e) {
						setDateText(btnEnd,day.getCalendar());
						calendarPop.setVisible(false);
						endTime = setTime(btnEnd.getText());
					}
				});
				Calendar c = Calendar.getInstance();
				c.clear();
				String[] s = btnEnd.getText().split("-");
				int[] a = new int[3];
				for (int i = 0; i < a.length; i++) {
					a[i] = Integer.parseInt(s[i]);
				}
				c.set(a[0], a[1] - 1, a[2]);
				calendarPop.updateDate(c);
				calendarPop.show(btnEnd, e.getX(), e.getY());
			}
		});
		
		btnStart = new JButton("起始日期");
		setDateText(btnStart, start);
		startTime = setTime(btnStart.getText());
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalendarPop calendarPop  = new CalendarPop();
				calendarPop.addDayClickListener(new DayClickListener() {
					
					@Override
					public void dayClicked(DayPanel day, MouseEvent e) {
						setDateText(btnStart,day.getCalendar());
						calendarPop.setVisible(false);
						startTime = setTime(btnStart.getText());
					}
				});
				Calendar c = Calendar.getInstance();
				c.clear();
				String[] s = btnStart.getText().split("-");
				int[] a = new int[3];
				for (int i = 0; i < a.length; i++) {
					a[i] = Integer.parseInt(s[i]);
				}
				c.set(a[0], a[1] - 1, a[2]);
				calendarPop.updateDate(c);
				calendarPop.show(btnStart, e.getX(), e.getY());
			}
		});
		btnStart.setBounds(530, 21, 113, 27);
		add(btnStart);
		btnEnd.setBounds(657, 21, 113, 27);
		add(btnEnd);
		
		JButton btnSearch = new JButton("查询");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchInfo();
			}
		});
		btnSearch.setBounds(794, 21, 113, 27);
		add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 68, 934, 349);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u65E5\u671F", "\u5F00\u76D8\u4EF7", "\u6536\u76D8\u4EF7", "\u6700\u9AD8\u4EF7", "\u6700\u4F4E\u4EF7", "\u540E\u590D\u6743\u4EF7", "\u6210\u4EA4\u91CF", "\u8F6C\u624B\u7387", "\u5E02\u76C8\u7387", "\u5E02\u51C0\u7387"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(9).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("股市有风险，投资需谨慎");
		label.setBounds(14, 437, 192, 18);
		add(label);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "search");
			}
		});
		button.setBounds(835, 430, 113, 27);
		add(button);

	}
	
	private void setDateText(JButton btn, Calendar c){
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		btn.setText(year + "-" + month + "-" + date);
	}
	
	private void displayByVO() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// 清除原有行

		for (ConcreteInfoVO vo : list) {
			String[] row = new String[10];
			row[0] = vo.getDate();
			row[1] = vo.getOpen();
			row[2] = vo.getClose();
			row[3] = vo.getHighest();
			row[4] = vo.getLowest();
			row[5] = vo.getAdj_price();
			row[6] = vo.getVolume();
			row[7] = vo.getTurnover();
			row[8] = vo.getPe();
			row[9] = vo.getPb();

			tableModel.addRow(row);
		}

		if (table.getRowCount() < 11) {
			int n = table.getRowCount();
			for (int i = 0; i < 11 - n; i++) {
				tableModel.addRow(new String[10]);
			}
		}

	}
	
	private String setTime(String s){
		String result;
		String[] temp = s.split("-");
		if(temp[1].length() == 1)
			result = temp[0].concat("-0" + temp[1]);
		else
			result = temp[0].concat("-" + temp[1]);
		if(temp[2].length() == 1)
			result = result.concat("-0" + temp[2]);
		else
			result = result.concat("-" + temp[2]);
		return result;
	};
	
	private void searchInfo() {
		list = controller.getInfoByRange(startTime, endTime, code);
		if(list.size()==0){
			TipDialog tipDialog = new TipDialog("暂无数据！");
			tipDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			tipDialog.setVisible(true);
		}
		displayByVO();
	}
	
	public void setStock(String code, String name){
		this.code = code;
		this.name = name;
		lblCode.setText("代号：" + code);
		lblName.setText("名称：" + name);
		searchInfo();
	};
}
