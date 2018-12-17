package com.projekt_saqs.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import com.projekt_saqs.model.InfoUpdateModel;
import com.projekt_saqs.model.stationBuilder.StationBuilder;
import com.projekt_saqs.model.varianzberechnung.VarianzBerechnung;
import com.projekt_saqs.presenter.Info;
import com.projekt_saqs.presenter.InfoUpdateInterface;
import com.projekt_saqs.presenter.ActualStationInfoPresenter.NewStationInfoPresenter;
import com.projekt_saqs.presenter.StationListPresenter.StationListPresenter;
import com.projekt_saqs.presenter.alertpresenter.AlertPresenter;
import com.projekt_saqs.presenter.uspresenter.USPresenter;

@SuppressWarnings("serial")
public class MainView extends JPanel {
    private Alert message;
    private StationWriter stationWriter;
    private UpdateStation updateSation;
    private VarianceWriter varianzWriter;
    private JButton calculate;
    private Thread t;
    private StationBuilder stationBuilder; //StationSet
    private VarianzBerechnung vb;
    private float u_actual;
    private float u_target;
    private ChartPanel panelDiag = null;
    ChartFrame chartFrm;

    public MainView() {
        this.stationBuilder = new StationBuilder();
        this.t = new Thread(this.stationBuilder);

        vb = new VarianzBerechnung();

        InfoUpdateModel uim_ss = this.stationBuilder;
        InfoUpdateModel uim_vb = vb;

        InfoUpdateInterface uii = new NewStationInfoPresenter(uim_ss, uim_vb);
        this.updateSation = new UpdateStation(uii);
        updateSation.getActual().setBounds(164, 45, 175, 15);
        updateSation.getLabelActual().setBounds(50, 45, 114, 15);
        updateSation.getTarget().setBounds(164, 30, 175, 15);
        updateSation.getLabelTarget().setBounds(50, 31, 114, 15);
        updateSation.getDate().setBounds(164, 15, 175, 15);
        updateSation.getLabelDate().setBounds(50, 15, 114, 15);
        updateSation.getId().setBounds(164, 0, 175, 15);
        updateSation.getLabelId().setBounds(50, 0, 114, 15);
        updateSation.setBounds(299, 0, 339, 60);

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateVariance();
                buildDiagram();
                // TODO to be removed

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateVariance();
                buildDiagram();
                // TODO to be removed

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateVariance();
                buildDiagram();

            }
        };

        updateSation.actual.getDocument().addDocumentListener(dl);
        CurrentStationInterface currentStation = this.updateSation;

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.stationWriter = new StationWriter(model, currentStation);

        StationWriterInterface swi = this.stationWriter;
        StationListPresenter slp = new StationListPresenter(swi);
        stationBuilder.addObserver(slp);

        this.message = new Alert();
        message.setBounds(349, 81, 289, 50);
        AlertViewInterface avi = this.message;
        AlertPresenter ap = new AlertPresenter(avi);
        stationBuilder.addObserver(ap);

        this.varianzWriter = new VarianceWriter();
        varianzWriter.setBounds(349, 60, 289, 20);
        VarianzViewInterface vvi = this.varianzWriter;

        USPresenter usp = new USPresenter(vvi);
        vb.addObserver(usp);

        this.t.start();

        this.calculate = new JButton("Varianz berechnen");
        this.calculate.setBounds(462, 240, 176, 60);
        setLayout(null);
        this.add(updateSation);
        updateSation.setLayout(null);
        this.add(this.varianzWriter);
        JScrollPane scrollPane = new JScrollPane(stationWriter);
        scrollPane.setBounds(0, 0, 299, 242);
        this.add(scrollPane);
        this.add(message);
        this.add(calculate);
        this.calculate.addMouseListener(new MouseListener() {

            @SuppressWarnings("static-access")
            @Override
            public void mouseClicked(MouseEvent e) {
                calculateVariance();
                buildDiagram();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
    }

    public MainView(StationBuilder n_ss) {
        this.stationBuilder = n_ss;
        this.t = new Thread(this.stationBuilder);

        vb = new VarianzBerechnung();

        InfoUpdateModel uim_ss = this.stationBuilder;
        InfoUpdateModel uim_vb = vb;

        InfoUpdateInterface uii = new NewStationInfoPresenter(uim_ss, uim_vb);
        this.updateSation = new UpdateStation(uii);
        updateSation.getActual().setBounds(164, 45, 175, 15);
        updateSation.getLabelActual().setBounds(50, 45, 114, 15);
        updateSation.getTarget().setBounds(164, 30, 175, 15);
        updateSation.getLabelTarget().setBounds(50, 31, 114, 15);
        updateSation.getDate().setBounds(164, 15, 175, 15);
        updateSation.getLabelDate().setBounds(50, 15, 114, 15);
        updateSation.getId().setBounds(164, 0, 175, 15);
        updateSation.getLabelId().setBounds(50, 0, 114, 15);
        updateSation.setBounds(299, 0, 339, 60);



        CurrentStationInterface csi = this.updateSation;

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.stationWriter = new StationWriter(model, csi);

        StationWriterInterface swi = this.stationWriter;
        StationListPresenter slp = new StationListPresenter(swi);
        stationBuilder.addObserver(slp);

        this.message = new Alert();
        AlertViewInterface avi = this.message;
        AlertPresenter ap = new AlertPresenter(avi);
        stationBuilder.addObserver(ap);

        this.varianzWriter = new VarianceWriter();
        VarianzViewInterface vvi = this.varianzWriter;

        USPresenter usp = new USPresenter(vvi);
        vb.addObserver(usp);

        this.t.start();

        this.calculate = new JButton("Berechnung");
        GridLayout gridLayout = new GridLayout(5, 2);
        this.setLayout(gridLayout);
        this.add(updateSation);
        this.add(this.varianzWriter);
        this.add(new JScrollPane(stationWriter));
        this.add(message);
        this.add(calculate);
        this.calculate.addMouseListener(new MouseListener() {

            @SuppressWarnings("static-access")
            @Override
            public void mouseClicked(MouseEvent e) {
                calculateVariance();
                buildDiagram();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
    }

    public StationWriter getSW() {
        return this.stationWriter;
    }

    public Alert getAlert() {
        return message;
    }

    public void setAlert(Alert alert) {
        this.message = alert;
    }

    public MainView getThis() {
        return this;
    }

    public StationWriter getSw() {
        return stationWriter;
    }

    public void setSw(StationWriter sw) {
        this.stationWriter = sw;
    }

    public UpdateStation getUs() {
        return updateSation;
    }

    public void setUs(UpdateStation us) {
        this.updateSation = us;
    }

    public VarianceWriter getVw() {
        return varianzWriter;
    }

    public void setVw(VarianceWriter vw) {
        this.varianzWriter = vw;
    }

    public JButton getCalculate() {
        return calculate;
    }

    public void setCalculate(JButton calculate) {
        this.calculate = calculate;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public StationBuilder getSs() {
        return stationBuilder;
    }

    public void setSs(StationBuilder ss) {
        this.stationBuilder = ss;
    }

    /**
     * 
     */
    private void calculateVariance() {
        if (getThis().getUs().getDate().getText().isEmpty() || getThis().getUs().getId().getText().isEmpty() || getThis().getUs().getTarget().getText().isEmpty()
                || getThis().getUs().getActual().getText().isEmpty()) {

            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(getThis(), "Bitte, füllen Sie das Formular komplet aus!", "Fehler", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!getThis().updateSation.validateDate()) {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(getThis(), "<html>Bitte, geben Sie ein gültiges Datum ein!<br>gültiges Format: TT.MM.JJJJ<br></html>", "Fehler beim Datum", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!getThis().updateSation.validateActual()) {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(getThis(), "Der aktuelle Wert muss ein gültiger reeller Wert sein!", "Fehler beim aktuellen Wert", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] textDateArray = getThis().getUs().getDate().getText().split("\\.");
                    int jour = Integer.parseInt(textDateArray[0]), mois = Integer.parseInt(textDateArray[1]), annee = Integer.parseInt(textDateArray[2]);

                    @SuppressWarnings("deprecation")
                    Date u_date = new Date(annee, mois - 1, jour);
                    String u_id = getThis().getUs().getId().getText();
                    u_target = Float.parseFloat(getThis().getUs().getTarget().getText());
                    u_actual = Float.parseFloat(getThis().getUs().getActual().getText());

                    Info info = new Info(u_id, u_date, u_target, u_actual);

                    getThis().getUs().getUii().getActualInfo(info);
                }
            }
        }
    }

    private void buildDiagram() {

        if (panelDiag != null) {
            panelDiag.removeAll();
            panelDiag.revalidate(); // This removes the old chart 
        }

        String categoryAxisLabel = "Target";
        String valueAxisLabel = "Aktueller Wert";
        DefaultCategoryDataset valueDiagram = new DefaultCategoryDataset();
        setValueForDiagram(valueDiagram, vb.getVariance(), u_actual, u_target);
        JFreeChart diagramm = ChartFactory.createLineChart("Varianz Diagramm", categoryAxisLabel, valueAxisLabel, valueDiagram);
        CategoryPlot plot = diagramm.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        chartFrm = new ChartFrame("Varianz Diagramm", diagramm);
        
        chartFrm.setVisible(true);
        chartFrm.setSize(500, 500);
        panelDiag = new ChartPanel(diagramm);
        panelDiag.repaint();

        this.add(panelDiag);
    }

    public void setValueForDiagram(DefaultCategoryDataset valueDiagram, float varianz_value, float aktuellWert, float target) {
        valueDiagram.setValue(target, "Varianz Diagramm", "Target");
        valueDiagram.setValue(aktuellWert, "Varianz Diagramm", "Aktueller Wert");
        valueDiagram.setValue(varianz_value, "Varianz Diagramm", "Varianz");
    }
}
