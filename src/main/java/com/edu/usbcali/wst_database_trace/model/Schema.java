package com.edu.usbcali.wst_database_trace.model;

import java.io.Serializable;

public class Schema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -588076847035075649L;
	private String esquema;

	public Schema(String esquema) {
		super();
		this.esquema = esquema;
	}

	public String getEsquema() {
		return esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	
	
	
}
