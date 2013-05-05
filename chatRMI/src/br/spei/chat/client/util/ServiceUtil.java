package br.spei.chat.client.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.spei.chat.rmi.service.ChatService;
import br.spei.chat.rmi.service.ChatService.NicknameException;

public class ServiceUtil {

    public static ChatService chatService() throws NicknameException,
	    RemoteException, MalformedURLException, NotBoundException {
	return (ChatService) Naming.lookup(ConfigUtil.uriRMI("ChatService"));
    }
}
