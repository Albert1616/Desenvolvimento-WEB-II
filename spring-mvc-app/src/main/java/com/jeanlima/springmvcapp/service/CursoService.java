package com.jeanlima.springmvcapp.service;

import com.jeanlima.springmvcapp.model.Curso;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CursoService {
    public void salvarCurso(Curso curso);
    public void deletarCurso(Curso curso);
    public List<Curso> getCursos();
}
