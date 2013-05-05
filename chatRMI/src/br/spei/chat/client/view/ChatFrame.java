package br.spei.chat.client.view;

import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatFrame extends JInternalFrame {
    private static final long serialVersionUID = -8434242822734503062L;

    private JButton jButtonSend;
    private JLabel jLabelUserList;
    private JList jListUser;
    private JScrollPane jScrollPaneListUser;
    private JScrollPane jScrollPaneMessage;
    private JTextArea jTextAreaMessage;
    private JTextField jTextSendMessage;

    private Socket socket = null;
    private DefaultListModel listClient;

    private String message;

    public ChatFrame(String nickname) {
	initComponents(nickname);
	setVisible(true);
    }

    private void initComponents(String nickname) {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle(nickname);
	setResizable(false);
	getContentPane().setLayout(null);
	setSize(796, 547);

	this.listClient = new DefaultListModel();
	this.jScrollPaneMessage = new JScrollPane();
	this.jTextAreaMessage = new JTextArea();
	this.jScrollPaneListUser = new JScrollPane();
	this.jListUser = new JList(listClient);
	this.jTextSendMessage = new JTextField();
	this.jButtonSend = new JButton();
	this.jLabelUserList = new JLabel();

	this.jTextAreaMessage.setColumns(50);
	this.jTextAreaMessage.setEditable(false);
	this.jTextAreaMessage.setRows(5);
	this.jTextAreaMessage.setAutoscrolls(false);
	this.jScrollPaneMessage.setViewportView(this.jTextAreaMessage);

	getContentPane().add(this.jScrollPaneMessage);
	this.jScrollPaneMessage.setBounds(10, 10, 570, 240);

	this.jScrollPaneListUser.setViewportView(this.jListUser);

	getContentPane().add(this.jScrollPaneListUser);
	this.jScrollPaneListUser.setBounds(590, 50, 190, 200);

	// this.jTextSendMessage.addActionListener(this);

	getContentPane().add(this.jTextSendMessage);
	this.jTextSendMessage.setBounds(10, 260, 570, 30);

	this.jButtonSend.setText("Enviar");
	// this.jButtonSend.addActionListener(this);

	getContentPane().add(this.jButtonSend);
	this.jButtonSend.setBounds(580, 260, 180, 30);

	this.jLabelUserList.setText("Usuários");
	getContentPane().add(this.jLabelUserList);
	this.jLabelUserList.setBounds(590, 5, 200, 17);

	this.jButtonSend.setEnabled(false);
    }
}
