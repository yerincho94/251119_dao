package kr.java.dao.service;

import kr.java.dao.model.dao.UserAccountDAO;
import kr.java.dao.model.dto.UserAccountDTO;
import kr.java.dao.model.entity.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    private final UserAccountDAO userAccountDAO;

    public UserAccountService(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    public void signup(UserAccountDTO dto) {
        UserAccount account = new UserAccount();
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassword());

        userAccountDAO.insertUserAccount(account);
    }

    public boolean login(UserAccountDTO dto) {
        // 존재하는지 여부를 이걸로 검색
        UserAccount account = userAccountDAO.selectByUsername(dto.getUsername());
        // Password가 같은지 확인
        return account.getPassword().equals(dto.getPassword());
    }
}
