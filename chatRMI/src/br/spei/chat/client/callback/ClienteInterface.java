package br.spei.chat.client.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClienteInterface extends Remote {

    public void atualizarListaUsuarios(List<String> usuarios)
	    throws RemoteException;

    public void receberMensagem(String msgRecebida) throws RemoteException;

}