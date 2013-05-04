package br.spei.chat.client.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.spei.chat.client.action.AboutAction;
import br.spei.chat.client.action.AbrirConfiguracaoFrameAction;
import br.spei.chat.client.action.SairAction;

public class Menu extends JMenuBar {
    private static final long serialVersionUID = 5918152108071921194L;

    private JMenu arquivo = new JMenu("Arquivo");
    private JMenu editar = new JMenu("Editar");
    private JMenu help = new JMenu("Help");

    private JMenuItem sair = new JMenuItem("Sair", JFrame.EXIT_ON_CLOSE);
    private JMenuItem configuracao = new JMenuItem("Configurações");
    private JMenuItem about = new JMenuItem("About");

    public Menu() {
	this.arquivo.add(this.sair);
	sairAddAction();
	this.editar.add(this.configuracao);
	configuracaoAddAction();
	this.help.add(this.about);
	aboutAddAction();
	this.add(this.arquivo);
	this.add(this.editar);
	this.add(this.help);
    }

    private void sairAddAction() {
	this.sair.addActionListener(new SairAction());
    }

    private void aboutAddAction() {
	this.about.addActionListener(new AboutAction());
    }

    private void configuracaoAddAction() {
	this.configuracao.addActionListener(new AbrirConfiguracaoFrameAction());
    }

}
