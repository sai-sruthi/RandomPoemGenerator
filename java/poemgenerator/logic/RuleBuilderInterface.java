package poemgenerator.logic;

import poemgenerator.entity.Rule;

public interface RuleBuilderInterface {

	public abstract Rule buildRule(String str);

}