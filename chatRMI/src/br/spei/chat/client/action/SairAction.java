package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SairAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	int resposta = JOptionPane.showConfirmDialog(null, "Deseja sair?",
		"Sair", JOptionPane.YES_NO_OPTION);
	if (resposta == 0) {
	    System.exit(0);
	}
    }

}
