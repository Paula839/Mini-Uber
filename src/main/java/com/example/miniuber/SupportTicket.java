package com.example.miniuber;
import java.util.ArrayList;
import java.util.List;

interface TicketObserver {
    void notifyUpdate(SupportTicket ticket);
}
abstract class SupportTicket implements Ticket {
    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;
    private List<TicketObserver> observers;
    public SupportTicket(int ticketId, String Username, String issueDescription) {
        this.ticketId = ticketId;
        this.Username = Username;
        this.issueDescription = issueDescription;
        this.resolved = false;
        this.observers = new ArrayList<>();
    }
    public void addObserver(TicketObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TicketObserver observer) {
        observers.remove(observer);
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
        notifyObservers();
    }

    private void notifyObservers() {
        for (TicketObserver observer : observers) {
            observer.notifyUpdate(this);
        }
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
