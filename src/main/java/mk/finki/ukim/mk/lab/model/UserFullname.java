package mk.finki.ukim.mk.lab.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserFullname implements Serializable {
    public String name;
    public String surname;
}
