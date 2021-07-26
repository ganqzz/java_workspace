package com.pluralsight;

public class CanonCameraFactory implements ICameraFactory {

	@Override
	public ICamera createCamera() {
		return new CanonCamera();
	}
}
