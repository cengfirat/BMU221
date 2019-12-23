
package odev6;

class nokta {
    int icerik;

    public nokta(int icerik) {
        this.icerik = icerik;
    }
}

public class Odev6_birinci {
    nokta[] dizi;
    int tane;

    public Odev6_birinci(int N) {
        dizi = new nokta[N];
        tane = 0;
    }

    void degistir(int ata, int k) {
        int temp = dizi[ata].icerik;
        dizi[ata].icerik = dizi[k].icerik;
        dizi[k].icerik = temp;
    }

    void yukariCik(int k) {
        int ata = (k - 1) / 2;
        while (ata >= 0 && dizi[ata].icerik > dizi[k].icerik) {
            degistir(ata, k);
            k = ata;
            ata = (k - 1) / 2;
        }
    }

    void minHeap(int k) {
        int solCocuk, sagCocuk;
        solCocuk = 2 * k + 1;
        sagCocuk = 2 * k + 2;
        while ((solCocuk < tane && dizi[k].icerik < solCocuk) ||
                (sagCocuk < tane && dizi[k].icerik < dizi[sagCocuk].icerik)) {
            if (sagCocuk >= tane || dizi[solCocuk].icerik > dizi[sagCocuk].icerik) {
                degistir(solCocuk, k);
            } else {
                degistir(sagCocuk, k);
            }
            solCocuk = 2 * k + 1;
            sagCocuk = 2 * k + 2;
        }
    }

    void ekle(nokta yeni) {
        tane++;
        dizi[tane - 1] = yeni;
        yukariCik(tane - 1);
    }

    int sil() {
        int silinen = dizi[0].icerik;
        dizi[0] = dizi[tane - 1];
        tane--;
        minHeap(0);
        System.out.println("Silinen deger: " + silinen);
        return dizi[0].icerik;
    }

    boolean minHeapMi(int k) {
        int ata = (k - 1) / 2;
        while (ata >= 0 && k > 0) {
            if (dizi[ata].icerik > dizi[k].icerik) {
                return false;
            }
            k--;
            ata = (k - 1) / 2;
        }
        return true;
    }

    void minCevirMax(Odev6_birinci b) {
        for (int i = 0; i < b.dizi.length / 2; i++) {
            dizi[i].icerik = dizi[dizi.length - i - 1].icerik +
                    (dizi[dizi.length - i - 1].icerik = dizi[i].icerik) * 0;
        }
    }

    public static void main(String[] args) {
        Odev6_birinci heap = new Odev6_birinci(10);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i;
            heap.ekle(new nokta(a[i]));
        }
        System.out.println("Heap agacimiz: ");
        for (int i = 0; i < heap.tane; i++) {
            System.out.print(heap.dizi[i].icerik + " ");
        }
        System.out.println("\n");
        //heap.sil();
        System.out.println("Max Heap'e cevrildikten sonra: ");
        heap.minCevirMax(heap);
        for (int i = 0; i < heap.tane; i++) {
            System.out.print(heap.dizi[i].icerik + " ");
        }
        System.out.println("\n");
        System.out.println("Min Heap mi? \n" + heap.minHeapMi(0));
    }
}

    

