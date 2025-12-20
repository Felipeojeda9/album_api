package cl.ipss.album.repositories;

import cl.ipss.album.models.Album;
import cl.ipss.album.models.Lamina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaminaRepository extends JpaRepository<Lamina, Long> {

    //listar todas las laminas activas de un album
    List<Lamina> findByAlbumAndActiveTrue(Album album);

    //Laminas faltanes (cantidad = 0)
    List<Lamina> findByAlbumAndCantidadEqualsActiveTrue(Album album, Integer cantidad);

    //Laminas repetidas (canitidad > 1)
    List<Lamina> findByAlbumAndCantidadEqualsAndActiveTrue(Album album, Integer cantidad);

}
