/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;



import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.Consumes;

import javax.ws.rs.*;
/**
 *
 * @author Camilo Andres Salinas Martinez 
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    //@Inject
    //EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario){
        return usuario;
    }
    
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosId") Long usuarioId,UsuarioDTO usuario){
        
        return usuario;
    }
    
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("usuariosId") Long usuarioId){
        
        return null;
    }
    
    @GET
    public List<UsuarioDTO> getUsuarios(){
        
        return new ArrayList<>();
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuarios(@PathParam("usuariosId") Long usuarioId){
        
        
    } 
    
    @Path("{usuariosId: \\d+}/billing")
    public Class<BillingInformationResource> getReviewResource(@PathParam("usuariosId") Long booksId) {
        
        return BillingInformationResource.class;
    }
    
    @Path("{usuariosId: \\d+}/comentarios")
    public Class<ComentarioResource> getComentariosResource(@PathParam("usuariosId") Long booksId) {
        
        return ComentarioResource.class;
    }

    @Path("{usuariosId: \\d+}/wishlist")
    public Class<WishListResource> getWishListResource(@PathParam("usuariosId") Long booksId) {
        
        return WishListResource.class;
    }
    
}
