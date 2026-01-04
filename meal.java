public class meal {
    private String mealName;
    private int weightInKilograms;
    private double priceInDollars;

    public void Meal(String mealName) {
        this.mealName = mealName;
    }

    public void Meal(String meanlName, double priceInDollars) 
    {
        this.Meal(meanlName);
        this.priceInDollars = priceInDollars;
    }

    public void Meal(String meanlName, double priceInDollars, int weight) 
    {
        this.Meal(meanlName, priceInDollars);
        this.weightInKilograms = weight;
    }

    public String toString() {
        String result = mealName;
        result += " ";
        result += ((100*this.priceInDollars)/(this.weightInKilograms * 1000));
        return result;
    }

}