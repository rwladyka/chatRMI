package br.spei.chat.client.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class Configuracao implements Serializable {
    private static final long serialVersionUID = -8350396994103888274L;

    private static Configuracao configuracao;

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String FOLDER = String.format("%s%s", USER_HOME,
	    "/chatRMI");
    private static final String FILE = String.format("%s%s", FOLDER,
	    "/server.conf");

    private Configuracao() {
    }

    private String ipServidor;
    private int port;

    public static Configuracao getInstance() {
	if (configuracao == null) {
	    configuracao = new Configuracao();
	}
	return configuracao;
    }

    public void serializar() {
	FileOutputStream arq = null;
	ObjectOutputStream out = null;
	try {
	    File diretorio = new File(FOLDER);
	    if (!diretorio.exists()) {
		diretorio.mkdirs();
	    }
	    File file = new File(FILE);
	    arq = new FileOutputStream(file);
	    out = new ObjectOutputStream(arq);
	    out.writeObject(this);
	    out.flush();
	} catch (IOException ex) {
	    ex.printStackTrace();
	} finally {
	    try {
		if (arq != null) {
		    arq.close();
		}
		if (out != null) {
		    out.close();
		}
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}
    }

    public void desserializar() {
	FileInputStream arqLeitura = null;
	ObjectInputStream in = null;
	try {
	    File dir = new File(FOLDER);
	    File arquivo = new File(FILE);
	    if (!dir.exists()) {
		dir.mkdirs();
	    }
	    if (!arquivo.exists()) {
		JOptionPane.showMessageDialog(null,
			"As configurações de servidor não foram feitas.");
	    }
	    arqLeitura = new FileInputStream(FILE);
	    in = new ObjectInputStream(arqLeitura);
	    configuracao = (Configuracao) in.readObject();
	} catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	} catch (IOException ex) {
	    ex.printStackTrace();
	} finally {
	    try {
		if (arqLeitura != null) {
		    arqLeitura.close();
		}
		if (in != null) {
		    in.close();
		}
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}

    }

    public String getIpServidor() {
	return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
	this.ipServidor = ipServidor;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }
}
