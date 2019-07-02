package ca.jrvs.apps.twitter.dto;

public class Coordinates {
    private double[] coordiantes;
    private String type;

    public double[] getCoordiantes() {
        return coordiantes;
    }

    public void setCoordiantes(double[] coordiantes) {
        this.coordiantes = coordiantes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
