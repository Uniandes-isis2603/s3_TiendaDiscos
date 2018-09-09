package co.edu.uniandes.csw.tiendadiscos.persistance;

import co.edu.uniandes.csw.tiendadiscos.entities.BillingInformationEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kevin Blanco
 */
@Stateless
public class BillingInformationPersistence {

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(BillingInformationPersistence.class.getName());

    public BillingInformationEntity create(BillingInformationEntity billingInformationEntity) {
        LOGGER.log(Level.INFO, "Creando un billing nuevo");
        em.persist(billingInformationEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un billing nuevo");
        return billingInformationEntity;
    }

    public BillingInformationEntity find(Long billingId) {
        LOGGER.log(Level.INFO, "Consultando billing con id={0}", billingId);
        return em.find(BillingInformationEntity.class, billingId);

    }

    public BillingInformationEntity update(BillingInformationEntity billingInformationEntity) {
        LOGGER.log(Level.INFO, "Actualizando billing con id={0}", billingInformationEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar billing con id = {0}", billingInformationEntity.getId());

        return em.merge(billingInformationEntity);

    }

    public void delete(Long billingId) {
        LOGGER.log(Level.INFO, "Borrando billing con id={0}", billingId);
        BillingInformationEntity billingEntity = find(billingId);
        em.remove(billingEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar billing con id = {0}", billingId);

    }

}
