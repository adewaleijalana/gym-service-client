
package com.example.gymservice.model.response;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSessions implements Serializable {

    private static final long serialVersionUID = -3115908926715216234L;

    private List<SingleTrainingSession> trainingSessions;

}
