/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author midni
 */
public class AccountDBContext extends DBContext {

    public void addAccount(Account a) {
        String sql = "INSERT INTO dbo.Account\n"
                + "  (\n"
                + "      username,\n"
                + "      password,\n"
                + "      displayname,\n"
                + "      roleID\n"
                + "  )\n"
                + "  VALUES\n"
                + "  (   ?, \n"
                + "      ?, \n"
                + "      ?, \n"
                + "      ? \n"
                + "      )\n";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUser());
            stm.setString(2, a.getPass());
            stm.setString(3, a.getDisplayName());
            stm.setInt(4, a.getRole().getRoleID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteAccount(String user) {
        String sql = "DELETE FROM dbo.Account WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editAccount(String pass, String displayName, int roleID, String user) {
        String sql = "UPDATE dbo.Account SET password = ?, displayname = ?, roleID = ? WHERE username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, displayName);
            stm.setInt(3, roleID);
            stm.setString(4, user);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Account> getListAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT [username], [password], [displayname], [roleID] FROM [Attendance_Management].[dbo].[Account]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setDisplayName(rs.getString("displayname"));
                a.setUser(rs.getString("username"));
                a.setPass(rs.getString("password"));
                Role r = new Role();
                r.setRoleID(rs.getInt("roleID"));
                if (rs.getInt("roleID") == 1) {
                    r.setRoleName("admin");
                } else if (rs.getInt("roleID") == 2) {
                    r.setRoleName("teacher");
                } else {
                    r.setRoleName("student");
                }
                a.setRole(r);
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

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
