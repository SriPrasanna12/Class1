package org.test;

import java.awt.AWTException;
import java.io.IOException;

public class Firstclass extends BaseClass {
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		launchBrowser("chrome");
		launchurl("http://demo.automationtesting.in/Frames.html");
		implicitwait(20);
		String url = getcurrenturl();
		System.out.println(url);
	    screenshot("sri.png");
	    getcurrenturl();}}