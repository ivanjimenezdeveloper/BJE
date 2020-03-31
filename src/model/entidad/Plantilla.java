package model.entidad;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Plantilla {
	private int id;
	
	//Constructores
	public Plantilla() {
		super();
	}

	public Plantilla(int id, String nombre) {
		super();
		this.id = id;
	}

	//Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
