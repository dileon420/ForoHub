package com.foro.challenge.controlador;

import com.foro.challenge.modelo.DatosTopico;
import com.foro.challenge.modelo.Topico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.foro.challenge.servicio.TopicoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService service;

    @GetMapping
    public Page<DatosTopico> listarTodos(Pageable paginacion) {
        return service.listarTodos(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopico> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DatosTopico> crear(@RequestBody Topico topico) {
        DatosTopico topicoCreado = service.guardar(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoCreado);
    }

    //Delete lógico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = service.encontrarPorId(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosTopico> actualizarTopico(@PathVariable Long id, @RequestBody Topico datosActualizados) {
        // Buscar el Topico existente por ID
        Optional<Topico> topicoExistenteOpt = service.obtenerTopicoPorId(id);

        if (topicoExistenteOpt.isPresent()) {
            // Obtener el Topico existente
            Topico topicoExistente = topicoExistenteOpt.get();

            // Actualizar los campos del tópico existente
            topicoExistente.setTitulo(datosActualizados.getTitulo());
            topicoExistente.setMensaje(datosActualizados.getMensaje());
            topicoExistente.setNombreCurso(datosActualizados.getNombreCurso());
            topicoExistente.setActivo(datosActualizados.getActivo());

            // Guardar el tópico actualizado
            DatosTopico datosTopicoActualizado = service.guardar(topicoExistente);

            // Devolver el registro actualizado
            return ResponseEntity.ok(datosTopicoActualizado);
        }

        // Si no se encuentra el tópico, devolver 404
        return ResponseEntity.notFound().build();
    }
}
