package application;

import java.time.LocalDate;

public class Vol {
   private int vvol_ID ;
   private String vvol_nom   ;
   private String vsource ;
   private String vdestination ;
   private String vheure_depart ;
   private String vheure_arrivee ;
   private int vnbr_place ;
   private float vvol_frais ;
   private LocalDate vdate_depart ;
	public Vol(int vvol_ID, String vvol_nom, String vsource, String vdestination, String vheure_depart,
			String vheure_arrivee, int vnbr_place, float vvol_frais, LocalDate vdate_depart) {
		super();
		this.vvol_ID = vvol_ID;
		this.vvol_nom = vvol_nom;
		this.vsource = vsource;
		this.vdestination = vdestination;
		this.vheure_depart = vheure_depart;
		this.vheure_arrivee = vheure_arrivee;
		this.vnbr_place = vnbr_place;
		this.vvol_frais = vvol_frais;
		this.vdate_depart = vdate_depart;
	}
	public int getVvol_ID() {
		return vvol_ID;
	}
	public void setVvol_ID(int vvol_ID) {
		this.vvol_ID = vvol_ID;
	}
	public String getVvol_nom() {
		return vvol_nom;
	}
	public void setVvol_nom(String vvol_nom) {
		this.vvol_nom = vvol_nom;
	}
	public String getVsource() {
		return vsource;
	}
	public void setVsource(String vsource) {
		this.vsource = vsource;
	}
	public String getVdestination() {
		return vdestination;
	}
	public void setVdestination(String vdestination) {
		this.vdestination = vdestination;
	}
	public String getVheure_depart() {
		return vheure_depart;
	}
	public void setVheure_depart(String vheure_depart) {
		this.vheure_depart = vheure_depart;
	}
	public String getVheure_arrivee() {
		return vheure_arrivee;
	}
	public void setVheure_arrivee(String vheure_arrivee) {
		this.vheure_arrivee = vheure_arrivee;
	}
	public int getVnbr_place() {
		return vnbr_place;
	}
	public void setVnbr_place(int vnbr_place) {
		this.vnbr_place = vnbr_place;
	}
	public float getVvol_frais() {
		return vvol_frais;
	}
	public void setVvol_frais(int vvol_frais) {
		this.vvol_frais = vvol_frais;
	}
	public LocalDate getVdate_depart() {
		return vdate_depart;
	}
	public void setVdate_depart(LocalDate vdate_depart) {
		this.vdate_depart = vdate_depart;
	}

}
