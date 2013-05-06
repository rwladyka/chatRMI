package br.spei.chat.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.spei.chat.model.Mensagem;

public class MensagemUtil {
    public static String formatarMensagem(Mensagem mensagem) {
	if (mensagem.getDestinatario() == null
		|| mensagem.getDestinatario().isEmpty()) {
	    mensagem.setDestinatario("Todos");
	}
	return String.format(mensagem.getTipoMensagem().getFormatoDescricao(),
		formatarData(mensagem.getData()), mensagem.getUsuario()
			.getNickname(), mensagem.getDestinatario());
    }

    public static String formatarData(Date data) {
	SimpleDateFormat dataFormatada = new SimpleDateFormat(
		"dd/MM/yyyy hh24:mm:ss");
	return dataFormatada.format(data);
    }

    public static String formatarMensagemConexao(Mensagem mensagem) {
	return String.format(mensagem.getTipoMensagem().getFormatoDescricao(),
		formatarData(mensagem.getData()), mensagem.getUsuario()
			.getNickname());
    }
}
