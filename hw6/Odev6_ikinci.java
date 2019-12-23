
package odev6_ikinci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class eleman {
    private int icerik;

    public eleman(int d) {
        icerik = d;
    }

    public int getKey() {
        return icerik;
    }
}

class bagliHash {
    int anahtar;
    int degisken;
    bagliHash ileri;

    public bagliHash(int anahtar, int degisken) {
        this.anahtar = anahtar;
        this.degisken = degisken;
        ileri = null;
    }

    int getAnahtar() {
        return anahtar;
    }

    int getDegisken() {
        return degisken;
    }

    void setDegisken(int degisken) {
        this.degisken = degisken;
    }

    bagliHash getIleri() {
        return ileri;
    }

    void setIleri(bagliHash ileri) {
        this.ileri = ileri;
    }
}

public class Odev6_ikinci {
    int boyut = 10;
    int[] tablo = new int[boyut];
    eleman[] hashDizisi;
    int diziBoyutu;
    eleman silinen;
    int tabloBoyutu = 10;
    bagliHash[] zincirTablo;

    static void eleman(int[] dizi1, int sayi) {
        List[] dizi2 = new List[sayi + 1];
        for (int i = 0; i < dizi2.length; i++) {
            dizi2[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < dizi1.length; i++) {
            if (dizi1[i] <= sayi) {
                dizi2[dizi1[i]].add(i);
            }
        }
        for (int i = 0; i < dizi2.length / 2; i++) {
            if (!dizi2[i].isEmpty() && !dizi2[sayi - 1].isEmpty()) {
                for (Object indis : dizi2[i]) {
                    for (Object digerIndis : dizi2[sayi - i]) {
                        System.out.println("[" + indis.toString() + ", " + digerIndis.toString() + "] indisteki degerlerin toplamı =" + sayi );
                    }
                }
            }
        }
    }

    static boolean anagram(final char[] str1, final char[] str2) {
        final int n1 = str1.length;
        final int n2 = str2.length;
        if (n1 != n2) {
            return false;
        }
        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i = 0; i < n1; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }

    int hash(int konum) {
        return konum % boyut;
    }

    void ekle(int konum, int deger) {
        int h = hash(konum);
        if (tablo[h] != 0) {
            h++;
            if (h >= boyut) {
                h = 0;
            }
        }
        tablo[hash(konum)] = deger;
    }

    boolean altkumemi(int[] deger, int[] altkume, int m, int n) {
        int i = 0, j = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (altkume[i] == deger[j]) {
                    break;
                }
            }
            if (j == m) {
                return false;
            }
        }
        return true;
    }

    public Odev6_ikinci(int diziBoyutu) {
        this.diziBoyutu = diziBoyutu;
        hashDizisi = new eleman[diziBoyutu];
        silinen = new eleman(-1);

        zincirTablo = new bagliHash[tabloBoyutu];
        for (int i = 0; i < tabloBoyutu; i++) {
            zincirTablo[i] = null;
        }
    }

    int dogrusalHash(int key) {
        return key % diziBoyutu;
    }

    void dogrusalEkle(eleman yeni) {
        int key = yeni.getKey();
        int hashDegeri = hash(key);
        while (hashDizisi[hashDegeri] != null && hashDizisi[hashDegeri].getKey() != -1) {
            ++hashDegeri;
            hashDegeri %= diziBoyutu;
        }
        hashDizisi[hashDegeri] = yeni;
    }

    eleman dogrusalArama(int key) {
        int hashDegeri = dogrusalHash(key);
        while (hashDizisi[hashDegeri] != null) {
            if (hashDizisi[hashDegeri].getKey() == key) {
                return hashDizisi[hashDegeri];
            }
            ++hashDegeri;
            hashDegeri %= diziBoyutu;
        }
        return null;
    }

    void tabloYazdir() {
        System.out.println("Tablo: ");
        for (int i = 0; i < diziBoyutu; i++) {
            if (hashDizisi[i] != null) {
                System.out.print(hashDizisi[i].getKey() + " ");
            } else {
                System.out.print("# ");
            }
        }
        System.out.println();
    }

    void zincirEkle(int anahtar, int degisken) {
        int h = (anahtar % tabloBoyutu);
        if (zincirTablo[h] == null) {
            zincirTablo[h] = new bagliHash(anahtar, degisken);
        } else {
            bagliHash temp = zincirTablo[h];
            while (temp.getIleri() != null && temp.getAnahtar() != anahtar) {
                temp = temp.getIleri();
            }
            if (temp.getAnahtar() == anahtar) {
                temp.setDegisken(degisken);
            } else {
                temp.setIleri(new bagliHash(anahtar, degisken));
            }
        }
    }

    int zincirArama(int anahtar) {
        int h = (anahtar % tabloBoyutu);
        if (zincirTablo[h] == null) {
            return -1;
        } else {
            bagliHash temp = zincirTablo[h];
            while (temp != null && temp.getAnahtar() != anahtar) {
                temp = temp.getIleri();
            }
            if (temp == null) {
                return -1;
            } else {
                return temp.getDegisken();
            }
        }
    }

    public static void main(final String[] args) {
        int[] dizi1 = {2, 5, 8, 1, 4, 7, 3, 6, 9, 10};
        eleman(dizi1, 9);
        System.out.println("----------");
        char[] str1 = {'a', 'l', 'a', 'ş', 'ı', 'm'};
        char[] str2 = {'a', 'l', 'ı', 'ş', 'm', 'a'};
        if (anagram(str1, str2)) {
            System.out.println("Anagramdır.");
        } else {
            System.out.println("Anagram degildir.");
        }
        System.out.println("----------");
        int[] konum = {2, 5, 8, 1, 4, 7, 3, 6, 9, 0};
        int[] deger = {1234, 5678, 9101, 2131, 4151, 6171, 8192, 1212, 2232, 4252};
        int[] altkume = {1234, 9101, 2131, 6171, 1212};
        Odev6_ikinci hash = new Odev6_ikinci(10);
        for (int i = 0; i < hash.boyut; i++) {
            hash.ekle(konum[i], deger[i]);
        }
        // hash.yazdir();
        if (hash.altkumemi(deger, altkume, deger.length, altkume.length)) {
            System.out.println("Alt kume dizisi deger dizisinin alt kumesidir");
        } else {
            System.out.println("Alt kume dizisi deger dizisinin alt kumesi degildir!");
        }
        System.out.println("----------");
        System.out.println("Dogrusal sinama: ");
        eleman yeni;
        int[] key = {1, 2, 4, 6, 9, 11, 12, 14};
        for (int i = 0; i < key.length; i++) {
            yeni = new eleman(key[i]);
            hash.dogrusalEkle(yeni);
        }
        hash.tabloYazdir();
        yeni = hash.dogrusalArama(2);
        if (yeni != null) {
            System.out.println("\nAranan deger Bulundu.");
        } else {
            System.out.println("\nAranan deger Bulunamadi.");
        }
        System.out.println("----------");
        System.out.println("Zincir hash: ");
        for (int i = 0; i < hash.tabloBoyutu; i++) {
            hash.zincirEkle(konum[i], deger[i]);
        }
        System.out.println("\nAranan konumdaki deger: " + hash.zincirArama(5));
    }
}


