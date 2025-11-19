package kr.java.dao.model.dao;

import kr.java.dao.model.entity.UserAccount;
import kr.java.dao.util.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserAccountDAOImpl implements UserAccountDAO{
    private final Connection connection = DBUtil.getConnection();

    @Override
    public UserAccount selectByUsername(String username) {
//        String sql = "SELECT * FROM USER_ACCOUNT WHERE username = ?";
        String sql = "SELECT account_id, username, password, created_at FROM USER_ACCOUNT WHERE username = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery(); // username이 unique하기 때문에 결과는 하나임
            if(rs.next()) {
                UserAccount account = new UserAccount();
                account.setAccountId(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setCreatedAt(rs.getString("created_at"));
                return account;
            }
            throw new RuntimeException("해당 username에 사용자가 존재하지 않습니다.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUserAccount(UserAccount userAccount) {
        String sql = "INSERT INTO USER_ACCOUNT (username, password) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userAccount.getUsername());
            pstmt.setString(2, userAccount.getPassword());
            pstmt.executeUpdate(); // 문제가 생기면 알아서 Exception 발생

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
