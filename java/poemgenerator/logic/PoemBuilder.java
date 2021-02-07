package poemgenerator.logic;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import poemgenerator.entity.Poem;
import poemgenerator.entity.Rule;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PoemBuilder implements PoemBuilderInterface {

	public static final String POEM_RULES_FILE = "poem-rules.txt";
	public static final String LINEBREAK = "$LINEBREAK";
	public static final String END = "$END";
	// Types which can be end words
	public static final String[] END_WORDS = { "ADJECTIVE", "NOUN", "VERB" };

	private RuleBuilderInterface rb;
	private Poem thePoem;

	@Override
	public Poem generatePoem() throws IOException {
		List<String> fileContent = null;
		// Change the path accordingly
		fileContent = Files.readAllLines(Paths.get("/resources/"+POEM_RULES_FILE));
		rb = new RuleBuilder();
		this.thePoem = new Poem();

		for (String string : fileContent) {
			Rule rule = rb.buildRule(string);
			this.thePoem.getRules().put(rule.getName(), rule);
		}

		List<String> refRules = this.thePoem.getRules().get("POEM")
				.getKeywordsRefRuleGroup();

		for (String rule : refRules) {
			buildLine(rule);
		}
		
		return this.thePoem;
	}

	private void buildLine(String word) {

		if (word.startsWith("<") && word.endsWith(">")) {
			word = word.substring(1, word.length() - 1);
		}

		if (LINEBREAK.equals(word)) {
			getThePoem().addWord("\n");
		} else if (END.equals(word)) {
			// adjective, a noun or a verb
			getThePoem().addWord(" " + this.getRandomEndWord());
		} else {
			Rule mainRule = getThePoem().getRules().get(word);

			if (mainRule != null) {
				String randonWord = mainRule.getRandomWord();
				String randonKeywordRefRule = mainRule
						.getRandomKeywordRefRule();
				buildLine(randonWord);
				buildLine(randonKeywordRefRule);
			} else {
				getThePoem().addWord(" " + word);
			}
		}
	}

	private String getRandomEndWord() {
		Random rand = new Random();
		int randomNum = rand.nextInt(END_WORDS.length);
		String selRule = END_WORDS[randomNum];
		// Selecting a word from the group
		return getThePoem().getRules().get(selRule).getRandomWord();
	}

	public Poem getThePoem() {
		return thePoem;
	}

	public void setThePoem(Poem thePoem) {
		this.thePoem = thePoem;
	}
}
