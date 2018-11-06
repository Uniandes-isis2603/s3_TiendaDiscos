package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Andrés Felipe Hernández León
 */
public class ViniloDTO implements Serializable 
{

    private static final long serialVersionUID = 1L;
  
    /**
     * id unico del vinilo.
     */
    private Long id;

    /**
     * Nombre del vinilo.
     */
    private String nombre;
    
    /**
     * Nombre del artista del vinilo.
     */
    private String artista;
    
    /**
     * Fecha de lanzamiento del vinilo.
     */
    private Date fechaLanzamiento;
    
    /**
     * Productora que lanzó el vinilo.
     */
    private String productora;
    
    /**
     * Información adicional del proveedor. 
     */
    private String informacionAdicional;
    
    /**
     * URI que redirige a la vista previa del vinilo si esta disponible.
     */
    private String previewURI;
    
    /**
     * Calificación promedio del vinilo.
     */
    private Double calificacion;

    /**
     * Precio del vinilo.
     */
    private Double precio;
    
    /**
     * Conexión con el usuario.
     */
    private UsuarioDTO usuario;
    
    /**
     * Empty constructor.
     */
    public ViniloDTO()
    {}
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param viniloEntity: Es la entidad que se va a convertir a DTO
     */
    public ViniloDTO(ViniloEntity viniloEntity)
    {
        if (viniloEntity != null)
        {
            this.id = viniloEntity.getId();
            this.artista = viniloEntity.getArtista();
            this.fechaLanzamiento = viniloEntity.getFechaLanzamiento();
            this.informacionAdicional = viniloEntity.getInformacionAdicional();
            this.nombre = viniloEntity.getNombre();
            this.previewURI = viniloEntity.getPreviewURI();
            this.productora = viniloEntity.getProductora();
            this.calificacion = viniloEntity.getCalificacion();                    
            this.precio = viniloEntity.getPrecio();
            if(viniloEntity.getUsuario() != null)
                this.usuario = new UsuarioDTO(viniloEntity.getUsuario());
        }
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ViniloEntity toEntity()
    {
        //Creo el objeto entity vacio.
        ViniloEntity viniloEntity = new ViniloEntity();
        //Ahora le asigno los valores.
        viniloEntity.setArtista(this.artista);
        viniloEntity.setFechaLanzamiento(this.fechaLanzamiento);
        viniloEntity.setId(this.id);
        viniloEntity.setInformacionAdicional(this.informacionAdicional);
        viniloEntity.setNombre(this.nombre);
        viniloEntity.setPreviewURI(this.previewURI);
        viniloEntity.setProductora(this.productora);
        viniloEntity.setCalificacion(this.calificacion);
        viniloEntity.setPrecio(this.precio);
        if(this.usuario != null)
            viniloEntity.setUsuario(this.usuario.toEntity());
        
        return viniloEntity;
    }
    
    /**
     * Obtiene el atributo nombre. 
     * @param nombre  atributo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el valor del del atributo artista.
     * @param artista valor del atributo.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Modifica el valor del atributo fecha de lanzamiento.
     * @param fechaLanzamiento nuevo valor del atributo.
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Modifica el valor del atributo productora.
     * @param productora nuevo valor del atributo.
     */
    public void setProductora(String productora) {
        this.productora = productora;
    }

    /**
     * Modifica la información adicional dada por el proveedor. 
     * @param informacionAdicional nuevo valor del atributo.
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * Modifica el valor del atributo previewURI.
     * @param previewURI nuevo valor del atributo.
     */
    public void setPreviewURI(String previewURI) {
        this.previewURI = previewURI;
    }
    
    /**
     * Establece el valor del atributo calificación.
     * @param calificacion nuevo valor de la calificación.
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Establece el precio del vinilo.
     * 
     * @param precio nuevo valor del atributo.
     */
    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }
    
    /**
     * Establece el usuario.
     * @param usuario  nuevo valor del atributo.
     */
    public void setUsuario(UsuarioDTO usuario)
    {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene el atributo calificacion.
     * @return atributo calificación.
     */
    public Double getCalificacion() {
        return calificacion;
    }
    
    /**
     * Obtiene el atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el atributo artista.
     * @return atributo artista.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Obtiene el atributo fecha de lanzamiento.
     * @return atributo fechaLanzamiento.
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    
    /**
     * Obtiene el atributo productora.
     * @return atributo productora.
     */
    public String getProductora() {
        return productora;
    }

    /**
     * Obtiene la información adicional del vinilo.
     * @return atributo informacionAdicional.
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * Obtiene el atributo previewURI.
     * @return atributo previewURI.
     */
    public String getPreviewURI() {
        return previewURI;
    }  
 
    /**
     * Obtiene el id único del vinilo.
     * @return atributo id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Obtiene el precio del vinilo.
     * @return atributo precio.
     */
    public Double getPrecio()
    {
        return precio;
    }
    
    /**
     * Obtiene la conexión con el usuario.
     * @return atributo usuario.
     */
    public UsuarioDTO getUsuario()
    {
        return usuario;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
}