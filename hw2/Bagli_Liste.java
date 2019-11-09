import java.util.Scanner;

public class Bagli_Liste {
    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        System.out.print("Açı Gir : ");
        double aci = k.nextDouble();
        Liste_Islemleri liste = new Liste_Islemleri();
        liste.aciEkle(aci);
        liste.goster();
        liste.ciz(1); 
    }
}