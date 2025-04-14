import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Person implements Comparable<Person>{
    private String fname, lname;
    private LocalDate birthDate;

    private LocalDate deathDate;
    private Set<Person> children;
    public boolean adopt(Person child){
        return children.add(child);
    }

    public Person(String fname, String lname, LocalDate birthDate, LocalDate deathDate) {
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.children = new TreeSet<>();
    }
    public Person(String fname, String lname, LocalDate birthDate) {
        this.fname = fname;
        this.lname = lname;
        this.birthDate = birthDate;
        this.children = new TreeSet<>();
    }

    public Person getYoungestChild(){
//        if(children.isEmpty())
//            return null;
        LocalDate youngestChildAge = LocalDate.MIN;
        Person youngestChild = null;
        for (Person child : children){
            if(child.birthDate.isAfter(youngestChildAge)) {
                youngestChildAge = child.birthDate;
                youngestChild = child;
            }
        }
        return youngestChild;
    }
    public List<Person> getChildren(){
        return List.copyOf(children);
    }

    @Override
    public int compareTo(Person o) {
        return this.birthDate.compareTo(o.birthDate);
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public static Person fromCsvLine(String line){
        String[] colums = line.split(",");
        String[] flname = colums[0].split(" ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthDate = null;
        LocalDate deathDate = null;
        if (isNotEmpty(colums[1])){
            birthDate = LocalDate.parse(colums[1], formatter);
        }
        if (isNotEmpty(colums[2])){
            deathDate = LocalDate.parse(colums[2], formatter);
        }
        return new Person(flname[0], flname[1], birthDate, deathDate);
    }

    public static List<Person> fromCsv(String path){
        List<Person> people = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                Person readPerson = fromCsvLine(line);
                people.add(readPerson);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist");
        } catch (IOException e) {
            System.err.println("Error during reading file");
        }
        return people;
    }



    public static boolean isNotEmpty(String s){
        return s != null && s != "" && s != " " && s != "\t";
    }
}
