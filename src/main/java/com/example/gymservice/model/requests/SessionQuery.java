package com.example.gymservice.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionQuery implements Serializable {

    private static final long serialVersionUID = -3104965819427386261L;

    private String coachName;
    private String [] weekDays;
}
