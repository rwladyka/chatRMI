package br.spei.chat.client.util;

import br.spei.chat.client.model.Configuracao;

public class ConfigUtil {
    public static final String SERVIDOR = Configuracao.getInstance()
	    .getIpServidor();
    public static final int PORT_SOCKET = 10000;
    private static final int PORT_URI = Configuracao.getInstance().getPort();
    private static final String RMI = String.format("%s%s%s%s%s", "rmi://",
	    SERVIDOR, ":", PORT_URI, "/");

    public static String uriRMI(String nomeService) {
	return String.format("%s%s", RMI, nomeService);
    }
}
