package P4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AnimalList implements Iterable<Animal>, Serializable{
	private static final long serialVersionUID = 1L;
	AnimalNode<Animal> head, tail;
	private int size;
	
	public int size() {return size;}
	public boolean isEmpty() {return size == 0;}
	
	//adding
	public void addFirst(Animal animal) {
		AnimalNode<Animal> n = new AnimalNode<>(animal);
		if(head == null)
			head = tail = n;
		else {
			n.next = head;
			head = n;
		}
		size++;
	}
	public void addLast(Animal animal) {
		AnimalNode<Animal> n = new AnimalNode<>(animal);
		if(head == null)
			head = tail = n;
		else {
			tail.next = n;
			tail = n;
		}
		size++;
	}
	public void add(int idx, Animal animal) {
		if(idx<0 || idx>size) throw new IndexOutOfBoundsException();
		else if (idx == 0)
			addFirst(animal);
		else if (idx == size)
			addLast(animal);
		else {
			AnimalNode<Animal> n = new AnimalNode<>(animal);
			AnimalNode<Animal> prev = head;
			for(int i = 0; i<idx-1; i++)
				prev = prev.next;
			n.next = prev.next;
			prev.next = n;
			size++;
		}
	}
	public void add(Animal animal) {
		addLast(animal);
	}
	
	//removing
	public Animal removeFirst() {
		if(head == null)
			return null;
		AnimalNode<Animal> temp = head;
		head = head.next;
		if(head == null)
			tail = null;
		size--;
		return temp.element;
	}
	public Animal removeLast() {
		if(head == null)
			return null;
		else if (size == 1)
			return removeFirst();
		else {
			AnimalNode<Animal> temp = tail;
			AnimalNode<Animal> prev = head;
			for(int i = 0; i < size-2; i++)
				prev = prev.next;
			tail = prev;
			tail.next = null;
			size--;
			return temp.element;
		}
	}
	public Animal remove(int idx) {
		if(idx < 0 || idx >= size)
			return null;
		else if(idx == 0)
			return removeFirst();
		else if (idx == size-1)
			return removeLast();
		else {
			AnimalNode<Animal> prev = head;
			for(int i = 0; i < idx-1; i++)
				prev = prev.next;
			AnimalNode<Animal> current = prev.next;
			prev.next = current.next;
			size--;
			return current.element;
		}
	}
	
	//getting
	public Animal getFirst() {
		if(head == null) return null;
		else return head.element;
	}
	public Animal getLast() {
		if(tail == null) return null;
		else return tail.element;
	}
	public Animal get(int idx) {
		if(idx < 0 || idx >= size) return null;
		else if(idx == 0) return getFirst();
		else if(idx == size-1) return getLast();
		else {
			AnimalNode<Animal> current = head;
			for(int i = 0; i < idx; i++)
				current = current.next;
			return current.element;
		}
	}
	//setting
	public Animal set(int idx, Animal animal) { 
		if(idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
		add(idx, animal);
		return remove(idx+1);
	}
	
	//toString method
	public String toString() {
		String list = "";
		AnimalNode<Animal> current = head;
		while(current != null) {
			list += current.element.toString() + "\n";
			current = current.next;
		}
		return list;
			
	}
	
	//custom methods
	public AnimalList getHungryAnimals() {
		AnimalList list = new AnimalList();
		AnimalNode<Animal> anim = head;
		while(anim.next != null) {
			if(anim.element.getEnergy() < 50)
				list.addLast(anim.element);
			anim = anim.next;
		}			
		return list.isEmpty() ? null : list;
	}
	public AnimalList getStarvingAnimals(){
		AnimalList list = new AnimalList();
		AnimalNode<Animal> anim = head;
		while(anim.next != null) {
			if(anim.element.getEnergy() < 17)
				list.addLast(anim.element);
			anim = anim.next;
		}
		return list.isEmpty() ? null : list;
	}
	public AnimalList getAnimalsInBarn(){
		AnimalList list = new AnimalList();
		AnimalNode<Animal> anim = head;
		while(anim.next != null) {
			//making these temp variables so the 'if' statement isn't as long
			double x = anim.element.getX();
			double y = anim.element.getY();
			if(x >=450 && x <= 550 && y >= 50 && y <= 150)
					list.addLast(anim.element);
			anim = anim.next;
		}
		return list.isEmpty() ? null : list;
	}
	public double getRequiredFood() {
		AnimalNode<Animal> anim = head;
		double total = 0;
		while(anim.next != null) {
			total += (100-anim.element.getEnergy());
			anim = anim.next;
		}
		return total;
	}
	
	//bonus:
	public AnimalList getByType(Class<? extends Animal> anim) {
		AnimalList list = new AnimalList();
		AnimalNode<Animal> n = head;
		while(n != null) {
			if(anim.isInstance(n.element))
				list.addLast(n.element);
			n = n.next;
		}
		return list;
	}
	

	//iterator method
	public Iterator<Animal> iterator() {
		return new MyIterator();
	}
	
	//class: MyIterator
	private class MyIterator implements Iterator<Animal> {
		AnimalNode<Animal> current = head;
		public boolean hasNext() {
			return current != null;
		}
		public Animal next() {
			if(current == null) throw new NoSuchElementException();
			AnimalNode<Animal> temp = current;
			current = current.next;
			return temp.element;
		}
	}
	
	//class: AnimalNode
	@SuppressWarnings("hiding")
	private class AnimalNode<Animal> implements Serializable{
		private static final long serialVersionUID = 1L;
		ArrayList<String> animal = new ArrayList<>();
		
		Animal element;
		AnimalNode<Animal> next;
		public AnimalNode(Animal a) {
			animal.remove("A");
			element = a;
		}
	}

}
