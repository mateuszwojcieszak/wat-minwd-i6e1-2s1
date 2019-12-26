using System;
using System.Collections.Generic;

namespace git_66216_66045
{
    public class GithubUser : User
    {
        public Repo[] reposArray{get;set;}
        public GithubFollower[] followersArray{get;set;}

        public GithubUser(User user, Repo[] repos, GithubFollower[] followers)
        {
            this.reposArray = repos;
            this.followersArray = followers;
            this.login = user.login;
            this.id = user.id;
            this.node_id = user.node_id;
            this.avatar_url = user.avatar_url;
            this.gravatar_id = user.gravatar_id;
            this.url = user.url;
            this.html_url = user.html_url;
            this.followers_url = user.followers_url;
            this.following_url = user.following_url;
            this.gists_url = user.gists_url;
            this.starred_url = user.starred_url;
            this.subscriptions_url = user.subscriptions_url;
            this.organizations_url = user.organizations_url;
            this.repos_url = user.repos_url;
            this.events_url = user.events_url;
            this.received_events_url = user.received_events_url;
            this.type = user.type;
            this.site_admin = user.site_admin;
            this.name = user.name;
            this.company = user.company;
            this.blog = user.blog;
            this.location = user.location;
            this.email = user.email;
            this.hireable = user.hireable;
            this.bio = user.bio;
            this.followers = user.followers;
            this.following = user.following;
            this.created_at = user.created_at;
            this.updated_at = user.updated_at;
        }

        public GithubUser()
        {
        }

        public void findFollowersOfMyFollowersDepthTwo()
        {
            foreach(GithubFollower follower in followersArray)
            {
                follower.followers = Methods.GetGithubFollowers(follower.login);
            }
        }

        public void findFollowersOfMyFollowersDepthThree()
        {
            foreach(GithubFollower follower in followersArray)
            {
                follower.followers = Methods.GetGithubFollowers(follower.login);
                foreach(GithubFollower followerTwo in follower.followers)
                {
                    followerTwo.followers = Methods.GetGithubFollowers(follower.login);
                }
            }
        }

        public void findFollowersOfMyFollowersDepthFour()
        {
            foreach(GithubFollower follower in followersArray)
            {
                follower.followers = Methods.GetGithubFollowers(follower.login);
                foreach(GithubFollower followerTwo in follower.followers)
                {
                    followerTwo.followers = Methods.GetGithubFollowers(follower.login);
                    foreach(GithubFollower followerThree in followerTwo.followers)
                    {
                        followerThree.followers = Methods.GetGithubFollowers(follower.login);
                    }
                }
            }
        }

    }
}