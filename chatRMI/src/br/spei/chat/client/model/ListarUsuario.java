package br.spei.chat.client.model;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListarUsuario {

    private static ListarUsuario lista;
    private JLabel labelUsarios;
    private JList<String> listaUsuarios;
    private DefaultListModel<String> listClient;

    private JScrollPane panelUsuarios;

    private ListarUsuario() {
	setLabelLista();
	setListClient();
	setListaUsuarios();
	setPanelUsuarios();
    }

    public static ListarUsuario getInstance() {
	if (lista == null) {
	    lista = new ListarUsuario();
	}
	return lista;
    }

    private void setLabelLista() {
	this.labelUsarios = new JLabel("Usuários");
	this.labelUsarios.setBounds(590, 5, 200, 17);
    }

    private void setListClient() {
	this.listClient = new DefaultListModel<String>();
    }

    private void setListaUsuarios() {
	this.listaUsuarios = new JList<String>(this.listClient);
    }

    private void setPanelUsuarios() {
	this.panelUsuarios = new JScrollPane();
	this.panelUsuarios.setViewportView(this.listaUsuarios);
	this.panelUsuarios.setBounds(590, 30, 190, 380);
    }

    public JLabel getLabelUsuarios() {
	return labelUsarios;
    }

    public JScrollPane getPanelUsuarios() {
	return panelUsuarios;
    }

    public void setListaUsuarios(List<String> usuarios) {
	this.listClient.clear();
	for (String usuario : usuarios) {
	    this.listClient.addElement(usuario);
	}
	this.listaUsuarios = new JList<String>(listClient);
    }

    public JList<String> getListaUsuarios() {
	return listaUsuarios;
    }

    public String getDestinatario() {
	// destinatario = listaUsuarios.getModel().getElementAt(
	// listaUsuarios.getSelectedIndex());
	// if (destinatario == null || destinatario.isEmpty()) {
	return "teste";
	// } else {
	// return destinatario;
	// }
    }
}
