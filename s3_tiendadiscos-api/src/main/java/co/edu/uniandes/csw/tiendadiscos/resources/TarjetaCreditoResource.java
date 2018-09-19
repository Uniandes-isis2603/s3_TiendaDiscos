/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.TarjetaCreditoDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.TarjetaCreditoLogic;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



/**
 *
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaCreditoResource {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaCreditoResource.class.getName());

    @Inject
    private TarjetaCreditoLogic tarjetaLogic;
    
    @POST
    public TarjetaCreditoDTO createTarjetaCredito(@PathParam("usuariosId") Long usuariosId, TarjetaCreditoDTO tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: input: {0}", tarjeta.toString());
        TarjetaCreditoDTO nuevoTarjetaDTO = new TarjetaCreditoDTO(tarjetaLogic.createTarjeta(usuariosId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: output: {0}", nuevoTarjetaDTO.toString());
        return nuevoTarjetaDTO;
    }
    
    /**
     * tarjetas de credito del billing del usuario
     * @param usuarioId
     * @return 
     */
    @GET
    public List<TarjetaCreditoDTO> getTarjetasCredito(@PathParam("usuariosId") Long usuariosId) {

        return new ArrayList<TarjetaCreditoDTO>();
    }
    
    /**
     * tarjeta en especifico del usuario
     * @param usuariosId id usuario 
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @GET
    @Path("{tarjetaCreditoId: \\d+}")
    public TarjetaCreditoDTO getTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {

        return new TarjetaCreditoDTO();
    }

    /**
     * actualizar datos tarjeta
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return 
     */
    @PUT
    @Path("{tarjetaId: \\d+}")
    public TarjetaCreditoDTO putTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaId") Long tarjetaCreditoId, TarjetaCreditoDTO tarjeta) {
        return tarjeta;
    }
    
    /**
     * eliminar tarjeta en especifico del usuario
     * @param usuariosId id usuario
     * @param tarjetaCreditoId  id tarjeta 
     */
    @DELETE
    @Path("{tarjetaId: \\d+}")
    public void deleteTarjetaCredito( @PathParam("tarjetaId") Long tarjetaId) {
    
    }


}
