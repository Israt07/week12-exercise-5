package com.weatherstation.rainfalldata;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class RainfallDataConsumer {
    public static void main(String[] args) {
        String fileName = "rainfall_data.bin"; //location of the file .
        
        
        //try and catch
        try {
            consumeRainfallData(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consumeRainfallData(String fileName) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            String stationName = dis.readUTF();
            String district = dis.readUTF();
            int numDays = dis.readInt();

            double[] rainfallData = new double[numDays];
            for (int i = 0; i < numDays; i++) {
                rainfallData[i] = dis.readDouble();
            }

            System.out.println("Station Name: " + stationName);
            System.out.println("District: " + district);
            System.out.println("Rainfall Data: ");
            for (double rainfall : rainfallData) {
                System.out.println(rainfall);
            }

            double averageRainfall = computeAverageRainfall(rainfallData);
            System.out.println("Average Rainfall: " + averageRainfall);
        }
    }

    private static double computeAverageRainfall(double[] rainfallData) {
        double sum = 0;
        for (double rainfall : rainfallData) {
            sum += rainfall;
        }
        return sum / rainfallData.length;
    }
}
