import java.time.LocalDate;
import java.util.Set;

public class Person implements Comparable<Person>{
    private String fname, lname;
    private LocalDate birthDate;
    private Set<Person> children;
    public boolean adopt(Person child){
        return children.add(child);
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


    @Override
    public int compareTo(Person o) {
        this.birthDate.compareTo(o.birthDate);
    }
}
