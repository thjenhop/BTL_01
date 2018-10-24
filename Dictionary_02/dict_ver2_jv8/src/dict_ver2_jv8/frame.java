package dict_ver2_jv8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import javax.swing.JCheckBox;

import com.darkprograms.*;
import com.darkprograms.speech.synthesiser.SynthesiserV2;
import com.darkprograms.speech.translator.GoogleTranslate;

import javazoom.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

 

public class frame {

	private JFrame frmFrame; 
	private JLabel lblDictionanry; 
	JPanel searchPanel;
	JLabel lblNewLabel;
	JPanel viewPanel;
	JTextArea viewBox;
	JComboBox comboBox;
	JButton btnNewButton;
	JPanel back_panel;
	JPanel functionPanel;
	JButton insertBtn; // line 394
	JButton deleteBtn; // line 472
	JButton addBtn; // line 316
	// search line 188
	//  button search 245
	// pronunciation 273
	protected DictionaryCommandLine dictcmd = new DictionaryCommandLine();
	private JCheckBox chckbxUseGoogleTranslate;
	/**
	 * rub method
	 * @throws IOException 
	 */
	public void run () {
		frmFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public frame() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		dictcmd.manager.insertFromFile();// nhap du lieu tu file khi khoi dong 
		
		frmFrame = new JFrame();
		frmFrame.setResizable(false);
		frmFrame.setTitle("main");
		frmFrame.setBounds(100, 100, 627, 595);
		frmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFrame.getContentPane().setLayout(null);
		frmFrame.addWindowListener( new WindowListener() { // truoc khi dong thi ghi lai toan bo tu vao file
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {			
					dictcmd.manager.exportToFile();
					//System.out.println("close");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
		});
		
		lblDictionanry = new JLabel("Dictionary");
		lblDictionanry.setForeground(Color.BLACK);
		lblDictionanry.setHorizontalAlignment(SwingConstants.CENTER);
		lblDictionanry.setFont(new Font("Arial", Font.BOLD, 30));
		lblDictionanry.setBounds(10, 10, 593, 45);
		frmFrame.getContentPane().add(lblDictionanry);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(10, 60, 259, 384);
		frmFrame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Search Box");
		lblNewLabel.setBounds(43, 10, 164, 29);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchPanel.add(lblNewLabel);
		
		
		viewPanel = new JPanel();
		viewPanel.setBounds(289, 60, 324, 384);
		frmFrame.getContentPane().add(viewPanel);
		viewPanel.setLayout(null);
		
		viewBox = new JTextArea();
		viewBox.setBorder(new EmptyBorder(1, 3, 3, 2));
		viewBox.setBackground(Color.WHITE);
		viewBox.setFont(new Font("Arial", Font.PLAIN, 18));
		viewBox.setBounds(10, 10, 304, 372);
		viewBox.setRows(10);
		viewBox.setLineWrap(true);
		viewBox.setWrapStyleWord(true);
		viewBox.setAutoscrolls(true);
		viewPanel.add(viewBox);
		
		// tao scroll bar cho viewBox
		JScrollPane scrollPane = new JScrollPane(viewBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 10, 304, 336);
		viewPanel.add(scrollPane);		
		
		chckbxUseGoogleTranslate = new JCheckBox("Translate Text");
		chckbxUseGoogleTranslate.setFont(new Font("Arial", Font.PLAIN, 18));
		chckbxUseGoogleTranslate.setBounds(60, 346, 153, 29);
		searchPanel.add(chckbxUseGoogleTranslate);
		
		comboBox = new JComboBox();
		comboBox.setSize(new Dimension(100, 100));
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox.setBounds(10, 49, 239, 39);
		comboBox.setEditable(true);
		comboBox.setUI(new BasicComboBoxUI() { // xoa dau mui ten trong box
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					@Override
					public int getWidth() {
						return 0;
					}
				};
			}
		});
		comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { // su kien phim cua search box
			String x;
			@Override
			public void keyReleased(KeyEvent e) {
				x= comboBox.getEditor().getItem().toString().toLowerCase();
				if( chckbxUseGoogleTranslate.isSelected() ) {
					 //su dung google
					if(!x.equals("") ) {
						
						try {
							String explain = GoogleTranslate.translate("vi", x);
							viewBox.setText("Nghĩa : \n"+explain);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else {
						viewBox.setText("");
					}
				}
				else {
					// su dung du lieu tu file text
					if( !x.equals("")) {
						if(e.getKeyCode() == e.VK_ENTER  ) {
							Word word_target = dictcmd.manager.dictionaryLookup(x);
							if(word_target == null) viewBox.setText("Từ không tồn tại");
							else {
								viewBox.setText("Từ : "+ word_target.getWord_target()+ "   /" + word_target.getWord_pronoun() +"/" );
								viewBox.setText(viewBox.getText() + "\nNghĩa\n" + word_target.getWord_explain() );
								viewBox.setCaretPosition(0); // dua con tro len dau neu trang qua dai
							}
						}
						else {
							if(e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN) {// chinh cursor chon tu
								comboBox.setCursor(new Cursor(0));
							}
							else {
								ArrayList<Word> relativeWord = new ArrayList<Word>();
								relativeWord = dictcmd.dictionarySearch(x);
								if( relativeWord.size() > 0) {
									comboBox.removeAllItems();
									comboBox.addItem(x);
									for( int i = 0; i< relativeWord.size(); i++) {
										comboBox.addItem(relativeWord.get(i).getWord_target());
									}
								comboBox.hidePopup();
								comboBox.showPopup();
								}
							}
						}	
					}
					else {
						comboBox.removeAllItems();
						comboBox.hidePopup();
					}
				}
			}		
		});
		searchPanel.add(comboBox);
		
		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// goi searcher va lookup bang nut search
				String x = comboBox.getEditor().getItem().toString();
				if( !x.equals("") ) { // o tim kiem khac rong
					if( dictcmd.manager.containTarget(x) ) { // neu tu co ton tai thi in ra nghia
						Word word_target = dictcmd.manager.dictionaryLookup(x);
						viewBox.setText("Từ : "+ word_target.getWord_target()+ "   /" + word_target.getWord_pronoun() +"/" );
						viewBox.setText(viewBox.getText() + "\nNghĩa\n" + word_target.getWord_explain() );
					}
					else {
						ArrayList<Word> relativeWord = new ArrayList<Word>();
						relativeWord = dictcmd.dictionarySearch(x);
						comboBox.removeAllItems();
						comboBox.addItem("");
						for( int i = 0; i< relativeWord.size(); i++) {
							comboBox.addItem(relativeWord.get(i).getWord_target());
						}
						comboBox.showPopup();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(60, 95, 135, 29);
		searchPanel.add(btnNewButton);
		
		JButton pronunBtn = new JButton("Pronunciate");
		pronunBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// phat am
				String target = comboBox.getEditor().getItem().toString().toLowerCase();
				target = target.replace("-", " ");
				SynthesiserV2 syn = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
				syn.setLanguage("en");
				AdvancedPlayer player = null;
				try {
					player = new AdvancedPlayer(syn.getMP3Data(target));
				} catch (JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					player.play();
				} catch (JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}									
			}
		});
		pronunBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		pronunBtn.setBounds(10, 346, 304, 38);
		viewPanel.add(pronunBtn);
		
		
		back_panel = new JPanel();
		back_panel.setBounds(10, 453, 593, 95);
		frmFrame.getContentPane().add(back_panel);
		back_panel.setLayout(null);
		
