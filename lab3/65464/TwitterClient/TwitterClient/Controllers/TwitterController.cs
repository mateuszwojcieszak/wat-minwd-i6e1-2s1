using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Tweetinvi;
using Tweetinvi.Logic;
using Tweetinvi.Models;
using TwitterClient.Model;

namespace TwitterClient.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TwitterController : ControllerBase
    {
        public TwitterController()
        {

        }

        [HttpGet(nameof(User))]
        public IAuthenticatedUser Get()
        {
            return Tweetinvi.User.GetAuthenticatedUser();
        }

        [HttpGet("Tweets")]
        public IEnumerable<ITweet> GetTweets([FromQuery] int tweetsCount = 5)
        {
            var user = Tweetinvi.User.GetAuthenticatedUser();

            var tweets = Timeline.GetUserTimeline(user, tweetsCount);

            return tweets;
        }


        [HttpGet("TestTweet")]
        public ITweet GetTestTweet()
        {
            return new TestTweet()
            {
                Text = "Text of tweet"
            };
        }
    }
}
