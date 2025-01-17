package com.foro.challenge.servicio;

import com.foro.challenge.modelo.DatosTopico;
import com.foro.challenge.modelo.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.foro.challenge.repositorio.TopicoRepository;

import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository repository;

    public Page<DatosTopico> listarTodos(Pageable paginacion) {
        return repository.findByActivoTrue(paginacion).map(DatosTopico::new);
    }

    public Optional<DatosTopico> obtenerPorId(Long id) {
        return repository.findById(id).map(DatosTopico::new);
    }

    public Topico encontrarPorId(Long id){
        return repository.getReferenceById(id);
    }

    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return repository.findById(id);
    }


    public DatosTopico guardar(Topico topico) {
        Topico topicoGuardado = repository.save(topico);
        return new DatosTopico(topicoGuardado);
    }
}
