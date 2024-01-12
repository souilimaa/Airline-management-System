package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;




public class AjouteVolController {

	  @FXML
	    private Button btn_ajt;

	    @FXML
	    private DatePicker dp_depart;

	    @FXML
	    private TextField txt_arrivee;

	    @FXML
	    private TextField txt_des;

	    @FXML
	    private TextField txt_frais_vol;

	    @FXML
	    private TextField txt_h_depart;

	    @FXML
	    private TextField txt_nbr_place;

	    @FXML
	    private TextField txt_source;

	    @FXML
	    private TextField txt_vol_id;

	    @FXML
	    private TextField txt_vol_nom;

    @FXML
    void ajouteVol(ActionEvent event) {
    	
    int vvol_ID, vnbr_place   ;
    String vvol_nom , vsource , vdestination , vheure_depart , vheure_arrivee ;
    LocalDate vdate_depart ;
    	float vvol_frais ;
    vvol_ID=Integer.parseInt(txt_vol_id.getText());
    vvol_nom=txt_vol_nom.getText();
    vsource=txt_source.getText();
    vdestination=txt_des.getText();
    vheure_depart=txt_h_depart.getText();
    vheure_arrivee=txt_arrivee.getText();
    vnbr_place=Integer.parseInt(txt_nbr_place.getText());
    vvol_frais=Float.parseFloat(txt_frais_vol.getText());
    vdate_depart=dp_depart.getValue();
    
    
    Connection conn ;
    PreparedStatement pst ;
    
    		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
				pst=conn.prepareStatement("insert into vol (vol_id,vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart) values(?,?,?,?,?,?,?,?,?)");
	    		pst.setInt(1, vvol_ID);
	    		pst.setString(2, vvol_nom);
	    		pst.setString(3, vsource);
	    		pst.setString(4, vdestination);
	    		pst.setString(5, vheure_depart);
	    		pst.setString(6, vheure_arrivee);
	    		pst.setInt(7, vnbr_place);
	    		pst.setFloat(8, vvol_frais);
	    		pst.setObject(9,vdate_depart);
	    		
	    		int status =pst.executeUpdate();
	    		  
	    		if(status==1) {
	    			JOptionPane.showMessageDialog(null, "Vol Ajouté");
	    			txt_vol_id.setText("");
	    			txt_vol_nom.setText("");
	    		    txt_source.setText("");
	    		    txt_des.setText("");
	    		    txt_h_depart.setText("");
	    		    txt_arrivee.setText("");
	    		    txt_nbr_place.setText("");
	    		    txt_frais_vol.setText("");
	    		    dp_depart.setValue(null);
	    		    
	    		    
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
    	
    	
    	
   
    
  
  

    
    
}
