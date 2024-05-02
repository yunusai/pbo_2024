package responsi1;

public class Person {
    protected String name, address;
    String hobi;

    public Person(String name, String address, String hobi) {
        this.name = name;
        this.address = address;
        this.hobi = hobi;
    }

    public void identity() {
        System.out.println("Nama : " + name);
        System.out.println("Alamat : " + address);
    }

    public void Hobi() {
        System.out.println("Hobi : " + hobi);
    }
}
