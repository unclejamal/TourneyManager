package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class FullGamesReport {

    private String tourneyName;
    private List<PartialGamesReport> partialReports = new ArrayList<PartialGamesReport>();

    public List<PartialGamesReport> getPartialReports() {
        return partialReports;
    }

    public void setPartialReports(List<PartialGamesReport> reports) {
        this.partialReports = reports;
    }

    public void addPartialReport(PartialGamesReport partial) {
        this.partialReports.add(partial);
    }

    public String getName() {
        return tourneyName;
    }

    public void setName(String name) {
        this.tourneyName = name;
    }

    public PartialGamesReport getPartialReport(String name) {
        for (PartialGamesReport partial : partialReports) {
            if (name.equals(partial.getName())) {
                return partial;
            }
        }

        return null;
    }
}