		functionPanel = new JPanel();
		functionPanel.setBounds(10, 22, 573, 52);
		back_panel.add(functionPanel);
		functionPanel.setLayout(new GridLayout(0, 3, 20, 0));
		
		addBtn = new JButton("ADD");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // goi cua so them tu
				JFrame frmAddframe = new JFrame();
				frmAddframe.setTitle("add_word");
				frmAddframe.setBounds(100, 100, 557, 274);
				frmAddframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmAddframe.getContentPane().setLayout(null);
				
				JLabel Tu = new JLabel("Word");
				Tu.setHorizontalAlignment(SwingConstants.LEFT);
				Tu.setFont(new Font("Arial", Font.PLAIN, 18));
				Tu.setBounds(30, 36, 95, 44);
				frmAddframe.getContentPane().add(Tu);
				
				JLabel Nghia = new JLabel("Explain");
				Nghia.setHorizontalAlignment(SwingConstants.LEFT);
				Nghia.setFont(new Font("Arial", Font.PLAIN, 18));
				Nghia.setBounds(30, 173, 95, 44);
				frmAddframe.getContentPane().add(Nghia);
				
				JTextField target_textField = new JTextField();
				target_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				target_textField.setBounds(135, 43, 235, 37);
				frmAddframe.getContentPane().add(target_textField);
				target_textField.setColumns(10);
				
				JTextField explain_textField = new JTextField();
				explain_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				explain_textField.setBounds(135, 177, 235, 37);
				frmAddframe.getContentPane().add(explain_textField);
				explain_textField.setColumns(10);
						
				JLabel statusLabel = new JLabel("");
				statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
				statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
				statusLabel.setBounds(0, 10, 533, 16);
				frmAddframe.getContentPane().add(statusLabel);
								
				JTextField pronoun_textField = new JTextField();
				pronoun_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				pronoun_textField.setColumns(10);
				pronoun_textField.setBounds(135, 106, 235, 37);
				frmAddframe.getContentPane().add(pronoun_textField);
				
				JLabel phienAm = new JLabel("Spelling");
				phienAm.setHorizontalAlignment(SwingConstants.LEFT);
				phienAm.setFont(new Font("Arial", Font.PLAIN, 18));
				phienAm.setBounds(30, 106, 95, 44);
				frmAddframe.getContentPane().add(phienAm);
				
