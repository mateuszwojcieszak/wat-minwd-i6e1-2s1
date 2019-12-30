using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Tweetinvi;
using Tweetinvi.Logic;
using Tweetinvi.Models;
using Tweetinvi.Parameters;
using TwitterClient.Model;
using Tweet = Tweetinvi.Tweet;

namespace TwitterClient.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TwitterController : ControllerBase
    {
        public TwitterController()
        {

        }

        [HttpGet("User/{userName}")]
        public IUser Get(string userName)
        {
            var user = Tweetinvi.User.GetUserFromScreenName(userName);

            if (user != null)
            {
                return user;
            }

            throw new Exception("UserIsNotExist");
        }

        [HttpGet("Tweets/{userName}")]
        public IEnumerable<ITweet> GetTweets(string userName, [FromQuery] int tweetsCount = 5)
        {
            var user = Tweetinvi.User.GetUserFromScreenName(userName);

            if (user != null)
            {
                var userTimelineParam = new UserTimelineParameters()
                {
                    MaximumNumberOfTweetsToRetrieve = tweetsCount,
                    IncludeRTS = true
                };

                var tweets = Timeline.GetUserTimeline(user, userTimelineParam).ToList();

                return tweets;
            }

            throw new Exception("UserIsNotExist");
        }
    }
}
