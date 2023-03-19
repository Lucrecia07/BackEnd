package com.portfolio.portfolio.Controller;

import com.portfolio.portfolio.Dto.DtoExperiencia;
import com.portfolio.portfolio.Entity.Experiencia;
import com.portfolio.portfolio.Security.Controller.Mensaje;
import com.portfolio.portfolio.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/explab")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"https://frontend.web.app", "http://localhost:4200"})
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExp){
        if(StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getDescripcionE()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        /*if(sExperiencia.existsByDescripcionE(dtoExp.getDescripcionE()))
            return new ResponseEntity(new Mensaje("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);*/
        
        Experiencia experiencia = new Experiencia(dtoExp.getNombreE(), dtoExp.getDescripcionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExp){
        //Valida si existe ID.
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        //Compara nombre de experiencias.
        if(sExperiencia.existsByNombreE(dtoExp.getNombreE()) && sExperiencia.getByNombreE(dtoExp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        //No puede estar vacío.
        if(StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        /*
        //Compara descripción de experiencias.
        if(sExperiencia.existsByDescripcionE(dtoExp.getDescripcionE()) && sExperiencia.getByDescripcionE(dtoExp.getDescripcionE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        */
        //No puede estar vacío.
        if(StringUtils.isBlank(dtoExp.getDescripcionE()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoExp.getNombreE());
        experiencia.setDescripcionE((dtoExp.getDescripcionE()));
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Valida si existe ID.
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        sExperiencia.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
}
