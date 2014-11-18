package ca.ualberta.cs.lonelytwitter.activity;

import java.util.Date;
import java.util.List;

import ca.ualberta.cs.lonelytwitter.R;
import ca.ualberta.cs.lonelytwitter.activity.helper.TweetsFileManager;
import ca.ualberta.cs.lonelytwitter.data.ImportantTweet;
import ca.ualberta.cs.lonelytwitter.data.LonelyTweet;
import ca.ualberta.cs.lonelytwitter.data.NormalLonelyTweet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<LonelyTweet> tweets;
	private ArrayAdapter<LonelyTweet> tweetAdapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		setTweets(tweetsProvider.loadTweets("file.sav"));
		tweetAdapter = new ArrayAdapter<LonelyTweet>(this, R.layout.list_item,
				getTweets());
		oldTweetsList.setAdapter(tweetAdapter);
	}

	public void save(View v) {
		String text = bodyText.getText().toString();

		LonelyTweet tweet = createTweet(text);

		if (tweet.isValid()) {
			getTweets().add(tweet);
			tweetAdapter.notifyDataSetChanged();

			bodyText.setText("");
			tweetsProvider.saveTweets(getTweets(), "file.sav");
		} else {
			Toast.makeText(this, "Invalid tweet", Toast.LENGTH_SHORT).show();
		}
	}

	private LonelyTweet createTweet(String text) {
		LonelyTweet tweet;

		if (text.contains("*")) {
			tweet = new ImportantTweet(text);
		} else {
			tweet = new NormalLonelyTweet(text, new Date());
		}
		return tweet;
	}

	public void clear(View v) {
		getTweets().clear();
		tweetAdapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(getTweets(), "file.sav");
	}

	private List<LonelyTweet> getTweets() {
		return tweets;
	}

	private void setTweets(List<LonelyTweet> tweets) {
		this.tweets = tweets;
	}

}