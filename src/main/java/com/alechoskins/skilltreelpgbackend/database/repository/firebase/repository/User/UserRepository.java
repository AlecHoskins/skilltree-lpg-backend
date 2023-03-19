package com.alechoskins.skilltreelpgbackend.database.repository.firebase.repository.User;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.database.repository.firebase.base.AbstractFirestoreRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User> implements IUserRepository{

    public UserRepository(){
        super(User.class, "Users");
    }

    public User get(String userId) throws InterruptedException, ExecutionException {
        return super.get(userId);
    }

    public List<User> getAllWhere(String field, Object value) throws InterruptedException, ExecutionException {
        return super.getAllWhere(field,value);
    }

    public String save(User user) throws ExecutionException, InterruptedException {
        return super.save(user);
    }

    public void update(String userId, User user) {
        super.update(userId, user);
    }

    public void delete(String userId) {
        dbFirestore.collection(collectionName).document(userId).delete();
    }
}
