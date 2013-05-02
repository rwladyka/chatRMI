package br.spei.chat.model;

import java.util.UUID;

public class Usuario {

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
    public boolean equals(Object obj) {
	if (this.nickname == obj) {
	    return true;
	}
	return false;
    }

    @Override
    public String toString() {
	return nickname;
    }
}
