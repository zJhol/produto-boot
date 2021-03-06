package br.com.fiap.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private static final String CATEGORIA_FOLDER = "categoria/";

	@Autowired
	public CategoriaRepository repository;

	@ApiOperation(value = "Lista todas as categorias existentes")
	@ApiResponses({ @ApiResponse(code = 200, message = "Listagem com sucesso"),
			@ApiResponse(code = 400, message = "Foi informado algo incorreto"),
			@ApiResponse(code = 500, message = "Erro ao buscar no banco")

	})
	@GetMapping()
	public ResponseEntity<List<CategoriaModel>> findAll(Model model) {

		// model.addAttribute("categorias", repository.findAll());
		List<CategoriaModel> categorias = repository.findAll();

		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> findById(@PathVariable("id") long id, Model model) {

		CategoriaModel categoria = repository.findById(id).get();
		return ResponseEntity.ok(categoria);
	}

	@PostMapping()
	public ResponseEntity save(@RequestBody @Valid CategoriaModel categoriaModel) {

		CategoriaModel categoria = repository.save(categoriaModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getIdCategoria()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody @Valid CategoriaModel categoriaModel) {

		categoriaModel.setIdCategoria(id);
		repository.save(categoriaModel);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteById(@PathVariable("id") long id) {

		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
