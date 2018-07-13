package com.nse;

/**
 * Created by POPO on 2/25/2018.
 */

public class ListItem2 {
    private String no, pemberi, penerima, tanggal, sifat, jenis, letak, bulan;

    public ListItem2(String no, String pemberi, String penerima, String tanggal, String sifat, String jenis, String letak, String bulan) {
        this.no = no;
        this.pemberi = pemberi;
        this.penerima = penerima;
        this.tanggal = tanggal;
        this.sifat = sifat;
        this.jenis = jenis;
        this.letak = letak;
        this.bulan = bulan;

    }

    public String getNo() {
        return no;
    }

    public String getPemberi() {
        return pemberi;
    }

    public String getPenerima() {
        return penerima;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getSifat() {
        return sifat;
    }

    public String getJenis() {
        return jenis;
    }

    public String getLetak() {
        return letak;
    }

    public String getBulan() {
        return bulan;
    }
}