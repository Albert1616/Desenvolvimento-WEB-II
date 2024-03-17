package com.jeanlima.springmvcapp.service;

import java.util.List;

import com.jeanlima.springmvcapp.model.Curso;
import org.springframework.stereotype.Service;

import com.jeanlima.springmvcapp.model.Aluno;

@Service
public interface AlunoService {

    public void salvarAluno(Aluno aluno);
    public void deletarAluno(Aluno aluno);
    public Aluno getAlunoById(Integer id);

    public List<Aluno> getAlunosByCurso(String cursoName);
    public List<Aluno> getAlunosByLinguagem(String linguagem);
    public List<Aluno> getAlunosBySo(String sistema);
    public List<Aluno> getListaAluno();

}
