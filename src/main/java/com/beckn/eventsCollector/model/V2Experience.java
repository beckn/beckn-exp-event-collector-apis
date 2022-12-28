package com.beckn.eventsCollector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "xc.experience")
public class V2Experience {
    @Id
    private int id;
    private String experience_id;
    private String experience_center_id;
    private String app_id;
    private Boolean active;
    private char user_review;
    private String user_comment;
    private Date start_ts;
    private Date end_ts;
    private Date created_at;
    private Date last_modified_at;
}
