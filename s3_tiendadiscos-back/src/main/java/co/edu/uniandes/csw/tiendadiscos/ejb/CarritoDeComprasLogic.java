/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;

import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.CarritoDeComprasPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class CarritoDeComprasLogic {

    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasLogic.class.getName());

    @Inject
    public CarritoDeComprasPersistence carritoComprasPersitence;

    @Inject
    public UsuarioPersistence usuarioPersistence;

    @Inject
    public ViniloPersistence viniloPersistence;

    @Inject
    public TransaccionPersistence transaccionPersistence;

    /**
     * Se encarga de crear un Carrito de compras en la base de datos
     *
     * @param usuarioId id del usuario el cual será el padre del nuevo
     * CarritoDeCompras.
     * @param entity Objeto de CarritoDeComprasEntity con los datos nuevos y su
     * ID.
     * @return Objeto de CarritoDeComprasEntity con los nuevos datos y su ID.
     * @throws BusinessLogicException si el usuario con id UsuarioId ya tiene
     * asignado un carrito. Si el usuario con id usuarioId no existe.
     */
    public CarritoDeComprasEntity create(Long usuarioId, CarritoDeComprasEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de Carrito de compras.");

        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        if (usuario == null) {
            throw new BusinessLogicException("No existe el usuario con ese id");
        }

        if (carritoComprasPersitence.find(usuarioId) != null) {
            throw new BusinessLogicException("El usuario ya tiene un carrito de compras");
        }
        entity.setUsuario(usuario);
        entity.setTotalCostDeCarritoCompras(0.0);
        LOGGER.log(Level.INFO, "Termina el proceso de cración de Carrito de compras.");

        carritoComprasPersitence.create(entity);
        return entity;
    }

    public CarritoDeComprasEntity get(Long usuarioId) {
        UsuarioEntity user = usuarioPersistence.find(usuarioId);
        if (user == null) {
            throw new WebApplicationException("El Usuario con el id " + usuarioId + " no existe.", 404);
        }

        CarritoDeComprasEntity carrito = usuarioPersistence.find(usuarioId).getCarritoCompras();
        if (carrito == null) {
            throw new WebApplicationException("El usuario con el id " + usuarioId + " no tiene un Carrito de compras", 404);
        }

        return carrito;
    }

    public CarritoDeComprasEntity update(CarritoDeComprasEntity carritoCompras, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar el Carrito de compras del usuario con el id{0}", usuarioId);
        if (usuarioPersistence.find(usuarioId).getCarritoCompras() == null) {
            throw new BusinessLogicException("El usuario no tiene asociado un carrito.");
        }

        CarritoDeComprasEntity newEntity = carritoComprasPersitence.update(carritoCompras);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar el carrito de compras con id{0}", newEntity.getId());
        return newEntity;
    }

    public void delete(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar el carrito de compras del usuario con id={0}", usuarioId);
        CarritoDeComprasEntity carrito = usuarioPersistence.find(usuarioId).getCarritoCompras();
        if (carrito == null) {
            throw new BusinessLogicException("El usuario no tiene un carrito de compras asociado.");
        }

        carritoComprasPersitence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar el carrito de compras del usuario con id{0}", usuarioId);
    }

    public CarritoDeComprasEntity agregarVinilo(Long usuarioId, Long viniloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de agregar el vinilo con id= " + viniloId + " al carrito de compras del usuario con id={0}", usuarioId);

        ViniloEntity vinilo = viniloPersistence.find(viniloId);
        if (vinilo == null) {
            throw new BusinessLogicException("El vinilo no existe");
        }
        CarritoDeComprasEntity carrito = carritoComprasPersitence.findByUserId(usuarioId);
        vinilo.getCarritosDeCompras().add(carrito);
        carrito.setTotalCostDeCarritoCompras(carrito.getTotalCostDeCarritoCompras() + vinilo.getPrecio());
        LOGGER.log(Level.INFO, "Termina el proceso de agregar el vinilo con id= " + viniloId + " al carrito de compras del usuario con id={0}", usuarioId);
        return carrito;
    }
    
     public CarritoDeComprasEntity eliminarVinilo(Long usuarioId, Long viniloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar el vinilo con id= " + viniloId + " al carrito de compras del usuario con id={0}", usuarioId);

        ViniloEntity vinilo = viniloPersistence.find(viniloId);
        if (vinilo == null) {
            throw new BusinessLogicException("El vinilo no existe");
        }
        CarritoDeComprasEntity carrito = carritoComprasPersitence.findByUserId(usuarioId);
        if(!carrito.getVinilosDeCarritoCompras().contains(vinilo)){
            throw new BusinessLogicException("El vinilo no existe en el carrito");
        }
        vinilo.getCarritosDeCompras().remove(carrito);
        carrito.setTotalCostDeCarritoCompras(carrito.getTotalCostDeCarritoCompras() - vinilo.getPrecio());
        LOGGER.log(Level.INFO, "Termina el proceso de eliminar el vinilo con id= " + viniloId + " al carrito de compras del usuario con id={0}", usuarioId);
        return carrito;
    }
}