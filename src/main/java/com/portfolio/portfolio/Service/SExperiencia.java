package com.portfolio.portfolio.Service;

import com.portfolio.portfolio.Entity.Experiencia;
import com.portfolio.portfolio.Repository.RExperiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperiencia {
    @Autowired
    RExperiencia rExperiencia;
    
    public List<Experiencia> list(){
        return rExperiencia.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return rExperiencia.findById(id);
    }
    
    public Optional<Experiencia> getByNombreE(String nombreE){
        return rExperiencia.findByNombreE(nombreE);
    }
    
    public Optional<Experiencia> getByDescripcionE(String descripcionE){
        return rExperiencia.findByDescripcionE(descripcionE);
    }
    
    public void save(Experiencia expe){
        rExperiencia.save(expe);
    }
    
    public void delete(int id){
        rExperiencia.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rExperiencia.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rExperiencia.existsByNombreE(nombreE);
    }
    
    public boolean existsByDescripcionE(String descripcionE){
        return rExperiencia.existsByNombreE(descripcionE);
    }
}