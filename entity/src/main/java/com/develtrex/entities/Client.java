package com.develtrex.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Data
@Log4j2
@Component
@EqualsAndHashCode
@Scope("prototype")
@Document(collection = "clients")
public class Client extends GlobalEntityPkString {
    // Fields
    private String names;
    private String lastnames;
    private String dni;
    private String phone;
    private String address;
}
