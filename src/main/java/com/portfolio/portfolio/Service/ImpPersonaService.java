package com.portfolio.portfolio.Service;

import com.portfolio.portfolio.Entity.Persona;
import com.portfolio.portfolio.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService{
    @Autowired IPersonaRepository ipersonaRepository;

    public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return ipersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return ipersonaRepository.findByNombre(nombre);
    }
    
    public Optional<Persona> getByApellido(String apellido){
        return ipersonaRepository.findByApellido(apellido);
    }
    
    public Optional<Persona> getByDescripcion(String descripcion){
        return ipersonaRepository.findByDescripcion(descripcion);
    }
    
    public void save(Persona persona){
        ipersonaRepository.save(persona);
    }
    
    public void delete(int id){
        ipersonaRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return ipersonaRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return ipersonaRepository.existsByNombre(nombre);
    }
    
    public boolean existsByApellido(String apellido){
        return ipersonaRepository.existsByApellido(apellido);
    }
    
    public boolean existsByDescripcion(String descripcion){
        return ipersonaRepository.existsByDescripcion(descripcion);
    }
    
}
