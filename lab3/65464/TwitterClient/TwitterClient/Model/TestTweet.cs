using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Tweetinvi.Models;
using Tweetinvi.Models.DTO;
using Tweetinvi.Models.Entities;

namespace TwitterClient.Model
{
    public class TestTweet  //ITweet
    {
        public long Id { get; }
        public string IdStr { get; }
        public Task<ITweet> PublishRetweetAsync()
        {
            throw new NotImplementedException();
        }

        public Task<List<ITweet>> GetRetweetsAsync()
        {
            throw new NotImplementedException();
        }

        public Task FavoriteAsync()
        {
            throw new NotImplementedException();
        }

        public Task UnFavoriteAsync()
        {
            throw new NotImplementedException();
        }

        public Task<IOEmbedTweet> GenerateOEmbedTweetAsync()
        {
            throw new NotImplementedException();
        }

        public Task<bool> DestroyAsync()
        {
            throw new NotImplementedException();
        }

        public bool Equals(ITweet other)
        {
            throw new NotImplementedException();
        }

        public void Favorite()
        {
            throw new NotImplementedException();
        }

        public void UnFavorite()
        {
            throw new NotImplementedException();
        }

        public ITweet PublishRetweet()
        {
            throw new NotImplementedException();
        }

        public List<ITweet> GetRetweets()
        {
            throw new NotImplementedException();
        }

        public bool UnRetweet()
        {
            throw new NotImplementedException();
        }

        public bool Destroy()
        {
            throw new NotImplementedException();
        }

        public IOEmbedTweet GenerateOEmbedTweet()
        {
            throw new NotImplementedException();
        }

        public DateTime CreatedAt { get; }
        public string Text { get; set; }
        public string Prefix { get; }
        public string Suffix { get; }
        public string FullText { get; set; }
        public int[] DisplayTextRange { get; }
        public int[] SafeDisplayTextRange { get; }
        public IExtendedTweet ExtendedTweet { get; set; }
        public ICoordinates Coordinates { get; set; }
        public string Source { get; set; }
        public bool Truncated { get; }
        public int? ReplyCount { get; set; }
        public long? InReplyToStatusId { get; set; }
        public string InReplyToStatusIdStr { get; set; }
        public long? InReplyToUserId { get; set; }
        public string InReplyToUserIdStr { get; set; }
        public string InReplyToScreenName { get; set; }
        public IUser CreatedBy { get; }
        public ITweetIdentifier CurrentUserRetweetIdentifier { get; }
        public int[] ContributorsIds { get; }
        public IEnumerable<long> Contributors { get; }
        public int RetweetCount { get; }
        public ITweetEntities Entities { get; }
        public bool Favorited { get; }
        public int FavoriteCount { get; }
        public bool Retweeted { get; }
        public bool PossiblySensitive { get; }
        public Language? Language { get; }
        public IPlace Place { get; }
        public Dictionary<string, object> Scopes { get; }
        public string FilterLevel { get; }
        public bool WithheldCopyright { get; }
        public IEnumerable<string> WithheldInCountries { get; }
        public string WithheldScope { get; }
        public ITweetDTO TweetDTO { get; set; }
        public DateTime TweetLocalCreationDate { get; }
        public List<IHashtagEntity> Hashtags { get; }
        public List<IUrlEntity> Urls { get; }
        public List<IMediaEntity> Media { get; }
        public List<IUserMentionEntity> UserMentions { get; }
        public List<ITweet> Retweets { get; set; }
        public bool IsRetweet { get; }
        public ITweet RetweetedTweet { get; }
        public int? QuoteCount { get; set; }
        public long? QuotedStatusId { get; }
        public string QuotedStatusIdStr { get; }
        public ITweet QuotedTweet { get; }
        public bool IsTweetPublished { get; }
        public bool IsTweetDestroyed { get; }
        public string Url { get; }
    }
}
