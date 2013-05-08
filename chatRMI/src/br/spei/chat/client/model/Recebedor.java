package br.spei.chat.client.model;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.client.view.MainFrame;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.ChatService.NicknameException;

public class Recebedor implements Runnable {
    private InputStream is;
    private Scanner scanner;

    public Recebedor(InputStream is) {
	this.is = is;
    }

    public void run() {
	scanner = new Scanner(this.is);
	while (scanner.hasNextLine()) {
	    String msgRecebida = scanner.nextLine();
	    if (msgRecebida.equals(ChatService.SOLICITAR_LISTA_USUARIOS)) {
		try {
		    MainFrame
			    .getInstance()
			    .getChatFrame()
			    .getListaUsuarios()
			    .setListaUsuarios(
				    ServiceUtil.chatService().listarUsuarios());
		} catch (RemoteException e) {
		    e.printStackTrace();
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (NicknameException e) {
		    e.printStackTrace();
		} catch (NotBoundException e) {
		    e.printStackTrace();
		}
	    } else {
		Conversa.getInstance().atualizarConversa(msgRecebida);
		Conversa.getInstance().atualizarConversa("\n");
	    }
	}
    }
}
