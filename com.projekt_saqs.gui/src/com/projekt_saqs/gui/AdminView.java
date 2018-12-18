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
import javax.swing.tree.DefaultMutableTreeNode;

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
public class AdminView extends JPanel {
    private Alert alert;
    private StationWriterB sw;
    private UpdateStation us;
    private VarianceWriter vw;
    private JButton calculate;
    private Thread t;
    private StationBuilder ss;
    VarianzBerechnung vb;

    public AdminView() {
        this.ss = new StationBuilder();
        this.t = new Thread(this.ss);

        vb = new VarianzBerechnung();

        InfoUpdateModel uim_ss = this.ss;
        InfoUpdateModel uim_vb = vb;
        InfoUpdateInterface uii = new NewStationInfoPresenter(uim_ss, uim_vb);
        this.us = new UpdateStation(uii);
        us.getTarget().setEditable(true);
        us.getActual().setBounds(138, 45, 138, 15);
        us.getLabelActual().setBounds(20, 45, 118, 15);
        us.getTarget().setBounds(138, 30, 138, 15);
        us.getLabelTarget().setBounds(20, 30, 118, 15);
        us.getDate().setBounds(138, 15, 138, 15);
        us.getLabelDate().setBounds(20, 15, 118, 15);
        us.getId().setBounds(138, 0, 138, 15);
        us.getLabelId().setBounds(20, 0, 118, 15);
        us.setBounds(173, 0, 277, 60);

        CurrentStationInterface csi = this.us;

        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Stationen");
        this.sw = new StationWriterB(racine, csi);

        StationWriterInterface swi = this.sw;
        StationListPresenter slp = new StationListPresenter(swi);
        ss.addObserver(slp);

        this.alert = new Alert();
        alert.setBounds(0, 119, 450, 53);
        AlertViewInterface avi = this.alert;
        AlertPresenter ap = new AlertPresenter(avi);
        ss.addObserver(ap);

        this.vw = new VarianceWriter();
        vw.setBounds(193, 60, 257, 20);
        VarianzViewInterface vvi = this.vw;

        USPresenter usp = new USPresenter(vvi);
        vb.addObserver(usp);

        this.t.start();

        this.calculate = new JButton("Berechnung");
        calculate.setBounds(246, 176, 204, 60);
        setLayout(null);

        this.add(alert);

        JScrollPane scrollPane = new JScrollPane(sw);
        scrollPane.setBounds(0, 0, 163, 120);
        this.add(scrollPane);
        //
        this.add(us);
        us.setLayout(null);

        this.add(this.vw);

        this.add(calculate);
        this.calculate.addMouseListener(new MouseListener() {

            @SuppressWarnings("static-access")
            @Override
            public void mouseClicked(MouseEvent e) {
                calculateVariance();
                //                buildDiagram();
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

    public AdminView(StationBuilder n_ss) {
        this.ss = n_ss;
        this.t = new Thread(this.ss);

        vb = new VarianzBerechnung();

        InfoUpdateModel uim_ss = this.ss;
        InfoUpdateModel uim_vb = vb;

        InfoUpdateInterface uii = new NewStationInfoPresenter(uim_ss, uim_vb);
        this.us = new UpdateStation(uii);
        us.getActual().setBounds(138, 45, 138, 15);
        us.getLabelActual().setBounds(20, 45, 118, 15);
        us.getTarget().setBounds(138, 30, 138, 15);
        us.getLabelTarget().setBounds(20, 30, 118, 15);
        us.getDate().setBounds(138, 15, 138, 15);
        us.getLabelDate().setBounds(20, 15, 118, 15);
        us.getId().setBounds(138, 0, 138, 15);
        us.getLabelId().setBounds(20, 0, 118, 15);
        us.setBounds(173, 0, 277, 60);

        CurrentStationInterface csi = this.us;

        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Stationen");
        this.sw = new StationWriterB(racine, csi);

        StationWriterInterface swi = this.sw;
        StationListPresenter slp = new StationListPresenter(swi);
        ss.addObserver(slp);

        this.alert = new Alert();
        AlertViewInterface avi = this.alert;
        AlertPresenter ap = new AlertPresenter(avi);
        ss.addObserver(ap);

        this.vw = new VarianceWriter();
        VarianzViewInterface vvi = this.vw;

        USPresenter usp = new USPresenter(vvi);
        vb.addObserver(usp);

        this.t.start();

        this.calculate = new JButton("Berechnung");
        GridLayout borderLayout = new GridLayout(5, 5);
        this.setLayout(borderLayout);

        this.add(alert);

        this.add(new JScrollPane(sw));

        this.add(us);

        this.add(this.vw);

        this.add(calculate);
        this.calculate.addMouseListener(new MouseListener() {

            @SuppressWarnings("static-access")
            @Override
            public void mouseClicked(MouseEvent e) {
                calculateVariance();
                //                buildDiagram();
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

    public StationWriterB getSW() {
        return this.sw;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public AdminView getThis() {
        return this;
    }

    public StationWriterB getSw() {
        return sw;
    }

    public void setSw(StationWriterB sw) {
        this.sw = sw;
    }

    public UpdateStation getUs() {
        return us;
    }

    public void setUs(UpdateStation us) {
        this.us = us;
    }

    public VarianceWriter getVw() {
        return vw;
    }

    public void setVw(VarianceWriter vw) {
        this.vw = vw;
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
        return ss;
    }

    public void setSs(StationBuilder ss) {
        this.ss = ss;
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
            if (!getThis().us.validateDate()) {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(getThis(),
                        "<html>Bitte, geben Sie ein gültiges Datum ein!<br>gültiges Format: TT.MM.JJJJ<br>Hinweis: Das Jahr muss zwischen 1990 und dem aktuellen Jahr liegen</html>",
                        "Fehler beim Datum", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!getThis().us.validateActual()) {
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(getThis(), "Der aktuelle Wert muss ein gültiger reeller Wert sein!", "Fehler beim aktuellen Wert", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane jop = new JOptionPane();
                    String[] textDateArray = getThis().getUs().getDate().getText().split("\\.");
                    int jour = Integer.parseInt(textDateArray[0]), mois = Integer.parseInt(textDateArray[1]), annee = Integer.parseInt(textDateArray[2]);

                    @SuppressWarnings("deprecation")
                    Date u_date = new Date(annee, mois - 1, jour); //mois - 1 parce que le mois est represente de 0 a 11 dans le system
                    String u_id = getThis().getUs().getId().getText();
                    float u_target = Float.parseFloat(getThis().getUs().getTarget().getText());
                    float u_actual = Float.parseFloat(getThis().getUs().getActual().getText());

                    Info info = new Info(u_id, u_date, u_target, u_actual);

                    getThis().getUs().getUii().getActualInfo(info);
                }
            }
        }
    }

    private void buildDiagram() {
        String categoryAxisLabel = "Target";
        String valueAxisLabel = "Aktueller Wert";
        DefaultCategoryDataset valueDiagram = new DefaultCategoryDataset();
        //        setValueForDiagram(valueDiagram, vb.getVariance(), u_actual, u_target);
        JFreeChart diagramm = ChartFactory.createLineChart("Varianz Diagramm", categoryAxisLabel, valueAxisLabel, valueDiagram);
        CategoryPlot plot = diagramm.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartFrame chartFrm = new ChartFrame("Varianz Diagramm", diagramm);
        chartFrm.setVisible(true);
        chartFrm.setSize(500, 500);
        ChartPanel panelDiag = new ChartPanel(diagramm);
        this.add(panelDiag);
    }

    public void setValueForDiagram(DefaultCategoryDataset valueDiagram, float varianz_value, float aktuellWert, float target) {
        valueDiagram.setValue(target, "Varianz Diagramm", "Target");
        valueDiagram.setValue(aktuellWert, "Varianz Diagramm", "Aktueller Wert");
        valueDiagram.setValue(varianz_value, "Varianz Diagramm", "Varianz");
    }

}
