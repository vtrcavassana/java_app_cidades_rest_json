package br.usjt.usjt_ccp3anmca_rest_json_ativ5.resource;


import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.usjt_ccp3anmca_rest_json_ativ5.model.Cidade;
import br.usjt.usjt_ccp3anmca_rest_json_ativ5.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	@Autowired
	private CidadeRepository cidadeRepo;

	@GetMapping ("/lista")
	public List <Cidade> todasAsCidades (){
		return cidadeRepo.findAll();
	}
	
	@PostMapping ("/cadastrar")
	public void salvar ( @RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentServletMapping().path("/cidades/{id}").buildAndExpand(c.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

	@GetMapping ("/{id}")
	public Cidade buscarPeloId (@PathVariable Long id) {
		return cidadeRepo.getOne(id);
	}
}