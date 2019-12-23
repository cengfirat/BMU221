package agacodev;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

class PaintImage extends JPanel
{
  Agac agac=new Agac();
  public static BufferedImage image;
 
  public PaintImage (Agac agac)
  {
    super();
    try
    {     
      this.agac=agac;  
      image = ImageIO.read(new File("harita.jpg"));
    }
    catch (IOException e)
    {
    }
  }

  @Override
  public void paintComponent(Graphics g)
  {
    g.drawImage(image, 0, 0, null);  
        ciz(agac.kok,g);
  }
    void ciz(Dugum tmp, Graphics g){
        if (tmp == null) return;
        if (tmp.sol != null)
        ciz (tmp.sol,g);
        
        double enlem=tmp.enlem;
        double boylam=tmp.boylam;
        int b=(int)(((boylam-26)/19)*780);
        int e=(int)(((42-enlem)/6)*350);
        g.fillOval(b,e,20,20);
        repaint(); 
        
        if (tmp.sag != null)
        ciz (tmp.sag,g);
    }
}

class Dugum{
    int plaka;
    String iladi;
    double enlem,boylam;
    Dugum sol,sag;
    Dugum(int plaka, String iladi, double enlem, double boylam){
        this.plaka=plaka;
        this.iladi=iladi;
        this.enlem=enlem;
        this.boylam=boylam;
        sol=null;sag=null;
    }   
}

class Agac{
    Dugum kok;
    int toplam;
    int say;
    Dugum[] dizi=new Dugum[100];
    Agac(){
        kok=null;
        toplam=0;
        say=0;
    }
    
    Dugum ara(int aranan){
        Dugum tmp = kok;
        while (tmp != null){
            if(aranan==tmp.plaka) return tmp;
            else if(aranan<tmp.plaka) tmp=tmp.sol;
            else tmp=tmp.sag;
        }
        return null;
    }
    
    void ekle(int plaka, String iladi, double enlem, double boylam){
        Dugum yeni=new Dugum(plaka,iladi,enlem,boylam);
        if(kok==null) kok=yeni;
        else{
            Dugum tmp=kok;
            Dugum once=null;
            while(tmp!=null){
                once=tmp;
                if(yeni.plaka<tmp.plaka) tmp=tmp.sol;
                else tmp=tmp.sag;
            }
            if(yeni.plaka<once.plaka) once.sol=yeni;
            else once.sag=yeni;
        }
    }
    
    String sil(int plaka)
    {
	Dugum once = null;
	Dugum tmp = kok;
	while (tmp != null && tmp.plaka != plaka)
	{
		once = tmp;
		if (plaka < tmp.plaka) {
			tmp = tmp.sol;
		}
		else {
			tmp = tmp.sag;
		}
	}

	// plaka bulunamazsa bitir
	if (tmp == null) {
                return plaka+" nolu plaka kodu bulunamadı.";
        }

	// durum1: silinecek düğümün hiç çocuğu yoksa
	if (tmp.sol == null && tmp.sag == null)
	{
		if (tmp != kok) {
			if (once.sol == tmp) {
				once.sol = null;
			} else {
				once.sag = null;
			}
		}
		else {
			kok = null;
		}
	}

	// durum2: silinecek düğümün 2 çocuğu varsa
	else if (tmp.sol != null && tmp.sag != null)
	{
		Dugum saginenkucugu  = enkucuk(tmp.sag);

		int silinecekplaka = saginenkucugu.plaka;
                String silinecekiladi=saginenkucugu.iladi;
                double silinecekenlem=saginenkucugu.enlem;
                double silinecekboylam=saginenkucugu.boylam;
                
		sil(saginenkucugu.plaka);

		tmp.plaka = silinecekplaka;
                tmp.iladi = silinecekiladi;
                tmp.enlem = silinecekenlem;
                tmp.boylam = silinecekboylam;      
	}

	// durum3: silinecek düğümün sadece tek çocuğu varsa
	else
	{
		Dugum cocuk = (tmp.sol != null)? tmp.sol: tmp.sag;

		if (tmp != kok)
		{
			if (tmp == once.sol) {
				once.sol = cocuk;
			} else {
				once.sag = cocuk;
			}
		}
		else {
			kok = cocuk;
		}
	}
        return plaka+" nolu plakaya ait veriler silindi.";
    }
     
  
    Dugum enkucuk(Dugum tmp){
        while (tmp.sol!=null)
            tmp=tmp.sol;
        return tmp;
    }
    
    Dugum enbuyuk(Dugum tmp){
        while (tmp.sag!=null)
            tmp=tmp.sag;
        return tmp;
    }
    
