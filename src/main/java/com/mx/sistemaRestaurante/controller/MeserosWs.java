package com.mx.sistemaRestaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaRestaurante.model.Meseros;
import com.mx.sistemaRestaurante.service.MeserosImp;

@RestController
@RequestMapping("MeserosWs")
@CrossOrigin
public class MeserosWs {

	@Autowired
	MeserosImp meserosImp;

	// URL: http://localhost:9000/MeserosWs/listar
	@GetMapping("listar")
	public List<Meseros> listar() {
		return meserosImp.listar();
	}

	// URL: http://localhost:9000/MeserosWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Meseros meseros) {
		String response = meserosImp.guardar(meseros);
		if (response.equals("El ID insertado ya existe"))
			return new ResponseEntity<>("El ID de ese mesero ya existe", HttpStatus.OK);
		else if (response.equals("El Nombre Completo insertado ya existe"))
			return new ResponseEntity<>("El Nombre Completo del mesero insertado ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(meseros, HttpStatus.CREATED);
	}

	// URL: http://localhost:9000/MeserosWs/buscar
	@PostMapping("buscar")
	public ResponseEntity<?> buscar(@RequestBody Meseros meseros) {

		Meseros mesero = meserosImp.buscarXId(meseros.getId());

		return new ResponseEntity<>(mesero, HttpStatus.OK);
	}

	// URL: http://localhost:9000/MeserosWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Meseros meseros) {
		boolean respuesta = meserosImp.editar(meseros);
		if (!respuesta)
			return new ResponseEntity<>("El ID de ese mesero no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(meseros, HttpStatus.CREATED);

	}

	// URL: http://localhost:9000/MeserosWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Meseros meseros) {
		boolean response = meserosImp.eliminar(meseros.getId());
		if (!response)
			return new ResponseEntity<>("Error: NO existe mesero con ese ID.", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("Mesero eliminado exitosamente.", HttpStatus.OK);
	}

}
