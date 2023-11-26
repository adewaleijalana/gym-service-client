
package com.example.gymservice.model.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResp implements Serializable {

    private static final long serialVersionUID = 4978591047255218820L;

    private String id;
    private String name;

    @Override
    public String toString() {
        return "MemberResp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
