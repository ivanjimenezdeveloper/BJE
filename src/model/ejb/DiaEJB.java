package model.ejb;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Dia;
import model.entidad.Usuario;
import model.entidad.dao.DiaDAO;

/**
 * EJB de la entidad dia
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class DiaEJB {

	/**
	 * Devuelve un dia segun su id
	 * @param id id del dia
	 * @return Objeto dia
	 */
	public Dia diaPorId(int id) {
		DiaDAO d = new DiaDAO();
		
		return d.DiaPorId(id);
	}
	
	/**
	 * Busca el horario en dias de un usuario segun un mes y un año
	 * @param u usuario del que buscamos el horario
	 * @param mes mes del año 
	 * @param anyo año del horario
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioUsuario(Usuario u, int mes, int anyo){
		DiaDAO d = new DiaDAO();
		
		//Desglosa el usuario en su id
		return d.horarioUsuario(u.getId(), mes, anyo);
		
	}
	
	
	/**
	 * todos los dias de los usuarios de un restaurante
	 * @param mes mes del año
	 * @param anyo año del horario
	 * @param idRestaurante id del restaurante
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioRestaurante(int mes, int anyo, int idRestaurante){
		DiaDAO d = new DiaDAO();
		
		
		return d.horarioRestaurante(mes, anyo, idRestaurante);
		
	}
	
	/**
	 * todos los dias de los usuarios de un restaurante
	 * @param mes mes del año
	 * @param anyo año del horario
	 * @param idRestaurante id del restaurante
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioUsuarioFecha(Usuario u, int mes, int anyo){
		DiaDAO d = new DiaDAO();
		
		
		return d.horarioUsuarioFecha(mes, anyo, u.getId());
		
	}
	
	/**
	 * Saca el nombre del dia del mes
	 * @param fecha String con la fecha con el formato yyyy-M-d
	 * @return String con el nombre del dia
	 * @throws ParseException
	 */
	public String NombreDia(String fecha) throws ParseException {
		
		//pasa el string a un objeto date
		Date date = new SimpleDateFormat("yyyy-M-d").parse(fecha);

		// saca a partir del la fecha el nombre del dia en ingles
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

		//traduce el nombre del dia
		if(dayOfWeek.equals("Monday")) {
			dayOfWeek = "Lunes";
		}else if(dayOfWeek.equals("Tuesday")) {
			dayOfWeek = "Martes";

		}else if(dayOfWeek.equals("Wednesday")) {
			dayOfWeek = "Miercoles";

		}else if(dayOfWeek.equals("Thursday")) {
			dayOfWeek = "Jueves";

		}else if(dayOfWeek.equals("Friday")) {
			dayOfWeek = "Viernes";

		}else if(dayOfWeek.equals("Saturday")) {
			dayOfWeek = "Sabado";

		}else if(dayOfWeek.equals("Sunday")) {
			dayOfWeek = "Domingo";

		}
		
		
		//junta el nombre con el dia del mes
		return dayOfWeek + " " + getDia(fecha);
		
		
	}
	
	/**
	 * Recupera de una fecha el numero del dia
	 * @param fecha fecha con formato yyyy-M-d
	 * @return String con el numero del dia
	 */
	public String getDia(String fecha) {
		
		//divide el string en "-"
		String[] arr = fecha.split("-");
		
		//recupera la ultima parte del string es decir los dias
		return arr[2];
	}
	
	/**
	 * Inserta un dia
	 * @param dia dia que insertar
	 * @param idHorario id del horario
	 */
	public void insertarDia(Dia dia, int idHorario) {
		DiaDAO d = new DiaDAO();
		
		//desglosa el horario en partes para la query
		d.insertarDia(dia.getFecha(), dia.getEntrada_1(), dia.getSalida_1(), dia.getEntrada_2(), dia.getSalida_2(), dia.getUsuario(), idHorario );
		
		
	}
	
	/**
	 * Edita un dia
	 * @param dia dia a editar
	 */
	public void editaDia(Dia dia) {
		DiaDAO d = new DiaDAO();
		
		//desglosa el dia en parametros
		d.editaDia(dia.getFecha(), dia.getEntrada_1(), dia.getSalida_1(), dia.getEntrada_2(), dia.getSalida_2(), dia.getUsuario());;
		
		
	}
	
	/**
	 * Comprueba si existe un dia con esa fecha y si pertenece a un usuario concreto
	 * @param us usuario al que podria pertenecer el dia
	 * @param fecha fecha del dia exacto con formato yyy-M-d
	 * @return 1 o mas si encuentra algo o 0 si no encuentra nada
	 */
	public int existeDia(Usuario us, String fecha) {
		DiaDAO d = new DiaDAO();

		return d.existeDia(us.getId(), fecha);
	}
	
	
}
