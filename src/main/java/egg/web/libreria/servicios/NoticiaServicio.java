/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Foto;
import egg.web.libreria.entidades.Noticia;
import egg.web.libreria.errors.ErrorServicio;
import egg.web.libreria.repositorios.NoticiaRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pc
 */
@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio nR;
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void cargarNoticia(String texto, MultipartFile archivo) throws ErrorServicio {
        
        validar(texto);
        
        
        Noticia noticia = new Noticia();
        Foto foto = fotoServicio.guardar(archivo);
        noticia.setFoto(foto);
        noticia.setTexto(texto);
        noticia.setFecha(new Date());
        
        nR.save(noticia);
    }

    public void validar(String text) throws ErrorServicio {
        if (text == null || text.isEmpty()) {
            throw new ErrorServicio("El texto de la noticia no puede esar vacio");
        }
    }
    
    @Transactional
    public void borrarNoticia(Noticia noticia){
        
        nR.delete(noticia);
    }

}
