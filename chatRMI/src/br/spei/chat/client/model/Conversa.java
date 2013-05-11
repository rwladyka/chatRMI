package br.spei.chat.client.model;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.spei.chat.client.action.ConversaEnterAction;

public class Conversa {

    private static Conversa conversa;
    private JLabel labelConversa;
    private JScrollPane panelConversa;
    private JTextArea textareaConversa;

    private Conversa() {
	setLabelConversa();
	setTextareaConversa();
	setPanelConversa();
    }

    public static Conversa getInstance() {
	if (conversa == null) {
	    conversa = new Conversa();
	}
	return conversa;
    }

    private void setLabelConversa() {
	labelConversa = new JLabel("Conversa");
	labelConversa.setBounds(10, 5, 200, 17);
    }

    private void setPanelConversa() {
	panelConversa = new JScrollPane();
	panelConversa.setBounds(10, 30, 570, 380);
	panelConversa.setViewportView(textareaConversa);
    }

    private void setTextareaConversa() {
	textareaConversa = new JTextArea();
	textareaConversa.setColumns(5);
	textareaConversa.setEditable(false);
	textareaConversa.setRows(5);
	textareaConversa.addKeyListener(new ConversaEnterAction());
    }

    public JLabel getLabelConversa() {
	return labelConversa;
    }

    public JScrollPane getPanelConversa() {
	return panelConversa;
    }

    public void atualizarConversa(String mensagem) {
	textareaConversa.append(mensagem);
    }
}
