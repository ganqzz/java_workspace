package designpatterns.structural.proxy;

import java.util.List;

public class TwitterServiceImpl implements TwitterService {

	@Override
	public String getTimeline(String screenName) {
		return "Implementation";
	}

	@Override
	public void postToTimeline(String screenName, String message) {
		System.out.println(message);
	}
}
