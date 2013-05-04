package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.spei.chat.client.view.ConfiguracaoFrame;
import br.spei.chat.client.view.MainFrame;

public class AbrirConfiguracaoFrameAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	MainFrame.getInstance().addJanelaInterna(
		ConfiguracaoFrame.getInstance());
    }
}
