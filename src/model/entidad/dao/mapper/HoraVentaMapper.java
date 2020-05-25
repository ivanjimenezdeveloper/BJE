package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import model.entidad.HoraVenta;

public interface HoraVentaMapper {

	public HoraVenta ventaPorId(@Param("id") int id,@Param("hora") int hora);

	public ArrayList<HoraVenta> getVentas();

}
