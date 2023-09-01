package com.example.miniuber;

public interface Ticket {
    int getTicketId();
    String getUsername();
    String getIssueDescription();
    boolean isResolved();
}
