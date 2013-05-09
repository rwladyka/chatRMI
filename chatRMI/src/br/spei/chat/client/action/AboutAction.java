package br.spei.chat.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AboutAction implements ActionListener {
    private final String SPEI = "Faculdades SPEI";
    private final String LPII = "TR de Sistemas Distribuidos.";
    private final String DESENVOLVIDO_POR = "Sistema Desenvolvido por:";
    private final String GEOVANI = " - Geovani Carraro";
    private final String GUSTAVO = " - Gustavo Mendes";
    private final String RICARDO = " - Ricardo Perez Gonçalves";
    private final String RODRIGO = " - Rodrigo Wladyka";
    private final String MENSAGEM_ABOUT = String.format(
	    "\t%s\n%s\n%s\n\t%s\n\t%s\n\t%s\n\t%s", SPEI, LPII,
	    DESENVOLVIDO_POR, GEOVANI, GUSTAVO, RICARDO, RODRIGO);

    @Override
    public void actionPerformed(ActionEvent e) {
	JOptionPane.showMessageDialog(null, MENSAGEM_ABOUT);
    }

}
