package com.example.demo.service;

import com.example.demo.model.Tarefas;
import com.example.demo.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {
    private TarefasRepository repository;

    @Autowired
    public TarefasService(TarefasRepository repository) {
        this.repository = repository;
    }

    public Tarefas create(Tarefas tarefa) {
        return repository.save(tarefa);
    }

    public List<Tarefas> findAll() {
        return repository.findAll();
    }

    public Tarefas findById(Long id) {
        Optional<Tarefas> found = repository.findById(id);

        if (found.isPresent()) return found.get();
        else return null;
    }

    public Tarefas update(Tarefas tarefa) {
        return repository.save(tarefa);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
