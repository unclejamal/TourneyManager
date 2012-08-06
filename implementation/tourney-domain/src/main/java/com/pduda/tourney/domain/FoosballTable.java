package com.pduda.tourney.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Table(name = "FOOSBALL_TABLE")
@Configurable(autowire = Autowire.BY_TYPE)
public class FoosballTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOOSBALL_TABLE_ID")
    private long id;
    @Column(name = "NAME")
    private String name;

    public FoosballTable(String name) {
        this.name = name;
    }

    public FoosballTable() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
