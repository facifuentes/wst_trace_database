package com.edu.usbcali.wst_database_trace.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbrelease")
public class TBRelease implements Serializable {
	private static final long serialVersionUID = -6761324198836451381L;

	@Id
	private Long r_id;
	
	private String r_release;

	private String r_campo;

	private String r_estructura;

	private String r_huella;

	private Timestamp r_fecha;
	
	private boolean r_lineabase;

	
	public TBRelease() {
		
	}

	public TBRelease(String r_release, String r_campo, String r_estructura, String r_huella) {
		super();
		this.r_release = r_release;
		this.r_campo = r_campo;
		this.r_estructura = r_estructura;
		this.r_huella = r_huella;
	}
	
	public TBRelease( String r_release, String r_campo, String r_estructura, String r_huella,Timestamp r_fecha
			) {
		super();
		this.r_release = r_release;
		this.r_campo = r_campo;
		this.r_estructura = r_estructura;
		this.r_huella = r_huella;
		this.r_fecha = r_fecha;
	}
	
	public TBRelease( String r_release, String r_campo, String r_estructura, String r_huella,Timestamp r_fecha,boolean r_lineaBase
			) {
		super();
		this.r_release = r_release;
		this.r_campo = r_campo;
		this.r_estructura = r_estructura;
		this.r_huella = r_huella;
		this.r_fecha = r_fecha;
		this.r_lineabase=r_lineaBase;
	}

	public Long getR_id() {
		return r_id;
	}

	public void setR_id(Long r_id) {
		this.r_id = r_id;
	}

	public String getR_release() {
		return r_release;
	}

	public void setR_release(String r_release) {
		this.r_release = r_release;
	}

	public String getR_campo() {
		return r_campo;
	}

	public void setR_campo(String r_campo) {
		this.r_campo = r_campo;
	}

	public String getR_estructura() {
		return r_estructura;
	}

	public void setR_estructura(String r_estructura) {
		this.r_estructura = r_estructura;
	}

	public String getR_huella() {
		return r_huella;
	}

	public void setR_huella(String r_huella) {
		this.r_huella = r_huella;
	}

	public Timestamp getR_fecha() {
		return r_fecha;
	}

	public void setR_fecha(Timestamp r_fecha) {
		this.r_fecha = r_fecha;
	}

	public boolean isr_lineaBase() {
		return r_lineabase;
	}

	public void setr_lineaBase(boolean r_lineabase) {
		this.r_lineabase = r_lineabase;
	}
	
	
	
	
}
