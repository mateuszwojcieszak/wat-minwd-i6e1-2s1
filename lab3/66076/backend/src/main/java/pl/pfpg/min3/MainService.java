package pl.pfpg.min3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.pfpg.min3.model.InputRequest;
import pl.pfpg.min3.model.UserInformation;
import pl.pfpg.min3.model.UserQueryResponse;
import pl.pfpg.min3.model.response.Data;

@RequiredArgsConstructor
@Slf4j
@Service
public class MainService {

  private final GithubService githubService;

  public UserInformation getUserData(InputRequest inputRequest) {
    UserInformation userInformation = githubService.getUsersByUserName(inputRequest.getInput());
    return userInformation;
  }
}
