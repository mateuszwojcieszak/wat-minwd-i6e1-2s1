package pl.pfpg.min3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pfpg.min3.model.InputBody;
import pl.pfpg.min3.model.UserInformation;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor

public class Controller {

  private final MainService mainService;

  @CrossOrigin
  @PostMapping("getUserData")
  public UserInformation getUserData(@RequestBody InputBody input){
    log.info("fire in the hole");
    UserInformation userData = mainService.getUserData(input.getInput());
    log.info(userData.getName());
    return userData;
  }
}


