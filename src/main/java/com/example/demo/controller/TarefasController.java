package com.example.demo.controller;

import com.example.demo.model.Tarefas;
import com.example.demo.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    private final TarefasService service;

    @Autowired
    public TarefasController(TarefasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createTarefa(@RequestBody Tarefas tarefa) throws Exception {
        try {
            Tarefas saved = service.create(tarefa);
            return new ResponseEntity<>(saved, null, HttpStatus.CREATED);
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try{
            List<Tarefas> found = service.findAll();
            if(!found.isEmpty()){
                return new ResponseEntity<>(found, null, HttpStatus.OK);
            } else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        try {
            Tarefas found = service.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tarefas tarefa) throws Exception {
        try{
            if(service.findById(id) != null) {
                tarefa.setId(id);
                return new ResponseEntity<>( service.update(tarefa), null, HttpStatus.OK);
            } else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);

        }
        catch(Exception e){
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
