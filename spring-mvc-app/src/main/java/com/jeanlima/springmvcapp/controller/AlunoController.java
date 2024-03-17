package com.jeanlima.springmvcapp.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.MockDataService;


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
        for(String sistema : mockDataService.getSistemasOperacionais()){
            List<Aluno> lst = alunoService.getAlunosBySo(sistema);
            if(lst != null){
                alunos_so.add(lst);
            }
        }
        model.addAttribute("alunos_so", alunos_so);
        model.addAttribute("sistemas", mockDataService.getSistemasOperacionais());
        return "aluno/listaSO";
    }
}
