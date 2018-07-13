package com.nse;

/**
 * Created by POPO on 2/25/2018.
 */

public class ListItem {
    private String nama, pihak2, tanggal, sifat, bulanan, bulan;

    public ListItem(String nama, String pihak2, String tanggal, String sifat, String bulanan, String bulan) {
        this.nama = nama;
        this.pihak2 = pihak2;
        this.tanggal = tanggal;
        this.sifat = sifat;
        this.bulanan = bulanan;
        this.bulan = bulan;

    }

    public String getNama() {
        return nama;
    }

    public String getPihak2() {
        return pihak2;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getSifat() {
        return sifat;
    }

    public String getBulanan() {
        return bulanan;
    }

    public String getBulan() {
        return bulan;
    }
}