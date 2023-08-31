package com.example.miniuber;
public class UisupportTicket{

        }
abstract class SupportTicket implements Ticket {
    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;

    public SupportTicket(int ticketId, String Username, String issueDescription) {
        this.ticketId = ticketId;
        this.Username = Username;
        this.issueDescription = issueDescription;
        this.resolved = false;
    }

    @Override
    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public String getIssueDescription() {
        return issueDescription;
    }

    @Override
    public boolean isResolved() {
        return resolved;
    }
}
