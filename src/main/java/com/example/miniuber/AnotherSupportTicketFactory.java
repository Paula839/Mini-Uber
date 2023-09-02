package com.example.miniuber;

public class AnotherSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new TechnicalSupportTicket(issueDescription);
    }
}
