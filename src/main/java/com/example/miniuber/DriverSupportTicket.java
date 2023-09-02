package com.example.miniuber;

public class DriverSupportTicket extends SupportTicket{
    private boolean issueWithAC;
    private boolean issueWithDestination;
    private boolean issueWithStoppage;
    private boolean issueWithSeats;
//    DriverSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithAC,boolean issueWithDestination,boolean issueWithStoppage,boolean issueWithSeats){
//        super(ticketId, Username, issueDescription);
//        this.issueWithAC=issueWithAC;
//        this.issueWithDestination=issueWithDestination;
//        this.issueWithStoppage=issueWithStoppage;
//        this.issueWithSeats=issueWithSeats;
//    }
    DriverSupportTicket(String issueDescription){
        super(issueDescription);

    }

    public boolean ACIssue(){
        return issueWithAC;
    }
    public void setIssueWithAC(boolean issueWithAC){
        this.issueWithAC=issueWithAC;
    }
    public boolean DestinationIssue(){
        return issueWithDestination;
    }
    public void setIssueWithDestination(boolean issueWithDestination){
        this.issueWithDestination=issueWithDestination;
    }
    public boolean StoppageIssue(){
        return issueWithStoppage;
    }
    public void setIssueWithStoppage(boolean issueWithStoppage){
        this.issueWithStoppage=issueWithStoppage;
    }
    public boolean SeatsIssue(){
        return issueWithSeats;
    }
    public void setIssueWithSeats(boolean issueWithSeats){
        this.issueWithSeats=issueWithSeats;
    }
}
