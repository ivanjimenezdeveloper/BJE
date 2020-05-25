package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Factura;

public interface FacturaMapper {

	public Factura facturaPorId(@Param("id") int id);

	public ArrayList<Factura> getFacturas();

	public ArrayList<Factura> FacturasPorRestaurante(@Param("restaurante") int idRestaurante);
}
