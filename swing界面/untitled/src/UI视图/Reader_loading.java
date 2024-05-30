package UI视图;


import madel.User;
import util.StringUtil;
import util.DbUtil;
import dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class Reader_loading extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton DLButton;
    private JButton GLZDLButton;
    private JPasswordField passwordField2;
    private JTextField textField1;

    private DbUtil dbutil=new DbUtil();
    private UserDao userDao=new UserDao();

    public Reader_loading() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        int windowWidth = this.getWidth(); //获得窗口宽
        int windowHeight = this.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        int screenWidth = kit.getScreenSize().width; //获取屏幕的宽
        int screenHeight = kit.getScreenSize().height; //获取屏幕的高
        this.setLocation(screenWidth/4 - windowWidth/4, screenHeight/4 - windowHeight/4);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        DLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void loginActionPerformed(ActionEvent e) {
        String username = textField1.getText();
        String password = new String(this.passwordField2.getPassword());
        if(StringUtil.isEmpty(username)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码不能为空");
            return;
        }
        User user = new User(username,password);
        Connection con=null;
        try {
            con= dbutil.getCon();
            User currentUser=userDao.login(con,user);
            if(currentUser!=null){
                JOptionPane.showMessageDialog(null,"登陆成功");
            }else{
                JOptionPane.showMessageDialog(null,"用户名或密码错误");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onOK() {
        // 在此处添加您的代码
        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    public static void main(String[] args) {
        Reader_loading dialog = new Reader_loading();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
