package ca.ualberta.cs.lonelytwitter.data;

import java.io.Serializable;
import java.util.Date;


public class NormalLonelyTweet extends LonelyTweet implements Serializable {

	public NormalLonelyTweet() {
	}

	public NormalLonelyTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

	@Override
	public String toString() {
		return getTweetDate() + " | " + getTweetBody();
	}

	public String getTweetBody() {
		return tweetBody;
	}
}
