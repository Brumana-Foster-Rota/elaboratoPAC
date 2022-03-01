package com.example.whatsonsalon;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.bson.types.ObjectId;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class SelezionaSlotAppuntamento extends AppCompatActivity {

    App app;
    Realm realm;
    String appID = "salon-ovfke";

    int minutiDaAllocare = 0;
    int numeroSlotDaProporre = 5;
    Date[] dateDaProporre = new Date[numeroSlotDaProporre];

    //codice del cliente selezionato nell'activity precedente e i relativi trattamenti richiesti
    String codiceCliente;
    ArrayList<String> codiciTrattamenti = new ArrayList<>();

    ArrayList<appuntamenti> appuntamentiDaOraList = new ArrayList<>();
    ArrayList<String> nomeTrattamenti = new ArrayList<>();
    RealmList<String> nomeTrattamentiRealmList = new RealmList<>();

    int idRadio = 300;
    RadioButton[] radiobuttons;
    Long[] millisecondiProposte;
    Long millisecondoScelto;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Date dateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_slot_appuntamento);

        Realm.init(this);

        Bundle extras = getIntent().getExtras();

        //visualizzo questa Activity solo se ho clienti e trattamenti! altrimenti rimane vuota!
        if(extras != null && extras.containsKey("cliente") && extras.containsKey("trattamenti")) {

            codiceCliente = extras.getString("cliente");
            codiciTrattamenti = extras.getStringArrayList("trattamenti");

            app = new App(new AppConfiguration.Builder(appID).build());
            Credentials credentials = Credentials.anonymous();

            app.loginAsync(credentials, result -> {

                if(result.isSuccess()) {

                    User user = app.currentUser();
                    String partition = "salon";
                    SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                    settaInfo(config);
                    proponiSlot(config);
                    riempiScrollView();

                    for(String s : nomeTrattamenti)
                        nomeTrattamentiRealmList.add(s);

                    //se ho radiobuttons allora definisco cosa succede quando clicco un radiobutton
                    if(radiobuttons.length != 0) {
                        RadioGroup rG = findViewById(R.id.listaAppuntamentiDaOggi);
                        rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                if(rG.getCheckedRadioButtonId() != -1) {
                                    //trovare l'indice del radiobutton con id IdRadioButtonChecked all'interno di radiobuttons
                                    //usare quell'indice per cercare i millisecondi corrispondenti a quella data in millisecondiProposte
                                    int idRadioButtonChecked = rG.getCheckedRadioButtonId();
                                    int indice = trovaIndice(radiobuttons, idRadioButtonChecked);

                                    if(indice != -1)
                                        millisecondoScelto = millisecondiProposte[indice];
                                }
                            }
                        });
                    }

                    initDatePicker();
                    dateButton = findViewById(R.id.datePickerButton2);
                    dateButton.setText(getTodaysDate());

                } else {
                    Log.e("SALON", "Autenticazione anonima non avvenuta: " + result.getError());
                }

                final Button bottone = findViewById(R.id.inserisci);
                bottone.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        RadioGroup rG = findViewById(R.id.listaAppuntamentiDaOggi);
                        if(rG.getCheckedRadioButtonId() != -1) {

                            User user = app.currentUser();
                            String partition = "salon";
                            SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                            Date dataOra = new Date();
                            dataOra.setTime(millisecondoScelto);

                            Calendar c = Calendar.getInstance();
                            c.setTime(dataOra);
                            c.add(Calendar.MINUTE, minutiDaAllocare);

                            String ora = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
                            String minuti;
                            if(c.get(Calendar.MINUTE) == 0)
                                minuti = "00";
                            else
                                minuti = String.valueOf(c.get(Calendar.MINUTE));

                            String oraminuti = ora.concat(minuti);

                            appuntamenti nuovoAppuntamento = new appuntamenti();
                            nuovoAppuntamento.set_id(new ObjectId());
                            nuovoAppuntamento.set_partition("salon");
                            nuovoAppuntamento.setCliente(codiceCliente);
                            nuovoAppuntamento.setDataOra(dataOra);
                            nuovoAppuntamento.setOraFine(c.getTime());
                            nuovoAppuntamento.setOra(Integer.valueOf(oraminuti));
                            nuovoAppuntamento.setTrattamenti(nomeTrattamentiRealmList);

                            try {
                                realm = Realm.getInstance(config);
                                realm.executeTransaction (transactionRealm -> {
                                    transactionRealm.insert(nuovoAppuntamento);
                                });
                            } finally {
                                Toast toast = Toast.makeText(getApplicationContext(), "Appuntamento inserito", Toast.LENGTH_SHORT);
                                toast.show();

                                realm.close();
                            }
                            startActivity(new Intent(SelezionaSlotAppuntamento.this, MainPage.class));
                        }
                    }
                });

                final Button bottoneManuale = findViewById(R.id.inserisciManuale);
                bottoneManuale.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        User user = app.currentUser();
                        String partition = "salon";
                        SyncConfiguration config = new SyncConfiguration.Builder(user, partition).allowQueriesOnUiThread(true).allowWritesOnUiThread(true).build();

                        Calendar dataSelezionata = Calendar.getInstance();
                        dataSelezionata.setTime(dateDate);

                        TextView t = findViewById(R.id.orarioPicker);
                        String oraminuti = t.getText().toString();
                        String[] parti = oraminuti.split(":");

                        //controllare che siano numeri! (DA FARE)

                        dataSelezionata.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parti[0]));
                        dataSelezionata.set(Calendar.MINUTE, Integer.valueOf(parti[1]));

                        Date dataSelezionataInDate = dataSelezionata.getTime();

                        Calendar dataSelezionataFine = Calendar.getInstance();
                        dataSelezionataFine.setTime(dataSelezionataInDate);
                        dataSelezionataFine.add(Calendar.MINUTE, minutiDaAllocare);

                        String ora = String.valueOf(dataSelezionataFine.get(Calendar.HOUR_OF_DAY));
                        String minuti;
                        if(dataSelezionataFine.get(Calendar.MINUTE) == 0)
                            minuti = "00";
                        else
                            minuti = String.valueOf(dataSelezionataFine.get(Calendar.MINUTE));

                        String om = ora.concat(minuti);

                        appuntamenti nuovoAppuntamento = new appuntamenti();
                        nuovoAppuntamento.set_id(new ObjectId());
                        nuovoAppuntamento.set_partition("salon");
                        nuovoAppuntamento.setCliente(codiceCliente);
                        nuovoAppuntamento.setDataOra(dataSelezionataInDate);
                        nuovoAppuntamento.setOraFine(dataSelezionataFine.getTime());
                        nuovoAppuntamento.setOra(Integer.valueOf(om));
                        nuovoAppuntamento.setTrattamenti(nomeTrattamentiRealmList);

                        try {
                            realm = Realm.getInstance(config);
                            realm.executeTransaction (transactionRealm -> {
                                transactionRealm.insert(nuovoAppuntamento);
                            });
                        } finally {
                            Toast toast = Toast.makeText(getApplicationContext(), "Appuntamento inserito", Toast.LENGTH_SHORT);
                            toast.show();

                            realm.close();
                        }
                        startActivity(new Intent(SelezionaSlotAppuntamento.this, MainPage.class));
                    }
                });
            });
        }
    }

    private void proponiSlot(SyncConfiguration config) {
        try {
            realm = Realm.getInstance(config);

            //non guardare la data, serve solo l'orario
            Calendar calendarOggi = Calendar.getInstance();
            int year = calendarOggi.get(Calendar.YEAR);
            int month = calendarOggi.get(Calendar.MONTH);
            int day = calendarOggi.get(Calendar.DAY_OF_MONTH);

            Calendar apertura = Calendar.getInstance();
            apertura.set(year, month, day, 9,0);
            Calendar chiusura = Calendar.getInstance();
            chiusura.set(year, month, day,18, 0);

            Date adesso = new Date();

            RealmResults<appuntamenti> appuntamentiDaOra = realm.where(appuntamenti.class).sort("dataOra", Sort.ASCENDING, "ora", Sort.ASCENDING).findAll();

            //appuntamentiDaOraList è praticamente la versione ArrayList della query qui sopra
            //motivazione: RealmResults non contiene oggetti ma riferimenti ad oggetti!
            //quindi in appuntamentiDaOraList ho effettivamente oggetti appuntamenti
            for(appuntamenti a : appuntamentiDaOra) {
                if(a.getDataOra().getTime() >= adesso.getTime() || (a.getDataOra().getTime() <= adesso.getTime() && a.getOraFine().getTime() >= adesso.getTime()))
                    appuntamentiDaOraList.add(a);
            }

            //se non ho appuntamenti propongo subito i primi slot (numero definito dalla variabile globale numeroSlotDaProporre)
            //attenzione però all'orario!!! se va oltre l'orario di chiusura devo passare a domani a partire dall'orario di apertura
            if(appuntamentiDaOraList.size() == 0) {
                //"proposta" così istanziata indica questo preciso istante (data e ora di quando accedo a questa activity)
                Calendar proposta = Calendar.getInstance();
                Calendar chiusuraFake = chiusura;

                //se proposta è prima dell'apertura del negozio, sposto la proposta all'apertura del negozio
                if(proposta.compareTo(apertura) < 0)
                    proposta = apertura;

                int i = 0;
                //finché devo proporre slot...
                while(i < numeroSlotDaProporre) {
                    //aggiungo alla proposta i minuti da allocare
                    //questo mi serve per l'if subito dopo
                    proposta.add(Calendar.MINUTE, minutiDaAllocare);

                    //se l'appuntamento NON va oltre la chiusura del negozio allora posso proporre questo preciso momento
                    //altrimenti devo spostare la proposta a domani, quando il negozio apre
                    if(proposta.compareTo(chiusuraFake) < 0) {
                        proposta.add(Calendar.MINUTE, -minutiDaAllocare);
                        dateDaProporre[i] = proposta.getTime();
                        proposta.add(Calendar.MINUTE, minutiDaAllocare);
                        i++;
                    }
                    else {
                        proposta = apertura;
                        proposta.add(Calendar.DAY_OF_MONTH, 1);
                        chiusuraFake.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }
            } else {

                //caso in cui devo navigare tra appuntamenti già esistenti
                Calendar proposta = Calendar.getInstance();
                Calendar chiusuraFake = Calendar.getInstance();
                chiusuraFake.setTime(chiusura.getTime());
                Calendar aperturaFake = Calendar.getInstance();
                aperturaFake.setTime(apertura.getTime());

                int i = 0;
                int j = 0;
                int size = appuntamentiDaOraList.size();

                //finché ho slot da proporre....
                while(i < numeroSlotDaProporre) {
                    Calendar propostaFake = Calendar.getInstance();
                    propostaFake.setTime(proposta.getTime());
                    propostaFake.add(Calendar.MINUTE, minutiDaAllocare);

                    if(proposta.compareTo(aperturaFake) < 0)
                        proposta = apertura;

                    if(proposta.compareTo(chiusuraFake) > 0 || propostaFake.compareTo(chiusuraFake) > 0) {
                        aperturaFake.add(Calendar.DAY_OF_MONTH, 1);
                        chiusuraFake.add(Calendar.DAY_OF_MONTH,1);
                        proposta = aperturaFake;
                    }

                    //se ho appuntamenti esistenti tra cui navigare
                    if(j < size) {
                        //self-explanatory...
                        //prendo il primo appuntamento che ho in lista e mi memorizzo le date e orari di inizio e fine appuntamento
                        Calendar inizioProxAppuntamento = Calendar.getInstance();
                        inizioProxAppuntamento.setTime(appuntamentiDaOraList.get(j).getDataOra());
                        Calendar fineProxAppuntamento = Calendar.getInstance();
                        fineProxAppuntamento.setTime(appuntamentiDaOraList.get(j).getOraFine());

                        //se mi trovo in mezzo ad un appuntamento sposto la proposta alla fine dell'appuntamento corrente
                        if(proposta.compareTo(inizioProxAppuntamento) > 0 && proposta.compareTo(fineProxAppuntamento) < 0) {
                            proposta = fineProxAppuntamento;
                            j++;
                        } else {
                            //altrimenti calcolo minuti e vedo se ci sto
                            long millisecondiOra = proposta.getTime().getTime();
                            long millisecondiDopo = inizioProxAppuntamento.getTime().getTime();
                            long differenzaMillisecondi = millisecondiDopo - millisecondiOra;

                            //se ho spazio, propogo questo slot e sposto la prossima proposta al termine di questo slot
                            if(differenzaMillisecondi >= minutiDaAllocare * 60000) {
                                dateDaProporre[i] = proposta.getTime();
                                proposta.add(Calendar.MINUTE, minutiDaAllocare);
                                i++;
                            } else {
                                //altrimenti sposto la proposta alla fine dell'appuntamento in studio
                                proposta = fineProxAppuntamento;
                                j++;
                            }
                        }
                    } else {
                        //se non ho più appuntamenti tra cui navigare...
                        // qui è come il caso in cui non ho appuntamenti
                        dateDaProporre[i] = proposta.getTime();
                        proposta.add(Calendar.MINUTE, minutiDaAllocare);
                        i++;
                    }
                }
            }
        } finally {
            realm.close();
        }
    }

    private void riempiScrollView() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataPropostaInString;
        RadioGroup rG = findViewById(R.id.listaAppuntamentiDaOggi);
        int count = 0;

        for(Date d : dateDaProporre)
            count++;

        radiobuttons = new RadioButton[count];
        millisecondiProposte = new Long[count];

        int x = 0;
        for(Date d : dateDaProporre) {
            dataPropostaInString = formatter.format(d);
            radiobuttons[x] = new RadioButton(this);
            radiobuttons[x].setText(dataPropostaInString);
            radiobuttons[x].setId(idRadio++);
            radiobuttons[x].setTextSize(16);
            millisecondiProposte[x] = d.getTime();
            rG.addView(radiobuttons[x]);
            x++;
        }
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

    private void settaInfo(SyncConfiguration config) {
        TextView infoCliente = findViewById(R.id.infoCliente);
        TextView infoTrattamenti = findViewById(R.id.infoTrattamenti);
        TextView infoMinuti = findViewById(R.id.infoMinuti);

        try {
            realm = Realm.getInstance(config);

            RealmResults<clienti> clienteSelezionato = realm.where(clienti.class).findAll();
            RealmResults<trattamenti> risultatiTrattamenti = realm.where(trattamenti.class).findAll();

            for(clienti c : clienteSelezionato) {
                if(codiceCliente.equals(c.get_id().toString())) {
                    infoCliente.setText(c.getNome() + " " + c.getCognome());
                    break;
                }
            }

            for(trattamenti t : risultatiTrattamenti) {
                if(codiciTrattamenti.contains(t.get_id().toString())) {
                    nomeTrattamenti.add(t.getNome());
                    minutiDaAllocare = minutiDaAllocare + Integer.valueOf(t.getDurata());
                }
            }
            infoTrattamenti.setText(nomeTrattamenti.toString());
            infoMinuti.setText("Durata: " + String.valueOf(minutiDaAllocare));

        } finally {
            realm.close();
        }
    }

    public void openDatePicker2(View view) {
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
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);
    }


    private String makeDateString(int day, int month, int year) {
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
}