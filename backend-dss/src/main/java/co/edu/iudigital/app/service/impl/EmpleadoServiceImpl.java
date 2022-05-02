package co.edu.iudigital.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.iudigital.app.dto.EmpleadoDto;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.NotFoundException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.model.Empleado;
import co.edu.iudigital.app.model.Usuario;
import co.edu.iudigital.app.repository.IEmpleadoRepository;
import co.edu.iudigital.app.repository.IUsuarioRepository;
import co.edu.iudigital.app.service.iface.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

	@Autowired
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoDto> listAll() throws RestException{
		List<Empleado> empleados = empleadoRepository.findAll();
		if(empleados == null) {
			throw new NotFoundException(ErrorDto.getErrorDto(
					HttpStatus.NOT_FOUND.getReasonPhrase(), 
					"No encontró", 
					HttpStatus.NOT_FOUND.value()
					)
				);
		}
		List<EmpleadoDto> empleadosDto = new ArrayList<>();
		for(Empleado e:empleados) {
			EmpleadoDto empleadoDto = new EmpleadoDto();
			empleadoDto.setId(e.getId());
			empleadoDto.setNombre(e.getNombre());
			empleadoDto.setApellido(e.getApellido());
			empleadoDto.setSueldo(e.getSueldo());
			empleadoDto.setIdUsuario(e.getUsuario().getId());
			empleadosDto.add(empleadoDto);
		}
		return empleadosDto;
	}

	@Override
	@Transactional(readOnly = true)
	public EmpleadoDto listById(Long id) throws RestException{
		Empleado empleado = empleadoRepository.findById(id).orElse(null);
		EmpleadoDto empleadoDto = new EmpleadoDto();
		empleadoDto.setId(empleado.getId());
		empleadoDto.setNombre(empleado.getNombre());
		empleadoDto.setApellido(empleado.getApellido());
		empleadoDto.setSueldo(empleado.getSueldo());
		return empleadoDto;
	}

	@Override
	@Transactional
	public EmpleadoDto save(EmpleadoDto empleado) throws RestException{
		Empleado empl = new Empleado();
		empl.setNombre(empleado.getNombre());
		empl.setApellido(empleado.getApellido());
		empl.setSueldo(empleado.getSueldo());
		Optional<Usuario> usuario = 
				usuarioRepository
				.findById(empleado.getIdUsuario());
		if(!usuario.isPresent()) {
			// TODO: COLOCAR EXCEPCION
			return null;
		}
		empl.setUsuario(usuario.get());
		Empleado emplGuardado = 
				empleadoRepository.save(empl);
		empleado.setId(emplGuardado.getId());
		return empleado;
	}

	@Override
	@Transactional
	public void destroyById(Long id) {
		empleadoRepository.deleteById(id);
	}

}
