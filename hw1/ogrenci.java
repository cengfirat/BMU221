/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl_odev2;

public class ogrenci {

    int no;
    String isim;
    int vize, genel;
    ogrenci ileri;
    
   ogrenci(int no, String isim, int vize, int genel){
   this.no=no;
   this.isim=isim;
   this.genel=genel;
   this.vize=vize;
   ileri=null;
   

}
}
