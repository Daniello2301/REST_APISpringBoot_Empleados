package co.edu.iudigital.app.service.iface;

import java.util.List;

import co.edu.iudigital.app.dto.EmpleadoDto;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Empleado;


public interface IEmpleadoService {
	
	public List<EmpleadoDto> listAll() throws RestException;
	
	public EmpleadoDto listById(Long id) throws RestException;
	
	public EmpleadoDto save(EmpleadoDto empleado) throws RestException;
	
	public void destroyById(Long id);
}
