package com.example.whatsonsalon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.Sync;
import io.realm.mongodb.sync.SyncConfiguration;

public class MainPage extends AppCompatActivity {

    App app;
    Realm realm;
    String appID = "salon-ovfke";

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Date dateDate;

    ArrayList<String> codiciAppuntamentiDaEliminare = new ArrayList<>();
    CheckBox[] checkboxes;
    String[] codiciAppCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Realm.init(this);

        //inizializzazione app e accesso anonimo a Realm
        app = new App(new AppConfiguration.Builder(appID).build());
        Credentials credentials = Credentials.anonymous();

        app.loginAsync(credentials, result -> {
            if(result.isSuccess()){

                //configurazione della connessione e apertura Realm
                User user = app.currentUser();
                String partition = "salon";
                SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                //metodi che servono per la rappresentazione grafica del dataPicker
                //inoltre mi rende possibile modificare la variabile globale dateDate che viene pickato nel dataPicker
                initDatePicker();
                dateButton = findViewById(R.id.datePickerButton);
                dateButton.setText(getTodaysDate());

                //metodo per la popolazione della tabella a primo avvio dell'activity
                //questa funzione verrà richiamata anche quando dovrò ripopolare la tabella per le successive volte
                //mettendo false come secondo campo della funzione! (il booleano mi indica se è la prima volte che popolo la tabella o meno)
                popolaTabella(config, true);
            } else {
                Log.e("SALON", "Autenticazione anonima non avvenuta: " + result.getError());
            }
        });

