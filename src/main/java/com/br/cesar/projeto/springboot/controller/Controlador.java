
package com.br.cesar.projeto.springboot.controller;

import com.br.cesar.projeto.springboot.model.Usuario;
import com.br.cesar.projeto.springboot.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controlador {
    @Autowired
   private UsuarioRepository usuarioRepository; 
    
    @PostMapping(value = "/salvar")
    @ResponseBody
  public ResponseEntity<Usuario> salvar(@RequestBody Usuario usu) {
    Usuario usuarioSalvo = usuarioRepository.save(usu);
    return ResponseEntity.ok(usuarioSalvo);
  }

  @GetMapping(value = "/listar")
  @ResponseBody
  public ResponseEntity<List<Usuario>> listar() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return ResponseEntity.ok(usuarios);
  }

  @PutMapping(value = "/atualizar/{id}")//para atualizar qualquer atributo, exceto o id
  @ResponseBody
  public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuarionovo){
  //usuarionovo é a resposta da requisição
      Usuario usuatual=usuarioRepository.findById(id).get();
      BeanUtils.copyProperties(usuarionovo,usuatual,"id");
      return usuarioRepository.save(usuatual);
  }

    
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Long id){
      usuarioRepository.deleteById(id);
     
  }
  @GetMapping(value = "/buscarporid/{id}")
  public Usuario buscarPorId(@PathVariable Long id){
  return usuarioRepository.findById(id).get();
  }
  
  @GetMapping(value = "/")
  public String retornaoi() {
    return "<h1>Ah, eu tô maluco!!</h1>";
  }

  @GetMapping(value = "/oi")
  public String retornaola() {
    return "<h1> VOCÊ quer brincar na neve??!</1>";
  }
}
