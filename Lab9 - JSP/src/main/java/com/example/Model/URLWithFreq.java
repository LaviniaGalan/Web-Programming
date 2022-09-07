package com.example.Model;


import java.util.Objects;

public class URLWithFreq {
    private int frequency;

    private String urlAddress;

    public URLWithFreq() {

    }
    public URLWithFreq(String urlAddress, int frequency) {
        this.urlAddress = urlAddress;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }


    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }


    @Override
    public String toString() {
        return "URL{" +
                "urlAddress ='" + urlAddress + '\'' +
                ", frequency = " + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URLWithFreq url = (URLWithFreq) o;
        return frequency == url.frequency && Objects.equals(urlAddress, url.urlAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, urlAddress);
    }

}
