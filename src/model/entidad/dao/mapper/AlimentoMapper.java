package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Alimento;

public interface AlimentoMapper {
	public Alimento AlimentoPorId(@Param("id") int id);
	
	public ArrayList<Alimento> busquedaAlimentos();
}
