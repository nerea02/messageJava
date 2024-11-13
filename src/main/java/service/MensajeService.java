/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Mensaje;
import org.springframework.stereotype.Service;

/**
 *
 * @author nerea
 */
@Service
public class MensajeService {

    private final List<Mensaje> mensajes = new ArrayList<>();
    private long idCounter = 1;

    // Crear un nuevo mensaje
    public Mensaje createMensaje(Mensaje mensaje) {
        mensaje.setId(idCounter++);
        mensajes.add(mensaje);
        return mensaje;
    }

    // Obtener todos los mensajes
    public List<Mensaje> getAllMensajes() {
        return mensajes;
    }

    // Obtener un mensaje por ID
    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajes.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    // Actualizar un mensaje
    public Mensaje updateMensaje(Long id, Mensaje mensajeActualizado) {
        for (Mensaje mensaje : mensajes) {
            if (mensaje.getId().equals(id)) {
                mensaje.setUser(mensajeActualizado.getUser());
                mensaje.setMensaje(mensajeActualizado.getMensaje());
                mensaje.setFechaCreacion(mensajeActualizado.getFechaCreacion());
                return mensaje;
            }
        }
        return null;
    }

    // Eliminar un mensaje
    public boolean deleteMensaje(Long id) {
        return mensajes.removeIf(m -> m.getId().equals(id));
    }
}
