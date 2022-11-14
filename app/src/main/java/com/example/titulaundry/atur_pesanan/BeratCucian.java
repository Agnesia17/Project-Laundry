package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.titulaundry.R;

public class BeratCucian extends AppCompatActivity {
    ImageButton bckToPesanan;
    int numberOrder = 1;
    int totalHarga = 0;
    CardView plsButton , minButton;
    TextView orderCount,brtCucian,hrgCucian,hargaFix;
    Button brtPsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berat_cucian);
        itemCountOrder();
        setBckToPesanan();
        bawahDataBerat();
        notif(BeratCucian.this);
    }
    public void notif(Activity activity){
        //change color notif bar
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        //set icons notifbar
        View decor = activity.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void setBckToPesanan(){
        bckToPesanan = (ImageButton) findViewById(R.id.kembali);
        bckToPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),pesanan.class);
//                startActivity(i);
                BeratCucian.super.onBackPressed();
            }
        });
    }
    public void bawahDataBerat(){
        brtPsn = (Button) findViewById(R.id.buatPesanan);
        brtPsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String layanan;
                String waktu;
                String harga;
                String tanggal;
                String alamat;
                String hariKembali;
                String waktuJemput;
                String waktuKembali;

                //inisiasi dari class berat cucian
                layanan = getIntent().getStringExtra("layanan");
                waktu = getIntent().getStringExtra("waktu");
                harga = getIntent().getStringExtra("harga");

                //inisiasi dari class pesanan
                tanggal = getIntent().getStringExtra("hariJemput");
                alamat = getIntent().getStringExtra("alamatUser");
                hariKembali = getIntent().getStringExtra("hariKembali");
                waktuJemput = getIntent().getStringExtra("waktuJemput");
                waktuKembali = getIntent().getStringExtra("waktuKembali");

                String count = String.valueOf(numberOrder);
                Intent i = new Intent(getApplicationContext(),pesanan.class);
                System.out.println("tanggal = "+tanggal);
                //data dari class berat
                i.putExtra("berat",count);
                i.putExtra("layanan",layanan);
                i.putExtra("waktu",waktu);
                i.putExtra("harga",harga);

                //data dari class pesanan
                i.putExtra("hariJemput",tanggal);
                i.putExtra("alamatUser",alamat);
                i.putExtra("hariKembali",hariKembali);
                i.putExtra("waktuJemput",waktuJemput);
                i.putExtra("waktuKembali",waktuKembali);

                startActivity(i);
            }
        });
    }
    public void itemCountOrder(){

        brtCucian = (TextView) findViewById(R.id.totalBeratCucian);
        brtCucian.setText(String.valueOf(numberOrder));

        orderCount = (TextView) findViewById(R.id.OrderCount);
        orderCount.setText(String.valueOf(numberOrder));

        hrgCucian = (TextView) findViewById(R.id.totalHargaCucian);
        String hargaBarang = getIntent().getStringExtra("hargaLaundry");
        hargaBarang = hargaBarang.replaceAll("[^\\d.]", "");
        hargaBarang = hargaBarang.replace(".","");
        System.out.println("tes sapa == "+hargaBarang);

        int totalHargabrg = Integer.parseInt(hargaBarang);
        hrgCucian.setText("Rp. "+hargaBarang+" X " + String.valueOf(numberOrder)+ " Kg");

        hargaFix = (TextView) findViewById(R.id.hargaFix);
        hargaFix.setText("Rp. "+ String.valueOf(totalHarga));

        plsButton = (CardView) findViewById(R.id.plsBtn);
        minButton = (CardView) findViewById(R.id.minBtn);

        plsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder+1;
                orderCount.setText(String.valueOf(numberOrder));
                brtCucian.setText(String.valueOf(numberOrder)+ " Kg");
                hrgCucian.setText("Rp. "+String.valueOf(totalHargabrg)+" X " + String.valueOf(numberOrder)+ " Kg");
                totalHarga = totalHargabrg * numberOrder;
                hargaFix.setText("Rp. "+ String.valueOf(totalHarga));
            }
        });

        minButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder>1){
                    numberOrder = numberOrder - 1;
                }
                orderCount.setText(String.valueOf(numberOrder));
                brtCucian.setText(String.valueOf(numberOrder)+ " Kg");
                hrgCucian.setText("Rp. "+String.valueOf(totalHargabrg)+" X " + String.valueOf(numberOrder)+ " Kg");
                totalHarga = totalHargabrg * numberOrder;
                hargaFix.setText("Rp. "+ String.valueOf(totalHarga));
            }
        });
    }
}