package cl.ipss.album.controller;

import cl.ipss.album.models.Lamina;
import cl.ipss.album.services.LaminaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/laminas")
    public class LaminaController {

        private final LaminaService laminaService;

        public LaminaController(LaminaService laminaService) {
            this.laminaService = laminaService;
        }

        //agregar lamina a un album
        @PostMapping("/album/{albumId}")
        public ResponseEntity<Lamina> agregarLamina(
            @PathVariable Long albumId,
            @RequestBody Lamina lamina) {

                Lamina creada = laminaService.agregarLamina(albumId, lamina);
                return new ResponseEntity<>(creada, HttpStatus.CREATED);
            }

        //listar laminas por album
        @GetMapping("/album/{albumId}")
        public ResponseEntity<List<Lamina>> listarPorAlbum(@PathVariable Long albumId) {
            return ResponseEntity.ok(laminaService.listarLaminasPorAlbum(albumId));
        }

        //laminas faltantes (cantidad = 0)
        @GetMapping("/album/{albumId}/faltantes")
        public ResponseEntity<List<Lamina>> faltantes(@PathVariable Long albumId) {
            return ResponseEntity.ok(laminaService.obtenerLaminasFaltantes(albumId));
        }

        //laminas repetidas (cantidad > 1)
        @GetMapping("/album/{albumId}/repetidas")
        public ResponseEntity<List<Lamina>> repetidas(@PathVariable Long albumId) {
            return ResponseEntity.ok(laminaService.obtenerLaminasRepetidas(albumId));
        }

        //actualizar lamina
        @PutMapping("/{id}")
        public ResponseEntity<Lamina> actualizar(
            @PathVariable Long id,
            @RequestBody Lamina lamina) {

                return ResponseEntity.ok(laminaService.actualizarLamina(id, lamina));
            }

        //eliminar lamina (logico)
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            laminaService.eliminarLamina(id);
            return ResponseEntity.noContent().build();
        }

    }
