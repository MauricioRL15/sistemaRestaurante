package com.mx.sistemaRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaRestaurante.dao.MesasDao;
import com.mx.sistemaRestaurante.dao.MeserosDao;
import com.mx.sistemaRestaurante.model.Mesas;
import com.mx.sistemaRestaurante.model.Meseros;

@Service
public class MesasImp {
	@Autowired
	MesasDao mesasDao;

	@Autowired
	MeserosDao meserosDao;

	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return mesasDao.findAll();
	}

	// GUARDAR(VALIDAR QUE EL ID Y NUM_MESA NO SE REPITA, ID_MESERO EXISTA):
	@Transactional
	public String guardar(Mesas mesas) {
		String Response = "";
		boolean banderaMesas = false;
		boolean banderaMeseros = false;

		// Validar que el id de mesero SI EXISTA para poder asignarle una mesa:
		for (Meseros me : meserosDao.findAll()) {
			if (me.getId().equals(mesas.getMesero().getId())) {
				banderaMeseros = true;
				// Validar que el id de mesas y el numero de la mesa NO se repian
				for (Mesas m : mesasDao.findAll()) {
					if (m.getId().equals(mesas.getId())) {
						Response = "El id de la mesa ya existe";
						banderaMesas = true;
						break;
					} else if (m.getNumMesa().equals(mesas.getNumMesa())) {
						Response = "Esa mesa ya esta asignada a un Mesero";
						banderaMesas = true;
						break;
					}
				}
				break;
			}
		}
		if (!banderaMeseros) {
			Response = "Error: No existe el Id de ese Mesero";

		} else if (!banderaMesas) {
			mesasDao.save(mesas);
			Response = "Mesa asignada Correctamente";
		}
		return Response;
	}

	// Buscar por ID
	@Transactional(readOnly = true)
	public Mesas buscarPorId(Long id) {
		return mesasDao.findById(id).orElse(null);
	}

	// EDITAR(VALIDAR QUE EL ID EXISTA):
	@Transactional
	public boolean editar(Mesas mesas) {
		boolean existe = false;
		for (Mesas mesa : mesasDao.findAll()) {
			if (mesa.getId().equals(mesas.getId())) {
				existe = true;
				mesasDao.save(mesas);
				break;
			}
		}
		return existe;

	}

	// ELIMINAR(VALIDAR QUE EL ID EXISTA)
	@Transactional
	public boolean eliminar(Long id) {
		boolean existe = false;
		for (Mesas m : mesasDao.findAll()) {
			if (m.getId().equals(id)) {
				mesasDao.deleteById(id);
				existe = true;
				break;
			}
		}
		return existe;
	}

}
