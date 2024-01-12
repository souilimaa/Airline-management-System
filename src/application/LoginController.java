package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField nom;

    @FXML
    private TextField password;

    @FXML
    void login(ActionEvent event) {
    	if(nom.getText().equals("")==false && password.getText().equals("")==false) {
    		Connection connection=Connector();
    		try {
    		String query="select * from admin where UserName=? and Password=?";
    		PreparedStatement ps;
    		ps=connection.prepareStatement(query);
			ps.setString(1,String.valueOf(nom.getText()));
			ps.setString(2,String.valueOf(password.getText()));
		ResultSet res=ps.executeQuery();
		if(!res.next()) {
			 Alert alert=new Alert(AlertType.WARNING);
				alert.setHeaderText("Nom d'utilisateur ou Mot de Passe incorrect!!!");
				alert.show(); 
		} 
		else {
			try {
				Stage currentStage;

		        currentStage=(Stage) nom.getScene().getWindow();
				currentStage.close();
				FXMLLoader fxmlload=new FXMLLoader(getClass().getResource("Home.fxml"));
				
				Parent root1=(Parent)fxmlload.load();
				Stage stage=new Stage();
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
    		}catch(Exception e) {
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
}
