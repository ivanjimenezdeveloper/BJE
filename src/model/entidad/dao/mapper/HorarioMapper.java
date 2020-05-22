package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Horario;

public interface HorarioMapper {

	public Horario horarioPorId(@Param("id") int id);

	public ArrayList<Horario> horariosPorRestaurante(@Param("restaurante") int idRestaurante);

	public void creaHorarioGeneral(@Param("activo") int activo, @Param("mes") int mes, @Param("anyo") int anyo);

}
