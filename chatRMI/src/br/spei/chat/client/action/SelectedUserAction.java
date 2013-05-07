package br.spei.chat.client.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import br.spei.chat.client.model.ListarUsuario;

public class SelectedUserAction implements MouseListener {
    private final ListarUsuario lista;

    public SelectedUserAction() {
	this.lista = ListarUsuario.getInstance();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	lista.getListaUsuarios().getModel()
		.getElementAt(lista.getListaUsuarios().getSelectedIndex());

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}
