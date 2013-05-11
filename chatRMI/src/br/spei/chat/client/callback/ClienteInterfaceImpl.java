package br.spei.chat.client.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import br.spei.chat.client.model.Conversa;
import br.spei.chat.client.util.ListUtil;
import br.spei.chat.client.view.ChatFrame;
import br.spei.chat.client.view.MainFrame;

@SuppressWarnings("serial")
public class ClienteInterfaceImpl extends UnicastRemoteObject implements
	ClienteInterface {

    public ClienteInterfaceImpl() throws RemoteException {
	super();
    }

    @Override
    public void atualizarListaUsuarios(List<String> usuarios)
	    throws RemoteException {
	ChatFrame cf = MainFrame.getInstance().getChatFrame();
	if (cf != null) {
	    MainFrame.getInstance().getChatFrame().getListaUsuarios()
		    .setListClient(ListUtil.toArray(usuarios));
	}
    }

    @Override
    public void receberMensagem(String msgRecebida) throws RemoteException {
	Conversa.getInstance().atualizarConversa(msgRecebida);
	Conversa.getInstance().atualizarConversa("\n");
    }
}