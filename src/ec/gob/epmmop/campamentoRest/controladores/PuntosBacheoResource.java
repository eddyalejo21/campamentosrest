package ec.gob.epmmop.campamentoRest.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.gob.epmmop.campamentos.modelo.PuntosBacheo;
import ec.gob.epmmop.campamentos.servicios.PuntosBacheoServicio;

@RequestScoped
@Path("/procesos")
public class PuntosBacheoResource {

	@EJB
	private PuntosBacheoServicio puntosBacheoServicio;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("saludar")
	public String saludar() {
		return "Hola este es un servicio rest en funcionamiento";
	}

	@POST
	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(PuntosBacheo puntosBacheo) {
		PuntosBacheo puntos = new PuntosBacheo();
		try {
			System.out.println("Ingresa al registro");
			puntos.setPunEstado('R');
			puntos.setPunEstadoAh('H');
//			puntosBacheo.setPunEstado('R');
//			puntosBacheo.setPunEstadoAh('A');
			puntosBacheoServicio.registrarCoordenada(puntos);
			
		} catch (Exception e) {
			Logger.getLogger(PuntosBacheoResource.class.getName()).log(Level.SEVERE, null,
					e);
		}
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("ok")
				.build();
	}
}
