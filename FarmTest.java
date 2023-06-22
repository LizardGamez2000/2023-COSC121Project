package P4;
//this class is similar to P3 but has more code for testing
public class FarmTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Farm myFarm = new Farm("stat.dat");
		System.out.println(myFarm.getAnimals().size()); //remove
		myFarm.getAnimals().addFirst(new Cow());
		myFarm.getAnimals().addLast(new Cow());
		myFarm.getAnimals().add(2, new Cow());
		Animal temp = myFarm.getAnimals().removeFirst();
		System.out.println("removed " + temp.getName());
		myFarm.getAnimals().set(0, new Llama());
		//you may want to test other linked list methods here
		for(Animal a: myFarm.getAnimals())
		a.setEnergy(10+Math.random()*90);
		System.out.printf("\nAvailable food before feeding: %.2f\n", 
		myFarm.getAvailableFood());
		System.out.println("\nInitial list of animals:\n-------------------------");
		myFarm.printAnimals();
		System.out.println("Adding a clone of the second animal\n----------------");
		myFarm.addClone(myFarm.getAnimals().get(1));
		myFarm.printAnimals();
		System.out.println("List of starving animals:\n------------");
		System.out.println(myFarm.getAnimals().getStarvingAnimals());
		System.out.println("List of hungry animals:\n------------");
		System.out.println(myFarm.getAnimals().getHungryAnimals());
		System.out.printf("Amount of food needed to feed all animals: %.2f\n",myFarm.getAnimals().getRequiredFood());
		System.out.println("\nFeeding animals:\n--------------");
		myFarm.feedAnimals();
		System.out.printf("\nAvailable food after feeding: %.2f\n", 
		myFarm.getAvailableFood());
		System.out.println("\nFarm summary:\n--------------");
		myFarm.printSummary();
		myFarm.exit("stat.dat");
		
		//Q: You will need to delete the stat.dat file that was previously created by your code from P3 (i.e. the one that 
		//uses the standard Java arrays), otherwise you will get an error. Can you explain why? (add your answer as 
		//a comment in FarmTest class
		
		//A: We need to delete the original stat.dat because we changed the data 'list' type from standard java arrays to a linked list. This would
		//cause a mismatch error since the two are not compatible with each other. The old farm still used arrays so loading it in wouldn't exactly
		//work very well now that the game is using a custom linked list.
	}
}
