package br.spei.chat.server.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService.NickNameException;

public class ServerModel implements Serializable {
    private static final long serialVersionUID = 3368529267336022665L;

    private static ServerModel server;
    private Set<Usuario> usuarios;
    private Map<Usuario, Socket> socketUsuarios;
    private Usuario lastConection;

    public static ServerModel INSTANCE = getInstance();

    private ServerModel() throws IOException {
	usuarios = new HashSet<Usuario>();
	socketUsuarios = new HashMap<Usuario, Socket>();
    }

    private static ServerModel getInstance() {
	if (server == null) {
	    try {
		server = new ServerModel();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return server;
    }

    public void adicionarSocketUsuario(Socket socket) {
	socketUsuarios.put(this.lastConection, socket);
    }

    public void adicionarUsuario(Usuario usuario) throws NickNameException {
	for (Usuario user : usuarios) {
	    if (usuario.getNickname().equals(user.getNickname())) {
		throw new NickNameException();
	    }
	}
	usuarios.add(usuario);
	lastConection = usuario;
    }

    public void removerUsuario(Usuario usuario) {
	for (Usuario user : usuarios) {
	    if (usuario.getNickname().equals(user.getNickname())) {
		this.usuarios.remove(user);
	    }
	}
    }

    public Set<Usuario> getUsuarios() {
	return usuarios;
    }

    public Map<Usuario, Socket> getSocketUsuarios() {
	return socketUsuarios;
    }

    public Socket getSocketUsuario(String nickname) {
	for (Usuario usuario : usuarios) {
	    if (nickname.equals(usuario.toString())) {
		return socketUsuarios.get(usuario);
	    }
	}
	return null;
    }

    public Socket getSocket(Usuario usuario) {
	return getSocketUsuario(usuario.getNickname());
    }

    public Usuario getUsuario(String nickname) {
	for (Usuario usuario : usuarios) {
	    if (usuario.equals(nickname)) {
		return usuario;
	    }
	}
	return null;
    }

    public Usuario getLastConection() {
	return lastConection;
    }
}
