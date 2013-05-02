package br.spei.chat.service.impl;

import java.rmi.RemoteException;

import br.spei.chat.model.Mensagem;
import br.spei.chat.model.Usuario;
import br.spei.chat.server.model.ServerModel;
import br.spei.chat.service.ChatService;

public class ChatServiceImpl implements ChatService {
    private static ServerModel server = ServerModel.INSTANCE;

    @Override
    public void receberMensagemPublica(Mensagem mensagem)
	    throws RemoteException {
	// TODO Auto-generated method stub

    }

    @Override
    public void receberMensagemPrivada(Mensagem mensagem)
	    throws RemoteException {

    }

    @Override
    public void transmitirMensagem(Mensagem mensagem) throws RemoteException {
	// TODO Auto-generated method stub

    }

    @Override
    public Usuario conectar(String nickname) throws RemoteException,
	    NicknameException {
	Usuario usuario = new Usuario(nickname);
	server.adicionarUsuario(usuario);
	return usuario;
    }

    @Override
    public void desconectar(Usuario usuario) throws RemoteException {
	server.removerUsuario(usuario);
    }

}
