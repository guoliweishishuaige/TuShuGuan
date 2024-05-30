package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import madel.Register;
/**
 * 管理者dao类
 */
public class RegisterDao {
    /**
     * 登陆验证
     */
    public Register login(Connection con,Register res)throws Exception{
        Register resultRes = null;
        String sql="select * from register where name=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,res.getName());
        pstmt.setString(2,res.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            resultRes = new Register();
            resultRes.setId(rs.getInt("id"));
            resultRes.setName(rs.getString("name"));
            resultRes.setPassword(rs.getString("password"));

        }
        return resultRes;
    }

}
