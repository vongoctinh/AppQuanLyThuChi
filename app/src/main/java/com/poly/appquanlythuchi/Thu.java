package com.poly.appquanlythuchi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.poly.appquanlythuchi.Adapter.KHOANTHU;
import com.poly.appquanlythuchi.Model.KhoanThu;
import com.poly.appquanlythuchi.database.DataThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Thu extends AppCompatActivity {
    DataThu dataThu;
    ArrayList<KhoanThu> arrayKhoanThu;
    KHOANTHU khoanthuAdapter;
    ListView ViewTHu;
    ImageView btn_addThu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu);
        ViewTHu = (ListView) findViewById( R.id.ViewThu);
        arrayKhoanThu = new ArrayList<>();
        khoanthuAdapter = new KHOANTHU(this, R.layout.thu_item_row, arrayKhoanThu);
        ViewTHu.setAdapter(khoanthuAdapter);
        dataThu = new DataThu( Thu.this, "Ghichu.sqlite", null,1);
        dataThu.QueryData( "CREATE TABLE IF NOT EXISTS ThuNhap(" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " TenKhoanThu NVARCHAR(200)," +
                " NgayThu VARCHAR," +
                " SoTien INTEGER)");
        ViewTHu = (ListView) findViewById(R.id.ViewThu);
        btn_addThu = (ImageView) findViewById(R.id.btn_addThu);
         btn_addThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Thu.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addthu);
                dialog.setTitle("Them khoản thu");
                final EditText tenThunhap = dialog.findViewById(R.id.edt_tenthunhap);
                final EditText ngayThu = dialog.findViewById(R.id.edt_ngaythu);
                final EditText soTien = dialog.findViewById(R.id.edt_sotien);
                Button btnLuu = dialog.findViewById(R.id.btn_Luu);
                Button btnDOng = dialog.findViewById(R.id.btn_dong);
                Button btnXoatrang = dialog.findViewById(R.id.btn_lammoi);
                dialog.setCancelable(false);
                dialog.show();
                ngayThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DATE);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Thu.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                calendar.set(year, month, dayOfMonth);
                                ngayThu.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean thuchien = true;
                        String tekhoanthu = tenThunhap.getText().toString();
                        if (tekhoanthu.equals("")){
                            Toast.makeText(Thu.this, " Hãy nhập tên khoản thu", Toast.LENGTH_SHORT).show();
                            thuchien = false;
                        }
                        String ngaythu = ngayThu.getText().toString();
                        if ( ngayThu.equals("")){
                            Toast.makeText(Thu.this, " Hãy nhập ngày thu", Toast.LENGTH_SHORT).show();
                            thuchien = false;
                        }
                        Integer sotien = Integer.valueOf(soTien.getText().toString());
                        if ( soTien.equals("")){
                            Toast.makeText(Thu.this, " Yêu cầu nhập số tiền", Toast.LENGTH_SHORT).show();
                            thuchien = false;
                        }
                        if (Integer.valueOf(soTien.getText().toString()) > 10000){
//                        String tenkhoanthu = tenThunhap.getText().toString();
//                        String ngaythu = ngayThu.getText().toString();
//                        Integer sotien = Integer.valueOf(soTien.getText().toString());
//                        if (tenkhoanthu.equals("")| ngaythu.equals("")| sotien.equals("")){
//                            Toast.makeText(Thu.this, "Mời bạn nhập lại",Toast.LENGTH_SHORT).show();
//                        } else  {
//                            dataThu.QueryData("INSERT INTO  ThuNhap VALUES(null, '"+tenkhoanthu+"', ' "+ngaythu+"','"+sotien+"')");
//                            Toast.makeText(Thu.this, " Đã them ", Toast.LENGTH_SHORT).show();
//                            dialog.cancel();
//                            GetData();
//                        }
//                    }
//                });
                btnXoatrang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tenThunhap.setText("");
                        ngayThu.setText("");
                        soTien.setText("");
                    }
                });
                btnDOng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
    }
      public void GetData(){
        Cursor dataThuNhap = dataThu.GetData("SELECT * FROM ThuNhap");
        arrayKhoanThu.clear();
        while (dataThuNhap.moveToNext()){
            String thunhap = dataThuNhap.getString(1);
            int id = dataThuNhap.getInt(0);
            String ngaythu = dataThuNhap.getString(2);
            String sotien = dataThuNhap.getString(3);
            arrayKhoanThu.add( new KhoanThu(id, thunhap, ngaythu, sotien));
        }
        khoanthuAdapter.notifyDataSetChanged();
    }
    public void DialogUpdet(final int id,String thunhap, String ngaythu, String Sotien){
        final Dialog dialog = new Dialog(Thu.this);
        dialog.setContentView(R.layout.dialog_addthu);
        dialog.setTitle("Update dữ liệu");
        final EditText updetThunhap = dialog.findViewById(R.id.edt_tenthunhap);
        final EditText updetNgaythu = dialog.findViewById(R.id.edt_ngaythu);
        final EditText updetSotien = dialog.findViewById(R.id.edt_sotien);
        final Button updetcane = dialog.findViewById(R.id.btn_dong);
        final Button updetluu = dialog.findViewById(R.id.btn_Luu);
        final Button updetclia = dialog.findViewById(R.id.btn_lammoi);
        updetNgaythu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Thu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        calendar.set(year, month, dayOfMonth);
                        updetNgaythu.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        updetluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = updetThunhap.getText().toString();
                String ngaymoi = updetNgaythu.getText().toString();
                String tienmoi = updetSotien.getText().toString();
                if ( tenmoi.equals("")| ngaymoi.equals("")| tienmoi.equals("")){
                    Toast.makeText(Thu.this, " Yêu cầu điền thông tin", Toast.LENGTH_SHORT).show();
                } else {
                dataThu.QueryData("UPDATE ThuNhap SET TenKhoanThu = '"+tenmoi+"', NgayThu = '"+ngaymoi+"', SoTien = '"+tienmoi+"' WHERE ID = '"+id+"' ");
               dialog.cancel();
                GetData();
                }
            }
        });
        updetcane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        updetclia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updetThunhap.setText("");
                updetNgaythu.setText("");
                updetSotien.setText("");
            }
        });
        dialog.show();
    }
public void DelectData( final int id,String thunhap, String ngaythu, String Sotien){
    final AlertDialog.Builder builder = new AlertDialog.Builder(Thu.this);
    builder.setMessage("Bạn có muống xóa mục này");
    builder.setPositiveButton("Đồng ý",  new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dataThu.QueryData("DELETE FROM ThuNhap WHERE ID = '"+id+"'");
            GetData();
        }
    });
    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
    builder.show();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     int id = item.getItemId();
      if (id == R.id.back) {
          Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);
       }
        return super.onOptionsItemSelected(item);
  }
}
