
package com.example.gymservice.model.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachResp implements Serializable {

    private static final long serialVersionUID = 6620145415967224852L;

    private String id;
    private String name;

    @Override
    public String toString() {
        return "Coach{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
