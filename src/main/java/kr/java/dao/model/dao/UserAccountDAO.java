package kr.java.dao.model.dao;

import kr.java.dao.model.entity.UserAccount;

public interface UserAccountDAO {
    UserAccount selectByUsername(String username);
    void insertUserAccount(UserAccount userAccount);
}
