/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3;

class Dugum {

    int ifade;
    Dugum ileri;

    public Dugum(int ifade) {
        this.ifade = ifade;
        this.ileri = null;
    }

}
public class STACK {
    int elemansayisi = 0;
    Dugum ust; // bas 

    public STACK() {
        this.ust = null;
    }

    void ekleme(Dugum d) {
        if (bosmu()) {
            ust = d;
        } else {
            d.ileri = ust;
            ust = d;
        }
        elemansayisi++;
    }

    int silme() {
        if (bosmu()) {
            System.out.println("Stack Boş");
            return 0;
        } else {
            Dugum tmp = ust;
            ust = ust.ileri;
            elemansayisi--;
            return tmp.ifade;
        }

    }

    boolean bosmu() {
        return elemansayisi == 0;
    }

    Dugum tepe() {
        return ust;
    }

    void goster() {
        if (bosmu()) {
            System.out.println("Stack boş");
        } else {
            Dugum d = ust;
            for (int i = 0; i < elemansayisi; i++) {
                System.out.println(d.ifade);
                d = d.ileri;
            }
        }
    }


    void kontrol(String ifade) {
        switch (ifade) {
            case "!":
                System.exit(0);
            case "^":
                silme();
                break;
            case "?":
                goster();
                break;
            case "+":
                if (elemansayisi >= 2) {
                    int sayi1 = silme();
                    int sayi2 = silme();
                    int toplam = sayi1 + sayi2;
                    Dugum d = new Dugum(toplam);
                    ekleme(d);
                } else {
                    System.out.println("Yeterli eleman yok.");
                }

                break;
            case "-":
                if (elemansayisi >= 2) {
                    int sayi1 = silme();
                    int sayi2 = silme();
                    int sonuc = sayi1 - sayi2;
                    Dugum d = new Dugum(sonuc);
                    ekleme(d);
                } else {
                    System.out.println("Yeterli eleman yok.");
                }
                break;
            case "/":
                if (elemansayisi >= 2) {
                    int sayi1 = silme();
                    int sayi2 = silme();
                    int sonuc = sayi1 / sayi2;
                    Dugum d = new Dugum(sonuc);
                    ekleme(d);
                } else {
                    System.out.println("Yeterli eleman yok.");
                }
                break;
            case "*":
                if (elemansayisi >= 2) {
                    int sayi1 = silme();
                    int sayi2 = silme();
                    int sonuc = sayi1 * sayi2;
                    Dugum d = new Dugum(sonuc);
                    ekleme(d);
                } else {
                    System.out.println("Yeterli eleman yok.");
                }
                break;
            default:
                try {
                    int gelenSayi = Integer.parseInt(ifade);
                    Dugum d = new Dugum(gelenSayi);
                    ekleme(d);
                } catch (Exception e) {
                    System.out.println("Geçerli ifade girin");
                }
                break;
        }
    }
    
}
