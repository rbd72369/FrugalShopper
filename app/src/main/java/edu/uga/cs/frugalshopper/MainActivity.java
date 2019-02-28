package edu.uga.cs.frugalshopper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateBtn = findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText price1ET = findViewById(R.id.price1ET);
                EditText price2ET = findViewById(R.id.price2ET);
                EditText price3ET = findViewById(R.id.price3ET);

                EditText pounds1ET = findViewById(R.id.pounds1ET);
                EditText pounds2ET = findViewById(R.id.pounds2ET);
                EditText pounds3ET = findViewById(R.id.pounds3ET);

                EditText ounces1ET = findViewById(R.id.ounces1ET);
                EditText ounces2ET = findViewById(R.id.ounces2ET);
                EditText ounces3ET = findViewById(R.id.ounces3ET);

                TextView result1TV = findViewById(R.id.result1TV);
                TextView result2TV = findViewById(R.id.result2TV);
                TextView result3TV = findViewById(R.id.result3TV);

                TextView bestBuyTV = findViewById(R.id.bestBuyTV);

                double price1 = doubleConverter(price1ET.getText().toString());
                double price2 = doubleConverter(price2ET.getText().toString());
                double price3 = doubleConverter(price3ET.getText().toString());

                double ounces1 = doubleConverter(ounces1ET.getText().toString());
                double ounces2 = doubleConverter(ounces2ET.getText().toString());
                double ounces3 = doubleConverter(ounces3ET.getText().toString());

                double pounds1 = doubleConverter(pounds1ET.getText().toString());
                double pounds2 = doubleConverter(pounds2ET.getText().toString());
                double pounds3 = doubleConverter(pounds3ET.getText().toString());

                double weight1 = pounds1 * 16 + ounces1;
                double weight2 = pounds2 * 16 + ounces2;
                double weight3 = pounds3 * 16 + ounces3;

                double result1 = price1/weight1;
                double result2 = price2/weight2;
                double result3 = price3/weight3;

                boolean undef1 = isUndef(result1);
                boolean undef2 = isUndef(result2);
                boolean undef3 = isUndef(result3);

                String bestBuy;
                if(undef1&&undef2&&undef3){
                    bestBuy = "\nALL EQUAL";
                }
                else if(undef2 && undef3){
                    bestBuy = "1";
                }
                else if(undef1 && undef3){
                    bestBuy = "2";
                }
                else if(undef1 && undef2){
                    bestBuy = "3";
                }
                else if(undef3){
                    if(result1<result2){
                        bestBuy = "1";
                    }
                    else if(result1==result2){
                        bestBuy = "1 and 2";
                    }
                    else bestBuy = "2";
                }
                else if(undef2){
                    if(result1<result3){
                        bestBuy = "1";
                    }
                    else if(result1==result3){
                        bestBuy = "1 and 3";
                    }
                    else bestBuy = "3";
                }
                else if(undef1){
                    if(result2<result3){
                        bestBuy = "2";
                    }
                    else if(result3==result2){
                        bestBuy = "2 and 3";
                    }
                    else bestBuy = "3";
                }
                else{
                    if(result1<result2 && result1<result3){
                        bestBuy = "1";
                    }
                    else if(result2<result1 && result2<result3 ){
                        bestBuy = "2";
                    }
                    else if(result3<result1 && result3<result2 ){
                        bestBuy = "3";
                    }
                    else if(result1==result2 && result1<result3 ){
                        bestBuy = "1 and 2";
                    }
                    else if(result1==result3 && (result1<result2 )){
                        bestBuy = "1 and 3";
                    }
                    else if(result2==result3 && result2<result1 ){
                        bestBuy = "2 and 3";
                    }
                    else{
                        bestBuy = "\nALL EQUAL";
                    }
                }

                DecimalFormat df = new DecimalFormat("####0.00");

                result1TV.setText("RESULT 1: $" + String.valueOf(df.format(result1)) + "/oz");
                result2TV.setText("RESULT 2: $" + String.valueOf(String.format("%.2f",result2)) + "/oz");
                result3TV.setText("RESULT 3: $" + String.valueOf(String.format("%.2f",result3)) + "/oz");
                bestBuyTV.setText("BEST BUY: " + String.valueOf(bestBuy));




            }
        });


    }

    /**
     * Converts string to double and returns the double
     * @param s the string
     * @return 0 if s is empty and the converted double if not
     */

    public double doubleConverter(String s){
        if(s.length()==0){
            return 0;
        }
        else return Double.parseDouble(s);
    }

    /**
     * checks if a double is undefined
     * @param d the double
     * @return true if undefined, false if defined
     */
    public boolean isUndef(double d){
        if(String.valueOf(d)=="NaN"){
            return true;
        }
        else return false;
    }
}
