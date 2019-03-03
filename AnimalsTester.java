package animalstester;

public class AnimalsTester {
    public static void main(String[] args) {
        Dog jackie = new Dog();
        jackie.setAge(10);
        jackie.setWeight(7);
        jackie.setBreed("Poodle");

        System.out.println("Dog object jackie:");
        System.out.println("age: " + jackie.getAge());
        System.out.println("weight: " + jackie.getWeight());
        System.out.println("breed: " + jackie.getBreed());
        jackie.speak();
        jackie.wagTail();

        System.out.println("-------------------------");

        Cat garfield = new Cat();
        garfield.setBreed("Siamese");
        System.out.println("Cat object garfield:");
        System.out.println("age: " + garfield.getAge());
        System.out.println("weight: " + garfield.getWeight());
        System.out.println("breed: " + garfield.getBreed());
        garfield.speak();

    }
}
