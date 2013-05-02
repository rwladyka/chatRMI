package br.spei.chat.model;

public class Mensagem {

    private Usuario usuario;
    private String mensagem;

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }

    @Override
    public String toString() {
	return mensagem;
    }
}
