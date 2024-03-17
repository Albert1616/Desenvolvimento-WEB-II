package com.jeanlima.springmvcapp.controller;

import com.jeanlima.springmvcapp.model.Curso;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/showForm")
    public String showForm(Model model){
        model.addAttribute("curso", new Curso());

        return "curso/formCurso";
    };
    @RequestMapping("/addCurso")
    public String addCurso(@ModelAttribute Curso curso, Model model){
        cursoService.salvarCurso(curso);
        return "curso/cursoPage";
    }
    @RequestMapping("/listCursos")
    public String listCursos(Model model){
        model.addAttribute("cursos", cursoService.getCursos());
        return "curso/listarCursos";
    }
}
