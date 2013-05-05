package br.spei.chat.client.view;

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

    private JLabel labelConversa;
    private JLabel labelMensagem;
    private JLabel labelUsarios;

    private JButton buttonSend;
    private JScrollPane panelUsuarios;
    private JScrollPane panelConversa;

    private JList listaUsuarios;
    private DefaultListModel listClient;

    private JTextArea textareaConversa;
    private JTextField jTextSendMessage;

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
	setLabelConversa();
	setLabelMensagem();
	setLabelUsuarios();
	setButtonEnviar();
	setPanelConversa();
	setPanelUsuarios();
	setListClient();
	setListaUsuarios();
	setTextareaConversa();
	setTextareaMensagem();
    }

    private void setLabelUsuarios() {
	this.labelUsarios = new JLabel("Usuários");
	this.labelUsarios.setBounds(590, 5, 200, 17);
	getContentPane().add(this.labelUsarios);
    }

    private void setLabelMensagem() {
	this.labelMensagem = new JLabel("Mensagem");
	this.labelMensagem.setBounds(10, 420, 200, 17);
	getContentPane().add(this.labelMensagem);
    }

    private void setLabelConversa() {
	this.labelConversa = new JLabel("Conversa");
	this.labelConversa.setBounds(10, 5, 200, 17);
	getContentPane().add(this.labelConversa);
    }

    private void setButtonEnviar() {
	this.buttonSend = new JButton("Enviar");
	this.buttonSend.setBounds(590, 440, 190, 70);
	// this.buttonSend.addActionListener(this);
	getContentPane().add(this.buttonSend);
    }

    private void setPanelConversa() {
	this.panelConversa = new JScrollPane();
	this.panelConversa.setViewportView(this.textareaConversa);
	getContentPane().add(this.panelConversa);
	this.panelConversa.setBounds(10, 30, 570, 380);
    }

    private void setPanelUsuarios() {
	this.panelUsuarios = new JScrollPane();
	this.panelUsuarios.setViewportView(this.listaUsuarios);
	this.panelUsuarios.setBounds(590, 30, 190, 380);
	getContentPane().add(this.panelUsuarios);
    }

    private void setListClient() {
	this.listClient = new DefaultListModel();
    }

    private void setListaUsuarios() {
	this.listaUsuarios = new JList(this.listClient);
    }

    private void setTextareaConversa() {
	this.textareaConversa = new JTextArea();
	this.textareaConversa.setColumns(50);
	this.textareaConversa.setEditable(false);
	this.textareaConversa.setRows(5);
	this.textareaConversa.setAutoscrolls(false);
    }

    private void setTextareaMensagem() {
	this.jTextSendMessage = new JTextField();
	this.jTextSendMessage.setBounds(10, 440, 570, 70);
	getContentPane().add(this.jTextSendMessage);
    }

}
