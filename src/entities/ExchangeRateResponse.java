package entities;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ExchangeRateResponse {

        @SerializedName("conversion_rates")
       public Map<String, Double> conversionRates;

}