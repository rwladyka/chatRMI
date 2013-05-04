package br.spei.chat.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.spei.chat.model.Mensagem;
import br.spei.chat.model.Usuario;

public interface ChatService extends Remote {
    @SuppressWarnings("serial")
    public class NicknameException extends Exception {
	public NicknameException() {
	    super("Já existe um usuário com este nickname.");
	}
    }

    public void enviarMensagemPublica(Mensagem mensagem) throws RemoteException;

    public void enviarMensagemPrivada(Mensagem mensagem, String destinatario)
	    throws RemoteException;

    public Usuario conectar(String nickname) throws RemoteException,
	    NicknameException;

    public void desconectar(Usuario usuario) throws RemoteException;

    public List<String> listarUsuarios() throws RemoteException;

}
