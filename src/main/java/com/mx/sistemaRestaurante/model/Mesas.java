package com.mx.sistemaRestaurante.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE MESAS(
	ID NUMBER PRIMARY KEY,
	NUM_MESA NUMBER NOT NULL,
	NUM_SILLAS NUMBER NOT NULL,
	ID_MESERO NUMBER NOT NULL,
	FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
);*/

@Entity
@Table(name="MESAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesas {

	@Id
	private Long id;
	private Integer numMesa; 
	private Integer numSillas;
	
	//Cardinalidad
	//Muchas mesas pertenecen a un mesero
	//fetch, indicamos como debe ser cargada la entidad
	//FetchType, indicamos que la relacion va a ser cargada al momento.
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="ID_MESERO")
	Meseros mesero;
	
}
