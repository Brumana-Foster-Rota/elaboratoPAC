package com.example.whatsonsalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.bson.types.ObjectId;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class AggiungiAppActivity extends AppCompatActivity {

    App app;
    Realm realm;
    String appID = "salon-ovfke";

    int idCheck = 100;
    int idRadio = 200;
    CheckBox[] checkboxes;
    String[] codiceTrattamentoCheckBox;
    ArrayList<String> trattamentiIntento = new ArrayList<>();
    RadioButton[] radiobuttons;
    String[] codiceClienteRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_app);

        Realm.init(this);

        app = new App(new AppConfiguration.Builder(appID).build());
        Credentials credentials = Credentials.anonymous();

        app.loginAsync(credentials, result -> {

            //questa variabile è una variabile che mi serve per passare informazioni da una activity all'altra
            Intent intento = new Intent(AggiungiAppActivity.this, SelezionaSlotAppuntamento.class);

            if (result.isSuccess()) {

                User user = app.currentUser();
                String partition = "salon";
                SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                //popolo la view e anche l'array di checkbox e radiobutton su cui posso lavorare (checkboxes)
                popola(config);

                //se ho checkboxes allora definisco cosa succede quando clicco i checkbox
                if(checkboxes.length != 0) {
                    for(int i = 0; i < checkboxes.length; i++) {
                        int j = i;
                        checkboxes[j].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                //se è checkato lo aggiungo a array di trattamenti da allocare
                                if(checkboxes[j].isChecked()){
                                    trattamentiIntento.add(codiceTrattamentoCheckBox[j]);
                                } else {
                                    if(trattamentiIntento.contains(codiceTrattamentoCheckBox[j]))
                                        trattamentiIntento.remove(codiceTrattamentoCheckBox[j]);
                                    //altrimenti lo tolgo dall'array di trattamenti da allocare (se è presente)
                                }
                            }
                        });
                    }
                }

                EditText filtro = findViewById(R.id.filtro);

                filtro.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.length() != 0) {
                            String f = String.valueOf(charSequence).toLowerCase();
                            for(RadioButton r : radiobuttons) {
                                if(!String.valueOf(r.getText()).toLowerCase().contains(f)) {
                                    r.setVisibility(View.GONE);
                                } else {
                                    r.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            for(RadioButton r : radiobuttons)
                                r.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                //se ho radiobuttons allora definisco cosa succede quando clicco un radiobutton
                if(radiobuttons.length != 0) {
                    RadioGroup rG = findViewById(R.id.gruppoRadio);
                    rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if(rG.getCheckedRadioButtonId() != -1) {

                                //trovare l'indice del radiobutton con id IdRadioButtonChecked all'interno di radiobuttons
                                //usare quell'indice per cerare l'id codice cliente in codiceClienteRadioButton
                                //e mandarlo con l'intent
                                int idRadioButtonChecked = rG.getCheckedRadioButtonId();
                                int indice = trovaIndice(radiobuttons, idRadioButtonChecked);
                                if(indice != -1)
                                    intento.putExtra("cliente", codiceClienteRadioButton[indice]);
                            }
                        }
                    });
                }
            } else {
                Log.e("SALON", "Autenticazione anonima non avvenuta: " + result.getError());
            }


            //mandare all'activity successiva le info per l'appuntamento
            final Button bottone = findViewById(R.id.selezionaslot);
            bottone.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    RadioGroup rG = findViewById(R.id.gruppoRadio);
                    //vado alla prossima activity solo se ho trattamenti da allocare e ho un cliente selezionato!
                    if(!trattamentiIntento.isEmpty() && rG.getCheckedRadioButtonId() != -1) {
                        intento.putExtra("trattamenti", trattamentiIntento);
                        startActivity(intento);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Seleziona almeno un trattamento e un cliente", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        });
    }

    private int trovaIndice(RadioButton[] radiobuttons, int idRadioButtonChecked) {
        int count = radiobuttons.length;
        int index = 0;
        boolean trovato = false;

        while(trovato == false && index < count) {
            if(radiobuttons[index].getId() == idRadioButtonChecked)
                trovato = true;
            else
                index++;
        }

        if(trovato == true)
            return index;
        else
            return -1;
    }


    private void popola(SyncConfiguration config) {
        try {
            realm = Realm.getInstance(config);
            RealmResults<trattamenti> risultatiTrattamenti = realm.where(trattamenti.class).findAll();
            RealmResults<clienti> risultatiClienti = realm.where(clienti.class).sort("nome", Sort.ASCENDING, "cognome", Sort.ASCENDING).findAll();

            LinearLayout lL = findViewById(R.id.linearLayout);
            RadioGroup rG = findViewById(R.id.gruppoRadio);

            int countClienti = 0;
            for(clienti c : risultatiClienti)
                countClienti++;

            radiobuttons = new RadioButton[countClienti];
            codiceClienteRadioButton = new String[countClienti];

            int x = 0;
            for(clienti c : risultatiClienti) {
                radiobuttons[x] = new RadioButton(this);
                radiobuttons[x].setText(c.getNome() + " " + c.getCognome());
                radiobuttons[x].setId(idRadio++);
                radiobuttons[x].setTextSize(16);
                codiceClienteRadioButton[x] = c.get_id().toString();
                rG.addView(radiobuttons[x]);
                x++;
            }

            int countTrattamenti = 0;
            for(trattamenti tr : risultatiTrattamenti)
                countTrattamenti++;

            checkboxes = new CheckBox[countTrattamenti];
            codiceTrattamentoCheckBox = new String[countTrattamenti];

            int i = 0;
            for(trattamenti tr : risultatiTrattamenti) {
                checkboxes[i] = new CheckBox(this);
                checkboxes[i].setText(tr.getNome());
                checkboxes[i].setTextSize(16);
                checkboxes[i].setId(idCheck++);
                codiceTrattamentoCheckBox[i] = tr.get_id().toString();
                lL.addView(checkboxes[i]);
                i++;
            }
        } catch (Exception e) {
        } finally {
            realm.close();
        }
    }
}