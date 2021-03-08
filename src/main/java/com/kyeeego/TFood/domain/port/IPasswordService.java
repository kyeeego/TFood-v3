package com.kyeeego.TFood.domain.port;

public interface IPasswordService {
    String hash(String password);

    boolean verify(String hash, String password);
}