				JButton enterBtn = new JButton("Enter");
				enterBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String target = target_textField.getText();
						String pronoun = pronoun_textField.getText();
						String explain = explain_textField.getText();
						if( target.equals("") || pronoun.equals("") || explain.equals("")) { 
							// 1 trong 3 o con trong thi k cho add tu
							String status = "Do not leave empty";
							statusLabel.setText(status);
						}
						else {
							String status = dictcmd.manager.addData(target, pronoun, explain);
							statusLabel.setText(status);
						}
					}
				});
				enterBtn.setFont(new Font("Arial", Font.PLAIN, 18));
				enterBtn.setBounds(407, 102, 95, 44);
				frmAddframe.getContentPane().add(enterBtn);
				frmAddframe.setVisible(true);
			}
		});
		addBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		functionPanel.add(addBtn);
		
		insertBtn = new JButton("INSERT");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // goi cua so sua tu
				JFrame frmInsertframe = new JFrame();
				frmInsertframe.setTitle("insert_word");
				frmInsertframe.setBounds(100, 100, 557, 288);
				frmInsertframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmInsertframe.getContentPane().setLayout(null);
				
				JLabel Tu = new JLabel("Word");
				Tu.setFont(new Font("Arial", Font.PLAIN, 18));
				Tu.setHorizontalAlignment(SwingConstants.LEFT);
				Tu.setBounds(30, 36, 95, 44);
				frmInsertframe.getContentPane().add(Tu);
				
				JLabel phienAm = new JLabel("Spelling");
				phienAm.setFont(new Font("Arial", Font.PLAIN, 18));
				phienAm.setBounds(30, 107, 95, 44);
				frmInsertframe.getContentPane().add(phienAm);
				
				JTextField target_textField = new JTextField();
				target_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				target_textField.setBounds(135, 43, 235, 37);
				frmInsertframe.getContentPane().add(target_textField);
				target_textField.setColumns(10);
				
				JTextField pronoun_textField = new JTextField();
				pronoun_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				pronoun_textField.setBounds(135, 106, 235, 37);
				frmInsertframe.getContentPane().add(pronoun_textField);
				pronoun_textField.setColumns(10);
				

				
				JLabel statusLabel = new JLabel("");
				statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
				statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
				statusLabel.setBounds(0, 10, 533, 16);
				frmInsertframe.getContentPane().add(statusLabel);
				
				JLabel Nghia = new JLabel("Explain");
				Nghia.setFont(new Font("Arial", Font.PLAIN, 18));
				Nghia.setBounds(30, 175, 95, 44);
				frmInsertframe.getContentPane().add(Nghia);
				
				JTextField explain_textField = new JTextField();
				explain_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				explain_textField.setColumns(10);
				explain_textField.setBounds(135, 177, 235, 37);
				frmInsertframe.getContentPane().add(explain_textField);
				
				
				JButton enterBtn = new JButton("Enter");
				enterBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						String target = target_textField.getText();
						String pronoun = pronoun_textField.getText();
						String explain = explain_textField.getText();
						if( target.equals("") || pronoun.equals("") || explain.equals("")) { 
							// 1 trong 3 o con trong thi k cho add tu
							String status = "Do not leave empty";
							statusLabel.setText(status);
						}
						else {
							String status = dictcmd.manager.insertData(target, pronoun, explain);
							statusLabel.setText(status);
						}
					}
				});
				enterBtn.setFont(new Font("Arial", Font.PLAIN, 18));
				enterBtn.setBounds(407, 107, 95, 44);
				frmInsertframe.getContentPane().add(enterBtn);
				frmInsertframe.setVisible(true);
			}
		});
		insertBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		functionPanel.add(insertBtn);
		
		
		
		deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // goi cua so xoa tu 
				JFrame frmDeleteframe = new JFrame();
				frmDeleteframe.setTitle("delete_word");
				frmDeleteframe.setBounds(100, 100, 557, 163);
				frmDeleteframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmDeleteframe.getContentPane().setLayout(null);
				
				JLabel statusLabel = new JLabel("");
				statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
				statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
				statusLabel.setBounds(0, 10, 533, 16);
				frmDeleteframe.getContentPane().add(statusLabel);
				
				JLabel Tu = new JLabel("Word");
				Tu.setFont(new Font("Arial", Font.PLAIN, 18));
				Tu.setHorizontalAlignment(SwingConstants.LEFT);
				Tu.setBounds(25, 52, 95, 44);
				frmDeleteframe.getContentPane().add(Tu);
				
				JTextField target_textField = new JTextField();
				target_textField.setFont(new Font("Arial", Font.PLAIN, 18));
				target_textField.setBounds(135, 56, 235, 37);
				frmDeleteframe.getContentPane().add(target_textField);
				target_textField.setColumns(10);
				
				JButton btnNewButton = new JButton("Enter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String target = target_textField.getText();
						if( target.equals(""))
						{
							String status = "Do not leave empty";
							statusLabel.setText(status);
						}
						else {
							String status = dictcmd.manager.deleteData(target);
							statusLabel.setText(status);
						}			
					}
				});
				btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
				btnNewButton.setBounds(413, 52, 95, 44);
				frmDeleteframe.getContentPane().add(btnNewButton);
				frmDeleteframe.setVisible(true);
			}
		});
		deleteBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		functionPanel.add(deleteBtn);
}
}
