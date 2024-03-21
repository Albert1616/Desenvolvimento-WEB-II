package com.jeanlima.springmvcapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jeanlima.springmvcapp.model.Curso;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.MockDataService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/aluno")
public class AlunoController {


    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;



    @Autowired
    MockDataService mockDataService;


    @RequestMapping("/showForm")
    public String showFormAluno(Model model, HttpSession session){

        model.addAttribute("aluno", new Aluno());
        model.addAttribute("linguagens", mockDataService.getLinguagens());
        model.addAttribute("sistemasOperacionais", mockDataService.getSistemasOperacionais());
        return "aluno/formAluno";
    }

    @RequestMapping("/addAluno")
    public String showFormAluno(@ModelAttribute("aluno") Aluno aluno,  Model model){
        UUID uuid = UUID.randomUUID();
        aluno.setId(uuid.toString());
        alunoService.salvarAluno(aluno);
        model.addAttribute("aluno", aluno);
        return "aluno/paginaAluno";
    }

    @RequestMapping("/getListaAlunos")
    public String showListaAluno(Model model) {

        List<Aluno> alunos = alunoService.getListaAluno();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunos";

    }
    @RequestMapping("/getListaAlunosLinguagens")
    public String showListaAlunoLinguagem(Model model){
        List<List<Aluno>> alunos_linguagem = new ArrayList<>();
        for(String linguagem : mockDataService.getLinguagens()){
            List<Aluno> lst = alunoService.getAlunosByLinguagem(linguagem);
            if(lst != null){
                alunos_linguagem.add(lst);
            }
        }
        model.addAttribute("alunos_linguagem", alunos_linguagem);
        return "aluno/listaLinguagem";
    }
    @RequestMapping("/getListaAlunosSo")
    public String showListaAlunoSistemaOp(Model model){
        List<List<Aluno>> alunos_so = new ArrayList<>();
        List<String> lst_so = new ArrayList<>();
        for(String sistema : mockDataService.getSistemasOperacionais()){
            List<Aluno> lst = alunoService.getAlunosBySo(sistema);
            if(lst != null){
                alunos_so.add(lst);
                lst_so.add(sistema);
            }
        }
        model.addAttribute("alunos_so", alunos_so);
        model.addAttribute("sistemas", lst_so);
        return "aluno/listaSO";
    }
    @RequestMapping("/getListaAlunoCurso")
    public String showListaAlunosCurso(Model model, HttpSession session){
        List<Curso> cursos = (List<Curso>) session.getAttribute("cursos");
        List<List<Aluno>> alunos_curso = new ArrayList<>();
        for(Curso curso : cursos){
            List<Aluno> lst = alunoService.getAlunosByCurso(curso.getName());
            if(lst != null){
                alunos_curso.add(lst);
            }
        }
        model.addAttribute("alunos_curso", alunos_curso);
        return "aluno/listaAlunoCurso";
    }
    @RequestMapping("alunoDetails/{alunoId}")
    public String alunoDetails(@PathVariable("alunoId") String id, Model model){
        Aluno aluno_detail = alunoService.getAlunoById(id);
        model.addAttribute("aluno", aluno_detail);
        return "aluno/paginaAluno";
    }
    @RequestMapping("alunoRemove/{alunoId}")
    public String alunoRemove(@PathVariable("alunoId") String id, Model model, RedirectAttributes redirectAttributes){
        Aluno aluno_remove = alunoService.getAlunoById(id);
        alunoService.deletarAluno(aluno_remove);
        model.addAttribute("alunos",alunoService.getListaAluno());
        redirectAttributes.addFlashAttribute("successMessage", "Aluno removido");
        return "redirect:/aluno/getListaAlunos";
    }
}
