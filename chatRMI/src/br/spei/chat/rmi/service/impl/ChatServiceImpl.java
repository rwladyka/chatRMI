package br.spei.chat.rmi.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.spei.chat.model.Mensagem;
import br.spei.chat.model.TipoMensagem;
import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.server.model.ServerModel;

public class ChatServiceImpl implements ChatService {
    private static ServerModel server = ServerModel.INSTANCE;

    @Override
    public List<String> listarUsuarios() throws RemoteException {
	List<String> users = new ArrayList<String>();
	for (Usuario usuario : server.getUsuarios()) {
	    users.add(usuario.getNickname());
	}
	return users;
    }

    @Override
    public void enviarMensagemPublica(Mensagem mensagem) throws RemoteException {
	for (Usuario usuario : server.getUsuarios()) {
	    try {
		Socket socket = server.getSocketUsuarios().get(usuario);
		PrintWriter output = new PrintWriter(socket.getOutputStream(),
			true);
		output.println(TipoMensagem.PUBLICA + "***"
			+ mensagem.getUsuario().toString() + "***"
			+ mensagem.getMensagem());
		output.flush();
	    } catch (IOException e) {
		desconectar(usuario);
	    }
	}

    }

    @Override
    public void enviarMensagemPrivada(Mensagem mensagem, String destinatario)
	    throws RemoteException {
	Socket socket = server.getSocketUsuario(destinatario);
	if (socket != null) {
	    try {
		PrintWriter output = new PrintWriter(socket.getOutputStream(),
			true);
		output.println(TipoMensagem.PRIVADA + "***"
			+ TipoMensagem.PRIVADA.getFormatoDescricao() + "***"
			+ mensagem.getMensagem());
		output.flush();
	    } catch (IOException e) {
		desconectar(mensagem.getUsuario());
	    }
	}
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
	enviarMensagemPublica(new Mensagem(" saiu do chat."));
    }

}
