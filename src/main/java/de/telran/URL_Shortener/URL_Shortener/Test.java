package de.telran.URL_Shortener.URL_Shortener;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) {
        HashFunction murmur = Hashing.murmur3_32_fixed(123);

        String link = "https://www.baeldung.com/spring-data-derived-queries";

        System.out.println(murmur.hashString(link, Charset.defaultCharset()));
    } // http://localhost:8080/go/7837c903
}
