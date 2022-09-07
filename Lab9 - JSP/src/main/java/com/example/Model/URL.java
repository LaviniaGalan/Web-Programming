package com.example.Model;

import java.util.Objects;

public class URL {

    private String urlAddress;

    public URL(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URL url = (URL) o;
        return Objects.equals(urlAddress, url.urlAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlAddress);
    }

    @Override
    public String toString() {
        return "URL{" +
                "urlAddress='" + urlAddress + '\'' +
                '}';
    }
}
