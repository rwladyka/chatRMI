package br.spei.chat.model;

import java.io.Serializable;
import java.util.UUID;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -5267311869379628456L;

    private UUID uuid;
    private String nickname;

    public Usuario(String nickname) {
	this.nickname = nickname;
	this.uuid = UUID.randomUUID();
    }

    public String getNickname() {
	return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = nickname;
    }

    public UUID getUuid() {
	return uuid;
    }

    @Override
    public String toString() {
	return nickname;
    }
}
