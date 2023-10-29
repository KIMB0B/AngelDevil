import java.util.Vector;

public class TextSource {
	private Vector<String> v = new Vector<String>();
	
	public TextSource() {
		v.add("hello");
		v.add("Game");
		v.add("I love you");
		v.add("Java Good");
		v.add("Mobile");
		v.add("Computer");
		v.add("Raspberry");
	}
	
	public String get() {
		int index = (int)(Math.random()*v.size());
		return v.get(index);
	}
}
