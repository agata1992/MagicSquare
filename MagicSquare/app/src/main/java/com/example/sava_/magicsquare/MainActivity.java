package com.example.sava_.magicsquare;

import android.app.AlertDialog;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button submit,new_game,resume,help;
    List<Integer> numberList = new ArrayList<Integer>();
    List<Integer> level = new ArrayList<Integer>();
    Chronometer chronometer;
    int Level;
    Boolean Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit=(Button)findViewById(R.id.button);
        new_game=(Button)findViewById(R.id.button2);
        resume=(Button)findViewById(R.id.button3);
        help=(Button)findViewById(R.id.button5);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        new_game.setEnabled(false);
        resume.setEnabled(false);
        submit.setEnabled(false);
        help.setEnabled(false);
        Help=false;
    }

    public void Resume(View view) {

        EditText edit;
        for (int i = 1; i < 10; i++){
            if(!level.contains(i)) {
                String ID = "editText" + (i);
                int resID = getResources().getIdentifier(ID, "id", getPackageName());
                edit = ((EditText) findViewById(resID));
                edit.setText("");
            }
        }
        resume.setEnabled(false);
        submit.setEnabled(true);
    }

    public void Start() {

        Integer[] sum=new Integer[6];
        Random generator = new Random();
        TextView record;
        EditText edittext;

        for(int i=0;i<6;i++)
            sum[i]=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                while (true) {
                    int var = generator.nextInt(9)+1;
                    if (!(numberList.contains(var))) {
                        numberList.add(var);

                        if (i == 0)
                            sum[0] += var ;
                        else if (i == 1)
                            sum[1] += var ;
                        else
                            sum[2] += var ;

                        if (j == 0)
                            sum[3] += var;
                        else if (j == 1)
                            sum[4] += var ;
                        else
                            sum[5] += var ;
                        break;
                    }
                }
            }

        }

        for (int i = 1; i < 7; i++) {
            String ID = "textView" + (i);
            int resID = getResources().getIdentifier(ID, "id", getPackageName());
            record= ((TextView) findViewById(resID));
            record.setText(Integer.toString(sum[i-1]));
        }


        for(int i=0;i<9-Level;i++){
            while(true) {
                int var = generator.nextInt(9) + 1;
                if (!level.contains(var)){
                    level.add(var);
                    String ID = "editText" + (var);
                    int resID = getResources().getIdentifier(ID, "id", getPackageName());
                    edittext = ((EditText) findViewById(resID));
                    edittext.setText(Integer.toString(numberList.get(var-1)));
                    edittext.setEnabled(false);
                    break;
                }
            }
        }

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void NewGame(View view) {

        EditText edit;
        TextView text;
        new_game.setEnabled(false);
        resume.setEnabled(false);
        submit.setEnabled(false);
        EditText edit_level=(EditText)findViewById(R.id.level);
        Button  submit_level=(Button)findViewById(R.id.button6);

        for (int i = 1; i < 10; i++) {
            String ID = "editText" + (i);
            int resID = getResources().getIdentifier(ID, "id", getPackageName());
            edit= ((EditText) findViewById(resID));
            edit.setEnabled(true);
            edit.setText("");
        }

        for (int i = 1; i < 7; i++) {
            String ID = "textView" + (i);
            int resID = getResources().getIdentifier(ID, "id", getPackageName());
            text= ((TextView) findViewById(resID));
            text.setText("X");
        }

        Level=0;
        Help=false;
        edit_level.setEnabled(true);
        edit_level.setText("");
        submit_level.setEnabled(true);
        numberList.clear();
        level.clear();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public void Check(View view) {

        int Int_field;
        EditText edittext ;
        TextView textview ;
        Integer[] sum=new Integer[]{0,0,0,0,0,0};
        Integer[] sum2=new Integer[6];
        List<Integer> numberList2 = new ArrayList<Integer>();
        String String_field;
        Boolean fields_ok=true;
        Boolean number_ok=true;
        Boolean sum_ok=true;
        float points;

        int k=1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String ID = "editText" + (k);
                int resID = getResources().getIdentifier(ID, "id", getPackageName());
                edittext = ((EditText) findViewById(resID));
                String_field=edittext.getText().toString();

                if(!String_field.equals("")) {
                    Int_field = Integer.parseInt(String_field);

                    if(numberList2.contains(Int_field))
                        number_ok=false;
                    else
                        numberList2.add(Int_field);

                    if (i == 0)
                        sum[0] += Int_field;
                    else if (i == 1)
                        sum[1] +=  Int_field;
                    else
                        sum[2] +=  Int_field;

                    if (j == 0)
                        sum[3] +=  Int_field;
                    else if (j == 1)
                        sum[4] +=  Int_field;
                    else
                        sum[5] +=  Int_field;

                    k++;
                }
                else {
                    new AlertDialog.Builder(this).setTitle("Information").setMessage("Fields can not be empty").setNeutralButton("Close", null).show();
                    i=j=3;
                    fields_ok=false;
                }
            }
        }

        if(fields_ok==true && number_ok==false )
            new AlertDialog.Builder(this).setTitle("Information").setMessage("Numbers can not be repeated").setNeutralButton("Close", null).show();

        if(fields_ok==true && number_ok==true) {

            submit.setEnabled(false);

            for (int i = 0; i < 6; i++) {
                String ID = "textView" + (i + 1);
                int resID = getResources().getIdentifier(ID, "id", getPackageName());
                textview = (TextView) findViewById(resID);
                sum2[i] = Integer.parseInt(textview.getText().toString());
            }


            for (int i = 0; i < 6; i++) {
                if (sum[i] != sum2[i]) {
                    sum_ok = false;
                    break;
                }
            }

            if (sum_ok == true) {

                chronometer.stop();
                long seconds = (SystemClock.elapsedRealtime() - chronometer.getBase())/1000;
                points= (float) (((1.0)/seconds)*10000*Level);

                if(Help==false)
                    points+=10;
                String information="Congratulation !\nYour score: "+(int)points;
                new AlertDialog.Builder(this).setTitle("Information").setMessage(information).setNeutralButton("Close", null).show();
                resume.setEnabled(false);
                new_game.setEnabled(true);
                help.setEnabled(false);

            }
            else {
                new AlertDialog.Builder(this).setTitle("Information").setMessage("Sorry! Its bad ").setNeutralButton("Close", null).show();
                resume.setEnabled(true);
                new_game.setEnabled(true);
            }
        }
    }

    public void Help(View view) {

        Random generator = new Random();
        EditText edittext;
        int var;

        while(true){
            var = generator.nextInt(9)+1;
            if(!level.contains(var))
                break;
            }

        String ID = "editText" + (var);
        int resID = getResources().getIdentifier(ID, "id", getPackageName());
        edittext = ((EditText) findViewById(resID));
        edittext.setText(Integer.toString(numberList.get(var-1)));
        edittext.setEnabled(false);
        help.setEnabled(false);
        Help=true;
    }

    public void Level(View view) {

        EditText edit_level=(EditText)findViewById(R.id.level);
        String string_level=edit_level.getText().toString();
        Button  submit_level=(Button)findViewById(R.id.button6);

        if(string_level.equals(""))
            new AlertDialog.Builder(this).setTitle("Information").setMessage("Field can not be empty").setNeutralButton("Close", null).show();
        else{
            Level=Integer.parseInt(string_level);
            if(Level==0)
                new AlertDialog.Builder(this).setTitle("Information").setMessage("Level must be from 1 to 9").setNeutralButton("Close", null).show();
            else{
                help.setEnabled(true);
                submit.setEnabled(true);
                edit_level.setEnabled(false);
                submit_level.setEnabled(false);
                if(Level==1)
                    help.setEnabled(false);

                Start();
            }
        }
    }

    public void Exit(View view) {
        finish();
    }

    public void onResume() {
        super.onResume();
    }
}
