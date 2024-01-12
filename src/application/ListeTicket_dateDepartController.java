package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import application.TicketListe;

public class ListeTicket_dateDepartController implements Initializable{
    
	
	private Connection conn = null;
	private PreparedStatement pst =null ;
	private ResultSet rs = null ;
	private ObservableList<TicketListe> data;
	
	public class DBConnection {
	    public static Connection Connexion(){
	        Connection con = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/airliners","root","");
	        } catch (ClassNotFoundException | SQLException ex) {
	            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        return con;
	    }
	}
	
    @FXML
    private TableView<TicketListe> Ticktable;

    @FXML
    private Button btn_cherche_tdp;

    @FXML
    private Button btn_toutTdp;

    @FXML
    private TableColumn<?, ?> cul_PassId;

    @FXML
    private TableColumn<?, ?> cul_PassNom;

    @FXML
    private TableColumn<?, ?> cul_Tdatedepart;

    @FXML
    private TableColumn<?, ?> cul_TickId;

    @FXML
    private TableColumn<?, ?> cul_VolID;

    @FXML
    private TableColumn<?, ?> cul_VolNom;

    @FXML
    private DatePicker tdp;

    @FXML
    void cherchertdp(ActionEvent event) {
    	
    	data.clear();

		try {
			pst=conn.prepareStatement("SELECT TicketNum , t.PassagerID , Nom,  t.vol_id  , vol_nom , date_depart From ticket t ,vol v , passager p WHERE date_depart=? AND t.vol_id=v.vol_id and p.PassagerID=t.PassagerID");
			pst.setString(1, tdp.getValue().toString());

			rs= pst.executeQuery();
			while(rs.next()) {
				data.add(new TicketListe(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDate(6).toLocalDate()));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		Ticktable.setItems(data);
    }

    @FXML
    void toutTicketdp(ActionEvent event) {
		loadDataFromDatabase();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		conn = DBConnection.Connexion();
		data=FXCollections.observableArrayList();
		setCellTable();
	}

	private void setCellTable() {
		cul_TickId.setCellValueFactory(new PropertyValueFactory<>("ticketnum"));
		cul_PassId.setCellValueFactory(new PropertyValueFactory<>("passagerid"));
		cul_PassNom.setCellValueFactory(new PropertyValueFactory<>("passagernom"));
		cul_VolID.setCellValueFactory(new PropertyValueFactory<>("volid"));
		cul_VolNom.setCellValueFactory(new PropertyValueFactory<>("volnom"));
		cul_Tdatedepart.setCellValueFactory(new PropertyValueFactory<>("datedepart"));

		
	}
	
	private void loadDataFromDatabase() {
		data.clear();

		try {
			pst = conn.prepareStatement("SELECT TicketNum , t.PassagerID , Nom,  t.vol_id  , vol_nom , date_depart From ticket t ,vol v , passager p WHERE t.vol_id=v.vol_id and p.PassagerID=t.PassagerID");
			rs= pst.executeQuery();
			while(rs.next()) {
				data.add(new TicketListe(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDate(6).toLocalDate()));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		Ticktable.setItems(data);
	}
}
