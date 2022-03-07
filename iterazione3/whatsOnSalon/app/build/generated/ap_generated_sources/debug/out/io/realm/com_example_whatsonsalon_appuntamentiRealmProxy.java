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
public class com_example_whatsonsalon_appuntamentiRealmProxy extends com.example.whatsonsalon.appuntamenti
    implements RealmObjectProxy, com_example_whatsonsalon_appuntamentiRealmProxyInterface {

    static final class appuntamentiColumnInfo extends ColumnInfo {
        long _idColKey;
        long _partitionColKey;
        long clienteColKey;
        long dataOraColKey;
        long oraColKey;
        long oraFineColKey;
        long trattamentiColKey;

        appuntamentiColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("appuntamenti");
            this._idColKey = addColumnDetails("_id", "_id", objectSchemaInfo);
            this._partitionColKey = addColumnDetails("_partition", "_partition", objectSchemaInfo);
            this.clienteColKey = addColumnDetails("cliente", "cliente", objectSchemaInfo);
            this.dataOraColKey = addColumnDetails("dataOra", "dataOra", objectSchemaInfo);
            this.oraColKey = addColumnDetails("ora", "ora", objectSchemaInfo);
            this.oraFineColKey = addColumnDetails("oraFine", "oraFine", objectSchemaInfo);
            this.trattamentiColKey = addColumnDetails("trattamenti", "trattamenti", objectSchemaInfo);
        }

        appuntamentiColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new appuntamentiColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final appuntamentiColumnInfo src = (appuntamentiColumnInfo) rawSrc;
            final appuntamentiColumnInfo dst = (appuntamentiColumnInfo) rawDst;
            dst._idColKey = src._idColKey;
            dst._partitionColKey = src._partitionColKey;
            dst.clienteColKey = src.clienteColKey;
            dst.dataOraColKey = src.dataOraColKey;
            dst.oraColKey = src.oraColKey;
            dst.oraFineColKey = src.oraFineColKey;
            dst.trattamentiColKey = src.trattamentiColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private appuntamentiColumnInfo columnInfo;
    private ProxyState<com.example.whatsonsalon.appuntamenti> proxyState;
    private RealmList<String> trattamentiRealmList;

    com_example_whatsonsalon_appuntamentiRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (appuntamentiColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.whatsonsalon.appuntamenti>(this);
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
    public String realmGet$cliente() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.clienteColKey);
    }

    @Override
    public void realmSet$cliente(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.clienteColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.clienteColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.clienteColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.clienteColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Date realmGet$dataOra() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.dataOraColKey)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.dataOraColKey);
    }

    @Override
    public void realmSet$dataOra(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dataOraColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setDate(columnInfo.dataOraColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dataOraColKey);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.dataOraColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$ora() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.oraColKey)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.oraColKey);
    }

    @Override
    public void realmSet$ora(Integer value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.oraColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setLong(columnInfo.oraColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.oraColKey);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.oraColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Date realmGet$oraFine() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.oraFineColKey)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.oraFineColKey);
    }

    @Override
    public void realmSet$oraFine(Date value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.oraFineColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setDate(columnInfo.oraFineColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.oraFineColKey);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.oraFineColKey, value);
    }

    @Override
    public RealmList<String> realmGet$trattamenti() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (trattamentiRealmList != null) {
            return trattamentiRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getValueList(columnInfo.trattamentiColKey, RealmFieldType.STRING_LIST);
            trattamentiRealmList = new RealmList<java.lang.String>(java.lang.String.class, osList, proxyState.getRealm$realm());
            return trattamentiRealmList;
        }
    }

    @Override
    public void realmSet$trattamenti(RealmList<String> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("trattamenti")) {
                return;
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getValueList(columnInfo.trattamentiColKey, RealmFieldType.STRING_LIST);
        osList.removeAll();
        if (value == null) {
            return;
        }
        for (java.lang.String item : value) {
            if (item == null) {
                throw new IllegalArgumentException("Storing 'null' into trattamenti' is not allowed by the schema.");
            } else {
                osList.addString(item);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "appuntamenti", false, 7, 0);
        builder.addPersistedProperty(NO_ALIAS, "_id", RealmFieldType.OBJECT_ID, Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "_partition", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "cliente", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "dataOra", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "ora", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "oraFine", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedValueListProperty(NO_ALIAS, "trattamenti", RealmFieldType.STRING_LIST, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static appuntamentiColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new appuntamentiColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "appuntamenti";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "appuntamenti";
    }

    @SuppressWarnings("cast")
    public static com.example.whatsonsalon.appuntamenti createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.example.whatsonsalon.appuntamenti obj = null;
        if (update) {
            Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
            appuntamentiColumnInfo columnInfo = (appuntamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class);
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
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_whatsonsalon_appuntamentiRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("trattamenti")) {
                excludeFields.add("trattamenti");
            }
            if (json.has("_id")) {
                if (json.isNull("_id")) {
                    obj = (io.realm.com_example_whatsonsalon_appuntamentiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.appuntamenti.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_whatsonsalon_appuntamentiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.appuntamenti.class, json.get("_id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field '_id'.");
            }
        }

        final com_example_whatsonsalon_appuntamentiRealmProxyInterface objProxy = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) obj;
        if (json.has("_partition")) {
            if (json.isNull("_partition")) {
                objProxy.realmSet$_partition(null);
            } else {
                objProxy.realmSet$_partition((String) json.getString("_partition"));
            }
        }
        if (json.has("cliente")) {
            if (json.isNull("cliente")) {
                objProxy.realmSet$cliente(null);
            } else {
                objProxy.realmSet$cliente((String) json.getString("cliente"));
            }
        }
        if (json.has("dataOra")) {
            if (json.isNull("dataOra")) {
                objProxy.realmSet$dataOra(null);
            } else {
                Object timestamp = json.get("dataOra");
                if (timestamp instanceof String) {
                    objProxy.realmSet$dataOra(JsonUtils.stringToDate((String) timestamp));
                } else {
                    objProxy.realmSet$dataOra(new Date(json.getLong("dataOra")));
                }
            }
        }
        if (json.has("ora")) {
            if (json.isNull("ora")) {
                objProxy.realmSet$ora(null);
            } else {
                objProxy.realmSet$ora((int) json.getInt("ora"));
            }
        }
        if (json.has("oraFine")) {
            if (json.isNull("oraFine")) {
                objProxy.realmSet$oraFine(null);
            } else {
                Object timestamp = json.get("oraFine");
                if (timestamp instanceof String) {
                    objProxy.realmSet$oraFine(JsonUtils.stringToDate((String) timestamp));
                } else {
                    objProxy.realmSet$oraFine(new Date(json.getLong("oraFine")));
                }
            }
        }
        ProxyUtils.setRealmListWithJsonObject(realm, objProxy.realmGet$trattamenti(), json, "trattamenti", update);
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.whatsonsalon.appuntamenti createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.whatsonsalon.appuntamenti obj = new com.example.whatsonsalon.appuntamenti();
        final com_example_whatsonsalon_appuntamentiRealmProxyInterface objProxy = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) obj;
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
            } else if (name.equals("cliente")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cliente((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$cliente(null);
                }
            } else if (name.equals("dataOra")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$dataOra(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        objProxy.realmSet$dataOra(new Date(timestamp));
                    }
                } else {
                    objProxy.realmSet$dataOra(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("ora")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$ora((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$ora(null);
                }
            } else if (name.equals("oraFine")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$oraFine(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        objProxy.realmSet$oraFine(new Date(timestamp));
                    }
                } else {
                    objProxy.realmSet$oraFine(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("trattamenti")) {
                objProxy.realmSet$trattamenti(ProxyUtils.createRealmListWithJsonStream(java.lang.String.class, reader));
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

    static com_example_whatsonsalon_appuntamentiRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class), false, Collections.<String>emptyList());
        io.realm.com_example_whatsonsalon_appuntamentiRealmProxy obj = new io.realm.com_example_whatsonsalon_appuntamentiRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.whatsonsalon.appuntamenti copyOrUpdate(Realm realm, appuntamentiColumnInfo columnInfo, com.example.whatsonsalon.appuntamenti object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.whatsonsalon.appuntamenti) cachedRealmObject;
        }

        com.example.whatsonsalon.appuntamenti realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
            long pkColumnKey = columnInfo._idColKey;
            org.bson.types.ObjectId value = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_id();
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
                    realmObject = new io.realm.com_example_whatsonsalon_appuntamentiRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.whatsonsalon.appuntamenti copy(Realm realm, appuntamentiColumnInfo columnInfo, com.example.whatsonsalon.appuntamenti newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.whatsonsalon.appuntamenti) cachedRealmObject;
        }

        com_example_whatsonsalon_appuntamentiRealmProxyInterface unmanagedSource = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addObjectId(columnInfo._idColKey, unmanagedSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, unmanagedSource.realmGet$_partition());
        builder.addString(columnInfo.clienteColKey, unmanagedSource.realmGet$cliente());
        builder.addDate(columnInfo.dataOraColKey, unmanagedSource.realmGet$dataOra());
        builder.addInteger(columnInfo.oraColKey, unmanagedSource.realmGet$ora());
        builder.addDate(columnInfo.oraFineColKey, unmanagedSource.realmGet$oraFine());
        builder.addStringList(columnInfo.trattamentiColKey, unmanagedSource.realmGet$trattamenti());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_whatsonsalon_appuntamentiRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.whatsonsalon.appuntamenti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        long tableNativePtr = table.getNativePtr();
        appuntamentiColumnInfo columnInfo = (appuntamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_id();
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
        String realmGet$_partition = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        }
        String realmGet$cliente = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$cliente();
        if (realmGet$cliente != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.clienteColKey, objKey, realmGet$cliente, false);
        }
        java.util.Date realmGet$dataOra = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$dataOra();
        if (realmGet$dataOra != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataOraColKey, objKey, realmGet$dataOra.getTime(), false);
        }
        Number realmGet$ora = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$ora();
        if (realmGet$ora != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.oraColKey, objKey, realmGet$ora.longValue(), false);
        }
        java.util.Date realmGet$oraFine = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$oraFine();
        if (realmGet$oraFine != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.oraFineColKey, objKey, realmGet$oraFine.getTime(), false);
        }

        RealmList<java.lang.String> trattamentiList = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$trattamenti();
        if (trattamentiList != null) {
            OsList trattamentiOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.trattamentiColKey);
            for (java.lang.String trattamentiItem : trattamentiList) {
                if (trattamentiItem == null) {
                    trattamentiOsList.addNull();
                } else {
                    trattamentiOsList.addString(trattamentiItem);
                }
            }
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        long tableNativePtr = table.getNativePtr();
        appuntamentiColumnInfo columnInfo = (appuntamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.appuntamenti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.appuntamenti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_id();
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
            String realmGet$_partition = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            }
            String realmGet$cliente = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$cliente();
            if (realmGet$cliente != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.clienteColKey, objKey, realmGet$cliente, false);
            }
            java.util.Date realmGet$dataOra = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$dataOra();
            if (realmGet$dataOra != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataOraColKey, objKey, realmGet$dataOra.getTime(), false);
            }
            Number realmGet$ora = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$ora();
            if (realmGet$ora != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.oraColKey, objKey, realmGet$ora.longValue(), false);
            }
            java.util.Date realmGet$oraFine = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$oraFine();
            if (realmGet$oraFine != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.oraFineColKey, objKey, realmGet$oraFine.getTime(), false);
            }

            RealmList<java.lang.String> trattamentiList = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$trattamenti();
            if (trattamentiList != null) {
                OsList trattamentiOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.trattamentiColKey);
                for (java.lang.String trattamentiItem : trattamentiList) {
                    if (trattamentiItem == null) {
                        trattamentiOsList.addNull();
                    } else {
                        trattamentiOsList.addString(trattamentiItem);
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.whatsonsalon.appuntamenti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        long tableNativePtr = table.getNativePtr();
        appuntamentiColumnInfo columnInfo = (appuntamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_id();
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
        String realmGet$_partition = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
        }
        String realmGet$cliente = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$cliente();
        if (realmGet$cliente != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.clienteColKey, objKey, realmGet$cliente, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.clienteColKey, objKey, false);
        }
        java.util.Date realmGet$dataOra = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$dataOra();
        if (realmGet$dataOra != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataOraColKey, objKey, realmGet$dataOra.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dataOraColKey, objKey, false);
        }
        Number realmGet$ora = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$ora();
        if (realmGet$ora != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.oraColKey, objKey, realmGet$ora.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.oraColKey, objKey, false);
        }
        java.util.Date realmGet$oraFine = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$oraFine();
        if (realmGet$oraFine != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.oraFineColKey, objKey, realmGet$oraFine.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.oraFineColKey, objKey, false);
        }

        OsList trattamentiOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.trattamentiColKey);
        trattamentiOsList.removeAll();
        RealmList<java.lang.String> trattamentiList = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$trattamenti();
        if (trattamentiList != null) {
            for (java.lang.String trattamentiItem : trattamentiList) {
                if (trattamentiItem == null) {
                    trattamentiOsList.addNull();
                } else {
                    trattamentiOsList.addString(trattamentiItem);
                }
            }
        }

        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        long tableNativePtr = table.getNativePtr();
        appuntamentiColumnInfo columnInfo = (appuntamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.appuntamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.appuntamenti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.appuntamenti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_id();
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
            String realmGet$_partition = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
            }
            String realmGet$cliente = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$cliente();
            if (realmGet$cliente != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.clienteColKey, objKey, realmGet$cliente, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.clienteColKey, objKey, false);
            }
            java.util.Date realmGet$dataOra = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$dataOra();
            if (realmGet$dataOra != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.dataOraColKey, objKey, realmGet$dataOra.getTime(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dataOraColKey, objKey, false);
            }
            Number realmGet$ora = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$ora();
            if (realmGet$ora != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.oraColKey, objKey, realmGet$ora.longValue(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.oraColKey, objKey, false);
            }
            java.util.Date realmGet$oraFine = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$oraFine();
            if (realmGet$oraFine != null) {
                Table.nativeSetTimestamp(tableNativePtr, columnInfo.oraFineColKey, objKey, realmGet$oraFine.getTime(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.oraFineColKey, objKey, false);
            }

            OsList trattamentiOsList = new OsList(table.getUncheckedRow(objKey), columnInfo.trattamentiColKey);
            trattamentiOsList.removeAll();
            RealmList<java.lang.String> trattamentiList = ((com_example_whatsonsalon_appuntamentiRealmProxyInterface) object).realmGet$trattamenti();
            if (trattamentiList != null) {
                for (java.lang.String trattamentiItem : trattamentiList) {
                    if (trattamentiItem == null) {
                        trattamentiOsList.addNull();
                    } else {
                        trattamentiOsList.addString(trattamentiItem);
                    }
                }
            }

        }
    }

    public static com.example.whatsonsalon.appuntamenti createDetachedCopy(com.example.whatsonsalon.appuntamenti realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.whatsonsalon.appuntamenti unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.whatsonsalon.appuntamenti();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.whatsonsalon.appuntamenti) cachedObject.object;
            }
            unmanagedObject = (com.example.whatsonsalon.appuntamenti) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_whatsonsalon_appuntamentiRealmProxyInterface unmanagedCopy = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) unmanagedObject;
        com_example_whatsonsalon_appuntamentiRealmProxyInterface realmSource = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$_id(realmSource.realmGet$_id());
        unmanagedCopy.realmSet$_partition(realmSource.realmGet$_partition());
        unmanagedCopy.realmSet$cliente(realmSource.realmGet$cliente());
        unmanagedCopy.realmSet$dataOra(realmSource.realmGet$dataOra());
        unmanagedCopy.realmSet$ora(realmSource.realmGet$ora());
        unmanagedCopy.realmSet$oraFine(realmSource.realmGet$oraFine());

        unmanagedCopy.realmSet$trattamenti(new RealmList<java.lang.String>());
        unmanagedCopy.realmGet$trattamenti().addAll(realmSource.realmGet$trattamenti());

        return unmanagedObject;
    }

    static com.example.whatsonsalon.appuntamenti update(Realm realm, appuntamentiColumnInfo columnInfo, com.example.whatsonsalon.appuntamenti realmObject, com.example.whatsonsalon.appuntamenti newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_whatsonsalon_appuntamentiRealmProxyInterface realmObjectTarget = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) realmObject;
        com_example_whatsonsalon_appuntamentiRealmProxyInterface realmObjectSource = (com_example_whatsonsalon_appuntamentiRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.whatsonsalon.appuntamenti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addObjectId(columnInfo._idColKey, realmObjectSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, realmObjectSource.realmGet$_partition());
        builder.addString(columnInfo.clienteColKey, realmObjectSource.realmGet$cliente());
        builder.addDate(columnInfo.dataOraColKey, realmObjectSource.realmGet$dataOra());
        builder.addInteger(columnInfo.oraColKey, realmObjectSource.realmGet$ora());
        builder.addDate(columnInfo.oraFineColKey, realmObjectSource.realmGet$oraFine());
        builder.addStringList(columnInfo.trattamentiColKey, realmObjectSource.realmGet$trattamenti());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("appuntamenti = proxy[");
        stringBuilder.append("{_id:");
        stringBuilder.append(realmGet$_id() != null ? realmGet$_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_partition:");
        stringBuilder.append(realmGet$_partition());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cliente:");
        stringBuilder.append(realmGet$cliente() != null ? realmGet$cliente() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dataOra:");
        stringBuilder.append(realmGet$dataOra() != null ? realmGet$dataOra() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ora:");
        stringBuilder.append(realmGet$ora() != null ? realmGet$ora() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{oraFine:");
        stringBuilder.append(realmGet$oraFine() != null ? realmGet$oraFine() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{trattamenti:");
        stringBuilder.append("RealmList<String>[").append(realmGet$trattamenti().size()).append("]");
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
        com_example_whatsonsalon_appuntamentiRealmProxy aappuntamenti = (com_example_whatsonsalon_appuntamentiRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aappuntamenti.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aappuntamenti.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aappuntamenti.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
