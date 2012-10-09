package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventPlayer;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TourneyManagementPoFactory {

    public TourneyManagementPo buildPo(Tourney tourney) {
        TourneyManagementPo po = new TourneyManagementPo();
        po.setTourneyName(tourney.getName());
        po.setEvents(buildEventPos(tourney.getTourneyEvents()));
        po.setWaitingGames(buildGamesPos(tourney.getWaitingGames()));
        po.setOngoingGames(buildGamesPos(tourney.getOngoingGames()));

        return po;
    }

    private List<TourneyEventPo> buildEventPos(Set<TourneyEvent> events) {
        List<TourneyEventPo> pos = new ArrayList<TourneyEventPo>();

        for (TourneyEvent event : events) {
            pos.add(buildEventPo(event));
        }

        return pos;
    }

    private TourneyEventPo buildEventPo(TourneyEvent event) {
        TourneyEventPo po = new TourneyEventPo();

        po.setId(event.getId());
        po.setDoubleGame(event.getEventCategory().isDouble());
        po.setEventCategory(event.getEventCategory());
        po.setStarted(event.isStarted());
        po.setTeams(buildTeamPos(event.getTeams()));

        return po;
    }

    private List<TeamPo> buildTeamPos(Set<Team> teams) {
        List<TeamPo> pos = new ArrayList<TeamPo>();

        for (Team team : teams) {
            pos.add(buildTeamPo(team));
        }

        return pos;
    }

    private TeamPo buildTeamPo(Team team) {
        System.out.println("build team");
        TeamPo po = new TeamPo();

        po.setId(team.getId());
        po.setTeamCode(team.getTeamCode());
        po.setPoints(team.getPoints());
        po.setMembers(buildPlayerPos(team.getEventPlayerOne(), team.getEventPlayerTwo()));

        return po;
    }

    private List<PlayerPo> buildPlayerPos(EventPlayer one, EventPlayer two) {
        List<PlayerPo> pos = new ArrayList<PlayerPo>();

        pos.add(buildPlayerPo(one));
        if (two != null) {
            pos.add(buildPlayerPo(two));
        }

        return pos;
    }

    private PlayerPo buildPlayerPo(EventPlayer member) {
        System.out.println("build player " + member.getFullName());

        PlayerPo po = new PlayerPo();

        po.setFullName(member.getFullName());
        po.setClub(member.getClub());
        po.setFee(member.getFee());
        po.setPoints(member.getPoints());
        po.setRankClass(member.getRankClass());

        return po;
    }

    private List<GamePo> buildGamesPos(List<Game> games) {
        List<GamePo> pos = new ArrayList<GamePo>();

        for (Game game : games) {
            pos.add(buildGamePo(game));
        }

        return pos;
    }

    private GamePo buildGamePo(Game game) {
        GamePo po = new GamePo();

        po.setStartDate(game.getStartDate());
        po.setEventCategory(game.getTourneyEvent().getEventCategory());
        po.setEventId(game.getTourneyEvent().getId());
        po.setGameCode(game.getGameCode().toString());
        po.setTeamHome(buildTeamPo(game.getTeamHome()));
        po.setTeamAway(buildTeamPo(game.getTeamAway()));


        return po;
    }
}
