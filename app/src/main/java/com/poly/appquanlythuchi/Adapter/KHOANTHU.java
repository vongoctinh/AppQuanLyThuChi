package com.poly.appquanlythuchi.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.poly.appquanlythuchi.Model.KhoanThu;
import com.poly.appquanlythuchi.R;
import com.poly.appquanlythuchi.Thu;

import java.util.List;

public class KHOANTHU extends BaseAdapter {
    private Thu context;
    private int layout;
    private  List<KhoanThu> khoanThuList;

    public KHOANTHU(Thu context, int layout, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.layout = layout;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
// Tạo View Holder
    private class  Viewholder{
        TextView tenthunhap, ngaythu, sotien;
        ImageView edit, xoa;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        if ( convertView == null){
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(layout, null);
            convertView = inflater.inflate(layout, null);
            viewholder.tenthunhap = (TextView) convertView.findViewById(R.id.View_tenthu);
            viewholder.ngaythu = (TextView) convertView.findViewById(R.id.View_ngayThu);
            viewholder.sotien = (TextView) convertView.findViewById(R.id.View_sotien);
            viewholder.edit = (ImageView) convertView.findViewById(R.id.Chinhsua);
            viewholder.xoa = (ImageView) convertView.findViewById(R.id.Xoa);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        final KhoanThu khoanThu = khoanThuList.get(position);
        viewholder.tenthunhap.setText(khoanThu.getTenthu());
        viewholder.ngaythu.setText(khoanThu.getNgaythu());
        viewholder.sotien.setText(Integer.valueOf(khoanThu.getSotien()));

//        Bắc sự kiện Update
        viewholder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogUpdet(khoanThu.getId(), khoanThu.getTenthu(), khoanThu.getNgaythu(), khoanThu.getSotien());
            }
        });
        viewholder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DelectData(khoanThu.getId(), khoanThu.getTenthu(), khoanThu.getNgaythu(), khoanThu.getSotien());
            }
        });
        return convertView;
    }
}
