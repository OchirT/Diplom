package ru.netology.diplomproject.repository;


import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserTokenRepository {
    private Map<String, String> tokenList = new ConcurrentHashMap<>();

    public void saveUserToken(String userName, String token) {
        tokenList.put(userName, token);
    }

    public void deleteUserAndToken(String token) {
        tokenList.remove(token);
    }

    public String getUserNameByToken(String token) {
        return tokenList.get(token);
    }
}

