package cl.ipss.album.services;

import cl.ipss.album.models.Album;
import cl.ipss.album.models.Lamina;
import cl.ipss.album.repositories.AlbumRepository;
import cl.ipss.album.repositories.LaminaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaminaService {
    
    private final LaminaRepository laminaRepository;
    private final AlbumRepository albumRepository;

    public LaminaService(LaminaRepository laminaRepository, AlbumRepository albumRepository) {
        this.laminaRepository = laminaRepository;
        this.albumRepository = albumRepository;
    }

    // agregar lamina a un album
    public Lamina agregarLamina(Long albumId, Lamina lamina) {
        Album album = albumRepository.findByIdAndActiveTrue(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));

    if (lamina.getCantidad() == null || lamina.getCantidad() < 0) {
        throw new IllegalArgumentException("La cantidad debe ser 0 o mayor");
    }

    lamina.setAlbum(album);
    return laminaRepository.save(lamina);
}

// Listar laminas activas de un album
public List<Lamina> listarLaminasPorAlbum(Long albumId) {
    Album album = albumRepository.findByIdAndActiveTrue(albumId)
    .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));
    
    return laminaRepository.findByAlbumAndActiveTrue(album);
}

//Laminas faltantes (cantidad = 0 ) de un album
public List <Lamina> obtenerLaminasFaltantes(Long albumId) {
    Album album = albumRepository.findByIdAndActiveTrue(albumId) 
    .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));

    return laminaRepository.findByAlbumAndCantidadAndActiveTrue(album, 0);

}

//laminas repetidas (cantidad > 1)
public List<Lamina> obtenerLaminasRepetidas(Long albumId) {
    Album album = albumRepository.findByIdAndActiveTrue(albumId)
    .orElseThrow(() -> new IllegalArgumentException("Album no encontrado"));

    return laminaRepository.findByAlbumAndCantidadGreaterThanAndActiveTrue(album, 1);

}
//actualiza lamina
public Lamina actualizarLamina(Long id, Lamina datosActualizados) {
    Lamina lamina = laminaRepository.findById(id)
       .orElseThrow(() -> new IllegalArgumentException("Lamina no encontrada"));

       if (!lamina.isActive()) {
        throw new IllegalArgumentException("no se puede actualizar una lamina eliminada");
       }

       lamina.setNombre(datosActualizados.getNombre());
       lamina.setNumero(datosActualizados.getNumero());
       lamina.setImagen(datosActualizados.getImagen());
       lamina.setCantidad(datosActualizados.getCantidad());

       return laminaRepository.save(lamina);
} 

//eliminacion logica
public void eliminarLamina(Long id) {
    Lamina lamina = laminaRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("Lamina no encontrada"));

    lamina.setActive(false);
    lamina.setDeletedAt(LocalDateTime.now());

    laminaRepository.save(lamina);
}
}
