package br.spei.chat.client.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import br.spei.chat.client.model.Configuracao;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -6068509917409475161L;

    private static MainFrame mainFrame;

    private JPanel painelDeskTop = new JPanel(new BorderLayout());
    private JDesktopPane desk = new JDesktopPane();

    public static MainFrame getInstance() {
	if (mainFrame == null) {
	    mainFrame = new MainFrame();
	}
	return mainFrame;
    }

    private MainFrame() {
	super("SPEIPARK3000");
	init();
    }

    public static void main(String args[]) {
	Configuracao.getInstance().desserializar();
	getInstance();
    }

    private void init() {
	setIconImage(new ImageIcon("images/logo_spei.png").getImage());
	setJMenuBar(new Menu());
	painelDeskTop.add(desk);
	this.add(painelDeskTop);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(500, 400);
	setVisible(true);
    }

    public void addJanelaInterna(JInternalFrame janelaInterna) {
	desk.remove(janelaInterna);
	desk.add(janelaInterna);
	desk.moveToFront(janelaInterna);
    }

    public void removerJanela(JInternalFrame janela) {
	desk.remove(janela);
    }
}