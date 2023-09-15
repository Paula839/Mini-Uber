package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UISupportTicket extends Page {
    public static String sql;
    public Label notifyText;
    SupportTicketFactory supportTicketFactory;

    public TextField lostText, anotherProblemText;
    public static String saveLostText, saveAnotherProblemText;

    public ImageView lostImage, driverImage, paymentImage, anotherImage;
    @FXML
    private Label didNotChooseHelp, EmptyTextBox1, EmptyTextBox11;

    public void onLostClick() {
        if (lostImage.getOpacity() == 0) {
            lostText.setOpacity(1);
            lostImage.setOpacity(1);
            anotherImage.setOpacity(0);
            driverImage.setOpacity(0);
            paymentImage.setOpacity(0);
            anotherProblemText.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");
        }

        else {
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");
        }
    }

    public void onDriverClick() {

        if(driverImage.getOpacity() == 0) {
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(0);
            driverImage.setOpacity(1);
            paymentImage.setOpacity(0);
            anotherProblemText.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");
        }

        else {
            driverImage.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");

        }
    }

    public void onAnotherClick() {
        if(anotherImage.getOpacity() == 0) {
            anotherProblemText.setOpacity(1);
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(1);
            driverImage.setOpacity(0);
            paymentImage.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");
        }

        else {
            anotherImage.setOpacity(0);
            anotherProblemText.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");

        }
    }

    public void onPaymentClick() {
        if(paymentImage.getOpacity() == 0) {
            lostText.setOpacity(0);
            lostImage.setOpacity(0);
            anotherImage.setOpacity(0);
            driverImage.setOpacity(0);
            paymentImage.setOpacity(1);
            anotherProblemText.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");
        }

        else {
            paymentImage.setOpacity(0);
            didNotChooseHelp.setText("");
            EmptyTextBox1.setText("");
            EmptyTextBox11.setText("");

        }
    }

    public void onGoBackClick(ActionEvent page) throws IOException {
        goTo(page, savePage);
    }

    public void onAboutUsClick(ActionEvent page) throws IOException {
        goTo(page, "AboutUs");
    }

    public void onLogOutClick(ActionEvent page) throws IOException {
        goTo(page, "WelcomePage");
    }

    public void onButtonClick(ActionEvent page) throws IOException, SQLException {
        boolean isSubmitted = false;
        if(driverImage.getOpacity() == 1) {
            supportTicketFactory = new DriverSupportTicketFactory();
            SupportTicket supportTicket = supportTicketFactory.createSupportTicket(
                    "About the driver");
            TicketObserver observer = new NotificationObserver(notifyText);
            observer.notify(supportTicket);
            AboutProblemRead aboutProblemRead = new AboutProblemRead(supportTicket);
            aboutProblemRead.read();
            Database.store(sql);

        }

        else if(anotherImage.getOpacity() == 1) {
            if(anotherProblemText.getLength() <= 2) {
                EmptyTextBox1.setText("Please specify description first");
            }

            else {
                SupportTicket supportTicket = supportTicketFactory.createSupportTicket(
                        anotherProblemText.getText());
                supportTicketFactory = new AnotherSupportTicketFactory();
                TicketObserver observer = new NotificationObserver(notifyText);
                observer.notify(supportTicket);
                AnotherProblemRead anotherProblemRead = new AnotherProblemRead();
                saveAnotherProblemText = anotherProblemText.getText();
                anotherProblemRead.read();
                Database.store(sql);
            }
        }
        else if(paymentImage.getOpacity() == 1) {
            supportTicketFactory = new PaymentSupportTicketFactory();
            SupportTicket supportTicket = supportTicketFactory.createSupportTicket(
                    "About The Payment");
            TicketObserver observer = new NotificationObserver(notifyText);
            observer.notify(supportTicket);
            AboutProblemRead aboutProblemRead = new AboutProblemRead(supportTicket);
            aboutProblemRead.read();
            Database.store(sql);
        }
        else if(lostImage.getOpacity() == 1) {
            supportTicketFactory = new LostSupportTicketFactory();
            if(lostText.getLength() <= 2){
                EmptyTextBox11.setText("Please specify description first");
            }
            else {
                SupportTicket supportTicket = supportTicketFactory.createSupportTicket
                        (
                        lostText.getText()
                        );
                TicketObserver observer = new NotificationObserver(notifyText);
                observer.notify(supportTicket);
                saveLostText = lostText.getText();
                LostRead lostRead = new LostRead();
                lostRead.read();
                Database.store(sql);

            }
        }
        else{
            didNotChooseHelp.setText("Please specify how can we support you");
        }
    }

}
 abstract class SupportTicket implements Ticket {

    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;
    public SupportTicket(String issueDescription) {

        this.issueDescription = issueDescription;
        this.resolved = false;
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



class AnotherSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new TechnicalSupportTicket(issueDescription);
    }
}

