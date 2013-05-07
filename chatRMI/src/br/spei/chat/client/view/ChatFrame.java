package br.spei.chat.client.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.spei.chat.client.action.EnviarMensagemAction;
import br.spei.chat.client.model.Conversa;
import br.spei.chat.client.model.ListarUsuario;

public class ChatFrame extends JInternalFrame {
    private static final long serialVersionUID = -8434242822734503062L;

    private JLabel labelMensagem;

    private JButton buttonSend;
    private JCheckBox reservada;

    private ListarUsuario listaUsuarios;
    private Conversa conversa;

    private JTextField jTextSendMessage;

    public ChatFrame(String nickname, List<String> usuarios) {
	conversa = Conversa.getInstance();
	listaUsuarios = ListarUsuario.getInstance();
	listaUsuarios.setListaUsuarios(usuarios);
	initComponents(nickname);
	setVisible(true);
	setResizable(false);
    }

    private void initComponents(String nickname) {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle(nickname);
	setResizable(false);
	getContentPane().setLayout(null);
	setSize(796, 547);
	setLabelMensagem();
	setTextareaMensagem();
	setButtonEnviar();
	setCheckReservada();
	getContentPane().add(conversa.getLabelConversa());
	getContentPane().add(conversa.getPanelConversa());
	getContentPane().add(listaUsuarios.getLabelUsuarios());
	getContentPane().add(listaUsuarios.getPanelUsuarios());
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

    public ListarUsuario getListausuarios() {
	return listaUsuarios;
    }

    public void limparMensagemEnviada() {
	jTextSendMessage.setText("");
    }
}
