package com.police.policesystem.Models;

public class Case {
    private int id;
    private String caseRefNo;
    private String dateReported;

    public Case(int id, String caseRefNo, String dateReported) {
        this.id = id;
        this.caseRefNo = caseRefNo;
        this.dateReported =dateReported;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseRefNo() {
        return caseRefNo;
    }

    public void setCaseRefNo(String caseRefNo) {
        this.caseRefNo = caseRefNo;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }
}
