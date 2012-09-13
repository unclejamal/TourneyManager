package com.pduda.tourney.web;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.TourneyHandler;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

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
        this.tournaments = tournamentHandler.getTournaments();
    }

    public List<Tourney> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tourney> tournaments) {
        this.tournaments = tournaments;
    }
}
