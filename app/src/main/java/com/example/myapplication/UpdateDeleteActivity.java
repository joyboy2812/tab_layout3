package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.dal.SQLiteHelper;
import com.example.myapplication.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eName, eDate;
    private RadioGroup rgOption1, rgOption2;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    private CheckBox cb1, cb2, cb3;
    private Button btUpdate, btCancel, btDelete;
    private Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        eName.setText(item.getName());
        eDate.setText(item.getDate());
        String[] values = item.getService().split(";");
        for (String value : values) {
            System.out.println(value);
            if (value.trim().equalsIgnoreCase("Hut thuoc")) {
                cb1.setChecked(true);
            }if (value.trim().equalsIgnoreCase("an sang")) {
                cb2.setChecked(true);
            }if (value.trim().equalsIgnoreCase("ca phe")) {
                cb3.setChecked(true);
            }
        }
        if (item.getKh().equalsIgnoreCase("Ha noi")){
            rb1.setChecked(true);
        } else if (item.getKh().equalsIgnoreCase("Da Nang")) {
            rb2.setChecked(true);
        } else if (item.getKh().equalsIgnoreCase("TP HCM")) {
            rb3.setChecked(true);
        } else if (item.getKh().equalsIgnoreCase("Hue")) {
            rb4.setChecked(true);
        }
        if (item.getHl().equalsIgnoreCase("co")){
            rb5.setChecked(true);
        } else if (item.getKh().equalsIgnoreCase("khong")) {
            rb6.setChecked(true);
        }
    }

    private void initView(){
        eName = findViewById(R.id.eName);
        eDate = findViewById(R.id.eDate);
        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.UpdateBt);
        btDelete = findViewById(R.id.btDelete);
        rgOption1 = findViewById(R.id.radio_group1);
        rgOption2 = findViewById(R.id.radio_group2);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        rb5 = findViewById(R.id.option5);
        rb6 = findViewById(R.id.option6);
        cb1 = findViewById(R.id.checkbox1);
        cb2 = findViewById(R.id.checkbox2);
        cb3 = findViewById(R.id.checkbox3);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(view==eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    if(month > 8 ){
                        date = dayOfMonth + "/" + (month+1) + "/" + year;
                    }else{
                        date = dayOfMonth + "/0" + (month+1) + "/" + year;

                    }
                    eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == btCancel){
            finish();
        }
        if(view == btDelete){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có chắc muốn xóa " + item.getName()+" không?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.deleteItem(id);
                    finish();
                }

            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(view == btUpdate){
            int id =item.getId();
            String name = eName.getText().toString();
            String d = eDate.getText().toString();
            String kh = "";
            if (rb1.isChecked()){
                kh = rb1.getText().toString();
            }else if (rb2.isChecked()){
                kh = rb2.getText().toString();
            }else if (rb3.isChecked()){
                kh = rb3.getText().toString();
            }else if (rb4.isChecked()){
                kh = rb4.getText().toString();
            }
            String hl = "";
            if (rb5.isChecked()){
                hl = rb5.getText().toString();
            }else if (rb6.isChecked()){
                hl = rb6.getText().toString();
            }
            StringBuilder sb = new StringBuilder();
            if (cb1.isChecked()){
                sb.append(cb1.getText().toString() + "; ");
            }
            if (cb2.isChecked()){
                sb.append(cb2.getText().toString() + "; ");
            }
            if (cb3.isChecked()){
                sb.append(cb3.getText().toString() + "; ");
            }
            String s = sb.toString();
            if(!name.isEmpty()){
                Item i = new Item(id,name,kh,d,hl,s);
                db.updateItem(i);
                finish();
            }
        }
    }
}