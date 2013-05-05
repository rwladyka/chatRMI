package br.spei.chat.client.view;

import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatFrame extends JFrame {
    private JButton jButtonSend;
    private JLabel jLabelUserList;
    private JList jListUser;
    private JScrollPane jScrollPaneListUser;
    private JScrollPane jScrollPaneMessage;
    private JTextArea jTextAreaMessage;
    private JTextField jTextSendMessage;
    private JTextField jTextUserName;

    private Socket socket = null;
    private DefaultListModel listClient;

    private String message;

    public ChatFrame() {
	initComponents();
    }

    private void initComponents() {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle("Chat");
	setResizable(false);
	getContentPane().setLayout(null);

	this.listClient = new DefaultListModel();
	this.jScrollPaneMessage = new JScrollPane();
	this.jTextAreaMessage = new JTextArea();
	this.jTextUserName = new JTextField();
	this.jScrollPaneListUser = new JScrollPane();
	this.jListUser = new JList(listClient);
	this.jTextSendMessage = new JTextField();
	this.jButtonSend = new JButton();
	this.jLabelUserList = new JLabel();

	this.jTextAreaMessage.setColumns(20);
	this.jTextAreaMessage.setEditable(false);
	this.jTextAreaMessage.setRows(5);
	this.jTextAreaMessage.setAutoscrolls(false);
	this.jScrollPaneMessage.setViewportView(this.jTextAreaMessage);

	getContentPane().add(this.jScrollPaneMessage);
	this.jScrollPaneMessage.setBounds(10, 10, 340, 240);

	// this.jTextUserName.addActionListener(this);
	getContentPane().add(jTextUserName);
	this.jTextUserName.setBounds(360, 10, 100, 20);

	this.jListUser.setToolTipText("Lista de Usuários");
	this.jScrollPaneListUser.setViewportView(this.jListUser);

	getContentPane().add(this.jScrollPaneListUser);
	this.jScrollPaneListUser.setBounds(360, 50, 190, 200);

	// this.jTextSendMessage.addActionListener(this);

	getContentPane().add(this.jTextSendMessage);
	this.jTextSendMessage.setBounds(10, 260, 340, 30);

	this.jButtonSend.setFont(new java.awt.Font("Times New Roman", 0, 11));
	this.jButtonSend.setText("Enviar");
	// this.jButtonSend.addActionListener(this);

	getContentPane().add(this.jButtonSend);
	this.jButtonSend.setBounds(360, 261, 70, 30);

	this.jLabelUserList.setFont(new java.awt.Font("Arial Black", 0, 11));
	this.jLabelUserList.setText("Lista de Usuários");
	getContentPane().add(this.jLabelUserList);
	this.jLabelUserList.setBounds(360, 30, 55, 17);

	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
		.getScreenSize();
	setBounds((screenSize.width - 570) / 2, (screenSize.height - 330) / 2,
		570, 330);

	this.jButtonSend.setEnabled(false);
    }
}
