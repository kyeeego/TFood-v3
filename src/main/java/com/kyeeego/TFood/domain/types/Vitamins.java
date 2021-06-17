package com.kyeeego.TFood.domain.types;

import lombok.Data;

@Data
public class Vitamins {
    private float C;
    private float A;
    private float D;
    private float E;
    private float K;
    private float B1;
    private float B2;
    private float B3;
    private float B5;
    private float B6;
    private float B9;
    private float B12;
    private float H;

    public Vitamins add(Vitamins other, float k) {
        C += other.C * k;
        A += other.A * k;
        D += other.D * k;
        E += other.E * k;
        K += other.K * k;
        B1 += other.B1 * k;
        B2 += other.B2 * k;
        B3 += other.B3 * k;
        B5 += other.B5 * k;
        B6 += other.B6 * k;
        B9 += other.B9 * k;
        B12 += other.B12 * k;
        H += other.H * k;
        return this;
    }
}
