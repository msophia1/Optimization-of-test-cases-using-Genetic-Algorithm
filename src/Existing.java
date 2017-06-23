/**
 * Created by 1021288 on 6/22/2017.
 */

package myprojectbg;

import java.io.File;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
//import java.awt.event.*;
public class Existing extends javax.swing.JFrame {
    //Boolean flg=false;
    String[] choice1;
    Connection con;
    public Existing() {}

    public Existing(int ch)
    {
        // flg=f;

        choice1 = new String[50];

        try
        {
            initComponents();
            if(ch==0)
            {
                jButton3.setEnabled(true);
                jButton1.setEnabled(false);

            }
            if(ch==1)
            {
                jButton1.setEnabled(true);
                jButton3.setEnabled(false);
            }
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:testdsn1");
            Statement st=con.createStatement();
            String sql="SELECT * FROM combotable";
            ResultSet rs=st.executeQuery(sql);
            int i=0;
            while(rs.next())
            {
                String d;
                d=rs.getString(2);
                choice1[i]=d;
                System.out.println(choice1[i]);
                i++;

            }
            jComboBox1.setModel(new DefaultComboBoxModel(choice1));
        }catch(Exception e){System.out.println(e.toString());}




    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GA Tool");
        setIconImage(new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\myprojectbg1\\src\\icon1.jpg").getImage());
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel1.setText("CHOOSE TABLE TO LOAD");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 290, 330, 30);

        jComboBox1.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "testcases", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(570, 280, 150, 40);

        jButton1.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jButton1.setText("SELECT TO OPTIMIZE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(210, 360, 310, 40);

        jButton2.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(210, 480, 310, 40);

        jButton3.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jButton3.setText("SELECT TO EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(210, 420, 310, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg7.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1200, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(evt.getSource()==jButton1)
        {
            String tablename = (String)jComboBox1.getSelectedItem();
            Myproject d=new Myproject(tablename);
            d.setSize(1000,800);
            d.show();
            this.hide();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(evt.getSource()==jButton2)
        {

            ProjGA mp1 = new ProjGA();
            //System.out.println(tablename);
            mp1.setSize(1000,800);
            mp1.show();
            this.hide();


        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(evt.getSource()==jButton3)
        {
            String tablename = (String)jComboBox1.getSelectedItem();
            Edittable mp1 = new Edittable(tablename);
            //System.out.println(tablename);
            mp1.setSize(1000,800);
            mp1.show();
            this.hide();


        }
    }//GEN-LAST:event_jButton3ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Existing().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}







