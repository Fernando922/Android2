package br.com.dipaulamobilesolutions.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

public class AlunoDAO {


    private final static List<Aluno> alunos = new ArrayList<>();


    public void salva(Aluno alunoCriado) {
        alunos.add(alunoCriado);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos); //imutabilidade
    }
}
