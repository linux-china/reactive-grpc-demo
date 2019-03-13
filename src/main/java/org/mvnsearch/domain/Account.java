package org.mvnsearch.domain;

/**
 * Account entity
 *
 * @author linux_china
 */
public class Account {
    private Integer id;
    private String nick;

    public Account() {
    }

    public Account(Integer id, String nick) {
        this.id = id;
        this.nick = nick;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
