package com.jeanlima.springmvcapp.service;

import java.util.ArrayList;
import java.util.List;

import com.jeanlima.springmvcapp.model.Curso;
import org.springframework.stereotype.Component;

import com.jeanlima.springmvcapp.model.Aluno;

@Component
public class AlunoServiceImpl implements  AlunoService{

    public List<Aluno> alunos = new ArrayList<Aluno>();

    @Override
    public void salvarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
        try{
            this.alunos.add(aluno);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        
        
    }

    @Override
    public void deletarAluno(Aluno aluno) {
       this.alunos.remove(aluno);
    }

    @Override
    public Aluno getAlunoById(String id) {
        for (Aluno aluno : alunos) {
            if(aluno.getId().equals(id)){
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> getListaAluno() {
        return this.alunos;
    }

    @Override
    public List<Aluno> getAlunosByCurso(String cursoName) {
        List<Aluno> alunos_curso = new ArrayList<>();
        for(Aluno aluno : alunos){
            if(aluno != null && aluno.getCurso().getName().equals(cursoName)){
                alunos_curso.add(aluno);
            }
        }
        return alunos_curso.size() != 0 ? alunos_curso : null;
    };

    @Override
    public List<Aluno> getAlunosByLinguagem(String linguagem) {
        List<Aluno> alunos_linguagem = new ArrayList<>();
        for(Aluno aluno : alunos){
            if(aluno != null && aluno.getLinguagem().equals(linguagem)){
                alunos_linguagem.add(aluno);
            }
        }
        if(alunos_linguagem.size() == 0){
            return null;
        };
        return alunos_linguagem;
    }

    @Override
    public List<Aluno> getAlunosBySo(String sistema) {
        List<Aluno> alunos_so = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getSistemasOperacionas() != null && aluno.getSistemasOperacionas().contains(sistema)) {
                alunos_so.add(aluno);
            }
        }
        return alunos_so.isEmpty() ? null : alunos_so;
    }
}
