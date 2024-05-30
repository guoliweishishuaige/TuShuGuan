package UI视图;

import dao.RegisterDao;
import madel.Register;
import util.DbUtil;
import util.StringUtil;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;

public class Controller_loading extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;

    private DbUtil dbutil=new DbUtil();
    private RegisterDao registerDao=new RegisterDao();

    public Controller_loading() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
                
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

    /**
     * 登陆时间处理器
     * @param e
     */
    private void loginActionPerformed(ActionEvent e) {
        String username = textField1.getText();
        String password = new String(this.passwordField1.getPassword());
        if(StringUtil.isEmpty(username)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码不能为空");
        }
        Register res=new Register(username,password);
        Connection con=null;
        try {
            con= dbutil.getCon();
            Register currentRegister=registerDao.login(con,res);
            if(currentRegister!=null){
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
        Controller_loading dialog = new Controller_loading();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
