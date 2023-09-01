package com.example.miniuber;

public class LostItemSupportTicket extends SupportTicket{
    private boolean itemLostIssue;
    public LostItemSupportTicket(int ticketId, String Username, String issueDescription,boolean itemLostIssue){
        super(ticketId,Username,issueDescription);
        this.itemLostIssue=itemLostIssue;
    }
    public boolean itemLostIssue(){
        return itemLostIssue;
    }
    public void setIssueWithItemLost(boolean itemLostIssue){
        this.itemLostIssue=itemLostIssue;
    }
}
