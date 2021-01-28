package br.com.dipaulamobilesolutions.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dipaulamobilesolutions.agenda.R;
import br.com.dipaulamobilesolutions.agenda.dao.AlunoDAO;
import br.com.dipaulamobilesolutions.agenda.model.activity.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR = "Novo aluno";
    private Button botaoSalvar;
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializarCampos();
        configuraBotaoSalvar();


        Intent dados = getIntent();
        Bundle extras = dados.getExtras();
        Aluno aluno = (Aluno) extras.getSerializable("aluno");  //casting para aluno



        etNome.setText(aluno.getNome());
        etTelefone.setText(aluno.getTelefone());
        etEmail.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvar() {
        botaoSalvar.setOnClickListener(v -> {
            Aluno alunoCriado = criarAluno();

            salvaAluno(alunoCriado);

        });
    }

    private void inicializarCampos() {
        botaoSalvar = findViewById(R.id.btnSalvar);
        etNome = findViewById(R.id.etnome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
    }

    private void salvaAluno(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    private Aluno criarAluno() {
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String email = etEmail.getText().toString();


        return new Aluno(nome, telefone, email);
    }
}