class DriverSupportTicket extends SupportTicket {
    private boolean issueWithAC;
    private boolean issueWithDestination;
    private boolean issueWithStoppage;
    private boolean issueWithSeats;
    DriverSupportTicket(String issueDescription){
        super(issueDescription);

    }

    public boolean ACIssue(){
        return issueWithAC;
    }
    public void setIssueWithAC(boolean issueWithAC){
        this.issueWithAC=issueWithAC;
    }
    public boolean DestinationIssue(){
        return issueWithDestination;
    }
    public void setIssueWithDestination(boolean issueWithDestination){
        this.issueWithDestination=issueWithDestination;
    }
    public boolean StoppageIssue(){
        return issueWithStoppage;
    }
    public void setIssueWithStoppage(boolean issueWithStoppage){
        this.issueWithStoppage=issueWithStoppage;
    }
    public boolean SeatsIssue(){
        return issueWithSeats;
    }
    public void setIssueWithSeats(boolean issueWithSeats){
        this.issueWithSeats=issueWithSeats;
    }
}

class DriverSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new DriverSupportTicket(issueDescription);
    }
}
class LostItemSupportTicket extends SupportTicket {
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
class LostSupportTicketFactory implements SupportTicketFactory {
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new LostItemSupportTicket(issueDescription);
    }
}
class PaymentSupportTicket extends SupportTicket {
    private boolean issueWithChange;
    private boolean issueWithUpdatedFares;
    private boolean issueWithCreditFares;
    private boolean issueWithOvercharges;
    PaymentSupportTicket(String issueDescription){
        super(issueDescription);

    }
    public boolean ChangeIssue(){
        return issueWithChange;
    }
    public void setIssueWithChange(boolean issueWithChange){
        this.issueWithChange=issueWithChange;
    }
    public boolean CreditIssue(){
        return issueWithCreditFares;
    }
    public void setIssueWithCreditFares(boolean issueWithCreditFares){
        this.issueWithCreditFares=issueWithCreditFares;
    }
    public boolean UpdatedFaresIssue(){
        return issueWithUpdatedFares;
    }
    public void setIssueWithUpdatedFares(boolean issueWithUpdatedFares){
        this.issueWithUpdatedFares=issueWithUpdatedFares;
    }
    public boolean OverchargesIssue(){
        return issueWithOvercharges;
    }
    public void setIssueWithOvercharges(boolean issueWithOvercharges){
        this.issueWithOvercharges=issueWithOvercharges;
    }
}

class PaymentSupportTicketFactory implements SupportTicketFactory{
    @Override
    public SupportTicket createSupportTicket(String issueDescription) {
        return new PaymentSupportTicket(issueDescription);
    }
}
interface SupportTicketFactory {
    SupportTicket createSupportTicket(String issueDescription);
}

class TechnicalSupportTicket extends SupportTicket {
    TechnicalSupportTicket(String issueDescription) {
        super(issueDescription);

    }
}

interface Ticket {

    String getIssueDescription();
    boolean isResolved();
}

class LostRead implements Read {
    @Override
    public void read() {
        UISupportTicket.sql = "INSERT INTO supporttickets VALUES( " +
                "\"" + UILogin.saveUsername + "\" , " +
                "\"" + UISupportTicket.saveLostText + "\"  " +
                ")";
    }
}
class AnotherProblemRead implements Read {
    @Override
    public void read() {
        UISupportTicket.sql = "INSERT INTO supporttickets VALUES( " +
                "\"" + UILogin.saveUsername + "\" , " +
                "\"" + UISupportTicket.saveAnotherProblemText + "\"  " +
                ")";
    }
}

class AboutProblemRead implements Read {
    SupportTicket supportTicket;
    AboutProblemRead(SupportTicket supportTicket) {
        this.supportTicket = supportTicket;
    }
    @Override
    public void read() {
        UISupportTicket.sql = "INSERT INTO supporttickets VALUES( " +
                "\"" + UILogin.saveUsername + "\" , " +
                "\"" + supportTicket.getIssueDescription() + "\"  " +
                ")";
    }
}

interface TicketObserver {
    void notify(Ticket ticket);
}
class NotificationObserver implements TicketObserver {
    Label notifyText;
    NotificationObserver(Label notifyText) {
        this.notifyText = notifyText;
    }
    @Override
    public void notify(Ticket ticket) {
        notifyText.setText("Ticket has been submit!");
    }
}