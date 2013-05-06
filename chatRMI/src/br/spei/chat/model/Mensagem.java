package br.spei.chat.model;

import java.io.Serializable;
import java.util.Date;

public class Mensagem implements Serializable {
    private static final long serialVersionUID = -774961992381395913L;

    private Date data;
    private Usuario usuario;
    private String destinatario;
    private String mensagem;
    private TipoMensagem tipoMensagem;

    public Mensagem() {
	this.data = new Date();
    }

    public Date getData() {
	return data;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public String getDestinatario() {
	return destinatario;
    }

    public void setDestinatario(String destinatario) {
	this.destinatario = destinatario;
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
