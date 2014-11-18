package ca.ualberta.cs.lonelytwitter.data;

import java.util.Date;

import android.util.Log;


public class ImportantTweet extends LonelyTweet {

	@Override
	public String toString() {
		String importantString = "IMPORTANT " + getTweetDate() + " | " + getTweetBody();
		Log.i("TAG", importantString);
		return importantString;
	}

	public ImportantTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

	public ImportantTweet(String text) {
		this.tweetDate = new Date();
		this.tweetBody = text;
	}
	
	@Override
	public boolean isValid() {
		if (tweetBody.trim().length() == 0
				|| tweetBody.trim().length() > 10) {
			return false;
		}
	
		return true;
	}

	public String getTweetBody() {
		return "IMPORTANT " + tweetBody;
	}
	
}
