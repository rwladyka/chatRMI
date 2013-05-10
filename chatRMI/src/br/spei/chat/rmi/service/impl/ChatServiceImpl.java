package br.spei.chat.rmi.service.impl;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import br.spei.chat.client.callback.ClienteInterface;
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
    public Usuario conectar(String nickname, ClienteInterface callback)
	    throws NickNameException, RemoteException {
	Usuario usuario = new Usuario(nickname);
	server.adicionarUsuario(usuario, callback);
	Mensagem mensagem = new Mensagem();
	mensagem.setTipoMensagem(TipoMensagem.CONECTADO);
	mensagem.setUsuario(usuario);
	mensagem.setMensagem(MensagemUtil.formatarMensagemConexao(mensagem));
	enviarMensagem(mensagem);
	atualizarListasUsuarios();
	return usuario;
    }

    @Override
    public void desconectar(Usuario usuario) throws RemoteException {
	server.removerUsuario(usuario);
	Mensagem mensagem = new Mensagem();
	mensagem.setTipoMensagem(TipoMensagem.SAIDA);
	mensagem.setUsuario(usuario);
	mensagem.setMensagem(MensagemUtil.formatarMensagemConexao(mensagem));
	enviarMensagem(mensagem);
	atualizarListasUsuarios();
    }

    private void enviarMensagemPublica(Mensagem mensagem)
	    throws RemoteException {
	for (Usuario usuario : server.getUsuarios()) {
	    try {
		ClienteInterface callback = server.getCallback().get(usuario);
		if (callback != null) {
		    callback.receberMensagem(MensagemUtil
			    .formatarMensagem(mensagem));
		}
	    } catch (IOException e) {
		desconectar(usuario);
	    }
	}
    }

    private void enviarMensagemReservada(Mensagem mensagem)
	    throws RemoteException {
	try {
	    ClienteInterface envio = server.getCallback().get(
		    mensagem.getUsuario());
	    ClienteInterface destinatario = server.getCallbackUsuario(mensagem
		    .getDestinatario());
	    if (destinatario == null) {
		enviarMensagemPublica(mensagem);
	    }
	    String msg = MensagemUtil.formatarMensagem(mensagem);
	    envio.receberMensagem(msg);
	    destinatario.receberMensagem(msg);
	} catch (IOException e) {
	    desconectar(server.getUsuario(mensagem.getDestinatario()));
	    e.printStackTrace();
	}
    }

    private void atualizarListasUsuarios() throws RemoteException {
	for (Usuario usuario : server.getUsuarios()) {
	    try {
		ClienteInterface callback = server.getCallback().get(usuario);
		if (callback != null) {
		    callback.atualizarListaUsuarios(listarUsuarios());
		}
	    } catch (IOException e) {
		desconectar(usuario);
	    }
	}
    }
}
