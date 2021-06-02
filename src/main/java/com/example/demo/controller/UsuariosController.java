package com.example.demo.controller;

import com.example.demo.model.Usuarios;
import com.example.demo.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosService service;

    @Autowired
    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuarios usuario) throws Exception {
        try{
            Usuarios saved = service.create(usuario);
            return new ResponseEntity<>(saved, null, HttpStatus.CREATED);

        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try{
            List<Usuarios> found = service.findAll();
            if(!found.isEmpty()){
                return new ResponseEntity<>(found, null, HttpStatus.OK);
            } else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        try{
            Usuarios found = service.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuarios usuario) throws Exception {
        try{
            if(service.findById(id) != null) {
                usuario.setId(id);
                return new ResponseEntity<>( service.update(usuario), null, HttpStatus.OK);
            } else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);

        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try{
            if(service.findById(id) != null) {
                service.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            } else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new Exception(e);
        }
    }
}
