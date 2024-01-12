package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ReservationTicketController implements Initializable  {
	
	
    @FXML
    private TextField CardNum;
    @FXML
    private DatePicker dateCard;
    @FXML
    private ComboBox<String> cardType;
    @FXML
    private ComboBox<String> volId;

    @FXML
    private TextField FraisVol;

    @FXML
    private TextField VoyageurId;

    @FXML
    private RadioButton btnBancaire;

    @FXML
    private RadioButton btnCash;

    @FXML
    private ToggleGroup paiement;

    @FXML
    private Pane pain;

    @FXML
    private Pane pain2;

    @FXML
    private Pane pain3;
    @FXML
    private Button btnReserv;
    @FXML
   private AnchorPane all;
    int NumTick;
    int varVolId;
    @FXML
    void ReserverTicket(ActionEvent event) {
    	
    	Connection connection=Connector();
    	if(!(VoyageurId.getText().equals("")&& volId.getSelectionModel().isEmpty()&&FraisVol.getText().equals(""))&&btnCash.isSelected()) {
    		
    		
    		
    		String query="INSERT INTO `ticket`(`TicketNum`, `PassagerID`, `vol_id`, `vol_frais`, `TypePaiement`, `DateTicket`) VALUES (null,?,?,?,?,Now())";
    	PreparedStatement ps;
		 try {
			 String query0="select PassagerID from passager where PassagerID=?";
	    		PreparedStatement ps0;
	    			ps0=connection.prepareStatement(query0);
	    			ps0.setInt(1,Integer.valueOf(VoyageurId.getText()));
	    		ResultSet res0=ps0.executeQuery();
	    		if(!res0.next()) {
	    			 Alert alert=new Alert(AlertType.WARNING);
	 				alert.setHeaderText("Ce Voyageur Id n'existe pas!!");
	 				alert.show(); 
	    		}
	    		else {
			 ps=connection.prepareStatement(query);
			 ps.setInt(1, Integer.valueOf(VoyageurId.getText()));
			 ps.setInt(2,varVolId);
			ps.setFloat(3,Float.valueOf(FraisVol.getText()));
			RadioButton paiementType = (RadioButton) paiement.getSelectedToggle(); 
			ps.setString(4,paiementType.getText());
			 ps.execute();
			 String query2="select TicketNum from ticket order by TicketNum desc LIMIT 1";   
			 PreparedStatement ps2=connection.prepareStatement(query2);
			 ResultSet result=ps2.executeQuery();	
			 while(result.next()) {
				 NumTick=result.getInt("TicketNum");
			 }
		
			 Alert alert=new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Le Numéro de Votre Ticket est : "+String.valueOf(NumTick));
				alert.show(); 
	    		}
		 }catch(SQLException e) {
			e.printStackTrace();

		 }
    	}
    	else if(!(volId.getSelectionModel().isEmpty())&&!(VoyageurId.getText().equals(""))&&!(FraisVol.getText().equals(""))&&btnBancaire.isSelected()&&!(CardNum.getText().equals(""))&&!(dateCard.getValue()==null)&&cardType.getSelectionModel().isEmpty()==false)
    	{
    		String query="INSERT INTO `ticket`(`TicketNum`, `PassagerID`, `vol_id`, `vol_frais`, `TypePaiement`, `DateTicket`) VALUES (null,?,?,?,?,Now())";
        	PreparedStatement ps;
    		 try {
    			 String query0="select PassagerID from passager where PassagerID=?";
    	    		PreparedStatement ps0;
    	    			ps0=connection.prepareStatement(query0);
    	    			ps0.setInt(1,Integer.valueOf(VoyageurId.getText()));
    	    		ResultSet res0=ps0.executeQuery();
    	    		if(!res0.next()) {
    	    			 Alert alert=new Alert(AlertType.WARNING);
    	 				alert.setHeaderText("Ce Voyageur Id n'existe pas!!");
    	 				alert.show(); 
    	    		} 
    	    		else {
    			 ps=connection.prepareStatement(query);
    			 ps.setInt(1, Integer.valueOf(VoyageurId.getText()));
    			 ps.setInt(2,varVolId);
    			ps.setFloat(3,Float.valueOf(FraisVol.getText()));
    			RadioButton paiementType = (RadioButton) paiement.getSelectedToggle(); 
    			ps.setString(4,paiementType.getText());
    			 ps.execute();
    			 String query2="select TicketNum from ticket order by TicketNum desc LIMIT 1";   
    			 PreparedStatement ps2=connection.prepareStatement(query2);
    			 ResultSet result=ps2.executeQuery();	
    			 while(result.next()) {
    				 NumTick=result.getInt("TicketNum");
    				
    			 }
    			 String query3="INSERT INTO `paiement`(`TicketNum`, `CarteNum`, `dateExpiration`, `CarteType`) VALUES (?,?,?,?)";
    			 PreparedStatement ps3=connection.prepareStatement(query3);
    			 ps3.setInt(1,NumTick);
    			 ps3.setLong(2,Long.valueOf(CardNum.getText()));
    			 ps3.setDate(3,Date.valueOf(dateCard.getValue()));
    			 ps3.setString(4,cardType.getValue());
    			 ps3.execute();
    			 Alert alert=new Alert(AlertType.INFORMATION);
    				alert.setHeaderText("Le Numéro de Votre Ticket est : "+String.valueOf(NumTick));
    				alert.show();
    	    		}
    		 }catch(SQLException e) {
    			e.printStackTrace();

    		 }
    			 
    		 
    	}
    	
    		else {
    	
    		Alert alert=new Alert(AlertType.WARNING);
    		alert.setHeaderText("Tous les Champs sont Obligatoires!!");
    		alert.setContentText(null);
    		alert.show();
    	}


    }
    
    
    
    
    public void showPaiementBank() {
    	pain.setVisible(true);
    	pain.managedProperty().bind(pain.visibleProperty());
    	pain2.setVisible(true);
    	pain2.managedProperty().bind(pain2.visibleProperty());
    	pain3.setVisible(true);
    	pain3.managedProperty().bind(pain3.visibleProperty());
    	btnReserv.setLayoutY(402);
    	Stage stage = (Stage) all.getScene().getWindow();
    	stage.setHeight(683);
    }
    
    
     
    
    public void HidePaiementBank() {
    	pain.setVisible(false);
    	pain.managedProperty().bind(pain.visibleProperty());
    	pain2.setVisible(false);
    	pain2.managedProperty().bind(pain2.visibleProperty());
    	pain3.setVisible(false);
    	pain3.managedProperty().bind(pain3.visibleProperty());
    	btnReserv.setLayoutY(226);
    	Stage stage = (Stage) all.getScene().getWindow();
    	stage.setHeight(520);
    }
    
    
    
    public Connection Connector() {
		String server_url="localhost";
		String db_name="airliners";
		String db_username="root";
		String db_password="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://"+server_url+"/"+db_name,db_username,db_password);
		return connection;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
    
    
    
    public void getFraisVol() {
    	Connection connection=Connector();
    	try {
    	String query="select vol_frais from vol where vol_id=?";
    	PreparedStatement ps1=connection.prepareStatement(query);
		ps1.setInt(1,varVolId);
		ResultSet result=ps1.executeQuery();	
		while(result.next()) {
			float frais=result.getFloat("vol_frais");
		FraisVol.setText(String.valueOf(frais));

		}
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    		}
    	}

    	
    
    	public void ShowAllVols() {
    		Connection connection=Connector();
        	try {
        	String query="SELECT v.* FROM vol v LEFT JOIN ticket t ON v.vol_id=t.vol_id group by v.vol_id having count(TicketNum)<nbr_place";
        	PreparedStatement ps1=connection.prepareStatement(query);
    		ResultSet result=ps1.executeQuery();	
    		while(result.next()) {
    			result.getInt("vol_id");
    			volId.getItems().add("vol Id: "+result.getInt("vol_id")+" ( "+result.getString("source")+" - "+result.getString("destination")+" ) "+"Départs le : "+result.getDate("date_depart")+" "+result.getString("heure_depart"));
    		}
        	}catch(Exception e) {
        		
        		e.printStackTrace();
        		}
    	}
    
    	
    	public void getVolId() {
    		String s=volId.getValue();
    		int end=s.indexOf(" (");
    		String res=s.substring(8,end);
    		varVolId=Integer.valueOf(res);
    	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	 FraisVol.setStyle("-fx-opacity: 1.0;");
		   volId.focusedProperty().addListener((ov, oldV, newV) -> {
			      if (!newV) { // focus lost
			    	  getFraisVol();
			           }
			        });
		   cardType.setItems(FXCollections.observableArrayList("Visa","Mastercard"));
		   ShowAllVols();
		  
	}

}
