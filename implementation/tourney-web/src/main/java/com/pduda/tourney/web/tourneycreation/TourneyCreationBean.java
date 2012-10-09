package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.service.tourney.TourneyCreationSo;
import com.pduda.tourney.domain.service.tourney.TourneyEventSo;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.domain.util.Clock;
import com.pduda.tourney.web.jsf.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("tourneyCreation")
@Scope("view")
public class TourneyCreationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyCreationBean.class.getClass().getName());
    public static final String ATTR_CATEGORY = "category";
    @Inject
    private TourneyHandler tourneyHandler;
    @Inject
    private SeedingStrategyFactory seedingStrategyFactory;
    @Inject
    private Clock clock;
    @Inject
    private WebUtils webUtils;
    private SelectItem[] categorySuggestions;
    private TourneyCreationPo po;

    @PostConstruct
    public void init() {
        String tourneyName = "Tourney " + (tourneyHandler.getTourneys().size() + 1);
        this.po = new TourneyCreationPo(tourneyName, clock);
        this.categorySuggestions = createCategorySuggestions();
    }

    public String createTourney() {
        log.log(Level.INFO, "User wants to create a tourney \"{0}\" for: {1}", new Object[]{po.getTourneyName(), po.getEvents()});

        List<TourneyEventSo> events = new ArrayList<TourneyEventSo>();
        for (TourneyEventPo eventPo : po.getEvents()) {
            events.add(new TourneyEventSo(eventPo.getCategory(), eventPo.getDate()));
        }
        TourneyCreationSo dto = new TourneyCreationSo(po.getTourneyName(), events);
        long tourneyId = tourneyHandler.createTournament(dto);

        webUtils.redirect("manageTourney.html?tourneyId=" + tourneyId);

        return "tourney";
    }

    public void addEvent(ActionEvent event) {
        log.log(Level.INFO, "User wants to add an event {0}, {1}", new Object[]{"zz", "yy"});

        po.addNewEvent();
    }

    public void removeEvent(ActionEvent event) {
        String categoryString = webUtils.getRequestAttribute(event, ATTR_CATEGORY);
        EventCategory category = EventCategory.valueOf(categoryString);
        log.log(Level.INFO, "User wants to remove the event: {0}", category);
        po.removeEvent(category);
    }

    private SelectItem[] createCategorySuggestions() {
        SelectItem[] suggestions = new SelectItem[EventCategory.values().length];
        int i = 0;
        for (EventCategory category : EventCategory.values()) {
            suggestions[i++] = new SelectItem(category, category.name());
        }

        return suggestions;
    }

    public SelectItem[] getCategorySuggestions() {
        return categorySuggestions;
    }

    public void setCategorySuggestions(SelectItem[] categorySuggestions) {
        this.categorySuggestions = categorySuggestions;
    }

    public TourneyCreationPo getPo() {
        return po;
    }

    public void setPo(TourneyCreationPo po) {
        this.po = po;
    }

    void setTourneyHandler(TourneyHandler tourneyHandler) {
        this.tourneyHandler = tourneyHandler;
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }

    void setWebUtils(WebUtils webUtils) {
        this.webUtils = webUtils;
    }
}