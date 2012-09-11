package com.pduda.tourney.domain.ranking;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "PZFS_RANKING")
@Configurable(autowire = Autowire.BY_TYPE)
public class PzfsRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(PzfsRanking.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PZFS_RANKING_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OPEN_SINGLE")
    private Ranking openSingle;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OPEN_DOUBLE")
    private Ranking openDouble;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WOMEN_SINGLE")
    private Ranking womenSingle;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WOMEN_DOUBLE")
    private Ranking womenDouble;

    public PzfsRanking(Ranking openSingle, Ranking openDouble, Ranking womenSingle, Ranking womenDouble) {
        this.openSingle = openSingle;
        this.openDouble = openDouble;
        this.womenSingle = womenSingle;
        this.womenDouble = womenDouble;
    }

    public PzfsRanking() {
        //JPA
    }

    public Ranking getOpenDouble() {
        return openDouble;
    }

    public Ranking getOpenSingle() {
        return openSingle;
    }

    public Ranking getWomenSingle() {
        return womenSingle;
    }

    public Ranking getWomenDouble() {
        return womenDouble;
    }
}
