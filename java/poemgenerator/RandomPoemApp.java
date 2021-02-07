package poemgenerator;

import java.io.IOException;

import poemgenerator.logic.PoemBuilder;
import poemgenerator.logic.PoemBuilderInterface;

/**
 * @author sruthitalluri
 */
public class RandomPoemApp {

	private PoemBuilderInterface poemBuilder;

	public void createPoem(){
		poemBuilder = new PoemBuilder();
		try {
			System.out.println(poemBuilder.generatePoem().getAllLines());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new RandomPoemApp().createPoem();
	}
}
