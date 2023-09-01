package com.example.miniuber;

public class TicketNotificationObserver implements TicketObserver{
    private String notification;

    @Override
    public void notifyUpdate(SupportTicket ticket) {
        if (ticket.isResolved()) {
            notification = "Ticket " + ticket.getTicketId() + " has been resolved.";///UI
        } else {
            notification = "Ticket " + ticket.getTicketId() + " is still open.";///UI
        }
         sendNotificationEmail();
    } private void sendNotificationEmail() {
        System.out.println("Sending notification email: " + notification);/////UI msg b2a ####
    }
}
