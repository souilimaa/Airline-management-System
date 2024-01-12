package application;

import java.time.LocalDate;

public class TicketListe {
     private int ticketnum ;
     private int passagerid;
     private String passagernom;
     private int volid;
     private String volnom;
     private LocalDate datedepart;
	public TicketListe(int ticketnum, int passagerid, String passagernom, int volid, String volnom,
			LocalDate datedepart) {
		super();
		this.ticketnum = ticketnum;
		this.passagerid = passagerid;
		this.passagernom = passagernom;
		this.volid = volid;
		this.volnom = volnom;
		this.datedepart = datedepart;
	}
	public int getTicketnum() {
		return ticketnum;
	}
	public void setTicketnum(int ticketnum) {
		this.ticketnum = ticketnum;
	}
	public int getPassagerid() {
		return passagerid;
	}
	public void setPassagerid(int passagerid) {
		this.passagerid = passagerid;
	}
	public String getPassagernom() {
		return passagernom;
	}
	public void setPassagernom(String passagernom) {
		this.passagernom = passagernom;
	}
	public int getVolid() {
		return volid;
	}
	public void setVolid(int volid) {
		this.volid = volid;
	}
	public String getVolnom() {
		return volnom;
	}
	public void setVolnom(String volnom) {
		this.volnom = volnom;
	}
	public LocalDate getDatedepart() {
		return datedepart;
	}
	public void setDatedepart(LocalDate datedepart) {
		this.datedepart = datedepart;
	}
     
     
     
}
