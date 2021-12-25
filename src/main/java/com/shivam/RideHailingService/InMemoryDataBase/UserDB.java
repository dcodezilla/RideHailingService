package com.shivam.RideHailingService.InMemoryDataBase;

import com.shivam.RideHailingService.models.User;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    Map<String, User> users = new HashMap<>();

    public void addUserToTheSystem(@NonNull final User user) {
        if (users.containsKey(user.getId())) {
            System.out.println("User already exists in the system!");
            return;
        }

        users.put(user.getId(), user);
    }

    public User getUser(@NonNull final String userId) {
        if (!users.containsKey(userId)) {
            System.out.println("User not found in the system!");
            return null;
        }
        return users.get(userId);
    }
}
