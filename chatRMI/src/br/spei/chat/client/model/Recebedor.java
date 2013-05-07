package br.spei.chat.client.model;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {
    private InputStream is;

    public Recebedor(InputStream is) {
	this.is = is;
    }

    @SuppressWarnings("resource")
    public void run() {
	Scanner s = new Scanner(this.is);
	while (s.hasNextLine()) {
	    Conversa.getInstance().atualizarConversa(s.nextLine());
	    Conversa.getInstance().atualizarConversa("\n");
	}
    }

}
