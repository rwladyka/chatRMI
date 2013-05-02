package br.spei.chat.server.model;

import java.util.HashSet;
import java.util.Set;

import br.spei.chat.model.Usuario;
import br.spei.chat.service.ChatService.NicknameException;

public class ServerModel {

    private static ServerModel server;
    private Set<Usuario> usuarios;

    public static ServerModel INSTANCE = getInstance();

    private ServerModel() {
	usuarios = new HashSet<Usuario>();
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
}
