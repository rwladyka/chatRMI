package br.spei.chat.model;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private static final long serialVersionUID = -774961992381395913L;

    private Usuario usuario;
    private String mensagem;
    private TipoMensagem tipoMensagem;

    public Mensagem() {
    }

    public Mensagem(String mensagem) {
	this.mensagem = mensagem;
	this.usuario = new Usuario("Sistema");
    }

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

    public TipoMensagem getTipoMensagem() {
	return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
	this.tipoMensagem = tipoMensagem;
    }

    @Override
    public String toString() {
	return mensagem;
    }
}
