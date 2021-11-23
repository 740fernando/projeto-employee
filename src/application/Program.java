package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class Program {
    public static void main(String args[]) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path : ");
        String path = sc.nextLine();

        System.out.print("Enter salary : ");
        Double salary = parseDouble(sc.nextLine());


        try (BufferedReader br = new BufferedReader((new FileReader(path)))) {

            List<Employee> list = new ArrayList<>();
            String lines = br.readLine();

            while(lines!=null){
                String[]fields =lines.split(";" );
                list.add(new Employee(fields[0],fields[1],Double.parseDouble(fields[2])));
                lines = br.readLine();
            }
            Comparator<String> comp=(x, y)->x.toUpperCase().compareTo(y.toUpperCase());
            List<String> names = list.stream()
                    .filter(e-> e.getSalary() > salary)
                    .map(e-> e.getEmail())
                    .sorted(comp)
                    .collect(Collectors.toList());

            System.out.println( "Email of people whose salary is more than 2000.00:");
            names.forEach(System.out::println);

            Double sum = list.stream()
                    .filter(e->e.getName().charAt(0)=='M' )
                    .map(e-> e.getSalary())
                    .reduce(0.0,(x,y)->x+y);

            System.out.println("Sum of salary of people whose name starts with 'M': "+sum.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
