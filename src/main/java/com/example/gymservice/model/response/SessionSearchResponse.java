package com.example.gymservice.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionSearchResponse implements Serializable {

    private static final long serialVersionUID = 5859587178591459396L;

    private String gymName;
    private String weekDay;
    private String coachName;
    private String memberNames;
}
