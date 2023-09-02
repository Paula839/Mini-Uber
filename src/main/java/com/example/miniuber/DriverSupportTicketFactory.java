package com.example.miniuber;

public class DriverSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new DriverSupportTicket(issueDescription);
    }
}
