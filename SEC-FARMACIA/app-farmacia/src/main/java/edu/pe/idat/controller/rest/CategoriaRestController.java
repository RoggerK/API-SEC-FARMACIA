package edu.pe.idat.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.pe.idat.model.Categoria;
import edu.pe.idat.service.CategoriaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/categoria")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaService servicio;

	//Listo posma y web
	@GetMapping
	public ResponseEntity<Object> buscartTodo() {

		List<Categoria> listCategoria = servicio.listarCategoria();
		return new ResponseEntity<>(listCategoria, HttpStatus.OK);
	}
	
	
	//Listo solo en posma
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
		Categoria cat = servicio.buscarCategoriaId(id);
		if (cat == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Producto no encontrado, id porporcionado no es correcto");
		return new ResponseEntity<Object>(cat, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Categoria c) {
		servicio.crearCategoria(c);
		return new ResponseEntity<Object>("Categoria creada correctamente", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Categoria c) {
		servicio.crearCategoria(c);
		return new ResponseEntity<Object>("Producto actualizada correctamente", HttpStatus.OK);
	}
	
	//Listo
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") Integer id) {
		servicio.eliminarCategoriaId(id);
		return new ResponseEntity<Object>("Categoria eliminado correctamente", HttpStatus.OK);
	}
	
}
