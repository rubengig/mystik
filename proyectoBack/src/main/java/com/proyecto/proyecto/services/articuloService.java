package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.entity.articuloEntity;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.exception.UnauthorizedAccessException;
import com.proyecto.proyecto.repository.articuloRepository;

@Service
public class articuloService implements serviceInterface<articuloEntity>{

    @Autowired
    articuloRepository oArticuloRepository;

    @Autowired
    private randomService oRandomService;

    @Autowired
    private AuthService oAuthService;

    private String[] arrNombres = {
    "Cristal del Alba",
    "Sortija de los Susurros",
    "Túnica de los Ecos",
    "Códice del Vidente",
    "Reloj del Tiempo Dormido",
    "Candelabro de Almas",
    "Fragmento de Luna",
    "Grimorio Espectral",
    "Capa del Velo Sombrío",
    "Esfera de los Sueños",
    "Daga de la Medianoche",
    "Flor de Sombras",
    "Máscara del Oráculo",
    "Colgante de Bruma",
    "Guantes del Vacío",
    "Runa del Silencio",
    "Anillo de Estrellas Caídas",
    "Taza de los Recuerdos",
    "Cuerno del Ocaso",
    "Mapa de los Caminos Velados",
    "Pluma del Guardián",
    "Cuenco del Equilibrio",
    "Piedra del Espíritu",
    "Cinturón del Eclipse",
    "Estatuilla del Destino",
    "Lámpara del Horizonte",
    "Amuleto de los Cuatro Vientos",
    "Botella de Niebla",
    "Diadema de los Sueños",
    "Cetro de Luz Silente",
    "Abanico de las Llamas Invisibles",
    "Lágrima del Infinito",
    "Fragmento de Aurora",
    "Collar de Penumbra",
    "Zapatillas del Caminante Onírico",
    "Capa de Somnolencia",
    "Linterna del Vacío",
    "Reloj de los Ojos Cerrados",
    "Flauta de los Espíritus",
    "Brújula Astral",
    "Cuerda de los Destinos",
    "Clavos del Juicio Eterno",
    "Libro de las Marcas Ocultas",
    "Espejo de las Verdades Rotos",
    "Alforja de los Recuerdos Lejanos",
    "Corona de las Estaciones",
    "Cajita de Murmullos",
    "Cascabel de la Intuición",
    "Velo de Estrellas",
    "Cáliz del Vacío Interior"
};


   private String[] arrDescripciones = {
    "forjada en rituales antiguos",
    "tejida con hilos del crepúsculo",
    "custodiada por generaciones de sabios",
    "bendecida por la luz de la luna llena",
    "escondida en ruinas olvidadas",
    "imbuida con energía astral",
    "susurrante al tacto",
    "resonante con sueños no contados",
    "grabada con runas olvidadas",
    "protegida por sellos arcanos",
    "usada en ceremonias de equilibrio espiritual",
    "emanando un tenue resplandor etéreo",
    "silenciosa pero inquietante",
    "purificada en las aguas del tiempo",
    "nacida del fuego estelar",
    "conectada a planos invisibles",
    "encantada para revelar lo oculto",
    "latente con memorias de otras vidas",
    "creada bajo el eclipse solar",
    "vibrando al compás del universo",
    "teñida con pigmentos de sueños",
    "capaz de alterar el flujo del destino",
    "guardiana de secretos prohibidos",
    "envuelta en brumas sagradas",
    "ligada al último suspiro del día"
};


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            articuloEntity oArticuloEntity = new articuloEntity();

            String nombre = arrNombres[oRandomService.getRandomInt(0, arrNombres.length - 1)];
            String descripcion = "Artículo " + arrDescripciones[oRandomService.getRandomInt(0, arrDescripciones.length - 1)];
            double precio = oRandomService.getRandomInt(5, 200) + oRandomService.getRandomInt(0, 99) / 100.0; // Ej: 79.99

            oArticuloEntity.setNombre(nombre);
            oArticuloEntity.setDescripcion(descripcion);
            oArticuloEntity.setPrecio(precio);

            oArticuloRepository.save(oArticuloEntity);
        }

        return oArticuloRepository.count();
    }

    public Page<articuloEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oArticuloRepository.findByNombreContainingOrDescripcionContaining(filter.get(), filter.get(), oPageable);
        } else {
            return oArticuloRepository.findAll(oPageable);
        }
    }

    public articuloEntity get(Long id) {
        return oArticuloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado"));
    }

    public Long count() {
        return oArticuloRepository.count();
    }

    public Long delete(Long id) {
        if(oAuthService.isAdmin()) {
            oArticuloRepository.deleteById(id);
            return 1L;
        } else {
             throw new UnauthorizedAccessException("No tienes permisos para borrar el articulo");
        }
        
    }

    public articuloEntity create(articuloEntity oArticuloEntity) {
        if(oAuthService.isAdmin()) {
            return oArticuloRepository.save(oArticuloEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para crear el articulo");
        }
        
    }

    public articuloEntity update(articuloEntity oArticuloEntity) {
         if(oAuthService.isAdmin()) {

        articuloEntity oArticuloEntityFromDatabase = oArticuloRepository.findById(oArticuloEntity.getId()).get();
        if (oArticuloEntity.getNombre() != null) {
            oArticuloEntityFromDatabase.setNombre(oArticuloEntity.getNombre());
        }
        if (Double.compare(oArticuloEntity.getPrecio(), Double.NaN) != 0) {
            oArticuloEntityFromDatabase.setPrecio(oArticuloEntity.getPrecio());
        }
        return oArticuloRepository.save(oArticuloEntityFromDatabase);

         }else {
             throw new UnauthorizedAccessException("No tienes permisos para actualizar el articulo");
         }
       
    }

    public Long deleteAll() {
        oArticuloRepository.deleteAll();
        return this.count();
    }

    public List<articuloEntity> getAll() {
        return oArticuloRepository.findAll();
    }
    
    
}
