package poemgenerator.logic;

import java.io.IOException;

import poemgenerator.entity.Poem;

public interface PoemBuilderInterface {

	public abstract Poem generatePoem() throws IOException;
}