package home.acc.JSF;


import home.acc.AccEJB;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//@ManagedBean
//@RequestScoped
@Named
@SessionScoped
public class ChartView implements Serializable {

    @Inject
    private AccEJB accEJB;
    private PieChartModel pieModel;
    private Date reportDate;

    @PostConstruct
    public void init() {
        createPieModel();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void createPieModel() {
        pieModel = new PieChartModel();

        List<Object[]> list = accEJB.getReport(reportDate);

        if (list.size() > 0) {
            list.forEach(element -> {
                pieModel.set((String) element[0], (Double) element[1]);
            });

            //
            // pieModel.setTitle("Simple Pie");
            pieModel.setLegendPosition("e");
            pieModel.setShadow(true);
            pieModel.setShowDataLabels(true);
        }
        else pieModel.setTitle("No data");
    }
}