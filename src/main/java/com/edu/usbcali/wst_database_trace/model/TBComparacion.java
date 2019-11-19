package com.edu.usbcali.wst_database_trace.model;

import java.io.Serializable;




public class TBComparacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129037268881915953L;
	private String releasenuevo;
	private String camponuevo;
	private String estructuranueva;
	private String huellanueva;
	private String releaseanterior;
	private String estructuraanterior;
	private String huellaanterior;
	private String estado;

	public TBComparacion() {
		
	}
	
	public TBComparacion(String releasenuevo, String camponuevo, String estructuranueva, String huellanueva,
			String releaseanterior, String estructuraanterior, String huellaanterior, String estado) {
		super();
		this.releasenuevo = releasenuevo;
		this.camponuevo = camponuevo;
		this.estructuranueva = estructuranueva;
		this.huellanueva = huellanueva;
		this.releaseanterior = releaseanterior;
		this.estructuraanterior = estructuraanterior;
		this.huellaanterior = huellaanterior;
		this.estado = estado;
	}

	public String getReleasenuevo() {
		return releasenuevo;
	}

	public void setReleasenuevo(String releasenuevo) {
		this.releasenuevo = releasenuevo;
	}

	public String getCamponuevo() {
		return camponuevo;
	}

	public void setCamponuevo(String camponuevo) {
		this.camponuevo = camponuevo;
	}

	public String getEstructuranueva() {
		return estructuranueva;
	}

	public void setEstructuranueva(String estructuranueva) {
		this.estructuranueva = estructuranueva;
	}

	public String getHuellanueva() {
		return huellanueva;
	}

	public void setHuellanueva(String huellanueva) {
		this.huellanueva = huellanueva;
	}

	public String getReleaseanterior() {
		return releaseanterior;
	}

	public void setReleaseanterior(String releaseanterior) {
		this.releaseanterior = releaseanterior;
	}

	public String getEstructuraanterior() {
		return estructuraanterior;
	}

	public void setEstructuraanterior(String estructuraanterior) {
		this.estructuraanterior = estructuraanterior;
	}

	public String getHuellaanterior() {
		return huellaanterior;
	}

	public void setHuellaanterior(String huellaanterior) {
		this.huellaanterior = huellaanterior;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
