
package com.example.gymservice.model.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSession {

    private CoachResp coach;
    private GymResp gym;
    private MemberResp member;
    private String weekday;

}
