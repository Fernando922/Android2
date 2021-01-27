package br.com.dipaulamobilesolutions.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionaAluno;
    private final AlunoDAO dao = new AlunoDAO();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);





        fabAdicionaAluno = findViewById(R.id.fabAdicionaAluno);

        configuraFabNovoAluno();


        dao.salva(new Aluno("Fernando", "123456", "454545@sdss"));
        dao.salva(new Aluno("Pedro", "123456", "454545@sdss"));
        dao.salva(new Aluno("José", "123456", "454545@sdss"));


    }

    private void configuraFabNovoAluno() {
        fabAdicionaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormulario();

            }
        });
    }

    private void abreFormulario() {
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();
    }

    private void configuraLista() {
        ListView lvAlunos = findViewById(R.id.lvAlunos);

        lvAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));


        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("qqr", "onItemClick: oi oi oi ");
            }
        });
    }
}

