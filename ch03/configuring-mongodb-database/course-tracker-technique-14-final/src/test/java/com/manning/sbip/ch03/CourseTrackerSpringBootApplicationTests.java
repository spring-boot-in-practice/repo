package com.manning.sbip.ch03;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() {
        // given
        DBObject object = BasicDBObjectBuilder.start().add("Manning", "Spring Boot In Practice").get();
        // when
        mongoTemplate.save(object, "collection");
        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
                .extracting("Manning")
                .containsOnly("Spring Boot In Practice");

    }
}
