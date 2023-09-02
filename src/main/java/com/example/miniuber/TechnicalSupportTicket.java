package com.example.miniuber;

public class TechnicalSupportTicket extends SupportTicket{
    private boolean issueWithBrokenCar;
    private boolean issueWithChangedDriver;
    private boolean issueWithDriverDiscrimination;
//    TechnicalSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithBrokenCar,boolean issueWithChangedDriver,boolean issueWithDriverDiscrimination){
//        super(ticketId, Username, issueDescription);
//        this.issueWithBrokenCar=issueWithBrokenCar;
//        this.issueWithChangedDriver=issueWithChangedDriver;
//        this.issueWithDriverDiscrimination=issueWithDriverDiscrimination;
//    }
    TechnicalSupportTicket(String issueDescription){
        super(issueDescription);

    }
    public boolean BrokenCarIssue(){
        return issueWithBrokenCar;
    }
    public void setIssueWithBrokenCar(boolean issueWithBrokenCar){
        this.issueWithBrokenCar=issueWithBrokenCar;
    }public boolean ChangedDriverIssue(){
        return issueWithChangedDriver;
    }
    public void setIssueWithChangedDriver(boolean issueWithChangedDriver){
        this.issueWithChangedDriver=issueWithChangedDriver;
    }public boolean DriverDiscriminationIssue(){
        return issueWithDriverDiscrimination;
    }
    public void setIssueWithDriverDiscrimination(boolean issueWithDriverDiscrimination){
        this.issueWithDriverDiscrimination=issueWithDriverDiscrimination;
    }
}
