package com.pduda.tourney.web;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.TournamentCategory;
import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.ranking.Rankings;
import com.pduda.tourney.domain.service.TournamentHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

@Named("tourneyCreation")
@Scope("view")
public class TourneyCreationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyCreationBean.class.getClass().getName());
    @Inject
    private TournamentHandler tournamentHandler;
    @Inject
    private Rankings rankings;
    private Ranking ranking;
    private List<Team> teams = new ArrayList<Team>();
    @Size(min = 3, max = 40)
    private String newPlayerName1;
    @Size(min = 3, max = 40)
    private String newPlayerName2;
    @Size(min = 3, max = 40)
    private String tourneyName;
    private TournamentCategory category = TournamentCategory.OS;
    private List<String> rankingSuggestions = new ArrayList<String>();

    @PostConstruct
    public void init() {
        log.info("Tournament Creation Bean: Post Construct");
        this.tourneyName = "Tourney " + (tournamentHandler.getTournaments().size() + 1);
    }

    public String createTourney() {
        log.log(Level.INFO, "User wants to create a {0} tourney \"{0}\" for: {1}", new Object[]{category, tourneyName, teams});
        int tourneyId = tournamentHandler.createTournament(category, tourneyName, teams);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("manageTourney.html?id=" + tourneyId);
        } catch (IOException ex) {
            System.out.println("dupsko");
        }
        return "tourney";
    }

    public void addTeam(ActionEvent event) {
        log.log(Level.INFO, "User wants to add a team {0}, {1}", new Object[]{newPlayerName1, newPlayerName2});

        Team team = null;
        Player newPlayer1 = createPlayer(newPlayerName1);
        if ((newPlayerName2 != null) && (!newPlayerName2.isEmpty())) {
            Player newPlayer2 = createPlayer(newPlayerName2);
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

    public boolean getDouble() {
        return category.isDouble();
    }

    public boolean getWomen() {
        return category.isWomen();
    }

    public void chooseCategory(ActionEvent event) {
        String cat = (String) event.getComponent().getAttributes().get("cat");
        log.log(Level.INFO, "User has chosen tourney category " + cat);
        switch (cat) {
            case "os":
                category = TournamentCategory.OS;
                ranking = rankings.getOpenSingle();
                break;
            case "od":
                category = TournamentCategory.OD;
                ranking = rankings.getOpenDouble();
                break;
            case "ws":
                category = TournamentCategory.WS;
                ranking = rankings.getWomenSingle();
                break;
            case "wd":
                category = TournamentCategory.WD;
                ranking = rankings.getWomenDouble();
                break;
            default:
                throw new RuntimeException("Not existing type");
        }

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
            String code1 = team.getMembers().get(0).getCode();
            Player player1 = ranking.getPlayersByCode(code1);
            if (player1 == null) {
                player1 = team.getMembers().get(0);
            }

            Team enhancedTeam = null;
            if (team.isDouble()) {
                String code2 = team.getMembers().get(1).getCode();
                Player player2 = ranking.getPlayersByCode(code2);
                if (player2 == null) {
                    player2 = team.getMembers().get(1);
                }
                enhancedTeam = new Team(player1, player2);
            } else {
                enhancedTeam = new Team(player1);
            }

            toReturn.add(enhancedTeam);
        }

        return toReturn;
    }

    private List<String> createRankingSuggestions(Ranking r) {
        List<String> toReturn = new ArrayList<String>();

        for (Player player : r.getPlayers()) {
            toReturn.add(player.getFullName());
        };

        return toReturn;
    }

    private Player createPlayer(String fullName) {
        List<Player> rankedPlayersWithThisName = ranking.getPlayersByFullName(fullName);
        Player player = null;
        // TODO many players with same name
        if (rankedPlayersWithThisName.size() > 0) {
            player = rankedPlayersWithThisName.get(0);
        } else {
            player = new Player(fullName);
        }
        return player;
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

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public TournamentCategory getCategory() {
        return category;
    }

    public void setCategory(TournamentCategory category) {
        this.category = category;
    }
//
//    private TopologyDataModel topology;
//
//    public TopologyDataModel getTopology() {
//        log.info("Getting topology");
//        return topology;
//    }
//  public void upload(ActionEvent event) {
//    Long actionNetworkElement = (Long) event.getComponent().getAttributes().get("actionNetworkElement");
//    log.info("User is starting an upload for gid: " + actionNetworkElement);
//
//    taskHandler.upload(actionNetworkElement);
//    getTopologyFromHibernate();
//  }
//
//  public void refreshTopology(ActionEvent event) {
//    getTopologyFromHibernate();
//  }
//
//  public void populateDatabase(ActionEvent event) {
//    networkElementRepo.createDummyNetworkElements();
//    getTopologyFromHibernate();
//
//  }
//
//  private void getTopologyFromHibernate() {
//    List<NetworkElement> list = networkElementRepo.findAll();
//    log.info("Topology is: " + list);
//    topology = new TopologyDataModel(list);
//  }
}
