package com.edu.usbcali.wst_database_trace.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReleaseSchema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2500428853931590814L;
	private String esquema;
	private String release;
	private Timestamp fecha;
	private boolean lineaBase;
	public ReleaseSchema(String esquema, String release, Timestamp fecha, boolean lineaBase) {
		super();
		this.esquema = esquema;
		this.release = release;
		this.fecha = fecha;
		this.lineaBase=lineaBase;
	}
	
	

	public boolean isLineaBase() {
		return lineaBase;
	}



	public void setLineaBase(boolean lineaBase) {
		this.lineaBase = lineaBase;
	}



	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
	
}
