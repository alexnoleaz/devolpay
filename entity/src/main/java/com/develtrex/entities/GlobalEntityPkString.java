package com.develtrex.entities;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Log4j2
@Data
public abstract class GlobalEntityPkString  implements Serializable {
    @Id
    private String id;
}
