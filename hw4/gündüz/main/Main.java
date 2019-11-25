package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import kuyruk.Index;
import kuyruk.Kuyruk;
import kuyruk.Statik_Kuyruk;

public class Main extends javax.swing.JFrame {

    private final JLabel[][] satrancKareleri = new JLabel[8][8];
    private final boolean[][] gezmeKontrol = new boolean[8][8];
    private final Index[][] Komsu = new Index[8][8];
    private List<Index> yol = new ArrayList<>();
    private final Color white =Color.WHITE;
    private final Color ligth_gray = Color.LIGHT_GRAY;
    private final ImageIcon at = new ImageIcon(getClass().getResource("/resim/at.png"));
    private final Statik_Kuyruk kuyruk = new Statik_Kuyruk(64);
    private final String adimText = "Adım : ";
    private final String satirText = "Satır : ";
    private final String sutunText = "Sütun : ";
    private boolean kontrol = true;
    private int satir, sutun;
    private final int basSatir = 0;
    private final int basSutun = 0; //
    private final int hedefSatir = 0;
    private final int hedefSutun = 1;

    public Main() {
        initComponents();
        baslangic();
    }

    private void baslangic() {
        for (int i = 0; i < satrancKareleri.length; i++) {
            for (int j = 0; j < satrancKareleri[i].length; j++) {
                JLabel kare = new JLabel();
                kare.setOpaque(true);
                kare.setPreferredSize(new Dimension(75, 75));
                if (kontrol) {
                    if (j % 2 == 0) {
                        kare.setBackground(white);
                    } else {
                        kare.setBackground(ligth_gray);
                    }
                } else {
                    if (j % 2 == 0) {
                        kare.setBackground(ligth_gray);
                    } else {
                        kare.setBackground(white);
                    }
                }
                satrancKareleri[i][j] = kare;
                gezmeKontrol[i][j] = false; 
                Komsu[i][j] = null; 
                sol_panel.add(kare);
            }
            kontrol = !kontrol;
        }
        satrancKareleri[basSatir][basSutun].setIcon(at);
        kuyruk.ekle(new Index(basSatir, basSutun));
        gezmeKontrol[basSatir][basSutun] = true;
    }

