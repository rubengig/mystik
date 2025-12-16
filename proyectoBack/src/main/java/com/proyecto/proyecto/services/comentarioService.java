package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.entity.articuloEntity;
import com.proyecto.proyecto.entity.comentarioEntity;
import com.proyecto.proyecto.entity.usuarioEntity;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.exception.UnauthorizedAccessException;
import com.proyecto.proyecto.repository.comentarioRepository;

@Service
public class comentarioService implements serviceInterface<comentarioEntity> {

    @Autowired
    comentarioRepository oComentarioRepository;

    @Autowired
    usuarioService oUsuarioService;

    @Autowired
    articuloService oArticuloService;

    @Autowired
    private AuthService oAuthService;

    public Long randomCreate(Long cantidad) {
        List<usuarioEntity> usuarios = oUsuarioService.getAll();
        List<articuloEntity> articulos = oArticuloService.getAll();

        String[] comentariosTexto = {
    "Este artículo ilumina mi camino, gracias por compartirlo.",
    "Las palabras aquí resuenan con la sabiduría ancestral.",
    "Siento que este conocimiento es un regalo de los espíritus.",
    "Profundo y revelador, como un susurro del más allá.",
    "Este texto abre puertas a mundos invisibles.",
    "Me ha conectado con energías que no comprendía antes.",
    "Un tributo perfecto a los secretos del universo.",
    "Gracias por traer luz a estos misterios ocultos.",
    "Cada palabra parece bendecida por antiguos rituales.",
    "Un refugio para el alma curiosa, me ha encantado.",
    "Este artículo vibra con la fuerza del cosmos.",
    "Me hace reflexionar sobre los ciclos de la existencia.",
    "Un mapa sagrado para quienes buscan la verdad oculta.",
    "Sentí la presencia de sabios antiguos al leerlo.",
    "Un portal hacia conocimientos que trascienden el tiempo.",
    "Paz y misterio se entrelazan en cada párrafo.",
    "Me ha inspirado a explorar mi propio sendero espiritual.",
    "Estas palabras tienen la magia de las leyendas olvidadas.",
    "Una joya escondida entre las sombras del conocimiento.",
    "Siento que este texto guarda secretos para mi alma.",
    "Como un encantamiento, me ha cautivado por completo.",
    "Perfecto para quienes buscan la luz en la oscuridad.",
    "Este artículo es un ritual de sabiduría y respeto.",
    "Me transportó a mundos donde la magia es real.",
    "Un faro que guía a los viajeros del alma.",
    "Gracias por compartir este saber ancestral y profundo.",
    "Cada frase parece cantada por voces etéreas.",
    "Inspirador y lleno de enseñanzas ocultas.",
    "Un tesoro para quienes caminan en busca del significado.",
    "Siento que estas ideas fluyen del espíritu mismo.",
    "Me ha llenado de paz y asombro interior.",
    "Un canto místico que resuena en mi interior.",
    "Lectura que reconecta con la esencia del ser.",
    "Como un hechizo, despertó mi curiosidad dormida.",
    "Un texto cargado de símbolos y energías sagradas.",
    "Me ha abierto puertas hacia nuevos horizontes.",
    "Un regalo para los corazones inquietos y soñadores.",
    "Este artículo es un susurro del universo a mi oído.",
    "Inspiración para la mente y el espíritu.",
    "Con cada línea, sentí que crecía mi entendimiento.",
    "Gracias por compartir este fragmento de eternidad.",
    "Un tejido de palabras que conjuran la magia.",
    "Una lectura que calma y despierta al mismo tiempo.",
    "Perfecto para quien busca respuestas en las sombras.",
    "Un texto que respira con la fuerza de lo invisible.",
    "Me ha llevado a reflexionar más allá de lo evidente.",
    "Siento que estas palabras son luz en la penumbra.",
    "Un mensaje profundo que reverbera en el alma.",
    "Lectura esencial para los amantes del misterio.",
    "Gracias por esta ventana hacia lo desconocido."
};


        Random rand = new Random();

        for (int i = 0; i < cantidad; i++) {
            comentarioEntity comentario = new comentarioEntity();

            // Asignar un usuario y un artículo aleatorio
            comentario.setUsuario(usuarios.get(rand.nextInt(usuarios.size())));
            comentario.setArticulo(articulos.get(rand.nextInt(articulos.size())));

            // Asignar un texto aleatorio de los posibles
            comentario.setTexto(comentariosTexto[rand.nextInt(comentariosTexto.length)]);

            // Guardar el comentario
            oComentarioRepository.save(comentario);
        }

        return oComentarioRepository.count();
    }

    public Page<comentarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oComentarioRepository
                    .findByTextoContaining(filter.get(), oPageable);
        } else {
            return oComentarioRepository.findAll(oPageable);
        }
    }

    public comentarioEntity get(Long id) {
        return oComentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
    }

    public Long count() {
        return oComentarioRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oComentarioRepository.deleteById(id);
            return 1L;
        } else {
            comentarioEntity oComentarioEntity = oComentarioRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Comentario no encontrado"));
            if (oAuthService.isUsuarioWithItsOwnData(oComentarioEntity.getUsuario().getId())) {
                oComentarioRepository.deleteById(id);
                return 1L;
            } else {
                throw new UnauthorizedAccessException("No tienes permisos para borrar el comentario");
            }
        }

    }

    public comentarioEntity create(comentarioEntity ocomentarioEntity) {
        if (oAuthService.isSessionActive()) {
            return oComentarioRepository.save(ocomentarioEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para hacer comentarios");
        }

    }

    public comentarioEntity update(comentarioEntity ocomentarioEntity) {
        return oComentarioRepository.save(ocomentarioEntity);
    }

    public Long deleteAll() {
        oComentarioRepository.deleteAll();
        return this.count();
    }

    public Page<comentarioEntity> getComentariosPorArticulo(Long idArticulo, Pageable pageable) {
        return oComentarioRepository.findByComentarioxArticulo(idArticulo, pageable);
    }

    public Page<comentarioEntity> getComentariosPorUsuario(Long idUsuario, Pageable pageable) {
        return oComentarioRepository.findByComentarioxUsuario(idUsuario, pageable);
    }
}
