package br.spei.chat.client.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import javax.swing.JTextArea;

public class ConversaEnterAction implements KeyListener {

    public ConversaEnterAction() {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
	String text =((JTextArea) arg0.getSource()).getText(); 
	if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
	    if (text == null || text.isEmpty()) {
		return;
	    }
	    try {
		ChatService chat = ServiceUtil.chatService();
		ClienteInterface callbackObj = new ClienteInterfaceImpl();
		Usuario usuario = chat.conectar(text, callbackObj);
		ConectarFrame.getInstance().setVisible(false);
		Who.setIam(usuario);
		ChatFrame chatFrame = new ChatFrame(usuario.getNickname(),
			chat.listarUsuarios());
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

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}
