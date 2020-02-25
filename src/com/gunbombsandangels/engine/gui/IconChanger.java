package com.gunbombsandangels.engine.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class IconChanger extends JFrame{
	
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private Color borderColor = Color.decode("#098eaf");
	private Color backgroundColor = Color.decode("#80c2da");
	
	IconChanger(JFrame frame, final GameFrame gameFrame){
		super();
		this.setUndecorated(true);
		mainPanel = new JPanel(new GridLayout(3,1));
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, borderColor));
		buttonPanel = new JPanel();
		mainPanel.setBackground(backgroundColor);
		buttonPanel.setBackground(backgroundColor);
		
		DefaultComboBoxModel<String> lifeIconSelect = new DefaultComboBoxModel<String>();
		
		lifeIconSelect.addElement("Original");
		lifeIconSelect.addElement("Heart");
		lifeIconSelect.addElement("Face");
		lifeIconSelect.addElement("Star");
		
		JComboBox<String> comboBox = new JComboBox<String>(lifeIconSelect);
		
		mainPanel.add(new JLabel("Life Icon: "));
		mainPanel.add(comboBox);
		
		//comboBox.getSelectedItem()
		okButton = new JButton("Okay");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameFrame.setLifeIcon(comboBox.getSelectedIndex());
				IconChanger.this.setVisible(false);
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancel");
				IconChanger.this.setVisible(false);
			}
		});
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel);
		this.add(mainPanel);
		
		setLocationRelativeTo(frame);
		pack();
		setVisible(false);
	}
	
	public void promptUser() {
		setVisible(true);
		repaint();
	}
}
