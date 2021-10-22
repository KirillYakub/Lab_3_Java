import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Database
{
    private static ArrayList<Triangle> list;

    public Database()
    { list = new ArrayList<>(); }

    public void add(int sideA, int sideB, int sideC, double perimeter, double area, double angleA, double angleB, double angleC)
    { list.add(new Triangle(sideA, sideB, sideC, perimeter, area, angleA, angleB, angleC)); }

    public static void cleans()
    { list.clear(); }

    public static void save(String filename) throws IOException
    {
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("SideA;SideB;SideC;Perimeter;Area;AngleA;AngleB;AngleC;");
        bufferedWriter.write(System.lineSeparator());
        for(Triangle triangle : list)
        {
            try {
                bufferedWriter.write(triangle.sideA + ";");
                bufferedWriter.write(triangle.sideB + ";");
                bufferedWriter.write(triangle.sideC + ";");
                bufferedWriter.write((int) triangle.perimeter + ";");
                bufferedWriter.write((int) triangle.area + ";");
                bufferedWriter.write((int) triangle.angleA + ";");
                bufferedWriter.write((int) triangle.angleB + ";");
                bufferedWriter.write((int) triangle.angleC + ";");
                bufferedWriter.write(System.lineSeparator());
            }catch (IOException e) {
                System.out.println("Error writing to file: " + e);
                System.out.println();
            }
        }
        bufferedWriter.close();
        fileWriter.close();
    }
    public static void load(String filename) throws IOException
    {
        cleans();
        String str;
        Scanner scanner = new Scanner(new FileReader(filename));
        scanner.nextLine();
        while(scanner.hasNextLine())
        {
            str = scanner.nextLine();
            String[] strings = str.split(";");
            list.add(new Triangle(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]),
                    Double.parseDouble(strings[3]), Double.parseDouble(strings[4]), Double.parseDouble(strings[5]),
                    Double.parseDouble(strings[6]), Double.parseDouble(strings[7])));
        }
        scanner.close();
    }

    public static void serialize(String filename)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
            outputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e);
            System.out.println();
        }
    }
    public static void deserialize(String filename)
    {
        cleans();
        try
        {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Triangle>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException e) {
            System.out.println("Error reading from file: " + e);
            System.out.println();
        } catch (ClassNotFoundException exception){
            System.out.println("Triangle class not found");
            System.out.println();
        }
    }

    public static void serializeFastJSON(String filename) throws IOException
    {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(outStream);
        bufferedWriter.write(JSON.toJSONString(list));
        bufferedWriter.close();
        outStream.close();
    }
    public static void deserializeFastJSON(String filename) throws IOException
    {
        cleans();
        Scanner scanner = new Scanner(new FileReader(filename));
        ArrayList<JSONObject> JSONList = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject jsonObject : JSONList)
        {
            list.add(new Triangle(jsonObject.getIntValue("sideA"), jsonObject.getIntValue("sideB"), jsonObject.getIntValue("sideC"),
                    jsonObject.getDoubleValue("perimeter"), jsonObject.getDoubleValue("area"), jsonObject.getDoubleValue("angleA"),
                    jsonObject.getDoubleValue("angleB"), jsonObject.getDoubleValue("angleC")));
        }
        scanner.close();
    }

    public static void output()
    {
        for(Triangle triangle : list)
        {
            System.out.println("Triangle: Side A = " + triangle.sideA + " сm, Side B = " + triangle.sideB + " cm, Side C = " + triangle.sideC + " cm");
            System.out.println("Angle A = " + triangle.angleA + " deg, Angle B = " + triangle.angleB + " deg, Angle C = " + triangle.angleC + " deg");
            System.out.println("Area = " + triangle.area + " сm^2, Perimeter = " + triangle.perimeter + " cm");
            System.out.println();
        }
    }
}