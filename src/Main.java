import java.util.Random;
import java.io.IOException;

public class Main
{
    private final static String filename = "List.txt";
    private static Triangle triangles = new Triangle();

    private static void generate()
    {
        Random random = new Random();
        int size = random.nextInt(20) + 10;

        for(int i = 0; i < size; i++)
        {
            do
            {
                triangles.generate();
            }while(!triangles.isCorrect());
            triangles.calculate();
            triangles.addToList();
        }
    }
    public static void main(String[] args) throws IOException
    {
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

        Database.cleans();
        generate();
        Database.save(filename);
    }
}