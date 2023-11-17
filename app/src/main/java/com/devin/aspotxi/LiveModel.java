package com.devin.aspotxi;

public class LiveModel {

    String teamA;
    String teamB;
    String time;
    String place;
    String date;
    String teamAscr;
    String teamBscr;
    String LscrUrl;

    public String getLscrUrl() {
        return LscrUrl;
    }

    public void setLscrUrl(String lscrUrl) {
        LscrUrl = lscrUrl;
    }

    public LiveModel(String teamAscr, String teamBscr) {
        this.teamAscr = teamAscr;
        this.teamBscr = teamBscr;
    }

    public String getTeamAscr() {
        return teamAscr;
    }

    public void setTeamAscr(String teamAscr) {
        this.teamAscr = teamAscr;
    }

    public String getTeamBscr() {
        return teamBscr;
    }

    public void setTeamBscr(String teamBscr) {
        this.teamBscr = teamBscr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchint() {
        return matchint;
    }

    public void setMatchint(String matchint) {
        this.matchint = matchint;
    }

    String matchint;

    public String getGtitle() {
        return gtitle;
    }

    public void setGtitle(String gtitle) {
        this.gtitle = gtitle;
    }

    String gtitle;

    public LiveModel(String gtitle) {
        this.gtitle = gtitle;
    }

    LiveModel(){

       }
    public LiveModel(String teamA, String teamB, String time, String place) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.time = time;
        this.place = place;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
