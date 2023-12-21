import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TextSource {
	private Vector<String> v = new Vector<>();
	
	public TextSource() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("./resources/word.txt"));

		String str;
		while ((str = reader.readLine()) != null) {
			v.add(str);
		}
		reader.close();
	}
	
	public String get() {
		int index = (int)(Math.random()*v.size());
		return v.get(index);
	}
}
