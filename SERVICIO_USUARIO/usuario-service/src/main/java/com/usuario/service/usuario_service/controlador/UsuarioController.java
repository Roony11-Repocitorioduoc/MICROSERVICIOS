package com.usuario.service.usuario_service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.usuario_service.entidades.Usuario;
import com.usuario.service.usuario_service.modelos.Producto;
import com.usuario.service.usuario_service.repositorio.UsuarioRepository;
import com.usuario.service.usuario_service.servicio.UsuarioService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    @Autowired

    private UsuarioService usuarioService;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id")int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteUserById(id);
        return ResponseEntity.noContent().build();

    }



    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){

        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/productos/{usuarioId}")
    public ResponseEntity<List<Producto>>listarProductos(@PathVariable("usuarioId")int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        List<Producto> productos = usuarioService.getProductos(id);
        return ResponseEntity.ok(productos);
    }


}
