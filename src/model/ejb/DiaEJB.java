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

@Stateless
@LocalBean
public class DiaEJB {

	public Dia diaPorId(int id) {
		DiaDAO d = new DiaDAO();
		
		return d.DiaPorId(id);
	}
	
	
	public ArrayList<Dia> horarioUsuario(Usuario u, int mes, int anyo){
		DiaDAO d = new DiaDAO();
		
		
		return d.horarioUsuario(u.getId(), mes, anyo);
		
	}
	
	public String NombreDia(String fecha) throws ParseException {
		
		
		Date date = new SimpleDateFormat("yyyy-M-d").parse(fecha);

		// Then get the day of week from the Date based on specific locale.
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

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
		
		
		
		return dayOfWeek + " " + getDia(fecha);
		
		
	}
	
	public String getDia(String fecha) {
		
		String[] arr = fecha.split("-");
		
		return arr[2];
	}
}
