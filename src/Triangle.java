import java.io.Serializable;
import java.lang.Math;
import java.util.Random;

public class Triangle implements Serializable
{
    private Random random;
    private Database database;
    public int sideA, sideB, sideC;
    public double perimeter, area, angleA, angleB, angleC;

    public Triangle(int sideA, int sideB, int sideC, double perimeter, double area, double angleA, double angleB, double angleC)
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
    {
        database = new Database();
        random = new Random();
    }

    public void generate()
    {
        sideA = random.nextInt(10) + 5;
        sideB = random.nextInt(10) + 5;
        sideC = random.nextInt(15) + 5;
    }
    public boolean isCorrect()
    {
        if(sideA + sideB > sideC && sideC > sideA && sideC > sideB)
            return true;
        else if(sideB + sideC > sideA && sideA > sideB && sideA > sideC)
            return true;
        else if(sideA + sideC > sideB && sideB > sideC && sideB > sideA)
            return true;
        else
            return false;
    }
    public void calculate()
    {
        perimeter = sideA + sideB + sideC;
        double half_perimeter = perimeter / 2;

        area = Math.round(Math.sqrt(half_perimeter * (half_perimeter - sideA) * (half_perimeter - sideB) * (half_perimeter - sideC)) * 1000d) / 1000d;

        angleA = (Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2)) / (2 * sideB * sideC);
        angleA = Math.round(Math.toDegrees(Math.acos(angleA)) * 1000d) / 1000d;

        angleB = (Math.pow(sideB, 2) + Math.pow(sideA, 2) - Math.pow(sideC, 2)) / (2 * sideA * sideB);
        angleB = Math.round(Math.toDegrees(Math.acos(angleB)) * 1000d) / 1000d;

        angleC = Math.round((180 - (angleA + angleB)) * 1000d) / 1000d;
    }
    public void addToList()
    { database.add(sideA, sideB, sideC, perimeter, area, angleA, angleB, angleC); }
}
