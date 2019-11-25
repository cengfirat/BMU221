package kuyruk;
public class Kuyruk {
    private Dugum bas;
    private Dugum son;
    public Kuyruk(){
        bas=null;
        son=null;
    }
    public void ekle(Index indis){
        Dugum yeni_dugum=new Dugum();
        yeni_dugum.deger=indis;
        yeni_dugum.sonraki=null;
        if(bas==null){
            bas=yeni_dugum;
            son=bas;
        }else{
            son.sonraki=yeni_dugum;
            son=son.sonraki;
        }
    }
    public Index sil() throws Exception{
        Index indis=null;
        if(bas==null){
            throw new Exception("Kuyruk boş!!!");
        }else{
            indis=bas.deger;
            bas=bas.sonraki;
        }
        return indis;
    }
    public boolean bosMu(){
        return bas==null;
    }
    private class Dugum{
        Index deger;
        Dugum sonraki;
    }
}
