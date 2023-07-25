package com.example.unittestbd.vue;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.unittestbd.R;
import com.example.unittestbd.controler.Controle;

public class ClientFiche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientfiche);
        init();
    }

    private EditText txtLastName;
    private EditText txtFirstName;
    private EditText txtSolde;
    private ImageView imgSmile;
    private TextView txtResult;

    private Controle controle;

    /**
     * Initialisation des liens avec les objets graphiques.
     */
    private void init(){
        try {
            txtFirstName = (EditText) findViewById(R.id.txtFirstname);
            txtLastName = (EditText) findViewById(R.id.txtLastname);
            txtSolde = (EditText) findViewById(R.id.txtSolde);
            ((Button)findViewById(R.id.btnCreer)).setEnabled(false);

            this.controle = Controle.getInstance(this);

            insertClient();

//            verifyFields();

            backToHome();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Ecoute événement sur le bouton "Creer".
     */
    private void insertClient() {
        ((Button)findViewById(R.id.btnCreer)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    String lastname = "";
                    String firstname = "";
                    int solde = 0;
                    try {
                        lastname = txtLastName.getText().toString();
                        firstname = txtFirstName.getText().toString();
                        solde = Integer.parseInt(txtSolde.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (lastname.length() < 3 || firstname.length() < 1 || solde == 0) {
                        if (lastname.length() < 3) {
                            Toast.makeText(ClientFiche.this, "Nom vide ou très court", Toast.LENGTH_SHORT).show();
                        }

                        if (firstname.length() < 1) {
                            Toast.makeText(ClientFiche.this, "Prénom vide", Toast.LENGTH_SHORT).show();
                        }

                        if (solde == 0) {
                            Toast.makeText(ClientFiche.this, "Solde vide", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            controle.createClient(ClientFiche.this,lastname, firstname, solde);
                            Toast.makeText(ClientFiche.this, "En cours...", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ClientFiche.this, "Erreur " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            }
        });
    }

    /**
     * Ecoute événement sur les champs de saisie.
     */
    private void verifyFields() {
        txtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ((Button)findViewById(R.id.btnCreer)).setEnabled(
                        s.toString().length() > 2
                );
            }
        });
    }

    /**
     * Revenir sur l'accueil.
     */
    private void backToHome() {
        ((Button)findViewById(R.id.txtLogo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientFiche.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(ClientFiche.this, "Accueil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}