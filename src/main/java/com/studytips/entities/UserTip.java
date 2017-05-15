package com.studytips.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studytips.enums.TipLevel;

import javax.persistence.*;

/**
 * Created by comp-dev on 4/13/17.
 */

@Entity
@Table(name="tb_user_tip")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTip extends GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String text;

    @Enumerated(EnumType.ORDINAL)
    private TipLevel tipLevel;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tip tip;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public TipLevel getTipLevel() {
        return tipLevel;
    }

    public void setTipLevel(TipLevel tipLevel) {
        this.tipLevel = tipLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