    private void hedefiBul() { 
        Index kareIndis;
        try {
            while (!kuyruk.bosMu()) {
                kareIndis = kuyruk.sil();
                this.satir = kareIndis.satir;
                this.sutun = kareIndis.sutun;
          
                adimlariEkle(kareIndis);
            }
            hedefYoluBul();
            hedefBasla();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void hedefYoluBul() { 
        yol = new ArrayList<>(); 
        yol.add(new Index(hedefSatir, hedefSutun));
        Index indis = Komsu[hedefSatir][hedefSutun];
        while (indis.satir != basSatir || indis.sutun != basSutun) {
            yol.add(indis);
            indis = Komsu[indis.satir][indis.sutun];
        }
        yol.add(new Index(basSatir, basSutun));
    }

    private void adimlariEkle(Index adim) { 
        adimEkle(new Index(adim.satir + 1, adim.sutun -1));
        adimEkle(new Index(adim.satir - 1, adim.sutun - 1));
        
        adimEkle(new Index(adim.satir + 1, adim.sutun + 1));
        adimEkle(new Index(adim.satir - 1, adim.sutun + 1));
       
        adimEkle(new Index(adim.satir + 1, adim.sutun + 1));
        adimEkle(new Index(adim.satir + 1, adim.sutun - 1));
       
        adimEkle(new Index(adim.satir - 1, adim.sutun + 1));
        adimEkle(new Index(adim.satir - 1, adim.sutun - 1));
        
        

    }

    private void adimEkle(Index indis) {
        if (TasmaKontrol(indis.satir,indis.sutun) && !gezmeKontrol[indis.satir][indis.sutun]) {
            kuyruk.ekle(indis);
            gezmeKontrol[indis.satir][indis.sutun] = true;
            Komsu[indis.satir][indis.sutun] = new Index(this.satir, this.sutun);
        }
    }

    private boolean TasmaKontrol(int satir,int sutun) {
        return satir >= 0 && satir <= 7 && sutun>=0 && sutun<=7;
    }
    private void hedefBasla() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int boyut = yol.size();
                Index öncekiIndis = yol.get(boyut - 1);
                 System.out.println("Satır:" + öncekiIndis.satir + ", Sütun:" + öncekiIndis.sutun); 
                int adim = 0; 
                for (int i = boyut - 2; i >= 0; i--) { 
                    adim++;
                    Index indis = yol.get(i);
                    System.out.println("Satır:" + indis.satir + ", Sütun:" + indis.sutun); 
                    satrancKareleri[öncekiIndis.satir][öncekiIndis.sutun].setIcon(null);
                    satrancKareleri[indis.satir][indis.sutun].setIcon(at); 
                    öncekiIndis = indis;
                    adimLB.setText(adimText + adim);
                    satirLB.setText(satirText + indis.satir);
                    sutunLB.setText(sutunText + indis.sutun);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sol_panel = new javax.swing.JPanel();
        sag_panel = new javax.swing.JPanel();
        baslaBtn = new javax.swing.JButton();
        adimLB = new javax.swing.JLabel();
        sutunLB = new javax.swing.JLabel();
        satirLB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ödev 4");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(845, 630));
        setMinimumSize(new java.awt.Dimension(845, 630));
        setPreferredSize(new java.awt.Dimension(845, 630));
        setResizable(false);
        setSize(new java.awt.Dimension(845, 630));
        getContentPane().setLayout(null);

        sol_panel.setMaximumSize(new java.awt.Dimension(600, 600));
        sol_panel.setMinimumSize(new java.awt.Dimension(600, 600));
        sol_panel.setPreferredSize(new java.awt.Dimension(600, 600));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        sol_panel.setLayout(flowLayout1);
        getContentPane().add(sol_panel);
        sol_panel.setBounds(0, 0, 600, 600);

        sag_panel.setBackground(new java.awt.Color(255, 255, 255));
        sag_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sag_panel.setMaximumSize(new java.awt.Dimension(240, 830));
        sag_panel.setMinimumSize(new java.awt.Dimension(240, 830));
        sag_panel.setPreferredSize(new java.awt.Dimension(240, 830));

        baslaBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        baslaBtn.setText("Başla");
        baslaBtn.setFocusable(false);
        baslaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                baslaBtnMousePressed(evt);
            }
        });

        adimLB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adimLB.setText("Adım : 0");

        sutunLB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sutunLB.setText("Sütün: 0");

        satirLB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        satirLB.setText("Satır : 7");

        javax.swing.GroupLayout sag_panelLayout = new javax.swing.GroupLayout(sag_panel);
        sag_panel.setLayout(sag_panelLayout);
        sag_panelLayout.setHorizontalGroup(
            sag_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sag_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sag_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sag_panelLayout.createSequentialGroup()
                        .addGroup(sag_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adimLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sutunLB, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(satirLB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sag_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(baslaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        sag_panelLayout.setVerticalGroup(
            sag_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sag_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(adimLB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(satirLB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sutunLB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241)
                .addComponent(baslaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(388, Short.MAX_VALUE))
        );

        getContentPane().add(sag_panel);
        sag_panel.setBounds(600, 0, 240, 630);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void baslaBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baslaBtnMousePressed
        satrancKareleri[hedefSatir][hedefSutun].setIcon(null);
        hedefiBul();
    }//GEN-LAST:event_baslaBtnMousePressed
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adimLB;
    private javax.swing.JButton baslaBtn;
    private javax.swing.JPanel sag_panel;
    private javax.swing.JLabel satirLB;
    private javax.swing.JPanel sol_panel;
    private javax.swing.JLabel sutunLB;
    // End of variables declaration//GEN-END:variables
}
