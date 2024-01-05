import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

	public void addWord(String word) {
		v.add(word);
	}

	public void saveWords() throws IOException {
		FileWriter writer = new FileWriter("./resources/word.txt");
		for (String word : v) {
			writer.write(word + "\n");
		}
		writer.close();
	}
}
