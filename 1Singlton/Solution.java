import animals.Animal;
import animals.Cat;
import animals.Dog;
import animals.Rat;

public class Solution {
    public static Animal readInitedAnimal(String animalType) {
        switch (animalType.toLowerCase()) {
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            case "rat":
                return new Rat();
            default:
                return () -> "Unknown Animal"; 
        }
    }
}
