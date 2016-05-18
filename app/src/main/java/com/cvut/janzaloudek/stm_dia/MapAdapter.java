package com.cvut.janzaloudek.stm_dia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by janzaloudek on 18/05/16.
 */
public abstract class MapAdapter<T> extends BaseAdapter {
    protected final ArrayList<Map.Entry<String, T>> mData;

    public MapAdapter(Map<String, T> map) {
        mData = new ArrayList<Map.Entry<String, T>>();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, T> getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getValue().hashCode();
    }
}
