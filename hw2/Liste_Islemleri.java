import javax.swing.JFrame;

class Dugum {
    int aci;
    double sin_deger;
    Dugum ileri,geri;

    public Dugum(int aci, double sin_deger) {
        this.aci = aci;
        this.sin_deger = sin_deger;
        this.ileri = null;
        this.geri = null;
    }
}
    
public class Liste_Islemleri {
    Dugum bas;
    Dugum son;
    int elemanSayisi = 0;
    double aci;

    public Liste_Islemleri() {
        bas = null;
        son = null; 
    }
    
    void sonaEkle(Dugum d) {
        if (bas == null) {
            d.geri = d;
            d.ileri = d;
            bas = son = d;
        } else {
            d.ileri = bas;
            d.geri = bas.geri;
            bas.geri.ileri = d;
            bas.geri = d;
        }
        elemanSayisi++;
    }
    
    void sondanSil() {
        if (bas.ileri == bas) {
            bas = null;
        } else {
            bas.geri.ileri = bas.ileri;
            bas.ileri.geri = bas.geri;
            bas = bas.ileri;
        }
        elemanSayisi--;
    }
    
    public void goster() {
        int i = 0;
        if (elemanSayisi==0) {
            System.out.println("Liste Boş");
        } else {
            Dugum d = bas;
            if (aci>=0) {
                while (i <= 360 - aci) {
                    System.out.println("Sin " + d.aci + " = " + d.sin_deger + " -> ");
                    d = d.ileri;
                    i++;
                }
                System.out.println("");
            }
            else{
                while (i >= -360 - aci) {
                    System.out.println("Sin " + d.aci + " = " + d.sin_deger + " -> ");
                    d = d.ileri;
                    i--;
                }
                System.out.println("");
            } 
        }
    }
    
    public void aciEkle(double aci) {
        int top = 0;
        this.aci= aci;
        Dugum d; 
        if (aci >= 0) {
            while (aci <= 360) { 
                d = new Dugum((int) aci, Math.sin(Math.toRadians(aci)));
                if (d.aci<=180) {
                    top+=d.sin_deger;
                }
                sonaEkle(d); 
                aci++;    
            }
            System.out.println("0-180 arası sin değerleri toplamı = "+top);
        } else if (aci < 0) {

            while (aci >= -360) {
                d = new Dugum((int) aci, Math.sin(Math.toRadians(aci)));
                sonaEkle(d);
                aci--; 
            } 
        }
    }
    void ciz(int periyot) {
        Ciz ciz = new Ciz(this, periyot, this.bas.aci);
        ciz.init();
        JFrame ekran = new JFrame();
        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.add(ciz);
        ekran.setVisible(true);

        ciz.start();
    }
}
