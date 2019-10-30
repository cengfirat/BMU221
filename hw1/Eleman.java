
package bl_odev1;


public class Eleman {
    int veri;
    Eleman ileri;
    public Eleman(int veri){
        ileri=null;
        this.veri=veri;
    }
    public void listele() // yazdırma fonksiyonu
	{
		System.out.print(veri + " ");
}
    
}
