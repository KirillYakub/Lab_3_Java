import java.io.Serializable;
import java.lang.Math;

public class Triangle implements Serializable
{
    private Database database;
    public double sideA, sideB, sideC, perimeter, area, angleA, angleB, angleC;

    public Triangle(double sideA, double sideB, double sideC, double perimeter, double area, double angleA, double angleB, double angleC)
    {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.perimeter = perimeter;
        this.area = area;
        this.angleA = angleA;
        this.angleB = angleB;
        this.angleC = angleC;
    }
    public Triangle()
    { database = new Database(); }

    public boolean isCorrect()
    {
        if(sideA + sideB > sideC || sideB + sideC > sideA || sideA + sideC > sideB)
            return true;
        else
            return false;
    }
    public void calculate()
    {
        perimeter = sideA + sideB + sideC;
        double half_perimeter = perimeter / 2;

        area = Math.round(Math.abs(Math.sqrt(half_perimeter * (half_perimeter - sideA) * (half_perimeter - sideB) * (half_perimeter - sideC))) * 1000d) / 1000d + 1;
        angleA = Math.round(Math.abs((Math.pow(sideA, 2) + Math.pow(sideC, 2) - Math.pow(sideB, 2)) / (2 * sideA * sideC) * 180 / Math.PI) * 1000d) / 1000d;
        angleB = Math.round(Math.abs((Math.pow(sideA, 2) + Math.pow(sideB, 2) - Math.pow(sideC, 2)) / (2 * sideA * sideB) * 180 / Math.PI) * 1000d) / 1000d;
        angleC = Math.round((180 - (angleA + angleB)) * 1000d) / 1000d;
    }
    public void addToList()
    { database.add(sideA, sideB, sideC, perimeter, area, angleA, angleB, angleC); }
}
