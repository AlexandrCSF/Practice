package sc.vsu.Kotov;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FFT fft = new FFT();
        System.out.println();

        signalCharts(fft);
        spectrumCharts(fft);
    }

    public static void signalCharts(FFT fft) {
        XYSeries initialSeries = new XYSeries("initial signal");
        XYSeries receivedSeries = new XYSeries("received signal");


        for (int i = 1; i < fft.Time; i++) {
            initialSeries.add(i * fft.Delta, fft.func(i* fft.Delta));
            receivedSeries.add(i * fft.Delta, fft.result[i].real);
        }

        XYDataset initialXYDataset = new XYSeriesCollection(initialSeries);
        JFreeChart initialChart = ChartFactory
                .createXYLineChart("Initial signal", "x", "y",
                        initialXYDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        XYDataset receivedXYDataset = new XYSeriesCollection(receivedSeries);
        JFreeChart receivedChart = ChartFactory
                .createXYLineChart("Received Signal", "x", "y",
                        receivedXYDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        JFrame initialSignal =
                new JFrame("Initial Signal");
        initialSignal.getContentPane()
                .add(new ChartPanel(initialChart));
        JFrame receivedSignal = new JFrame("Received Signal");
        receivedSignal.getContentPane().add(new ChartPanel(receivedChart));

        initialSignal.setSize(800, 700);
        receivedSignal.setSize(800, 700);

        receivedSignal.setLocation(400,300);


        initialSignal.show();
        receivedSignal.show();
    }

    public static void spectrumCharts(FFT fft) {
        DefaultCategoryDataset datasetInitial = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetReceived = new DefaultCategoryDataset();

        for (int i = 0; i < fft.N; i++) {
            datasetInitial.addValue((Number) fft.C[i].module(), "Спектр изначального сигнала", i);
            datasetReceived.addValue((Number) fft.receivedSpectrum[i].module(),"Спектр полученного сигнала",i);
        }

        JFreeChart initialSpectrumChart = ChartFactory.createBarChart("Initial signal spectrum", "i", "C", datasetInitial);
        JFrame initialSpectrum = new JFrame("initial Spectrum");
        JFreeChart receivedSpectrumChart = ChartFactory.createBarChart("Received signal spectrum", "i", "C", datasetReceived);
        JFrame receivedSpectrum = new JFrame("Received Spectrum");

        initialSpectrum.getContentPane().add(new ChartPanel(initialSpectrumChart));
        initialSpectrum.setSize(800,700);
        receivedSpectrum.getContentPane().add(new ChartPanel(receivedSpectrumChart));
        receivedSpectrum.setSize(800,700);

        receivedSpectrum.setLocation(1200,0);
        initialSpectrum.setLocation(800,0);

        receivedSpectrum.show();
        initialSpectrum.show();
    }
}
