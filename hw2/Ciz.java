import java.awt.Color;
import javax.swing.JApplet;
import java.awt.Graphics;

class ciz extends JApplet{
    Liste_Islemleri bagliListe;
    int Peryot;
    int Aci;

    public ciz(Liste_Islemleri bagliListe, int Peryot, int Aci) {
        this.bagliListe = bagliListe;
        this.Peryot = Peryot;
        this.Aci = Aci;
    }
    
    @Override
    public void init() {
        setSize(500, 500);
    }
    
    @Override
    public void ciz(Graphics g) {
        
        int baslangic_aci = bagliListe.bas.aci;
        if(baslangic_aci >= 0){
            bagliListe.aciEkle(0);
        }
        else if(baslangic_aci < 0){
            bagliListe.aciEkle(-1);
        }
        int peryot_sayisi = Peryot;
        bagliListe.aciEkle(baslangic_aci);
        Dugum d = bagliListe.bas;
        double yDegeri1;
        double yDegeri2;
        int grafikBaslangicY = 300;
        int dalgaBoyu = 100;
        int peryot_uzunluk = 360;
        int BaslangicX = 400;
        g.setColor(Color.blue);
        double x = baslangic_aci;

        if (baslangic_aci >= 0) {
            while (x <= peryot_uzunluk * peryot_sayisi) {
                yDegeri1 = d.sin_deger;
                yDegeri2 = d.ileri.sin_deger; 
                g.drawLine((int) x + BaslangicX, grafikBaslangicY - (int) (dalgaBoyu * yDegeri1), (int) x + BaslangicX, grafikBaslangicY - (int) (dalgaBoyu * yDegeri2));
                d = d.ileri;
                x += 1;
            }

        } else if (baslangic_aci < 0) {
            while (x > (-1) * peryot_uzunluk * peryot_sayisi) {
                yDegeri1 = d.sin_deger;
                yDegeri2 = d.ileri.sin_deger;
                g.drawLine((int) x + BaslangicX, grafikBaslangicY - (int) (dalgaBoyu * yDegeri1), (int) x + BaslangicX, grafikBaslangicY - (int) (dalgaBoyu * yDegeri2));
                d = d.ileri;
                x -= 1;
            } 
        }

        g.setColor(Color.BLACK);
        g.drawLine(0, 300, 800, 300); 
        g.drawLine(400, 0, 400, 600); 
    }
}
