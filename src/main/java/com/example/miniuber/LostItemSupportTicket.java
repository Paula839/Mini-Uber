package com.example.miniuber;

public class LostItemSupportTicket extends SupportTicket{
    private boolean itemLostIssue;
    public LostItemSupportTicket( String issueDescription){
        super(issueDescription);
    }
    public boolean itemLostIssue(){
        return itemLostIssue;
    }
    public void setIssueWithItemLost(boolean itemLostIssue){
        this.itemLostIssue=itemLostIssue;
    }
}
