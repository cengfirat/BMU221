package kuyruk;
public class Statik_Kuyruk {
    private Index[] anaDizi;
    private int bas=0;
    private int son=0;
    private int elemanSayisi=0;
    private int N;
    public Statik_Kuyruk(int N){
        this.N=N;
        anaDizi=new Index[N];
    }
    public void ekle(Index deger){
        if(doluMu()){
            System.out.println("Kuyruk Dolu !!!");
        }else{
            anaDizi[son]=deger;
            son=(son+1)%N;
            elemanSayisi++;
        }
    }
    public Index sil() throws Exception{
        Index sonuc=null;
        if(bosMu()){
            throw new Exception("Kuyruk Boş !!!");
        }else{
            sonuc=anaDizi[bas];
            bas=(bas+1)%N;
            elemanSayisi--;
        }
        return sonuc;
    }
    public boolean doluMu(){
        return elemanSayisi==N;
    }
    public boolean bosMu(){
        return elemanSayisi==0;
    }
}
