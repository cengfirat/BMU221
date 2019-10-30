/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl_odev1;

public class liste {
        Eleman ilk; 
	Eleman son; 
	public liste() 
	{
	ilk = null; 
	son = null;
	}

 void listeBasinaEkle(Eleman yeni){
   if(son==null)
       son=yeni;
   yeni.ileri=ilk;
   ilk=yeni;
   
}
void listeSonunaEkle(Eleman yeni){
   if(ilk==null)
       ilk=yeni;
   else
       son.ileri=yeni;
     son=yeni; 
}
void listeOrtaEkle(Eleman yeni, Eleman once){
yeni.ileri=once.ileri;
once.ileri=yeni;
}
void listeBasiSil() {
ilk =ilk.ileri;
if (ilk == null) {
son = null;
}
}
void listeSonuSil() {
Eleman tmp, once;
tmp = ilk;
once = null;
while (tmp != son) {
once = tmp;
tmp = tmp.ileri;
}
if (once == null) {
ilk = null;
} else {
once.ileri = null;
}
son = once;
}
void istenenisil(int a){
 if (ilk == null) 
     return; 
     Eleman temp = ilk; 
     //ilk eleman silinecekse
        if (a == 0) 
        { 
            ilk= temp.ileri;  
            return; 
        } 
  
        // Silinecek elemanın öncesini bul
        for (int i=0; temp!=null && i<a-1; i++) 
            temp = temp.ileri; 
  
        // a>eleman sayısı ise silemeyiz
        if (temp == null || temp.ileri == null) 
            return; 
  
        // teemp.ileri silinecek olan elemandır.
        // Silinecek olan elemanın sonrasını gösteren isaretçiyi tut 
        Eleman x = temp.ileri.ileri; 
  
        temp.ileri = x;  // Silinen elemanın bağlantıyı kopar 
  
}
	public void yazdir() {
		System.out.print("Liste : ");
		Eleman aktif = ilk; 
		while (aktif != null) // Listenin sonuna kadar kontrol ediliyor
		{
			aktif.listele(); // Veri yazdırılıyor
			aktif = aktif.ileri; // Döngü döndürülüyor
		}
		System.out.println("");
	}
        //Decimal sayısı binary sayıya dönüştürüp bağlı liste olarak döndüren metot
        public void donustur(liste l,int a){
              
            while(a>=1){
              int  x=a%2;
              Eleman bir = new Eleman(x);
              l.listeSonunaEkle(bir);
              a=a/2;   }  }
//ikilik tabanda iki sayı alan toplayıp bir bağlı liste olarak döndüren metod
public Eleman topla(Eleman l1, Eleman l2){
       
        Eleman res = null; 
        Eleman prev = null; 
        Eleman temp = null; 
        int elde = 0, sum; 
        while (l1 != null || l2 != null) 
        { 
            sum = elde + (l1 != null ? l1.veri : 0) 
                    + (l2 != null ? l2.veri : 0); 
            elde = (sum == 2) ? 1 : 0; 
            sum = sum % 2; 
            temp = new Eleman(sum); 
  
            if (res == null) { 
                res = temp; 
            } else { 
                prev.ileri = temp; 
            } 
            prev = temp; 
  
            // l1 and l2 işaretçilerini ileri düğümlere taşı 
            if (l1 != null) { 
                l1 = l1.ileri; 
            } 
            if (l2 != null) { 
                l2 = l2.ileri; 
            } } 
  
        if (elde > 0) { 
            temp.ileri = new Eleman(elde); 
        } 
        return res;
}
    public static void main(String[] args) {
        Eleman bir = new Eleman(3); 
       Eleman iki=new Eleman(4);
       
        liste a=new liste();
        liste b=new liste();
        liste c=new liste();
        
        a.donustur(a, bir.veri);
        b.donustur(b, iki.veri);
        a.yazdir();
        b.yazdir();
        b.istenenisil(2);
        b.yazdir();
        Eleman rs=c.topla(a.ilk, b.ilk);
        c.listeSonunaEkle(rs);
        //c.yazdir();
        //c.istenenisil(1);
        //c.yazdir();
        
    }   
}
