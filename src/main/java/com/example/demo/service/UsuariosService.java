package com.example.demo.service;

import com.example.demo.model.Usuarios;
import com.example.demo.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    private UsuariosRepository repository;

    @Autowired
    public UsuariosService(UsuariosRepository repository) {
        this.repository = repository;
    }

    public Usuarios create(Usuarios usuario) {
        return repository.save(usuario);
    }

    public List<Usuarios> findAll() {
        return repository.findAll();
    }

    public Usuarios findById(Long id) {
        Optional<Usuarios> found = repository.findById(id);

        if(found.isPresent()) return found.get();
        else return null;
    }

    public Usuarios update(Usuarios usuario) {
        return repository.save(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
