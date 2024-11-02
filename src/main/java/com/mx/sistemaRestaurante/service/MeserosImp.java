package com.mx.sistemaRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaRestaurante.dao.MeserosDao;
import com.mx.sistemaRestaurante.model.Meseros;

@Service
public class MeserosImp {

	@Autowired
	MeserosDao meserosDao;

	@Transactional(readOnly = true)
	public List<Meseros> listar() {
		return meserosDao.findAll();
	}

	// Guardar (VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA:

	@Transactional
	public String guardar(Meseros meseros) {
		String respuesta = "";
		boolean existe = false;
		String nombre = "";
		for (Meseros m : meserosDao.findAll()) {
			nombre = m.getNombre() + m.getApp() + m.getApm();
			if (m.getId().equals(meseros.getId())) {
				existe = true;
				respuesta = "El ID insertado ya existe";
				break;
			} else if (nombre.equalsIgnoreCase(meseros.getNombre() + meseros.getApp() + meseros.getApm())) {
				existe = true;
				respuesta = "El Nombre Completo insertado ya existe";
			}
		}
		if (!existe) {
			meserosDao.save(meseros);
			respuesta = "Se guardo correctamente el Mesero";
		}
		return respuesta;
	}

	// Buscar por ID
	@Transactional(readOnly = true)
	public Meseros buscarXId(Long id) {
		return meserosDao.findById(id).orElse(null);
	}

	// EDITAR(VALIDAR QUE EL ID EXISTA):
	@Transactional
	public boolean editar(Meseros meseros) {
		boolean existe = false;
		for (Meseros me : meserosDao.findAll()) {
			if (me.getId().equals(meseros.getId())) {
				existe = true;
				meserosDao.save(meseros);
				break;
			}
		}
		return existe;

	}

	@Transactional
	public boolean eliminar(Long id) {
		boolean existe = false;
		for (Meseros m : meserosDao.findAll()) {
			if (m.getId().equals(id)) {
				meserosDao.deleteById(id);
				existe = true;
				break;
			}
		}
		return existe;
	}

}
