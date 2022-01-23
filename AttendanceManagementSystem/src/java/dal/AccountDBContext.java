/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author midni
 */
public class AccountDBContext extends DBContext {
    
    public Account getAccount(String user, String pass) {
        try {
            String sql = "SELECT A.[username], A.[password], A.[displayname], A.[roleID], R.RoleName "
                    + "FROM [Attendance_Management].[dbo].[Account] A INNER JOIN "
                    + "[Attendance_Management].[dbo].[Role] R ON A.roleID = R.RoleID "
                    + "WHERE [username] = ? AND [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                Role role = new Role();
                acc.setUser(rs.getString("username"));
                acc.setPass(rs.getString("password"));
                acc.setDisplayName(rs.getString("displayname"));
                role.setRoleID(rs.getInt("roleID"));
                role.setRoleName(rs.getString("RoleName"));
                acc.setRole(role);
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
