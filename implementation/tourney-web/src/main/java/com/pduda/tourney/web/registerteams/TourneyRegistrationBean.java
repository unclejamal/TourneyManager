package com.pduda.tourney.web.registerteams;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.EventPlayer;
import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.service.payments.PersistentPaymentsHandler;
import com.pduda.tourney.domain.service.ranking.RankingHandler;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.web.jsf.WebUtils;
import com.pduda.tourney.web.tourneycreation.SeedingStrategyFactory;
import com.pduda.tourney.web.tourneycreation.SeedingType;
import com.pduda.tourney.web.tourneycreation.TeamsByPointsComparator;
import com.pduda.tourney.web.tourneymgmt.TourneyManagementBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.springframework.context.annotation.Scope;

@Named("tourneyRegistration")
@Scope("view")
public class TourneyRegistrationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyRegistrationBean.class.getClass().getName());
    @Inject
    private TourneyHandler tournamentHandler;
    @Inject
    private PersistentPaymentsHandler feeHandler;
    @Inject
    private RankingHandler rankingHandler;
    @Inject
    private WebUtils webUtils;
    private TourneyEvent tourneyEvent;
    private Ranking ranking;
    private List<Team> teams = new ArrayList<Team>();
    @Size(min = 3, max = 40)
    private String newPlayerName1;
    @Size(min = 3, max = 40)
    private String newPlayerName2;
    private List<String> rankingSuggestions = new ArrayList<String>();
    private SeedingType seedingType = SeedingType.FULLY;
    @Inject
    private SeedingStrategyFactory seedingStrategyFactory;
    private SelectItem[] seedingSuggestions;

    @PostConstruct
    public void init() {
        this.seedingSuggestions = createSeedingSuggestions();
        tourneyEvent = tournamentHandler.getEvent(getEventId());
        chooseRanking(tourneyEvent.getEventCategory());
    }

    private SelectItem[] createSeedingSuggestions() {
        SelectItem[] suggestions = new SelectItem[SeedingType.values().length];
        int i = 0;
        for (SeedingType type : SeedingType.values()) {
            suggestions[i++] = new SelectItem(type, type.name());
        }

        return suggestions;
    }

    public String registerTeams() {
        log.log(Level.INFO, "User wants to register teams: {0}", new Object[]{teams});

        seedingStrategyFactory.getSeedingStrategy(seedingType).seed(teams);
        tournamentHandler.registerTeams(tourneyEvent.getId(), new HashSet<Team>(teams));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("manageTourney.html?" + TourneyManagementBean.PARAM_TOURNEY_ID + "=" + tourneyEvent.getTourney().getId());
        } catch (IOException ex) {
            System.out.println("dupsko");
        }
        return "tourney";
    }

    public void addTeam(ActionEvent event) {
        log.log(Level.INFO, "User wants to add a team {0}, {1}", new Object[]{newPlayerName1, newPlayerName2});

        Team team;
        EventPlayer newPlayer1 = createTourneyPlayer(newPlayerName1);
        if ((newPlayerName2 != null) && (!newPlayerName2.isEmpty())) {
            EventPlayer newPlayer2 = createTourneyPlayer(newPlayerName2);
            team = new Team(newPlayer1, newPlayer2);
        } else {
            team = new Team(newPlayer1);
        }

        teams.add(team);
        Collections.sort(teams, new TeamsByPointsComparator());
    }

    public void reorderTeams(ActionEvent event) {
        log.log(Level.INFO, "User has reordered teams");
        this.teams = enhanceTeams(ranking, teams);
    }

    public List<String> getRankingSuggestions() {
        return rankingSuggestions;
    }

    public boolean getDoubleGame() {
        return tourneyEvent.getEventCategory().isDouble();
    }

    public boolean getWomenGame() {
        return tourneyEvent.getEventCategory().isWomen();
    }

    public void chooseRanking(EventCategory category) {
        switch (category) {
            case AS:
                ranking = rankingHandler.getPzfsRanking().getOpenSingle();
                break;
            case OS:
                ranking = rankingHandler.getPzfsRanking().getOpenSingle();
                break;
            case AD:
                ranking = rankingHandler.getPzfsRanking().getOpenDouble();
                break;
            case OD:
                ranking = rankingHandler.getPzfsRanking().getOpenDouble();
                break;
            case WS:
                ranking = rankingHandler.getPzfsRanking().getWomenSingle();
                break;
            case WD:
                ranking = rankingHandler.getPzfsRanking().getWomenDouble();
                break;
            default:
                throw new RuntimeException("Not existing type");
        }

        // TODO add dyp (and specials)

        // TODO should be limited to ama players, when ad as
        rankingSuggestions = createRankingSuggestions(ranking);
    }

    public void removeTeam(ActionEvent event) {
        String teamName = event.getComponent().getAttributes().get("name").toString();
        log.log(Level.INFO, "User wants to remove a team: {0}", teamName);

        for (Iterator<Team> i = teams.iterator(); i.hasNext();) {
            Team team = i.next();
            if (teamName.equals(team.getName())) {
                i.remove();
            }
        }
    }

    private List<Team> enhanceTeams(Ranking ranking, List<Team> prototypes) {
        List<Team> toReturn = new ArrayList<Team>();

        for (Team team : prototypes) {
            EventPlayer player1 = createTourneyPlayerFromPrototype(team.getEventPlayerOne(), ranking);

            Team enhancedTeam = null;
            if (team.isDouble()) {
                EventPlayer player2 = createTourneyPlayerFromPrototype(team.getEventPlayerTwo(), ranking);

                enhancedTeam = new Team(player1, player2);
            } else {
                enhancedTeam = new Team(player1);
            }


            toReturn.add(enhancedTeam);
        }

        return toReturn;
    }

    private EventPlayer createTourneyPlayerFromPrototype(EventPlayer prototype, Ranking ranking) {
        String code = prototype.getCode();
        RankingPlayer player = ranking.getPlayersByCode(code);
        if (player == null) {
            return prototype;
        }

//        player.setFee(player.getFee());
        return new EventPlayer(player);
    }

    private List<String> createRankingSuggestions(Ranking r) {
        List<String> toReturn = new ArrayList<String>();

        for (RankingPlayer player : r.getPlayers()) {
            toReturn.add(player.getFullName());
        }

        return toReturn;
    }

    private EventPlayer createTourneyPlayer(String fullName) {
        List<RankingPlayer> rankedPlayersWithThisName = ranking.getPlayersByFullName(fullName);
        RankingPlayer player = null;
        // TODO many players with same name
        if (rankedPlayersWithThisName.size() > 0) {
            player = rankedPlayersWithThisName.get(0);
        } else {
            player = new RankingPlayer(fullName);
        }

        EventPlayer tourneyPlayer = new EventPlayer(player);
        tourneyPlayer.setFee(feeHandler.getDefaultFee(player, tourneyEvent.getEventCategory()));
        return tourneyPlayer;
    }

    protected long getEventId() {
        return Long.valueOf(webUtils.getRequestParameter("eventId"));
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getNewPlayer2() {
        return newPlayerName2;
    }

    public void setNewPlayer2(String newPlayer2) {
        this.newPlayerName2 = newPlayer2;
    }

    public String getNewPlayer1() {
        return newPlayerName1;
    }

    public void setNewPlayer1(String newPlayer1) {
        this.newPlayerName1 = newPlayer1;
    }

    public SelectItem[] getSeedingSuggestions() {
        return seedingSuggestions;
    }

    public SeedingType getSeedingType() {
        return seedingType;
    }

    public void setSeedingType(SeedingType seedingType) {
        this.seedingType = seedingType;
    }
}