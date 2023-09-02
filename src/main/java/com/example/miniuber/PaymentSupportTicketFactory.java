package com.example.miniuber;

public class PaymentSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new PaymentSupportTicket(issueDescription);
    }
}
