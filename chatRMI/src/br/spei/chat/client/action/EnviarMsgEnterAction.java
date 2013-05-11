package br.spei.chat.client.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.client.view.ChatFrame;
import br.spei.chat.client.view.MainFrame;
import br.spei.chat.model.Mensagem;
import br.spei.chat.model.TipoMensagem;
import br.spei.chat.rmi.service.ChatService;

public class EnviarMsgEnterAction implements KeyListener {
    
    private Mensagem mensagem;
    private ChatFrame chatFrame;

    public EnviarMsgEnterAction() {
    }

    private Mensagem getMensagem() {
	return mensagem;
    }

    private void setMensagem() {
	Mensagem mensagem = new Mensagem();
	mensagem.setDestinatario(chatFrame.getListaUsuarios().getDestinatario());
	mensagem.setMensagem(chatFrame.getMensagem());
	mensagem.setUsuario(Who.IAM);
	if (chatFrame.isReservada()) {
	    mensagem.setTipoMensagem(TipoMensagem.RESERVADA);
	} else {
	    mensagem.setTipoMensagem(TipoMensagem.PUBLICA);
	}
	this.mensagem = mensagem;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
	if(arg0.getKeyCode() != KeyEvent.VK_ENTER){
	    return;
	}
	chatFrame = MainFrame.getInstance().getChatFrame();
	setMensagem();
	if (mensagem == null) {
	    return;
	}
	if (mensagem.getMensagem() == null || mensagem.getMensagem().isEmpty()) {
	    return;
	}
	try {
	    ChatService chat = ServiceUtil.chatService();
	    chat.enviarMensagem(getMensagem());
	    chatFrame.limparMensagemEnviada();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Não foi possível enviar a mensagem!");
	}
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}
