package com.portfolio.portfolio.Controller;

import com.portfolio.portfolio.Dto.DtoPersona;
import com.portfolio.portfolio.Entity.Persona;
import com.portfolio.portfolio.Security.Controller.Mensaje;
import com.portfolio.portfolio.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"https://frontend.web.app", "http://localhost:4200"})
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;
    
    /*@GetMapping("/traer")
    public List<Persona> getPersona(){
        return personaService.getPersona();
    }*/
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    
    /*@PreAuthorize("hasRole ('ADMIN')")*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }
    
    
    /*@PreAuthorize("hasRole ('ADMIN')")*/
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nomnbre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(personaService.existsByNombre(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = new Persona(
                dtoPersona.getNombre(), dtoPersona.getApellido(), dtoPersona.getDescripcion(), dtoPersona.getImg()
        );
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);
    }
    
    /*@PreAuthorize("hasRole ('ADMIN')")*/
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPersona){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        /*if(personaService.existsByNombre(dtoPersona.getNombre()) && personaService.getByNombre(dtoPersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }
        
        if(personaService.existsByApellido(dtoPersona.getApellido()) && personaService.getByApellido(dtoPersona.getApellido()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese apellido ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPersona.getApellido())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }
        
        if(personaService.existsByDescripcion(dtoPersona.getDescripcion()) && personaService.getByDescripcion(dtoPersona.getDescripcion()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPersona.getDescripcion())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }*/
        
        
        Persona persona = personaService.getOne(id).get();
        
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setDescripcion(dtoPersona.getDescripcion());
        persona.setImg(dtoPersona.getImg());
        
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
}
