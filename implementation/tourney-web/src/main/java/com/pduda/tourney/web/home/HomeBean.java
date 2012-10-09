package com.pduda.tourney.web.home;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
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
    private TourneyHandler tourneyHandler;
    private HomePo po;
    private HomePoFactory poFactory = new HomePoFactory();

    @PostConstruct
    public void init() {
        List<Tourney> tourneys = tourneyHandler.getTourneys();
        this.po = poFactory.buildPo(tourneys);
    }

    public HomePo getPo() {
        return po;
    }

    public void setPo(HomePo po) {
        this.po = po;
    }
}
