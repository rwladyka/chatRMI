package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Socks;
import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.client.view.ChatFrame;
import br.spei.chat.client.view.ConectarFrame;
import br.spei.chat.client.view.MainFrame;
import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.ChatService.NickNameException;

public class ConectarAction implements ActionListener {

    public ConectarAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String nickname = ConectarFrame.getInstance().getNick();
	if (nickname == null || nickname.isEmpty()) {
	    return;
	}
	try {
	    ChatService chat = ServiceUtil.chatService();
	    Usuario usuario = chat.conectar(nickname);
	    ConectarFrame.getInstance().setVisible(false);
	    Who.setIam(usuario);
	    ChatFrame chatFrame = new ChatFrame(usuario.getNickname(),
		    chat.listarUsuarios());
	    MainFrame.getInstance().setChatFrame(chatFrame);
	    MainFrame.getInstance().addJanelaInterna(chatFrame);
	    new Socks();
	} catch (NickNameException ne) {
	    JOptionPane.showMessageDialog(null,
		    "Já existe um usuário com este nickname!");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Não foi possível conectar no servidor!");
	}
    }
}
