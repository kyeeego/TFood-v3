package com.kyeeego.TFood.domain.types;

import lombok.Data;

@Data
public class Minerals {
    private float Ca;
    private float P;
    private float Mg;
    private float Na;
    private float Fe;
    private float Zn;
    private float I;
    private float Se;
    private float F;

    public Minerals add(Minerals other, float k) {
        Ca += other.Ca * k;
        P += other.P * k;
        Mg += other.Mg * k;
        Na += other.Na * k;
        Fe += other.Fe * k;
        Zn += other.Zn * k;
        I += other.I * k;
        Se += other.Se * k;
        F += other.F * k;
        return this;
    }
}
