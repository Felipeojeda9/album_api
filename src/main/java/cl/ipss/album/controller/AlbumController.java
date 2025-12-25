package cl.ipss.album.controller;

import cl.ipss.album.models.Album;
import cl.ipss.album.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    //crear album
    @PostMapping
    public ResponseEntity<Album> crearAlbum(@RequestBody Album album) {
        Album creado = albumService.crearAlbum(album);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    //listar albunes
    @GetMapping
    public ResponseEntity<List<Album>> listarAlbums() {
        return ResponseEntity.ok(albumService.listarAlbums());
    }
 

    //obtener album por id 
    @GetMapping("/{id}")
    public ResponseEntity<Album> obtenerAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.obtenerAlbumPorId(id));
    }

    //actualizar album
    @PutMapping("/{id}")
    public ResponseEntity<Album> actualizarAlbum(
        @PathVariable Long id,
        @RequestBody Album album) {
            return ResponseEntity.ok(albumService.actualizarAlbum(id, album));
        }

        //eliminar album
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarAlbum(@PathVariable Long id) {
            albumService.eliminarAlbum(id);
            return ResponseEntity.noContent().build();
        }
        }
