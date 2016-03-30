/*
 * Copyright (c) 2016 www.teedrei.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.teedrei.localize;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.teedrei.SpringTest;
import com.teedrei.rools.helloworld.HelloWorldAction;
import com.teedrei.rools.rulesengine.Facts;
import com.teedrei.rools.rulesengine.RulesEngineService;

/**
 * A sample unit test case for A nested rule that only executes if the parent rule condition is satisfied
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/localize/spring-config.xml")
public class GreetingRuleTest extends SpringTest
{
	@Autowired
	private RulesEngineService rulesEngineService;

	/**
	 * Test where a name and a locale is found customizing the greeting further
	 */
	@Test
	public void runTestWithNameAndLocale()
	{
		// mock the data
		Facts facts = new Facts();
		facts.set("NAME", "John Doe");
		facts.set("LOCALE", new Locale("ph"));
		// call the rules engine to apply the rules
		rulesEngineService.applyRules(facts);

		String greeting = (String) facts.get(HelloWorldAction.GREETING_MESSAGE);
		// after the execution, certain rules should have been applied.
		Assert.assertEquals(greeting, "Kumusta John Doe");
	}

	/**
	 * Test where only a name is found and locale is not provided showing a default greeting message of Hello
	 */
	@Test
	public void runTestWithName()
	{
		// mock the data
		Facts facts = new Facts();
		facts.set("NAME", "John Doe");
		// call the rules engine to apply the rules
		rulesEngineService.applyRules(facts);

		String greeting = (String) facts.get(HelloWorldAction.GREETING_MESSAGE);
		// after the execution, certain rules should have been applied.
		Assert.assertEquals(greeting, "Hello John Doe");
	}

	/**
	 * Test where a name is not provided showing so no greeting or customize greeting is produced
	 */
	@Test
	public void runTestWithoutName()
	{
		// mock the data
		Facts facts = new Facts();
		// call the rules engine to apply the rules
		rulesEngineService.applyRules(facts);

		String greeting = (String) facts.get(HelloWorldAction.GREETING_MESSAGE);
		// after the execution, certain rules should have been applied.
		Assert.assertNull(greeting);
	}
}
