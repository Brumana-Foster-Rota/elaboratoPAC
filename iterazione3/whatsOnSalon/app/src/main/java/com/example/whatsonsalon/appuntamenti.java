package com.example.whatsonsalon;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import java.util.Date;
import org.bson.types.ObjectId;

public class appuntamenti extends RealmObject {
    @PrimaryKey
    private ObjectId _id;

    @Required
    private String _partition;

    private String cliente;

    private Date dataOra;

    private Integer ora;

    private Date oraFine;

    @Required
    private RealmList<String> trattamenti;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String get_partition() { return _partition; }
    public void set_partition(String _partition) { this._partition = _partition; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public Date getDataOra() { return dataOra; }
    public void setDataOra(Date dataOra) { this.dataOra = dataOra; }

    public Integer getOra() { return ora; }
    public void setOra(Integer ora) { this.ora = ora; }

    public Date getOraFine() { return oraFine; }
    public void setOraFine(Date oraFine) { this.oraFine = oraFine; }

    public RealmList<String> getTrattamenti() { return trattamenti; }
    public void setTrattamenti(RealmList<String> trattamenti) { this.trattamenti = trattamenti; }
}

