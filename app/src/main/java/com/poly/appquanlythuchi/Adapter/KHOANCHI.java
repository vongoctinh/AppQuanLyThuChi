package com.poly.appquanlythuchi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.appquanlythuchi.Chi;
import com.poly.appquanlythuchi.Model.KhoanChi;
import com.poly.appquanlythuchi.R;

import java.util.List;

public class KHOANCHI extends BaseAdapter {
    Chi context;
    int layout;
    List<KhoanChi> khoanChiList;

    public KHOANCHI(Chi context, int layout, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.layout = layout;
        this.khoanChiList = khoanChiList;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView Khoanchi, Ngaychi, Sotien;
        ImageView delete, edit;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if ( convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.Khoanchi = convertView.findViewById(R.id.View_tenchi);
            holder.Ngaychi = convertView.findViewById(R.id.View_ngaychi);
            holder.Sotien = convertView.findViewById(R.id.View_sotien);
            holder.delete = convertView.findViewById(R.id.delete);
            holder.edit = convertView.findViewById(R.id.edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final KhoanChi khoanChi= khoanChiList.get(position);
        holder.Khoanchi.setText(khoanChi.getKhoanchi());
        holder.Ngaychi.setText(khoanChi.getNgaychi());
        holder.Sotien.setText(khoanChi.getSotien());
//      bắt sự kiện update xóa
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Bạn có muống xóa không ",Toast.LENGTH_SHORT).show();
            context.DeleteData(khoanChi.getId(), khoanChi.getKhoanchi(), khoanChi.getNgaychi(), khoanChi.getSotien() );
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText( context, "Thay đổi", Toast.LENGTH_SHORT).show();
                context.EditData(khoanChi.getId(), khoanChi.getKhoanchi(), khoanChi.getNgaychi(), khoanChi.getSotien());
            }
        });
        return convertView;
    }
}
