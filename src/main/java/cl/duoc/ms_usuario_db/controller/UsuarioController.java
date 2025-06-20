package cl.duoc.ms_usuario_db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_usuario_db.model.entities.Usuario;
import cl.duoc.ms_usuario_db.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Enlista
    @GetMapping
    public List<Usuario> selectAllUsuarios(){
        return usuarioService.selectAllUsuario();
    }

    // Busca 
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return (usuario != null) ? new ResponseEntity<>(usuario, HttpStatus.OK)
                                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Crea
    @PostMapping("")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return (updatedUsuario != null) ? new ResponseEntity<>(updatedUsuario, HttpStatus.OK)
                                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        if (deleted) {
            return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}