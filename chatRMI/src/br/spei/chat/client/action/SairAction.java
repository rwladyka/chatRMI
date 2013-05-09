package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.ChatService.NickNameException;

public class SairAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair?",
		"Sair", JOptionPane.YES_NO_OPTION);
	if (resposta == 0) {
	    try {
		ChatService chat = ServiceUtil.chatService();
		chat.desconectar(Who.IAM);
	    } catch (RemoteException e1) {
		e1.printStackTrace();
	    } catch (MalformedURLException e1) {
		e1.printStackTrace();
	    } catch (NickNameException e1) {
		e1.printStackTrace();
	    } catch (NotBoundException e1) {
		e1.printStackTrace();
	    } finally {
		System.exit(0);
	    }
	}
    }
}
