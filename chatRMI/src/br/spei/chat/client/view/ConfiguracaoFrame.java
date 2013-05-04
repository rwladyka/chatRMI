package br.spei.chat.client.view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.spei.chat.client.action.GravarConfiguracaoAction;
import br.spei.chat.client.model.Configuracao;

public class ConfiguracaoFrame extends JInternalFrame {
    private static final long serialVersionUID = -8700979883162349866L;

    private static ConfiguracaoFrame configuracaoFrame;

    private JPanel panel = new JPanel();
    private JButton gravar = new JButton("Gravar");
    private JLabel servidor = new JLabel("IP Servidor: ");
    private JLabel porta = new JLabel("Porta: ");
    private JTextField valorServidor = new JTextField(12);
    private JTextField valorPorta = new JTextField(5);

    public static ConfiguracaoFrame getInstance() {
	if (configuracaoFrame == null) {
	    configuracaoFrame = new ConfiguracaoFrame();
	}
	if (configuracaoFrame.isClosed()) {
	    configuracaoFrame.setVisible(true);
	}
	return configuracaoFrame;
    }

    private ConfiguracaoFrame() {
	super("Configurações");
	iniciarComponentes();
	setVisible(true);
    }

    private void iniciarComponentes() {
	panel.add(servidor);
	valorServidor.setText(Configuracao.getInstance().getIpServidor());
	panel.add(valorServidor);
	panel.add(porta);
	valorPorta
		.setText(String.valueOf(Configuracao.getInstance().getPort()));
	panel.add(valorPorta);
	panel.add(gravar);
	addActionGravar();
	add(panel);
	setSize(500, 70);
	setClosable(true);
    }

    private void addActionGravar() {
	this.gravar.addActionListener(new GravarConfiguracaoAction(this));
    }

    public String getServidor() {
	return valorServidor.getText();
    }

    public int getPorta() {
	int porta = 0;
	if (!valorPorta.getText().trim().isEmpty()) {
	    porta = Integer.parseInt(valorPorta.getText());
	}
	return porta;
    }
}
