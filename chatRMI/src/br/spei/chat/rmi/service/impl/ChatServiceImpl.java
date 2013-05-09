package br.spei.chat.rmi.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import br.spei.chat.model.Mensagem;
import br.spei.chat.model.TipoMensagem;
import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.server.model.ServerModel;
import br.spei.chat.server.util.MensagemUtil;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private static final long serialVersionUID = 7154266572305424129L;
    private static ServerModel server;

    public ChatServiceImpl() throws RemoteException {
	super();
	server = ServerModel.INSTANCE;
    }

    @Override
    public List<String> listarUsuarios() throws RemoteException {
	List<String> users = new ArrayList<String>();
	users.add("Todos");
	for (Usuario usuario : server.getUsuarios()) {
	    users.add(usuario.getNickname());
	}
	return users;
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) throws RemoteException {
	if (mensagem.getMensagem() == null || mensagem.getMensagem().isEmpty()) {
	    return;
	}
	if (mensagem.isReservada()) {
	    enviarMensagemReservada(mensagem);
	} else {
	    enviarMensagemPublica(mensagem);
	}
    }

    @Override
    public Usuario conectar(String nickname) throws NickNameException,
	    RemoteException {
	Usuario usuario = new Usuario(nickname);
	server.adicionarUsuario(usuario);
	Mensagem mensagem = new Mensagem();
	mensagem.setTipoMensagem(TipoMensagem.CONECTADO);
	mensagem.setUsuario(usuario);
	mensagem.setMensagem(MensagemUtil.formatarMensagemConexao(mensagem));
	enviarMensagem(mensagem);
	enviarSolicitarListaUsuario();
	return usuario;
    }

    @Override
    public void desconectar(Usuario usuario) throws RemoteException {
	server.removerUsuario(usuario);
	Mensagem mensagem = new Mensagem();
	mensagem.setTipoMensagem(TipoMensagem.SAIDA);
	mensagem.setUsuario(usuario);
	mensagem.setMensagem(MensagemUtil.formatarMensagem(mensagem));
	enviarMensagem(mensagem);
	enviarSolicitarListaUsuario();
    }

    private void enviarMensagemPublica(Mensagem mensagem)
	    throws RemoteException {
	for (Usuario usuario : server.getUsuarios()) {
	    try {
		Socket socket = server.getSocketUsuarios().get(usuario);
		if (socket != null) {
		    PrintWriter output = new PrintWriter(
			    socket.getOutputStream(), true);
		    output.println(MensagemUtil.formatarMensagem(mensagem));
		    output.flush();
		}
	    } catch (IOException e) {
		desconectar(usuario);
	    }
	}
    }

    private void enviarMensagemReservada(Mensagem mensagem)
	    throws RemoteException {
	try {
	    Socket envio = server.getSocket(mensagem.getUsuario());
	    Socket destinatario = server.getSocketUsuario(mensagem
		    .getDestinatario());
	    if (destinatario == null) {
		enviarMensagemPublica(mensagem);
	    }
	    PrintWriter outputEnvio = new PrintWriter(envio.getOutputStream(),
		    true);
	    outputEnvio.println(MensagemUtil.formatarMensagem(mensagem));
	    outputEnvio.flush();
	    PrintWriter output = new PrintWriter(
		    destinatario.getOutputStream(), true);
	    output.println(MensagemUtil.formatarMensagem(mensagem));
	    output.flush();
	} catch (IOException e) {
	    desconectar(server.getUsuario(mensagem.getDestinatario()));
	    e.printStackTrace();
	}
    }

    private void enviarSolicitarListaUsuario() throws RemoteException {
	for (Usuario usuario : server.getUsuarios()) {
	    try {
		Socket socket = server.getSocketUsuarios().get(usuario);
		if (socket != null) {
		    PrintWriter output = new PrintWriter(
			    socket.getOutputStream(), true);
		    output.println(SOLICITAR_LISTA_USUARIOS);
		    output.flush();
		}
	    } catch (IOException e) {
		desconectar(usuario);
	    }
	}
    }

}
