package br.spei.chat.client.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.spei.chat.client.action.EnviarMensagemAction;
import br.spei.chat.client.action.EnviarMsgEnterAction;
import br.spei.chat.client.model.Conversa;
import br.spei.chat.client.model.ListarUsuario;
import br.spei.chat.client.util.ListUtil;

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
	listaUsuarios.setListClient(ListUtil.toArray(usuarios));
	listaUsuarios.addUsersToPanel();
	initComponents(nickname);
	setVisible(true);
	setResizable(false);
    }

    private void initComponents(String nickname) {
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
	labelMensagem = new JLabel("Mensagem");
	labelMensagem.setBounds(10, 420, 200, 17);
	getContentPane().add(labelMensagem);
    }

    private void setButtonEnviar() {
	buttonSend = new JButton("Enviar");
	buttonSend.setBounds(590, 440, 190, 70);
	buttonSend.addMouseListener(new EnviarMensagemAction(this));
	getContentPane().add(buttonSend);
    }

    private void setTextareaMensagem() {
	jTextSendMessage = new JTextField();
	jTextSendMessage.setBounds(10, 440, 570, 70);
	jTextSendMessage.addKeyListener(new EnviarMsgEnterAction());
	getContentPane().add(this.jTextSendMessage);
    }

    private void setCheckReservada() {
	reservada = new JCheckBox("Reservada");
	reservada.setBounds(100, 420, 200, 17);
	getContentPane().add(reservada);
    }

    public String getMensagem() {
	String mensagem = "";
	if (jTextSendMessage != null) {
	    mensagem = jTextSendMessage.getText();
	}
	return mensagem;
    }

    public boolean isReservada() {
	if (reservada != null) {
	    return reservada.isSelected();
	}
	return false;
    }

    public ListarUsuario getListaUsuarios() {
	return listaUsuarios;
    }

    public void limparMensagemEnviada() {
	jTextSendMessage.setText("");
    }
}