        //bottone Aggiungi per passare all'attività successiva (AggiungiAppActivity)
        final Button bottoneAggiungi = findViewById(R.id.button2);
        bottoneAggiungi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, AggiungiAppActivity.class));
            }
        });

        //bottone Elimina per eliminare gli appuntamenti che sono stati selezionati
        final Button bottoneElimina = findViewById(R.id.bottonElimina);
        bottoneElimina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!codiciAppuntamentiDaEliminare.isEmpty()) {

                    User user = app.currentUser();
                    String partition = "salon";
                    SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                    try {
                        realm = Realm.getInstance(config);
                        RealmResults<appuntamenti> risultatiAppuntamenti = realm.where(appuntamenti.class).findAll();

                        for(appuntamenti a : risultatiAppuntamenti) {
                            if(codiciAppuntamentiDaEliminare.contains(a.get_id().toString())){
                                realm.executeTransaction( transactionRealm -> {
                                    a.deleteFromRealm();
                                });
                            }
                        }
                    } finally {
                        String testoToast;
                        if(codiciAppuntamentiDaEliminare.size() == 1)
                            testoToast = "Appuntamento eliminato con successo";
                        else
                            testoToast = "Appuntamenti eliminati con successo";

                        Toast toast = Toast.makeText(getApplicationContext(), testoToast, Toast.LENGTH_SHORT);
                        toast.show();

                        realm.close();
                    }
                    startActivity(new Intent(MainPage.this, MainPage.class));
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Nessun appuntamento selezionato", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    //metodo per la popolazione della tabella
    public void popolaTabella(SyncConfiguration config, boolean primaVolta) {

        //data di oggi che mi serve come parametro della prima query che faccio quando accedo a questa Activity
        //trasforma il formato della data per farlo coincidere con lo schema di Realm
        Date dataOggi = new Date();
        SimpleDateFormat formatterPerQuery = new SimpleDateFormat("yyyy-MM-dd");
        String strPerQuery;

        try {
            //apro Realm con la configurazione config
            realm = Realm.getInstance(config);

            //query di fetch degli appuntamenti e dei clienti
            RealmResults<appuntamenti> risultatiAppuntamenti = realm.where(appuntamenti.class).sort("ora", Sort.ASCENDING).findAll();
            RealmResults<clienti> risultatiClienti = realm.where(clienti.class).findAll();

            LinearLayout lL = findViewById(R.id.linearLayout);

            // se primaVolta == true allora significa che sto accedendo a questa activity per la prima volta e che quindi devo visualizzare gli appuntamenti di OGGI
            // altrimenti, se non è la primaVolta, visualizzo gli appuntamenti della data scelta nel datapicker (dateDate)
            if(primaVolta)
                strPerQuery = formatterPerQuery.format(dataOggi);
            else {
                strPerQuery = formatterPerQuery.format(dateDate);
                lL.removeAllViewsInLayout();
            }

            //conto quanti checkbox devo creare (e quindi quali appuntamenti coincidono con la data selezionata)
            int cont = 0;
            for(appuntamenti apt : risultatiAppuntamenti) {
                if(formatterPerQuery.format(apt.getDataOra()).equals(strPerQuery) && apt.isValid())
                    cont++;
            }

            checkboxes = new CheckBox[cont];
            codiciAppCheckBox = new String[cont];

            int i = 0;
            for (appuntamenti apt : risultatiAppuntamenti) {
                //se le date coincidono allora creo un checkbox con le informazioni generali dell'appuntamento
                if(formatterPerQuery.format(apt.getDataOra()).equals(strPerQuery) && apt.isValid()) {
                    ArrayList<String> infoAppuntamenti = new ArrayList<>();

                    for(String s : apt.getTrattamenti())
                        infoAppuntamenti.add(s);

                    clienti clienteInfo = trovaCliente(apt.getCliente(), risultatiClienti);

                    checkboxes[i] = new CheckBox(this);
                    checkboxes[i].setText(clienteInfo.getNome().toUpperCase()  + " " + clienteInfo.getCognome().toUpperCase() + " " + infoAppuntamenti.toString() +"\n[" + mostraOrario(apt.getDataOra().getTime()) + " - " + mostraOrario(apt.getOraFine().getTime()) + "]");
                    checkboxes[i].setTextSize(15);
                    codiciAppCheckBox[i] = apt.get_id().toString();
                    lL.addView(checkboxes[i]);
                    i++;
                }
            }
        } finally {
            realm.close();
        }

        //...poi per i checkbox che ho creato definisco la funzione onCheckedChanged, cioè cosa succede quando seleziono una data
        //quello che faccio è riempire l'arraylist codiciAppuntamentiDaEliminare che contiene i codici degli appuntamenti selezionati
        //che andranno poi eliminati
        if(checkboxes.length != 0) {
            for(int i = 0; i < checkboxes.length; i++) {
                int j = i;
                checkboxes[j].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        //se è checkato aggiungo a codiciAppuntamentiDaEliminare il codice relativo dell'appuntamento
                        //è definito qui anche il caso in cui checko e un-checko un appuntamento
                        if(checkboxes[j].isChecked()){
                            codiciAppuntamentiDaEliminare.add(codiciAppCheckBox[j]);
                        } else {
                            if(codiciAppuntamentiDaEliminare.contains(codiciAppCheckBox[j]))
                                codiciAppuntamentiDaEliminare.remove(codiciAppCheckBox[j]);
                        }
                    }
                });
            }
        }

    }

    //metodo che mi ritorna il cliente con il codiceCliente
    //mi serve per mostrare le informazioni del cliente di un determinato appuntamento
    public clienti trovaCliente(String codiceCliente, RealmResults<clienti> arrayClienti){
        clienti vuoto = new clienti();
        for(int i = 0; i< arrayClienti.size(); i++) {
            if(codiceCliente.equals(arrayClienti.get(i).get_id().toString())) {
                return arrayClienti.get(i);
            }
        }
        return vuoto;
    }

    // ------- DA QUI IN POI SONO METODI E FUNZIONI CHE SERVONO PER LA VISUALIZZAZIONE E FUNZIONAMENTO DEL DATAPICKER ------- //
    // (risultato più importante di questa serie di metodi è la definizione della data selezionata nel datapicker, che risiede nella variabile (globale) dateDate)

    public String mostraOrario(long millisecondi) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millisecondi));

        String ore = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minuti;

        if(c.get(Calendar.MINUTE) == 0)
            minuti = "00";
        else if(c.get(Calendar.MINUTE) < 10)
            minuti = "0".concat(String.valueOf(c.get(Calendar.MINUTE)));
        else
            minuti = String.valueOf(c.get(Calendar.MINUTE));

        return ore + ":" + minuti;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

                Calendar c = Calendar.getInstance();
                c.set(year, month-1, day);

                //variabile globale che indica la data selezionata nel datapicker
                dateDate = c.getTime();

                User user = app.currentUser();
                String partition = "salon";
                SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                //aggiornaTabella();
                popolaTabella(config, false);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);
    }


    private String makeDateString(int day, int month, int year) {
        //return getMonthFormat(month) + " " + day + " " + year;
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "GENNAIO";
        if(month == 2)
            return "FEBBRAIO";
        if(month == 3)
            return "MARZO";
        if(month == 4)
            return "APRILE";
        if(month == 5)
            return "MAGGIO";
        if(month == 6)
            return "GIUGNO";
        if(month == 7)
            return "LUGLIO";
        if(month == 8)
            return "AGOSTO";
        if(month == 9)
            return "SETTEMBRE";
        if(month == 10)
            return "OTTOBRE";
        if(month == 11)
            return "NOVEMBRE";
        if(month == 12)
            return "DICEMBRE";

        //default should never happen
        return "GENNAIO";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.currentUser().logOutAsync(result -> {
            if (result.isSuccess()) {
                Log.v("QUICKSTART", "Successfully logged out.");
            } else {
                Log.e("QUICKSTART", "Failed to log out, error: " + result.getError());
            }
        });
    }

    public void esci(View view) {
        MainPage.this.finishAffinity();
        System.exit(0);
    }
}