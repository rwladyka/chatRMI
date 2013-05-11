package br.spei.chat.client.model;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListarUsuario {

    private static ListarUsuario lista;
    private JLabel labelUsarios;
    private JList<String> listaUsuarios;
    private String[] listClient;
    private String destinatario;

    private JScrollPane panelUsuarios;

    private ListarUsuario() {
	labelUsarios = new JLabel("Usuários");
	labelUsarios.setBounds(590, 5, 200, 17);
	panelUsuarios = new JScrollPane();
	panelUsuarios.setBounds(590, 30, 190, 380);
	setListaUsuarios();
	destinatario = "Todos";
    }

    public static ListarUsuario getInstance() {
	if (lista == null) {
	    lista = new ListarUsuario();
	}
	return lista;
    }

    public void setListClient(String[] users) {
	listClient = users;
    }

    public void addUsersToPanel() {
	listaUsuarios.setListData(listClient);
	panelUsuarios.setViewportView(listaUsuarios);
    }

    private void setListaUsuarios() {
	listaUsuarios = new JList<String>(listClient);
	listaUsuarios.setBounds(590, 30, 190, 380);
	listaUsuarios.setEnabled(true);
	listaUsuarios.setValueIsAdjusting(true);
	listaUsuarios.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent arg0) {
		destinatario = listaUsuarios.getSelectedValue();
		System.out.println(listaUsuarios.getSelectedIndex());
		System.out.println(destinatario);
	    }
	});
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
