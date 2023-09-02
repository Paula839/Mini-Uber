package com.example.miniuber;

public class LostSupportTicketFactory implements SupportTicketFactory {
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new LostItemSupportTicket(issueDescription);
    }
}
