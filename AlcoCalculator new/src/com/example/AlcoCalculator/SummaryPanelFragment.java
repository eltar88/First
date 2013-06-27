package com.example.AlcoCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Сергей
 * Date: 13.06.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class SummaryPanelFragment extends SherlockFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.summary_panel_fragment, container, false);
        return view;
    }
}