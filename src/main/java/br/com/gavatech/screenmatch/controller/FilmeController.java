package br.com.gavatech.screenmatch.controller;
import br.com.gavatech.screenmatch.domain.filme.DadosAtualizacaoFilme;
import br.com.gavatech.screenmatch.domain.filme.Filme;
import br.com.gavatech.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.gavatech.screenmatch.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id,Model model){
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme",filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model){
       model.addAttribute("lista",repository.findAll());
        return "filmes/listagem";
    }
    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);

        repository.save(filme);

         return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAtualizacaoFilme dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

   @DeleteMapping
   @Transactional
    public String removeFilme(Long id){
      repository.deleteById(id);
        return "redirect:/filmes";
    }
}
