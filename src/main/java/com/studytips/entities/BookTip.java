package com.studytips.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studytips.enums.TipLevel;

import javax.persistence.*;

/**
 * Created by comp-dev on 4/13/17.
 */

@Entity
@Table(name="tb_book_tip")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookTip extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    private String title;

    private String author;

    private String edition;

    @Enumerated(EnumType.ORDINAL)
    private TipLevel tipLevel;

    @ManyToOne
    private Tip tip;

    @ManyToOne
    private User user;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public TipLevel getTipLevel() {
        return tipLevel;
    }

    public void setTipLevel(TipLevel tipLevel) {
        this.tipLevel = tipLevel;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
