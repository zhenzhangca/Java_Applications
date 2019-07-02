package ca.jrvs.apps.twitter.example.dto;

import java.util.Date;

public class Financial {
    private Date reportDate;
    private long grossProfit;
    private long costOfRevenue;
    private long operatingRevenue;
    private long totalRevenue;
    private long operatingIncome;
    private long netIncome;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public long getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(long grossProfit) {
        this.grossProfit = grossProfit;
    }

    public long getCostOfRevenue() {
        return costOfRevenue;
    }

    public void setCostOfRevenue(long costOfRevenue) {
        this.costOfRevenue = costOfRevenue;
    }

    public long getOperatingRevenue() {
        return operatingRevenue;
    }

    public void setOperatingRevenue(long operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(long operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }
}
