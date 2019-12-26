import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Scraper {
    static int depth = 4;

    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder conb = new ConfigurationBuilder();

        conb.setDaemonEnabled(true)
                .setOAuthConsumerKey("Z3WExA6iTKcIONDWGSebFqOHq")
                .setOAuthConsumerSecret("w3PJ1L043VvL1peZuARDUHuO1GlGolmZdS4jg1eFdXHenjclo7")
                .setOAuthAccessToken("1198388825690640389-03XqimPewaDSk4vCga5RXewGxTHxzt")
                .setOAuthAccessTokenSecret("MBsyW4F98IVmTmm8WbIbsxWaHfDi2qJaZ4vEUSzODCb5x");

        TwitterFactory twtfac = new TwitterFactory(conb.build());
        twitter4j.Twitter twt = twtfac.getInstance();

        List<Status> status = twt.getHomeTimeline();
        System.out.println("Tweets of user "+status.get(0).getUser().getScreenName());
        for (int i = status.size() - 1 ; i > depth ; i--) {
            status.remove(i);
        }
        status.remove(depth);
        for (Status st : status) //tweety
            System.out.println(st.getText());
        System.out.println(" ");

        String twitterScreenName = twt.getScreenName();
        IDs followerIDs = twt.getFollowersIDs(twitterScreenName, -1);
        long[] ids = followerIDs.getIDs();

        int howmany = 0;
        if(ids.length<depth)
            howmany = ids.length;
        else
            howmany = depth;
        long[] idsdepth = new long[howmany]; //main obserwatorzy
        for(int i=0; i<howmany ; i++)
            idsdepth[i]=ids[i];
        for (long id : idsdepth) { //pętla z main obserwatorami
            twitter4j.User user = twt.showUser(id); //bierzemy followera
            System.out.println(" Main follower's name: " + user.getScreenName()); //main obserwujący

            IDs followerIDsOfFollowers = twt.getFollowersIDs(user.getScreenName(), -1); //obserwujący obserwującego
            long[] fofIDs = followerIDsOfFollowers.getIDs(); //id followersów
            int howmany1 = 0;
            if(fofIDs.length<depth)
                howmany1 = fofIDs.length;
            else
                howmany1 = depth;
            long[] fofIDsdepth = new long[howmany1];
            for(int i=0; i<howmany1; i++)
                fofIDsdepth[i] = fofIDs[i];
            String followers = "";
            for (int i=0; i<fofIDsdepth.length; i++) {
                twitter4j.User user1 = twt.showUser(fofIDsdepth[i]);
                getfollowers(twt,user1.getId());
                followers += user1.getScreenName()+", ";
            }
            System.out.println("Followers of" + user.getScreenName() + ": " + followers);
            System.out.println(" ");
        }
    }

    public static void getfollowers(Twitter twt, long id) throws TwitterException {
            twitter4j.User user = twt.showUser(id); //bierzemy followera
            System.out.println(" Follower's name: " + user.getScreenName()); //main obserwujący

            IDs followerIDsOfFollowers = twt.getFollowersIDs(user.getScreenName(), -1); //obserwujący obserwującego
            long[] fofIDs = followerIDsOfFollowers.getIDs(); //id followersów
            int howmany1 = 0;
            if(fofIDs.length<depth)
                howmany1 = fofIDs.length;
            else
                howmany1 = depth;
            long[] fofIDsdepth = new long[howmany1];
            for(int i=0; i<howmany1; i++)
                fofIDsdepth[i] = fofIDs[i];
            String followers = "";
            for (int i=0; i<fofIDsdepth.length; i++) {
                twitter4j.User user1 = twt.showUser(fofIDsdepth[i]);
                followers += user1.getScreenName()+", ";
            }
            System.out.println("Followers of" + user.getScreenName() + ": " + followers);
            System.out.println(" ");
    }
}