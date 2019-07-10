package ca.jrvs.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Financial {
    @JsonProperty("reportDate")
    private String reportDate;
    @JsonProperty("grossProfit")
    private BigInteger grossProfit;
    @JsonProperty("costOfRevenue")
    private BigInteger costOfRevenue;
    @JsonProperty("operatingRevenue")
    private BigInteger operatingRevenue;
    @JsonProperty("totalRevenue")
    private BigInteger totalRevenue;
    @JsonProperty("operatingIncome")
    private BigInteger operatingIncome;
    @JsonProperty("netIncome")
    private BigInteger netIncome;

    @JsonProperty("reportDate")
    public String getReportDate() {
        return reportDate;
    }

    @JsonProperty("reportDate")
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    @JsonProperty("grossProfit")
    public BigInteger getGrossProfit() {
        return grossProfit;
    }

    @JsonProperty("grossProfit")
    public void setGrossProfit(BigInteger grossProfit) {
        this.grossProfit = grossProfit;
    }

    @JsonProperty("costOfRevenue")
    public BigInteger getCostOfRevenue() {
        return costOfRevenue;
    }

    @JsonProperty("costOfRevenue")
    public void setCostOfRevenue(BigInteger costOfRevenue) {
        this.costOfRevenue = costOfRevenue;
    }

    @JsonProperty("operatingRevenue")
    public BigInteger getOperatingRevenue() {
        return operatingRevenue;
    }

    @JsonProperty("operatingRevenue")
    public void setOperatingRevenue(BigInteger operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    @JsonProperty("totalRevenue")
    public BigInteger getTotalRevenue() {
        return totalRevenue;
    }

    @JsonProperty("totalRevenue")
    public void setTotalRevenue(BigInteger totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @JsonProperty("operatingIncome")
    public BigInteger getOperatingIncome() {
        return operatingIncome;
    }

    @JsonProperty("operatingIncome")
    public void setOperatingIncome(BigInteger operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    @JsonProperty("netIncome")
    public BigInteger getNetIncome() {
        return netIncome;
    }

    @JsonProperty("netIncome")
    public void setNetIncome(BigInteger netIncome) {
        this.netIncome = netIncome;
    }
}
