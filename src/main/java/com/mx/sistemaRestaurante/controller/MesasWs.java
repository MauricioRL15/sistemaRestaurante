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

import com.mx.sistemaRestaurante.model.Mesas;
import com.mx.sistemaRestaurante.service.MesasImp;

@RestController
@RequestMapping("MesasWs")
@CrossOrigin
public class MesasWs {

	@Autowired
	MesasImp mesasImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping("listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// http://localhost:9000/MesasWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesas) {
		String validaRespuestas = mesasImp.guardar(mesas);
		if (validaRespuestas.equals("Error: No existe el Id de ese Mesero"))
			return new ResponseEntity<>("Ese ID de ese mesero no existe", HttpStatus.OK);
		else if (validaRespuestas.equals("El id de la mesa ya existe"))
			return new ResponseEntity<>("Ese ID de ese mesa ya existe", HttpStatus.OK);
		else if (validaRespuestas.equals("Esa mesa ya esta asignada a un Mesero"))
			return new ResponseEntity<>("Esa mesa ya fue asignada a un Mesero", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesas, HttpStatus.CREATED);
	}

	// http://localhost:9000/MesasWs/buscar
	@PostMapping("buscar")
	public ResponseEntity<?> buscar(@RequestBody Mesas mesas) {

		Mesas mesa = mesasImp.buscarPorId(mesas.getId());

		return new ResponseEntity<>(mesa, HttpStatus.OK);
	}

	// http://localhost:9000/MesasWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Mesas mesas) {
		boolean respuesta = mesasImp.editar(mesas);
		if (!respuesta)
			return new ResponseEntity<>("El ID de esa mesa no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesas, HttpStatus.CREATED);

	}

	//http://localhost:9000/MesasWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Mesas mesas) {
		boolean response = mesasImp.eliminar(mesas.getId());
		if (!response)
			return new ResponseEntity<>("Error: NO existe mesa con ese ID.", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("Mesa eliminado exitosamente.", HttpStatus.OK);
	}

}
