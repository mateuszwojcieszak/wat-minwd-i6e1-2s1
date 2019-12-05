package pl.pfpg.min3.model;


import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserQueryResponse {

  private List<UserInformation> users;
}
