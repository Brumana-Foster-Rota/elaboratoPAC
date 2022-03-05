package com.example.whatsonsalon;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class clienti extends RealmObject {
    @PrimaryKey
    private ObjectId _id;
    @Required
    private String _partition;
    private String cognome;
    private String comlpeanno;
    private String compleanno;
    private String nome;
    private String telefono;
    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String get_partition() { return _partition; }
    public void set_partition(String _partition) { this._partition = _partition; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getComlpeanno() { return comlpeanno; }
    public void setComlpeanno(String comlpeanno) { this.comlpeanno = comlpeanno; }
    public String getCompleanno() { return compleanno; }
    public void setCompleanno(String compleanno) { this.compleanno = compleanno; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}