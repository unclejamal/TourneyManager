package com.pduda.tourney.web;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.TourneyHandler;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named("home")
@Scope("view")
public class HomeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(HomeBean.class.getClass().getName());
    @Inject
    private TourneyHandler tournamentHandler;
    private List<Tourney> tournaments;

    @PostConstruct
    public void init() {
        log.info("Home Bean: Post Construct");
        this.tournaments = tournamentHandler.getTournaments();
    }

    public List<Tourney> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tourney> tournaments) {
        this.tournaments = tournaments;
    }
}
