package br.spei.chat.client.model;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	this.labelConversa = new JLabel("Conversa");
	this.labelConversa.setBounds(10, 5, 200, 17);
    }

    private void setPanelConversa() {
	this.panelConversa = new JScrollPane();
	this.panelConversa.setBounds(10, 30, 570, 380);
	this.panelConversa.setViewportView(textareaConversa);
    }

    private void setTextareaConversa() {
	this.textareaConversa = new JTextArea();
	this.textareaConversa.setColumns(50);
	this.textareaConversa.setEditable(false);
	this.textareaConversa.setRows(5);
	this.textareaConversa.setAutoscrolls(false);
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
