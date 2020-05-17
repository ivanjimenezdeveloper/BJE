package model.entidad;

public class Alimento {
	private int id, categoria;
	private String nombre;

	public Alimento() {
		super();
	}

	public Alimento(int id, int categoria, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;

		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
