package co.edu.iudigital.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.app.dto.EmpleadoDto;
import co.edu.iudigital.app.exception.BadRequestException;
import co.edu.iudigital.app.exception.ErrorDto;
import co.edu.iudigital.app.exception.InternalServerErrorException;
import co.edu.iudigital.app.exception.NotFoundException;
import co.edu.iudigital.app.exception.RestException;
import co.edu.iudigital.app.service.iface.IEmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController{

	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK) // 200
	public ResponseEntity<Object> index() throws RestException{// tambien pueden colocar ResponseEntity<List<EmpleadoDto>>
		LOG.info("index");
		List<EmpleadoDto> response = 
				empleadoService.listAll();
		return ResponseEntity.ok().body(response);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDto> show(@PathVariable Long id) throws RestException {
		EmpleadoDto response =  empleadoService.listById(id);
		return ResponseEntity.ok().body(response);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED) //201
	public ResponseEntity<EmpleadoDto> create(@RequestBody EmpleadoDto empleado) throws RestException{
		try {
			EmpleadoDto response = 
					empleadoService.save(empleado);
			return new ResponseEntity<>(response, 
					HttpStatus.CREATED);
		} catch (BadRequestException e) {
			LOG.error("Error " + e);
			throw e;
		} catch (Exception e) {
			throw new InternalServerErrorException(
						ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
								"Error de backend", HttpStatus.INTERNAL_SERVER_ERROR.value())
					);
		}
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws RestException {
		EmpleadoDto empleado = empleadoService.listById(id);
		if(empleado == null) {
			throw new NotFoundException(
					ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
							"No encontró prospecto", HttpStatus.NOT_FOUND.value())
				);
		}else {
			empleadoService.destroyById(id);
		}
		
	}
}
