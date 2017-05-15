package com.studytips.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studytips.enums.TipLevel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by comp-dev on 4/13/17.
 */
@Entity
@Table(name="tb_web_tip")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebTip extends GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private TipLevel tipLevel;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Link> links;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
