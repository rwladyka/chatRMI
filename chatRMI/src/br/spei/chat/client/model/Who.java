package br.spei.chat.client.model;

import br.spei.chat.model.Usuario;

public class Who {

    public static Usuario IAM;

    public static void setIam(Usuario usuario) {
	Who.IAM = usuario;
    }
}
