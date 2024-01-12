package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class TicketDetailsController implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {       	
       	 VoyageurId.setStyle("-fx-opacity: 1.0;");
       	NomVoyageur.setStyle("-fx-opacity: 1.0;");
       	PrenomVoyageur.setStyle("-fx-opacity: 1.0;");
        VolId.setStyle("-fx-opacity: 1.0;");
        dateDepart.setStyle("-fx-opacity: 1.0;");
        DateTicket.setStyle("-fx-opacity: 1.0;");
       	destination.setStyle("-fx-opacity: 1.0;");
       	heureDepart.setStyle("-fx-opacity: 1.0;");
       	source.setStyle("-fx-opacity: 1.0;");
       	volNom.setStyle("-fx-opacity: 1.0;");
       	
   	}
	@FXML
	private TextField NumTicket;
	@FXML
	private TextField VoyageurId;
	@FXML
	private TextField NomVoyageur;
	@FXML
	private TextField PrenomVoyageur;
	@FXML
	private TextField VolId;
	@FXML
	private TextField volNom;
	@FXML
	private TextField source;
	@FXML
	private TextField destination;
	@FXML
	private TextField dateDepart;
	@FXML
	private TextField heureDepart;
	@FXML
	private TextField DateTicket;
	@FXML
	private Button btnRechercher;
@FXML
private Button nettoyer;
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
	@FXML
	public void RechercherTicket(ActionEvent event) {
		if(NumTicket.getText().equals("")==false) {
		Connection connection=Connector();
    	String query="select t.PassagerID,Nom,Prenom,t.vol_id,vol_nom,source,destination,date_depart,heure_depart,DateTicket from vol v,passager p,ticket t where v.vol_id=t.vol_id and p.PassagerID=t.PassagerID and TicketNum=?";
    	
    	try{
    		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,Integer.valueOf(NumTicket.getText()));
		ResultSet result=ps.executeQuery();	
		if(!result.next()) {
			Alert alert=new Alert(AlertType.WARNING);
    		alert.setHeaderText("Ce Numéro de Ticket n'existe pas!!!");
    		alert.setContentText(null);
    		alert.show();
		}
		else {
		do {
			int vid=result.getInt(1);
			String n=result.getString(2);
			String p=result.getString(3);
			int volid=result.getInt(4);
			String vn=result.getString(5);
			String s=result.getString(6);
			String d=result.getString(7);
			Date dat=result.getDate(8);
			String h=result.getString(9);
			 java.sql.Timestamp dt = result.getTimestamp(10);
			VoyageurId.setText(vid+"");
			NomVoyageur.setText(n+"");
			PrenomVoyageur.setText(p+"");
			VolId.setText(volid+"");
			volNom.setText(vn+"");
			source.setText(s+"");
			destination.setText(d+"");
			dateDepart.setText(dat+"");
			heureDepart.setText(h+"");
			DateTicket.setText(dt+"");
    	}while(result.next());
		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		}
		else {
			Alert alert=new Alert(AlertType.WARNING);
    		alert.setHeaderText("Veuillez entrer le numéro de Ticket!!");
    		alert.setContentText(null);
    		alert.show();
		}
	}
	@FXML
	public void Nettoyer() {
		NumTicket.clear();
		VoyageurId.clear();
		NomVoyageur.clear();
		PrenomVoyageur.clear();
		VolId.clear();
		volNom.clear();
		source.clear();
		destination.clear();
		dateDepart.clear();
		heureDepart.clear();
		DateTicket.clear();
		
		
	}
	
}
