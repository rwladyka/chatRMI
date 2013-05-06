package br.spei.chat.client.view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.spei.chat.client.action.ConectarAction;

public class ConectarFrame extends JInternalFrame {
    private static final long serialVersionUID = -8093133734052974695L;

    private static ConectarFrame conectarFrame;

    public static ConectarFrame getInstance() {
	if (conectarFrame == null) {
	    conectarFrame = new ConectarFrame();
	}
	return conectarFrame;
    }

    private JPanel panel = new JPanel();
    private JButton connect;
    private JLabel nickname;
    private JTextField nick;

    private ConectarFrame() {
	initComponents();
    }

    private void initComponents() {
	this.connect = new JButton("Conectar");
	this.setNickname(new JLabel("Nickname: "));
	this.nick = new JTextField(25);
	addActionConectar();
	panel.add(nickname);
	panel.add(nick);
	panel.add(connect);
	add(panel);
	setSize(500, 70);
	setClosable(false);
	setVisible(true);
	MainFrame.getInstance().addJanelaInterna(this);
    }

    public void addActionConectar() {
	this.connect.addActionListener(new ConectarAction());
    }

    private void setNickname(JLabel nickname) {
	this.nickname = nickname;
    }

    public String getNick() {
	return nick.getText();
    }

}
