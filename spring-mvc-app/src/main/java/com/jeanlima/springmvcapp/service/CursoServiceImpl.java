package com.jeanlima.springmvcapp.service;

import com.jeanlima.springmvcapp.model.Curso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoServiceImpl implements CursoService {
    private List<Curso> cursos = new ArrayList<Curso>(
            List.of(
                    new Curso("Desenvolvimento web II")
            )
    );


    @Override
    public void salvarCurso(Curso curso){
        try{
            this.cursos.add(curso);
        }catch (Exception e){
            System.out.printf(e.toString());
        }
    }

    @Override
    public void deletarCurso(Curso curso) {
        this.cursos.remove(curso);
    }

    @Override
    public List<Curso> getCursos() {
        return this.cursos;
    }
}
