package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.spei.chat.client.model.Configuracao;
import br.spei.chat.client.view.ConfiguracaoFrame;
import br.spei.chat.client.view.MainFrame;

public class GravarConfiguracaoAction implements ActionListener {

    private ConfiguracaoFrame configuracao;

    public GravarConfiguracaoAction(ConfiguracaoFrame configuracao) {
	this.configuracao = configuracao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Configuracao.getInstance().setIpServidor(configuracao.getServidor());
	Configuracao.getInstance().setPort(configuracao.getPorta());
	Configuracao config = Configuracao.getInstance();
	config.serializar();
	JOptionPane.showMessageDialog(null,
		"Configuração alterada com sucesso!");
	MainFrame.getInstance().removerJanela(configuracao);
    }
}
