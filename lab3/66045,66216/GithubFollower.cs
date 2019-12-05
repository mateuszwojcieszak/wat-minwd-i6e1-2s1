using System;

namespace git_66216_66045
{
    public class GithubFollower : Follower
    {
        public GithubFollower[] followers{get;set;}

        public GithubFollower(Follower follower)
        {
            this.login = follower.login;
            this.id = follower.id;
            this.node_id = follower.node_id;
            this.avatar_url = follower.avatar_url;
            this.gravatar_id = follower.gravatar_id;
            this.url = follower.url;
            this.html_url = follower.html_url;
            this.followers_url = follower.followers_url;
            this.following_url = follower.following_url;
            this.gists_url = follower.gists_url;
            this.starred_url = follower.starred_url;
            this.subscriptions_url = follower.subscriptions_url;
            this.organizations_url = follower.organizations_url;
            this.repos_url = follower.repos_url;
            this.events_url = follower.events_url;
            this.received_events_url = follower.received_events_url;
            this.type = follower.type;
            this.site_admin = follower.site_admin;
        }

    }
}