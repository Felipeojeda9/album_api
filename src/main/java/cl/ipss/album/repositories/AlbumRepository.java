package cl.ipss.album.repositories;
import cl.ipss.album.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface AlbumRepository extends JpaRepository<Album, Long> {

    //listaar solo los albumes activos 
    List<Album> findByActiveTrue();

    //buscar album activo por id
    Optional<Album> findByIdAndActiveTrue(Long id);
}
