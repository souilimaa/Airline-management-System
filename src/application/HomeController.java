package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;

public class HomeController {
	  @FXML
	    private Menu Systeme;
	  @FXML
	    private Label label;
	  

	    @FXML
	    private Menu Ticket;
	  @FXML
	    void goToAjouterVoyageur(ActionEvent event) {
		  try {
			FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("AjtVoyageur.fxml"));
			Parent root1=(Parent)fxmlload.load();
			Stage stage=new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
	    }
	  catch(Exception e) {
		  
	  }
	  }
	    @FXML
	    void goToModifierVoyageur(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("UpdateVoyageur.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    
	    @FXML
	    void goToAjoutVol(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("AjouteVol.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    
	    @FXML
	    void goToModifierVol(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("ModifierVol.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goToReserverTicket(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("ReservationTicket.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goToTicketDetails(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("TicketDetails.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goListerVolparSource(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("ListeVol_Source_Dep.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goTolisterVolparDate(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("ListeVol_dateDepart.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goListerTicket(ActionEvent event) {
	    	try {
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("ListeTicket_dateDepart.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
	    @FXML
	    void goToLogin(MouseEvent event) {
	    	try {
	    	 Stage currentStage=(Stage) label.getScene().getWindow();
	    	 currentStage.close();

				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("Login.fxml"));
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
		    }
		  catch(Exception e) {
			  
		  }
	    }
    }

	
