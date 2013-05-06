package br.spei.chat.server;

import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.impl.ChatServiceImpl;
import br.spei.chat.server.model.ServerModel;

public class Server {

    private final int PORT_SOCKET = 10000;

    private ServerModel server;
    private ServerSocket serverSocket;

    public static void main(String args[]) {
	new Server();
    }

    public Server() {
	try {
	    ChatService cs = new ChatServiceImpl();
	    Registry registry = LocateRegistry.createRegistry(1066);
	    registry.rebind("ChatService", cs);

	    System.out.println("Servidor iniciado!");

	    serverSocket = new ServerSocket(PORT_SOCKET);
	    server = ServerModel.INSTANCE;
	    while (true) {
		server.adicionarSocketUsuario(serverSocket.accept());
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}