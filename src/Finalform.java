/**
 * Created by 1021288 on 6/22/2017.
 */

package myprojectbg;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Finalform extends javax.swing.JFrame {

    Connection con;
    Statement st;

    int cn=1002;
    int wt[]=new int[cn];
    int idc[]=new int[cn];
    int arr[]=new int[cn];
    String tn[]=new String[cn];
    //String ti[]=new String[cn];
    String td[]=new String[cn];
    String er[]=new String[cn];
    String rc[]=new String[cn];
    String fh[]=new String[cn];
    int ctr=0;
    String tablename;
    public Finalform() {

    }

    public Finalform(String str,int c,int cn1)
    {
        tablename=str;
        ctr=c-1;
        cn=cn1;

        try
        {
            initComponents();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:testdsn1");
            st = con.createStatement();


            Alter_Table();
            idc=getData();
            storedata(idc);
            Delete_Table();

            String ii=Integer.toString(ctr);
            String query="insert into selectedtest"+ii+" values(?,?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(query);

            for(int j=0;j<idc.length;j++)
            {
                if(arr[j]!=0)
                {
                    pst.setInt(1,arr[j]);
                    pst.setString(2,fh[j]);
                    pst.setString(3,tn[j]);
                    pst.setString(4,td[j]);
                    pst.setString(5,er[j]);
                    pst.setString(6,rc[j]);

                    pst.executeUpdate();

                }

                int i=1;

            }

            int flg=0;

            int a[]=new int[fh.length];
            for(int i=0;i<fh.length;i++)
            {
                flg=0;
                String s=fh[i];
                String s1=tn[i];
                int j=0;
                int k=0;

                while(j<i)
                {
                    if(fh[j].equals(s) && tn[j].equals(s1))
                    {
                        a[k]=arr[j];
                        System.out.println("fh[j]"+fh[j]);
                        System.out.println("tn[j]"+tn[j]);
                        System.out.println("a[k]"+a[k]);
                        k++;
                        flg=1;

                    }
                    if(flg==1)
                        break;
                    j++;
                }
            }

            System.out.println("total deleted"+a.length);
            for(int i=0;i<a.length;i++)
            {
                System.out.println("in delete");
                try
                {

                    String qu="delete * from selectedtest"+ii+" where id=?";
                    PreparedStatement prepStmt = con.prepareStatement(qu);
                    prepStmt.setInt(1,a[i]);
                    int x= prepStmt.executeUpdate();

                }
                catch(Exception e){System.out.println(e.toString());}
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        setTable();
    }

    private int[] getData()
    {
        int c=0;
        int arr1[]=new int[cn];
        try
        {
            String ii=Integer.toString(ctr);
            String query="select * from selectedtest"+ii;

            st=con.createStatement();

            ResultSet rs=st.executeQuery(query);


            while(rs.next())
            {
                int id= rs.getInt(1);
                arr1[c]=id;
                c++;
                //System.out.println("id:" + id);
            }
        }

        catch (Exception e){e.printStackTrace();}
        return arr1;
    }

    private void storedata(int[] idc)
    {
        int c=0;

        try
        {
            String q="select * from "+tablename;
            st=con.createStatement();
            ResultSet rs=st.executeQuery(q);
            while(rs.next())
            {
                int id= rs.getInt(1);
                for(int i=0;i<idc.length;i++)
                {
                    if(id==idc[i])
                    {
                        arr[c]=id;

                        String d1 = rs.getString(2);
                        fh[c]=d1;

                        String d2 = rs.getString(3);
                        tn[c]=d2;

                        String d4 = rs.getString(4);
                        td[c]=d4;

                        String d5 = rs.getString(5);
                        er[c]=d5;

                        String d6 = rs.getString(6);
                        rc[c]=d6;

                        c++;
                        break;
                        //System.out.println("id:" + id);
                    }
                }
            }
        }
        catch(Exception e){System.out.println(e.toString());}
    }
    private void Alter_Table()
    {
        try
        {
            String ii=Integer.toString(ctr);
            String query="alter table selectedtest"+ii+" add fun_header varchar(10),test_no varchar(10),test_desc varchar(50),expected_result varchar(50),req_cri varchar(10)";
            int i=st.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Delete_Table()
    {
        try
        {
            String ii=Integer.toString(ctr);
            String query="delete * from selectedtest"+ii;
            int i=st.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setTable()
    {
        int cans=0;
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        try
        {
            String ii=Integer.toString(ctr);
            String sql = "SELECT * FROM selectedtest"+ii;
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0);

            while (rs.next())
            {
                String d1, d2, d4, d3,d5;
                int i,j;

                i = rs.getInt(1);
                cans++;

                d1 = rs.getString(2);
                d2 = rs.getString(3);
                d3 = rs.getString(4);
                d4 = rs.getString(5);
                d5= rs.getString(6);

                model.addRow(new Object[]{i,d3,d4,d5});
            }

            rs.close();
            //con.close();
        }

        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        int flen=cans;
        String slen=Integer.toString(flen);
        jLabel1.setText("Total Selected Test Cases:"+slen);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GA Tool");
        setIconImage(new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\myprojectbg1\\src\\icon1.jpg").getImage());
        getContentPane().setLayout(null);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "ID", "Testcase_desc", "Expected_result", "Req_criticality"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(255, 153, 0));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 1010, 500);

        jLabel1.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 590, 360, 24);

        jButton1.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(460, 530, 110, 29);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg7.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1200, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(evt.getSource()==jButton1)
        {
            ProjGA d=new ProjGA();
            d.setSize(1000,800);
            d.show();
            this.hide();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finalform().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

