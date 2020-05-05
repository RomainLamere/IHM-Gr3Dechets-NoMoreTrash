package com.example.nomoretrash.statistiques.diagram;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.nomoretrash.R;
import com.example.nomoretrash.signalements.signaler.DescriptionFragment;
import com.example.nomoretrash.statistiques.StatistiquesActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DiagramCircle extends DiagramFragment {


    private static final int[] MY_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53), Color.rgb(20, 90, 150)};


    public DiagramCircle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diagram_circle, container, false);

        if (getyData() != null) {
            setUpDiagramCircle(v);
        }

        return v;
    }

    private void setUpDiagramCircle(View v) {
        //Mise en forme des données
        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < getyData().length; i++) {
            pieEntryList.add(new PieEntry(getyData()[i], getxData()[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntryList, "");

        PieData pieData = new PieData(dataSet);
        dataSet.setColors(MY_COLORS);

        //Création du graph
        PieChart pieChart = v.findViewById(R.id.pieChart1);
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setEntryLabelTextSize(18);
        pieChart.animateY(1000);
        pieChart.setNoDataText("Pas encore de statistiques, effectuez d'autres signalements pour en avoir !");
        pieChart.setNoDataTextColor(Color.BLACK);

        pieChart.invalidate();
    }

}