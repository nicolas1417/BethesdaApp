package com.beth.bethesdaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    public float numero = 0;
    public int entero = 0;
    public String resultadoTotal = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("IEEE754");
        Button entrada = (Button)findViewById(R.id.buttonConvert);
        entrada.setOnClickListener(myListener);
        AdView adview = (AdView)findViewById(R.id.adView);

        AdRequest adrequest = new AdRequest.Builder().build();
        adview.loadAd(adrequest);
    }

    private View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText ingresado = (EditText) findViewById(R.id.ingresado);
            numero = Float.parseFloat(ingresado.getText().toString()); //Agarre el ingresado y quedo en un float
            entero = (int) numero;
            System.out.println(numero); //Eliminar
            String resultadoParcial = division(entero);
            String mantissa = resultadoParcial + multiplicacion(numero, entero);
            mantissa = mantissa.substring(1);
            for(int i = mantissa.length(); i < 23; i++){
                mantissa = mantissa + "0";
            }
            resultadoTotal = division((resultadoParcial.length()) + 126) + mantissa;
            CheckBox check = (CheckBox) findViewById(R.id.checkBox);
            if(check.isChecked())
                resultadoTotal = "1" + resultadoTotal;
            else
                resultadoTotal = "0" + resultadoTotal;
            System.out.println("El resultado total es: " + resultadoTotal); // Eliminar
            System.out.println(mantissa);   //Eliminar
            TextView resultadoVista = (TextView) findViewById(R.id.textViewResultado);
            resultadoVista.setText("Result:  " + resultadoTotal);
        }
    };

    public String division(int n) {
        String result = "";
        while((n/2) != 0){
            result = (n % 2) + result;
            n = n/2;
        }
        return "1" + result;
    }

    public String multiplicacion(float n, int n2){
        float num = n - n2;
        int buffer = 0;
        String result = "";
        do{
            buffer = (int) (num * 2);
            result = result + buffer;
            num = (num * 2) - buffer;
        }while(num != 0);
        return result;
    }

}
