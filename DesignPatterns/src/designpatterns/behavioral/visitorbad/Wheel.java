package designpatterns.behavioral.visitorbad;

public class Wheel implements AtvPart {

	@Override
	public double calculateShipping() {
		return 12;
	}
}
