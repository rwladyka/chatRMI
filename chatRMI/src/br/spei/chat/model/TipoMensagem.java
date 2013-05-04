package br.spei.chat.model;

public enum TipoMensagem {

    PUBLICA("PUBLICA", "%s falou para todos: "), PRIVADA("PRIVADA",
	    "%s falou reservadamente com %s");

    private String tipoMensagem;
    private String formatoDescricao;

    private TipoMensagem(String tipoMensagem, String formatoDescricao) {
	this.tipoMensagem = tipoMensagem;
	this.formatoDescricao = formatoDescricao;
    }

    public String getTipoMensagem() {
	return tipoMensagem;
    }

    public String getFormatoDescricao() {
	return formatoDescricao;
    }

    @Override
    public String toString() {
	return tipoMensagem;
    }

}
