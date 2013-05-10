package br.spei.chat.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.spei.chat.client.callback.ClienteInterface;
import br.spei.chat.model.Mensagem;
import br.spei.chat.model.Usuario;

public interface ChatService extends Remote {
    @SuppressWarnings("serial")
    public class NickNameException extends Exception {
	public NickNameException() {
	    super("Já existe um usuário com este nickname.");
	}
    }

    public static final String SOLICITAR_LISTA_USUARIOS = "***SOLICITAR LISTA***";

    public void enviarMensagem(Mensagem mensagem) throws RemoteException;

    public Usuario conectar(String nickname, ClienteInterface callback)
	    throws RemoteException, NickNameException;

    public void desconectar(Usuario usuario) throws RemoteException;

    public List<String> listarUsuarios() throws RemoteException;

}
