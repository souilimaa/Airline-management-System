package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AjtVoyageurController {
	@FXML
	TextField PassportId;
	@FXML
	TextField Nom;
	@FXML
	TextField Prenom;
	@FXML
	TextField Nationality;
	@FXML
	TextField Adresse;
	@FXML
	TextField PhoneNum;
	@FXML
	RadioButton btnFemme;
	@FXML
	RadioButton btnHomme;
	@FXML
	ToggleGroup genre;
	String genrebtn;
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
	public void AjtVoyageur() {
		if(btnFemme.isSelected()||btnHomme.isSelected()&& Nom.getText()!=""&& Prenom.getText()!="" && Nationality.getText()!=""&& Adresse.getText()!=""&& PhoneNum.getText()!="") {
			Connection connection=Connector();
			String Sql="INSERT INTO `passager`(`PassagerID`, `PassportID`, `Nom`, `Prenom`, `Genre`, `Nationality`, `Adresse`, `NumeroTel`) VALUES (NULL,?,?,?,?,?,?,?)";
		 PreparedStatement ps;
		 try {
			 ps=connection.prepareStatement(Sql);
			 ps.setInt(1, Integer.valueOf(PassportId.getText()));
			 ps.setString(2, Nom.getText());
			 ps.setString(3, Prenom.getText());
			 RadioButton gnr = (RadioButton) genre.getSelectedToggle(); 
			 ps.setString(4, gnr.getText());
			 ps.setString(5, Nationality.getText());
			 ps.setString(6,Adresse.getText());
			 ps.setString(7,PhoneNum.getText());
			 ps.execute();
			 Alert alert=new Alert(AlertType.INFORMATION);
				alert.setContentText("Le voyageur a été Ajouté avec Succès");
				alert.show();
				PassportId.clear();
				Nom.clear();
				Prenom.clear();
				Nationality.clear();
				Adresse.clear();
				PhoneNum.clear();
				btnHomme.setSelected(false);
				btnFemme.setSelected(false);
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
}
