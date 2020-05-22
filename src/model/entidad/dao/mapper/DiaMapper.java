package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Dia;

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
			@Param("usuario") int idUsuario);
	
	public int existeDia(@Param("usuario")int idUsuario, @Param("fecha")String fecha);

	
}
