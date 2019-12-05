var sys = require("sys"),
	twitter = require("ntwitter"),
	mongoose = require("mongoose"),
	db_user,
	db_pass,
	db_url,
	db_port,
	db_name,
	coll,
	Schema = mongoose.Schema,
	ObjectId = Schema.ObjectId,
	TweetsSchema,
	tweetsModel,
	lastTweetId,
	page,
	twit;

// Define database settings
db_user = "CHANGME";
db_pass = "CHANGME";
db_url = "CHANGME";
db_port = 27017; // Change if required
db_name = "CHANGME";
coll = "tweets";
	
// Set up database schema for tweets
TweetsSchema = new Schema({
	_id: ObjectId,
	tweet: {}
});
mongoose.model("TweetsSchema", TweetsSchema);
tweetsModel = mongoose.model("TweetsSchema", coll);
	
// Connect to the database
mongoose.connect("mongodb://"+db_user+":"+db_pass+"@"+db_url+":"+db_port+"/"+db_name);

// Set up Twitter connection
twit = new twitter({
	consumer_key: "CHANGME",
	consumer_secret: "CHANGME",
	access_token_key: "CHANGME",
	access_token_secret: "CHANGME"
});

// ID of last tweet
lastTweetId = 0;

// Start on the first page of results
page = 1;

// Find last tweet added to the database 
tweetsModel.find({}, {"tweet.id_str": 1}).sort("tweet.id", "descending").limit(1).run(function (err, result) {
	if (err != null) {
		sys.puts(err);
		return;
	};
	
	var tweet = result[0].tweet;	
	lastTweetId = Number(tweet.id_str);
	
	retrieveOldTweets(lastTweetId, page);
});


// Catch up on previous tweets using the REST Search
function retrieveOldTweets(lastTweetId, page) {
	sys.log("Retrieving old tweets");
	twit.search("html5", {"rpp": 100, "result_type": "recent", "since_id": lastTweetId, "page": page}, function(err, data) {
		if (err != null) {
			sys.puts(err);
			return;
		};
		
		if (!data.results.length || data.results.length == 0) {
			sys.puts("No more search results");
			
			// Once caught up use the Streaming API for live tracking
			startStream();
			return;
		};
		
		var i, results = data.results;
		for (i = 0; i < results.length; i++) {
			tweet = results[i];
			addTweet(tweet);
		};
		
		retrieveOldTweets(lastTweetId, ++page);
	});	
};

// Grab tweets from the Streaming API 
function startStream() {
	sys.log("Retrieving live tweets");
	
	// This should probably grab a few previous tweets to make sure the transition is seamless
	twit.stream("statuses/filter", {track:"html5"}, function(stream) {
		stream.on("data", function (tweet) {
			addTweet(tweet);
		});
	
		stream.on("error", function (error) {
			sys.puts(sys.inspect(error));
			
			// Exit process so Forever can restart it
			stream.destroy();
			process.exit(1);
		});
	
		stream.on("end", function (end) {
			sys.puts(sys.inspect(end));
			
			// Exit process so Forever can restart it
			stream.destroy();
			process.exit(1);
		});
	});
};

// Add tweet to the database
function addTweet(tweet) {
	if (!tweet.id) {
		sys.puts("Tweet id is not present");
		return;
	};
	
	// Count existing tweets with the same id
	tweetsModel.count({"tweet.id": tweet.id}, function(err, count) {
		if (err != null) {
			sys.puts(err);
			return;
		};
		
		if (count > 0) {
			sys.puts("Tweet already exists with id "+tweet.id);
			return;
		};
		
		if (count == 0) {
			var t = new tweetsModel();
			t.tweet = tweet;

			// Convert string date to a JavaScript Date object
			t.tweet.created_at = new Date(tweet.created_at);

			t.save(function(err) {
				if (err != null) {
					sys.puts(err);
					return;
				};

				sys.puts("Added tweet with id "+tweet.id);
			});
		};
	});
};
