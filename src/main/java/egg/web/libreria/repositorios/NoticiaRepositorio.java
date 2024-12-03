/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package egg.web.libreria.repositorios;

import egg.web.libreria.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {
    
    @Query("SELECT a FROM Noticia a WHERE a.id = :id")
    public Noticia buscarPorId(@Param("id") Integer id);
}
