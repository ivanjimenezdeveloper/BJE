package model.entidad;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rol {
	private int id;
	private String nombre;
	
	//Constructores
	public Rol() {
		super();
	}

	public Rol(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	//Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
