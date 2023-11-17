package com.devin.aspotxi;

public class Model {

    String teamA;
    String teamB;
    String time;
    String place;
    String date;
    String teamAscr;
    String teamBscr;
    String LscrUrl;
    String earnU;

    public String getEarnU() {
        return earnU;
    }

    public void setEarnU(String earnU) {
        this.earnU = earnU;
    }

    public String getLscrUrl() {
        return LscrUrl;
    }

    public void setLscrUrl(String lscrUrl) {
        LscrUrl = lscrUrl;
    }

    public Model(String teamAscr, String teamBscr) {
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

    public Model(String gtitle) {
        this.gtitle = gtitle;
    }

    Model(){

       }
    public Model(String teamA, String teamB, String time, String place) {
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
