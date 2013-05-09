package br.spei.chat.client.model;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListarUsuario {

    private static ListarUsuario lista;
    private JLabel labelUsarios;
    private JList<String> listaUsuarios;
    private DefaultListModel<String> listClient;
    private String destinatario;

    private JScrollPane panelUsuarios;

    private ListarUsuario() {
	setLabelLista();
	setListClient();
	setListaUsuarios();
	setPanelUsuarios();
	destinatario = "Todos";
    }

    public static ListarUsuario getInstance() {
	if (lista == null) {
	    lista = new ListarUsuario();
	}
	return lista;
    }

    private void setLabelLista() {
	labelUsarios = new JLabel("Usuários");
	labelUsarios.setBounds(590, 5, 200, 17);
    }

    private void setListClient() {
	listClient = new DefaultListModel<String>();
    }

    public void setListaUsuarios(List<String> usuarios) {
	listClient.clear();
	for (String usuario : usuarios) {
	    listClient.addElement(usuario);
	}
	listaUsuarios = new JList<String>(listClient);
	panelUsuarios.setViewportView(listaUsuarios);
    }

    private void setListaUsuarios() {
	listaUsuarios = new JList<String>(listClient);
	listaUsuarios.setBounds(590, 30, 190, 380);
	listaUsuarios.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent arg0) {
		destinatario = listaUsuarios.getSelectedValue();
		System.out.println(listaUsuarios.getSelectedIndex());
		System.out.println(destinatario);
	    }
	});
    }

    private void setPanelUsuarios() {
	panelUsuarios = new JScrollPane();
	panelUsuarios.setBounds(590, 30, 190, 380);
    }

    public JLabel getLabelUsuarios() {
	return labelUsarios;
    }

    public JScrollPane getPanelUsuarios() {
	return panelUsuarios;
    }

    public JList<String> getListaUsuarios() {
	return listaUsuarios;
    }

    public String getDestinatario() {
	return destinatario;
    }
}
