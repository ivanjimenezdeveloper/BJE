package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Horario;
import model.entidad.dao.HorarioDAO;

/**
 * EJB de horarios
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class HorarioEJB {
	
	/**
	 * Busca un horario general segun una id
	 * @param id id del horario general
	 * @return Objeto horario general
	 */
	public Horario horarioPorId(int id) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horarioPorDia(id);
	}
	
	/**
	 * Devuelve los horarios que estan conectados a un restaurante
	 * @param idRestaurante id del restaurante por el que filtrar
	 * @return Arraylist de horarios generales
	 */
	public ArrayList<Horario> horariosPorRestaurante(int idRestaurante) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horariosPorRestaurante(idRestaurante);
	}
	
	/**
	 * Devuelve los horarios que estan conectados a un restaurante activos
	 * @param idRestaurante id del restaurante por el que filtrar
	 * @return Arraylist de horarios generales
	 */
	public ArrayList<Horario> horariosPorRestauranteActivo(int idRestaurante) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horariosPorRestauranteActivo(idRestaurante);
	}
	
	/**
	 * Devuelve todos los horarios generales
	 * @return Arraylist de horarios generales
	 */
	public ArrayList<Horario> getHorarios() {
		HorarioDAO h = new HorarioDAO();
		
		return h.getHorarios();
	}

	/**
	 * Inserta un horario general a la base de datos
	 * @param activo boolean para determinar si esta activo o no
	 * @param mes mes del año 
	 * @param anyo año por el que filtrar
	 */
	public void creaHorarioGeneral(boolean activo, int mes, int anyo) {
		HorarioDAO h = new HorarioDAO();

		//si es true guarda un 1 porque en la base de datos es un integer
		int activoInsert = (activo) ? 1 : 0;
		
		h.creaHorarioGeneral(activoInsert, mes, anyo);
	}
	
	/**
	 * Devuelve la id de un horario segun su mes y su año
	 * @param mes mes del año
	 * @param anyo año por el que filtrar
	 * @return Objeto horario
	 */
	public Horario horarioIdPorMesAnyo(int mes, int anyo) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horarioIdPorMesAnyo(mes, anyo);
	}
	
	public void activaHorario(int id) {
		HorarioDAO h = new HorarioDAO();

		h.activaHorario(id);
	}
	
	
}
