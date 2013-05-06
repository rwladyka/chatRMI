package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.client.view.ChatFrame;
import br.spei.chat.model.Mensagem;
import br.spei.chat.model.TipoMensagem;
import br.spei.chat.rmi.service.ChatService;

public class EnviarMensagemAction implements ActionListener {

    private Mensagem mensagem;
    private ChatFrame chatFrame;

    public EnviarMensagemAction(ChatFrame chatFrame) {
	this.chatFrame = chatFrame;
	setMensagem(chatFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

    private Mensagem getMensagem() {
	return mensagem;
    }

    private void setMensagem(ChatFrame chat) {
	Mensagem mensagem = new Mensagem();
	mensagem.setDestinatario(chat.getDestinatario());
	mensagem.setMensagem(chat.getMensagem());
	mensagem.setUsuario(Who.IAM);
	if (chat.isReservada()) {
	    mensagem.setTipoMensagem(TipoMensagem.RESERVADA);
	} else {
	    mensagem.setTipoMensagem(TipoMensagem.PUBLICA);
	}
	this.mensagem = mensagem;
    }
}
