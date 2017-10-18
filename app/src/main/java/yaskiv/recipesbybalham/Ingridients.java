package yaskiv.recipesbybalham;

/**
 * Created by yaskiv on 12.06.2016.
 */

public class Ingridients {
    public Ingridients(String name, String quantity,String measure) {
        Name = name;
        Quantity = quantity;
        Measure=measure;
    }

    private String Name;
    private String Quantity;

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    private String Measure;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
