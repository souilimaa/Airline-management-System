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

public class UpdateVoyageurController {
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
	TextField VoyageurId;
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
	public void ToutNettoyer() {
		VoyageurId.clear();
		PassportId.clear();
		Nom.clear();
		Prenom.clear();
		Nationality.clear(); 
		Adresse.clear();
		PhoneNum.clear();
		btnHomme.setSelected(false);
		btnFemme.setSelected(false);
	}  
	public void SelectVoyageurById(){
	if(!(VoyageurId.getText().equals(""))) {
		try {
			Connection connection=Connector();
			String sql;
			sql="Select * From passager where PassagerID=? ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(VoyageurId.getText()));
			ResultSet result=ps.executeQuery();	
			if(!result.next()) {
				Alert alert=new Alert(AlertType.WARNING);
				alert.setHeaderText("Ce Voyageur n'existe Pas!!");
				alert.show();
				VoyageurId.clear();
				PassportId.clear();
				Nom.clear();
				Prenom.clear();
				Nationality.clear(); 
				Adresse.clear();
				PhoneNum.clear();
				btnHomme.setSelected(false);
				btnFemme.setSelected(false);
				
			}
			else {
				do {
				int passagerId=result.getInt("PassagerID");
				int passportID=result.getInt("PassportID");
				String nom=result.getString("Nom");
				String prenom=result.getString("Prenom");
				String genre1=result.getString("Genre");
				String nationality=result.getString("Nationality");
				String adresse=result.getString("Adresse");
				String numerotel=result.getString("NumeroTel");	
				VoyageurId.setText(String.valueOf(passagerId));
				PassportId.setText(String.valueOf(passportID));
				Prenom.setText(prenom);
				Nom.setText(nom);
				Adresse.setText(adresse);
				Nationality.setText(nationality);
				PhoneNum.setText(numerotel);
				if(genre1.equals("Femme")) {
				
					btnFemme.setSelected(true);
				}
				else {
					btnHomme.setSelected(true);
				}
			
				}while (result.next());
			}
			
		}

		catch(Exception e) {
			e.printStackTrace();
		}
	}
	else {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setHeaderText("Veuillez entrer Id de Voyageur!!");
		alert.show();	}
		
	}
	
	public void SelectVoyageurByPassportId() {
		if(!(PassportId.getText().equals(""))) {
			try {
				Connection connection=Connector();
				String sql;
				sql="Select * From passager where PassportID=? ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setInt(1,Integer.valueOf(PassportId.getText()));
				ResultSet result=ps.executeQuery();	
				if(!result.next()) {
					Alert alert=new Alert(AlertType.WARNING);
					alert.setHeaderText("Ce Voyageur n'existe Pas!!");
					alert.show();
					ToutNettoyer() ;
				}
				else {
					do {
					int passagerId=result.getInt("PassagerID");
					int passportID=result.getInt("PassportID");
					String nom=result.getString("Nom");
					String prenom=result.getString("Prenom");
					String genre1=result.getString("Genre");
					String nationality=result.getString("Nationality");
					String adresse=result.getString("Adresse");
					String numerotel=result.getString("NumeroTel");	
					VoyageurId.setText(String.valueOf(passagerId));
					PassportId.setText(String.valueOf(passportID));
					Prenom.setText(prenom);
					Nom.setText(nom);
					Adresse.setText(adresse);
					Nationality.setText(nationality);
					PhoneNum.setText(numerotel);
					if(genre1.equals("Femme")) {
					
						btnFemme.setSelected(true);
					}
					else {
						btnHomme.setSelected(true);
					}
				
					}while (result.next());
				}
				
			}

			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert=new Alert(AlertType.WARNING);
			alert.setHeaderText(" Veuillez entrer le Id de Passport!!");
			alert.show();	} 
	}
	public void UpdateVoyageur() {
if(!(VoyageurId.getText().equals("")&&PassportId.getText().equals("")&&Nom.getText().equals("")&&Prenom.getText().equals("")&&Nationality.getText().equals("")&&Adresse.getText().equals("")&&PhoneNum.getText().equals(""))&&btnFemme.isSelected()||btnHomme.isSelected()) {
		Connection connection=Connector();
		
		
		
		String sql="UPDATE `passager` SET `PassagerID`=?,`PassportID`=?,`Nom`=?,`Prenom`=?,`Genre`=?,`Nationality`=?,`Adresse`=?,`NumeroTel`=? WHERE PassagerID=?";
	PreparedStatement ps;
	try {
		String sql1;
		sql1="Select * From passager where PassagerID=? ";
		PreparedStatement ps1=connection.prepareStatement(sql1);
		ps1.setInt(1,Integer.valueOf(VoyageurId.getText()));
		ResultSet result=ps1.executeQuery();	
		if(!result.next()) {
			Alert alert=new Alert(AlertType.WARNING);
			alert.setHeaderText("Ce Voyageur n'existe Pas!!");
			alert.show();
			ToutNettoyer() ;
			
		}
		else {
		ps=connection.prepareStatement(sql);
		ps.setInt(1,Integer.valueOf(VoyageurId.getText().toString()));
		ps.setInt(2,Integer.valueOf(PassportId.getText().toString()));
		ps.setString(3,Nom.getText() );
		ps.setString(4,Prenom.getText() );
		RadioButton gnr = (RadioButton) genre.getSelectedToggle(); 
		 ps.setString(5, gnr.getText());
		 ps.setString(6,Nationality.getText() );
		ps.setString(7,Adresse.getText() );
		ps.setString(8,PhoneNum.getText() );
		ps.setInt(9,Integer.valueOf(VoyageurId.getText().toString()));

		ps.execute();
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setContentText("Le voyageur a été Mise à jour avec Succès");
		alert.show();
		ToutNettoyer();
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
}else {
	Alert alert=new Alert(AlertType.WARNING);
	alert.setHeaderText("Tous les Champs sont Obligatoires!!");
	alert.setContentText(null);
	alert.show();
}
	}
	public void SupprimerVoyageur() {
		try {
			if(VoyageurId.getText()!="") {
			Connection connection=Connector();
			String sql1;
			sql1="Select * From passager where PassagerID=? ";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			ps1.setInt(1,Integer.valueOf(VoyageurId.getText()));
			ResultSet result=ps1.executeQuery();	
			if(!result.next()) {
				Alert alert=new Alert(AlertType.WARNING);
				alert.setHeaderText("Ce Voyageur n'existe Pas!!");
				alert.show();
				ToutNettoyer() ;
			}else {
			String sql="DELETE FROM `passager` WHERE PassagerID=?";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(VoyageurId.getText().toString()));
			ps.execute();
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setContentText("Le voyageur a été Supprimer avec Succès");
			alert.show();
			}
			}
			else {
				Alert alert=new Alert(AlertType.WARNING);
				alert.setHeaderText("Veuillez entrer Voyageur Id!!");
				alert.setContentText(null);
				alert.show();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
