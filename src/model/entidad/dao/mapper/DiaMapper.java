package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Dia;
import net.shibboleth.utilities.java.support.annotation.ParameterName;

/**
 * Interfaz del mapper de la entidad Dia
 * 
 * @author HIBAN
 *
 */
public interface DiaMapper {
	public Dia DiaPorId(@Param("id") int id);

	public ArrayList<Dia> horarioUsuario(@Param("usuario") int idUsuario, @Param("mes") int mes,
			@Param("anyo") int anyo);

	public ArrayList<Dia> horarioRestaurante(@Param("mes") int mes,
			@Param("anyo") int anyo, @Param("restaurante") int idRestaurante);

	public ArrayList<Dia> horarioUsuarioFecha(@Param("mes") int mes,
			@Param("anyo") int anyo, @Param("usuario") int idUsuario);
	
	public void insertaDia(@Param("fecha") String fecha, @Param("entrada1") String entrada1,
			@Param("salida1") String salida1, @Param("entrada2") String entrada2, @Param("salida2") String salida2,
			@Param("usuario") int idUsuario, @Param("idHorario")int idHorario);
	
	public int existeDia(@Param("usuario")int idUsuario, @Param("fecha")String fecha);

	public void editaDia(@Param("fecha") String fecha, @Param("idUsuario")int idUsuario,
			@Param("entrada_1")String entrada_1,@Param("salida_1")String salida_1,
			@Param("entrada_2")String entrada_2, @Param("salida_2")String salida_2);
	
}
