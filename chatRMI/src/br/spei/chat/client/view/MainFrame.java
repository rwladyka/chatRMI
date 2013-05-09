package br.spei.chat.client.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.spei.chat.client.model.Configuracao;
import br.spei.chat.client.model.Who;
import br.spei.chat.client.util.ServiceUtil;
import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.ChatService.NickNameException;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -6068509917409475161L;

    private static MainFrame mainFrame;

    private JPanel painelDeskTop = new JPanel(new BorderLayout());
    private JDesktopPane desk = new JDesktopPane();
    private ChatFrame chatFrame;

    public static MainFrame getInstance() {
	if (mainFrame == null) {
	    mainFrame = new MainFrame();
	}
	return mainFrame;
    }

    private MainFrame() {
	super("CHAT SPEI");
	init();
    }

    public static void main(String args[]) {
	Configuracao.getInstance().desserializar();
	getInstance();
	ConectarFrame.getInstance();
    }

    private void init() {
	setIconImage(new ImageIcon("images/logo_spei.png").getImage());
	setJMenuBar(new Menu());
	painelDeskTop.add(desk);
	this.add(painelDeskTop);
	setSize(800, 600);
	setVisible(true);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent arg0) {
		if (JOptionPane.showConfirmDialog(null, "Deseja sair", "Sair",
			JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
		    try {
			ChatService cs = ServiceUtil.chatService();
			cs.desconectar(Who.IAM);
		    } catch (RemoteException e) {
			e.printStackTrace();
		    } catch (MalformedURLException e) {
			e.printStackTrace();
		    } catch (NickNameException e) {
			e.printStackTrace();
		    } catch (NotBoundException e) {
			e.printStackTrace();
		    } finally {
			System.exit(0);
		    }
		}
	    }
	});
    }

    public void addJanelaInterna(JInternalFrame janelaInterna) {
	desk.remove(janelaInterna);
	desk.add(janelaInterna);
	desk.moveToFront(janelaInterna);
    }

    public void removerJanela(JInternalFrame janela) {
	desk.remove(janela);
    }

    public JDesktopPane getDesk() {
	return desk;
    }

    public void setChatFrame(ChatFrame chatFrame) {
	this.chatFrame = chatFrame;
    }

    public ChatFrame getChatFrame() {
	return chatFrame;
    }
}