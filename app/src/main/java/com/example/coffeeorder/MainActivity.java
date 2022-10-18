package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int quantity= 0;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.quantityvariable);

    }


    public void plus(View v) {
        if (quantity==100){
            return;
        }
        quantity = quantity + 1;
        t1.setText(String.valueOf(quantity));
    }
    public void minus(View v) {
        if (quantity==0){
            return;
        }
        quantity = quantity -1;
        t1.setText(String.valueOf(quantity));
    }




    public void displaymessage(String message){
        TextView summary = (TextView) findViewById(R.id.ordersumm);
        summary.setText(message);
    }



    public void order (View v) {
        int price =  5;
        CheckBox ch1 = (CheckBox) findViewById(R.id.chb1);
        boolean check = ch1.isChecked();


        CheckBox ch2 = (CheckBox) findViewById(R.id.chb2);
        boolean check2 = ch2.isChecked();

        EditText name = (EditText) findViewById(R.id.cname);
        String nname = name.getText().toString();

        EditText number = (EditText) findViewById(R.id.cnumber);
        Editable nnumber = number.getText();

        if (check){
            price = price + 1;
        }
        if (check2){
            price = price  + 2;
        }
        int totalprice = price * quantity;

        String newmessage = ordersummary(nname,totalprice, check, check2);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:" + nnumber ));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", newmessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displaymessage(newmessage);

    }

    public String ordersummary (String name,int total, boolean checking, boolean checking2){
        String newmessage = " Name : " + name;
                newmessage += "\n Quantity : " + quantity;
        newmessage += "\n Total price : " + total;
        newmessage += "\n Added Whipped cream ? : " + checking ;
        newmessage += "\n Added chochlate topping ? : " + checking2 ;
        return newmessage;
    }



//    String message = " Name : "  + "\n " + " Quantity : "+ total  ;

}

