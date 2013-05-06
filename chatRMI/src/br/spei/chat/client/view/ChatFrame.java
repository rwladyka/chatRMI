package br.spei.chat.client.view;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.spei.chat.client.action.EnviarMensagemAction;
import br.spei.chat.client.model.Conversa;

public class ChatFrame extends JInternalFrame {
    private static final long serialVersionUID = -8434242822734503062L;

    private JLabel labelMensagem;
    private JLabel labelUsarios;

    private JButton buttonSend;
    private JScrollPane panelUsuarios;
    private JCheckBox reservada;

    private JList<String> listaUsuarios;
    private DefaultListModel<String> listClient;

    private Conversa conversa;

    private JTextField jTextSendMessage;

    public ChatFrame(String nickname, List<String> usuarios) {
	conversa = Conversa.getInstance();
	initComponents(nickname);
	setListaUsuarios(usuarios);
	setVisible(true);
    }

    private void initComponents(String nickname) {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle(nickname);
	setResizable(false);
	getContentPane().setLayout(null);
	setSize(796, 547);
	setLabelMensagem();
	setLabelUsuarios();
	setTextareaMensagem();
	setListClient();
	setListaUsuarios();
	setButtonEnviar();
	setPanelUsuarios();
	setCheckReservada();
	getContentPane().add(conversa.getLabelConversa());
	getContentPane().add(conversa.getPanelConversa());
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

    private void setButtonEnviar() {
	this.buttonSend = new JButton("Enviar");
	this.buttonSend.setBounds(590, 440, 190, 70);
	this.buttonSend.addMouseListener(new EnviarMensagemAction(this));
	getContentPane().add(this.buttonSend);
    }

    private void setPanelUsuarios() {
	this.panelUsuarios = new JScrollPane();
	this.panelUsuarios.setViewportView(this.listaUsuarios);
	this.panelUsuarios.setBounds(590, 30, 190, 380);
	getContentPane().add(this.panelUsuarios);
    }

    private void setListClient() {
	this.listClient = new DefaultListModel<String>();
    }

    private void setListaUsuarios() {
	this.listaUsuarios = new JList<String>(this.listClient);
    }

    private void setTextareaMensagem() {
	this.jTextSendMessage = new JTextField();
	this.jTextSendMessage.setBounds(10, 440, 570, 70);
	getContentPane().add(this.jTextSendMessage);
    }

    private void setCheckReservada() {
	this.reservada = new JCheckBox("Reservada");
	this.reservada.setBounds(100, 420, 200, 17);
	getContentPane().add(this.reservada);
    }

    public void setListaUsuarios(List<String> usuarios) {
	for (String usuario : usuarios) {
	    this.listClient.addElement(usuario);
	}
	this.listaUsuarios = new JList<String>(listClient);
    }

    public String getMensagem() {
	String mensagem = "";
	if (this.jTextSendMessage != null) {
	    mensagem = this.jTextSendMessage.getText();
	}
	return mensagem;
    }

    public boolean isReservada() {
	if (reservada != null) {
	    return reservada.isSelected();
	}
	return false;
    }

    public String getDestinatario() {
	if (listaUsuarios != null) {
	    return listaUsuarios.getSelectedValue();
	}
	return "Todos";
    }

    public void limparMensagemEnviada() {
	jTextSendMessage.setText("");
    }
}
