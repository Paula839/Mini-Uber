package com.example.miniuber;

public class PaymentSupportTicket extends SupportTicket{
    private boolean issueWithChange;
    private boolean issueWithUpdatedFares;
    private boolean issueWithCreditFares;
    private boolean issueWithOvercharges;
    PaymentSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithChange,boolean issueWithUpdatedFares,boolean issueWithCreditFares,boolean issueWithOvercharges){
        super(ticketId, Username, issueDescription);
        this.issueWithChange=issueWithChange;
        this.issueWithCreditFares=issueWithCreditFares;
        this.issueWithUpdatedFares=issueWithUpdatedFares;
        this.issueWithOvercharges=issueWithOvercharges;
    }
    public boolean ChangeIssue(){
        return issueWithChange;
    }
    public void setIssueWithChange(boolean issueWithChange){
        this.issueWithChange=issueWithChange;
    }
    public boolean CreditIssue(){
        return issueWithCreditFares;
    }
    public void setIssueWithCreditFares(boolean issueWithCreditFares){
        this.issueWithCreditFares=issueWithCreditFares;
    }
    public boolean UpdatedFaresIssue(){
        return issueWithUpdatedFares;
    }
    public void setIssueWithUpdatedFares(boolean issueWithUpdatedFares){
        this.issueWithUpdatedFares=issueWithUpdatedFares;
    }
    public boolean OverchargesIssue(){
        return issueWithOvercharges;
    }
    public void setIssueWithOvercharges(boolean issueWithOvercharges){
        this.issueWithOvercharges=issueWithOvercharges;
    }
}
