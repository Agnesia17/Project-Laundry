package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.titulaundry.Dashboard.MainMenu;
import com.example.titulaundry.Dashboard.home_fragment;
import com.example.titulaundry.Dashboard.order_fragment;
import com.example.titulaundry.R;

import org.w3c.dom.Text;

public class pesanan extends AppCompatActivity {
    ImageButton toDashboard;
    CardView setAlamat,setBeratCuci;
    TextView header , headerBawah,hargaCucian,lamaWaktu;
    public String layanan , desc , waktu , harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        notif(pesanan.this);
        toKembali();
        setAlamat();
        setPesanan();
        setBeratCucuian();
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
    public void setBeratCucuian(){
        setBeratCuci = (CardView) findViewById(R.id.menu2);
        setBeratCuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BeratCucian.class);
                i.putExtra("hargaLaundry",String.valueOf(harga));
                startActivity(i);
            }
        });

    }


    public void setPesanan(){
        header = (TextView) findViewById(R.id.header);
        headerBawah = (TextView) findViewById(R.id.headerbawah);
        lamaWaktu = (TextView) findViewById(R.id.keterangan);
        hargaCucian = (TextView) findViewById(R.id.totalBerat);

        layanan = getIntent().getStringExtra("layanan");
        waktu = getIntent().getStringExtra("waktu");
        harga = getIntent().getStringExtra("harga");

        header.setText(layanan);
        headerBawah.setText(layanan);
        lamaWaktu.setText(waktu + " waktu pengerjaan");
        hargaCucian.setText(harga);

    }
    public void setAlamat(){
        setAlamat = (CardView) findViewById(R.id.menu1);
        setAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),set_waktu_alamat.class);
                startActivity(i);
            }
        });
    }
    public void toKembali(){
        toDashboard = (ImageButton) findViewById(R.id.kembali);
        toDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesanan.super.onBackPressed();
            }
        });
    }
}