import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

public class ProfileHandler implements AjaxHandler
{
	public boolean isPage(){
		return true;
	}

	@Override
	public int service(HttpServletRequest req, HttpServletResponse resp, JsonNode request, ObjectNode response, String[] uriSplit, User u) throws ServletException, IOException {
		if(uriSplit.length == 0)
			return 404;
		if(!ObjectId.isValid(uriSplit[0]))
			return 404;
		ObjectId id = new ObjectId(uriSplit[0]);

		User user = (User)User.databaseConnectivity().getByInfoInDataBase(ID, id);

		if(user == null)
			return 404;
		response.put("data", user.toProfileNode());

		return 200;
	}
}
