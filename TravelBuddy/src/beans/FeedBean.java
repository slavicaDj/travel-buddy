package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import clients.feed.FeedClient;
import model.Feed;

@ManagedBean(name="feedBean")
@SessionScoped
public class FeedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5874864811403887391L;
	private ArrayList<Feed> feeds = new ArrayList<>();
	
	public FeedBean() {
		feeds = FeedClient.getFeeds();
	}
	
	public ArrayList<Feed> getFeeds() {
		return feeds;
	}
	
	public void setFeeds(ArrayList<Feed> feeds) {
		this.feeds = feeds;
	}

}
