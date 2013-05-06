package br.spei.chat.client.model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import br.spei.chat.client.util.ConfigUtil;

public class Socks {

    private Socket socket;

    public Socks() {
	try {
	    socket = new Socket(ConfigUtil.SERVIDOR, ConfigUtil.PORT_SOCKET);
	    Recebedor recebedor = new Recebedor(socket.getInputStream());
	    new Thread(recebedor).start();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
