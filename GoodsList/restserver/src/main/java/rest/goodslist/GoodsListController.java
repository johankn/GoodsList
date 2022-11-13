package rest.goodslist;

import java.util.concurrent.atomic.AtomicLong;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import json.DataObject;
import json.FileOperator;

//import core.DataObject;

@RestController
public class GoodsListController {

  //./mvnw spring-boot:run

	private static final String template = "Yo waazap";

	@GetMapping("/file")
	public String getUsers() {
    FileOperator fo = new FileOperator();
    return fo.getAllUsersAsList("..//ui/src/main/resources/ui/dataObjects.json").toString();
	}
}
