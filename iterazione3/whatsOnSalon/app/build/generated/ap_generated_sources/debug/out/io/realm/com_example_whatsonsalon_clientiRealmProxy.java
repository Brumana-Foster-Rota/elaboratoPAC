package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.NativeContext;
import io.realm.internal.OsList;
import io.realm.internal.OsMap;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSet;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.core.NativeRealmAny;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_example_whatsonsalon_clientiRealmProxy extends com.example.whatsonsalon.clienti
    implements RealmObjectProxy, com_example_whatsonsalon_clientiRealmProxyInterface {

    static final class clientiColumnInfo extends ColumnInfo {
        long _idColKey;
        long _partitionColKey;
        long cognomeColKey;
        long comlpeannoColKey;
        long compleannoColKey;
        long nomeColKey;
        long telefonoColKey;

        clientiColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("clienti");
            this._idColKey = addColumnDetails("_id", "_id", objectSchemaInfo);
            this._partitionColKey = addColumnDetails("_partition", "_partition", objectSchemaInfo);
            this.cognomeColKey = addColumnDetails("cognome", "cognome", objectSchemaInfo);
            this.comlpeannoColKey = addColumnDetails("comlpeanno", "comlpeanno", objectSchemaInfo);
            this.compleannoColKey = addColumnDetails("compleanno", "compleanno", objectSchemaInfo);
            this.nomeColKey = addColumnDetails("nome", "nome", objectSchemaInfo);
            this.telefonoColKey = addColumnDetails("telefono", "telefono", objectSchemaInfo);
        }

        clientiColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new clientiColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final clientiColumnInfo src = (clientiColumnInfo) rawSrc;
            final clientiColumnInfo dst = (clientiColumnInfo) rawDst;
            dst._idColKey = src._idColKey;
            dst._partitionColKey = src._partitionColKey;
            dst.cognomeColKey = src.cognomeColKey;
            dst.comlpeannoColKey = src.comlpeannoColKey;
            dst.compleannoColKey = src.compleannoColKey;
            dst.nomeColKey = src.nomeColKey;
            dst.telefonoColKey = src.telefonoColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private clientiColumnInfo columnInfo;
    private ProxyState<com.example.whatsonsalon.clienti> proxyState;

    com_example_whatsonsalon_clientiRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (clientiColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.whatsonsalon.clienti>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public org.bson.types.ObjectId realmGet$_id() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo._idColKey)) {
            return null;
        }
        return (org.bson.types.ObjectId) proxyState.getRow$realm().getObjectId(columnInfo._idColKey);
    }

    @Override
    public void realmSet$_id(org.bson.types.ObjectId value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field '_id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$_partition() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo._partitionColKey);
    }

    @Override
    public void realmSet$_partition(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field '_partition' to null.");
            }
            row.getTable().setString(columnInfo._partitionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field '_partition' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo._partitionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$cognome() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cognomeColKey);
    }

    @Override
    public void realmSet$cognome(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.cognomeColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.cognomeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.cognomeColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.cognomeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$comlpeanno() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.comlpeannoColKey);
    }

    @Override
    public void realmSet$comlpeanno(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.comlpeannoColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.comlpeannoColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.comlpeannoColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.comlpeannoColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$compleanno() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.compleannoColKey);
    }

    @Override
    public void realmSet$compleanno(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.compleannoColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.compleannoColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.compleannoColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.compleannoColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nome() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nomeColKey);
    }

    @Override
    public void realmSet$nome(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nomeColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.nomeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nomeColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nomeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$telefono() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.telefonoColKey);
    }

    @Override
    public void realmSet$telefono(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.telefonoColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.telefonoColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.telefonoColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.telefonoColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "clienti", false, 7, 0);
        builder.addPersistedProperty(NO_ALIAS, "_id", RealmFieldType.OBJECT_ID, Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "_partition", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "cognome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "comlpeanno", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "compleanno", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "nome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "telefono", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static clientiColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new clientiColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "clienti";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "clienti";
    }

    @SuppressWarnings("cast")
    public static com.example.whatsonsalon.clienti createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.whatsonsalon.clienti obj = null;
        if (update) {
            Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
            clientiColumnInfo columnInfo = (clientiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class);
            long pkColumnKey = columnInfo._idColKey;
            long objKey = Table.NO_MATCH;
            if (json.isNull("_id")) {
                objKey = table.findFirstNull(pkColumnKey);
            } else {
                objKey = table.findFirstObjectId(pkColumnKey, new org.bson.types.ObjectId((String)json.get("_id")));
            }
            if (objKey != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_whatsonsalon_clientiRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("_id")) {
                if (json.isNull("_id")) {
                    obj = (io.realm.com_example_whatsonsalon_clientiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.clienti.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_whatsonsalon_clientiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.clienti.class, json.get("_id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field '_id'.");
            }
        }

        final com_example_whatsonsalon_clientiRealmProxyInterface objProxy = (com_example_whatsonsalon_clientiRealmProxyInterface) obj;
        if (json.has("_partition")) {
            if (json.isNull("_partition")) {
                objProxy.realmSet$_partition(null);
            } else {
                objProxy.realmSet$_partition((String) json.getString("_partition"));
            }
        }
        if (json.has("cognome")) {
            if (json.isNull("cognome")) {
                objProxy.realmSet$cognome(null);
            } else {
                objProxy.realmSet$cognome((String) json.getString("cognome"));
            }
        }
        if (json.has("comlpeanno")) {
            if (json.isNull("comlpeanno")) {
                objProxy.realmSet$comlpeanno(null);
            } else {
                objProxy.realmSet$comlpeanno((String) json.getString("comlpeanno"));
            }
        }
        if (json.has("compleanno")) {
            if (json.isNull("compleanno")) {
                objProxy.realmSet$compleanno(null);
            } else {
                objProxy.realmSet$compleanno((String) json.getString("compleanno"));
            }
        }
        if (json.has("nome")) {
            if (json.isNull("nome")) {
                objProxy.realmSet$nome(null);
            } else {
                objProxy.realmSet$nome((String) json.getString("nome"));
            }
        }
        if (json.has("telefono")) {
            if (json.isNull("telefono")) {
                objProxy.realmSet$telefono(null);
            } else {
                objProxy.realmSet$telefono((String) json.getString("telefono"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.whatsonsalon.clienti createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.whatsonsalon.clienti obj = new com.example.whatsonsalon.clienti();
        final com_example_whatsonsalon_clientiRealmProxyInterface objProxy = (com_example_whatsonsalon_clientiRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("_id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$_id(null);
                } else {
                    objProxy.realmSet$_id(new org.bson.types.ObjectId(reader.nextString()));
                }
            } else if (name.equals("_partition")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$_partition((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$_partition(null);
                }
            } else if (name.equals("cognome")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cognome((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$cognome(null);
                }
            } else if (name.equals("comlpeanno")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$comlpeanno((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$comlpeanno(null);
                }
            } else if (name.equals("compleanno")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$compleanno((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$compleanno(null);
                }
            } else if (name.equals("nome")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nome((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nome(null);
                }
            } else if (name.equals("telefono")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$telefono((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$telefono(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field '_id'.");
        }
        return realm.copyToRealmOrUpdate(obj);
    }

    static com_example_whatsonsalon_clientiRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class), false, Collections.<String>emptyList());
        io.realm.com_example_whatsonsalon_clientiRealmProxy obj = new io.realm.com_example_whatsonsalon_clientiRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.whatsonsalon.clienti copyOrUpdate(Realm realm, clientiColumnInfo columnInfo, com.example.whatsonsalon.clienti object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.whatsonsalon.clienti) cachedRealmObject;
        }

        com.example.whatsonsalon.clienti realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
            long pkColumnKey = columnInfo._idColKey;
            org.bson.types.ObjectId value = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_id();
            long objKey = Table.NO_MATCH;
            if (value == null) {
                objKey = table.findFirstNull(pkColumnKey);
            } else {
                objKey = table.findFirstObjectId(pkColumnKey, value);
            }
            if (objKey == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(objKey), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_example_whatsonsalon_clientiRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.whatsonsalon.clienti copy(Realm realm, clientiColumnInfo columnInfo, com.example.whatsonsalon.clienti newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.whatsonsalon.clienti) cachedRealmObject;
        }

        com_example_whatsonsalon_clientiRealmProxyInterface unmanagedSource = (com_example_whatsonsalon_clientiRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addObjectId(columnInfo._idColKey, unmanagedSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, unmanagedSource.realmGet$_partition());
        builder.addString(columnInfo.cognomeColKey, unmanagedSource.realmGet$cognome());
        builder.addString(columnInfo.comlpeannoColKey, unmanagedSource.realmGet$comlpeanno());
        builder.addString(columnInfo.compleannoColKey, unmanagedSource.realmGet$compleanno());
        builder.addString(columnInfo.nomeColKey, unmanagedSource.realmGet$nome());
        builder.addString(columnInfo.telefonoColKey, unmanagedSource.realmGet$telefono());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_whatsonsalon_clientiRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.whatsonsalon.clienti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        long tableNativePtr = table.getNativePtr();
        clientiColumnInfo columnInfo = (clientiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_id();
        long objKey = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
        } else {
            objKey = Table.nativeFindFirstObjectId(tableNativePtr, pkColumnKey, primaryKeyValue.toString());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$_partition = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        }
        String realmGet$cognome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$cognome();
        if (realmGet$cognome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cognomeColKey, objKey, realmGet$cognome, false);
        }
        String realmGet$comlpeanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$comlpeanno();
        if (realmGet$comlpeanno != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.comlpeannoColKey, objKey, realmGet$comlpeanno, false);
        }
        String realmGet$compleanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$compleanno();
        if (realmGet$compleanno != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.compleannoColKey, objKey, realmGet$compleanno, false);
        }
        String realmGet$nome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
        }
        String realmGet$telefono = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$telefono();
        if (realmGet$telefono != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.telefonoColKey, objKey, realmGet$telefono, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        long tableNativePtr = table.getNativePtr();
        clientiColumnInfo columnInfo = (clientiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.clienti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.clienti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_id();
            long objKey = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
            } else {
                objKey = Table.nativeFindFirstObjectId(tableNativePtr, pkColumnKey, primaryKeyValue.toString());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$_partition = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            }
            String realmGet$cognome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$cognome();
            if (realmGet$cognome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cognomeColKey, objKey, realmGet$cognome, false);
            }
            String realmGet$comlpeanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$comlpeanno();
            if (realmGet$comlpeanno != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.comlpeannoColKey, objKey, realmGet$comlpeanno, false);
            }
            String realmGet$compleanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$compleanno();
            if (realmGet$compleanno != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.compleannoColKey, objKey, realmGet$compleanno, false);
            }
            String realmGet$nome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
            }
            String realmGet$telefono = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$telefono();
            if (realmGet$telefono != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.telefonoColKey, objKey, realmGet$telefono, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.whatsonsalon.clienti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        long tableNativePtr = table.getNativePtr();
        clientiColumnInfo columnInfo = (clientiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_id();
        long objKey = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
        } else {
            objKey = Table.nativeFindFirstObjectId(tableNativePtr, pkColumnKey, primaryKeyValue.toString());
        }
        if (objKey == Table.NO_MATCH) {
            objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
        }
        cache.put(object, objKey);
        String realmGet$_partition = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
        }
        String realmGet$cognome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$cognome();
        if (realmGet$cognome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cognomeColKey, objKey, realmGet$cognome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cognomeColKey, objKey, false);
        }
        String realmGet$comlpeanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$comlpeanno();
        if (realmGet$comlpeanno != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.comlpeannoColKey, objKey, realmGet$comlpeanno, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.comlpeannoColKey, objKey, false);
        }
        String realmGet$compleanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$compleanno();
        if (realmGet$compleanno != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.compleannoColKey, objKey, realmGet$compleanno, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.compleannoColKey, objKey, false);
        }
        String realmGet$nome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nomeColKey, objKey, false);
        }
        String realmGet$telefono = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$telefono();
        if (realmGet$telefono != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.telefonoColKey, objKey, realmGet$telefono, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.telefonoColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        long tableNativePtr = table.getNativePtr();
        clientiColumnInfo columnInfo = (clientiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.clienti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.clienti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.clienti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_id();
            long objKey = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                objKey = Table.nativeFindFirstNull(tableNativePtr, pkColumnKey);
            } else {
                objKey = Table.nativeFindFirstObjectId(tableNativePtr, pkColumnKey, primaryKeyValue.toString());
            }
            if (objKey == Table.NO_MATCH) {
                objKey = OsObject.createRowWithPrimaryKey(table, pkColumnKey, primaryKeyValue);
            }
            cache.put(object, objKey);
            String realmGet$_partition = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
            }
            String realmGet$cognome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$cognome();
            if (realmGet$cognome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cognomeColKey, objKey, realmGet$cognome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cognomeColKey, objKey, false);
            }
            String realmGet$comlpeanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$comlpeanno();
            if (realmGet$comlpeanno != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.comlpeannoColKey, objKey, realmGet$comlpeanno, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.comlpeannoColKey, objKey, false);
            }
            String realmGet$compleanno = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$compleanno();
            if (realmGet$compleanno != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.compleannoColKey, objKey, realmGet$compleanno, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.compleannoColKey, objKey, false);
            }
            String realmGet$nome = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nomeColKey, objKey, false);
            }
            String realmGet$telefono = ((com_example_whatsonsalon_clientiRealmProxyInterface) object).realmGet$telefono();
            if (realmGet$telefono != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.telefonoColKey, objKey, realmGet$telefono, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.telefonoColKey, objKey, false);
            }
        }
    }

    public static com.example.whatsonsalon.clienti createDetachedCopy(com.example.whatsonsalon.clienti realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.whatsonsalon.clienti unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.whatsonsalon.clienti();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.whatsonsalon.clienti) cachedObject.object;
            }
            unmanagedObject = (com.example.whatsonsalon.clienti) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_whatsonsalon_clientiRealmProxyInterface unmanagedCopy = (com_example_whatsonsalon_clientiRealmProxyInterface) unmanagedObject;
        com_example_whatsonsalon_clientiRealmProxyInterface realmSource = (com_example_whatsonsalon_clientiRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$_id(realmSource.realmGet$_id());
        unmanagedCopy.realmSet$_partition(realmSource.realmGet$_partition());
        unmanagedCopy.realmSet$cognome(realmSource.realmGet$cognome());
        unmanagedCopy.realmSet$comlpeanno(realmSource.realmGet$comlpeanno());
        unmanagedCopy.realmSet$compleanno(realmSource.realmGet$compleanno());
        unmanagedCopy.realmSet$nome(realmSource.realmGet$nome());
        unmanagedCopy.realmSet$telefono(realmSource.realmGet$telefono());

        return unmanagedObject;
    }

    static com.example.whatsonsalon.clienti update(Realm realm, clientiColumnInfo columnInfo, com.example.whatsonsalon.clienti realmObject, com.example.whatsonsalon.clienti newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_whatsonsalon_clientiRealmProxyInterface realmObjectTarget = (com_example_whatsonsalon_clientiRealmProxyInterface) realmObject;
        com_example_whatsonsalon_clientiRealmProxyInterface realmObjectSource = (com_example_whatsonsalon_clientiRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.whatsonsalon.clienti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addObjectId(columnInfo._idColKey, realmObjectSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, realmObjectSource.realmGet$_partition());
        builder.addString(columnInfo.cognomeColKey, realmObjectSource.realmGet$cognome());
        builder.addString(columnInfo.comlpeannoColKey, realmObjectSource.realmGet$comlpeanno());
        builder.addString(columnInfo.compleannoColKey, realmObjectSource.realmGet$compleanno());
        builder.addString(columnInfo.nomeColKey, realmObjectSource.realmGet$nome());
        builder.addString(columnInfo.telefonoColKey, realmObjectSource.realmGet$telefono());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("clienti = proxy[");
        stringBuilder.append("{_id:");
        stringBuilder.append(realmGet$_id() != null ? realmGet$_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_partition:");
        stringBuilder.append(realmGet$_partition());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cognome:");
        stringBuilder.append(realmGet$cognome() != null ? realmGet$cognome() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{comlpeanno:");
        stringBuilder.append(realmGet$comlpeanno() != null ? realmGet$comlpeanno() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{compleanno:");
        stringBuilder.append(realmGet$compleanno() != null ? realmGet$compleanno() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nome:");
        stringBuilder.append(realmGet$nome() != null ? realmGet$nome() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{telefono:");
        stringBuilder.append(realmGet$telefono() != null ? realmGet$telefono() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long objKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (objKey ^ (objKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_example_whatsonsalon_clientiRealmProxy aclienti = (com_example_whatsonsalon_clientiRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aclienti.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aclienti.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aclienti.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
