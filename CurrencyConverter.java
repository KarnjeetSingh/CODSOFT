public class CurrencyConverter {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            String urlStr = BASE_URL + API_KEY + "/latest/" + baseCurrency;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder responseStrBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

            double rate = conversionRates.getDouble(targetCurrency);
            double convertedAmount = rate * amount;

            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("Error fetching exchange rates. Please check currency codes and try again.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
