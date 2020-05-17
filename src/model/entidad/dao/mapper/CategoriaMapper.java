package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Categoria;

public interface CategoriaMapper {
	public Categoria CategoriaPorId(@Param("id") int id);
	
	public ArrayList<Categoria> busquedaCategorias();
}
