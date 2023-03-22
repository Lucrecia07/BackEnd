package com.portfolio.portfolio.Repository;

import com.portfolio.portfolio.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Integer>{
    public Optional<Proyecto> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
    public Optional<Proyecto> findByDescripcion(String descripcion);
    public boolean existsByDescripcion(String descripcion);
}
