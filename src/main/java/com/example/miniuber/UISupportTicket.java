package com.example.miniuber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;


public class UISupportTicket extends DefaultSettings {
    public static String sql;
    SupportTicketFactory supportTicketFactory;

    public TextField lostText, anotherProblemText;
    public static String saveLostText, saveAnotherProblemText;

    public ImageView lostImage, driverImage, paymentImage, anotherImage;
    @FXML
    private Label didNotChooseHelp, EmptyTextBox1, EmptyTextBox11;

    public void onLostClick() {
        if(lostImage.getOpacity() == 0) {
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
            AboutProblemRead aboutProblemRead = new AboutProblemRead(supportTicket);
            aboutProblemRead.read();
            Database.store(sql);
        }

        else if(anotherImage.getOpacity() == 1) {
            supportTicketFactory = new AnotherSupportTicketFactory();
            if(anotherProblemText.getLength() <= 2){
                EmptyTextBox1.setText("Please specify description first");
            }

            else {
                SupportTicket supportTicket = supportTicketFactory.createSupportTicket(
                        anotherProblemText.getText());
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


    public void OnPaymentComplaintSubmitClick(ActionEvent actionEvent) {
    }

    public void OnSubmitDriverComplaintClick(ActionEvent actionEvent) {
    }
}
 abstract class  SupportTicket implements Ticket {

    private int ticketId;
    private String Username;
    private String issueDescription;
    private boolean resolved;

    public SupportTicket( String issueDescription) {

        this.issueDescription = issueDescription;
        this.resolved = false;
    }


//    @Override
//    public int getTicketId() {
//        return ticketId;
//    }
//
//    @Override
//    public String getUsername() {
//        return Username;
//    }

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
    //    DriverSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithAC,boolean issueWithDestination,boolean issueWithStoppage,boolean issueWithSeats){
//        super(ticketId, Username, issueDescription);
//        this.issueWithAC=issueWithAC;
//        this.issueWithDestination=issueWithDestination;
//        this.issueWithStoppage=issueWithStoppage;
//        this.issueWithSeats=issueWithSeats;
//    }
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
    //    PaymentSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithChange,boolean issueWithUpdatedFares,boolean issueWithCreditFares,boolean issueWithOvercharges){
//        super(ticketId, Username, issueDescription);
//        this.issueWithChange=issueWithChange;
//        this.issueWithCreditFares=issueWithCreditFares;
//        this.issueWithUpdatedFares=issueWithUpdatedFares;
//        this.issueWithOvercharges=issueWithOvercharges;
//    }
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
    private boolean issueWithBrokenCar;
    private boolean issueWithChangedDriver;
    private boolean issueWithDriverDiscrimination;
    //    TechnicalSupportTicket(int ticketId, String Username, String issueDescription,boolean issueWithBrokenCar,boolean issueWithChangedDriver,boolean issueWithDriverDiscrimination){
//        super(ticketId, Username, issueDescription);
//        this.issueWithBrokenCar=issueWithBrokenCar;
//        this.issueWithChangedDriver=issueWithChangedDriver;
//        this.issueWithDriverDiscrimination=issueWithDriverDiscrimination;
//    }
    TechnicalSupportTicket(String issueDescription){
        super(issueDescription);

    }
    public boolean BrokenCarIssue(){
        return issueWithBrokenCar;
    }
    public void setIssueWithBrokenCar(boolean issueWithBrokenCar){
        this.issueWithBrokenCar=issueWithBrokenCar;
    }public boolean ChangedDriverIssue(){
        return issueWithChangedDriver;
    }
    public void setIssueWithChangedDriver(boolean issueWithChangedDriver){
        this.issueWithChangedDriver=issueWithChangedDriver;
    }public boolean DriverDiscriminationIssue(){
        return issueWithDriverDiscrimination;
    }
    public void setIssueWithDriverDiscrimination(boolean issueWithDriverDiscrimination){
        this.issueWithDriverDiscrimination=issueWithDriverDiscrimination;
    }
}

interface Ticket {
    //    int getTicketId();
//    String getUsername();
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

