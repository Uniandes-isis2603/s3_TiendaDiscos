/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.BillingInformationDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.MedioDePagoDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.MedioDePagoLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.MedioDePagoEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Kevin Blanco
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MedioDePagoResource {

    private static final Logger LOGGER = Logger.getLogger(MedioDePagoResource.class.getName());

    @Inject
    private MedioDePagoLogic tarjetaLogic;

    @POST
    public MedioDePagoDTO createTarjetaCredito(@PathParam("usuariosId") Long usuariosId, MedioDePagoDTO tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: input: {0}", tarjeta.toString());
        MedioDePagoDTO nuevoTarjetaDTO = new MedioDePagoDTO(tarjetaLogic.createTarjeta(usuariosId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource createTarjeta: output: {0}", nuevoTarjetaDTO.toString());
        return nuevoTarjetaDTO;
    }

    /**
     * tarjetas de credito del billing del usuario
     *
     * @param usuarioId
     * @return
     */
    @GET
    public List<MedioDePagoDTO> getTarjetasCredito(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource getReviews: input: {0}", usuariosId);
        List<MedioDePagoDTO> listaDTOs = listEntity2DTO(tarjetaLogic.getTarjetas(usuariosId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * tarjeta en especifico del usuario
     *
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return
     */
    @GET
    @Path("{tarjetaCreditoId: \\d+}")
    public MedioDePagoDTO getTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaCreditoId") Long tarjetaCreditoId) {
        LOGGER.log(Level.INFO, "TarjetaCreditoResource getTarjetaCredito: input: {0}", tarjetaCreditoId);
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaCreditoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing/tarjetasDeCredito" + tarjetaCreditoId + " no existe.", 404);
        }
        MedioDePagoDTO tarjetaDTO = new MedioDePagoDTO(entity);
        LOGGER.log(Level.INFO, "TarjetaCreditoResource getTarjetaCredito: output: {0}", tarjetaDTO.toString());
        return tarjetaDTO;
    }

    /**
     * actualizar datos tarjeta
     *
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     * @return
     */
    @PUT
    @Path("{tarjetaId: \\d+}")
    public MedioDePagoDTO updateTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaId") Long tarjetaCreditoId, MedioDePagoDTO tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReviewResource updateTarjetaCredito: input: usuariosId: {0} , tarjetaCreditoId: {1} , review:{2}", new Object[]{usuariosId, tarjetaCreditoId, tarjeta.toString()});
        if (tarjetaCreditoId.equals(tarjeta.getId())) {
            throw new BusinessLogicException("Los ids de la Tarjeta no coinciden.");
        }
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaCreditoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing/tarjetasDeCredito/" + tarjetaCreditoId + " no existe.", 404);

        }
        MedioDePagoDTO tarjetaDTO = new MedioDePagoDTO(tarjetaLogic.updateTarjeta(usuariosId, tarjetaCreditoId, tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource updateTarjetaCredito: output:{0}", tarjetaDTO.toString());
        return tarjetaDTO;
    }

    /**
     * eliminar tarjeta en especifico del usuario
     *
     * @param usuariosId id usuario
     * @param tarjetaCreditoId id tarjeta
     */
    @DELETE
    @Path("{tarjetaId: \\d+}")
    public void deleteTarjetaCredito(@PathParam("usuariosId") Long usuariosId, @PathParam("tarjetaId") Long tarjetaId) throws BusinessLogicException {
        MedioDePagoEntity entity = tarjetaLogic.getTarjeta(usuariosId, tarjetaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/billing/tarjetasDeCredito/" + tarjetaId + " no existe.", 404);
        }
        tarjetaLogic.deleteTarjeta(usuariosId, tarjetaId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TarjetaCreditoEntity a una
     * lista de objetos TarjetaCreditoDTO (json)
     *
     * @param entityList corresponde a la lista de tarjetas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de tarjetas en forma DTO (json)
     */
    private List<MedioDePagoDTO> listEntity2DTO(List<MedioDePagoEntity> entityList) {
        List<MedioDePagoDTO> list = new ArrayList<MedioDePagoDTO>();
        for (MedioDePagoEntity entity : entityList) {
            list.add(new MedioDePagoDTO(entity));
        }
        return list;
    }

}