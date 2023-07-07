package com.police.policesystem.Models;

public class Case {
    String id,openCases,closedCases,otherCases,accidentCases,p3Cases;

    public Case(String id, String openCases, String closedCases, String otherCases, String accidentCases, String p3Cases) {
        this.id = id;
        this.openCases = openCases;
        this.closedCases = closedCases;
        this.otherCases = otherCases;
        this.accidentCases = accidentCases;
        this.p3Cases = p3Cases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getOpenCases() {
        return openCases;
    }

    public void setOpenCases(String openCases) {
        this.openCases = openCases;
    }

    public String getClosedCases() {
        return closedCases;
    }

    public void setClosedCases(String closedCases) {
        this.closedCases = closedCases;
    }

    public String getOtherCases() {
        return otherCases;
    }

    public void setOtherCases(String otherCases) {
        this.otherCases = otherCases;
    }

    public String getAccidentCases() {
        return accidentCases;
    }

    public void setAccidentCases(String accidentCases) {
        this.accidentCases = accidentCases;
    }

    public String getP3Cases() {
        return p3Cases;
    }

    public void setP3Cases(String p3Cases) {
        this.p3Cases = p3Cases;
    }
}

