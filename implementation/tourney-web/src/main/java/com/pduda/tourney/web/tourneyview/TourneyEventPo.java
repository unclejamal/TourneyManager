package com.pduda.tourney.web.tourneyview;

public class TourneyEventPo {

    private String eventCategory;
    private String standingsTextReport;
    private String wbrAsJson;
    private String lbrAsJson;
    private String fin1AsJson;
    private String fin2AsJson;
    private boolean started;

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getStandingsTextReport() {
        return standingsTextReport;
    }

    public void setStandingsTextReport(String standingsTextReport) {
        this.standingsTextReport = standingsTextReport;
    }

    public String getWbrAsJson() {
        return wbrAsJson;
    }

    public void setWbrAsJson(String wbrAsJson) {
        this.wbrAsJson = wbrAsJson;
    }

    public String getLbrAsJson() {
        return lbrAsJson;
    }

    public void setLbrAsJson(String lbrAsJson) {
        this.lbrAsJson = lbrAsJson;
    }

    public String getFin1AsJson() {
        return fin1AsJson;
    }

    public void setFin1AsJson(String fin1AsJson) {
        this.fin1AsJson = fin1AsJson;
    }

    public String getFin2AsJson() {
        return fin2AsJson;
    }

    public void setFin2AsJson(String fin2AsJson) {
        this.fin2AsJson = fin2AsJson;
    }
}
