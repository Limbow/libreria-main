package egg.web.libreria.servicios;

import egg.web.libreria.entidades.Foto;
import egg.web.libreria.errors.ErrorServicio;
import egg.web.libreria.repositorios.FotoRepositorio;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fR;

    @Transactional
    public Foto guardar(MultipartFile archivo) throws ErrorServicio {
        
        if (archivo != null && !archivo.isEmpty()) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                
                return fR.save(foto);
            } catch (IOException e) {
                throw new ErrorServicio("Error al guardar la foto: " + e.getMessage());
            }
        }else{
            throw new ErrorServicio("El archivo no puede estar vacío");
        }
    }

    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                
                if (idFoto != null) {
                    Optional<Foto> respuesta = fR.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }
                
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fR.save(foto);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
}
