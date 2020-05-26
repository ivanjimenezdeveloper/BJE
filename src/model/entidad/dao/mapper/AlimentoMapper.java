package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Alimento;

public interface AlimentoMapper {
	public Alimento AlimentoPorId(@Param("id") int id);
	
	public ArrayList<Alimento> busquedaAlimentos();
	
	public void editaAlimento(@Param("nombre")String nombre, @Param("idCategoria")int idCategoria, 
			@Param("id")int id,@Param("tiempo")int tiempo);

	public void creaAlimento(@Param("nombre")String nombre, @Param("idCategoria")int idCategoria, 
			@Param("tiempo")int tiempo);
	
	public void eliminaAlimento(@Param("id")int idAlimento);
}
