package com.pluralsight;

public class NikonCamera implements ICamera {

	@Override
	public void takePhoto() {
		System.out.println("Nikon photo taken");
	}
}
