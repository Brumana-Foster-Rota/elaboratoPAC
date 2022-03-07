package io.realm;


public interface com_example_whatsonsalon_appuntamentiRealmProxyInterface {
    public org.bson.types.ObjectId realmGet$_id();
    public void realmSet$_id(org.bson.types.ObjectId value);
    public String realmGet$_partition();
    public void realmSet$_partition(String value);
    public String realmGet$cliente();
    public void realmSet$cliente(String value);
    public java.util.Date realmGet$dataOra();
    public void realmSet$dataOra(java.util.Date value);
    public Integer realmGet$ora();
    public void realmSet$ora(Integer value);
    public java.util.Date realmGet$oraFine();
    public void realmSet$oraFine(java.util.Date value);
    public RealmList<String> realmGet$trattamenti();
    public void realmSet$trattamenti(RealmList<String> value);
}
