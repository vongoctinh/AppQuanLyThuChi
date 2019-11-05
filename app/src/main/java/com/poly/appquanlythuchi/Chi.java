package com.poly.appquanlythuchi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import com.poly.appquanlythuchi.Adapter.KHOANCHI;
import com.poly.appquanlythuchi.Model.KhoanChi;
import com.poly.appquanlythuchi.database.DataChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class Chi extends AppCompatActivity {
 ImageView btn_addChi;
 ListView ViewChi;
 DataChi dataChi;
 ArrayList<KhoanChi> arrayKhoaChi;
 KHOANCHI adapterKhoanChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi);
        btn_addChi = (ImageView) findViewById(R.id.btn_addChi);
        ViewChi = (ListView) findViewById(R.id.ViewChi);
        arrayKhoaChi = new ArrayList<>();
        adapterKhoanChi = new KHOANCHI(Chi.this, R.layout.chi_item_row, arrayKhoaChi);
        ViewChi.setAdapter(adapterKhoanChi);

//        Tạo database ghi chú
        dataChi = new DataChi(this, "KhoanThu.sqlite", null,1);
//        Tạo bảng Khoản chi
        dataChi.Querydata("CREATE TABLE IF NOT EXISTS ChiTieu(" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " KhoanChi NVARCHAR(200)," +
                " Ngaychi VARCHAR," +
                " TienChi VARCHAR)");

      Getdata();
//        them dữ liệu vào bản
//        dataChi.Querydata("INSERT INTO ChiTieu VALUSE(' null ', 'asdhsd', '12/1/2019', '20000')");
// lấy dữ liệu từ bản ra màng hình
//        Cursor data = dataChi.GetData("SELECT * FROM ChiTieu");
//        while (data.moveToNext()){
//            int id = data.getInt(0);
//            String ten = data.getString(1);
//            String ngay = data.getString(2);
//            String tien = data.getString(3);
//            arrayKhoaChi.add(  new KhoanChi(id, ten, ngay, tien));
//        }
//        adapterKhoanChi.notifyDataSetChanged();



        btn_addChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Chi.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addchi);
                final EditText tenKhoanchi = dialog.findViewById(R.id.edt_tenkhoanchi);
                final EditText ngayChi = dialog.findViewById(R.id.edt_ngaychi);
                final EditText soTien = dialog.findViewById(R.id.edt_sotien);
                Button btnLuu = dialog.findViewById(R.id.btn_Luu);
                Button btnDOng = dialog.findViewById(R.id.btn_dong);
                Button btnXoatrang = dialog.findViewById(R.id.btn_lammoi);
                dialog.setCancelable(false);
                dialog.show();
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText nkhoanchi = dialog.findViewById(R.id.edt_tenkhoanchi);
                        final EditText nngaychi = dialog.findViewById(R.id.edt_ngaychi);
                        final EditText nsotien = dialog.findViewById(R.id.edt_sotien);
                                String tenkhoanchi = nkhoanchi.getText().toString();
                                String ngaychi = nngaychi.getText().toString();
                                String sotien = nsotien.getText().toString();
                                if (tenKhoanchi.equals("")| ngayChi.equals("")|sotien.equals("")){
                                    Toast.makeText(Chi.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                                } else {
                                    dataChi.Querydata("INSERT INTO ChiTieu VALUES(null, '"+tenkhoanchi+"', '"+ngaychi+"', '"+sotien+"')");
                                    dialog.cancel();
                                    Getdata();
                                }
                    }
                });
                btnXoatrang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tenKhoanchi.setText("");
                        ngayChi.setText("");
                        soTien.setText("");
                    }
                });
                btnDOng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                ngayChi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DATE);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Chi.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            calendar.set(year, month, dayOfMonth);
                            ngayChi.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });
            }
        });
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
    public void Getdata(){
      //  Cursor laydulieu = dataChi.GetData("")
        Cursor data = dataChi.GetData("SELECT * FROM ChiTieu");
        arrayKhoaChi.clear();
        while (data.moveToNext()){
            int id = data.getInt(0);
            String ten = data.getString(1);
            String ngay = data.getString(2);
            String tien = data.getString(3);
            arrayKhoaChi.add(  new KhoanChi(id, ten, ngay, tien));
        }
        adapterKhoanChi.notifyDataSetChanged();
    }
    public void DeleteData( final int id, String khoanchi, String ngaychi, String sotien){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Chi.this);
        builder.setMessage("Bạn có muống xóa mục này");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataChi.Querydata("DELETE FROM ChiTieu WHERE ID ='"+id+"'");
                Getdata();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }); builder.show();
    }
    public void EditData(final int id, String khoanchi, String ngaychi, String sotien){
        final Dialog dialog = new Dialog(Chi.this);
        dialog.setContentView(R.layout.dialog_addchi);
        dialog.setTitle("Update dữ liệu");
        final EditText updateChi = dialog.findViewById(R.id.edt_tenkhoanchi);
        final EditText updateNgaychi = dialog.findViewById(R.id.edt_ngaychi);
        final EditText updateSotien = dialog.findViewById(R.id.edt_sotien);
        final Button saveupdate = dialog.findViewById(R.id.btn_Luu);
        final Button cleaupdate = dialog.findViewById(R.id.btn_lammoi);
        final Button caneupdte = dialog.findViewById(R.id.btn_dong);
        updateNgaychi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog= new DatePickerDialog(Chi.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        calendar.set( year, month, dayOfMonth);
                        updateNgaychi.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
        saveupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = updateChi.getText().toString();
                String ngaymoi = updateNgaychi.getText().toString();
                String tienmoi = updateSotien.getText().toString();
                if ( tenmoi.equals("")| ngaymoi.equals("")| tienmoi.equals("")){
                    Toast.makeText(Chi.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    dataChi.Querydata("UPDATE ChiTieu SET KhoanChi = '"+tenmoi+"' , NgayChi = '"+ngaymoi+"' , TienChi = '"+tienmoi+"'");
                    dialog.cancel();
                    Getdata();
                }
            }
        });
        caneupdte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        cleaupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChi.setText("");
                updateNgaychi.setText("");
                updateSotien.setText("");
            }
        });
        dialog.show();
    }
}
