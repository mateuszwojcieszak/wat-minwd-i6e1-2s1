using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using RestSharp;

namespace git_66216_66045
{
    [ApiController]
    [Produces("application/json")]
    [Route("[controller]")]
    public class BaseController : ControllerBase
    {
        [HttpGet]
        [Route("{username}/{depth}")]
        public GithubUser GetUserInfo(string username, int depth)
        {
            if(depth<1 || depth>3)
            {
                throw new ArgumentOutOfRangeException("Value out of range. Maximum value is 4, minimum 1.");
            }
            else
            {
                GithubUser user = new GithubUser(Methods.GetUser(username), Methods.GetRepos(username), Methods.GetGithubFollowers(username));
                if(depth == 2)
                {
                    user.findFollowersOfMyFollowersDepthTwo();
                }else if(depth==3)
                {
                    user.findFollowersOfMyFollowersDepthThree();
                }else if(depth==4)
                {
                    user.findFollowersOfMyFollowersDepthFour();
                }
                return user;
            }
        }

    }
}