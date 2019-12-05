package pl.pfpg.min3;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHPersonSet;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterator;
import org.kohsuke.github.PagedSearchIterable;
import org.springframework.stereotype.Service;
import pl.pfpg.min3.model.UserInformation;
import pl.pfpg.min3.model.response.Data;


@Service
@RequiredArgsConstructor
@Slf4j
public class GithubService {

  public static final String USERS = "Users";

  public UserInformation getUsersByUserName(String userName) {
    try {
      GitHub gitHub = GitHub.connectAnonymously();
      GHUserSearchBuilder ghUserSearchBuilder = gitHub.searchUsers();
      PagedSearchIterable<GHUser> list = ghUserSearchBuilder.q(userName).list();
      PagedIterator<GHUser> ghUserPagedIterator = list._iterator(0);
      GHUser ghUser = ghUserPagedIterator.next();
      UserInformation finalUser = createUser(ghUser);
      return finalUser;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Data fillUserWithFollowers(GHUser user) {
    Data dataFirst = new Data();
    dataFirst.setName(user.getLogin());
    try {
      GHPersonSet<GHUser> followers = user.getFollowers();
      followers.iterator().forEachRemaining(x -> {
        Data dataSecond = new Data();
        dataSecond.setName(x.getLogin());
        secondStageOfFollowers(dataSecond, x);
        dataFirst.getChildren().add(dataSecond);
      });
      return dataFirst;
    } catch (IOException e) {
      e.printStackTrace();
      return new Data();

    }
  }

  private void secondStageOfFollowers(Data dataSecond, GHUser ghUser) {
    try {
      ghUser.getFollowers().iterator().forEachRemaining(x -> {
      Data dataThird = new Data();
      dataThird.setName(x.getLogin());
      dataSecond.getChildren().add(dataThird);
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private UserInformation createUser(GHUser x) {
    try {
      log.info(x.getName());
      return UserInformation.builder().
          email(x.getEmail()).
          name(x.getLogin()).
          avatarUrl(x.getAvatarUrl()).
          blog(x.getBlog()).
          company(x.getCompany()).
          createdAt(x.getCreatedAt()).
          followersCount(x.getFollowersCount()).
          followingCunt(x.getFollowingCount()).
          publicRepos(x.getPublicRepoCount()).
          htmlUrl(x.getHtmlUrl().getPath()).
          data(fillUserWithFollowers(x)).
          build();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
