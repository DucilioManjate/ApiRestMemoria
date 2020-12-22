package com.agenda.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.agenda.api.model.Usuario;



@RestController
@RequestMapping("/usuarios")
public class UsuarioCrontroller {
	
	
	private List<Usuario> listaUsuarios = new ArrayList<>();
	public UsuarioCrontroller() {
		this.listaUsuarios.addAll(Arrays.asList(
				new Usuario(1L,"Lio", "liomanjate@gmail.com","Vida4050@123"),
				new Usuario(2L,"Professor", "lucas@gmail.com","okay4050@123"),
				new Usuario(3L,"marcia", "marcia@gmail.com","oi4050@123")
				));
		
	
	}
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios(){
		return ResponseEntity.ok(this.listaUsuarios);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id){
		Usuario user = this.listaUsuarios.stream().filter(U -> U.getId() == id).findAny().orElse(null);
		
		return ResponseEntity.ok(user);
		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario user){
		Long id = this.listaUsuarios.size()+1L;
		user.setId(id);
		this.listaUsuarios.add(user);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario user){
		Usuario usuarioAtualizar = this.listaUsuarios.stream().filter(U -> U.getId() == id).findAny().orElse(null);
		if(usuarioAtualizar == null) {
			return ResponseEntity.notFound().build();
		}
		user.setId(id);
		int index = id.intValue() -1;
		this.listaUsuarios.set(index, user);
		return ResponseEntity.ok(user);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id){
		Usuario usuarioEliminar = this.listaUsuarios.stream().filter(U -> U.getId() == id).findAny().orElse(null);
		if(usuarioEliminar == null) {
			return ResponseEntity.notFound().build();
		}
		
		int index = id.intValue() -1;
		this.listaUsuarios.remove(index);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	

}
