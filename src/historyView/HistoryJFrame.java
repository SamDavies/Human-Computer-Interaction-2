/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package historyView;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;

import models.Model;
import models.ModelSingleton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utility.Patient;
import utility.PatientData;

import javax.swing.*;

/**
 *
 * @author samdavies
 */
public class HistoryJFrame extends javax.swing.JFrame {

    private Model model = ModelSingleton.getInstance();

    /**
     * Creates new form HistoryJFrame
     */
    public HistoryJFrame() {
        initComponents();
        //set window in the center of the screen
        //Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        //Move the window
        this.setLocation(x, y);

        XYSeries breathingSeries = new XYSeries("Breathing Rate");
        XYSeries oxygenSeries = new XYSeries("Oxygen");
        XYSeries temperatureSeries = new XYSeries("Temperature");
        XYSeries bloodPressureSeries = new XYSeries("Blood Pressure");
        XYSeries heartRateSeries = new XYSeries("Heart Rate");

        // add date to all the series
        for (Map.Entry<String, PatientData> entry : model.getSelectedPatient().data.entrySet()) {
            breathingSeries.add(Double.parseDouble(entry.getKey()), entry.getValue().getBreathing());
            oxygenSeries.add(Double.parseDouble(entry.getKey()), entry.getValue().getOxygen());
            temperatureSeries.add(Double.parseDouble(entry.getKey()), entry.getValue().getTemperature());
            bloodPressureSeries.add(Double.parseDouble(entry.getKey()), entry.getValue().getBloodPressure());
            heartRateSeries.add(Double.parseDouble(entry.getKey()), entry.getValue().getHeartRate());
        }

        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(breathingSeries);
        dataset.addSeries(oxygenSeries);
        dataset.addSeries(temperatureSeries);
        dataset.addSeries(bloodPressureSeries);
        dataset.addSeries(heartRateSeries);

        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Chart", // Title
                "time(s)", // x-axis Label
                "Rate", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        this.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        chart.getPlot().setBackgroundAlpha(1);
        chart.getPlot().setBackgroundPaint(Color.BLUE);

        HistoryButtonsJPanel buttonPanel = new HistoryButtonsJPanel();

        ChartUtilities.applyCurrentTheme(chart);
        this.add(CP, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(66, 66, 66));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
