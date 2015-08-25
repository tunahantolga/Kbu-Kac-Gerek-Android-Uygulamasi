package com.example.tunahantolga.vizefinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.calculator);
    }

 public void Clear(View v){

     Button temizle = (Button) findViewById(R.id.temizle);

     final EditText vize_not = (EditText) findViewById(R.id.editText);
     final EditText vize_odev = (EditText) findViewById(R.id.editText2);
     final EditText final_odev = (EditText) findViewById(R.id.editText3);
     final TextView gereken_not = (TextView) findViewById(R.id.textView3);

     vize_not.setText("");
     vize_odev.setText("");
     final_odev.setText("");
     gereken_not.setText("");
 }

    public void onButtonClick(View v ){
        Button hesapla = (Button) findViewById(R.id.hesapla);

        final EditText vize_not = (EditText) findViewById(R.id.editText);
        final EditText vize_odev = (EditText) findViewById(R.id.editText2);
        final EditText final_odev = (EditText) findViewById(R.id.editText3);
        final TextView gereken_not = (TextView) findViewById(R.id.textView3);

        final_odev.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        float value_vize;
        float value_vodev;
        float value_fodev;

        if(!vize_not.getText().toString().equals("")) {
            value_vize = Float.parseFloat(vize_not.getText().toString());
        }
        else{
            value_vize = 0;
        }

        if(!vize_odev.getText().toString().equals("")) {
            value_vodev = Float.parseFloat(vize_odev.getText().toString());
        }
        else{
            value_vodev = 0;
        }
        if(!final_odev.getText().toString().equals("")) {
            value_fodev = Float.parseFloat(final_odev.getText().toString());
        }
        else{
            value_fodev = 0;
        }

        float vize_carpan = (float) 0.4;
        float final_carpan = (float) 0.6;
        float  not = 0;

        if(v.getId() == R.id.hesapla)
        {
            vize_not.setFilters(new InputFilter[]{new InputFilterMinMax("1","12")});
            if( value_vize > 100 || value_vodev > 100 || value_fodev >100){
                Toast.makeText(MainActivity.this,"100'den Büyük Not Girmemelisiniz!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(vize_not.getText().toString().equals("")){
                Toast.makeText(MainActivity.this,"Vize Notu Girmelisiniz!",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                not = (value_vize * vize_carpan) + (value_vodev * vize_carpan) + (value_fodev * final_carpan);
                not = 60 - not;
                not = not / 6 * 10;
        }
            if( not < 50 ){
                not = 50;
            }

            gereken_not.setText(not + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bilgi) {
            Intent intent = new Intent(this,Info.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.paylas) {
            Intent paylas = new Intent(Intent.ACTION_SEND);
            paylas.setType("text/plain");
            String mesaj = "Karabük Üniversitesi Kaç Gerek? Android Uygulaması - ";
            paylas.putExtra(Intent.EXTRA_TEXT,mesaj);
            startActivity(paylas);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
