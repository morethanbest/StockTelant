package ui.work1;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.APIController;
import vo.Exchange;
import vo.OriginInfoVO;

public class MainPanel extends JPanel implements ActionListener {
	private JTextField txtInput;
	private SearchPanel searchPanel;
	private CardLayout card;
	private JToggleButton btnSh;
	private JToggleButton btnSz;
	private StockInfoPanel infoPanel;
	private JPanel panel;
	private JButton btnSearch;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(null);
		
		txtInput = new JTextField();
		txtInput.setBounds(294, 10, 370, 37);
		txtInput.setColumns(30);
		add(txtInput);

		btnSh = new JToggleButton("上海");
		btnSh.setBounds(53, 17, 77, 30);
		btnSh.setFocusPainted(false);
		btnSh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnSz.isSelected())
					btnSz.setSelected(false);
				else
					btnSz.setSelected(true);
				if (btnSh.isSelected()) {
					searchPanel.exchangeturning(Exchange.sh);
				} else if (btnSz.isSelected()) {
					searchPanel.exchangeturning(Exchange.sz);
				}
			}
		});
		btnSh.setSelected(true);
		add(btnSh);

		btnSz = new JToggleButton("深圳");
		btnSz.setFocusPainted(false);
		btnSz.setBounds(160, 17, 82, 30);
		btnSz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnSh.isSelected())
					btnSh.setSelected(false);
				else
					btnSh.setSelected(true);
				if (btnSh.isSelected()) {
					searchPanel.exchangeturning(Exchange.sh);
				} else if (btnSz.isSelected()) {
					searchPanel.exchangeturning(Exchange.sz);
				}
			}
		});
		add(btnSz);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtInput.getText().equals("")) {

				} else if (txtInput.getText().charAt(0) == 's') {
					String[] splits = txtInput.getText().split(" ");
					if (splits.length == 1) {
						infoPanel.setStock(splits[0], "");
					} else {
						infoPanel.setStock(splits[0], splits[1]);
					}
					card.show(panel, "info");
				} else {
					infoPanel.setStock("", txtInput.getText());
					card.show(panel, "info");
				}

			}
		});
		btnSearch.setBounds(703, 13, 98, 30);
		add(btnSearch);

		panel = new JPanel();
		panel.setBounds(23, 69, 963, 500);
		add(panel);
		card = new CardLayout();
		panel.setLayout(card);
		searchPanel = new SearchPanel(this);
		panel.add(searchPanel, "search");
		infoPanel = new StockInfoPanel(card, panel);
		panel.add(infoPanel, "info");
		
		setupAutoComplete(this, txtInput);
	}

	// 当文本框输入内容时，判断下拉框中是否有符合要求的列表
	private static boolean isAdjusting(JComboBox cbInput) {
		if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
			return (Boolean) cbInput.getClientProperty("is_adjusting");
		}
		return false;
	}

	// 如果下拉框中有符合要求的列表，就马上主动弹出下拉，否则就让下拉消失
	private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
		cbInput.putClientProperty("is_adjusting", adjusting);
	}

	// 设置自动补全
	public static void setupAutoComplete(MainPanel mainPanel,
			final JTextField txtInput) {
		// 将JComboBox高度设为0，塞入JTextField
		final DefaultComboBoxModel model = new DefaultComboBoxModel();
		final JComboBox cbInput = new JComboBox(model) {
			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}

		};
		
//        APIController controller = new APIController();
//        List<String> items = new ArrayList<String>();
//		setAdjusting(cbInput, false);
//		for (String item : items) {
//			model.addElement(item);
//		}
		cbInput.setSelectedItem(null);
		// 增加监听，开始补全
		cbInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (!isAdjusting(cbInput)) {
					if (cbInput.getSelectedItem() != null) {
						txtInput.setText(cbInput.getSelectedItem().toString());
					}
				}
			}
		});
		// 设置快捷键：当输入ESC，主动关掉下拉列表；当输入回车或空格，直接把第一项符合要求的字符串输入文本框
		txtInput.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				setAdjusting(cbInput, true);
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (cbInput.isPopupVisible()) {
						e.setKeyCode(KeyEvent.VK_ENTER);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					e.setSource(cbInput);
					cbInput.dispatchEvent(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if(cbInput.getSelectedItem()!=null){
							txtInput.setText(cbInput.getSelectedItem().toString());
							cbInput.setPopupVisible(false);
						}
						
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cbInput.setPopupVisible(false);
				}
				setAdjusting(cbInput, false);
			}
		});
		// 监控文本框输入，以“不区分大小写”、“和输入字符串开头相同的项”的规则进行过滤
		txtInput.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			public void changedUpdate(DocumentEvent e) {
				updateList();
			}

			private void updateList() {
				setAdjusting(cbInput, true);
				model.removeAllElements();
				String input = txtInput.getText();
				
				if (!input.isEmpty()) {

					APIController controller = new APIController();
					List<String> items = controller.getCodeName(input, Exchange.both);
					for(String item:items){
						model.addElement(item);
					}
					
				}
				cbInput.setPopupVisible(model.getSize() > 0);
				setAdjusting(cbInput, false);
			}
		});
		mainPanel.add(cbInput);
		cbInput.setBounds(txtInput.getX(),
				txtInput.getY() + txtInput.getHeight() - 2,
				txtInput.getWidth(), 0);
	}

	public void skipToInfo(OriginInfoVO vo) {
		infoPanel.setStock(vo.getCode(), vo.getName());
		card.show(panel, "info");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = txtInput.getText();
		infoPanel.setStock(code, "");
		card.next(panel);
		
		
	}
}
