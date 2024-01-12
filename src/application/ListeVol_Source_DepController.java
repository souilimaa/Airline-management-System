package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import application.Vol;

public class ListeVol_Source_DepController implements Initializable {

    @FXML
    private Button btn_cherche;

    @FXML
    private ComboBox<String> cb_des;

    @FXML
    private ComboBox<String> cb_source;

    @FXML
    private TableColumn<Vol, LocalDate> col_datedep;

    @FXML
    private TableColumn<Vol, String> col_des;

    @FXML
    private TableColumn<Vol, Float> col_frais;

    @FXML
    private TableColumn<Vol, String> col_h_arr;

    @FXML
    private TableColumn<Vol, String> col_h_dep;

    @FXML
    private TableColumn<Vol, Integer> col_id;

    @FXML
    private TableColumn<Vol, String> col_nom;

    @FXML
    private TableColumn<Vol, Integer> col_np;

    @FXML
    private TableColumn<Vol, String> col_source;

    @FXML
    private TableView<Vol> ctable;

    @FXML
    private Button btn_tout;
    @FXML
    void toutVol(ActionEvent event) {
		RemplireTab();

    }
    
    
    public void RemplireTab() {
    	ObservableList<Vol> listVol = FXCollections.observableArrayList();
    	ctable.getItems().clear();
    	
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");

			pst=conn.prepareStatement("select vol_id,vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart from vol");
			
		
			rs= pst.executeQuery();
			 while(rs.next()) {
				 listVol.add(new Vol (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getFloat(8),rs.getDate(9).toLocalDate()));
			 }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
    	col_id.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vvol_ID"));
    	col_nom.setCellValueFactory(new PropertyValueFactory<Vol,String>("vvol_nom"));
    	col_source.setCellValueFactory(new PropertyValueFactory<Vol,String>("vsource"));
    	col_des.setCellValueFactory(new PropertyValueFactory<Vol,String>("vdestination"));
    	col_h_dep.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_depart"));
    	col_h_arr.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_arrivee"));
    	col_np.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vnbr_place"));
    	col_frais.setCellValueFactory(new PropertyValueFactory<Vol,Float>("vvol_frais"));
    	col_datedep.setCellValueFactory(new PropertyValueFactory<Vol,LocalDate>("vdate_depart"));
    	ctable.setItems(listVol);

    }
    @FXML
    void chercherAct() {

    	String csource = cb_source.getValue();
    	String cdestination = cb_des.getValue();
    	ObservableList<Vol> listVol = FXCollections.observableArrayList();
    	ctable.getItems().clear();
    	
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");

			pst=conn.prepareStatement("select vol_id,vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart from vol where source=? and destination=?");
			
			pst.setString(1, csource);
			pst.setString(2, cdestination);
			rs= pst.executeQuery();
			 while(rs.next()) {
				 listVol.add(new Vol (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getFloat(8),rs.getDate(9).toLocalDate()));
			 }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
    	col_id.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vvol_ID"));
    	col_nom.setCellValueFactory(new PropertyValueFactory<Vol,String>("vvol_nom"));
    	col_source.setCellValueFactory(new PropertyValueFactory<Vol,String>("vsource"));
    	col_des.setCellValueFactory(new PropertyValueFactory<Vol,String>("vdestination"));
    	col_h_dep.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_depart"));
    	col_h_arr.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_arrivee"));
    	col_np.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vnbr_place"));
    	col_frais.setCellValueFactory(new PropertyValueFactory<Vol,Float>("vvol_frais"));
    	col_datedep.setCellValueFactory(new PropertyValueFactory<Vol,LocalDate>("vdate_depart"));
    	ctable.setItems(listVol);

    	
    }

    @FXML
    void remplirDes(MouseEvent event) {
    	 List<String> cdestinations = new ArrayList<String>();
         try {
  		Class.forName("com.mysql.cj.jdbc.Driver");
  		conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
  		
  		pst=conn.prepareStatement("select destination from vol");
  		rs=pst.executeQuery();
  		while(rs.next()) {
  			cdestinations.add(rs.getString("destination"));
  		}
         
  		
  	} catch (ClassNotFoundException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	} catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
  		cb_des.setItems(FXCollections.observableArrayList(cdestinations));
    }

    @FXML
    void remplirSource(MouseEvent event) {
       List<String> csources = new ArrayList<String>();
       try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
		
		pst=conn.prepareStatement("select source from vol");
		rs=pst.executeQuery();
		while(rs.next()) {
			csources.add(rs.getString("source"));
		}
       
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		cb_source.setItems(FXCollections.observableArrayList(csources));
    }
    Connection conn;
    PreparedStatement pst ;
    ResultSet rs;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		chercherAct();
	}
	
	   

}
