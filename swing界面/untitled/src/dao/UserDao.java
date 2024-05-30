package dao;

import madel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户Dao类
 */
public class UserDao {
    /**
     * 登陆验证
     */
    public User login(Connection con,User Us)throws Exception{
        User resultUs = null;
        String sql="select * from user where name=? and password=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,Us.getName());
        pst.setString(2,Us.getPassword());
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            resultUs=new User();
            resultUs.setId(rs.getInt("id"));
            resultUs.setName(rs.getString("name"));
            resultUs.setPassword(rs.getString("password"));
            resultUs.setBorrow(rs.getString("borrow"));
            resultUs.setBorrow_right(rs.getInt("borrow_right"));
        }

        return resultUs;
    }

}
