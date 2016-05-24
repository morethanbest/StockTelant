package ui;

import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.FlowLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.KeyAdapter;  
import java.awt.event.KeyEvent;  
import java.util.ArrayList;  
import java.util.Locale;  
  

import javax.swing.DefaultComboBoxModel;  
import javax.swing.JComboBox;  
import javax.swing.JFrame;  
import javax.swing.JTextField;  
import javax.swing.UIManager;  
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;  
import javax.swing.event.DocumentListener;

import logic.Buffer;
import logic.SimpleInfoLogic;  
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		MainFrame frame=new MainFrame();
		frame.start();
		Buffer buffer=new Buffer();
		Thread thread=new Thread(buffer);
		thread.start();
	}

}
