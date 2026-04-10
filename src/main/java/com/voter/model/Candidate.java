package com.voter.model;

public class Candidate {
    private int    candidateId;
    private String name;
    private String party;
    private String symbol;
    private int    voteCount;

    public Candidate() {}

    public Candidate(int candidateId, String name, String party, String symbol, int voteCount) {
        this.candidateId = candidateId;
        this.name        = name;
        this.party       = party;
        this.symbol      = symbol;
        this.voteCount   = voteCount;
    }

    public int    getCandidateId() { return candidateId; }
    public String getName()        { return name; }
    public String getParty()       { return party; }
    public String getSymbol()      { return symbol; }
    public int    getVoteCount()   { return voteCount; }

    public void setCandidateId(int v)   { this.candidateId = v; }
    public void setName(String v)       { this.name        = v; }
    public void setParty(String v)      { this.party       = v; }
    public void setSymbol(String v)     { this.symbol      = v; }
    public void setVoteCount(int v)     { this.voteCount   = v; }
}
