package br.spei.chat.client.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.client.view.ChatFrame;
import br.spei.chat.model.Mensagem;
import br.spei.chat.model.TipoMensagem;
import br.spei.chat.rmi.service.ChatService;

public class EnviarMensagemAction implements MouseListener {

    private Mensagem mensagem;
    private static ChatFrame chatFrame;

    public EnviarMensagemAction(ChatFrame chatFrame) {
	EnviarMensagemAction.chatFrame = chatFrame;
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
    public void mouseClicked(MouseEvent e) {
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
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
