package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.spei.chat.client.callback.ClienteInterface;
import br.spei.chat.client.callback.ClienteInterfaceImpl;
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
	    ClienteInterface callbackObj = new ClienteInterfaceImpl();
	    Usuario usuario = chat.conectar(nickname, callbackObj);
	    ConectarFrame.getInstance().setVisible(false);
	    Who.setIam(usuario);
	    String user = usuario.getNickname();
	    List<String> users = chat.listarUsuarios();
	    ChatFrame chatFrame = new ChatFrame(user, users);
	    MainFrame.getInstance().setChatFrame(chatFrame);
	    MainFrame.getInstance().addJanelaInterna(chatFrame);
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
