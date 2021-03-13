package ec.gob.epmmop.bio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {

	private String fechaRegistro;

	private String nombre;
	private String cedula;
	private String fechaNac;
	private String celular;
	private String mail;
	private String codigo;

}
