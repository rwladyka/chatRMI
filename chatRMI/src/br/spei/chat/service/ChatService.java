package br.spei.chat.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.spei.chat.model.Mensagem;
import br.spei.chat.model.Usuario;

public interface ChatService extends Remote {
    @SuppressWarnings("serial")
    public class NicknameException extends Exception {
	public NicknameException() {
	    super("Já existe um usuário com este nickname.");
	}
    }

    public void receberMensagemPublica(Mensagem mensagem)
	    throws RemoteException;

    public void receberMensagemPrivada(Mensagem mensagem)
	    throws RemoteException;

    public void transmitirMensagem(Mensagem mensagem) throws RemoteException;

    public Usuario conectar(String nickname) throws RemoteException,
	    NicknameException;

    public void desconectar(Usuario usuario) throws RemoteException;

}
