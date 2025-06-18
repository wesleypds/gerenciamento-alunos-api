package com.gen.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gen.api.model.Student;
import com.gen.api.model.mapper.StudentMapper;
import com.gen.api.repository.StudentRepository;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    @GetMapping("/inserirAlunos")
    public ModelAndView insertAlunos(Student aluno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/formAluno");
        modelAndView.addObject("aluno", new Student());
        return modelAndView;
    }

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAluno(@Valid Student aluno, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("Aluno/formAluno");
            modelAndView.addObject("aluno");
        } else {
            modelAndView.setViewName("redirect:/alunos-adicionados");
            repository.save(mapper.toEntity(aluno));
        }
        return modelAndView;
    }

    @GetMapping("alunos-adicionados")
    public ModelAndView listagemAlunos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/listAlunos");
        modelAndView.addObject("alunosList", repository.findAll()
                                                                        .stream()
                                                                        .map(aluno -> mapper.toModel(aluno))
                                                                        .toList());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/editar");
        Student aluno = mapper.toModel(repository.findById(id).get());
        modelAndView.addObject("aluno", aluno);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Student aluno) {
        ModelAndView modelAndView = new ModelAndView();
        repository.save(mapper.toEntity(aluno));
        modelAndView.setViewName("redirect:/alunos-adicionados");
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerAluno(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("filtro-alunos")
    public ModelAndView filtroAlunos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/filtroAlunos");
        return modelAndView;
    }

    @GetMapping("alunos-ativos")
    public ModelAndView listaAlunosAtivos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/alunos-ativos");
        modelAndView.addObject("alunosAtivos", repository.findByStatusActive());
        return modelAndView;
    }

    @GetMapping("alunos-inativos")
    public ModelAndView listaAlunosInativos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/alunos-inativos");
        modelAndView.addObject("alunosInativos", repository.findByStatusInactive());
        return modelAndView;
    }

    @PostMapping("/pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> listaAlunos;
        if(nome == null || nome.trim().isEmpty()) {
            listaAlunos = repository.findAll()
                                    .stream()
                                    .map(aluno -> mapper.toModel(aluno))
                                    .toList();
        } else {
            listaAlunos = repository.findByNameContainingIgnoreCase(nome)
                                    .stream()
                                    .map(aluno -> mapper.toModel(aluno))
                                    .toList();;
        }
        modelAndView.addObject("ListaDeAlunos", listaAlunos);
        modelAndView.setViewName("Aluno/pesquisa-resultado");
        return modelAndView;
    }
}
