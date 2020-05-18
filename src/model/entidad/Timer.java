package model.entidad;

public class Timer {
	private int id, idAlimento, tiempo_restante, idcategoria;

	public Timer() {
		super();
	}

	public Timer(int id, int idAlimento, int tiempo_restante, int idcategoria) {
		super();
		this.id = id;
		this.idAlimento = idAlimento;
		this.tiempo_restante = tiempo_restante;
		this.idcategoria = idcategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}

	public int getTiempo_restante() {
		return tiempo_restante;
	}

	public void setTiempo_restante(int tiempo_restante) {
		this.tiempo_restante = tiempo_restante;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}



}