    void guncelle(int plaka, String yeniiladi, double yenienlem, double yeniboylam){
        Dugum guncellenecek=ara(plaka);
        guncellenecek.iladi=yeniiladi;
        guncellenecek.enlem=yenienlem;
        guncellenecek.boylam=yeniboylam;     
    }
    
    void preorder(Dugum tmp){
        if (tmp == null) return;
        System.out.print(tmp.plaka+" ");
        if (tmp.sol != null)
        preorder(tmp.sol);
        if (tmp.sag != null)
        preorder(tmp.sag);
    }
    
    void inorder(Dugum tmp){
        if (tmp == null) return;
        if (tmp.sol != null)
        inorder (tmp.sol);
        System.out.print(tmp.plaka+" ");
        if (tmp.sag != null)
        inorder (tmp.sag);
    }
    
    void postorder(Dugum tmp){
        if (tmp == null) return;
        if (tmp.sol != null)
        postorder(tmp.sol);
        if (tmp.sag != null)
        postorder(tmp.sag);
        System.out.print(tmp.plaka+" ");
    }
    
    void levelorder() 
    { 
        int h = yukseklik(kok);  
        for (int i=1; i<=h; i++){
            levelegoreyaz(kok, i);
            
            System.out.println();
        }
    } 
  
    int yukseklik(Dugum tmp) 
    { 
        if (tmp == null) return 0; 
        else
        { 
            int solyukseklik = yukseklik(tmp.sol); 
            int sagyukseklik = yukseklik(tmp.sag); 
              
            /* use the larger one */
            if (solyukseklik > sagyukseklik) 
                return(solyukseklik+1); 
            else return(sagyukseklik+1);  
        } 
    } 
  
    void levelegoreyaz (Dugum tmp ,int level) 
    { 
        
        if (tmp == null) return; 
        if (level == 1) {System.out.print(tmp.plaka + " ");
        toplam+=tmp.plaka;}
        else if (level > 1) 
        { 
            levelegoreyaz(tmp.sol, level-1); 
            levelegoreyaz(tmp.sag, level-1); 
        } 
    } 

    boolean BSTmi(Dugum tmp)  
    {  
        if (tmp == null) return(true);  
      
        if (tmp.sol!=null && enbuyuk(tmp.sol).plaka > tmp.plaka) return(false);  
      
        if (tmp.sag!=null && enkucuk(tmp.sag).plaka < tmp.plaka) return(false);  
    
        if (!BSTmi(tmp.sol) || !BSTmi(tmp.sag)) return(false);  
      
        return(true);  
    }  
    
    boolean dengelimi(Dugum tmp) 
    { 
        if (tmp == null) 
            return true; 
  
        int solyukseklik = yukseklik(tmp.sol); 
        int sagyukseklik = yukseklik(tmp.sag); 
  
        if (Math.abs(solyukseklik - sagyukseklik) <= 1 && dengelimi(tmp.sol) && dengelimi(tmp.sag)) 
            return true; 

        return false; 
    } 
    void bonus()
    {
        JFrame f = new JFrame("Harita");
        f.add(new PaintImage(this));
        f.setSize(810,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
   
    
}

public class Agacodev {

    public static void main(String[] args) {
        Agac agac=new Agac();
        agac.preorder(agac.kok);
        agac.ekle(23, "Elazığ", 38.6748, 39.2225);
        agac.ekle(12, "Bingöl", 38.8855, 40.4966);
        agac.ekle(34, "İstanbul", 41.0082, 28.9784);
        agac.ekle(35, "İzmirr", 35.321333, 37);
        agac.ekle(6, "Ankara", 39.9334, 32.8597);
        agac.ekle(1, "Adana", 36.9914, 35.3308);
        agac.ekle(58, "Sivas", 39.7505, 37.015);
        agac.ekle(38, "Kayseri", 38.7205, 35.4826);
        agac.preorder(agac.kok);
        System.out.println("");
        agac.inorder(agac.kok);
        System.out.println("");
        agac.postorder(agac.kok);
        System.out.println("");
        
        System.out.println(agac.ara(23).iladi);       
        agac.guncelle(35,"İzmir",38.4237,27.1428);
        System.out.println(agac.ara(23).iladi);
        
        System.out.println(agac.sil(60));
        System.out.println(agac.sil(10));
        
        System.out.print("preorder-->");
        agac.preorder(agac.kok);
        System.out.println("");
        System.out.print("inorder-->");
        agac.inorder(agac.kok);
        System.out.println("");
        System.out.print("postorder-->");
        agac.postorder(agac.kok);
        System.out.println("");
        System.out.println("levelorder-->");
        agac.levelorder();
        System.out.println(agac.BSTmi(agac.kok)? "İkili": "İkili değil" );
        System.out.println(agac.dengelimi(agac.kok)? "Dengeli": "Dengeli değil" );
        agac.bonus();
        System.out.println(agac.toplam);

    }
    
}
