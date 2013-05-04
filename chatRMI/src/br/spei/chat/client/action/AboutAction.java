package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AboutAction implements ActionListener {
    private final String SPEI = "Faculdades SPEI";
    private final String LPII = "TR de Sistemas Distribuidos.";
    private final String DESENVOLVIDO_POR = "Sistema Desenvolvido por:";
    private final String GEOVANI = " - Geovani ";
    private final String RICARDO = " - Ricardo ";
    private final String RODRIGO = " - Rodrigo Wladyka";
    private final String ALUNO = " - Auno";
    private final String MENSAGEM_ABOUT = String.format(
	    "\t%s\n%s\n%s\n\t%s\n\t%s\n\t%s\n\t%s", SPEI, LPII,
	    DESENVOLVIDO_POR, ALUNO, GEOVANI, RICARDO, RODRIGO);

    @Override
    public void actionPerformed(ActionEvent e) {
	JOptionPane.showMessageDialog(null, MENSAGEM_ABOUT);
    }

}
