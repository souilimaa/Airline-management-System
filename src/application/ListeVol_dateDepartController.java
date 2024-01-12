package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import application.Vol;

public class ListeVol_dateDepartController {

    @FXML
    private Button btn_toutdp;
	
    @FXML
    private Button btn_cherche_dp;

    @FXML
    private TableColumn<Vol, LocalDate> cul_datedep;

    @FXML
    private TableColumn<Vol, String> cul_des;

    @FXML
    private TableColumn<Vol, Float> cul_frais;

    @FXML
    private TableColumn<Vol, String> cul_h_arr;

    @FXML
    private TableColumn<Vol, String> cul_h_dep;

    @FXML
    private TableColumn<Vol, Integer> cul_id;

    @FXML
    private TableColumn<Vol, String> cul_nom;

    @FXML
    private TableColumn<Vol, Integer> cul_np;

    @FXML
    private TableColumn<Vol, String> cul_source;
    
    @FXML
    private DatePicker dp;

    @FXML
    private TableView<Vol> dptable;
    
    @FXML
    void toutVoldp(ActionEvent event) {
    	ObservableList<Vol> listVoldp = FXCollections.observableArrayList();
    	dptable.getItems().clear();
    	
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");

			pst=conn.prepareStatement("select vol_id,vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart from vol");
			
		
			rs= pst.executeQuery();
			 while(rs.next()) {
				 listVoldp.add(new Vol (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getFloat(8),rs.getDate(9).toLocalDate()));
			 }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
    	cul_id.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vvol_ID"));
    	cul_nom.setCellValueFactory(new PropertyValueFactory<Vol,String>("vvol_nom"));
    	cul_source.setCellValueFactory(new PropertyValueFactory<Vol,String>("vsource"));
    	cul_des.setCellValueFactory(new PropertyValueFactory<Vol,String>("vdestination"));
    	cul_h_dep.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_depart"));
    	cul_h_arr.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_arrivee"));
    	cul_np.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vnbr_place"));
    	cul_frais.setCellValueFactory(new PropertyValueFactory<Vol,Float>("vvol_frais"));
    	cul_datedep.setCellValueFactory(new PropertyValueFactory<Vol,LocalDate>("vdate_depart"));
    	dptable.setItems(listVoldp);

    }

    @FXML
    void chercherActdp(ActionEvent event) {
    	LocalDate dpdatedepart = dp.getValue();
    	String dpdatedepartString = dpdatedepart.toString();
    	ObservableList<Vol> listVoldp = FXCollections.observableArrayList();
    	dptable.getItems().clear();
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
			pst=conn.prepareStatement("select vol_id,vol_nom,source,destination,heure_depart,heure_arrivee,nbr_place,vol_frais,date_depart from vol where date_depart=?");
			
			
			pst.setString(1, dpdatedepartString);
			
			rs= pst.executeQuery();
			 while(rs.next()) {
				 listVoldp.add(new Vol (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getFloat(8),rs.getDate(9).toLocalDate()));
			 }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
    	cul_id.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vvol_ID"));
    	cul_nom.setCellValueFactory(new PropertyValueFactory<Vol,String>("vvol_nom"));
    	cul_source.setCellValueFactory(new PropertyValueFactory<Vol,String>("vsource"));
    	cul_des.setCellValueFactory(new PropertyValueFactory<Vol,String>("vdestination"));
    	cul_h_dep.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_depart"));
    	cul_h_arr.setCellValueFactory(new PropertyValueFactory<Vol,String>("vheure_arrivee"));
    	cul_np.setCellValueFactory(new PropertyValueFactory<Vol,Integer>("vnbr_place"));
    	cul_frais.setCellValueFactory(new PropertyValueFactory<Vol,Float>("vvol_frais"));
    	cul_datedep.setCellValueFactory(new PropertyValueFactory<Vol,LocalDate>("vdate_depart"));
    	dptable.setItems(listVoldp);

    }
    Connection conn;
    PreparedStatement pst ;
    ResultSet rs;

}
