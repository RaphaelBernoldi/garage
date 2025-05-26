package br.com.estapar.garage.webhook.operations;

public class CalculatorPrice {


    public static double calcLess25Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance < 25 ?  basePrice - (basePrice * 0.1) :result;
    }
    public static double calcBetween25And50Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 25 && totalOccupance <= 50 ?  basePrice : result;
    }
    public static double calcBetween50And75Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 50 && totalOccupance <= 75 ?  basePrice + (basePrice * 0.1) : result;
    }
    public static double calcBigger75Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 75 ?  basePrice + (basePrice * 0.25) : result;
    }

    public static double calcComplete(double totalOccupance, double basePrice){
        double result = 0.0;
        result = calcLess25Percent(totalOccupance, basePrice, result);
        result = calcBetween25And50Percent(totalOccupance, basePrice, result);
        result = calcBetween50And75Percent(totalOccupance, basePrice, result);
        result = calcBigger75Percent(totalOccupance, basePrice, result);
        return result;
    }
}
