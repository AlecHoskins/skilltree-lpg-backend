package com.alechoskins.skilltreelpgbackend.database.repository.firebase.base;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractFirestoreRepository<T> {

    //region Properties

    @Autowired
    protected Firestore dbFirestore;
    protected final Class<T> documentClass;
    protected final String collectionName;

    //endregion

    //region Contructor

    public AbstractFirestoreRepository(Class<T> documentClass, String collectionName){
        this.documentClass = documentClass;
        this.collectionName = collectionName;
    }

    //endregion

    protected T documentSnapshotToObject(DocumentSnapshot documentSnapshot) {
        return documentSnapshot.toObject(documentClass);
    }

    //region CRUD

    public T get(String documentId) throws InterruptedException, ExecutionException {
        DocumentReference docRef = dbFirestore.collection(collectionName).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return documentSnapshotToObject(document);
        } else {
            return null;
        }
    }

    public List<T> getAllWhere(String field, Object value) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future =
                dbFirestore.collection(collectionName)
                        .whereEqualTo(field, value)
                        .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<T> results = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            results.add(documentSnapshotToObject(document));
        }
        return results;
    }

    public String save(T obj) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = dbFirestore.collection(collectionName).add(obj);
        return future.get().getId();
    }

    public void update(String documentId, T obj) {
        DocumentReference docRef = dbFirestore.collection(collectionName).document(documentId);
        docRef.set(obj, SetOptions.merge());
    }

    public void delete(String documentId) {
        dbFirestore.collection(collectionName).document(documentId).delete();
    }

    //endregion
}
