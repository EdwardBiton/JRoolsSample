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
package com.teedrei.rools.localize;

import java.util.Locale;
import java.util.ResourceBundle;

import com.teedrei.rools.helloworld.HelloWorldAction;
import com.teedrei.rools.helloworld.HelloWorldCondition;
import com.teedrei.rools.rulesengine.Action;
import com.teedrei.rools.rulesengine.Facts;

/**
 * Hello World Action will create a greeting message in the data context
 * 
 * @author edward.biton@gmail.com
 */
public class LocalizeGreetingAction implements Action
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teedrei.simplerulesengine.rulesengine.rules.Action#execute(com.teedrei.simplerulesengine.rulesengine.rules
	 * .Facts)
	 */
	@Override
	public void execute(Facts facts)
	{
		String name = (String) facts.get(HelloWorldCondition.NAME_KEY);
		Locale locale = (Locale) facts.get(LocalizeGreetingCondition.LOCALE_KEY);

		ResourceBundle messageBundle = ResourceBundle.getBundle("messages", locale);
		String greeting = messageBundle.getString("greeting");
		facts.set(HelloWorldAction.GREETING_MESSAGE, greeting + " " + name);
	}

}
