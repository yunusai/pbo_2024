package responsi1;

import responsi1.Student;

public class Person extends Student {
    String hobi;

    public Person(String name, String nim, String address, String modul, int sks, int spp, String hobi) {
        super(name, nim, address, modul, sks, spp);
        this.hobi = hobi;
    }

    public void getPerson() {
        System.out.println("Hobi : " + hobi);
    }
}
