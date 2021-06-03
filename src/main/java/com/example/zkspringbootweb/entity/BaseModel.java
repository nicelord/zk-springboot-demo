package com.example.zkspringbootweb.entity;

import io.ebean.Model;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
class BaseModel extends Model {

    @Id
    @GeneratedValue
    Long id;

}
