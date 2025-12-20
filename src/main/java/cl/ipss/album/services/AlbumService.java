package cl.ipss.album.services;

import cl.ipss.album.models.Album;
import cl.ipss.album.repositories.AlbumRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    
    //crear album
    public Album crearAlbum(Album album) {
        if (album.getNombre() == null || album.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del album no es obligatorio");
        }
        return albumRepository.save(album);
    }
    //listar albumes activos
    public List<Album> listarAlbums() {
        return albumRepository.findByActiveTrue();
    }
    //obtener album por id
    public Album obtenerAlbumPorId(Long id) {
        return albumRepository.findByIdAndActiveTrue(id)
        .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));
    }
    //actualizar album
    public Album actualizarAlbum(Long id, Album datosActualizados) {
        Album album = obtenerAlbumPorId(id);

        album.setNombre(datosActualizados.getNombre());
        album.setImagen(datosActualizados.getImagen());
        album.setFechaLanzamiento(datosActualizados.getFechaLanzamiento());
        album.setTipoAlbum(datosActualizados.getTipoAlbum());

        return albumRepository.save(album);
    }

    //eliminacion logica
    public void eliminarAlbum( Long id) {
        Album album = obtenerAlbumPorId(id);

        album.setActive(false);
        album.setDeletedAt(LocalDateTime.now());

        albumRepository.save(album);
    }
    }

