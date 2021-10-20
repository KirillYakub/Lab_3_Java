import java.util.Random;
import java.io.IOException;

public class Main
{
    private final static String filename = "List.txt";

    private static void generate()
    {
        Random random = new Random();
        int size = random.nextInt(10) + 10;
        Triangle triangles = new Triangle();

        for(int i = 0; i < size; i++)
        {
            do
            {
                triangles.sideA = random.nextInt(15) + 5;
                triangles.sideB = random.nextInt(15) + 5;
                triangles.sideC = random.nextInt(15) + 5;
            }while(!triangles.isCorrect());
            triangles.calculate();
            triangles.addToList();
        }
    }
    public static void main(String[] args) throws IOException
    {
        generate();
        Database.save(filename);
        Database.load(filename);
        System.out.println("Результат нативной реализации №1:");
        Database.output();

        Database.serialize(filename);
        Database.deserialize(filename);
        System.out.println("Результат нативной реализации №2:");
        Database.output();

        Database.serializeFastJSON(filename);
        Database.deserializeFastJSON(filename);
        System.out.println("Результат внешней библиотеки FastJSON:");
        Database.output();
    }
}