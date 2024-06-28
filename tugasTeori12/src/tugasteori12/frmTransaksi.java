/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugasteori12;
/**
 *
 * @author asus
 */
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class frmTransaksi extends javax.swing.JFrame {
    Connection Con; 
    ResultSet RsBrg; 
    ResultSet RsKons; 
    Statement stm; 
    double total=0; 
    String tanggal; 
    Boolean edit=false; 
    DefaultTableModel tableModel = new DefaultTableModel( 
    new Object [][] {}, 
    new String [] { 
    "Kd Barang", "Nama Barang","Harga Barang","Jumlah","Total" 
    });
       /**
     * Creates new form frmTransaksi
     */
    public frmTransaksi() { 
        initComponents(); 
        open_db(); 
        inisialisasi_tabel(); 
        aktif(false); 
        setTombol(true); 
        txtTgl.setEditor(new JSpinner.DateEditor(txtTgl, 
       "yyyy/MM/dd")); }
    
    //method hitung penjualan 
    private void hitung_jual() 
     { 
     double xtot,xhrg; 
     int xjml; 
     xhrg=Double.parseDouble(txtHarga.getText()); 
     xjml=Integer.parseInt(txtJml.getText()); 
     xtot=xhrg*xjml; 
     String xtotal=Double.toString(xtot); 
     txtTot.setText(xtotal); 
     total=total+xtot; 
     txtTotal.setText(Double.toString(total)); 
     } 
    //method buka database 
    private void open_db() 
    { 
     try{ 
     KoneksiMysql kon = new KoneksiMysql 
    ("localhost","root","root","penjualan"); 
     Con = kon.getConnection(); 
     //System.out.println("Berhasil "); 
     }catch (Exception e) { 
     System.out.println("Error : "+e); 
     } 
    } 
    //methohd baca data konsumen 
     private void baca_konsumen() 
     { 
     try{ 
     stm=Con.createStatement(); 
     ResultSet rs=stm.executeQuery("select kd_kons,nm_kons from 
    konsumen"); 
     rs.beforeFirst(); 
     while(rs.next()) 
     { 
     cmbKd_Kons.addItem(rs.getString(1).trim()); 
     } 
     rs.close(); 
     } 
     catch(SQLException e) 
     { 
     System.out.println("Error : "+e); 
     } 
     } 
    //method baca data barang 
     private void baca_barang() 
     { 
     try{ 
     stm=Con.createStatement(); 
     ResultSet rs=stm.executeQuery("select * from barang"); 
     rs.beforeFirst(); 
     while(rs.next()) 
    { 
        cmbKd_Brg.addItem(rs.getString(1).trim()); 
        } 
        rs.close(); 
        } 
        catch(SQLException e) 
        { 
        System.out.println("Error : "+e); 
        } 
        } 
       //method baca barang setelah combo barang di klik 
        private void detail_barang(String xkode) 
        { 
        try{ 
        stm=Con.createStatement(); 
        ResultSet rs=stm.executeQuery("select * from barang where 
       kd_brg='"+xkode+"'"); 
        rs.beforeFirst(); 
        while(rs.next()) 
        { 
        txtNm_Brg.setText(rs.getString(2).trim()); 
        txtHarga.setText(Double.toString((Double)rs.getDouble(4))); 
        } 
        rs.close(); 
        } 
        catch(SQLException e) 
        { 
        System.out.println("Error : "+e); 
        } 
        } 
       //method baca konsumen setelah combo konsumen di klik 
        private void detail_konsumen(String xkode) 
        { 
        try{ 
        stm=Con.createStatement(); 
        ResultSet rs=stm.executeQuery("select * from konsumen where 
       kd_kons='"+xkode+"'"); 
        rs.beforeFirst(); 
        while(rs.next()) 
        { 
        txtNama.setText(rs.getString(2).trim()); 
        } 
        rs.close(); 
        } 
        catch(SQLException e) 
        { 
        System.out.println("Error : "+e); 
        } 
        } 
       //method set model tabel 
       public void inisialisasi_tabel() 
       { 
        tblJual.setModel(tableModel);
       }
       
       //method pengkosongan isian 
    private void kosong() 
    { 
     txtNoJual.setText(""); 
     txtNama.setText(""); 
     txtHarga.setText(""); 
     txtTotal.setText(""); 
    } 
    //method kosongkan detail jual 
    private void kosong_detail() 
    { 
     txtNm_Brg.setText(""); 
     txtHarga.setText(""); 
     txtJml.setText(""); 
     txtTot.setText(""); 
    } 
    //method aktif on/off isian 
    private void aktif(boolean x) 
    { 
     cmbKd_Kons.setEnabled(x); 
     cmbKd_Brg.setEnabled(x); 
     txtTgl.setEnabled(x); 
     txtJml.setEditable(x); 
    } 
    //method set tombol on/off 
    private void setTombol(boolean t) 
    { 
     cmdTambah.setEnabled(t); 
     cmdSimpan.setEnabled(!t); 
     cmdBatal.setEnabled(!t); 
     cmdKeluar.setEnabled(t); 
     cmdHapusItem.setEnabled(!t); 
    } 
    //method buat nomor jual otomatis 
    private void nomor_jual() 
    { 
     try{ 
     stm=Con.createStatement(); 
     ResultSet rs=stm.executeQuery("select no_jual from jual"); 
     int brs=0; 
     while(rs.next()) 
     { 
     brs=rs.getRow(); 
     } 
     if(brs==0) 
     txtNoJual.setText("1"); 
     else 
     {int nom=brs+1; 
     txtNoJual.setText(Integer.toString(nom)); 
     } 
     rs.close(); 
     } 
     catch(SQLException e) 
     { 
     System.out.println("Error : "+e); 
     } 
    } 
    
        //method simpan detail jual di tabel 
    private void simpan_ditabel() 
    { 
     try{ 
     String tKode=cmbKd_Brg.getSelectedItem().toString(); 
     String tNama=txtNm_Brg.getText(); 
     double hrg=Double.parseDouble(txtHarga.getText()); 
     int jml=Integer.parseInt(txtJml.getText()); 
     double tot=Double.parseDouble(txtTot.getText()); 
     tableModel.addRow(new Object[]{tKode,tNama,hrg,jml,tot}); 
     inisialisasi_tabel(); 
     } 
     catch(Exception e) 
     { 
     System.out.println("Error : "+e); 
     } 
    } 
    //method simpan transaksi penjualan pada table di MySql 
    private void simpan_transaksi() 
    { 
     try{ 
     String xnojual=txtNoJual.getText(); 
     format_tanggal(); 
     String xkode=cmbKd_Kons.getSelectedItem().toString(); 
     String msql="insert into jual 
    values('"+xnojual+"','"+xkode+"','"+tanggal+"')"; 
     stm.executeUpdate(msql); 
     for(int i=0;i<tblJual.getRowCount();i++) 
     { 
     String xkd=(String)tblJual.getValueAt(i,0); 
     double xhrg=(Double)tblJual.getValueAt(i,2); 
     int xjml=(Integer)tblJual.getValueAt(i,3); 
     String zsql="insert into djual 
    values('"+xnojual+"','"+xkd+"',"+xhrg+","+xjml+")";
     stm.executeUpdate(zsql); 
     } 
     } 
     catch(SQLException e) 
     { 
     System.out.println("Error : "+e); 
     } 
    } 
    //method membuat format tanggal sesuai dengan MySQL
    private void format_tanggal() 
    { 
     String DATE_FORMAT = "yyyy-MM-dd"; 
     java.text.SimpleDateFormat sdf = new 
    java.text.SimpleDateFormat(DATE_FORMAT); 
     Calendar c1 = Calendar.getInstance(); 
     int year=c1.get(Calendar.YEAR); 
     int month=c1.get(Calendar.MONTH)+1; 
     int day=c1.get(Calendar.DAY_OF_MONTH); 
     tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day); 
    } 
    public void itemTerpilih(){ 
        frmSelectBarang fDB = new frmSelectBarang(); 
        fDB.fAB = this; 
        txtId.setText(idBrg); 
        txtNm_Brg.setText(namaBrg); 
        txtHarga.setText(hargaBrg); 
    }




        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        KdBarang = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        kdKonsumen = new javax.swing.JComboBox<>();
        nmKonsumen = new javax.swing.JTextField();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        cmdCetak = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        uangBayar = new javax.swing.JTextField();
        btnPilih = new javax.swing.JButton();
        txtSearch1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        uangKembali = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No. Jual");

        jLabel2.setText("Tanggal Jual");

        label1.setName(""); // NOI18N
        label1.setText("Kode Konsumen");

        label2.setText("Nama Konsumen");

        kdKonsumen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        delete.setText("Hapus Barang");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Barang", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Total");

        tambah.setText("Tambah");

        simpan.setText("Simpan");

        cancel.setText("Batal");

        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        exit.setText("Keluar");

        jLabel4.setText("Bayar");

        btnPilih.setText("Pilih Barang");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel5.setText("Kembali");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPilih)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(44, 44, 44)
                                .addComponent(KdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nmKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kdKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tambah)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(simpan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmdCetak)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(exit))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(31, 31, 31)
                                    .addComponent(uangBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(31, 31, 31)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(31, 31, 31)
                                    .addComponent(uangKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(KdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kdKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nmKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(btnPilih)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uangBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uangKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah)
                    .addComponent(simpan)
                    .addComponent(cancel)
                    .addComponent(cmdCetak)
                    .addComponent(exit))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
        String ctk="Nota Penjualan\nNo :"+txtNoJual.getText()+"\nTanggal : "+tanggal; 
         ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------"; 
         ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal"; 
         ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------"; 

         for(int i=0;i<tblJual.getRowCount();i++) 
         { 
         String xkd=(String)tblJual.getValueAt(i,0); 
         String xnama=(String)tblJual.getValueAt(i,1); 
         double xhrg=(Double)tblJual.getValueAt(i,2); 
         int xjml=(Integer)tblJual.getValueAt(i,3); 
         double xtot=(Double)tblJual.getValueAt(i,4); 
         ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot; 
         } 
         ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------"; 
         text.setText(ctk); 
         String headerField=""; 
         String footerField=""; 
         MessageFormat header = createFormat(headerField); 
         MessageFormat footer = createFormat(footerField); 
         boolean interactive = true;//interactiveCheck.isSelected(); 
         boolean background = true;//backgroundCheck.isSelected(); 
         PrintingTask task = new PrintingTask(header, footer, 
        interactive); 
         if (background) { 
         task.execute(); 
         } else { 
         task.run(); 
         } 
         } 

    }//GEN-LAST:event_cmdCetakActionPerformed

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
        frmSelectBarang fDB = new frmSelectBarang(); 
         fDB.fAB = this; 
         fDB.setVisible(true); 
         fDB.setResizable(false);
    }//GEN-LAST:event_btnPilihActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTransaksi().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField KdBarang;
    private javax.swing.JButton btnPilih;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton delete;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> kdKonsumen;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JTextField nmKonsumen;
    private javax.swing.JButton simpan;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField uangBayar;
    private javax.swing.JTextField uangKembali;
    // End of variables declaration//GEN-END:variables
}
