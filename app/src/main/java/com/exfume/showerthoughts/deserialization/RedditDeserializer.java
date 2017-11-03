package com.exfume.showerthoughts.deserialization;

import com.exfume.showerthoughts.model.Post;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RedditDeserializer implements JsonDeserializer<List<Post>> {

    @Override
    public List<Post> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Post> list = new ArrayList<>();
        if (json.isJsonArray()) {
            json = json.getAsJsonArray().get(0);
        }
        JsonArray jArray = json.getAsJsonObject().getAsJsonObject("data").getAsJsonArray("children");
        for (int i = 0; i < jArray.size() ; i++) {
            JsonObject jObject = jArray.get(i).getAsJsonObject().getAsJsonObject("data");

            Post post = new Post.Builder()
                            .setTitle(jObject.get("title").getAsString())
                            .setAuthor(jObject.get("author").getAsString())
                            .setPermaLink(jObject.get("permalink").getAsString())
                            .setDate(jObject.get("created").getAsLong())
                            .setComments(jObject.get("num_comments").getAsInt())
                            .setScore(jObject.get("score").getAsInt())
                            .build();
            list.add(post);

        }

        return list;
    }
}
