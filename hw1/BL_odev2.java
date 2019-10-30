
package bl_odev2;

import java.util.Scanner;


public class BL_odev2 {

  ogrenci ilk=null,son=null;
  
   void SonaEkle(ogrenci yeni){
   if(ilk==null)
       ilk=yeni;
   else
       son.ileri=yeni;
     son=yeni; 
}
    public void BasaEkle(ogrenci yeni){
   if(son==null)
       son=yeni;
   yeni.ileri=ilk;
   ilk=yeni;}
    
    void listeOrtaEkle(ogrenci yeni, ogrenci once){
    yeni.ileri=once.ileri;
    once.ileri=yeni;}
    void listeBasiSil() {
         ilk =ilk.ileri;
         if (ilk == null) {
         son = null;}}
    void listeSonuSil() {
ogrenci tmp, once;
tmp = ilk;
once = null;
while (tmp != son) {
once = tmp;
tmp = tmp.ileri;}
if (once == null) {
ilk = null;
} else {
once.ileri = null;
}son = once;}
void istenenisil(int no){
      
        ogrenci temp = ilk, once = null; 
 
        if (temp != null && temp.no == no) 
        { 
            ilk = temp.ileri; 
            return;    } 

        while (temp != null && temp.no != no) 
        { 
            once = temp; 
            temp = temp.ileri;  }     

        if (temp == null) return; 
        // bağlantıyı kopar
        once.ileri = temp.ileri; } 

  
  void vizesienyuksek(){
      ogrenci tmp=ilk;
      ogrenci max=null;
      while(tmp.ileri!=null ){
          
          if(tmp.vize>tmp.ileri.vize){
              max=tmp;}
          
            tmp=tmp.ileri; 
                      }   
      System.out.println(max.vize);
          }
      

void ortbul(BL_odev2 l1,BL_odev2 l2){
    ogrenci tmp=ilk;
    double ort;
    while(tmp.ileri!=null){
        ort=tmp.vize*0.4+tmp.genel*0.6;
        if(ort>=50){
            l1.BasaEkle(tmp);}
        else{
            l2.BasaEkle(tmp);
        }
    }
}
    void listele(){
    ogrenci gecici=ilk;
    while(gecici !=null){
        System.out.println(" "+gecici.no + " " + gecici.isim + " " + gecici.vize+""+gecici.genel);
        gecici=gecici.ileri;
    }
    }
    
}
class test{
    public static void main(String[] args) {
        int secim=-1;
        Scanner klavye= new Scanner(System.in);
        BL_odev2 l2= new BL_odev2();
        BL_odev2 l1= new BL_odev2();
        BL_odev2 l3= new BL_odev2();
        do{
            System.out.println("1-Öğrenci ekle\n2: Listele\n3:Sil\n4:İsteneniSil\n5:ortbul\6:maxvize\n0:Cıkıs");
        secim= klavye.nextInt();
        switch(secim){
            case 1:
                System.out.println("Ögrencinin Numarasını giriniz:");
                int no= klavye.nextInt();
                System.out.println("Öğrencinin adı:");
                String isim=klavye.next();
                System.out.println("Öğrencinin vizesi:");
                int vize=klavye.nextInt();
                System.out.println("Öğrencinin finali:");
                int genel=klavye.nextInt();
                ogrenci e=new ogrenci(no, isim, vize, genel);
                l2.BasaEkle(e);
                System.out.println("Öğrenci Eklendi.");
                break;
            case 2:
                //l2.listele();
                l1.listele();
                l3.listele();
                break;
            case 3:
                l2.listeBasiSil();
                System.out.println("Öğrenci silindi");
               break;
            case 4:
                System.out.println("Silmek istediğiniz öğrenci numarasını giriniz:");
                int numara=klavye.nextInt();
                 l2.istenenisil(numara);
                System.out.println(numara+"numaralı öğrenci silindi:");
                break;
            case 5:
                l2.ortbul(l1, l3);
                break;
            case 6:
                l2.vizesienyuksek();
                break;
        }}
        while(secim!=0);        }}
