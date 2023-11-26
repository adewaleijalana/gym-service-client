
package com.example.gymservice.model.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymResp implements Serializable {

    private static final long serialVersionUID = -8762368085927625036L;

    private String id;
    private String name;

    @Override
    public String toString() {
        return "GymResp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
