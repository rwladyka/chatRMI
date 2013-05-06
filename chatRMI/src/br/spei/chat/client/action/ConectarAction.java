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
import br.spei.chat.rmi.service.ChatService.NicknameException;

public class ConectarAction implements ActionListener {

    public ConectarAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    ChatService chat = ServiceUtil.chatService();
	    Usuario usuario = chat.conectar(ConectarFrame.getInstance()
		    .getNick());
	    ConectarFrame.getInstance().setVisible(false);
	    Who.setIam(usuario);
	    MainFrame.getInstance()
		    .addJanelaInterna(
			    new ChatFrame(usuario.getNickname(), chat
				    .listarUsuarios()));
	    new Socks();
	} catch (NicknameException ne) {
	    JOptionPane.showMessageDialog(null,
		    "Já existe um usuário com este nickname!");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Não foi possível conectar no servidor!");
	}
    }
}
