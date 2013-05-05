package br.spei.chat.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.impl.ChatServiceImpl;

public class Server {

    public static void main(String args[]) {
	new Server();
    }

    public Server() {
	try {
	    ChatService cs = new ChatServiceImpl();
	    Registry registry = LocateRegistry.getRegistry(1066);
	    registry.rebind("ChatService", cs);

	    System.out.println("Servidor iniciado!");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}