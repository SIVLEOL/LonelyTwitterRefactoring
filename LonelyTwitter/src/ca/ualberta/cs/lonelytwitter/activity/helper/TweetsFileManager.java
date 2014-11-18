package ca.ualberta.cs.lonelytwitter.activity.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.lonelytwitter.data.LonelyTweet;

import android.content.Context;
import android.util.Log;

public class TweetsFileManager {

	private Context context;

	public TweetsFileManager(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public List<LonelyTweet> loadTweets(String inputFile) {
		List<LonelyTweet> tweets = new ArrayList<LonelyTweet>();

		try {
			FileInputStream fis = context.openFileInput(inputFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object object = ois.readObject();

			if (object instanceof ArrayList) {
				tweets = (ArrayList<LonelyTweet>) object;
			} else {
				Log.i("LonelyTwitter", "Error casting");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return tweets;
	}

	public void saveTweets(List<LonelyTweet> tweets, String outputFile) {
		try {
			FileOutputStream fos = context.openFileOutput(outputFile, 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(tweets);

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
