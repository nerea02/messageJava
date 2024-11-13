/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.Optional;
import model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MensajeService;

/**
 *
 * @author nerea
 */
@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // Crear un mensaje
    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeService.createMensaje(mensaje);
        return new ResponseEntity<>(nuevoMensaje, HttpStatus.CREATED);
    }

    // Obtener todos los mensajes
    @GetMapping
    public ResponseEntity<List<Mensaje>> getAllMensajes() {
        List<Mensaje> mensajes = mensajeService.getAllMensajes();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    // Obtener un mensaje por id
    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        Optional<Mensaje> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Actualizar un mensaje
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> updateMensaje(@PathVariable Long id, @RequestBody Mensaje mensaje) {
        Mensaje mensajeActualizado = mensajeService.updateMensaje(id, mensaje);
        return mensajeActualizado != null ?
               ResponseEntity.ok(mensajeActualizado) :
               ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long id) {
        boolean eliminado = mensajeService.deleteMensaje(id);
        return eliminado ?
               ResponseEntity.noContent().build() :
               ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
