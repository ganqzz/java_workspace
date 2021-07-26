package pluralsight;

public class NotifierStub implements Notifier {

	@Override
	public boolean send(String message) {
		// do nothing
		return true;
	}
}
