package pl.pfpg.min3.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.pfpg.min3.GithubService;

@RequiredArgsConstructor
@Slf4j
@Service
public class MainService {

  private final GithubService githubService;

  public UserInformation getUserData(InputRequest inputRequest) {
    UserQueryResponse usersByUserName = githubService.getUsersByUserName(inputRequest.input);
    if(usersByUserName.getUsers().isEmpty()){
      log.info("Nie ma takiego uzytkownika githuba");
    }
    UserInformation userInformation = usersByUserName.getUsers().get(0);
    return userInformation;
  }
}
