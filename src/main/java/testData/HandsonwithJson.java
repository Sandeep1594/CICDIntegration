package testData;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandsonwithJson {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		String json= "[{\"name\":\"Sandeep\",\"age\":25},{\"name\":\"Anuj\",\"age\":24}]";
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,Object>> data = mapper.readValue(json, new TypeReference<List<HashMap<String, Object>>>(){});
		System.out.println(data);
			
		}
				
	}

