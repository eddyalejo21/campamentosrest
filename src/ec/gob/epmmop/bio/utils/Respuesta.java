package ec.gob.epmmop.bio.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {
	
	private String mensaje;
	private boolean ok;
	private Integer estado;

}
