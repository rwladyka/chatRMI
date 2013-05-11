package br.spei.chat.client.util;

import java.util.List;

public class ListUtil {

    public static String[] toArray(List<String> users)  {
	String[] usuarios = new String[users.size() +1];
	int i = 0;
	for (String user : users) {
	    usuarios[i] = user;
	    i++;
	}
	return usuarios;
    }
}
