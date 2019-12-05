package pl.pfpg.min3.model.response;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Data {

  String name;
  List<Data> children;

  public Data(){
    children = new LinkedList<>();
  }
}
