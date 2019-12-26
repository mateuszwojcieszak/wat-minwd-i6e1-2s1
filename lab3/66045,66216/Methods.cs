using System;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using RestSharp;

namespace git_66216_66045
{
    public static class Methods
    {   
        public static User GetUser(string username)
        {
            var client = new RestClient("http://api.github.com/users");
            var request = new RestRequest("/{username}",Method.GET);
            request.AddUrlSegment("username",username);
            var response = client.Execute(request);
            if(response.StatusCode.ToString() == "OK")
            {
                var user = JsonConvert.DeserializeObject<User>(response.Content);
                return user;
            }else
            {
                return new User();
            }
        }

        public static Repo[] GetRepos(string username)
        {
            var client = new RestClient("http://api.github.com/users");
            var request = new RestRequest("/{username}/repos",Method.GET);
            request.AddUrlSegment("username",username);
            var response = client.Execute(request);
            if(response.StatusCode.ToString() == "OK")
            {
                var repos = JsonConvert.DeserializeObject<IEnumerable<Repo>>(response.Content);
                return repos.ToArray();
            }else
            {
                List<Repo> repos = new List<Repo>();
                return repos.ToArray();
            }
        }

        public static Follower[] GetFollowers(string username)
        {
            var client = new RestClient("http://api.github.com/users");
            var request = new RestRequest("/{username}/followers",Method.GET);
            request.AddUrlSegment("username",username);
            var response = client.Execute(request);
            if(response.StatusCode.ToString() == "OK")
            {
                var followers = JsonConvert.DeserializeObject<IEnumerable<Follower>>(response.Content);
                return followers.ToArray();
            }else
            {
                List<Follower> followers = new List<Follower>();
                return followers.ToArray();
            }
        }

        public static GithubFollower[] GetGithubFollowers(string username)
        {
            List<GithubFollower> gits = new List<GithubFollower>();
            foreach(Follower follower in GetFollowers(username))
            {
                gits.Add(new GithubFollower(follower));
            }
            return gits.ToArray();
        }

        public static GithubFollower[] GetGithubFollowers(string username, int depth)
        {
            List<GithubFollower> gits = new List<GithubFollower>();
            foreach(Follower follower in GetFollowers(username))
            {
                gits.Add(new GithubFollower(follower));
            }
            return gits.ToArray();
        }
        
    }
}