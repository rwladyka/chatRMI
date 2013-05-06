package br.spei.chat.model;

import java.io.Serializable;

public enum TipoMensagem implements Serializable {

    PUBLICA(1, "[%s] %s falou para %s: ", false), //
    RESERVADA(2, "[%s] %s falou reservadamente com %s", true), //
    SAIDA(3, "[%s] %s saiu da conversa", false), //
    CONECTADO(4, "[%s] %s entrou na conversa", false);

    private int id;
    private String formatoDescricao;
    private boolean reservada;

    private TipoMensagem(int id, String formatoDescricao, boolean reservada) {
	this.formatoDescricao = formatoDescricao;
	this.reservada = reservada;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFormatoDescricao() {
	return formatoDescricao;
    }

    public boolean isReservada() {
	return reservada;
    }

    @Override
    public String toString() {
	return name();
    }

}
