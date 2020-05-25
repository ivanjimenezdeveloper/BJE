package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.HoraVenta;

public interface HoraVentaMapper {

	public HoraVenta ventaPorId(@Param("id") int id, @Param("hora") int hora);

	public ArrayList<HoraVenta> getVentas();

	public ArrayList<HoraVenta> getVentasPorRestauranteFecha(@Param("restaurante") int idRestaurante,
			@Param("fecha") String fecha);

	public HoraVenta existeVenta(@Param("idFactura") int idFactura, @Param("idRestaurante") int idRestaurante,
			@Param("hora") int hora);

	public void insertaHoraVenta(@Param("venta") Double venta, @Param("hora") int hora, @Param("idFactura") int idFactura,
			@Param("idRestaurante") int idRestaurante);

	public void editaHoraVenta(@Param("venta") Double venta, @Param("hora") int hora, @Param("idFactura") int idFactura,
			@Param("idRestaurante") int idRestaurante);
}
