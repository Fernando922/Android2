package br.com.dipaulamobilesolutions.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

import static br.com.dipaulamobilesolutions.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionaAluno;
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);


        fabAdicionaAluno = findViewById(R.id.fabAdicionaAluno);

        configuraFabNovoAluno();
        configuraLista();

        dao.salva(new Aluno("Fernando", "16994153565", "454545@sdss"));
        dao.salva(new Aluno("Pedro", "123456", "454545@sdss"));
        dao.salva(new Aluno("José", "123456", "454545@sdss"));


    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraFabNovoAluno() {
        fabAdicionaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreformularioModoInsereAluno();

            }
        });
    }

    private void abreformularioModoInsereAluno() {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        startActivity(intent);
    }


    private void configuraLista() {
        ListView lvAlunos = findViewById(R.id.lvAlunos);
        configuraAdapter(lvAlunos);
        configuraListenerDeCliquePorItem(lvAlunos);
        configuraListenerDeCliqueLongoPorItem(lvAlunos);
    }

    private void configuraListenerDeCliqueLongoPorItem(ListView lvAlunos) {
        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                removerAlunoDaLista(alunoEscolhido);
                return true;  //significa que vc vai consumir o evento todo!
            }
        });
    }

    private void removerAlunoDaLista(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);  //remove da lista recarregando a pagina
    }

    private void configuraListenerDeCliquePorItem(ListView lvAlunos) {
        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });


    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intent);
    }

    private void configuraAdapter(ListView lvAlunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvAlunos.setAdapter(adapter);
    }
}

