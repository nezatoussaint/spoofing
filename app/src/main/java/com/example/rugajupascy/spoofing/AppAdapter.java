package com.example.rugajupascy.spoofing;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo>{
    private  List<ApplicationInfo> appList= null;
    private  Context context;
    private PackageManager packageManager;
    public AppAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public AppAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AppAdapter(@NonNull Context context, int resource, @NonNull ApplicationInfo[] objects) {
        super(context, resource, objects);
    }

    public AppAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ApplicationInfo[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AppAdapter(@NonNull Context context, int resource, @NonNull List<ApplicationInfo> objects) {
        super(context, resource, objects);
        this.context =context;
        this.appList = objects;
        packageManager = context.getPackageManager();
    }

    public AppAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ApplicationInfo> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public int getCount() {
        return ((null !=appList )?appList.size():0);
    }

    @Nullable
    @Override
    public ApplicationInfo getItem(int position) {
        return ((null!=appList)?appList.get(position):null);
    }

    @Override
    public long getItemId(int position) {
       return  position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =convertView;
        if(null == view){
            LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item,null);
        }
        ApplicationInfo data = appList.get(position);
        if(null!= data){
            TextView appName= (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_package);
            ImageView iconView = (ImageView) view.findViewById(R.id.appicon);

            appName.setText(data.loadLabel(packageManager));
            packageName.setText(data.packageName);
            iconView.setImageDrawable(data.loadIcon(packageManager));
        }
        return view;
    }
}
