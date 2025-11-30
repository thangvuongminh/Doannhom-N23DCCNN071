
package model;
public class Diem {
    private String maMon; 
    private String msv;   
    private float tongKet;

    public Diem() {
    }

    public Diem(String maMon, String msv, float tongKet) {
        this.maMon = maMon;
        this.msv = msv;
        this.tongKet = tongKet;
    }

    public String getMaMon() { return maMon; }
    public void setMaMon(String maMon) { this.maMon = maMon; }

    public String getMsv() { return msv; }
    public void setMsv(String msv) { this.msv = msv; }

    public float getTongKet() { return tongKet; }
    public void setTongKet(float tongKet) { this.tongKet = tongKet; }
}
