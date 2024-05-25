package org.jibran.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {

    private String id;

    private String firstName;

    private Integer currentPosition;
}
