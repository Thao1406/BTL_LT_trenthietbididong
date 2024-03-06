package com.example.may_tinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnDot,btnEqual,btnAC,btnDel,btnDiv,btnMinus,btnPlus,btnMulti;

    private TextView textViewHistory,textViewResul;
    private String number =null;
    double lastnumber=0, firstnumber=0;
    boolean operator= false;

    boolean isEqual= false;
    boolean dot=true;
    boolean del=true;
    String status= null;

    String history,result;

    String pattern="###,###,#####";
    DecimalFormat decimalFormat=new DecimalFormat(pattern);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = this.findViewById(R.id.btn0);
        btn1 = this.findViewById(R.id.btn1);
        btn2 = this.findViewById(R.id.btn2);
        btn3 = this.findViewById(R.id.btn3);
        btn4 = this.findViewById(R.id.btn4);
        btn5 = this.findViewById(R.id.btn5);
        btn6 = this.findViewById(R.id.btn6);
        btn7 = this.findViewById(R.id.btn7);
        btn8 = this.findViewById(R.id.btn8);
        btn9 = this.findViewById(R.id.btn9);

        btnPlus = this.findViewById(R.id.btnPlus);
        btnMinus = this.findViewById(R.id.btnMinus);
        btnMulti = this.findViewById(R.id.btnMulti);
        btnDiv = this.findViewById(R.id.btnDiv);

        btnDot = this.findViewById(R.id.btnDot);
        btnEqual = this.findViewById(R.id.btnEqual);
        btnAC = this.findViewById(R.id.btnAC);
        btnDel = this.findViewById(R.id.btnDel);

        textViewHistory = this.findViewById(R.id.textviewHistory);
        textViewResul = this.findViewById(R.id.textviewResult);

        btn0.setOnClickListener(view -> numberclick("0"));
        btn1.setOnClickListener(view -> numberclick("1"));
        btn2.setOnClickListener(view -> numberclick("2"));
        btn3.setOnClickListener(view -> numberclick("3"));
        btn4.setOnClickListener(view -> numberclick("4"));
        btn5.setOnClickListener(view -> numberclick("5"));
        btn6.setOnClickListener(view -> numberclick("6"));
        btn7.setOnClickListener(view -> numberclick("7"));
        btn8.setOnClickListener(view -> numberclick("8"));
        btn9.setOnClickListener(view -> numberclick("9"));

        btnPlus.setOnClickListener(view -> {

            if(isEqual){
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+"+"));
            }else{
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+result+"+"));
            }

                 if(operator){
                     if(status=="multi"){
                         Multi();
                     }else {
                         if(status=="div"){
                             Div();
                         }else{
                             if(status=="minus"){
                                 Minus();
                             }else{
                                 Plus();
                             }
                         }
                     }

                 }
                 operator= false;
                 number= null;
                 status="sum";
                 isEqual=false;
        });

        btnMinus.setOnClickListener(view -> {
            if(isEqual){
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+"-"));
            }else {
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+result+"-"));
            }

            if(operator){
                if(status=="multi"){
                    Multi();
                }else {
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="sum"){
                            Plus();
                        }else{
                            Minus();
                        }
                    }
                }

            }
            operator= false;
            number= null;
            status="minus";
            isEqual=false;
        });

        btnMulti.setOnClickListener(view -> {

            if(isEqual){
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+"*"));
            } else{
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+result+"*"));
            }

            if(operator){
                if(status=="sum"){
                    Multi();
                }else {
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="minus"){
                            Minus();
                        }else{
                            Multi();
                        }
                    }
                }

            }
            operator= false;
            number= null;
            status="multi";
            isEqual=false;
        });

        btnDiv.setOnClickListener(view -> {

            if(isEqual){
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+"/"));
            }else {
                history=textViewHistory.getText().toString();
                result=textViewResul.getText().toString();
                textViewHistory.setText((history+result+"/"));

            }

            if(operator){
                if(status=="multi"){
                    Multi();
                }else {
                    if(status=="sum"){
                        Plus();
                    }else{
                        if(status=="minus"){
                            Minus();
                        }else{
                            Div();
                        }
                    }
                }

            }
            operator= false;
            number= null;
            status="div";
            isEqual=false;
        });


        btnEqual.setOnClickListener(view -> {

            history=textViewHistory.getText().toString();
            result=textViewResul.getText().toString();
            textViewHistory.setText((history+result));

            if(operator){
                if(status=="multi"){
                    Multi();
                }else {
                    if(status=="sum"){
                        Plus();
                    }else{
                        if(status=="minus"){
                            Minus();
                        }else
                            if(status=="div") {
                            Div();
                        }else {
                                firstnumber=Double.parseDouble(textViewResul.getText().toString());
                            }
                    }
                }

            }
            operator= false;
            isEqual=true;
            dot=false;

        });


        btnAC.setOnClickListener(view ->{
            number=null;
            operator=false;
            textViewResul.setText("0");
            textViewHistory.setText("");
            firstnumber=0;
            lastnumber=0;
            dot=true;
            del=true;
        });

        btnDel.setOnClickListener(view ->{
            if(del){
                textViewResul.setText("0");
            }else{
                number=number.substring(0,number.length()-1);
                if(number.length()==0){
                    btnDel.setClickable(false);
                }else{
                    if(number.contains(".")){
                        dot=false;
                    }else{
                        dot=true;
                    }
                }
            }
            textViewResul.setText(number);
        });

        btnDot.setOnClickListener(view ->{

            if(dot){
                if(number==null){
                    number="0.";
                }else{
                    number=number+".";
                }
            }
            dot=false;
            textViewResul.setText(number);
        });
    }

    public void numberclick(String view){
        if(number==null){
            number=view;
        }else{
            number=number+view;
        }
        textViewResul.setText(number);
        operator= true;
        del=false;
        btnDel.setClickable(true);

    }

    public void Minus(){
        if(firstnumber==0){
            firstnumber=Double.parseDouble(textViewResul.getText().toString());
        }else {
            lastnumber=Double.parseDouble(textViewResul.getText().toString());
            firstnumber=firstnumber-lastnumber;
        }
        textViewResul.setText(decimalFormat.format(firstnumber));

    }
    public void Plus(){
        lastnumber=Double.parseDouble(textViewResul.getText().toString());
        firstnumber=firstnumber+lastnumber;
        textViewResul.setText(decimalFormat.format(firstnumber));
        dot=true;
    }
    public void Multi(){
        if (firstnumber==0){
            firstnumber=1;
        }
        lastnumber=Double.parseDouble(textViewResul.getText().toString());
        firstnumber=firstnumber*lastnumber;
        textViewResul.setText(decimalFormat.format(firstnumber));
        dot=true;
    }
    public void Div(){
        if(firstnumber==0){
            lastnumber=Double.parseDouble(textViewResul.getText().toString());
            firstnumber=lastnumber;
        }else {
            lastnumber=Double.parseDouble(textViewResul.getText().toString());
            firstnumber=firstnumber/lastnumber;
        }
        textViewResul.setText(decimalFormat.format(firstnumber));
        dot=true;
    }

}