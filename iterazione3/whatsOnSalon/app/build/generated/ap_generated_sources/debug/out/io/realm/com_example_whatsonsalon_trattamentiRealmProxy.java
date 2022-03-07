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
public class com_example_whatsonsalon_trattamentiRealmProxy extends com.example.whatsonsalon.trattamenti
    implements RealmObjectProxy, com_example_whatsonsalon_trattamentiRealmProxyInterface {

    static final class trattamentiColumnInfo extends ColumnInfo {
        long _idColKey;
        long _partitionColKey;
        long durataColKey;
        long nomeColKey;

        trattamentiColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("trattamenti");
            this._idColKey = addColumnDetails("_id", "_id", objectSchemaInfo);
            this._partitionColKey = addColumnDetails("_partition", "_partition", objectSchemaInfo);
            this.durataColKey = addColumnDetails("durata", "durata", objectSchemaInfo);
            this.nomeColKey = addColumnDetails("nome", "nome", objectSchemaInfo);
        }

        trattamentiColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new trattamentiColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final trattamentiColumnInfo src = (trattamentiColumnInfo) rawSrc;
            final trattamentiColumnInfo dst = (trattamentiColumnInfo) rawDst;
            dst._idColKey = src._idColKey;
            dst._partitionColKey = src._partitionColKey;
            dst.durataColKey = src.durataColKey;
            dst.nomeColKey = src.nomeColKey;
        }
    }

    private static final String NO_ALIAS = "";
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private trattamentiColumnInfo columnInfo;
    private ProxyState<com.example.whatsonsalon.trattamenti> proxyState;

    com_example_whatsonsalon_trattamentiRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (trattamentiColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.whatsonsalon.trattamenti>(this);
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
    public String realmGet$durata() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.durataColKey);
    }

    @Override
    public void realmSet$durata(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.durataColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.durataColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.durataColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.durataColKey, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(NO_ALIAS, "trattamenti", false, 4, 0);
        builder.addPersistedProperty(NO_ALIAS, "_id", RealmFieldType.OBJECT_ID, Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "_partition", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "durata", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty(NO_ALIAS, "nome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static trattamentiColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new trattamentiColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "trattamenti";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "trattamenti";
    }

    @SuppressWarnings("cast")
    public static com.example.whatsonsalon.trattamenti createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.whatsonsalon.trattamenti obj = null;
        if (update) {
            Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
            trattamentiColumnInfo columnInfo = (trattamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class);
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
                    objectContext.set(realm, table.getUncheckedRow(objKey), realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_example_whatsonsalon_trattamentiRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("_id")) {
                if (json.isNull("_id")) {
                    obj = (io.realm.com_example_whatsonsalon_trattamentiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.trattamenti.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_example_whatsonsalon_trattamentiRealmProxy) realm.createObjectInternal(com.example.whatsonsalon.trattamenti.class, json.get("_id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field '_id'.");
            }
        }

        final com_example_whatsonsalon_trattamentiRealmProxyInterface objProxy = (com_example_whatsonsalon_trattamentiRealmProxyInterface) obj;
        if (json.has("_partition")) {
            if (json.isNull("_partition")) {
                objProxy.realmSet$_partition(null);
            } else {
                objProxy.realmSet$_partition((String) json.getString("_partition"));
            }
        }
        if (json.has("durata")) {
            if (json.isNull("durata")) {
                objProxy.realmSet$durata(null);
            } else {
                objProxy.realmSet$durata((String) json.getString("durata"));
            }
        }
        if (json.has("nome")) {
            if (json.isNull("nome")) {
                objProxy.realmSet$nome(null);
            } else {
                objProxy.realmSet$nome((String) json.getString("nome"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.whatsonsalon.trattamenti createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.example.whatsonsalon.trattamenti obj = new com.example.whatsonsalon.trattamenti();
        final com_example_whatsonsalon_trattamentiRealmProxyInterface objProxy = (com_example_whatsonsalon_trattamentiRealmProxyInterface) obj;
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
            } else if (name.equals("durata")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$durata((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$durata(null);
                }
            } else if (name.equals("nome")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nome((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nome(null);
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

    static com_example_whatsonsalon_trattamentiRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class), false, Collections.<String>emptyList());
        io.realm.com_example_whatsonsalon_trattamentiRealmProxy obj = new io.realm.com_example_whatsonsalon_trattamentiRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.whatsonsalon.trattamenti copyOrUpdate(Realm realm, trattamentiColumnInfo columnInfo, com.example.whatsonsalon.trattamenti object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.whatsonsalon.trattamenti) cachedRealmObject;
        }

        com.example.whatsonsalon.trattamenti realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
            long pkColumnKey = columnInfo._idColKey;
            org.bson.types.ObjectId value = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_id();
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
                    realmObject = new io.realm.com_example_whatsonsalon_trattamentiRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.whatsonsalon.trattamenti copy(Realm realm, trattamentiColumnInfo columnInfo, com.example.whatsonsalon.trattamenti newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.whatsonsalon.trattamenti) cachedRealmObject;
        }

        com_example_whatsonsalon_trattamentiRealmProxyInterface unmanagedSource = (com_example_whatsonsalon_trattamentiRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addObjectId(columnInfo._idColKey, unmanagedSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, unmanagedSource.realmGet$_partition());
        builder.addString(columnInfo.durataColKey, unmanagedSource.realmGet$durata());
        builder.addString(columnInfo.nomeColKey, unmanagedSource.realmGet$nome());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_whatsonsalon_trattamentiRealmProxy managedCopy = newProxyInstance(realm, row);
        cache.put(newObject, managedCopy);

        return managedCopy;
    }

    public static long insert(Realm realm, com.example.whatsonsalon.trattamenti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        long tableNativePtr = table.getNativePtr();
        trattamentiColumnInfo columnInfo = (trattamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_id();
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
        String realmGet$_partition = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        }
        String realmGet$durata = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$durata();
        if (realmGet$durata != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.durataColKey, objKey, realmGet$durata, false);
        }
        String realmGet$nome = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
        }
        return objKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        long tableNativePtr = table.getNativePtr();
        trattamentiColumnInfo columnInfo = (trattamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.trattamenti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.trattamenti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_id();
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
            String realmGet$_partition = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            }
            String realmGet$durata = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$durata();
            if (realmGet$durata != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.durataColKey, objKey, realmGet$durata, false);
            }
            String realmGet$nome = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.whatsonsalon.trattamenti object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        long tableNativePtr = table.getNativePtr();
        trattamentiColumnInfo columnInfo = (trattamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_id();
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
        String realmGet$_partition = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_partition();
        if (realmGet$_partition != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
        }
        String realmGet$durata = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$durata();
        if (realmGet$durata != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.durataColKey, objKey, realmGet$durata, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.durataColKey, objKey, false);
        }
        String realmGet$nome = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nomeColKey, objKey, false);
        }
        return objKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        long tableNativePtr = table.getNativePtr();
        trattamentiColumnInfo columnInfo = (trattamentiColumnInfo) realm.getSchema().getColumnInfo(com.example.whatsonsalon.trattamenti.class);
        long pkColumnKey = columnInfo._idColKey;
        com.example.whatsonsalon.trattamenti object = null;
        while (objects.hasNext()) {
            object = (com.example.whatsonsalon.trattamenti) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            org.bson.types.ObjectId primaryKeyValue = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_id();
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
            String realmGet$_partition = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$_partition();
            if (realmGet$_partition != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._partitionColKey, objKey, realmGet$_partition, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo._partitionColKey, objKey, false);
            }
            String realmGet$durata = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$durata();
            if (realmGet$durata != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.durataColKey, objKey, realmGet$durata, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.durataColKey, objKey, false);
            }
            String realmGet$nome = ((com_example_whatsonsalon_trattamentiRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeColKey, objKey, realmGet$nome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nomeColKey, objKey, false);
            }
        }
    }

    public static com.example.whatsonsalon.trattamenti createDetachedCopy(com.example.whatsonsalon.trattamenti realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.whatsonsalon.trattamenti unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.whatsonsalon.trattamenti();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.whatsonsalon.trattamenti) cachedObject.object;
            }
            unmanagedObject = (com.example.whatsonsalon.trattamenti) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_whatsonsalon_trattamentiRealmProxyInterface unmanagedCopy = (com_example_whatsonsalon_trattamentiRealmProxyInterface) unmanagedObject;
        com_example_whatsonsalon_trattamentiRealmProxyInterface realmSource = (com_example_whatsonsalon_trattamentiRealmProxyInterface) realmObject;
        Realm objectRealm = (Realm) ((RealmObjectProxy) realmObject).realmGet$proxyState().getRealm$realm();
        unmanagedCopy.realmSet$_id(realmSource.realmGet$_id());
        unmanagedCopy.realmSet$_partition(realmSource.realmGet$_partition());
        unmanagedCopy.realmSet$durata(realmSource.realmGet$durata());
        unmanagedCopy.realmSet$nome(realmSource.realmGet$nome());

        return unmanagedObject;
    }

    static com.example.whatsonsalon.trattamenti update(Realm realm, trattamentiColumnInfo columnInfo, com.example.whatsonsalon.trattamenti realmObject, com.example.whatsonsalon.trattamenti newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_example_whatsonsalon_trattamentiRealmProxyInterface realmObjectTarget = (com_example_whatsonsalon_trattamentiRealmProxyInterface) realmObject;
        com_example_whatsonsalon_trattamentiRealmProxyInterface realmObjectSource = (com_example_whatsonsalon_trattamentiRealmProxyInterface) newObject;
        Table table = realm.getTable(com.example.whatsonsalon.trattamenti.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);
        builder.addObjectId(columnInfo._idColKey, realmObjectSource.realmGet$_id());
        builder.addString(columnInfo._partitionColKey, realmObjectSource.realmGet$_partition());
        builder.addString(columnInfo.durataColKey, realmObjectSource.realmGet$durata());
        builder.addString(columnInfo.nomeColKey, realmObjectSource.realmGet$nome());

        builder.updateExistingTopLevelObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("trattamenti = proxy[");
        stringBuilder.append("{_id:");
        stringBuilder.append(realmGet$_id() != null ? realmGet$_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_partition:");
        stringBuilder.append(realmGet$_partition());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{durata:");
        stringBuilder.append(realmGet$durata() != null ? realmGet$durata() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nome:");
        stringBuilder.append(realmGet$nome() != null ? realmGet$nome() : "null");
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
        com_example_whatsonsalon_trattamentiRealmProxy atrattamenti = (com_example_whatsonsalon_trattamentiRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = atrattamenti.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = atrattamenti.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != atrattamenti.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
