package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;


public class ModifierVolController {
	
	 @FXML
	    private Button btn_mod;

	    @FXML
	    private Button btn_mod_chercher;

	    @FXML
	    private Button btn_supr;

	    @FXML
	    private TextField cherche_par_id_vol;

	    @FXML
	    private DatePicker mod_date_dep;

	    @FXML
	    private TextField mod_des;

	    @FXML
	    private TextField mod_frais_vol;

	    @FXML
	    private TextField mod_h_arivee;

	    @FXML
	    private TextField mod_h_depart;

	    @FXML
	    private TextField mod_nbr_place;

	    @FXML
	    private TextField mod_source;

	    @FXML
	    private TextField mod_vol_nom;

	    
	    
	    
    @FXML
    void chercherVol(ActionEvent event) {
try {
			
    		Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
			try {
				pst=conn.prepareStatement("Select vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart From vol Where vol_id=?");
				int vvol_ID=Integer.parseInt(cherche_par_id_vol.getText());
				pst.setInt(1, vvol_ID);
				 rs1 = pst.executeQuery();
				if(rs1.next()==false) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("ID not found");
					alert.showAndWait();
					
						
				}
				else {
					
					mod_vol_nom.setText(rs1.getString("vol_nom"));
					mod_source.setText(rs1.getString("source"));
					mod_des.setText(rs1.getString("destination"));
					mod_h_depart.setText(rs1.getString("heure_depart"));
					mod_h_arivee.setText(rs1.getString("heure_arrivee"));
					mod_nbr_place.setText(rs1.getString("nbr_place"));
					mod_frais_vol.setText(rs1.getString("vol_frais"));
					mod_date_dep.setValue(LocalDate.parse(rs1.getString("date_depart")));			}
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	

    }

    @FXML
    void modifierVol(ActionEvent event) {

    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
			pst=conn.prepareStatement("update vol set vol_nom=?,source=?,destination=?,heure_depart=?,heure_arrivee=?,nbr_place=?,vol_frais=?,date_depart=? WHERE vol_id=?");
    		
    		pst.setString(1, mod_vol_nom.getText());
    		pst.setString(2, mod_source.getText());
    		pst.setString(3, mod_des.getText());
    		pst.setString(4, mod_h_depart.getText());
    		pst.setString(5, mod_h_arivee.getText());
    		pst.setInt(6, Integer.parseInt(mod_nbr_place.getText()));
    		pst.setFloat(7,  Float.parseFloat(mod_frais_vol.getText()));
    		pst.setObject(8,mod_date_dep.getValue());
    	    pst.setInt(9, Integer.parseInt(cherche_par_id_vol.getText()));
    		
    	   
    	    int status =pst.executeUpdate();
  		  
    		if(status==1) {
    			JOptionPane.showMessageDialog(null, "Vol Modifié");
    			
    		
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "Failed");

    		}
    		
    		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }

    @FXML
    void supprimerVol(ActionEvent event) {

    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
			pst=conn.prepareStatement("delete from vol WHERE vol_id=?");
			pst.setInt(1, Integer.parseInt(cherche_par_id_vol.getText()));
			int status = pst.executeUpdate();
			if(status==1) {
    			JOptionPane.showMessageDialog(null, "Vol Supprimé!");
    			
    			cherche_par_id_vol.setText("");
    			mod_vol_nom.setText("");
    			mod_source.setText("");
    			mod_des.setText("");
    			mod_h_depart.setText("");
    			mod_h_arivee.setText("");
    			mod_nbr_place.setText("");
    			mod_frais_vol.setText("");
    			mod_date_dep.setValue(null);
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "Failed");

    		}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    Connection conn;
    PreparedStatement pst ;
    ResultSet rs1;
   
   
    /*public void Connect()
    {
    	try {
			
    		Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }*/
    
}
