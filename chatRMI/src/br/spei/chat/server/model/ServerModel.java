package br.spei.chat.server.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.spei.chat.client.callback.ClienteInterface;
import br.spei.chat.model.Usuario;
import br.spei.chat.rmi.service.ChatService.NickNameException;

public class ServerModel implements Serializable {
    private static final long serialVersionUID = 3368529267336022665L;

    private static ServerModel server;
    private Set<Usuario> usuarios;
    private Map<Usuario, ClienteInterface> callbackUsuarios;

    public static ServerModel INSTANCE = getInstance();

    private ServerModel() throws IOException {
	usuarios = new HashSet<Usuario>();
	callbackUsuarios = new HashMap<Usuario, ClienteInterface>();
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

    public void adicionarCallbackUsuario(Usuario usuario,
	    ClienteInterface callback) {
	callbackUsuarios.put(usuario, callback);
    }

    public void adicionarUsuario(Usuario usuario, ClienteInterface callback)
	    throws NickNameException {
	for (Usuario user : usuarios) {
	    if (usuario.getNickname().equals(user.getNickname())) {
		throw new NickNameException();
	    }
	}
	usuarios.add(usuario);
	adicionarCallbackUsuario(usuario, callback);
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

    public Map<Usuario, ClienteInterface> getCallback() {
	return callbackUsuarios;
    }

    public ClienteInterface getCallbackUsuario(String nickname) {
	for (Usuario usuario : usuarios) {
	    if (nickname.equals(usuario.toString())) {
		return callbackUsuarios.get(usuario);
	    }
	}
	return null;
    }

    public ClienteInterface getSocket(Usuario usuario) {
	return getCallbackUsuario(usuario.getNickname());
    }

    public Usuario getUsuario(String nickname) {
	for (Usuario usuario : usuarios) {
	    if (usuario.equals(nickname)) {
		return usuario;
	    }
	}
	return null;
    }
}
