package br.spei.chat.server.model;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService.NicknameException;

public class ServerModel implements Serializable {
    private static final long serialVersionUID = 3368529267336022665L;

    private static ServerModel server;
    private Set<Usuario> usuarios;
    private Map<Usuario, Socket> socketUsuarios;

    public static ServerModel INSTANCE = getInstance();

    private ServerModel() {
	usuarios = new HashSet<Usuario>();
	socketUsuarios = new HashMap<Usuario, Socket>();
    }

    private static ServerModel getInstance() {
	if (server == null) {
	    server = new ServerModel();
	}
	return server;
    }

    public void adicionarUsuario(Usuario usuario) throws NicknameException {
	if (usuarios.contains(usuario)) {
	    throw new NicknameException();
	}
	this.usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
	this.usuarios.remove(usuarios);
    }

    public Set<Usuario> getUsuarios() {
	return usuarios;
    }

    public Map<Usuario, Socket> getSocketUsuarios() {
	return socketUsuarios;
    }

    public Socket getSocketUsuario(String nickname) {
	for (Usuario usuario : usuarios) {
	    if (usuario.equals(nickname)) {
		return socketUsuarios.get(usuario);
	    }
	}
	return null;
    }
}
